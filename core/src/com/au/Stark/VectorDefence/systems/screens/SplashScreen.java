package com.au.Stark.VectorDefence.systems.screens;

import com.au.Stark.VectorDefence.MainGame;
import com.au.Stark.VectorDefence.systems.ScreenManager;
import com.au.Stark.VectorDefence.systems.TextureManager;
import com.au.Stark.VectorDefence.systems.camera.OrthoCamera;
//import com.au.Stark.SpaceInvaders.entity.Missile;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen extends Screen {
	
	private OrthoCamera camera;
	private Texture texture;
	private long splashTime;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		texture = TextureManager.STARKLOGO;
		camera = new OrthoCamera();
		//camera.resize();
		splashTime = System.currentTimeMillis();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		camera.update();
		
		if((System.currentTimeMillis() - splashTime) >= 1450) {
			ScreenManager.setScreen(new GameScreen());
		}
		
		
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(texture, (MainGame.WIDTH / 2) - (texture.getWidth() / 2), (MainGame.HEIGHT / 2) - (texture.getHeight() / 2));
		sb.end();
	}

	@Override
	public void reseize(int width, int height) {
		// TODO Auto-generated method stub
		camera.resize();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
