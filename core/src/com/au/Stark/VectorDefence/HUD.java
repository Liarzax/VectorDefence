package com.au.Stark.VectorDefence;

import com.au.Stark.VectorDefence.MainGame;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/*import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;*/

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
	private Rectangle lPanel = new Rectangle(1, 0, MainGame.PLAYWIDTHMIN, MainGame.PLAYHEIGHT);
	private int leftXPadding = 5;
	
	// Bottom 'Quick' Display area.				v 40
	private Rectangle BPanel = new Rectangle(1, MainGame.PLAYHEIGHT, (MainGame.WIDTH-1), (MainGame.HEIGHT-1));
	private int bottomXPadding = 20;
	private int statusLocation = 405;
	private int mainLocation = 420;
	
	// for Text
	private BitmapFont font = new BitmapFont();
	
	public void HUD() {
		
	}
	
	public void initHUD() {
		font.setColor(Color.WHITE);
	}
	
	public void updateHUD() {
		
	}
	
	public void renderHUD(SpriteBatch sb, ShapeRenderer shapeRenderer, Color color, Ship player) {
		displayLeftPanel(sb, shapeRenderer , color, player);
		displayBottomPanel(sb, shapeRenderer , color, player);
		
		if (displayRecivedPowerup) {
			if (weaponPowerup) {
				//g.setColor(Color.YELLOW);
				//g.drawString("WEAPON POWER UP!", bottomXPadding, statusLocation);
				shapeRenderer.setColor(Color.YELLOW);
				
			}
			if (shieldPowerup) {
				//g.setColor(Color.CYAN);
				//g.drawString("SHIELD POWER UP!", bottomXPadding, statusLocation);
				shapeRenderer.setColor(Color.CYAN);
				
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
			font.setColor(color);
			font.draw(sb, "Current Kills "+curPoints, (bottomXPadding + 160), mainLocation);
			
			// display Player HP - can loop, fix later
			font.setColor(Color.GREEN);
			String hpString = "Empty";
			if (player.getHealthCur() == 0) {
				// should not be seeing this screen.
				font.setColor(Color.RED);
				hpString = "ERROR";
			}
			if (player.getHealthCur() == 1) {
				font.setColor(Color.RED);
				hpString = "|    ";	
			}
			if (player.getHealthCur() == 2) {
				font.setColor(Color.RED);
				hpString = "||   ";
			}
			if (player.getHealthCur() == 3) {
				font.setColor(Color.YELLOW);
				hpString = "|||  ";
			}
			if (player.getHealthCur() == 4) {
				hpString = "|||| ";
			}
			if (player.getHealthCur() == 5) {
				hpString = "|||||";
			}
			
			font.draw(sb, "Ship HP ["+hpString+"]", bottomXPadding, mainLocation);
		}
		else {
			//g.setColor(Color.RED);
			//g.drawString("Player has been Killed with a Total of "+curPoints+" Points!", bottomXPadding, mainLocation);
			font.setColor(Color.RED);
			font.draw(sb, "Player has been Killed with a Total of "+curPoints+" Points!", bottomXPadding, mainLocation);
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
	
	private void displayLeftPanel(SpriteBatch sb, ShapeRenderer shapeRenderer, Color color, Ship player) {
		//g.setColor(color);
		shapeRenderer.setColor(color);
		//g.drawString("Current Kills "+curPoints, (xPadding + 160), mainLocation);
		//panel = new Rectangle(0, 0, 100, 400);
		//g.draw(lPanel);
		shapeRenderer.rect(lPanel.x, lPanel.y,lPanel.width, lPanel.height);
		
		//g.drawString(player.getWeaponName(), leftXPadding, 45);
		//g.drawString("Level = "+player.getWeaponLevel(), leftXPadding, 65);
		//g.drawString("Exp = " + player.getWeaponEXP(), leftXPadding, 85);
		
		font.setColor(color);
		font.draw(sb, player.getWeaponName(), leftXPadding, 45);
		font.draw(sb, "Level = "+player.getWeaponLevel(), leftXPadding, 65);
		font.draw(sb, "Exp = " + player.getWeaponEXP(), leftXPadding, 85);
		
	}
	
	private void displayBottomPanel(SpriteBatch sb, ShapeRenderer shapeRenderer, Color color, Ship player) {
		//g.setColor(color);
		shapeRenderer.setColor(color);
		//g.drawString("Current Kills "+curPoints, (xPadding + 160), mainLocation);
		//panel = new Rectangle(0, 0, 100, 400);
		//g.draw(BPanel);
		shapeRenderer.setColor(color);
		shapeRenderer.rect(BPanel.x, BPanel.y,BPanel.width, BPanel.height);
	}

}
