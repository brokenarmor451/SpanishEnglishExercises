package inv.game.spanish_english_exercises.objects;

import java.nio.channels.WritePendingException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Filter;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;

import inv.game.spanish_english_exercises.SpEnExercises;
import inv.game.spanish_english_exercises.objects.ButtonRun.Callback;

public final class Utility {

    public static void enableScale(Button button) {
      button.setOrigin(Align.center);
      button.setTransform(true);
    }

    public static void dimBackground(SpEnExercises game, Viewport viewport) {
        game.shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
        game.shapeRenderer.begin(ShapeType.Filled);
        game.shapeRenderer.setColor(0,0,0,0.75f);
        game.shapeRenderer.rect(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.shapeRenderer.end();
        
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public static LabelStyle makeLabelStyle(Color labelBg, BitmapFont font) {
      Pixmap pixmap = new Pixmap(100,100, Pixmap.Format.RGBA8888);
      pixmap.setColor(labelBg);
      pixmap.setFilter(Filter.NearestNeighbour);
      pixmap.fillCircle(50, 50, 50);

      final Texture t = new Texture(pixmap);
      t.setFilter(TextureFilter.Linear, TextureFilter.Linear);

      final NinePatchDrawable npd = new NinePatchDrawable(new NinePatch(t,45,45,45,45));

      pixmap.dispose();
  
      final LabelStyle lb = new LabelStyle(font, Color.BLACK);
      lb.background = npd;
  
      
      return lb;
    }

    public static Button makeButton(String text, ButtonStyle style, float width, float height) {
    if(text != null) {
      final ImageTextButton btn = new ImageTextButton(text, (ImageTextButtonStyle) style);
      btn.setSize(width, height);
      enableScale(btn);
      return btn;
    } else {
      final ImageButton btn = new ImageButton((ImageButtonStyle) style);
      btn.setSize(width, height);
      enableScale(btn);
      return btn;
    }
  }

  public static Container<? extends Button> makeButtonContainer(SpEnExercises game, Button button, float width, float height, Callback callback) {
    final Container<? extends Button> c = new Container<>(button).size(width, height);
    c.addListener(new ButtonRun(game, c, null, callback));
    return c;
  }
}