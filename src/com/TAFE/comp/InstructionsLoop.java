package com.TAFE.comp;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class InstructionsLoop {

	public boolean canContinue = false;
	
	public InstructionsLoop() {
		
	}
	
	public void initInstructions() {
		
	}
	
	public void updateInstructions() {
		
	}
	
	public void renderInstructions(Graphics g) {
		
		g.setColor(Color.blue);
		g.drawString("Welcome to Vector Defense", 20, 40);
		g.setColor(Color.white);
		g.drawString("You will be the little green rectangle, Destroy the other Squares!", 20, 60);
		g.drawString("Collect the Yellow & Cyan squares for powerups!", 20, 80);
		
		g.drawString("Your ship auto fires, focus on destroying and doging!", 20, 120);
		g.drawString("[W],[A],[S],[D] = Move Ship Up, Left, Down, Right Respectivly", 20, 140);
		g.drawString("    [SPACE]     = Hit Space to get a Short Burst of Speed!", 20, 160);
		
		g.drawString("Your ship has 5 Health & a 3 strengh Shield, Be Carefull!", 20, 200);
		
		g.drawString("Hit [Enter] to get Started!", 20, 240);
		g.drawString("Or [ESC] at anytime to Quit, When you Die, for Instance :(", 20, 260);
		
	}
	
}
