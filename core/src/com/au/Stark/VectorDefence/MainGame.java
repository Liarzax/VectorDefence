package com.au.Stark.VectorDefence;

import com.au.Stark.VectorDefence.systems.ScreenManager;
import com.au.Stark.VectorDefence.systems.TextureManager;
import com.au.Stark.VectorDefence.systems.screens.SplashScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MainGame extends ApplicationAdapter {
	
	final static int majorVersion = 0, minorVersion = 1, bugfix = 5, buildRev = 10;
	final static String devStage = "Alpha";
	final static String version = "v"+majorVersion+"."+minorVersion+"."+bugfix+"-"+devStage+"   build."+buildRev;
	public static String TITLE = "Vector Defence "+version;
	
	public static final int  WIDTH = 800, PLAYWIDTHMAX = 800, PLAYWIDTHMIN = 100, OFFSCREENWIDTHBUFFER = 815,
							 HEIGHT = 440, PLAYHEIGHT = 400, OFFSCREENHEIGHTBUFFER = 455,
							 				//PLAYHEIGHT = 440
							 FPS_LIMIT = 60;
	
	public static boolean  showFPS = true,
						   fullscreen = false,
						   debug = true;
	
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		//ScreenManager.setScreen(new GameScreen());
		ScreenManager.setScreen(new SplashScreen());
		
	}

	@Override
	public void dispose() {
		if (ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().destroy();
		}
		batch.dispose();
		
	}

	@Override
	public void render() {
		// clears screen to black (0,0,0) or white (1,1,1) or Blue (0,0,1) or etc.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().update();
		}
		
		if (ScreenManager.getCurrentScreen() != null) {
			//batch.setProjectionMatrix(camera.combined);
			//batch.begin();
			ScreenManager.getCurrentScreen().render(batch);
			//batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		if (ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().reseize(WIDTH, HEIGHT);
		}
	}

	@Override
	public void pause() {
		if (ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().pause();
		}
		
	}

	@Override
	public void resume() {
		if (ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().resume();
		}
		
	}
}
