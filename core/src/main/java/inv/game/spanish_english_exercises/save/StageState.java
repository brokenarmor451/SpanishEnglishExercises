package inv.game.spanish_english_exercises.save;

import java.util.ArrayList;
import java.util.HashMap;

public class StageState {
    public int currentLevel;
    public int foundCount;
    public int completedCount;
    public int currentCorrect;
    public int answerIndex = 0;
    public int titleIndex = 0;
    public int numberOfLevels;
    public ArrayList<Integer> buttonContainerIndices;
    public ArrayList<ArrayList<Integer>> buttonIndices;
    public ArrayList<ArrayList<Integer>> titleIndices;
    //public boolean[] founds;
    public HashMap<String, Boolean> founds;

    public StageState(int currentLevel, int foundCount) {

    }

    public void reset() {
      foundCount = 0;
      answerIndex = 0;
      titleIndex = 0;
    }

    public void resetMedium() {
        currentLevel = 0;
        founds = new HashMap<>(8);
    }
}