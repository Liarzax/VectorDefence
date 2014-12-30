package com.au.Stark.VectorDefence.entity;


import com.au.Stark.VectorDefence.MainGame;
import com.au.Stark.VectorDefence.systems.TextureManager;
import com.au.Stark.VectorDefence.systems.camera.OrthoCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
	
	//private final OrthoCamera camera;
	boolean hasJumped = false, canJump = false;
	boolean isFalling = false, canFall = false;;
	boolean isAttacking = false, canAttack = false;
	
	
	
	/*public Player(Vector2 pos, OrthoCamera camera) {
		super(TextureManager.PLAYER, pos);
		this.camera = camera;
	}*/
	public Player(Vector2 pos) {
		super(TextureManager.PLAYER, pos);
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		// state to jump, fall, moveRight, attack.
		// run to jump/attack/fall | attack to run/jump/fall, jump to run/attack/fall
		// will need check to see if jumped, if falling.
		
		
	}
	
	public void render() {
		
	}
	
}
