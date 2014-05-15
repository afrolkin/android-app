package com.gravswipe.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gravswipe.game.GravSwipe;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "grav swipe";
		cfg.width = 1280;
		cfg.height = 720;
		cfg.forceExit = true;  
		cfg.vSyncEnabled = true;
		new LwjglApplication(new GravSwipe(), cfg);
	}
}
