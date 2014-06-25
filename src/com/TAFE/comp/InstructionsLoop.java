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
		g.drawString("Welcome to vector Deffence", 20, 40);
		g.setColor(Color.white);
		g.drawString("You will be the little green rectangle, Destroy the red Squares!", 20, 60);
		g.drawString("Collect the Yellow squares for a fire rate and dammage increase!", 20, 80);
		
		g.drawString("[W],[A],[S],[D] = Move Ship Up, Left, Down, Right Respectivly", 20, 120);
		g.drawString("    [SPACE]     = Hit space to shoot!", 20, 140);
		
		g.drawString("Your ship has 5 Health, Be Carefull!", 20, 180);
		
		g.drawString("Hit [Enter] to get Started!", 20, 220);
		g.drawString("Or [ESC] at anytime to Quit :(", 20, 240);
		
	}
	
}
