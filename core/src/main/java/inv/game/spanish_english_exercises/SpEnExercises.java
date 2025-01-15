package inv.game.spanish_english_exercises;

import inv.game.spanish_english_exercises.objects.GameAssets;
import inv.game.spanish_english_exercises.save.GameState;
import inv.game.spanish_english_exercises.save.StageState;
import inv.game.spanish_english_exercises.screens.AboutScreen;
import inv.game.spanish_english_exercises.screens.ExercisesScreen;
import inv.game.spanish_english_exercises.screens.LearnScreen;
import inv.game.spanish_english_exercises.screens.MainMenu;
import inv.game.spanish_english_exercises.screens.SplashScreen;

import java.util.HashMap;

import org.w3c.dom.Text;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.google.gson.Gson;

import games.rednblack.miniaudio.MASound;
import games.rednblack.miniaudio.MiniAudio;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SpEnExercises extends Game {
    final MiniAudio miniAudio;

    public BitmapFont[] fonts = new BitmapFont[11];

    public GameAssets assets;
    public SpriteBatch batch;
    public Skin skin;
    public HashMap<String, ScreenAdapter> screens = new HashMap<String, ScreenAdapter>(4);
    public int width;
    public int height;

    private GameState gameState;
    private FileHandle saveFile;
    private FileHandle saveFile2;
    private StageState[] stageStates;
    private Gson gson;

    public ShapeRenderer shapeRenderer;

    public TextureAtlas atlas;
    public TextureAtlas atlas2; // for backgounds
    public TextureAtlas splashAtlas;

    public final TextureRegion[] ninePatchBackgrounds = new TextureRegion[4];

    public Sprite[] backgrounds;

    public MASound[] sounds = new MASound[4];

    public SpEnExercises(MiniAudio miniAudio) {
      this.miniAudio = miniAudio;
    }

    @Override
    public void create() {
        generateSave();
        loadGameState();
        loadStageStates();
        batch = new SpriteBatch();
        assets = new GameAssets(miniAudio);

        assets.loadSplash();
        assets.manager.finishLoading();
        assignSplashAtlas();

        putScreens();

        setScreen(new SplashScreen(this));
        assets.loadFonts();
        assets.loadSkin();
        assets.loadAtlas();
        assets.loadSounds();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setCatchKey(Keys.BACK, true);
    }

    @Override
    public void dispose() {
      assets.manager.dispose();
    }

    private void putScreens() {
        screens.put("Learn Screen", new LearnScreen(this));
        screens.put("Exercises Screen", new ExercisesScreen(this));
        screens.put("Main Menu", new MainMenu(this));
        screens.put("About Screen", new AboutScreen(this));
    }

    public void makeTextures() {
      ninePatchBackgrounds[0] = atlas.findRegion("bg_rounded");
      ninePatchBackgrounds[1] = atlas.findRegion("bg_rounded_two");
      ninePatchBackgrounds[2] = atlas.findRegion("bg_rounded_three");
      ninePatchBackgrounds[3] = atlas.findRegion("button_light_green");

      backgrounds = new Sprite[]{
        atlas2.createSprite("tiles"),
        atlas2.createSprite("road"),
        atlas2.createSprite("forest"),
        atlas2.createSprite("lake")
      };

      for(int i = 0; i < backgrounds.length; i++) {
        backgrounds[i].setPosition(0, 0);
      }
    }

    public void assignFonts() {
      fonts[0] = assets.manager.get("galindoBlack.ttf", BitmapFont.class);
      fonts[1] = assets.manager.get("galindoRedHead.ttf", BitmapFont.class);
      fonts[2] = assets.manager.get("galindoBlueHead.ttf", BitmapFont.class);
      fonts[3] = assets.manager.get("galindoBlackSmall.ttf",BitmapFont.class);
      fonts[4] = assets.manager.get("galindoBlackBordered.ttf", BitmapFont.class);
      fonts[5] = assets.manager.get("galindoDarkBlueHead.ttf", BitmapFont.class);
      fonts[6] = assets.manager.get("galindoDarkBlueBordered.ttf", BitmapFont.class);
      fonts[7] = assets.manager.get("galindoBlackH1.ttf", BitmapFont.class);
      fonts[8] = assets.manager.get("robotoMonoBlack1.ttf", BitmapFont.class);
      fonts[9] = assets.manager.get("robotoMonoBlack2.ttf", BitmapFont.class);
      fonts[10] = assets.manager.get("passionOneBlack1.ttf", BitmapFont.class);
      
    }

    public void assignSounds() {
      sounds[0] = assets.manager.get("sounds/ui_click.wav", MASound.class);
      sounds[1] = assets.manager.get("sounds/choice_button_click.wav", MASound.class);
      sounds[2] = assets.manager.get("sounds/stage_completed.wav", MASound.class);
      sounds[3] = assets.manager.get("sounds/playful.mp3", MASound.class);
      sounds[3].setLooping(true);
    }

    public void assignSplashAtlas() {
      splashAtlas = assets.manager.get("splash/splash.atlas", TextureAtlas.class);
    }

    public void playChoiceClickSound() {
      if(gameState.soundOn) {
        sounds[1].play();
      }
    }

    public void playUiClickSound() {
      if(gameState.soundOn) {
        sounds[0].play();
      }
    }

    public void playStageCompletedSound() {
      if(gameState.soundOn) {
        sounds[2].play();
      }
    }

    public void playBackgroundMusic() {
      if(gameState.musicOn) {
        sounds[3].play();
      }
    }

    public void stopBackgroundMusic() {
      if(sounds[3].isPlaying()) {
        sounds[3].stop();
      }
    }

    public StageState getStageState(int index) {
      return stageStates[index];
    }

    private void generateSave() {
    saveFile = Gdx.files.local("stageStates.json");
    gson = new Gson();
	  if(!saveFile.exists()) {
	    stageStates = new StageState[2000];
	    for(int i = 0; i < 2000; i++) {
	      stageStates[i] = new StageState(
	        0,
            0
	       );
	     }
	     
	     String saveString = gson.toJson(stageStates);
	     saveFile.writeString(saveString, false);
	  }
	  
	  saveFile2 = Gdx.files.local("gameState.json");
	  
	  if(!saveFile2.exists()) {
	    gameState = new GameState();
	 
	   String saveString = gson.toJson(gameState);
	   saveFile2.writeString(saveString, false);
	  }
  }

  private void resetStageStates() {
    for(int i = 0; i < 2000; i++) {
      if(stageStates[i].buttonContainerIndices != null) {
        stageStates[i] = new StageState(
        0,
          0
       );
      }
    }
  }

  public void restartFromStage1() {
    resetStageStates();
    gameState.restartFromStage1();
  }

  private void loadStageStates() {
    String saveString = saveFile.readString();
    stageStates = gson.fromJson(saveString, StageState[].class);
  }

  private void loadGameState() {
    String saveString = saveFile2.readString();
    gameState = gson.fromJson(saveString, GameState.class);
  }

  public void saveGameState() {
    String saveString = gson.toJson(gameState);
    saveFile2.writeString(saveString, false);
  }

  public void saveStageStates() {
    String saveString = gson.toJson(stageStates);
    saveFile.writeString(saveString, false);
  }

  public void resetGameState() {
  }

  public GameState getGameState() {
    return gameState;
  }
}