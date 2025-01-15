package inv.game.spanish_english_exercises.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import inv.game.spanish_english_exercises.SpEnExercises;
import inv.game.spanish_english_exercises.dialogs.ConfirmationDialog;
import inv.game.spanish_english_exercises.dialogs.ConfirmationDialog;
import inv.game.spanish_english_exercises.objects.ButtonRun;
import inv.game.spanish_english_exercises.objects.Utility;
import inv.game.spanish_english_exercises.objects.ButtonRun.Callback;
import inv.game.spanish_english_exercises.save.GameState;
import inv.game.spanish_english_exercises.save.GameState.Difficulty;


// Reason to using container is use size of container to buttons to propely get button place while checking on onUp

public class MainMenu extends ScreenAdapter {
    public final SpEnExercises game;

    private boolean isSettingsDialogShowing;
    private boolean isDifficultyChanged;
    private final int numberOfStages = 20;

    private Table root;
    private Table headTable;
    private Table buttonsTable;
    
    private ScrollPane buttonsScrollPane;
    
    private Container settingsContainer;
    private Container exercisesContainer;
    private Container learningContainer;
    private Container[] stageContainers;

    final private ImageTextButton[] difficultyButtons = new ImageTextButton[3];

    private Label gameTypeText;
    public LabelStyle titleStyle;

    private FitViewport viewport;
    private ScreenViewport viewport2;
    private FillViewport viewport3;

    private Stage stage;
    private Stage stage2;
    private Stage stage3;

    public ButtonStyle[] buttonStyles;

    private Button exercisesButton;
    private Button learningButton;

    private Dialog settingsDialog;
    private ConfirmationDialog exitDialog;
    private ConfirmationDialog difficultyDialog;

    private WindowStyle exitDialogStyle;
    private WindowStyle settingsDialogStyle;

    private LabelStyle labelStyle;
    private LabelStyle iconLabelStyle;

    private Difficulty difficulty;

    private final GameState gameState;

    private final String difficultyInformationText = "Difficulty Information\n\nWhenever a wrong choice is selected:\n\nEASY: Resets the current level\nof the selected stage.\n\nMEDIUM: Restarts from the\nfirst level of the selected stage.\n\nHARD: Deletes all progress and\nrestarts from the first stage.";

    public MainMenu(SpEnExercises game) {
        this.game = game;
        gameState = game.getGameState();
    }

    @Override
    public void show() {
        

        viewport = new FitViewport(800, 1600);
        viewport2 = new ScreenViewport();
        viewport3 = new FillViewport(768, 1216);
        stage = new Stage(viewport, game.batch);
        stage2 = new Stage(viewport2, game.batch);
        stage3 = new Stage(viewport, game.batch);

        makeStyles();
        makeWidgets();
        makeSettingsDialog();
        makeDifficultyDialog();
        makeButtons();
        makeHead();
        updateClickables();
        stage.addActor(root);
        stage2.addActor(headTable);

        InputMultiplexer multiplexer = new InputMultiplexer(new ExitInput(), stage3, stage,stage2);

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

        if(exitDialog.getIsShowing() || isSettingsDialogShowing || difficultyDialog.getIsShowing()) {
            Utility.dimBackground(game, viewport2);
        }

        viewport.apply();
        stage3.act(delta);
        stage3.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport2.update(width, height, true);
        viewport3.update(width,height,true);
        updateSizes(height);
    }

    private void drawBackground() {
        game.batch.begin();
        game.backgrounds[1].draw(game.batch);
        game.batch.end();
    }

