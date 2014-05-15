package net.obviam.gravswipe.controller;
//cache elements like verticies of all the things
// rename images, rename classes, refactor this entire file to contain obly controller things, no rendering
// make it so that you can jump on other boxes
// make mid air obstacles
// purple pits
// maybe change player block to a rounded square or something
// positive blocks, blocks that aid player - small powerups like temporary double jump, and "remove all blocks"  - use hexagon sprites, stars etc
// circles that move faster - implement these but make sure that they dont intersect with other objects
// make falling animation when entering purple pits
// fix collision detection with pits

import java.util.HashMap;
import java.util.Map;


import java.util.Random;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import net.obviam.gravswipe.view.WorldRenderer;
import net.obviam.gravswipe.screens.GameScreen;
import net.obviam.gravswipe.screens.GameScreen.Type;
import net.obviam.gravswipe.model.Block;
import net.obviam.gravswipe.model.Circ;
import net.obviam.gravswipe.model.MainBlock;
import net.obviam.gravswipe.model.MainBlock.State;
import net.obviam.gravswipe.model.Obstacle;
import net.obviam.gravswipe.model.OriginBlock;
import net.obviam.gravswipe.model.Powerup;
import net.obviam.gravswipe.model.Rect;
import net.obviam.gravswipe.model.Square;
import net.obviam.gravswipe.model.World;

public class WorldController {

	enum Keys {
		JUMP, SHIFTUP, SHIFTDOWN, PAUSE, RESTART
	}
	
	private static final float ACCELERATION 	= 85f;
	private static final float GRAVITY 			= -85f;
	private static final float MAX_JUMP_SPEED	= 20f;
	private static final float DAMP 			= 0.90f;
	private static final float MAX_VEL 			= 4f;
	private static final float SCROLL_SPEED 	= -6.5f;
	private static final float CIRC_SPEED 	= -10.5f;
	private float onBlockHeight = 2f;

	
	private static final float WIDTH = 16f;

	private World 	world;
	private MainBlock 	mainBlock;
	private Powerup 	powerup;
	private OriginBlock 	originBlock;


	private long jumpPressedTime;
	private boolean jumpingPressed;
	private boolean shiftUpPressed;
	private boolean shiftDownPressed;
	private boolean onSquare = false;
	private boolean newGame = true;

	
	// the value of this is a random number - this provides the random screens
	Random random = new Random();
	private int levelNum;
	
