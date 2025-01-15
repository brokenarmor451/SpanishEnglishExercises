package inv.game.spanish_english_exercises.screens;

import inv.game.spanish_english_exercises.SpEnExercises;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.Color;

public class SplashScreen extends ScreenAdapter{
    private final SpEnExercises game;

    public SplashScreen(SpEnExercises game) {
        this.game = game;
    }

  private Animation<TextureRegion> splashAnimation;
  
  private float stateTime = 0f;
  
  private FitViewport gameViewport;

  private TextureRegion currentFrame;
  
  @Override
  public void show() {
    gameViewport = new FitViewport(800,1600);
    gameViewport.getCamera().position.set(400,800,0);
    
    splashAnimation = new Animation<TextureRegion>(0.05f, game.splashAtlas.findRegions("splash"), PlayMode.LOOP);
  }
  
  @Override
  public void render(float delta) {
    ScreenUtils.clear(new Color(0xEEEEEEFF));
    
    gameViewport.apply();
    game.batch.setProjectionMatrix(gameViewport.getCamera().combined);
    
    stateTime += delta;
    currentFrame = splashAnimation.getKeyFrame(stateTime, true);
    
    game.batch.begin();
    game.batch.draw(
      currentFrame, 
      800 / 2 - currentFrame.getRegionWidth() / 2, 
      1600 / 2 - currentFrame.getRegionHeight() / 2
    );
    game.batch.end();
    
     if(game.assets.manager.update(17)) {
      game.assignFonts();
      game.assignSounds();
      game.skin = game.assets.manager.get("ui/uiSkin.json", Skin.class);
      game.atlas = game.assets.manager.get("gameTextures/gameTextures.atlas", TextureAtlas.class);
      game.atlas2 = game.assets.manager.get("backgrounds/backgrounds.atlas", TextureAtlas.class);
      game.makeTextures();
      game.setScreen(game.screens.get("Main Menu"));
    }

  }
  
  @Override
	public void resize(int width, int height) {
	  gameViewport.update(width,height, true);
	}

}