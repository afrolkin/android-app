package net.obviam.gravswipe.model;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// Blocks made for creating the floor/ceiling
public class RestartButton {

	public static final float SIZE = 1f; // 3 unit

	Vector2 	position = new Vector2();
	Rectangle 	bounds = new Rectangle();

	public RestartButton(Vector2 pos) {
		this.position = pos;
		this.bounds.width = 9f;
		this.bounds.height = SIZE;
	}
	
	public Rectangle getBounds(){
		return bounds; 
	}
	
	public Vector2 getPosition(){
		return position; 
	}
	
	// temp method
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
}