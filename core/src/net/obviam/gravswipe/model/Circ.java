package net.obviam.gravswipe.model;


import net.obviam.gravswipe.model.MainBlock.State;
import net.obviam.gravswipe.screens.GameScreen;
import net.obviam.gravswipe.screens.GameScreen.Type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// Blocks made for creating the purple pits
public class Circ {

	public static final float SIZE = 0.5f; // 1 unit

	public static final float SPEED = 2f;	// unit per second
	static final float JUMP_VELOCITY = 1f;
	public boolean collected = false;
	public boolean animationDone = false;
	public boolean pointsAdded = false;

	float 		stateTime = 0;


	Vector2 	acceleration = new Vector2();
	Vector2 	velocity = new Vector2();
	Vector2 	position = new Vector2();
	Rectangle 	bounds = new Rectangle();

	public Circ(Vector2 pos) {
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
        if (collected){
        	stateTime += delta;
        }
        else{
        	stateTime = 0;
        }
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
	
	public float getStateTime() {
		return stateTime;
	}
	
	public void setStateTime(float a) {
		stateTime = a;
	}
}
