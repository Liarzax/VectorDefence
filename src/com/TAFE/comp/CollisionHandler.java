package com.TAFE.comp;

public class CollisionHandler {
	
	
	public void CollisionHandler() {
		
	}
	
	public void initCollisionHandler() {
		
	}
	
	// check for bullets v enemies, bullets v player, enemies v player
	public void checkForCollisions(BulletHandler bullets, EnemyHandler enemies) {
		// temp collisions.
		if(!bullets.bullets.isEmpty() && !enemies.enemiesShip.isEmpty()) {
			for (int b = 0; b < bullets.getAmmountOfBullets(); b++) {
				for (int e = 0; e < enemies.getAmmountOfEnemies(); e++) {
					// if collide, damage enemy, remove bullet.
					// ship type 0 = normal red, type 1 = power up yellow, type 2 = blue can fire back!
					// bullet type 0 = players, bullet type 1 = enemies
					if (bullets.bullets.get(b).bullet.intersects(enemies.enemiesShip.get(e).ship) && bullets.bullets.get(b).getBulletType() == 0 && (enemies.enemiesShip.get(e).type != 1 || enemies.enemiesShip.get(e).type != 3)) {
						enemies.enemiesShip.get(e).processDammage(bullets.bullets.get(b).getBulletDammage());
						if(enemies.enemiesShip.get(e).getHealthCur() <= 0) {
							enemies.enemiesShip.get(e).deathFromPlayer = true;
						}
						bullets.bullets.get(b).needsToRemove = true;
					}
					else if (bullets.bullets.get(b).bullet.intersects(enemies.enemiesShip.get(e).ship) && bullets.bullets.get(b).getBulletType() == 1 && (enemies.enemiesShip.get(e).type != 1 || enemies.enemiesShip.get(e).type != 3)) {
						bullets.bullets.get(b).needsToRemove = true;
					}
				}
			}
		}
	}
	public void checkForCollisions(BulletHandler bullets, Ship player) {
		if(!bullets.bullets.isEmpty() && !player.needsToRemove) {
			for (int b = 0; b < bullets.getAmmountOfBullets(); b++) {
				// if collide, damage player, remove bullet.
				// if bullet hits players shield, and bullet is enemies, and shield is active, damage the shield.
				if(bullets.bullets.get(b).bullet.intersects(player.shieldSystem.shield) && bullets.bullets.get(b).getBulletType() == 1 && player.shieldSystem.shieldEnabled) {
					player.shieldSystem.dammageShield(bullets.bullets.get(b).getBulletDammage());
					bullets.bullets.get(b).needsToRemove = true;
					//player.shieldSystem.removeShield = true;
					//player.shieldSystem.shield.setRadius(0);
				}
				// else if the bullet hits the player and is the enemies, damage the player
				if (bullets.bullets.get(b).bullet.intersects(player.ship) && bullets.bullets.get(b).getBulletType() == 1) {
					player.processDammage(bullets.bullets.get(b).getBulletDammage());
					bullets.bullets.get(b).needsToRemove = true;
				}
			}
		}
	}
	public void checkForCollisions(EnemyHandler enemies, Ship player) {
		if(!enemies.enemiesShip.isEmpty() && !player.needsToRemove) {
			for (int e = 0; e < enemies.getAmmountOfEnemies(); e++) {
				// if collide, damage player, remove ship.
				// type 0 = normal red, type 1 = power up yellow, type 2 = blue can fire back!
				if (enemies.enemiesShip.get(e).ship.intersects(player.ship) && (enemies.enemiesShip.get(e).type != 1 || enemies.enemiesShip.get(e).type != 3)) {
					player.processDammage(enemies.enemiesShip.get(e).getShipCollisionDammage());
					enemies.enemiesShip.get(e).collideWithPlayer = true;
					enemies.enemiesShip.get(e).deathFromPlayer = true;
					enemies.enemiesShip.get(e).needsToRemove = true;
				}
				else if(enemies.enemiesShip.get(e).ship.intersects(player.ship) && (enemies.enemiesShip.get(e).type == 1 || enemies.enemiesShip.get(e).type == 3)) {
					player.proccessPowerup(player, enemies.enemiesShip.get(e).type);
					enemies.enemiesShip.get(e).collideWithPlayer = true;
					enemies.enemiesShip.get(e).deathFromPlayer = true;
					enemies.enemiesShip.get(e).needsToRemove = true;
				}
			}
		}
	}
	
	// process collisions.
	public void processCollisions(BulletHandler bullets, EnemyHandler enemies, Ship player, HUD hud) {
		// remove any ships or bullets
		//not to sure why this is spastic and i cant put it in the above collisions
		for (int s = 0; s < enemies.getAmmountOfEnemies(); s++) {
			if (enemies.enemiesShip.get(s).needsToRemove && enemies.enemiesShip.get(s).type == 0) {
				if (enemies.enemiesShip.get(s).deathFromPlayer == true) {
					hud.increasePoints();
				}
				enemies.enemiesShip.remove(s);
			}
			else if (enemies.enemiesShip.get(s).needsToRemove && (enemies.enemiesShip.get(s).type == 1 || enemies.enemiesShip.get(s).type == 3)) {
				if (enemies.enemiesShip.get(s).collideWithPlayer == true) {
					hud.displayPowerUpRecived(enemies.enemiesShip.get(s).type);
				}
				enemies.enemiesShip.remove(s);
			}
			else if (enemies.enemiesShip.get(s).needsToRemove && enemies.enemiesShip.get(s).type == 2) {
				if (enemies.enemiesShip.get(s).deathFromPlayer == true) {
					hud.increasePoints();
					hud.increasePoints();
				}
				enemies.enemiesShip.remove(s);
			}
		}
		for (int b = 0; b < bullets.getAmmountOfBullets(); b++) {
			if (bullets.bullets.get(b).needsToRemove) {
				bullets.bullets.remove(b);
			}
		}
		
		// ooh dear, player has died. Blegh. 
		if (player.needsToRemove) {
			hud.needToDisplayDeathMessage();
		}
	}

}
