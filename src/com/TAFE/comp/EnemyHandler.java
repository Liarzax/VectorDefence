package com.TAFE.comp;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class EnemyHandler {
	
	// randomise this to spawn random enemies?
	//private Vector2f eSpawnPoint;
	public List<Ship> enemiesShip;
	// powerup chance = 10%, PoweredEnemy chance = 45%
	private int powerUpChance = 90;
	private int poweredEnemyChance = 65;
	
	// handle spawn timing.
	private int timerTillSpawn = 0;
	private int timerCur = 0;
	private int minTimer = 10, maxTimer = 400;
	
	// use this to slowly make harder?
	private int totalEnemiesSpawned = 0;
	private int difficulty = 0;
	
	// dif enemy healths
	private int hpStrong = 4;
	private int hpStandard = 2;
	
	public void EnemyHandler() {
		
	}
	
	public void initEnemyHandler(int screenWidth, int screenHeight) {
		//eSpawnPoint = new Vector2f(screenWidth - 1, screenHeight/2);
		enemiesShip = new ArrayList<Ship>();
		
	}
	
	public void updateEnemyHandler() {
		// this is for the difficulty, its a bit spastic right now.
		if (totalEnemiesSpawned > minTimer) {
			
			totalEnemiesSpawned = 0;
			maxTimer -= 15;
			
			if (maxTimer < minTimer) {
				maxTimer = minTimer;
				System.out.println("Difficulty Maxed!");
			}
			else {
				System.out.println("Difficulty Increase!");
				difficulty++;
			}
			// should i reset the enemies spawned?
		}
		
		//eSpawnPoint / width = 800 /height = 400
		if(timerCur > timerTillSpawn) {
			int ammountToSpawn = generateRandomNumber(2, 4);
			ammountToSpawn += difficulty;
			spawnEnemyCluster(800, ammountToSpawn);
			timerCur = 0;
			timerTillSpawn = generateRandomNumber(minTimer, maxTimer);
		}
		else {
			// tick tock.
			timerCur++;
		}
		
	}

	public void updateEnemies(int delta, BulletHandler bullets) {
		if (!enemiesShip.isEmpty()) {
			for (int i = 0; i < enemiesShip.size(); i++) {
				enemiesShip.get(i).handleShipLeft(delta);
				enemiesShip.get(i).setShipNewPosition();
				if (enemiesShip.get(i).type == 2) {
					// 0 = bullet type player, 1 = bullet type enemy (maby enemy bullets should be 2 if type 2 enemies fire, confusion!)
					// check if enemy can shoot, then shoot.
					// maby put a rand number in here to make it try and fire every now and then
					if(enemiesShip.get(i).getFireCooldown() < enemiesShip.get(i).getFireRate()) {
						enemiesShip.get(i).setFireCooldown(enemiesShip.get(i).getFireCooldown() + 1);
					}
					else {
						// 0 = bullet type player, 1 = bullet type enemy
						bullets.createNewBullet(enemiesShip.get(i).posCur.x, enemiesShip.get(i).posCur.y, enemiesShip.get(i).getDammage(), 1);
						enemiesShip.get(i).setFireCooldown(0);
					}
					
				}
			}
		}
	}
	
	public void renderEnemies(Graphics g, Color color) {
		if (!enemiesShip.isEmpty()) {
			for (int i = 0; i < enemiesShip.size(); i++) {
				// if type 1, means ur a powerup not an enemy, lala.. so be yellow!
				// type 2 = blue can fire back!
				if(enemiesShip.get(i).type == 1) {
					enemiesShip.get(i).renderShip(g, Color.yellow);
				}
				else if (enemiesShip.get(i).type == 2) {
					enemiesShip.get(i).renderShip(g, Color.blue);
				}
				else {
					enemiesShip.get(i).renderShip(g, color);
				}
				
			}
		}
	}
	
	public void spawnEnemy(float spawnX) {
		Ship enemy = new Ship();
		int eSpawnPointY = generateRandomNumber(1, 400);
		enemy.initShip(spawnX, eSpawnPointY, 10, 10);
		enemiesShip.add(enemy);
		totalEnemiesSpawned++;
	}
	
	private void spawnEnemyCluster(float spawnX, int ammountToSpawn) {
		// timer till spawn, so not all at once.
		int randX = generateRandomNumber((int)spawnX-10,  (int)spawnX+10);
		for (int i = 0; i < ammountToSpawn; i++) {
			Ship enemy = new Ship();
			int possiblePowerup = generateRandomNumber(1,  100);
			if(possiblePowerup > powerUpChance) {
				// type 1 is powerup!
				enemy.type = 1;
			}
			else if(possiblePowerup > poweredEnemyChance) {
				// type 2 can fire at the player!
				enemy.type = 2;
				enemy.setFireRate((80 - difficulty));
				enemy.setShipHealth((hpStrong + difficulty));
			}
			else {
				// type 0 is standard enemy
				enemy.type = 0;
				enemy.setShipHealth((hpStandard + difficulty));
			}
			int eSpawnPointY = generateRandomNumber(1, 400);
			enemy.initShip(randX, eSpawnPointY, 10, 10);
			enemiesShip.add(enemy);
			totalEnemiesSpawned++;
			
			randX = generateRandomNumber((int)spawnX-10,  (int)spawnX+10);
		}
		
		// should have a check to see if the enemies are spawned on-top of each-other, if so move them
	}
	
	// this can act as spawn point and timer, lala
	private int generateRandomNumber(int min, int max) {
		int num = (int) ((Math.random() * (max - min)) + min); 
		return num;
	}

	public int getAmmountOfEnemies() {
		return enemiesShip.size();
	}
	
}
