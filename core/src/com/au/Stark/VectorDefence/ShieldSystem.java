package com.au.Stark.VectorDefence;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;

/*import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;*/

public class ShieldSystem {
	
	private boolean shieldEnabled = false;
	private int shieldMax = 3;
	private int shieldCur = shieldMax;
	
	// curX, curY, Range;
	Circle shield = null;
	private float shieldRange = 24f;
	//public boolean removeShield;
	
	
	public ShieldSystem() {
		
	}
	
	public void initShieldSystem(float curX, float curY) {
		setShieldEnabled(true);
		shield = new Circle(curX, curY, shieldRange);
	}
	
	public void updateShieldSystem(float curX, float curY) {
		if (isShieldEnabled() && shield != null) {
			// just give it 1hp for now?
			
			// update position with ship
			//shield.setCenterX(curX);
			shield.setX(curX);
			//shield.setCenterY(curY);
			shield.setY(curY);
		}
	}
	
	public void renderShieldSystem(ShapeRenderer shapeRenderer) {
		if (isShieldEnabled() && shield != null) {
			//g.setColor(Color.cyan);
			//g.draw(shield);
			//shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(Color.CYAN);
			shapeRenderer.circle(shield.x, shield.y, shieldRange);
			//shapeRenderer.end();
			
		}
		else {
			// shields off
		}
	}
	
	public void dammageShield(int dammage) {
		shieldCur -= dammage;
		if (shieldCur <= 0) {
			setShieldEnabled(false);
			shield.setRadius(0);
		}
	}
	
	public void regenShield(Ship ship) {
		if(!ship.shieldSystem.isShieldEnabled()) {
			ship.shieldSystem.setShieldEnabled(true);
			
			ship.shieldSystem.shield = new Circle(ship.posCur.x, ship.posCur.y, shieldRange);
			ship.shieldSystem.shieldCur = 1;
		}
		else {
			//shieldEnabled = true;
			ship.shieldSystem.shieldCur++;
			if (ship.shieldSystem.shieldCur > ship.shieldSystem.shieldMax) {
				ship.shieldSystem.shieldCur = ship.shieldSystem.shieldMax;
			}
		}
	}

	public boolean isShieldEnabled() {
		return shieldEnabled;
	}

	public void setShieldEnabled(boolean shieldEnabled) {
		this.shieldEnabled = shieldEnabled;
	}

	public int getShieldMax() {
		return shieldMax;
	}

	public void setShieldMax(int shieldMax) {
		this.shieldMax = shieldMax;
	}

	public int getShieldCur() {
		return shieldCur;
	}

	public void setShieldCur(int shieldCur) {
		this.shieldCur = shieldCur;
	}
	
	

}