    public void makeHead() {
        
        final Container c = new Container();

        final ImageButton settingsButton = new ImageButton((ImageButtonStyle) buttonStyles[6]);
        settingsButton.setSize(game.height / 15, game.height / 15);
        Utility.enableScale(settingsButton);
        settingsContainer = new Container<>(settingsButton).size(game.height / 15, game.height / 15);

        exercisesButton = new ImageButton((ImageButtonStyle) buttonStyles[2]);
        exercisesButton.setSize(game.height / 8, 225 * game.height / 4800);
        Utility.enableScale(exercisesButton);

        learningButton = new ImageButton((ImageButtonStyle) buttonStyles[1]);
        learningButton.setSize(game.height / 8, 225 * game.height / 4800);
        Utility.enableScale(learningButton);

        exercisesContainer = new Container(exercisesButton).size(game.height / 8, 225 * game.height / 4800);
        learningContainer = new Container(learningButton).size(game.height / 8, 225 * game.height / 4800);

        final Callback settingsUp = () -> {
            showSettingsDialog();
        };

        final Callback exercisesUp = () -> {
            gameState.enterToLearning = false;
            c.setActor(learningContainer);
            gameTypeText.setText("EXERCISES");
            updateButtonStyles((ImageTextButtonStyle) buttonStyles[5], false);
            updateClickables();
        };

        final Callback learningUp = () -> {
            gameState.enterToLearning = true;
            c.setActor(exercisesContainer);
            gameTypeText.setText("LEARNING");
            updateButtonStyles((ImageTextButtonStyle) buttonStyles[0], true);
            updateClickables();
        };


        exercisesContainer.addListener(new ButtonRun(game,exercisesContainer, null, exercisesUp));
        learningContainer.addListener(new ButtonRun(game,learningContainer, null, learningUp));
        settingsContainer.addListener(new ButtonRun(game,settingsContainer, null, settingsUp));

        c.setActor(gameState.enterToLearning ? exercisesContainer : learningContainer);
        
        headTable.add(settingsContainer).size(game.height / 15, game.height / 15).top().left().pad(15).expand();
        headTable.add(c).size(game.height / 8, 225 * game.height / 4800).top().right().pad(15).expand();

        exitDialog.actorToShow = headTable;

    }

    private void updateSizes(int height) {
        final int newWidth = height / 8;
        final int newHeight = 225 * height / 4800;

        exercisesContainer.size(newWidth, newHeight);
        exercisesContainer.getActor().setSize(newWidth, newHeight);

        learningContainer.size(newWidth,newHeight);
        learningContainer.getActor().setSize(newWidth, newHeight);

        settingsContainer.size(height / 15, height / 15);
        settingsContainer.getActor().setSize(height / 15, height / 15);

        headTable.getCells().get(0).size(height / 15, height / 15).top().left().pad(15).expand();
        headTable.getCells().get(1).size(newWidth, newHeight).top().right().pad(15).expand();


        exercisesContainer.getActor().setOrigin(Align.center);
        learningContainer.getActor().setOrigin(Align.center);
        settingsContainer.getActor().setOrigin(Align.center);

    }

    public void makeButtons() {
        for(int i = 0; i < numberOfStages; i ++) {

            if(i % 4 == 0) {
                buttonsTable.row();
            }

            if(gameState.unlockedStages[i] == 'O' || gameState.unlockedStages[i] == 'Y') {

                final int i2 = i;
                Callback stageButtonUp = () -> {
                    enterToStage(i2);
                };

                ImageTextButton stageButton = new ImageTextButton(String.valueOf(i + 1), (ImageTextButtonStyle) buttonStyles[0]);
                Utility.enableScale(stageButton);
                
                stageContainers[i] = new Container(stageButton).size(150,150);
                stageContainers[i].addListener(new ButtonRun(game,stageContainers[i], null, stageButtonUp));

                buttonsTable.add(stageContainers[i]).pad(25);

            } else {
 
                ImageButton stageButton = new ImageButton((ImageButtonStyle) buttonStyles[3]);
                Utility.enableScale(stageButton);

                buttonsTable.add(stageButton).pad(25);
            }
        }

        if(!gameState.enterToLearning) {
           updateButtonStyles((ImageTextButtonStyle) buttonStyles[5], false);
        }
    }

    private void updateButtonStyles(ImageTextButtonStyle style, boolean withText) {
        for(int i = 0; i < numberOfStages; i ++) {
            if(gameState.unlockedStages[i] == 'Y') {
                ((ImageTextButton) stageContainers[i].getActor()).setText(withText ? String.valueOf(i + 1): "");;
                ((ImageTextButton) stageContainers[i].getActor()).setStyle(style);
            } else if(gameState.unlockedStages[i] == 'O') {
                ((ImageTextButton) stageContainers[i].getActor()).setText(String.valueOf(i + 1));;
                ((ImageTextButton) stageContainers[i].getActor()).setStyle(buttonStyles[0]);
            }
        }
    }

