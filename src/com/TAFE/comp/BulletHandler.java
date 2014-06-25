package com.TAFE.comp;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BulletHandler {

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
				if (bullets.get(i).posCur.x > 800) {
					bullets.remove(bullets.get(i));
				}
			}
		}
	}
	
	public void renderBullets(Graphics g, Color color) {
		if (!bullets.isEmpty()) {
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).renderBullet(g, color);
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