package com.TAFE.comp;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class Main extends BasicGame{
	
	final static int majorVersion = 0, minorVersion = 1, bugfix = 4, buildRev = 7;
	final static String devStage = "Alpha";
	final static String version = "v"+majorVersion+"."+minorVersion+"."+bugfix+"-"+devStage+"   build."+buildRev;
	final static String title = "Vector Defense "+version;
	
	public static final int  WIDTH = 800, PLAYWIDTHMAX = 800, PLAYWIDTHMIN = 100, OFFSCREENWIDTHBUFFER = 815,
							 HEIGHT = 440, PLAYHEIGHT = 400, OFFSCREENHEIGHTBUFFER = 455,
							 FPS_LIMIT = 60;
	
	public static boolean  showFPS = true,
						   fullscreen = false,
						   debug = true;
	
	private InstructionsLoop instructionsScreen = new InstructionsLoop();
	
	private Ship 			 player 	= new Ship();
	private BulletHandler 	 bullets 	= new BulletHandler();
	private EnemyHandler 	 enemies 	= new EnemyHandler();
	private CollisionHandler collisions = new CollisionHandler();
	private HUD 			 hud 		= new HUD();
	
	public Main(String title){
		super(title);
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Main(title));
		app.setDisplayMode(WIDTH,  HEIGHT,  fullscreen);
		app.setSmoothDeltas(true);
		app.setTargetFrameRate(FPS_LIMIT);
		app.setShowFPS(showFPS);
		app.start();
		
	}

	public void init(GameContainer container) throws SlickException {
		
		
		//temp x, y, width, height (25/10 is small).
		player.initShip(115, 200, 25, 10);
		// force player to have 5 hp
		player.setShipHealth(5);
		// force player to be a bit quicker
		player.increaseMaxSpeed(0.05f);
		// force player shield to activate
		player.shieldSystem.initShieldSystem(player.posCur.x, player.posCur.y);
		bullets.initBulletHandler();
		
		// temp enemy spawn // height 400, width 800.
		enemies.initEnemyHandler(800, 400);
		//enemies.spawnEnemy(WIDTH-1, 25);
		
		collisions.initCollisionHandler();
		hud.initHUD();
		
		// attempt to load particle system? and fail misserable, CUT.
	}

	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput(); 
		
		if (instructionsScreen.canContinue) {
			updateMainGame(container, delta, input);
		}
		else {
			updateInstructions(container, delta, input);
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (instructionsScreen.canContinue) {
			renderMainGame(g);
		}
		else {
			renderInstructions(g);
		}

	}
	
	public void updateInstructions(GameContainer container, int delta, Input input) {
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE))
			container.exit();
		
		if (input.isKeyPressed(Keyboard.KEY_RETURN))
			instructionsScreen.canContinue = true;
	}
	
	public void updateMainGame(GameContainer container, int delta, Input input) {
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE))
			container.exit();
		
		// if not dead.
		if(!player.needsToRemove) {
			// process input.
			if (input.isKeyDown(Keyboard.KEY_W) || input.isKeyDown(Keyboard.KEY_UP)){
				player.handleShipUp(delta);
			}
			if (input.isKeyDown(Keyboard.KEY_S) || input.isKeyDown(Keyboard.KEY_DOWN)){
				player.handleShipDown(delta);
			}
			if (input.isKeyDown(Keyboard.KEY_A) || input.isKeyDown(Keyboard.KEY_LEFT)){
				player.handleShipLeft(delta);
			}
			if (input.isKeyDown(Keyboard.KEY_D) || input.isKeyDown(Keyboard.KEY_RIGHT)){
				player.handleShipRight(delta);
			}
			
			// FIRE SON!
			if (input.isKeyPressed(Keyboard.KEY_SPACE) || input.isKeyDown(Keyboard.KEY_SPACE)) {
				if(player.getFireCooldown() < player.getFireRate()) {
					player.setFireCooldown(player.getFireCooldown() + 1);
				}
				else {
					// 0 = bullet type player, 1 = bullet type enemy
					bullets.createNewBullet((player.getPosCur().x + 10), player.getPosCur().y, player.getDammage(), 0);
					player.setFireCooldown(0);
				}
			}
		}
		
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
	
	public void renderInstructions(Graphics g) {
		instructionsScreen.renderInstructions(g);
	}
	
	public void renderMainGame(Graphics g) {
		if (!player.needsToRemove) {
			player.renderShip(g, Color.green);
		}
		else {
			player.renderShip(g, Color.black);
		}
		
		enemies.renderEnemies(g, Color.red);
		bullets.renderBullets(g, Color.white);
		hud.renderHUD(g, Color.white, player);
	}
}
