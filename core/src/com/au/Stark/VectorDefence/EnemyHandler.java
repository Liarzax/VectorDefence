package com.au.Stark.VectorDefence;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/*import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;*/

public class EnemyHandler {
	
	// randomise this to spawn random enemies?
	//private Vector2f eSpawnPoint;
	public List<Ship> enemiesShip;
	// powerup chance = 10%, PoweredEnemy chance = 45%
	private int poweredShieldChance = 90;
	private int powerUpChance = 80;
	private int poweredEnemyChance = 65;
	
	// handle spawn timing.
	private int timerTillSpawn = 0;
	private int timerCur = 0;
	private int minTimer = 10, maxTimer = 400;
	
	// use this to slowly make harder?
	private int totalEnemiesSpawned = 0;
	private int enemiesSpawnedSinceLast = 0;
	private int difficulty = 0;
	
	// dif enemy healths
	private int hpStrong = 4;
	private int hpStandard = 2;
	private int hpMiniBoss = 10;
	
	// boss stuff
	private boolean spawningBoss = false;
	private int bossDestroyedTimes = 0;
	private boolean isBossSpawned = false;
	
	/*private float redSpeed = 0.08f, blueSpeed = 0.12f, redBossSpeed = 0.04f, blueBossSpeed = 0.04f, 
			weponPowerUpSpeed = 0.10f, shieldPowerUpSpeed = 0.10f;*/
	private float redSpeed = 0.03f, blueSpeed = 0.04f, redBossSpeed = 0.02f, blueBossSpeed = 0.03f, 
			weponPowerUpSpeed = 0.02f, shieldPowerUpSpeed = 0.02f;
	
	
	
	// this is unused as of yet.
	// type 0 = normal red, type 1 = power up yellow, type 2 = blue can fire back!
	/*public enum shipType {
	    enemyRed(0), powerUpYellow(1), enemyBlue(2);

	    private int type;

	    shipType(int type) {
	        this.type = type;
	    }

	    public int getType() {
	        return type;
	    }
	}*/
	// usage: shipType enemy = shipType.enemyRed;
	
	
	
	public void EnemyHandler() {
		
	}
	
	public void initEnemyHandler(int screenWidth, int screenHeight) {
		//eSpawnPoint = new Vector2f(screenWidth - 1, screenHeight/2);
		enemiesShip = new ArrayList<Ship>();
		
	}
	
