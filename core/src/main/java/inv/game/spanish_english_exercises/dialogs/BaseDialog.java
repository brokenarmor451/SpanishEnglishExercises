package inv.game.spanish_english_exercises.dialogs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

import inv.game.spanish_english_exercises.SpEnExercises;
import inv.game.spanish_english_exercises.objects.ButtonRun.Callback;


public abstract class BaseDialog extends Dialog {
  public final SpEnExercises game;

  private boolean isShowing;

  public Label info;

  public Container[] buttons;

  public Callback[] callbacks;

  public BaseDialog(WindowStyle style, SpEnExercises game) {
    super("", style);
    this.game = game;
  }


  public void setIsShowing(boolean isShowing) {
    this.isShowing = isShowing;
  }

  public boolean getIsShowing() {
    return this.isShowing;
  }

  @Override
  public Dialog show(Stage stage) {
    setIsShowing(true);

    return super.show(stage);

  }

  @Override
  public void hide() {
    setIsShowing(false);

    super.hide();
  }

  public void make() {
    makeButtons();
    makeInfo();

    info.setAlignment(Align.center);


    final Table t = getContentTable();
    final Table t2 = new Table();
    final Table t3 = new Table();
    t3.add(info);


    t.add(t3);
    t.row();


    t2.add(buttons[0]).padRight(25);
    t2.add(buttons[1]);

    t.add(t2);
  }

  public void setInfo(Label info) {
    this.info = info;
  }

  public abstract void makeButtons();

  public abstract void makeInfo();


}