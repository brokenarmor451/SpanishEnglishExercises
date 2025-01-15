package inv.game.spanish_english_exercises.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import inv.game.spanish_english_exercises.SpEnExercises;
import inv.game.spanish_english_exercises.objects.ButtonRun;
import inv.game.spanish_english_exercises.objects.Utility;
import inv.game.spanish_english_exercises.objects.ButtonRun.Callback;

public class AboutScreen extends ScreenAdapter{
    private final SpEnExercises game;

    private boolean isLicenseTextShowing;

    private Table root;
    private Table head;
    private Table buttonsTable;
    private Table textShowing;

    private Container backContainer;
    private Container[] licenseContainers;

    private String[] names;
    private String[] texts;

    private ButtonStyle[] buttonStyles;
    private LabelStyle[] labelStyles;

    private Label licensesLabel;
    private Label actualLicense;

    private ScrollPane buttonList;
    private ScrollPane scrollText;

    private Stage stage;
    private Stage stage2;

    private FitViewport viewport;
    private ScreenViewport viewport2;
    private FillViewport viewport3;

    private HashMap<String, String> licenseMap;

    public AboutScreen(SpEnExercises game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new FitViewport(800, 1600);
        viewport2 = new ScreenViewport();
        viewport3 = new FillViewport(768, 1216);
        viewport3.getCamera().position.set(384,608,0);

        stage = new Stage(viewport,game.batch);
        stage2 = new Stage(viewport2, game.batch);


        setNames();
        setTexts();
        makeStyles();
        makeHead();
        makeRoot();
        makeTextShowing();
        makeButtonList();

        final InputMultiplexer multiplexer = new InputMultiplexer(new ExitInput(),stage2, stage);
        
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        game.batch.setProjectionMatrix(viewport3.getCamera().combined);
        viewport3.apply();
        drawBackground();

        viewport2.apply();
        stage2.act(delta);
        stage2.draw();

        viewport.apply();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport2.update(width, height);
        viewport3.update(width, height, true);

        updateSizes(height);
    }

    private void drawBackground() {
        game.batch.begin();
        game.backgrounds[0].draw(game.batch);
        game.batch.end();
    }

    private void makeRoot() {
        root = new Table();
        root.setSize(800, 1200);
        root.setY(200);
        buttonsTable = new Table();
        buttonsTable.setSize(800, 1000);
        buttonList = new ScrollPane(buttonsTable);

        licensesLabel = new Label("LICENSES", labelStyles[0]);
        
        root.add(licensesLabel).padBottom(50);
        root.row();
        root.add(buttonList).size(800,1000);
        stage.addActor(root);
    
    }

    private void makeHead() {
        final ImageButton backButton = new ImageButton((ImageButtonStyle) buttonStyles[2]);
        backButton.setSize(game.height / 15, game.height / 15);
        Utility.enableScale(backButton);

        backContainer = new Container<>(backButton).size(game.height / 15, game.height / 15);

        final Callback backUp = () -> {
            game.setScreen(game.screens.get("Main Menu"));
        };

        backContainer.addListener(new ButtonRun(game,backContainer, null, backUp));

        head = new Table();
        head.setFillParent(true);
        head.add(backContainer).expand().top().left().pad(15);
        stage2.addActor(head);
    }

    private void updateSizes(int height) {
        backContainer.size(height / 15, height / 15);
        backContainer.getActor().setSize(height / 15, height / 15);

        backContainer.getActor().setOrigin(Align.center);

        head.getCells().get(0).top().left();
    }

