package com.au.Stark.VectorDefence;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/*import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;*/

public class InstructionsLoop {

	public boolean canContinue = false;
	
	// for Text
	private BitmapFont font = new BitmapFont();
	
	public InstructionsLoop() {
		
	}
	
	public void initInstructions() {
		font.setColor(Color.WHITE);
	}
	
	public void updateInstructions() {
		
	}
	
	public void renderInstructions(SpriteBatch sb, ShapeRenderer shapeRenderer) {
		
		font.setColor(Color.BLUE);
		font.draw(sb, "Welcome to Vector Defence", 20, 40);
		font.setColor(Color.WHITE);
		font.draw(sb, "You are the green rectangle, Destroy the other Squares!", 20, 60);
		font.draw(sb, "Collect the Yellow & Cyan squares for powerups!", 20, 80);
		
		font.draw(sb, "Your ship auto fires, focus on destroying and dodging!", 20, 120);
		font.draw(sb, "[W],[A],[S],[D] = Move Ship Up, Left, Down, Right Respectivly", 20, 140);
		font.draw(sb, "    [SPACE]     = Hit Space to get a Short Burst of Speed!", 20, 160);
		
		font.draw(sb, "Your ship has 5 Health & a 3 strengh Shield, Be Carefull!", 20, 200);
		
		font.draw(sb, "Hit [Enter] to get Started!", 20, 240);
		font.draw(sb, "Or [ESC] at anytime to Quit, When you Die, for Instance :(", 20, 260);
		
	}
	
}
