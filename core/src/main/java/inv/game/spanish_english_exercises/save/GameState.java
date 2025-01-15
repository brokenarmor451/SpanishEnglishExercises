package inv.game.spanish_english_exercises.save;

import java.util.Arrays;

public final class GameState {
    public static enum Difficulty {
      EASY,
      MEDIUM,
      HARD
    }


    public char[] unlockedStages = new char[2000];
    private int currentStage;
    public boolean enterToLearning = true;
    public boolean musicOn = true;
    public boolean soundOn = true;
    public Difficulty gameDifficulty = Difficulty.EASY;
    
    public GameState() {
        Arrays.fill(unlockedStages, 'N');
        unlockedStages[0] = 'O';
    }

    public void unlockNextStage() {
        unlockedStages[currentStage] = 'Y';
        currentStage++;
        if(currentStage < 2000) {
          if(unlockedStages[currentStage] == 'N') {
            unlockedStages[currentStage] = 'O';
          }
        }
      }

      public void restartFromStage1() {
        unlockedStages = new char[2000];
        Arrays.fill(unlockedStages, 'N');
        unlockedStages[0] = 'O';
        setCurrentStage(0);
      }

      public void setCurrentStage(int i) {
        currentStage = i;
      }

      public int getCurrentStage() {
        return currentStage;
      }

      public void setDifficulty(Difficulty difficulty) {
        gameDifficulty = difficulty;
      }
}