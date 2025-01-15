package inv.game.spanish_english_exercises.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import games.rednblack.miniaudio.MiniAudio;

import inv.game.spanish_english_exercises.SpEnExercises;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MiniAudio miniAudio = new MiniAudio(null,false,false,true);

        miniAudio.setupAndroid(getAssets());

        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true; // Recommended, but not required.
        initialize(new SpEnExercises(miniAudio), configuration);
    }
}