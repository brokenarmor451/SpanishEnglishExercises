package inv.game.spanish_english_exercises.screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import inv.game.spanish_english_exercises.objects.LearningStages;
import inv.game.spanish_english_exercises.save.StageState;

public class ExercisesScreenTest {
    private static ExercisesScreen es;

    @BeforeAll
    public static void init() {
        es = new ExercisesScreen(null);
        es.setTitlesAndChoices(new LearningStages().get(0));
        es.setSavedState(new StageState(0, 0));
        es.makeIndices();
        es.updateCurrentLevelText();
        es.initSavedState();
    }

    @Test
    public void checkTextsAndUpdate() {


        for(int i = 0; i < 8; i++) {
            es.checkTextsAndUpdate("Ok", "Ok");
        }

        assertTrue(es.isAllWordsFound());

        assertEquals(es.currentLevelText, String.valueOf(1));
        
        for(int i = 0; i < 2; i++) {
            es.moveToNextLevel();
        }

        es.updateCurrentLevelText();
        
        assertEquals(es.currentLevelText, String.valueOf(3));

        assertTrue(es.isAllLevelsFinished());
        
    }

    @Test
    public void isAlreadyFound() {
        es.markFound("One");

        assertTrue(es.isAlreadyFound("One"));
        assertFalse(es.isAlreadyFound("Two"));
    }

}