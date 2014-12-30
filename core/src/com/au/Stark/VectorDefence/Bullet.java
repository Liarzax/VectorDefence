package com.au.Stark.VectorDefence;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/*import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;*/

public class Bullet {
	
	Vector2 posCur = new Vector2(0,0),
			 posNext = new Vector2(0,0),
			 bulletSize = new Vector2(5,8);
	
	Rectangle bullet;

	private float velocity = 0f;
	private float acceleration = 0.08f;
	//private float maxSpeed = 0.32f;
	private float maxSpeed = 0.12f;
	
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
		this.bullet.setCenter(curX, curY);
		//this.bullet.setCenterY(curY);
	}
	
	public void updateBullet(int delta) {
		if (bulletType == 0) {
			handleBulletRight(delta);
			setBulletNewPoss();
			if (this.posCur.x >= MainGame.OFFSCREENWIDTHBUFFER) {
				this.needsToRemove = true;
			}
		}
		else if (bulletType == 1) {
			handleBulletLeft(delta);
			setBulletNewPoss();
			if (this.posCur.x <= MainGame.PLAYWIDTHMIN) {
				this.needsToRemove = true;
			}
		}
	}
	
	public void renderBullet(ShapeRenderer shapeRenderer, Color color) {
		//g.setColor(color);
		//g.draw(bullet);
		shapeRenderer.setColor(color);
		shapeRenderer.rect(bullet.x, bullet.y,bullet.width, bullet.height);
	}
	
	private void handleBulletRight(int delta) {
		velocity = velocity + acceleration;
		if(velocity  > maxSpeed){
			velocity  = maxSpeed;
		}
		
		posNext.x += delta * velocity;
	}
	
	private void setBulletNewPoss() {
		bullet.setCenter(this.posNext.x, this.posCur.y);
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
