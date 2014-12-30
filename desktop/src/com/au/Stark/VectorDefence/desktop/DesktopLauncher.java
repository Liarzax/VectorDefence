package com.au.Stark.VectorDefence.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.au.Stark.VectorDefence.MainGame;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = MainGame.TITLE;
		// used to load pictures NOT a power of 2.
		//config.useGL20 = true;
		config.width = MainGame.WIDTH;
		config.height = MainGame.HEIGHT;
		
		//config.fullscreen = MainGame.FULLSCREEN;
		//config.foregroundFPS = MainGame.FPSLIMIT;
		
		new LwjglApplication(new MainGame(), config);
	}
}
