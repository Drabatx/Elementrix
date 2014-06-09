package drabat.elementrixgameandroid;

import com.badlogic.gdx.backends.android.AndroidApplication;

import drabat.elementrix.game.GameElementrix;

import android.os.Bundle;

public class Main extends AndroidApplication{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new GameElementrix(), false);
    }
}
