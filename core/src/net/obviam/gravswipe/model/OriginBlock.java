package net.obviam.gravswipe.model;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// this block is used to determine when to ctreate a new level, it is invisible to the player, used only in rndomizing levels
public class OriginBlock {

	public static final float SIZE = 1f; // 1 unit

	public static final float SPEED = 2f;	// unit per second

	Vector2 	acceleration = new Vector2();
	Vector2 	velocity = new Vector2();
	Vector2 	position = new Vector2();
	Rectangle 	bounds = new Rectangle();

	public OriginBlock(Vector2 pos) {
		this.position = pos;
		this.bounds.width = SIZE;
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
	
	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));
	}
	
	public void updatePos(float delta) {
		position.add(velocity.cpy().scl(delta));
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public Vector2 getVelocity() {
		return velocity;
	}
}