    private void updateClickables() {
        for(int i = 0; i < numberOfStages; i++) {
            if(gameState.unlockedStages[i] == 'Y' || gameState.unlockedStages[i] == 'O') {
                if(((ImageTextButton) stageContainers[i].getActor()).getStyle() == buttonStyles[5]) {
                    stageContainers[i].setTouchable(Touchable.disabled);
                } else {
                    stageContainers[i].setTouchable(Touchable.enabled);
                }
            }
        }
    }

    private void updateSettingsButtonStyle(ImageButton button, ImageButtonStyle style) {
        button.setStyle(style);
    }

    private void enterToStage(int index) {
        if(gameState.enterToLearning) {

            final LearnScreen ls = (LearnScreen) game.screens.get("Learn Screen");
            ls.setSelectedStage(index);
            game.setScreen(ls);
        } else if(gameState.unlockedStages[index] == 'O'){
            final ExercisesScreen es = (ExercisesScreen) game.screens.get("Exercises Screen");
            es.setSelectedStage(index);
            gameState.setCurrentStage(index);
            game.setScreen(es);
        }
    }

    public void makeWidgets() {
        makeTitle();
        root = new Table();
        root.setSize(800, 1300);
        root.setY(100);
        buttonsTable = new Table();
        buttonsScrollPane = new ScrollPane(buttonsTable);
        root.add(gameTypeText).size(400,100).padBottom(50);
        root.row();
        root.add(buttonsScrollPane).size(800,1200);
        headTable = new Table();
        headTable.setFillParent(true);
        stageContainers = new Container[numberOfStages];


        exitDialog = new ConfirmationDialog(exitDialogStyle, game, (ImageTextButtonStyle) buttonStyles[4], "Do you want to exit ?", Gdx.app::exit);
        exitDialog.make();

        settingsDialog = new Dialog("", settingsDialogStyle);
    }

    private void makeTitle() {
        gameTypeText = new Label(gameState.enterToLearning ? "LEARNING" : "EXERCISES", titleStyle);
        gameTypeText.setAlignment(Align.center);
    }

