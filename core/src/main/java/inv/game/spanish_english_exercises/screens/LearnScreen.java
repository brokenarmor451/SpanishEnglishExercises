package inv.game.spanish_english_exercises.screens;

import inv.game.spanish_english_exercises.SpEnExercises;
import inv.game.spanish_english_exercises.dialogs.ConfirmationDialog;
import inv.game.spanish_english_exercises.objects.ButtonRun;
import inv.game.spanish_english_exercises.objects.LearningStages;
import inv.game.spanish_english_exercises.objects.Utility;
import inv.game.spanish_english_exercises.objects.ButtonRun.Callback;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;

public class LearnScreen extends ScreenAdapter {
    private final TextureRegionDrawable[] backgrounds = new TextureRegionDrawable[2];

    public String[] englishTexts;
    public String[] spanishTexts;
    //public Label abbreviations;
    public Label[] titles;
    public Label[] englishLabels;
    public Label[] spanishLabels;
    private int selectedStage;

    private FitViewport viewport;
    private ScreenViewport viewport2;
    private FillViewport viewport3;
    private Stage stage;
    private Stage stage2;
    private Stage stage3;
    public Table translationsTable;
    public Table titlesTable;
    public Table table;
    private Table hudRoot;
    //public Table abbreviationsTable;
    public ScrollPane sc;
    private final SpEnExercises game;

    public ButtonStyle[] buttonStyles;

    private ConfirmationDialog menuDialog;

    private Container menuContainer;
    private Container exercisesContainer;

