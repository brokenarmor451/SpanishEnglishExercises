package inv.game.spanish_english_exercises.dialogs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;

import inv.game.spanish_english_exercises.SpEnExercises;
import inv.game.spanish_english_exercises.objects.ButtonRun.Callback;
import inv.game.spanish_english_exercises.objects.Utility;

public final class ConfirmationDialog extends BaseDialog {
  final ImageTextButtonStyle buttonStyle;
  final String text;
  final Callback callback;
  public Callback noUp;
  public Actor actorToShow;

  public ConfirmationDialog(WindowStyle style, SpEnExercises game, ImageTextButtonStyle buttonStyle, String text, Callback callback) {
    super(style, game);
    this.buttonStyle = buttonStyle;
    this.text = text;
    this.callback = callback;

  }

  @Override
  public void makeInfo() {
    info = new Label(text, Utility.makeLabelStyle(new Color(0xbae6acff), game.fonts[10]));
  }

  @Override
  public void makeButtons() {
    final ImageTextButton yesButton = (ImageTextButton) Utility.makeButton("Yes", buttonStyle, 200,75);
    final ImageTextButton noButton = (ImageTextButton) Utility.makeButton("No", buttonStyle, 200,75);

    final Callback noUp = () -> {
      if(actorToShow != null) {
        actorToShow.setVisible(true);
      }
      if(this.noUp != null) {
        this.noUp.execute();
      }

      hide();
    };

    buttons = new Container[] {
      Utility.makeButtonContainer(game, yesButton, 200,75, callback),
      Utility.makeButtonContainer(game, noButton, 200,75, noUp)
    };

  }

  public void setActor(Actor actor) {
    actorToShow = actor;
  }
}