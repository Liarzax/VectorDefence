package com.TAFE.comp;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
	
	Vector2f posCur = new Vector2f(0,0),
			 posNext = new Vector2f(0,0),
			 bulletSize = new Vector2f(5,8);
	
	Rectangle bullet;

	private float velocity = 0f;
	private float acceleration = 0.08f;
	private float maxSpeed = 0.32f;
	
	public boolean needsToRemove = false;
	private int bulletDammage = 1;
	
	// 0 players, 1 enemies
	private int bulletType = 0;
	
	public void Bullet() {
		
	}
	
	public void initBullet(float curX, float curY) {
		this.posCur.x = curX;
		this.posCur.y = curY;
		this.posNext = posCur;
		
		bullet = new Rectangle(posCur.x, posCur.y, bulletSize.x, bulletSize.y);
		this.bullet.setCenterX(curX);
		this.bullet.setCenterY(curY);
	}
	
	public void updateBullet(int delta) {
		if (bulletType == 0) {
			handleBulletRight(delta);
			setBulletNewPoss();
		}
		else if (bulletType == 1) {
			handleBulletLeft(delta);
			setBulletNewPoss();
		}
	}
	
	public void renderBullet(Graphics g, Color color) {
		g.setColor(color);
		g.draw(bullet);
	}
	
	private void handleBulletRight(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.x += delta * velocity;
	}
	
	private void setBulletNewPoss() {
		bullet.setCenterX(this.posNext.x);
	}

	public void setBulletDammage(int bulletDammage) {
		this.bulletDammage = bulletDammage;
	}
	
	public int getBulletDammage() {
		return this.bulletDammage;
	}
	
	private void handleBulletLeft(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.x -= delta * velocity;
	}
	
	public int getBulletType() {
		return bulletType;
	}

	public void setBulletType(int bulletType) {
		this.bulletType = bulletType;
	}
}