	public void updateEnemyHandler() {
		// this is for the difficulty, its a bit spastic right now.
		// the whole boss spawned thing is good, it stops them from spaning, maby use it when have giant bosses for end battles!
		//if (!isBossSpawned) {
			if (enemiesSpawnedSinceLast > minTimer) {
				
				enemiesSpawnedSinceLast = 0;
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
		//}
		
		if (totalEnemiesSpawned >= (10 * (bossDestroyedTimes+1))) {
			if (!isBossSpawned) {
				// TYPE 10 = RED BOSS | TYPE 11 = BLUE BOSS
				spawnBOSS(MainGame.WIDTH, 10);
			}
			// other stuff?
		}
		
		
	}

	public void updateEnemies(int delta, BulletHandler bullets) {
		if (!enemiesShip.isEmpty()) {
			for (int i = 0; i < enemiesShip.size(); i++) {
				// movement mechanics
				if (enemiesShip.get(i).isBossShip()) {
					//enemiesShip.get(i).handleShipLeft(delta);
					
					// calculate blue (blue = 1) boss bullets
					if (enemiesShip.get(i).bossType == 2) { 
						if(enemiesShip.get(i).getFireCooldown() < enemiesShip.get(i).getFireRate()) {
							enemiesShip.get(i).setFireCooldown(enemiesShip.get(i).getFireCooldown() + 1);
						}
						else {
							// 0 = bullet type player, 1 = bullet type enemy
							if (difficulty >= 0) {
								bullets.createNewBullet(enemiesShip.get(i).posCur.x - 6, enemiesShip.get(i).posCur.y, enemiesShip.get(i).getDammage(), 1);
								//enemiesShip.get(i).setFireCooldown(0);
							}
							if (difficulty >= 4) {
								bullets.createNewBullet(enemiesShip.get(i).posCur.x - 6, enemiesShip.get(i).posCur.y - 4, enemiesShip.get(i).getDammage(), 1);
								//bullets.createNewBullet(enemiesShip.get(i).posCur.x - 1, enemiesShip.get(i).posCur.y + 2, enemiesShip.get(i).getDammage(), 1);
								//enemiesShip.get(i).setFireCooldown(0);
							}
							if (difficulty >= 8) {
								bullets.createNewBullet(enemiesShip.get(i).posCur.x - 6, enemiesShip.get(i).posCur.y + 4, enemiesShip.get(i).getDammage(), 1);
								//enemiesShip.get(i).setFireCooldown(0);
							}
							
							enemiesShip.get(i).setFireCooldown(0);
						}			
					}
					
				}
				
				enemiesShip.get(i).handleShipLeft(delta);
				
				enemiesShip.get(i).setShipNewPosition();
				if (enemiesShip.get(i).shieldSystem != null) {
					if(enemiesShip.get(i).shieldSystem.isShieldEnabled()) {
						enemiesShip.get(i).shieldSystem.updateShieldSystem(enemiesShip.get(i).posCur.x, enemiesShip.get(i).posCur.y);
					}
				}
				
				// If enemy ship is outside render range (to far to the left), remove ship of screen.
				if (enemiesShip.get(i).getPosCurX() < MainGame.PLAYWIDTHMIN) {
					enemiesShip.get(i).needsToRemove = true;
					//break;
				}
				
				// shooting mechanics
				if (enemiesShip.get(i).type == 2) {
					// 0 = bullet type player, 1 = bullet type enemy (maby enemy bullets should be 2 if type 2 enemies fire, confusion!)
					// check if enemy can shoot, then shoot.
					// maby put a rand number in here to make it try and fire every now and then
					if(enemiesShip.get(i).getFireCooldown() < enemiesShip.get(i).getFireRate()) {
						enemiesShip.get(i).setFireCooldown(enemiesShip.get(i).getFireCooldown() + 1);
					}
					else {
						// 0 = bullet type player, 1 = bullet type enemy
						bullets.createNewBullet(enemiesShip.get(i).posCur.x - 1, enemiesShip.get(i).posCur.y, enemiesShip.get(i).getDammage(), 1);
						enemiesShip.get(i).setFireCooldown(0);
					}
					
				}
			}
		}
	}
	
	public void renderEnemies(ShapeRenderer shapeRenderer, Color color) {
		if (!enemiesShip.isEmpty()) {
			for (int i = 0; i < enemiesShip.size(); i++) {
				// if type 1, means ur a powerup not an enemy, lala.. so be yellow!
				// type 2 = blue can fire back!
				if(enemiesShip.get(i).type == 1) {
					enemiesShip.get(i).renderShip(shapeRenderer, Color.YELLOW);
				}
				else if (enemiesShip.get(i).type == 2) {
					enemiesShip.get(i).renderShip(shapeRenderer, Color.BLUE);
				}
				else if (enemiesShip.get(i).type == 3) {
					enemiesShip.get(i).renderShip(shapeRenderer, Color.CYAN);
				}
				else if (enemiesShip.get(i).type == 10) {
					if (enemiesShip.get(i).bossType == 1) {
						enemiesShip.get(i).renderShip(shapeRenderer, Color.RED);
					}
					else if (enemiesShip.get(i).bossType == 2) {
						enemiesShip.get(i).renderShip(shapeRenderer, Color.BLUE);
					}
					
				}
				else {
					enemiesShip.get(i).renderShip(shapeRenderer, color);
				}
				
			}
		}
	}
	
	// TYPE 10 = RED BOSS | TYPE 11 = BLUE BOSS
	public void spawnBOSS(float spawnX, int type) {
		// temp vriables
		Vector2 tempSize = new Vector2(20,20);
		
		Ship enemy = new Ship();
		enemy.type = 10;
		enemy.setShipHealth(hpMiniBoss * (difficulty + 1));
		enemy.setMaxSpeed(this.redBossSpeed);
		tempSize = new Vector2(20,20);
		
		System.out.println("Spawning BOSS enemy");
		
		int eSpawnPointY = generateRandomNumber(1, MainGame.PLAYHEIGHT);
		int randX = generateRandomNumber((int)spawnX-5,  (int)spawnX+5);
		
		enemy.initShip(randX, eSpawnPointY, tempSize.x, tempSize.y);
		enemy.shieldSystem.initShieldSystem(randX, eSpawnPointY);
		// if red1 boss do this, else if blue2 boss continue
		int tempBossType = generateRandomNumber(1, 3);
		//int tempBossType = 2;
		if (tempBossType == 1) {
			enemy.shieldSystem.setShieldMax(enemy.shieldSystem.getShieldMax() * (difficulty + 1));
			enemy.shieldSystem.setShieldCur(enemy.shieldSystem.getShieldMax());
			enemy.bossType = 1;
		}
		else if (tempBossType == 2) {
			enemy.setFireRate((80 - difficulty));
			enemy.bossType = 2;
		}
		enemy.shieldSystem.shield.setRadius(48);
		enemy.setBossShip(true);
		enemiesShip.add(enemy);
		
		enemiesSpawnedSinceLast++;
		totalEnemiesSpawned++;
		isBossSpawned = true;
	}
	
	private void spawnEnemyCluster(float spawnX, int ammountToSpawn) {
		// timer till spawn, so not all at once.
		int randX = generateRandomNumber((int)spawnX-5,  (int)spawnX+5);
		
		// temp vriables
		Vector2 tempSize = new Vector2(10,10);
		
		// start spawning
		for (int i = 0; i < ammountToSpawn; i++) {
			Ship enemy = new Ship();
			int possiblePowerup = generateRandomNumber(1,  100);
			// new shield powerup spawn!
			if(possiblePowerup > poweredShieldChance) {
				// type 3 is shield regen powerup!
				enemy.type = 3;
				tempSize = new Vector2(8,8);
				enemy.setMaxSpeed(this.shieldPowerUpSpeed);
				// set state to powerup
				System.out.println("Spawning Shield Powerup!");
			}
			else if(possiblePowerup > powerUpChance) {
				// type 1 is powerup!
				enemy.type = 1;
				tempSize = new Vector2(12,12);
				enemy.setMaxSpeed(this.weponPowerUpSpeed);
				// set state to powerup
				System.out.println("Spawning Weapon Powerup!");
			}
			else if(possiblePowerup > poweredEnemyChance) {
				// type 2 can fire at the player!
				enemy.type = 2;
				enemy.setFireRate((80 - difficulty));
				enemy.setShipHealth((hpStrong + difficulty));
				tempSize = new Vector2(14,10);
				// set state to blue
				//System.out.println("Spawning Blue enemy!");
				enemy.setMaxSpeed(this.blueSpeed);
			}
			else {
				// type 0 is standard enemy
				enemy.type = 0;
				enemy.setShipHealth((hpStandard + difficulty));
				tempSize = new Vector2(10,10);
				// set state to red
				//System.out.println("Spawning red enemy");
				enemy.setMaxSpeed(this.redSpeed);
			}
			int eSpawnPointY = generateRandomNumber(1, MainGame.PLAYHEIGHT);
			enemy.initShip(randX, eSpawnPointY, tempSize.x, tempSize.y);
			enemiesShip.add(enemy);
			enemiesSpawnedSinceLast++;
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

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getTotalEnemiesSpawned() {
		return totalEnemiesSpawned;
	}

	public boolean isBossSpawned() {
		return isBossSpawned;
	}

	public void setBossSpawned(boolean isBossSpawned) {
		this.isBossSpawned = isBossSpawned;
	}

	public int getBossDestroyedTimes() {
		return bossDestroyedTimes;
	}

	public void setBossDestroyedTimes(int bossDestroyedTimes) {
		this.bossDestroyedTimes = bossDestroyedTimes;
	}
	
	
}
