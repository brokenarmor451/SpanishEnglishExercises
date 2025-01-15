package inv.game.spanish_english_exercises.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;

import inv.game.spanish_english_exercises.SpEnExercises;
import inv.game.spanish_english_exercises.screens.ExercisesScreen;

public class ButtonRun extends InputListener {
    private Container button;
    private final Callback onDown;
    private final Callback onUp;
    private final SpEnExercises game;

    public ButtonRun(SpEnExercises game, Container button, Callback onDown, Callback onUp) {
        this.game = game;
        this.button = button;
        this.onDown = onDown;
        this.onUp = onUp;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.button.getActor().addAction(Actions.scaleTo(0.5f,0.5f, 0.05f));

        if(onDown != null) {
            onDown.execute();;
        }

        return true;
    }
            
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        final float xPos = event.getStageX();
        final float yPos = event.getStageY();
        
        final Vector2 ps = this.button.localToStageCoordinates(new Vector2(0,0));

        this.button.getActor().clearActions();
        
        if(
          (xPos > ps.x) &&
          (xPos < ps.x + this.button.getWidth()) &&
          (yPos > ps.y) &&
          (yPos < ps.y + this.button.getHeight())
        ) {
            game.playUiClickSound();
            this.button.getActor().addAction(Actions.scaleTo(1f, 1f, 0.05f));
            onUp.execute();
            
        } else {
          this.button.getActor().addAction(Actions.scaleTo(1f, 1f, 0.05f));
        }
    }

    public static interface Callback {
    
        public abstract void execute();
        
    }
}