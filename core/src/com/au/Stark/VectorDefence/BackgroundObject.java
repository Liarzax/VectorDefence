package com.au.Stark.VectorDefence;

/*import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;*/

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class BackgroundObject {
	
	Vector2 posCur = new Vector2(0,0),
			 posNext = new Vector2(0,0);
	
	Circle star = null;
	private float starSize = 24f;
	Color starColor = new Color(Color.WHITE);
	
	public boolean isVisible = true;
	
	
	public BackgroundObject() {
		
	}
	
	public void createBackgroundObject(float x, float y, float size) {
		this.posCur = new Vector2(x, y);
		this.posNext = posCur;
		
		this.starSize = size;
		Circle star = new Circle(posCur.x, posCur.y, starSize);
		
		this.isVisible = true;
	}
	
	public void updateBackgroundObject() {
		
	}
	
	public void renderBackgroundObject() {
		
	}
	
}