	private int numLevels;
	private int setNum;


	
	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.JUMP, false);
		keys.put(Keys.PAUSE, false);
		keys.put(Keys.RESTART, false);
	};

	public WorldController(World world) {
		this.world = world;
		this.mainBlock = world.getMainBlock();
		this.originBlock = world.getOriginBlock();
		this.powerup = world.getPowerup();
		this.numLevels = world.getNumLevels();
//		this.numLevels = 28;

		
	}

	// ** Key presses and touches **************** //

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}
	
	public void pausePressed() {
		keys.get(keys.put(Keys.PAUSE, true));
	}
	
	public void restartPressed() {
		newGame = false;
		keys.get(keys.put(Keys.RESTART, true));
	}
	
	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
		jumpingPressed = false;
	}

	/** The main update method **/
	
	public void deathAnimation(float delta) {
		mainBlock.getAcceleration().y = GRAVITY;
		mainBlock.getAcceleration().scl(delta);
		mainBlock.getVelocity().y = 0;
		mainBlock.setState(State.DYING);
		mainBlock.setPosition(mainBlock.getPosition());
		mainBlock.update(delta);


		
//		mainBlock.getAcceleration().y = GRAVITY;
//		mainBlock.getAcceleration().scl(delta);
//		mainBlock.getVelocity().add(mainBlock.getAcceleration().x, mainBlock.getAcceleration().y);
		mainBlock.update(delta);
	}
	
	public void update(float delta) {
		
//		if (newGame){
//			world.drawLevel(0, setNum); // draw blank level for first level
//			if (setNum == 2){
//				setNum = 1;
//			}
//			else{
//				setNum = 2;
//			}
//			newGame = false;
//		}
		
		if (originBlock.getPosition().x < 0){
			levelNum = random.nextInt(numLevels) + 1;
			world.drawLevel(levelNum, setNum);
//			world.drawLevel(numLevels, setNum);
//			world.drawLevel(20, setNum);

			if (setNum == 2){
				setNum = 1;
			}
			else{
				setNum = 2;
			}
//        	System.out.println("called");
		}
		
		mainBlock.setScore(mainBlock.getScore() + 1);
		
		// clean this method up move most of it to worldrenderer
		processInput();
		
		mainBlock.getAcceleration().y = GRAVITY;
		mainBlock.getAcceleration().scl(delta);
		mainBlock.getVelocity().add(mainBlock.getAcceleration().x, mainBlock.getAcceleration().y);
		
		originBlock.getAcceleration().x = GRAVITY;
		originBlock.getAcceleration().scl(delta);
		originBlock.getVelocity().add(originBlock.getAcceleration().x, originBlock.getAcceleration().y);
		originBlock.update(delta);
		originBlock.getVelocity().x = SCROLL_SPEED;
		
		powerup.getAcceleration().x = GRAVITY;
		powerup.getAcceleration().scl(delta);
		powerup.getVelocity().add(powerup.getAcceleration().x, powerup.getAcceleration().y);
		powerup.update(delta);
		powerup.getVelocity().x = SCROLL_SPEED;

		// remove this
//		for (Block block : world.getBlocks()) {
//			block.getAcceleration().x = GRAVITY;
//			block.getAcceleration().scl(delta);
//			block.getVelocity().add(block.getAcceleration().x, block.getAcceleration().y);
//			block.update(delta);
//			block.getVelocity().x = SCROLL_SPEED;
//			
//			if (block.getPosition().x < -8){
//				block.getPosition().x = 16;
//			}
//			
//
//			block.setPosition(block.getPosition());
//		}
		
		for (Square square : world.getSquares()) {
			onSquare = false;
			
			if (square == null){
				System.out.println("null");

			}
//			if (square.visible == true && square.getPosition().x + square.SIZE < 0) {
//				square.carryingBlock = false;
//				square.visible = false;
////				System.out.println("now visible");
////				System.out.println(world.getSquares().indexOf(square, false));
//			}
//			else if(square.visible == false && square.getPosition().x < 20f && square.getPosition().x > 0f){
//				square.visible = true;
////				System.out.println("now not visible");
//			}
//			else if (square.visible == true) {
////				System.out.println("visible");
//			}
			square.getAcceleration().x = GRAVITY;
			square.getAcceleration().scl(delta);
			square.getVelocity().add(square.getAcceleration().x, square.getAcceleration().y);
			square.update(delta);
			square.getVelocity().x = SCROLL_SPEED;
//			onSquare = false;

			
			//mainblock just slides off the square
//			if (square.getPosition().y < mainBlock.getPosition().y && square.visible == true && square.carryingBlock == true && square.getPosition().x + square.SIZE < mainBlock.getPosition().x) {
			// SOMETHING IS WRONG HERE< REDO THIS WHOLE SECTION< RETHINK HOW TO DO IT
//			if (square.getPosition().x < mainBlock.getPosition().x + mainBlock.SIZE) {
//			if (square.visible == true && square.carryingBlock && square.getPosition().x + square.SIZE < mainBlock.getPosition().x) {
//				square.carryingBlock = false;
//				onBlockHeight = 0;
////				System.out.printf("for some reason here");
////				onSquare = false;
//				
//				if (!mainBlock.getState().equals(State.JUMPING)) {
//					mainBlock.getVelocity().y = 0;
//					mainBlock.setState(State.JUMPING);
//		        	System.out.println("falling");
//				}
//			}
//        	System.out.println(onSquare);
			


			
			// first check if the mainblock is falling
//			if (mainBlock.getState() == State.JUMPING && mainBlock.getVelocity().y <= 0 && (mainBlock.getPosition().x + mainBlock.SIZE >= square.getPosition().x) && (mainBlock.getPosition().x <= (square.getPosition().x + square.SIZE)) && ((mainBlock.getPosition().y + mainBlock.SIZE >= square.getPosition().y + square.SIZE) && (mainBlock.getPosition().y <= (square.getPosition().y + square.SIZE)))) {
			if (square.carryingBlock){
				System.out.println(world.getSquares().indexOf(square, false));
				
			}
			// check if it lands on the block
			// figure out why i need the times 2  - if i dont have it, the blocks fall off too early
			
			// check if block crashes into square from below 
			if (!onSquare && ((mainBlock.getPosition().x + mainBlock.SIZE > square.getPosition().x) && (mainBlock.getPosition().x + mainBlock.SIZE < (square.getPosition().x + square.SIZE))) && ((mainBlock.getPosition().y + mainBlock.SIZE > square.getPosition().y) && (mainBlock.getPosition().y + mainBlock.SIZE < (square.getPosition().y + square.SIZE)))) {
				mainBlock.setStateTime(0);

				GameScreen.setState(Type.STOPPED);
		        System.out.println("collision");
			}
			// check if block crashes drectly into a square
			else if (!onSquare && ((mainBlock.getPosition().x + mainBlock.SIZE > square.getPosition().x) && (mainBlock.getPosition().x + mainBlock.SIZE < (square.getPosition().x + square.SIZE))) && ((mainBlock.getPosition().y + mainBlock.SIZE == square.getPosition().y + square.SIZE) && (mainBlock.getPosition().y == (square.getPosition().y)))) {
				mainBlock.setStateTime(0);

				GameScreen.setState(Type.STOPPED);
		        System.out.println("collision");
			}
			// check if the top part of the sqare touches the bottom part of the block, the 0.5f represents the "leeway", ie, how forgiving this is, if the block is a tiny bit below the square, then it is stll acceptable
			else if (!onSquare && !square.carryingBlock && ((mainBlock.getPosition().x + mainBlock.SIZE > square.getPosition().x) && (mainBlock.getPosition().x + mainBlock.SIZE * 1.85 < (square.getPosition().x + square.SIZE))) && ((mainBlock.getPosition().y + mainBlock.SIZE * 1.85 > square.getPosition().y + square.SIZE) && (mainBlock.getPosition().y < (square.getPosition().y + square.SIZE)))) {
				mainBlock.setStateTime(0);

				GameScreen.setState(Type.STOPPED);
		        System.out.println("collision");
			}
			//check if block lands on square
			// to do: make sure the block dies if it slips off a square and crashes into one
			else if (mainBlock.getVelocity().y <= 0 && (mainBlock.getPosition().x + mainBlock.SIZE >= square.getPosition().x) && (mainBlock.getPosition().x + mainBlock.SIZE <= square.getPosition().x + square.SIZE * 2) && ((mainBlock.getPosition().y + mainBlock.SIZE >= square.getPosition().y + square.SIZE) && (mainBlock.getPosition().y <= (square.getPosition().y + square.SIZE)))) {
//				System.out.println("landed on block");
		        square.carryingBlock = true;
//				mainBlock.getVelocity().y = 0;

//				mainBlock.setState(State.IDLE);
//		        onSquare = true;
		        onBlockHeight = square.getPosition().y + square.SIZE;
//		        System.out.println(onSquare);

			}
		    else if (!jumpingPressed && mainBlock.getState().equals(State.IDLE) && square.carryingBlock && square.getPosition().x + square.SIZE < mainBlock.getPosition().x ) {
				square.carryingBlock = false;
				
//				onSquare = false;
				onBlockHeight = 2f;
				mainBlock.getVelocity().y = 0;
//				mainBlock.setState(State.JUMPING);
//	        	System.out.println("not on block");				
			}
			
		    if (square.carryingBlock){
		    	onSquare = true;
		    	System.out.println("on blocks");				
		    	
		    }
			

			

			
//			if (square.getPosition().x < -1){
//				square.getPosition().x = 16;
//			}
			

			square.setPosition(square.getPosition());
		}
		
		for (Obstacle obstacle : world.getObstacles()) {
			if (obstacle == null){
				System.out.println("null");

			}
			obstacle.getAcceleration().x = GRAVITY;
			obstacle.getAcceleration().scl(delta);
			obstacle.getVelocity().add(obstacle.getAcceleration().x, obstacle.getAcceleration().y);
			obstacle.update(delta);
			obstacle.getVelocity().x = SCROLL_SPEED;
			
//			 collision detection
			//check line of each triangle
			// just check if verticies of triangle are in the square
			// a is the left verticie of the triangle, b is the top, c is the right
			float ax = obstacle.getPosition().x;
			float ay = obstacle.getPosition().y;

			float bx = obstacle.getPosition().x + (obstacle.SIZE/(float)2);
			float by = obstacle.getPosition().y + obstacle.SIZE;

			float cx = obstacle.getPosition().x + obstacle.SIZE;
			float cy = obstacle.getPosition().y;
			
			//check if a touches the mainblock (the only way this can occur is if the mainblock crashes head on into the triangle)
			if ((mainBlock.getPosition().x + mainBlock.SIZE >= ax && ax >= mainBlock.getPosition().x && mainBlock.getPosition().y == ay)) {
				mainBlock.setStateTime(0);

				GameScreen.setState(Type.STOPPED);
			}
			
			//check if b touches the mainblock (the only way this can occur is if the mainblock lands on the triangle)
			if (bx >= mainBlock.getPosition().x && bx <= mainBlock.getPosition().x + mainBlock.SIZE && by >= mainBlock.getPosition().y && by <= mainBlock.getPosition().y + mainBlock.SIZE){
				System.out.println("fuckshit");
//				mainBlock.setState(State.DYING);
//				mainBlock.setState(State.IDLE);
				mainBlock.setStateTime(0);

				GameScreen.setState(Type.STOPPED);
			}
			
			//check if c touches the mainblock (the only way this can occur is if the mainblock lands on the triangle but passes b)
			if (cx >= mainBlock.getPosition().x && cx <= mainBlock.getPosition().x + mainBlock.SIZE && cy == mainBlock.getPosition().y){
//				mainBlock.setState(State.IDLE);
				mainBlock.setStateTime(0);

				GameScreen.setState(Type.STOPPED);
			}

//			if (((mainBlock.getPosition().x >= obstacle.getPosition().x) && (mainBlock.getPosition().x <= (obstacle.getPosition().x + obstacle.SIZE))) && ((mainBlock.getPosition().y >= obstacle.getPosition().y) && (mainBlock.getPosition().y <= (obstacle.getPosition().y + obstacle.SIZE)))) {
//				GameScreen.setState(Type.STOPPED);
////		        System.out.println("collision");
//			}
//			else if (((mainBlock.getPosition().x + mainBlock.SIZE >= obstacle.getPosition().x) && (mainBlock.getPosition().x + mainBlock.SIZE <= (obstacle.getPosition().x + obstacle.SIZE))) && ((mainBlock.getPosition().y + mainBlock.SIZE >= obstacle.getPosition().y) && (mainBlock.getPosition().y + mainBlock.SIZE <= (obstacle.getPosition().y + obstacle.SIZE)))) {
//				GameScreen.setState(Type.STOPPED);
////		        System.out.println("collision");
//			}
			
			// reset position
//			if (obstacle.getPosition().x < -1){
//				obstacle.getPosition().x = 16;
//			}
//			

			obstacle.setPosition(obstacle.getPosition());
		}
		
		for (Rect rect: world.getRects()) {
			if (rect == null){
				System.out.println("null");

			}
			rect.getAcceleration().x = GRAVITY;
			rect.getAcceleration().scl(delta);
			rect.getVelocity().add(rect.getAcceleration().x, rect.getAcceleration().y);
			rect.update(delta);
			rect.getVelocity().x = SCROLL_SPEED;
			
//			 collision detection
			//check line of each triangle
			// just check if verticies of triangle are in the square
			// a is the left verticie of the triangle, b is the top, c is the right
//			float ax = rect.getPosition().x;
//			float ay = rect.getPosition().y;
//
//			float bx = rect.getPosition().x + (rect.SIZE/(float)2);
//			float by = rect.getPosition().y + rect.SIZE;
//
//			float cx = rect.getPosition().x + rect.SIZE;
//			float cy = rect.getPosition().y;
//			
//			//check if a touches the mainblock (the only way this can occur is if the mainblock crashes head on into the triangle)
//			if ((mainBlock.getPosition().x + mainBlock.SIZE >= ax && ax >= mainBlock.getPosition().x && mainBlock.getPosition().y == ay)) {
//				GameScreen.setState(Type.STOPPED);
//			}
//			
//			//check if b touches the mainblock (the only way this can occur is if the mainblock lands on the triangle)
//			if (bx >= mainBlock.getPosition().x && bx <= mainBlock.getPosition().x + mainBlock.SIZE && by >= mainBlock.getPosition().y && by <= mainBlock.getPosition().y + mainBlock.SIZE){
//				GameScreen.setState(Type.STOPPED);
//			}
//			
//			//check if c touches the mainblock (the only way this can occur is if the mainblock lands on the triangle but passes b)
//			if (cx >= mainBlock.getPosition().x && cx <= mainBlock.getPosition().x + mainBlock.SIZE && cy == mainBlock.getPosition().y){
//				GameScreen.setState(Type.STOPPED);
//			}

			if (((mainBlock.getPosition().x >= rect.getPosition().x) && (mainBlock.getPosition().x <= (rect.getPosition().x + rect.SIZE))) && ((mainBlock.getPosition().y >= rect.getPosition().y) && (mainBlock.getPosition().y <= (rect.getPosition().y + rect.SIZE)))) {
				mainBlock.setStateTime(0);
				GameScreen.setState(Type.STOPPED);
//		        System.out.println("collision");
			}
//			else if (((mainBlock.getPosition().x + mainBlock.SIZE >= rect.getPosition().x) && (mainBlock.getPosition().x + mainBlock.SIZE <= (rect.getPosition().x + rect.SIZE))) && ((mainBlock.getPosition().y + mainBlock.SIZE >= rect.getPosition().y) && (mainBlock.getPosition().y + mainBlock.SIZE <= (rect.getPosition().y + rect.SIZE)))) {
//				GameScreen.setState(Type.STOPPED);
////		        System.out.println("collision");
//			}
			
			//reset position
//			if (rect.getPosition().x < -1){
//				rect.getPosition().x = 16;
//			}
//			

			rect.setPosition(rect.getPosition());
		}
		
		for (Circ circ: world.getCircs()) {
			if (circ == null){
				System.out.println("null");

			}
			if (circ.getPosition().x + circ.SIZE < 0){
				circ.collected = false;
				circ.setStateTime(0);
				circ.animationDone = false;
				circ.pointsAdded = false;

			}
			circ.getAcceleration().x = GRAVITY;
			circ.getAcceleration().scl(delta);
			circ.getVelocity().add(circ.getAcceleration().x, circ.getAcceleration().y);
			circ.update(delta);
			circ.getVelocity().x = SCROLL_SPEED;
			
//			 collision detection
			//check line of each triangle
			// just check if verticies of triangle are in the square
			// a is the left verticie of the triangle, b is the top, c is the right
//			float ax = circ.getPosition().x;
//			float ay = circ.getPosition().y;
//
//			float bx = circ.getPosition().x + (circ.SIZE/(float)2);
//			float by = circ.getPosition().y + circ.SIZE;
//
//			float cx = circ.getPosition().x + circ.SIZE;
//			float cy = circ.getPosition().y;
//			
//			//check if a touches the mainblock (the only way this can occur is if the mainblock crashes head on into the triangle)
//			if ((mainBlock.getPosition().x + mainBlock.SIZE >= ax && ax >= mainBlock.getPosition().x && mainBlock.getPosition().y == ay)) {
//				GameScreen.setState(Type.STOPPED);
//			}
//			
//			//check if b touches the mainblock (the only way this can occur is if the mainblock lands on the triangle)
//			if (bx >= mainBlock.getPosition().x && bx <= mainBlock.getPosition().x + mainBlock.SIZE && by >= mainBlock.getPosition().y && by <= mainBlock.getPosition().y + mainBlock.SIZE){
//				GameScreen.setState(Type.STOPPED);
//			}
//			
//			//check if c touches the mainblock (the only way this can occur is if the mainblock lands on the triangle but passes b)
//			if (cx >= mainBlock.getPosition().x && cx <= mainBlock.getPosition().x + mainBlock.SIZE && cy == mainBlock.getPosition().y){
//				GameScreen.setState(Type.STOPPED);
//			}

			if (((mainBlock.getPosition().x + mainBlock.SIZE >= circ.getPosition().x) && (mainBlock.getPosition().x <= (circ.getPosition().x + circ.SIZE))) && ((mainBlock.getPosition().y <= circ.getPosition().y) && (mainBlock.getPosition().y + mainBlock.SIZE >= (circ.getPosition().y + circ.SIZE)))) {
				if (!circ.pointsAdded) {
					mainBlock.setScore(mainBlock.getScore() + 100);
					circ.pointsAdded = true;
				}
				circ.collected = true;
				System.out.println("coin collision");
			}
			else if (((mainBlock.getPosition().x + mainBlock.SIZE >= circ.getPosition().x) && (mainBlock.getPosition().x + mainBlock.SIZE <= (circ.getPosition().x + circ.SIZE))) && ((mainBlock.getPosition().y + mainBlock.SIZE >= circ.getPosition().y) && (mainBlock.getPosition().y + mainBlock.SIZE <= (circ.getPosition().y + circ.SIZE)))) {
		        circ.collected = true;
				System.out.println("coin collision");
			}
			
			
			//reset position
//			if (circ.getPosition().x < -1){
//				circ.getPosition().x = 16;
//			}
//			

			circ.setPosition(circ.getPosition());
		}
		
		
		mainBlock.update(delta);

		
//		for (Square square : world.getSquares()) {
//			if (square.carryingBlock) {
//				onSquare = true;
//			}
//		}
			
//		if (onSquare){
////			System.out.println("whattst");
////			mainBlock.getPosition().y = onBlockHeight;
//			mainBlock.setPosition(mainBlock.getPosition());
//			mainBlock.setState(State.IDLE);
////			mainBlock.getVelocity().y = 0;
//
//		}
		
		
		if (mainBlock.getPosition().y < onBlockHeight) {
			if (onSquare) {
				mainBlock.setState(State.IDLE);
			}
			mainBlock.getPosition().y = onBlockHeight;
			mainBlock.setPosition(mainBlock.getPosition());
			if (mainBlock.getState().equals(State.JUMPING)) {
					mainBlock.setState(State.IDLE);
			}
		}
		else if((!(GameScreen.getType().equals(Type.STOPPED) && !(mainBlock.getState().equals(State.DYING)))) && !jumpingPressed && !(mainBlock.getState().equals(State.JUMPING)) && !onSquare && mainBlock.getPosition().y + mainBlock.SIZE > onBlockHeight) {

//        	mainBlock.getVelocity().y = 0;
			mainBlock.setState(State.JUMPING);
		}
		
		if (GameScreen.getType().equals(Type.STOPPED)){
//			mainBlock.setState(State.IDLE);
//			mainBlock.setState(State.DYING);
		}
		
	}
	
	public void resetBlocks() {		
		
		onSquare = false;
		onBlockHeight = 2f;
		
//		for (int i = 0; i < 24; i = i + 8) {
//			blocks.add(new Block(new Vector2(i, 0)));
//		}
		
//		if (newGame){
//			world.drawLevel(0, setNum); // draw blank level for first level
//			if (setNum == 2){
//				setNum = 1;
//			}
//			else{
//				setNum = 2;
//			}
//			newGame = false;
//		}
//		
//		onSquare = false; 
		mainBlock.setScore(0);
		mainBlock.getPosition().x = 2f;
		mainBlock.getPosition().y = 2f;
		mainBlock.setPosition(mainBlock.getPosition());
		mainBlock.setState(State.IDLE);

		
		
		powerup.getPosition().x = 13f;
		powerup.getPosition().y = 4f;
		powerup.setPosition(powerup.getPosition());

		for (Obstacle obstacle : world.getObstacles()) {
			obstacle.getPosition().x = -1;
			obstacle.getPosition().y = -1;
			obstacle.setPosition(obstacle.getPosition());
		}
		
		for (Square square : world.getSquares()) {
//			System.out.printf("reset");
			square.carryingBlock = false;
			square.visible = false;
			square.getPosition().x = 0;
			square.getVelocity().x = 0;
			square.getPosition().y = 100;
			square.setPosition(square.getPosition());
		}
		
		for (Rect rect : world.getRects()) {
			rect.getPosition().x = -1;
			rect.getPosition().y = -1;
			rect.setPosition(rect.getPosition());
		}
		 
		for(Circ circ : world.getCircs()) {
			circ.collected = false;
			circ.animationDone = false;
			circ.pointsAdded = false;
			circ.setStateTime(0);
			circ.getPosition().x = -1;
			circ.getPosition().y = -1;
			circ.setPosition(circ.getPosition());
		}
		
		levelNum = random.nextInt(numLevels) + 1;
		world.drawLevel(levelNum, setNum);
//		world.drawLevel(numLevels, setNum);
//		world.drawLevel(20, setNum);

		if (setNum == 2){
			setNum = 1;
		}
		else{
			setNum = 2;
		}
	}
	
	public void stopBlocks() {		
		
		mainBlock.getVelocity().x = 0;
	
		for (Obstacle obstacle : world.getObstacles()) {
			obstacle.getVelocity().x = 0;
		}
	}

	/** Change Bob's state and parameters based on input controls **/
	private void processInput() {
		if (keys.get(Keys.JUMP)) {
			if ((!(GameScreen.getType().equals(Type.STOPPED) && !(mainBlock.getState().equals(State.DYING)))) && !mainBlock.getState().equals(State.JUMPING) ) {
				
//				for (Square square : world.getSquares()) {
//					square.carryingBlock = false;
//				}
//				onSquare = false;
				onBlockHeight = 2f;
				jumpingPressed = true;
				mainBlock.setState(State.JUMPING);
				mainBlock.getVelocity().y = MAX_JUMP_SPEED;
//				mainBlock.setPosition(mainBlock.getPosition().rotate(20));
//				WorldRenderer.rotateRight()
			}
		}
		else if (keys.get(Keys.PAUSE)) {
			GameScreen.setState(Type.PAUSE);
			// reset the pause key back to false
			keys.get(keys.put(Keys.PAUSE, false));
		}
		else if (keys.get(Keys.RESTART)) {
			resetBlocks();
			// reset the pause key back to false
			keys.get(keys.put(Keys.RESTART, false));
		}
	}
}
