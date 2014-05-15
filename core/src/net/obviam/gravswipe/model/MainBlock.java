package net.obviam.gravswipe.model;

import net.obviam.gravswipe.screens.GameScreen;
import net.obviam.gravswipe.screens.GameScreen.Type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// Main player controlled block
public class MainBlock {

	public enum State {
		IDLE, SHIFTING, JUMPING, DYING, FALLING
// falling refers to falling off of the orange squares
	}

	public static final float SPEED = 2f;	// unit per second
	static final float JUMP_VELOCITY = 1f;
	public static final float SIZE = 1f; // half a unit

	Vector2 	position = new Vector2();
	Vector2 	acceleration = new Vector2();
	Vector2 	velocity = new Vector2();
	Rectangle 	bounds = new Rectangle();
	State		state = State.IDLE;
	float 		stateTime = 0;
	int 		score = 0;


	public MainBlock(Vector2 position) {
		this.position = position;
//		this.position. rotate(30);
		this.bounds.height = SIZE; // for collision detection
		this.bounds.width = SIZE;
	}
	
	public Rectangle getBounds(){
		return bounds; 
	}
	 
	public Vector2 getPosition(){
		return position; 
	}
	
	public void setState(State newState) {
		this.state = newState;
	}
	
	public int getScore(){
		return score; 
	}
	
	public void setScore(int newscore) {
		this.score = newscore;
	}
	
	// temp method
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void update(float delta) {
        if (state == State.JUMPING || state == State.DYING || (GameScreen.getType().equals(Type.STOPPED))){
        	stateTime += delta;
        }
        else{
        	stateTime = 0;
        }
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

	public State getState() {
		return state;
	}
}

