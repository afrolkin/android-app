package com.gravswipe.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gravswipe.game.GravSwipe;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//		config.numSamples = 2;
		config.hideStatusBar = true;
		config.useImmersiveMode = true;
		initialize(new GravSwipe(), config);
	}
}
