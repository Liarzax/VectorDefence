package com.TAFE.comp;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class HUD {
	// height 400, width 800.
	private int curPoints = 0;
	private boolean deathMessageDisplayed = false;
	private boolean displayRecivedPowerup = false;
	
	private int displayTimmerMax = 40;
	private int displayTimmerCur = 0;
	
	private int xPadding = 20;
	private int statusLocation = 400;
	private int mainLocation = 415;
	
	public void HUD() {
		
	}
	
	public void initHUD() {
		
	}
	
	public void updateHUD() {
		
	}
	
	public void renderHUD(Graphics g, Color color, Ship player) {
		if (displayRecivedPowerup) {
			g.setColor(Color.yellow);
			g.drawString("POWER UP!", xPadding, statusLocation);
			
			if (displayRecivedPowerup && displayTimmerCur < displayTimmerMax) {
				displayTimmerCur++;
			}
			else {
				displayRecivedPowerup = false;
				displayTimmerCur = 0;
			}
		}
		
		if (!deathMessageDisplayed) {
			g.setColor(color);
			g.drawString("Current Kills "+curPoints, (xPadding + 160), mainLocation);
			
			// display Player HP - can loop, fix later
			g.setColor(Color.green);
			String hpString = "Empty";
			if (player.getHealthCur() == 0) {
				// should not be seeing this screen.
				g.setColor(Color.red);
				hpString = "ERROR";
			}
			if (player.getHealthCur() == 1) {
				g.setColor(Color.red);
				hpString = "|    ";	
			}
			if (player.getHealthCur() == 2) {
				g.setColor(Color.red);
				hpString = "||   ";
			}
			if (player.getHealthCur() == 3) {
				g.setColor(Color.yellow);
				hpString = "|||  ";
			}
			if (player.getHealthCur() == 4) {
				hpString = "|||| ";
			}
			if (player.getHealthCur() == 5) {
				hpString = "|||||";
			}
			
			g.drawString("Ship HP ["+hpString+"]", xPadding, mainLocation);
		}
		else {
			g.setColor(Color.red);
			g.drawString("Player has been Killed with a Total of "+curPoints+" Points!", xPadding, mainLocation);
		}
		
	}
	
	public void increasePoints() {
		curPoints++;
	}

	public void needToDisplayDeathMessage() {
		displayDeathMessage();
	}
	
	private void displayDeathMessage() {
		if (!deathMessageDisplayed) {
			System.out.println("Player ship has been Destroyed!");
			deathMessageDisplayed = true;
		}
	}

	public void displayPowerUpRecived() {
		displayRecivedPowerup = true;
	}
	

}
