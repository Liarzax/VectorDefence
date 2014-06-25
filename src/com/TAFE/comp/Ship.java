package com.TAFE.comp;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Ship {
	
	Vector2f posCur = new Vector2f(0,0),
			 posNext = new Vector2f(0,0),
			 shipSize = new Vector2f(0,0);
	
	Rectangle ship;

	private float velocity = 0f;
	private float acceleration = 0.04f;
	private float maxSpeed = 0.12f;
	
	private int healthMax = 3;
	private int healthCur = healthMax;
	public boolean needsToRemove = false;
	
	// type 0 = normal red, type 1 = power up yellow, type 2 = blue can fire back!
	public int type = 0;
	
	private int fireRate = 20;
	private int fireCooldown = getFireRate();
	private int dammage = 1;
	private int shipCollisionDammage = 1;
	
	// lets give the ships a SHIELD!
	ShieldSystem shieldSystem = new ShieldSystem();
	
	public void Ship() {
		
	}
	
	public void initShip(float posX, float posY, float sizeX, float sizeY) {
		this.posCur.x = posX;
		this.posCur.y = posY;
		this.posNext = posCur;
		this.shipSize.x = sizeX;
		this.shipSize.y = sizeY;
		
		ship = new Rectangle(posCur.x, posCur.y, shipSize.x, shipSize.y);
		this.ship.setCenterX(posX);
		this.ship.setCenterY(posY);
	}
	
	public void updateShip() {		
		// check if out of bounds. width 800/ height 400
		if (posNext.x > 800) {
			posNext.x = 800;
		}
		if (posNext.x < 1) {
			posNext.x = 1;
		}
		if (posNext.y > 400) {
			posNext.y = 400;
		}
		if (posNext.y < 1) {
			posNext.y = 1;
		}
		
		setShipNewPosition();
		if(shieldSystem.shieldEnabled = true) {
			shieldSystem.updateShieldSystem(posCur.x, posCur.y);
		}
	}
	
	public void renderShip(Graphics g, Color color) {
		g.setColor(color);
		g.draw(ship);
		
		if(shieldSystem.shieldEnabled = true) {
			shieldSystem.renderShieldSystem(g);
		}
	}
	
	public void handleShipUp(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.y -= delta * velocity;
	}
	public void handleShipDown(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.y += delta * velocity;
	}
	public void handleShipLeft(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.x -= delta * velocity;
	}
	public void handleShipRight(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.x += delta * velocity;
	}
	
	public void setShipNewPosition() {
		ship.setCenterY(this.posNext.y);
		ship.setCenterX(this.posNext.x);
	}
	
	public Vector2f getPosCur() {
		return posCur;
	}

	public void setPosCur(Vector2f posCur) {
		this.posCur = posCur;
	}

	public Vector2f getPosNext() {
		return posNext;
	}

	public void setPosNext(Vector2f posNext) {
		this.posNext = posNext;
	}

	public void processDammage(int dammage) {
		// animation, sound effect
		
		healthCur -= dammage;
		if (healthCur < 1) {
			needsToRemove = true;
		}
		//return health;
	}
	
	public void destroyShip() {
		// animation, sound effects, maybe power-up points, etc.
		System.out.println("Boom!");
	}
	
	public void setShipHealth(int shipHealth) {
		healthMax = shipHealth;
		healthCur = healthMax;
	}

	public void increaseMaxSpeed(float value) {
		maxSpeed += value;
	}

	public int getFireCooldown() {
		return fireCooldown;
	}

	public void setFireCooldown(int fireCooldown) {
		this.fireCooldown = fireCooldown;
	}

	public int getFireRate() {
		return fireRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	public void proccessPowerup(Ship ship) {
		// powerup augments ship!
		ship.fireRate -= 2;
		ship.setDammage(ship.getDammage() + 1);
		
	}

	public int getShipCollisionDammage() {
		return shipCollisionDammage;
	}

	public void setShipCollisionDammage(int shipCollisionDammage) {
		this.shipCollisionDammage = shipCollisionDammage;
	}

	public int getDammage() {
		return dammage;
	}

	public void setDammage(int dammage) {
		this.dammage = dammage;
	}

	public int getHealthMax() {
		return healthMax;
	}

	public void setHealthMax(int healthMax) {
		this.healthMax = healthMax;
	}

	public int getHealthCur() {
		return healthCur;
	}

	public void setHealthCur(int healthCur) {
		this.healthCur = healthCur;
	}
	
	
}
