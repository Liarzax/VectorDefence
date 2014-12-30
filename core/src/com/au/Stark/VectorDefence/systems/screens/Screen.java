package com.au.Stark.VectorDefence.systems.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Screen {
	
	public abstract void create();
	
	public abstract void update();
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void reseize(int width, int height);
	
	public abstract void destroy();
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract void hide();
}
