package com.TAFE.comp;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class HUD {
	// height 400, width 800.
	private int curPoints = 0;
	private boolean deathMessageDisplayed = false;
	private boolean displayRecivedPowerup = false;
	
	private boolean weaponPowerup = false;
	private boolean shieldPowerup = false;
	
	private int displayTimmerMax = 40;
	private int displayTimmerCur = 0;
	
	// Left Display Panel
	private Rectangle lPanel = new Rectangle(1, 0, Main.PLAYWIDTHMIN, Main.PLAYHEIGHT);
	private int leftXPadding = 5;
	
	// Bottom 'Quick' Display area.
	private Rectangle BPanel = new Rectangle(1, Main.PLAYHEIGHT, (Main.WIDTH-1), (Main.HEIGHT-1));
	private int bottomXPadding = 20;
	private int statusLocation = 405;
	private int mainLocation = 420;
	
	
	public void HUD() {
		
	}
	
	public void initHUD() {
		
	}
	
	public void updateHUD() {
		
	}
	
	public void renderHUD(Graphics g, Color color, Ship player) {
		displayLeftPanel(g , color, player);
		displayBottomPanel(g , color, player);
		
		if (displayRecivedPowerup) {
			if (weaponPowerup) {
				g.setColor(Color.yellow);
				g.drawString("WEAPON POWER UP!", bottomXPadding, statusLocation);
			}
			if (shieldPowerup) {
				g.setColor(Color.cyan);
				g.drawString("SHIELD POWER UP!", bottomXPadding, statusLocation);
			}
			
			if (displayRecivedPowerup && displayTimmerCur < displayTimmerMax) {
				displayTimmerCur++;
			}
			else {
				displayRecivedPowerup = false;
				weaponPowerup = false;
				shieldPowerup = false;
				displayTimmerCur = 0;
			}
		}
		
		if (!deathMessageDisplayed) {
			g.setColor(color);
			g.drawString("Current Kills "+curPoints, (bottomXPadding + 160), mainLocation);
			
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
			
			g.drawString("Ship HP ["+hpString+"]", bottomXPadding, mainLocation);
		}
		else {
			g.setColor(Color.red);
			g.drawString("Player has been Killed with a Total of "+curPoints+" Points!", bottomXPadding, mainLocation);
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

	public void displayPowerUpRecived(int type) {
		if(type == 1) {
			weaponPowerup = true;
		}
		else if(type == 3) {
			shieldPowerup = true;
		}
		
		displayRecivedPowerup = true;
	}
	
	private void displayLeftPanel(Graphics g, Color color, Ship player) {
		g.setColor(color);
		//g.drawString("Current Kills "+curPoints, (xPadding + 160), mainLocation);
		//panel = new Rectangle(0, 0, 100, 400);
		g.draw(lPanel);
		
		g.drawString(player.getWeaponName(), leftXPadding, 45);
	}
	
	private void displayBottomPanel(Graphics g, Color color, Ship player) {
		g.setColor(color);
		//g.drawString("Current Kills "+curPoints, (xPadding + 160), mainLocation);
		//panel = new Rectangle(0, 0, 100, 400);
		g.draw(BPanel);
	}

}
