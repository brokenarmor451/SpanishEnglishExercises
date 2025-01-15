package inv.game.spanish_english_exercises.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import games.rednblack.miniaudio.MASound;
import games.rednblack.miniaudio.MiniAudio;
import games.rednblack.miniaudio.loader.MASoundLoader;
import inv.game.spanish_english_exercises.SpEnExercises;

import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Color;

public class GameAssets {
  final MiniAudio miniAudio;

  public final AssetManager manager = new AssetManager();
  
  public final String font = "fonts/Galindo-Regular.ttf";
  public final String font2 = "fonts/roboto_mono_regular.ttf";
  public final String font3 = "fonts/passion_one_regular.ttf";
  public final String skin = "ui/uiSkin.json";
  public final String gameTexturesAtlas = "gameTextures/gameTextures.atlas";
  public final String backgroundsAtlas = "backgrounds/backgrounds.atlas";
  public final String splashAtlas = "splash/splash.atlas";

  public GameAssets(MiniAudio miniAudio) {
    this.miniAudio = miniAudio;
  }

  public void loadImages() {
    //manager.load(images, TextureAtlas.class);
  }
  
  public void loadSkin() {
    SkinParameter params = new SkinParameter("ui/uiSkin.atlas");
    manager.load(skin, Skin.class, params);
  }

  public void loadAtlas() {
    manager.load(gameTexturesAtlas, TextureAtlas.class);
    manager.load(backgroundsAtlas, TextureAtlas.class);
  }
  
  public void loadSplash() {
    manager.load(splashAtlas, TextureAtlas.class);
  }
  
  public void loadSounds() {
    manager.setLoader(MASound.class, new MASoundLoader(miniAudio, manager.getFileHandleResolver()));
    manager.load("sounds/ui_click.wav", MASound.class);
    manager.load("sounds/choice_button_click.wav", MASound.class);
    manager.load("sounds/stage_completed.wav", MASound.class);
    manager.load("sounds/playful.mp3", MASound.class);
  }
  
  public void loadFonts() {
    FileHandleResolver resolver = new InternalFileHandleResolver();
    manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
    manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
    
    FreeTypeFontLoaderParameter p1 = new FreeTypeFontLoaderParameter();
    p1.fontFileName = font;
    p1.fontParameters.size = 34;
    p1.fontParameters.color = new Color(0x000000FF);
    p1.fontParameters.minFilter = TextureFilter.Linear;
    p1.fontParameters.magFilter = TextureFilter.Linear;
    manager.load("galindoBlack.ttf", BitmapFont.class, p1);

    FreeTypeFontLoaderParameter p2 = new FreeTypeFontLoaderParameter();
    p2.fontFileName = font;
    p2.fontParameters.size = 48;
    p2.fontParameters.color = new Color(0xB8001FFF);
    p2.fontParameters.minFilter = TextureFilter.Linear;
    p2.fontParameters.magFilter = TextureFilter.Linear;
    manager.load("galindoRedHead.ttf", BitmapFont.class, p2);

    FreeTypeFontLoaderParameter p3 = new FreeTypeFontLoaderParameter();
    p3.fontFileName = font;
    p3.fontParameters.size = 48;
    p3.fontParameters.color = new Color(0x384B70FF);
    p3.fontParameters.minFilter = TextureFilter.Linear;
    p3.fontParameters.magFilter = TextureFilter.Linear;
    manager.load("galindoBlueHead.ttf", BitmapFont.class, p3);

    FreeTypeFontLoaderParameter p4 = new FreeTypeFontLoaderParameter();
    p4.fontFileName = font;
    p4.fontParameters.size = 26;
    p4.fontParameters.color = new Color(0x000000FF);
    p4.fontParameters.minFilter = TextureFilter.Linear;
    p4.fontParameters.magFilter = TextureFilter.Linear;
    manager.load("galindoBlackSmall.ttf", BitmapFont.class, p4);

    FreeTypeFontLoaderParameter p5 = new FreeTypeFontLoaderParameter();
    p5.fontFileName = font;
    p5.fontParameters.size = 28;
    p5.fontParameters.color = new Color(0x000000FF);
    p5.fontParameters.minFilter = TextureFilter.Linear;
    p5.fontParameters.magFilter = TextureFilter.Linear;
    manager.load("galindoBlackBordered.ttf", BitmapFont.class, p5);

    FreeTypeFontLoaderParameter p6 = new FreeTypeFontLoaderParameter();
    p6.fontFileName = font;
    p6.fontParameters.size = 34;
    p6.fontParameters.color = new Color(0x112a46ff);
    p6.fontParameters.minFilter = TextureFilter.Linear;
    p6.fontParameters.magFilter = TextureFilter.Linear;
    manager.load("galindoDarkBlueHead.ttf", BitmapFont.class, p6);

    FreeTypeFontLoaderParameter p7 = new FreeTypeFontLoaderParameter();
    p7.fontFileName = font;
    p7.fontParameters.size = 60;
    p7.fontParameters.color = new Color(0x112A46ff);
    p7.fontParameters.minFilter = TextureFilter.Linear;
    p7.fontParameters.magFilter = TextureFilter.Linear;
    p7.fontParameters.borderColor = Color.BLACK;
    p7.fontParameters.borderWidth = 3;
    manager.load("galindoDarkBlueBordered.ttf", BitmapFont.class, p7);

    FreeTypeFontLoaderParameter p8 = new FreeTypeFontLoaderParameter();
    p8.fontFileName = font;
    p8.fontParameters.size = 55;
    p8.fontParameters.color = new Color(0x000000ff);
    p8.fontParameters.minFilter = TextureFilter.Linear;
    p8.fontParameters.magFilter = TextureFilter.Linear;
    p8.fontParameters.borderColor = Color.BLACK;
    p8.fontParameters.borderWidth = 3;
    manager.load("galindoBlackH1.ttf", BitmapFont.class, p8);
    

    FreeTypeFontLoaderParameter p9 = new FreeTypeFontLoaderParameter();
    p9.fontFileName = font2;
    p9.fontParameters.size = 34;
    p9.fontParameters.color = new Color(0x000000ff);
    p9.fontParameters.minFilter = TextureFilter.Linear;
    p9.fontParameters.magFilter = TextureFilter.Linear;
   // p9.fontParameters.borderColor = Color.BLACK;
    //p9.fontParameters.borderWidth = 3;
    manager.load("robotoMonoBlack1.ttf", BitmapFont.class, p9);

    FreeTypeFontLoaderParameter p10 = new FreeTypeFontLoaderParameter();
    p10.fontFileName = font2;
    p10.fontParameters.size = 23;
    p10.fontParameters.color = new Color(0x000000ff);
    p10.fontParameters.minFilter = TextureFilter.Linear;
    p10.fontParameters.magFilter = TextureFilter.Linear;

    manager.load("robotoMonoBlack2.ttf", BitmapFont.class, p10);

    FreeTypeFontLoaderParameter p11 = new FreeTypeFontLoaderParameter();
    p11.fontFileName = font3;
    p11.fontParameters.size = 35;
    p11.fontParameters.color = new Color(0x000000ff);
    p11.fontParameters.minFilter = TextureFilter.Linear;
    p11.fontParameters.magFilter = TextureFilter.Linear;

    manager.load("passionOneBlack1.ttf", BitmapFont.class, p11);
  }
  
}