    public LearnScreen(SpEnExercises game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new FitViewport(800,1600);
        viewport2 = new ScreenViewport();
        viewport3 = new FillViewport(768, 1216);
        stage = new Stage(viewport, game.batch);
        stage2 = new Stage(viewport2, game.batch);
        stage3 = new Stage(viewport, game.batch);
    
        final String[][] texts = new LearningStages().get(selectedStage);
        spanishTexts = texts[0];
        englishTexts = texts[1];
        initTables();
        initLabels();
        initScrollPane();
        makeTableBackgrounds();
        makeTable();
        makeButtonStyles();
        makeHud();
        stage.addActor(table);
        stage2.addActor(hudRoot);

        final InputMultiplexer multiplexer = new InputMultiplexer(new ExitInput(),stage3, stage, stage2);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        game.batch.setProjectionMatrix(viewport3.getCamera().combined);
        viewport3.apply();
        drawBackground();

        viewport.apply();
        stage.act(delta);
        stage.draw();

        viewport2.apply();
        stage2.act(delta);
        stage2.draw();

        if(menuDialog.getIsShowing()) {
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

    private void makeTableBackgrounds() {
        final Pixmap bluebg = new Pixmap(1,1,Pixmap.Format.RGB565);
        bluebg.setColor(new Color(0xAEC9E5FF));
        bluebg.fill();

        final Pixmap yellowbg = new Pixmap(1,1,Pixmap.Format.RGB565);
        yellowbg.setColor(0xE5F883FF);
        yellowbg.fill();

        backgrounds[0] = new TextureRegionDrawable(new Texture(bluebg));
        backgrounds[1] = new TextureRegionDrawable(new Texture(yellowbg));
    }

    private void drawBackground() {
        game.batch.begin();
        game.backgrounds[3].draw(game.batch);
        game.batch.end();
    }

    public void initTables() {
        table = new Table();
        table.setSize(700, 1200);
        table.setX(50);
        table.setY(300);

        translationsTable = new Table();
        translationsTable.setWidth(700);

        titlesTable = new Table();
        //abbreviationsTable = new Table();

        hudRoot = new Table();
        hudRoot.setFillParent(true);
    }

    public void initLabels() {
        final LabelStyle[] labelStyles = new LabelStyle[]{
            new LabelStyle(game.fonts[0], null),
            new LabelStyle(game.fonts[1], null),
            new LabelStyle(game.fonts[2], null),
        };

        labelStyles[1].background = new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[1],45,45,45,45));
        labelStyles[2].background = new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[2],45,45,45,45));

        titles = new Label[2];
        titles[0] = new Label("Spanish", labelStyles[1]);
        titles[1] = new Label("English", labelStyles[2]);
        englishLabels = new Label[englishTexts.length];
        spanishLabels = new Label[spanishTexts.length];
        for(int i = 0; i < englishTexts.length; i++) {
            englishLabels[i] = new Label(englishTexts[i],labelStyles[0]);
            spanishLabels[i] = new Label(spanishTexts[i],labelStyles[0]);
        }
        //abbreviations = new Label("(p.) = plural (i.) = informal (f.) = formal (s.) = singular", lS4);
    }

    public void initScrollPane() {
        sc = new ScrollPane(translationsTable);
    }

    public void setSelectedStage(int index) {
        selectedStage = index;
    }

    private void makeButtonStyles() {
      final NinePatchDrawable np = new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[3],45,45,45,45));

        buttonStyles = new ButtonStyle[] {
            new ImageTextButtonStyle(np,np, null, game.fonts[3]),
            new ImageButtonStyle(game.skin.get("menu", ImageButtonStyle.class)),
            new ImageButtonStyle(game.skin.get("exercises",ImageButtonStyle.class))
        };
    }

    private void updateSizes(int height) {
        menuContainer.getActor().setSize(height / 8, height / 8);
        menuContainer.getActor().setOrigin(Align.center);
        menuContainer.size(height / 8, height /8);
        hudRoot.getCell(menuContainer).size(height / 8, height / 8);

        if(hudRoot.getCell(exercisesContainer) != null) {
            exercisesContainer.getActor().setSize(height / 10 * 600/225, height / 10);
            exercisesContainer.size(height / 10 * 600/225, height / 10);
            hudRoot.getCell(exercisesContainer).size(height / 10 * 600/225, height / 10);
        }
    }

    public void makeHud() {
        final Callback yesUp = () -> {
          //game.saveStageStates();
          game.setScreen(game.screens.get("Main Menu"));
        };

        menuDialog = new ConfirmationDialog(new WindowStyle(game.fonts[7], Color.BLACK, null), game, (ImageTextButtonStyle) buttonStyles[0], "Return to the main menu ?", yesUp);
        menuDialog.make();
        menuDialog.setActor(hudRoot);

        final Callback menuUp = () -> {
           
           hudRoot.setVisible(false);
           
           menuDialog.show(stage3);
        };

        final ImageButton menuButton = (ImageButton) Utility.makeButton(null, (ImageButtonStyle) buttonStyles[1], game.height / 8, game.height / 8);
        menuContainer = (Container<ImageButton>) Utility.makeButtonContainer(game, menuButton, game.height / 8, game.height / 8 , menuUp);
  
        final ImageButton exercisesButton = new ImageButton((ImageButtonStyle) buttonStyles[2]);
        exercisesButton.setSize((game.height / 10) * (600/225), game.height/10);
        Utility.enableScale(exercisesButton);

        final Callback exercisesUp = () -> {
            game.getGameState().enterToLearning = false;
            final ExercisesScreen es = (ExercisesScreen) game.screens.get("Exercises Screen");
            es.setSelectedStage(selectedStage);
            game.setScreen(es);
        };

        exercisesContainer = new Container<>(exercisesButton).size(game.height / 10 * 600/225, game.height/10);
        exercisesContainer.addListener(new ButtonRun(game,exercisesContainer, null, exercisesUp));

        hudRoot.add(menuContainer).expand().bottom().left().pad(15);

        if(game.getGameState().unlockedStages[selectedStage] == 'O') { // If corresponding exercises completed dont add this exercises button
            hudRoot.add(exercisesContainer).expand().bottom().right().pad(15);
        }
        
    }

    public void makeTable() {
        //abbreviationsTable.add(abbreviations);
        //table.add(abbreviationsTable).width(800);
        //table.row();
        titlesTable.add(titles[0]).expandX().left().height(100);
        titlesTable.add(titles[1]).expandX().right().height(100);
        table.add(titlesTable).width(700);
        table.row();
        table.add(sc).size(700,1000).expandX();

        int backgroundSwitch = 0;
        for(int i = 0; i < englishTexts.length; i++) {
            final Table t = new Table();
            t.add(spanishLabels[i]).expandX().left();
            t.add(englishLabels[i]).expandX().right();
            t.setBackground(backgrounds[backgroundSwitch]);
            //t.setColor(0, 0, 0, 1);
            //t.setBackground();
            //t.addActor(backgrounds[backgroundSwitch]);
            if(backgroundSwitch == 0) {
                backgroundSwitch = 1;
            } else {
                backgroundSwitch = 0;
            }
            translationsTable.add(t).width(700);
            translationsTable.row();
        }
    }

    private void showExitDialog() {
      hudRoot.setVisible(false);
      menuDialog.show(stage3);
     }

     private void hideExitDialog() {
      hudRoot.setVisible(true);
      menuDialog.hide();
     }

    public final class ExitInput extends InputAdapter {
	    @Override
      public boolean keyDown(int keycode) {

        if(keycode == Keys.BACK) {
            if(menuDialog.getIsShowing()) {
               hideExitDialog();
            } else {
               showExitDialog();
            }
          return true;
        }
        return false;
      }
    }
}