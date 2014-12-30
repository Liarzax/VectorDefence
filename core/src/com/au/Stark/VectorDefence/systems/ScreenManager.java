package com.au.Stark.VectorDefence.systems;

import com.au.Stark.VectorDefence.systems.screens.Screen;

public class ScreenManager {
	
	// TODO set starting point, splash screen or something?
	private static Screen currentScreen;// = new MenuScreen();
	
	public static void setScreen(Screen screen) {
		if (currentScreen != null) {
			// if a screen exists.
			// destroy everything on the current screen.
			currentScreen.destroy();
		}
		
		// switch to new screen.
		currentScreen = screen;
		// create new screen.
		currentScreen.create();
		
	}
	
	public static Screen getCurrentScreen() {
		return currentScreen;
	}
	
}