    private void makeStyles() {
        final NinePatchDrawable np = new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[3], 45,45,45,45));

        buttonStyles = new ButtonStyle[] {
            new ImageTextButtonStyle(game.skin.getDrawable("stage_button"), game.skin.getDrawable("stage_button"), null, game.fonts[6]),
            game.skin.get("learning", ImageButtonStyle.class),
            game.skin.get("exercises", ImageButtonStyle.class),
            game.skin.get("lock", ImageButtonStyle.class),
            new ImageTextButtonStyle(np,np, null, game.fonts[0]),
            new ImageTextButtonStyle(game.skin.getDrawable("completed"), game.skin.getDrawable("completed"), null, game.fonts[6]),
            game.skin.get("settings", ImageButtonStyle.class),
            game.skin.get("music_on", ImageButtonStyle.class),
            game.skin.get("music_off", ImageButtonStyle.class),
            game.skin.get("sound_on", ImageButtonStyle.class),
            game.skin.get("sound_off", ImageButtonStyle.class),
            new ImageTextButtonStyle(game.skin.getDrawable("button_bg_two"), game.skin.getDrawable("button_bg_two"), null,game.fonts[8]),
            new ImageTextButtonStyle(game.skin.getDrawable("button_bg_two_checked"), game.skin.getDrawable("button_bg_two_checked"), null,game.fonts[8]),
            game.skin.get("close", ImageButtonStyle.class),
            game.skin.get("about", ImageButtonStyle.class)
        };
        iconLabelStyle = new LabelStyle(game.fonts[8], null);
        iconLabelStyle.background = new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[1], 45,45,45,45));
        labelStyle = new LabelStyle(game.fonts[9], null);
        labelStyle.background = new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[1], 45,45,45,45));
        titleStyle = new LabelStyle(game.fonts[8], null);
        titleStyle.background = game.skin.getDrawable("title_bg_3");
        exitDialogStyle = new WindowStyle(game.fonts[0], Color.BLACK, null);
        settingsDialogStyle = new WindowStyle(game.fonts[0], Color.BLACK, new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[0], 45,45,45,45)));
    }

    private void makeSettingsDialog() {
        final Table t = settingsDialog.getContentTable();
        final Table t2 = new Table();  // music button and label
        final Table t3 = new Table();  // sound button and label
        final Table t4 = new Table();  // difficulty buttons
        final Table t5 = new Table(); // about button and label
        final Table difficultyTable = new Table();

		final ImageButton musicButton = (ImageButton) Utility.makeButton(null, gameState.musicOn ? (ImageButtonStyle) buttonStyles[7]: (ImageButtonStyle) buttonStyles[8], 150,150);
		final ImageButton soundButton = (ImageButton) Utility.makeButton(null, gameState.soundOn ? (ImageButtonStyle) buttonStyles[9]: (ImageButtonStyle) buttonStyles[10], 150,150);
		final ImageButton aboutButton = (ImageButton) Utility.makeButton(null, (ImageButtonStyle) buttonStyles[14], 150,150);
		final ImageButton closeButton = (ImageButton) Utility.makeButton(null, (ImageButtonStyle) buttonStyles[13], 90,90);

		difficultyButtons[0] = (ImageTextButton) Utility.makeButton("EASY", checkDifficulty(Difficulty.EASY),200,75 );
		difficultyButtons[1] = (ImageTextButton) Utility.makeButton("MEDIUM", checkDifficulty(Difficulty.MEDIUM),200,75);
	    difficultyButtons[2] = (ImageTextButton) Utility.makeButton("HARD", checkDifficulty(Difficulty.HARD),200,75 );

        final Callback musicUp = () -> {
            gameState.musicOn = !gameState.musicOn;

            updateSettingsButtonStyle(musicButton, gameState.musicOn ? (ImageButtonStyle) buttonStyles[7]: (ImageButtonStyle) buttonStyles[8]);
        };

        final Callback soundUp = () -> {
            gameState.soundOn = !gameState.soundOn;
            updateSettingsButtonStyle(soundButton, gameState.soundOn ? (ImageButtonStyle) buttonStyles[9]: (ImageButtonStyle) buttonStyles[10]);
        };

        final Callback closeOnUp = () -> {
            hideSettingsDialog();
        };

        final Callback easyUp = () -> {

            if(gameState.gameDifficulty != Difficulty.EASY) {
                difficulty = Difficulty.EASY;
                showDifficultyDialog();
            }
            
        };

        final Callback mediumUp = () -> {

            if(gameState.gameDifficulty != Difficulty.MEDIUM) {
                difficulty = Difficulty.MEDIUM;
                showDifficultyDialog();
            }
        };

        final Callback hardUp = () -> {
            if(gameState.gameDifficulty != Difficulty.HARD) {
                difficulty = Difficulty.HARD;
                showDifficultyDialog();
            }
        };

        final Callback aboutUp = () -> {
            isSettingsDialogShowing = false;
            game.setScreen(game.screens.get("About Screen"));
        };

        final Label musicLabel = new Label("Music", iconLabelStyle);
        musicLabel.setAlignment(Align.center);
        final Label soundLabel = new Label("Sound", iconLabelStyle);
        soundLabel.setAlignment(Align.center);
        final Label aboutLabel = new Label("About", iconLabelStyle);
        aboutLabel.setAlignment(Align.center);
        final Label difficultyLabel = new Label("Difficulty", iconLabelStyle);
        difficultyLabel.setAlignment(Align.center);
        final Label difficultyInformation = new Label(difficultyInformationText, labelStyle);
        difficultyInformation.setAlignment(Align.center);

	    final Container musicContainer = Utility.makeButtonContainer(game, musicButton, 150, 150, musicUp);
		final Container soundContainer = Utility.makeButtonContainer(game, soundButton, 150, 150, soundUp);
		final Container aboutContainer = Utility.makeButtonContainer(game, aboutButton, 150, 150, aboutUp);
		final Container closeContainer = Utility.makeButtonContainer(game, closeButton, 90, 90, closeOnUp);

		final Container easyContainer = Utility.makeButtonContainer(game, difficultyButtons[0], 200, 75, easyUp);
		final Container mediumContainer = Utility.makeButtonContainer(game, difficultyButtons[1], 200, 75, mediumUp);
		final Container hardContainer = Utility.makeButtonContainer(game, difficultyButtons[2], 200, 75, hardUp);

        t2.add(musicLabel).padRight(50);
        t2.add(musicContainer);

        t3.add(soundLabel).padRight(50);
        t3.add(soundContainer);

        t5.add(aboutLabel).padRight(50);
        t5.add(aboutContainer);

        t4.add(easyContainer).padRight(25);
        t4.add(mediumContainer).padRight(25);
        t4.add(hardContainer);

        difficultyTable.add(difficultyLabel).expand().padBottom(25);
        difficultyTable.row();
        difficultyTable.add(t4);

        t.add(closeContainer).expand().top().right().padBottom(25);
        t.row();
        t.add(t2);
        t.row();
        t.add(t3).padBottom(25);
        t.row();
        t.add(t5).padBottom(25);
        t.row();
        t.add(difficultyTable).padBottom(25);
        t.row();
        t.add(difficultyInformation).width(675);

        initDifficultyButtonsClickables();
    }

		private ImageTextButtonStyle checkDifficulty(Difficulty difficulty) {
			return gameState.gameDifficulty == difficulty ? (ImageTextButtonStyle) buttonStyles[12] : (ImageTextButtonStyle) buttonStyles[11];
		}
	

    private void initDifficultyButtonsClickables() {
        for(int i = 0; i < 3; i++) {
            if(difficultyButtons[i].getStyle() == buttonStyles[12]) {
                difficultyButtons[i].setTouchable(Touchable.disabled);
                return;
            }
        }
    }

    private void changeCheckOfDifficultyButtons() {
        int selectedIndex = gameState.gameDifficulty.ordinal();
         for (int i = 0; i < difficultyButtons.length; i++) {
            if (i == selectedIndex) {
            // Set the selected button style and disable it
            difficultyButtons[i].setStyle((ImageTextButtonStyle) buttonStyles[12]);
            difficultyButtons[i].setTouchable(Touchable.disabled);
            } else {
            // Set the non-selected buttons style and enable them
            difficultyButtons[i].setStyle((ImageTextButtonStyle) buttonStyles[11]);
            difficultyButtons[i].setTouchable(Touchable.enabled);
            }
         }
    }

    private void makeDifficultyDialog() {
        final Callback yesUp = () -> {
            isDifficultyChanged = true;
            gameState.setDifficulty(difficulty);
            changeCheckOfDifficultyButtons();
            hideDifficultyDialog();
        };

        difficultyDialog = new ConfirmationDialog(settingsDialogStyle, game, (ImageTextButtonStyle) buttonStyles[4],"All progress will be lost.\nContinue?", yesUp);
        difficultyDialog.noUp =  this::hideDifficultyDialog;
        difficultyDialog.make();
    }

    private void showSettingsDialog() {
        //settingsContainer.setVisible(false);
        headTable.setVisible(false);
        isSettingsDialogShowing = true;
        settingsDialog.show(stage3);
    }

    private void hideSettingsDialog() {
        if(isDifficultyChanged) {
            isDifficultyChanged = false;
            game.restartFromStage1();
            buttonsTable.clear();
            makeButtons();
            game.saveStageStates();
        }
        game.saveGameState();
        
        if(!difficultyDialog.getIsShowing()) {
            //settingsContainer.setVisible(true);
            headTable.setVisible(true);
        }

        isSettingsDialogShowing = false;
        settingsDialog.hide();
    }
    private void showDifficultyDialog() {
        difficultyDialog.show(stage3);
        hideSettingsDialog();
    }

    private void hideDifficultyDialog() {
        showSettingsDialog();
        difficultyDialog.hide();
    }


    public final class ExitInput extends InputAdapter {
	    @Override
      public boolean keyDown(int keycode) {

        if(keycode == Keys.BACK) {

            if(difficultyDialog.getIsShowing()) {
                hideDifficultyDialog();
                return true;
            }

            if(isSettingsDialogShowing) {
                hideSettingsDialog();
                return true;
            }

            if(exitDialog.getIsShowing()) {
                headTable.setVisible(true);
                exitDialog.hide();
            } else {
                headTable.setVisible(false);
                exitDialog.show(stage3);
            }
            
          
          return true;
        }
        return false;
      }
	}
}