package inv.game.spanish_english_exercises.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;

import inv.game.spanish_english_exercises.SpEnExercises;
import inv.game.spanish_english_exercises.screens.ExercisesScreen;

public class ChoiceInput extends InputListener {
    private int choiceIndex;
    private ExercisesScreen exercisesScreen;
    private Container<ImageTextButton> button;
    private SpEnExercises game;

    public ChoiceInput(SpEnExercises game, int i, ExercisesScreen exercisesScreen, Container<ImageTextButton> button) {
        choiceIndex = i;
        this.game = game;
        this.exercisesScreen = exercisesScreen;
        this.button = button;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.button.getActor().addAction(Actions.scaleTo(0.5f,0.5f, 0.03f));
        return true;
    }
            
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        final float xPos = event.getStageX();
        final float yPos = event.getStageY();
        
        final Vector2 ps = this.button.localToStageCoordinates(new Vector2(0,0));

        this.button.getActor().clearActions(); // to avoid previous action overlaps current

          if(
            (xPos > ps.x) &&
            (xPos < ps.x + this.button.getWidth()) &&
            (yPos > ps.y) &&
            (yPos < ps.y + this.button.getHeight())
          ) {
            game.playChoiceClickSound();

            this.button.getActor().addAction(Actions.sequence(Actions.scaleTo(1f, 1f, 0.05f), Actions.run(
              () -> {
                final String text = (String) ((ImageTextButton) this.button.getActor()).getText().toString();
                exercisesScreen.checkSelectionAndUpdate(this.button,choiceIndex,text);
              }
            )));

          
        } else {
          this.button.getActor().addAction(Actions.scaleTo(1f, 1f, 0.05f));
        }
    }
}