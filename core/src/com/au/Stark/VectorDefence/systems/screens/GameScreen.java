package com.au.Stark.VectorDefence.systems.screens;

/*import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;*/

import com.au.Stark.VectorDefence.BackgroundHandler;
import com.au.Stark.VectorDefence.BulletHandler;
import com.au.Stark.VectorDefence.CollisionHandler;
import com.au.Stark.VectorDefence.EnemyHandler;
import com.au.Stark.VectorDefence.HUD;
import com.au.Stark.VectorDefence.InstructionsLoop;
import com.au.Stark.VectorDefence.MainGame;
import com.au.Stark.VectorDefence.Ship;
import com.au.Stark.VectorDefence.entity.Player;
import com.au.Stark.VectorDefence.systems.TextureManager;
import com.au.Stark.VectorDefence.systems.camera.OrthoCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends Screen {
	
	// misc
	private OrthoCamera camera = new OrthoCamera();
	private InstructionsLoop instructionsScreen = new InstructionsLoop();
	
	private Ship 			  player 	 = new Ship();
	private BulletHandler 	  bullets 	 = new BulletHandler();
	private EnemyHandler 	  enemies 	 = new EnemyHandler();
	private CollisionHandler  collisions = new CollisionHandler();
	private HUD 			  hud 		 = new HUD();
	private BackgroundHandler background = new BackgroundHandler();
	
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	// for delta multiploier
	// (Gdx.graphics.getDeltaTimer() * deltaSpeed)
	//final int deltaSpeed = 100;
	

	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		//System.out.println("Created");
		//camera = new OrthoCamera();
		camera.resize();
		//entityManager = new EntityManager(10, camera);
		//player = new Player(new Vector2(240, 15), camera);
		//lButtonRec = new Rectangle(15, 15, TextureManager.BUTTON.getWidth(), TextureManager.BUTTON.getHeight());
		//rButtonRec = new Rectangle((MainGame.WIDTH-15) - TextureManager.BUTTON.getWidth(), 15, TextureManager.BUTTON.getWidth(), TextureManager.BUTTON.getHeight());
		
		// init the background
		background.initBackgroundHandler();
		
		//temp x, y, width, height (25/10 is small).
		player.initShip(115, 200, 25, 10);
		// force player to have 5 hp
		player.setShipHealth(5);
		// force player to be a bit quicker
		player.increaseMaxSpeed(0.05f);
		// force player shield to activate
		player.shieldSystem.initShieldSystem(player.posCur.x, player.posCur.y);
		bullets.initBulletHandler();
		// force player to have higher/better weapons
			// weapon initialized to = 0 | need 1 or more to fire a bullet.
		player.setWeaponLevel(1);
		
		// temp enemy spawn // height 400, width 800.
		enemies.initEnemyHandler(800, 400);
		//enemies.spawnEnemy(WIDTH-1, 25);
		
		collisions.initCollisionHandler();
		hud.initHUD();
		
		// attempt to load particle system? and fail misserable, CUT.
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//System.out.println("Update");
		camera.update();
		//entityManager.update();
		
		// Process Input (Touch/Keyboard).
		/*int buttonPressed = 0;
		// Register Touch
		if (Gdx.input.isTouched()) {
			Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
			//Touch Buttons Pressed
			if (lButtonRec.contains(touch.x, touch.y)){
				System.out.println("Tapping Left'N'Shit");
				buttonPressed = 1;
			}
			if (rButtonRec.contains(touch.x, touch.y)){
				System.out.println("Tapping Right'N'Shit");
				buttonPressed = 2;
			}
			
		}*/
		
		// Register PC Buttons Pressed & Process Touch Presses
		/*if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		if (Gdx.input.isKeyPressed(Keys.A) || buttonPressed == 1) {
			System.out.println("Left Button Activated");
			
		}
		else if (Gdx.input.isKeyPressed(Keys.D) || buttonPressed == 2) {
			System.out.println("Right Button Activated");
			
		}
		// Nothing Pressed.
		else {		}*/
		
		//Input input = container.getInput(); 
		//Input input = Gdx.input.isKeyPressed()
		
		if (instructionsScreen.canContinue) {
			//updateMainGame((Gdx.graphics.getFramesPerSecond() * deltaSpeed));
			updateMainGame(Gdx.graphics.getFramesPerSecond());
		}
		else {
			//updateInstructions((Gdx.graphics.getFramesPerSecond() * deltaSpeed));
			updateInstructions(Gdx.graphics.getFramesPerSecond());
		}
		
		
		
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		//System.out.println("Render");
		//camera.update();
		//shapeRenderer.setProjectionMatrix(camera.combined);
		
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		//entityManager.render(sb);
		
		//camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		//shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.begin(ShapeType.Line);
		
		if (instructionsScreen.canContinue) {
			renderMainGame(sb, shapeRenderer);
		}
		else {
			renderInstructions(sb, shapeRenderer);
		}
		
		shapeRenderer.end();
		
		// render button
		//sb.draw(lButtonTexture, lButtonX, lButtonY);
		//sb.draw(rButtonTexture, rButtonX, rButtonY);
		sb.end();
		
		
		
		
	}

	@Override
	public void reseize(int width, int height) {
		// TODO Auto-generated method stub
		//System.out.println("Resize");
		camera.resize();
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	// Other Stuff
	public void updateInstructions(int delta) {
		
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			//container.exit();
			System.exit(0);
		
		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.ENTER))
			instructionsScreen.canContinue = true;
	}
	
	public void updateMainGame(int delta) {
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			//container.exit();
			System.exit(0);
		
		// Process Input (Touch/Keyboard).
		int buttonPressed = 0;
		// Register Touch
		if (Gdx.input.isTouched()) {
			Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
			//Touch Buttons Pressed
			if(touch.x < MainGame.WIDTH / 2) {
				System.out.println("Tapping");
				if(touch.y > (MainGame.HEIGHT / 2) + 80) {
					System.out.println("Top");
					buttonPressed = 1;
				}
				if(touch.y > (MainGame.HEIGHT / 2) - 80 && touch.y < (MainGame.HEIGHT / 2) + 80) {
					System.out.println("Middle");
					buttonPressed = 2;
				}
				if(touch.y < (MainGame.HEIGHT / 2) - 80) {
					System.out.println("Bottom");
					buttonPressed = 3;
				}
				System.out.println("Left'N'Shit");
			}
			else {
				System.out.println("Tapping");
				if(touch.y > (MainGame.HEIGHT / 2) + 80) {
					System.out.println("Top");
					buttonPressed = 4;
				}
				if(touch.y > (MainGame.HEIGHT / 2) - 80 && touch.y < (MainGame.HEIGHT / 2) + 80) {
					System.out.println("Middle");
					buttonPressed = 5;
				}
				if(touch.y < (MainGame.HEIGHT / 2) - 80) {
					System.out.println("Bottom");
					buttonPressed = 6;
				}
				System.out.println("Right'N'Shit");
			}
				
		}
		
		// Register PC Buttons Pressed & Process Touch Presses
		/*if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		if (Gdx.input.isKeyPressed(Keys.A) || buttonPressed == 1) {
			System.out.println("Left Button Activated");
			
		}
		else if (Gdx.input.isKeyPressed(Keys.D) || buttonPressed == 2) {
			System.out.println("Right Button Activated");
			
		}
		// Nothing Pressed.
		else {		}*/
		
		// if not dead.
		if(!player.needsToRemove) {
			// process input.
			//if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)){
			if ((Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) || (buttonPressed == 3 || buttonPressed == 6)){
				player.handleShipUp(delta);
				if (player.overDriveActive) {
					player.handleShipUp(delta);
				}
			}
			//if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			if ((Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) || (buttonPressed == 1 || buttonPressed == 4)){
				player.handleShipDown(delta);
				if (player.overDriveActive) {
					player.handleShipDown(delta);
				}
			}
			if ((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) || (buttonPressed == 1 || buttonPressed == 2 || buttonPressed == 3)){
				player.handleShipLeft(delta);
				if (player.overDriveActive) {
					player.handleShipLeft(delta);
				}
			}
			if ((Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) || (buttonPressed == 4 || buttonPressed == 5 || buttonPressed == 6)){
				player.handleShipRight(delta);
				if (player.overDriveActive) {
					player.handleShipRight(delta);
				}
			}
			if (Gdx.input.isKeyPressed(Keys.SPACE) && (player.overdriveCooldownCur >= player.overdriveCooldownMax)) {
				player.overDriveActive = true;
			}
			
			
			// Player Cooldowns
			if (player.overDriveActive) {
				player.overdriveDuration++;
				if (player.overdriveDuration > player.overdriveDurationMax) {
					player.overDriveActive = false;
					player.overdriveDuration = 0;
					player.overdriveCooldownCur = 0;
				}
			}
			if (player.overdriveCooldownCur < player.overdriveCooldownMax) {
				player.overdriveCooldownCur++;
			}
			if (player.getFireCooldown() <= player.getFireRate()) {
				player.setFireCooldown(player.getFireCooldown() + 1);
			}
			
			// FIRE SON!
			if (player.getFireCooldown() >= player.getFireRate()) {
				if (player.getWeaponLevel() >= 1) {
					bullets.createNewBullet((player.getPosCur().x + 10), player.getPosCur().y, player.getDammage(), 0);
				}
				if (player.getWeaponLevel() >= 2) {
					bullets.createNewBullet((player.getPosCur().x + 10), (player.getPosCur().y + 5), player.getDammage(), 0);
				}
				if (player.getWeaponLevel() >= 3) {
					bullets.createNewBullet((player.getPosCur().x + 10), (player.getPosCur().y - 5), player.getDammage(), 0);
				}
				
				player.setFireCooldown(0);
			}
			
			// template for cooldoown stuff
			/*if (player.getFireCooldown() <= player.getFireRate()) {
				player.setFireCooldown(player.getFireCooldown() + 1);
			}
			// FIRE SON!
			if (input.isKeyPressed(Keyboard.KEY_SPACE) && (player.getFireCooldown() >= player.getFireRate())) {
								
			}*/
			
		}
		
		background.updateBackgroundHandler(bullets, enemies, player);
		
		player.updateShip();
		enemies.updateEnemies(delta, bullets);
		bullets.updateBullets(delta);
		
		// spawn random enemy
		enemies.updateEnemyHandler();
		
		// collision stuff
		collisions.checkForCollisions(bullets, enemies);
		collisions.checkForCollisions(bullets, player);
		collisions.checkForCollisions(enemies, player);
		
		collisions.processCollisions(bullets, enemies, player, hud);
		
		hud.updateHUD();
	}
	
	public void renderInstructions(SpriteBatch sb, ShapeRenderer shapeRenderer) {
		instructionsScreen.renderInstructions(sb, shapeRenderer);
	}
	
	public void renderMainGame(SpriteBatch sb, ShapeRenderer shapeRenderer) {
		background.renderBackgroundHandler(shapeRenderer);
		
		player.renderShip(shapeRenderer, Color.GREEN);
		
		enemies.renderEnemies(shapeRenderer, Color.RED);
		bullets.renderBullets(shapeRenderer, Color.WHITE);
		hud.renderHUD(sb, shapeRenderer, Color.WHITE, player);
		
		// debugging stuff
		//g.drawString("Debugging =" + enemies.getTotalEnemiesSpawned(), 1, 185);
		
	}

}
