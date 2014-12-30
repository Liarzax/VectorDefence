package com.au.Stark.VectorDefence;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/*import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;*/

public class BulletHandler {

	//TODO Need to get these of the player/enemy firing said bullet. (In-case ship has diff sizes).
	private int playerOffset = 8;
	private int enemyOffset = 8;
	List<Bullet> bullets;
	
	public void BulletHandler() {
		
	}
	
	public void initBulletHandler() {
		bullets = new ArrayList<Bullet>();
	}
	
	public void updateBullets(int delta) {
		if (!bullets.isEmpty()) {
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).updateBullet(delta);
				
				// if outside range, remove bullet. Width = 800, Height = 400
				if (bullets.get(i).posCur.x > MainGame.OFFSCREENWIDTHBUFFER) {
					bullets.remove(bullets.get(i));
				}
			}
		}
	}
	
	public void renderBullets(ShapeRenderer shapeRenderer, Color color) {
		if (!bullets.isEmpty()) {
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).renderBullet(shapeRenderer, color);
			}
		}
	}
	
	
	public void createNewBullet(float bulletX, float bulletY, int dammage, int bulletType) {
		Bullet bullet = new Bullet();
		bullet.setBulletDammage(dammage);
		bullet.setBulletType(bulletType);
		// need to add player/enemy offset to not shoot self.
		// 0 players, 1 enemies
		if (bullet.getBulletType() == 0) {
			bullet.initBullet((bulletX+playerOffset), bulletY);
		}
		else if (bullet.getBulletType() == 1) {
			bullet.initBullet((bulletX-enemyOffset), bulletY);
		}
		
		
		bullets.add(bullet);
	}

	public int getAmmountOfBullets() {
		return bullets.size();
	}
		
}
