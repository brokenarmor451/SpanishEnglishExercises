package inv.game.spanish_english_exercises.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Level {
    public int numberOfButtons;
    public String[] choices;
    public int[] answers;
    public HashMap<String, String> translations = new HashMap<>(20);

    public Level(int numberOfButtons, String[] choices, int[] answers) {
        this.numberOfButtons = numberOfButtons;
        this.choices = choices;
        this.answers = answers;
    }
}