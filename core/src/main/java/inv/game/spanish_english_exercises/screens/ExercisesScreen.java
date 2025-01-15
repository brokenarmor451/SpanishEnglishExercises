package inv.game.spanish_english_exercises.screens;

import inv.game.spanish_english_exercises.SpEnExercises;
import inv.game.spanish_english_exercises.dialogs.ConfirmationDialog;
import inv.game.spanish_english_exercises.objects.ButtonRun;
import inv.game.spanish_english_exercises.objects.ChoiceInput;
import inv.game.spanish_english_exercises.objects.LearningStages;
import inv.game.spanish_english_exercises.objects.Level;
import inv.game.spanish_english_exercises.objects.Utility;
import inv.game.spanish_english_exercises.objects.ButtonRun.Callback;
import inv.game.spanish_english_exercises.save.GameState;
import inv.game.spanish_english_exercises.save.StageState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ExercisesScreen extends ScreenAdapter{
    private boolean isLevelFinishDialogShowing;
    private boolean isStageFinishDialogShowing;

    public String currentLevelText;

    private SpEnExercises game;
    private String[][] titlesAndChoices;

    private Stage stage;
    private Stage stage2;
    private Stage stage3;

    private ScreenViewport viewport2;
    private FitViewport viewport;
    private FillViewport viewport3;

    //public Level currentLevel;

    private final int numberOfButtons = 8;
    private final int numberOfLevels = 3;
    private int selectedStage;

    private Table root;
    private Table hudRoot;
    private Table choicesTable;
    private Label title;
    private Label currentLevelLabel;

    private Container<ImageButton> menuContainer;
    public Container<ImageTextButton>[] buttonContainers;

    public ButtonStyle[] buttonStyles;

    private GameState gameState;
    private StageState savedState;

    private ConfirmationDialog menuDialog;
    private Dialog levelFinishDialog;
    private Dialog stageFinishDialog;

    private WindowStyle dialogStyle;

    private LabelStyle[] labelStyles; // use as background for dialog icon texts

    private HashMap<String, String> translations = new HashMap<>(20);


    public ExercisesScreen(SpEnExercises game) {
      this.game = game;
    }

     @Override
     public void show() {
        viewport = new FitViewport(800, 1600);
        viewport2 = new ScreenViewport();
        viewport3 = new FillViewport(768, 1216);

        stage = new Stage(viewport, game.batch);
        stage2 = new Stage(viewport2, game.batch);
        stage3 = new Stage(viewport, game.batch);

        menuContainer = new Container<>();

        game.playBackgroundMusic();

        gameState = game.getGameState();
        setSavedState(game.getStageState(selectedStage));
        setTitlesAndChoices(new LearningStages().get(selectedStage));
        makeTranslations();
        makeIndices();
        initSavedState();
        makeStyles();
        makeTables();
        makeTitle();
        makeButtons();
        makeCurrentLevelLabel();
        initClickables();
        makeRoot();
        makeHud();
        makeLevelFinishDialog();
        makeStageFinishDialog();
        stage.addActor(root);
        stage2.addActor(hudRoot);

        final InputMultiplexer multiplexer = new InputMultiplexer(new ExitInput(), stage3, stage, stage2);

        Gdx.input.setInputProcessor(multiplexer);
     }

     @Override
     public void render(float delta) {
      ScreenUtils.clear(Color.BLACK);

      game.batch.setProjectionMatrix(viewport3.getCamera().combined);
      viewport3.apply();
      drawBackground();

        viewport.apply();
        stage.act(delta);
        stage.draw();

        viewport2.apply();
        stage2.act(delta);
        stage2.draw();

        if(menuDialog.getIsShowing() || isLevelFinishDialogShowing || isStageFinishDialogShowing) {
         Utility.dimBackground(game, viewport2);
        }

        viewport.apply();
        stage3.act(delta);
        stage3.draw();

     }

     @Override
     public void resize(int width, int height) {
        viewport.update(width, height, true);
        viewport2.update(width, height, true);
        viewport3.update(width, height,true);
        updateSizes(height);
     }

     @Override
     public void hide() {
      game.stopBackgroundMusic();
      Gdx.input.setInputProcessor(null);
     }

     private void drawBackground() {
      game.batch.begin();
      game.backgrounds[2].draw(game.batch);
      game.batch.end();
     }

     private void updateSizes(int height) {
      final int newSize = height / 8;

      menuContainer.size(newSize, newSize);
      menuContainer.getActor().setSize(newSize, newSize);
      menuContainer.getActor().setOrigin(Align.center);

      hudRoot.getCells().get(0).expand().bottom().left().pad(15);
     }

     private void makeTranslations() {
      for(int i = 0; i < 20; i++) {
         translations.put(titlesAndChoices[0][i], titlesAndChoices[1][i]);
      }
     }

     public void makeIndices() {
      if(savedState.buttonIndices == null) {
         savedState.buttonIndices = new ArrayList<ArrayList<Integer>>(3);
         savedState.titleIndices = new ArrayList<ArrayList<Integer>>(3);
         savedState.buttonContainerIndices = new ArrayList<Integer>(8);
         
         for(int i = 0; i < 3; i++) {
            savedState.buttonIndices.add(new ArrayList<Integer>());
            savedState.titleIndices.add(new ArrayList<Integer>());
         }

         for(int i = 0; i < 20; i++) {
            if(i > 15) {
               savedState.buttonIndices.get(2).add(i);
               savedState.titleIndices.get(2).add(i);
               continue;
            }

            if(i > 7) {
               savedState.buttonIndices.get(1).add(i);
               savedState.titleIndices.get(1).add(i);
               continue;
            }

            if(i >= 0 ) {
               savedState.buttonIndices.get(0).add(i);
               savedState.titleIndices.get(0).add(i);
               savedState.buttonContainerIndices.add(i);
            }
         }

         for(int i = 0; i < 4; i++) {
            savedState.buttonIndices.get(2).add(i);
            savedState.titleIndices.get(2).add(i);
         }

         Collections.shuffle(savedState.buttonIndices.get(0));
         Collections.shuffle(savedState.buttonIndices.get(1));
         Collections.shuffle(savedState.buttonIndices.get(2));

         Collections.shuffle(savedState.titleIndices.get(0));
         Collections.shuffle(savedState.titleIndices.get(1));
         Collections.shuffle(savedState.titleIndices.get(2));

         Collections.shuffle(savedState.buttonContainerIndices);
      }
     }

     private void makeTitle() {
      LabelStyle ls = new LabelStyle(game.fonts[5], null);
      ls.background = game.skin.getDrawable("title_bg");
      title = new Label(titlesAndChoices[0][savedState.titleIndices.get(savedState.currentLevel).get(savedState.titleIndex)], ls);
      title.setAlignment(Align.center);
     }

     private void makeButtons() {

      buttonContainers = new Container[numberOfButtons];

      for(int i = 0; i < numberOfButtons; i++) {
         final String choiceText = titlesAndChoices[1][savedState.buttonIndices.get(savedState.currentLevel).get(i)];
         final ImageTextButton button = new ImageTextButton(choiceText, savedState.founds.get(choiceText) ? (ImageTextButtonStyle) buttonStyles[1]: (ImageTextButtonStyle) buttonStyles[0]);
         buttonContainers[i] = new Container<ImageTextButton>(button).size(325,200);
         Utility.enableScale(button);
         buttonContainers[i].addListener(new ChoiceInput(game,i, this, buttonContainers[i]));
      }
     
      addButtonsToTable();

     }

     private void makeStyles() {
      final NinePatchDrawable np = new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[3],45,45,45,45));
      buttonStyles = new ButtonStyle[]{
         new ImageTextButtonStyle(game.skin.getDrawable("choice_button"), game.skin.getDrawable("choice_button"), null, game.fonts[4]),
         new ImageTextButtonStyle(game.skin.getDrawable("choice_button_green"), game.skin.getDrawable("choice_button_green"), null, game.fonts[4]),
         new ImageButtonStyle(game.skin.get("menu", ImageButtonStyle.class)),
         new ImageTextButtonStyle(np,np, null, game.fonts[3]),
         new ImageButtonStyle(game.skin.get("main_menu", ImageButtonStyle.class)),
         new ImageButtonStyle(game.skin.get("next_level", ImageButtonStyle.class)),
     };
      dialogStyle = new WindowStyle(game.fonts[0], Color.BLACK, null);

      labelStyles = new LabelStyle[] {
         new LabelStyle(game.fonts[0], null) {
            {
               background = new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[1],45,45,45,45));
            }
         },

         Utility.makeLabelStyle(new Color(0xabe4ebff), game.fonts[10])
      };
     }

     private void makeCurrentLevelLabel() {
      updateCurrentLevelText();
      currentLevelLabel = new Label("Level " + currentLevelText ,labelStyles[0]);
      currentLevelLabel.setAlignment(Align.center);
     }

     private void updateCurrentLevelLabel() {
      updateCurrentLevelText();
      currentLevelLabel.setText("Level " + currentLevelText);
     }

     public void updateCurrentLevelText() {
      currentLevelText = String.valueOf(savedState.currentLevel + 1);
     }

     public void addButtonsToTable() {
         choicesTable.add(buttonContainers[savedState.buttonContainerIndices.get(0)]).size(325,200).expandX().padRight(50).padBottom(50);
         choicesTable.add(buttonContainers[savedState.buttonContainerIndices.get(1)]).size(325,200).padBottom(50);
         choicesTable.row();
         choicesTable.add(buttonContainers[savedState.buttonContainerIndices.get(2)]).size(325, 200).expandX().padRight(50).padBottom(50);
         choicesTable.add(buttonContainers[savedState.buttonContainerIndices.get(3)]).size(325, 200).padBottom(50);
         choicesTable.row();
         choicesTable.add(buttonContainers[savedState.buttonContainerIndices.get(4)]).size(325, 200).expandX().padRight(50).padBottom(50);
         choicesTable.add(buttonContainers[savedState.buttonContainerIndices.get(5)]).size(325, 200).padBottom(50);
         choicesTable.row();
         choicesTable.add(buttonContainers[savedState.buttonContainerIndices.get(6)]).size(325, 200).expandX().padRight(50);
         choicesTable.add(buttonContainers[savedState.buttonContainerIndices.get(7)]).size(325, 200);
    }

     public void updateButtonStyle(int index, Container button, int styleIndex) {
      if(index == -1) {
         ((ImageTextButton) button.getActor()).setStyle(buttonStyles[styleIndex]);
      } else {
         ((ImageTextButton) buttonContainers[index].getActor()).setStyle(buttonStyles[styleIndex]);
      }
     }

     public boolean checkTextsAndUpdate(String s1, String s2) {
      if(s1.equals(s2)) {
         savedState.titleIndex++;
         savedState.foundCount++;

         return true;
      }
         return false;
     }

     public boolean isAllWordsFound() {
      if(savedState.foundCount == numberOfButtons) {
         return true;
      }

      return false;
     }

     public boolean isAllLevelsFinished() {
      if(savedState.currentLevel + 1 == numberOfLevels) {
         return true;
      } else {
         return false;
      }
     }

     public boolean isAlreadyFound(String text) {
      if(savedState.founds.get(text)) {
         return true;
      } else {
         return false;
      }
     }

     public void checkSelectionAndUpdate(Container button,int choiceIndex, String buttonText) {
      if(checkTextsAndUpdate(buttonText, translations.get(title.getText().toString()))) {
         if(isAllWordsFound()) {
            if(isAllLevelsFinished()) {
               showStageFinishDialog();
            } else {
               showLevelFinishDialog();
            }            
         } else {
            markFound(buttonText);
            updateTitle();
         }

         updateButtonStyle(-1,button, 1);

         button.setTouchable(Touchable.disabled);
         
      } else {
         if(!isAlreadyFound(buttonText)) {

            switch(gameState.gameDifficulty) {
               case EASY:
                  shuffleTitles();
                  resetLevel(true);
                  shuffleAndReassignTable();
                  break;
               case MEDIUM:
                  if(savedState.currentLevel == 0) {
                     shuffleTitles();
                     resetLevel(true);
                     shuffleAndReassignTable();
                     break;
                  } else {
                     resetMediumDifficulty();
                     break;
                  }
               case HARD:

                  if(selectedStage == 0 && savedState.currentLevel == 0) {
                     shuffleTitles();
                     resetLevel(true);
                     shuffleAndReassignTable();
                     break;
                  } else if(selectedStage == 0 && savedState.currentLevel >= 1) {
                     resetMediumDifficulty();
                     break;
                  } else {
                     game.restartFromStage1();
                     game.setScreen(game.screens.get("Main Menu"));
                     break;
                  }
            }
            
        }
      }
     }

     private void initClickables() {
      for(int i = 0; i < 8; i++) {
         if(savedState.founds.get(buttonContainers[i].getActor().getText().toString())) {
            buttonContainers[i].setTouchable(Touchable.disabled);
         }
      }
     }

     public void initSavedState() {
      if(savedState.founds == null) {
         savedState.founds = new HashMap<>(8);
         //Gdx.app.log("CRASHING", String.valueOf(savedState.buttonIndices.get(2).size()));
         for(int i = 0; i < 8; i++) {
            savedState.founds.put(titlesAndChoices[1][savedState.buttonIndices.get(savedState.currentLevel).get(i)], false);
         }
      }
     }

     public void markFound(String text) {
      savedState.founds.replace(text, true);
     }

     private void shuffleChoices() {
      Collections.shuffle(savedState.buttonContainerIndices); 
     }

     private void shuffleAndReassignTable() {
      choicesTable.clear();
      shuffleChoices();
      addButtonsToTable();
     }

     private void shuffleTitles() {
      Collections.shuffle(savedState.titleIndices.get(savedState.currentLevel));
     }

     public void reset() {
      savedState.reset();
      for(int i = 0; i < 8; i++) {
         savedState.founds.replace(titlesAndChoices[1][savedState.buttonIndices.get(savedState.currentLevel).get(i)], false);
      }
     }

     private void resetLevel(boolean withUpdateStyle) {
      reset();

      shuffleTitles();

      updateTitle();

      if(withUpdateStyle) {
         for(int i = 0; i < numberOfButtons; i++) {
            updateButtonStyle(i, null,0);
         }
      }

      for(int i = 0; i < 8; i++) {
         if(buttonContainers[i].getTouchable() == Touchable.disabled) {
            buttonContainers[i].setTouchable(Touchable.enabled);
         }
      }
     }

     public void resetMedium() {
      savedState.resetMedium();
      for(int i = 0; i < 8; i++) {
         savedState.founds.put(titlesAndChoices[1][savedState.buttonIndices.get(savedState.currentLevel).get(i)], false);
      }
      Collections.shuffle(savedState.buttonContainerIndices);
     }

     private void resetMediumDifficulty() {
      resetMedium();
      updateCurrentLevelLabel();
      resetLevel(false);
      choicesTable.clear();
      makeButtons();
     }

     public void updateTitle() {
      title.setText(titlesAndChoices[0][savedState.titleIndices.get(savedState.currentLevel).get(savedState.titleIndex)]);
     }

     public void makeRoot() {   
      root.add(title).size(500,100).padBottom(65);
      root.row();
      root.add(choicesTable).padBottom(25);
      root.row();
      root.add(currentLevelLabel).expand().bottom().right();
     }

     public void makeTables() {
      root = new Table();
      root.setSize(800,1000);
      root.setY(400);
      hudRoot = new Table();
      hudRoot.setFillParent(true);
      choicesTable = new Table();
     }

     public void makeHud() {
      final Callback noUp = () -> {
         hide();
       };
   
       final Callback yesUp = () -> {
         game.saveStageStates();
         game.setScreen(game.screens.get("Main Menu"));
       };


      menuDialog = new ConfirmationDialog(dialogStyle, game, (ImageTextButtonStyle) buttonStyles[3], "Return to the main menu ?", yesUp);
      menuDialog.make();

      final Callback menuUp = () -> {
         
         menuContainer.setVisible(false);
         
         menuDialog.show(stage3);
      };


      final ImageButton menuButton = (ImageButton) Utility.makeButton(null, (ImageButtonStyle) buttonStyles[2], game.height / 8, game.height / 8);
      menuContainer = (Container<ImageButton>) Utility.makeButtonContainer(game, menuButton, game.height / 8, game.height / 8 , menuUp);

      menuDialog.setActor(menuContainer);

      hudRoot.add(menuContainer).expand().bottom().left().pad(15);
     }

     public void makeLevelFinishDialog() {
      levelFinishDialog = new Dialog("", dialogStyle);

      final ImageButton mainMenuButton = (ImageButton)Utility.makeButton(null, buttonStyles[4], 150,150);
      final ImageButton nextLevelButton = (ImageButton)Utility.makeButton(null, buttonStyles[5], 150,150);

      final Label nextLevelText = new Label("Next Level", labelStyles[0]);
      final Label mainMenuText = new Label("Main Menu", labelStyles[0]);

      nextLevelText.setAlignment(Align.center);
      mainMenuText.setAlignment(Align.center);


      final Label infoLabel = new Label("Level Completed", labelStyles[1]);

      final Callback mainMenuUp = () -> {
         hideLevelFinishDialog();
         if(savedState.currentLevel + 1 < numberOfLevels) {
            moveToNextLevelGraphical(true);;
         }

         game.saveStageStates();
         game.setScreen(game.screens.get("Main Menu"));
      };

      final Callback nextLevelUp = () -> {
         hideLevelFinishDialog();
         if(savedState.currentLevel + 1 < numberOfLevels) {
            moveToNextLevelGraphical(false);
         }
         
      };

      final Container mainMenuContainer = Utility.makeButtonContainer(game, mainMenuButton, 150,150, mainMenuUp);
      final Container nextLevelContainer = Utility.makeButtonContainer(game, nextLevelButton, 150,150, nextLevelUp);


      final Table t = levelFinishDialog.getContentTable();
      final Table t2 = new Table();
      final Table t3 = new Table();

      t2.add(nextLevelText).padRight(20).height(100);
      t2.add(nextLevelContainer);

      t3.add(mainMenuText).padRight(20).height(100);
      t3.add(mainMenuContainer);

      t.add(infoLabel);
      t.row();
      t.add(t2);
      t.row();
      t.add(t3);

      
     }

     public void makeStageFinishDialog() {
      stageFinishDialog = new Dialog("", dialogStyle);

      final Label infoLabel = new Label("Stage Completed", labelStyles[1]);

      final ImageButton mainMenuButton = (ImageButton) Utility.makeButton(null, buttonStyles[4], 150,150);

      final Label mainMenuText = new Label("Main Menu", labelStyles[0]);

      final Callback mainMenuUp = () -> {
         isStageFinishDialogShowing = false;
         gameState.enterToLearning = true;

         gameState.unlockNextStage();
         game.saveStageStates();
         game.saveGameState();
         game.setScreen(game.screens.get("Main Menu"));
      };

      final Container mainMenuContainer = Utility.makeButtonContainer(game, mainMenuButton, 150,150, mainMenuUp);


      final Table t = stageFinishDialog.getContentTable();
      final Table t2 = new Table();

      t2.add(mainMenuText).padRight(20).height(100);
      t2.add(mainMenuContainer);

      t.add(infoLabel);
      t.row();
      t.add(t2);

     }

     public void moveToNextLevel() {
      savedState.currentLevel++;
      //updateCurrentLevelText();
      savedState.founds = new HashMap<>(8);

      for(int i = 0; i < 8; i++) {
         savedState.founds.put(titlesAndChoices[1][savedState.buttonIndices.get(savedState.currentLevel).get(i)], false);
      }
      
     }

     private void moveToNextLevelGraphical(boolean isMainMenuEntered) {
      moveToNextLevel();
      updateCurrentLevelLabel();
      resetLevel(false);
      
      if(!isMainMenuEntered) {
         choicesTable.clear();
         makeButtons();
      }
     }

     private void showLevelFinishDialog() {
      menuContainer.setVisible(false);
      isLevelFinishDialogShowing = true;
      levelFinishDialog.show(stage3);
     }

     private void hideLevelFinishDialog() {
      menuContainer.setVisible(true);
      isLevelFinishDialogShowing = false;
      levelFinishDialog.hide();
     }

     private void showStageFinishDialog() {
      menuContainer.setVisible(false);
      game.playStageCompletedSound();
      isStageFinishDialogShowing = true;
      stageFinishDialog.show(stage3);
     }

     private void showExitDialog() {
      menuContainer.setVisible(false);
      menuDialog.show(stage3);
     }

     private void hideExitDialog() {
      menuContainer.setVisible(true);
      menuDialog.hide();
     }

     public void setSelectedStage(int index) {
      selectedStage = index;
     }

     public void setTitle(Label title) {
      this.title = title;
     }

     public void setTitlesAndChoices(String[][] texts) {
      titlesAndChoices = texts;
     }

     public String[][] getTitlesAndChoices() {
      return titlesAndChoices;
     }

     public StageState getStageState() {
      return savedState;
     }

     public void setSavedState(StageState stageState) {
      savedState = stageState;
     }


     public final class ExitInput extends InputAdapter {
	    @Override
      public boolean keyDown(int keycode) {

        if(keycode == Keys.BACK) {
            if(menuDialog.getIsShowing()) {
               hideExitDialog();
            } else if(!isLevelFinishDialogShowing && !isStageFinishDialogShowing){
               showExitDialog();
            }
          return true;
        }
        return false;
      }
	}
} 