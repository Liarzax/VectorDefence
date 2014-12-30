package com.au.Stark.VectorDefence;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/*import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;*/

public class Ship {
	
	public Vector2 posCur = new Vector2(0,0);

	Vector2 posNext = new Vector2(0,0);

	Vector2 shipSize = new Vector2(0,0);
	
	Rectangle ship;

	/*private float velocity = 0f;
	private float acceleration = 0.04f;
	private float maxSpeed = 0.12f;*/
	private float velocity = 0f;
	private float acceleration = 0.02f;
	private float maxSpeed = 0.03f;
	
	private int healthMax = 3;
	private int healthCur = healthMax;
	public boolean needsToRemove = false;
	public boolean deathFromPlayer = false;
	public boolean collideWithPlayer = false;
	
	// ship type 0 = normal red, type 1 = power up yellow, type 2 = blue can fire back!
	public int type = 0;
	private boolean isBossShip = false;
	// boss type 1 = red, 2 = blue
    public int bossType = 0;
	
	private Weapon weapon = new Weapon();
	private int weaponLevel = 0;
	private int weaponEXP = 0;
	
	// maby depend this on hull type, same as hp, maxhp, etc?
	private int shipCollisionDammage = 1;
	
	// lets give the ships a SHIELD!
	public ShieldSystem shieldSystem = new ShieldSystem();
	
	// overdrive
	public int overdriveCooldownCur = 0;
	public int overdriveCooldownMax = 120;
	public int overdriveDuration = 0;
	public int overdriveDurationMax = 20;
	public boolean overDriveActive = false;
	

	
	public void Ship() {
		
	}
	
	public void initShip(float posX, float posY, float sizeX, float sizeY) {
		this.posCur.x = posX;
		this.posCur.y = posY;
		this.posNext = posCur;
		this.shipSize.x = sizeX;
		this.shipSize.y = sizeY;
		
		ship = new Rectangle(posCur.x, posCur.y, shipSize.x, shipSize.y);
		this.ship.setCenter(posX, posY);
		//this.ship.setCenterY(posY);
	}
	
	public void updateShip() {		
		// check if out of bounds. width 800/ height 400
		if (posNext.x > MainGame.PLAYWIDTHMAX) {
			posNext.x = MainGame.PLAYWIDTHMAX;
		}
		if (posNext.x < MainGame.PLAYWIDTHMIN) {
			posNext.x = MainGame.PLAYWIDTHMIN;
		}
		if (posNext.y > MainGame.PLAYHEIGHT) {
			posNext.y = MainGame.PLAYHEIGHT;
		}
		if (posNext.y < 1) {
			posNext.y = 1;
		}
		
		setShipNewPosition();
		if (shieldSystem != null) {
			if(shieldSystem.isShieldEnabled()) {
				shieldSystem.updateShieldSystem(posCur.x, posCur.y);
			}
		}
	}
	
	public void renderShip(ShapeRenderer shapeRenderer, Color color) {
		if (!needsToRemove) {
			//g.setColor(color);
			//g.draw(ship);
			shapeRenderer.setColor(color);
			//shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.rect(ship.x, ship.y,ship.width, ship.height);
			//shapeRenderer.end();
			
			if (shieldSystem != null) {
				if(shieldSystem.isShieldEnabled()) {
					shieldSystem.renderShieldSystem(shapeRenderer);
				}
			}
		}
	}
	
	public void handleShipUp(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.y -= delta * velocity;
		//posNext.y -= velocity;
	}
	public void handleShipDown(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.y += delta * velocity;
		//posNext.y += velocity;
	}
	public void handleShipLeft(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.x -= delta * velocity;
		//posNext.x -= velocity;
	}
	public void handleShipRight(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.x += delta * velocity;
		//posNext.x += velocity;
	}
	
	public void setShipNewPosition() {
		ship.setCenter(this.posNext.x, this.posNext.y);
		//ship.setCenter(this.posNext.x);
	}
	
	public Vector2 getPosCur() {
		return posCur;
	}
	
	public float getPosCurX() {
		return posCur.x;
	}
	
	public float getPosCurY() {
		return posCur.y;
	}

	public void setPosCur(Vector2 posCur) {
		this.posCur = posCur;
	}

	public Vector2 getPosNext() {
		return posNext;
	}

	public void setPosNext(Vector2 posNext) {
		this.posNext = posNext;
	}

	public void processDammage(int dammage) {
		// animation, sound effect
		
		healthCur -= dammage;
		if (healthCur < 1) {
			needsToRemove = true;
			//deathFromPlayer = true;
		}
		//return health;
	}
	
	public void destroyShip() {
		// animation, sound effects, maybe power-up points, etc.
		System.out.println("Boom!");
		shieldSystem.setShieldEnabled(false);
	}
	
	public void setShipHealth(int shipHealth) {
		healthMax = shipHealth;
		healthCur = healthMax;
	}

	public void increaseMaxSpeed(float value) {
		maxSpeed += value;
	}

	public int getFireCooldown() {
		return weapon.getFireCooldown();
	}

	public void setFireCooldown(int fireCooldown) {
		this.weapon.setFireCooldown(fireCooldown);
	}

	public int getFireRate() {
		return this.weapon.getFireRate();
	}

	public void setFireRate(int fireRate) {
		this.weapon.setFireRate(fireRate);
	}

	public void proccessPowerup(Ship ship, int type) {
		// powerup augments ship!
		if (type == 1) {
			ship.weapon.fireRate -= 1;
			ship.setDammage(ship.getDammage() + 1);
		}
		if (type == 3) {
			if (ship.shieldSystem != null) {
				ship.shieldSystem.regenShield(ship);
			}
			else {
				ship.shieldSystem = new ShieldSystem();
				ship.shieldSystem.initShieldSystem(ship.posCur.x, ship.posCur.y);
			}
			//ship.shieldSystem.regenShield();
		}
	}

	public int getShipCollisionDammage() {
		return shipCollisionDammage;
	}

	public void setShipCollisionDammage(int shipCollisionDammage) {
		this.shipCollisionDammage = shipCollisionDammage;
	}

	public int getDammage() {
		return this.weapon.dammage;
	}

	public void setDammage(int dammage) {
		this.weapon.dammage = dammage;
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

	public Vector2 getShipSize() {
		return shipSize;
	}

	public void setShipSize(Vector2 shipSize) {
		this.shipSize = shipSize;
	}
	
	public String getWeaponName() {
		return this.weapon.getName();
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public ShieldSystem getShieldSystem() {
		return shieldSystem;
	}

	public void setShieldSystem(ShieldSystem shieldSystem) {
		this.shieldSystem = shieldSystem;
	}

	public int getWeaponLevel() {
		return weaponLevel;
	}

	public void setWeaponLevel(int weaponLevel) {
		this.weaponLevel = weaponLevel;
	}

	public int getWeaponEXP() {
		return weaponEXP;
	}

	public void setWeaponEXP(int weaponEXP) {
		this.weaponEXP = weaponEXP;
	}

	public boolean isBossShip() {
		return isBossShip;
	}

	public void setBossShip(boolean isBossShip) {
		this.isBossShip = isBossShip;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void handleShipDeAcceleration(int delta) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