    private void makeTextShowing() {
        final Table t = new Table();
        t.setBackground(new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[2], 45,45,45, 45)));

        final ImageButton closeButton = new ImageButton((ImageButtonStyle) buttonStyles[1]);
        Utility.enableScale(closeButton);

        final Container closeContainer = new Container<>(closeButton).size(90,90);

        final Callback closeOnUp = () -> {
            hideText();
        };

        closeContainer.addListener(new ButtonRun(game,closeContainer, null, closeOnUp));


        actualLicense = new Label("", labelStyles[1]);
        actualLicense.setWrap(true);

        scrollText = new ScrollPane(actualLicense);

        t.add(scrollText).size(650,1000);

        textShowing = new Table();
        textShowing.setSize(650, 1090);
        textShowing.setX(75);
        textShowing.add(closeContainer).expand().right().padBottom(15);
        textShowing.row();
        textShowing.add(t).size(650, 1000);
    }

    private void makeButtonList() {
        final ImageTextButton[] licenseButtons = new ImageTextButton[names.length];
        licenseContainers = new Container[names.length];

        for(int i = 0; i < names.length; i ++) {
            final int i2 = i;

            licenseButtons[i] = new ImageTextButton(names[i], (ImageTextButtonStyle) buttonStyles[0]);
            Utility.enableScale(licenseButtons[i]);
            licenseContainers[i] = new Container<>(licenseButtons[i]).size(650,100);

            final Callback licenseUp = () -> {
                showText(texts[i2]);
            };

            licenseContainers[i].addListener(new ButtonRun(game,licenseContainers[i], null, licenseUp));

            buttonsTable.add(licenseContainers[i]).padBottom(25);
            buttonsTable.row();
        }
    }

    private void makeStyles() {
        buttonStyles = new ButtonStyle[]{
            new ImageTextButtonStyle(game.skin.getDrawable("license_button_bg"), game.skin.getDrawable("license_button_bg"), null, game.fonts[8]),
            game.skin.get("close", ImageButtonStyle.class),
            game.skin.get("back", ImageButtonStyle.class)
        };

        labelStyles = new LabelStyle[] {
            new LabelStyle(game.fonts[8], null),
            new LabelStyle(game.fonts[8], null)
        };

        labelStyles[0].background = new NinePatchDrawable(new NinePatch(game.ninePatchBackgrounds[1], 45,45,45,45));
    }

    private void showText(String text) {
        isLicenseTextShowing = true;
        root.setVisible(false);
        head.setVisible(false);
        actualLicense.setText(text);
        textShowing.setY(2600);
        stage.addActor(textShowing);
        textShowing.addAction(Actions.moveTo(75, 255, 0.25f));
    }

    private void hideText() {
        isLicenseTextShowing = false;
        
        textShowing.addAction(Actions.sequence(Actions.moveTo(75,-1090, 0.25f), Actions.run(() -> {
            scrollText.cancel();
            scrollText.setScrollY(0);
            textShowing.remove();
            root.setVisible(true);
            head.setVisible(true);
          })));
    }

    private void setNames() {
        names = new String[] {
            "libGDX",
            "libGDX Android Backend",
            "libGDX FreeType",
            "libGDX FreeType Native Libraries",
            "libGDX jniGen library",
            "libGDX Native Libraries",
            "error-prone annotations",
            "JsInterop Annotations",
            "gson",
            "Sofadi One Font",
            "Roboto Mono Font",
            "Passion One Font",
            "This Game"
          };
    }

    private void setTexts() {
        final String[] textsToCopy = Gdx.files.internal("texts/LICENSES.txt").readString().split("✓");

        texts = new String[names.length];
        texts[0] = textsToCopy[0];  // textsToCopy[0] = APACHE LICENSE
        texts[1] = textsToCopy[0];
        texts[2] = textsToCopy[0];
        texts[3] = textsToCopy[0];
        texts[4] = textsToCopy[0];
        texts[5] = textsToCopy[0];
        texts[6] = textsToCopy[0];
        texts[10] = textsToCopy[0];
        texts[7] = textsToCopy[1] + "\n" + textsToCopy[0]; // Copyright notice + APACHE LICENSE
        texts[8] = textsToCopy[2] + "\n" + textsToCopy[0];
        texts[9] = textsToCopy[3];
        texts[11] = textsToCopy[4];
        texts[12] = textsToCopy[5] + "\n" + textsToCopy[0];
    }

    public final class ExitInput extends InputAdapter {
	    @Override
      public boolean keyDown(int keycode) {

        if(keycode == Keys.BACK) {

            if(isLicenseTextShowing) {
                hideText();
                return true;
            }

            game.setScreen(game.screens.get("Main Menu"));
            
          return true;
        }
        return false;
      }
	}
}
