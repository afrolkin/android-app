package net.obviam.gravswipe.model;

import java.util.Arrays;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.obviam.gravswipe.model.World;



//rename this to levels instead of level

public class Levels {
	
	/** The blocks making up the obstacle blocks **/
	Array<Obstacle> obstacles;
	/** The blocks making up the obstacle blocks **/
	Array<Square> squares;
	/** The blocks making up the world **/
	Array<Rect> rects; 
	/** The circles **/
	Array<Circ> circs;

	
	World 	world;
	int start = 0;
	int end = 0;
	float SCROLL_SPEED = -6.5f;

	public Levels(World world) {
		this.world = world;
		this.obstacles = world.getObstacles();
		this.squares = world.getSquares();
		this.rects = world.getRects();
		this.circs = world.getCircs();

	}
	

	private void drawLevel(int levelNum, int setNum) {
		// x corressponds to the level being loaded
		// setnum corressponds to the set number, set 1 uses item 0-19 of each array, while set 2 uses items 20-39
		//world represents the world set which the level is being loaded with
		
		if (setNum == 2){
			int start = 20;
			int end = 39;
		}
		else{
			int start = 0;
			int end = 39;
		}
		
		int i = start;
		
		if (levelNum == 1){
			obstacles.get(start).getPosition().x = 5;
			obstacles.get(start).getPosition().y = 2;
			obstacles.get(start).setPosition(obstacles.get(start).getPosition());
			obstacles.get(start).getVelocity().x = SCROLL_SPEED;

			
		}
	}
}
	
	
	
	
//	int i = 18;
//	for (Obstacle obstacle : world.getObstacles()) {
//		obstacle.getPosition().x = i;
//		obstacle.getPosition().y = 2;
//		i = i + 5;
//		obstacle.setPosition(obstacle.getPosition());
//		obstacle.getVelocity().x = SCROLL_SPEED;
//
//
//	}
//	
//	i = 14;
//	int j = 2;
//	for (Square square : world.getSquares()) {
//		square.carryingBlock = false;
//		square.getPosition().x = i;
//		square.getPosition().y = j;
//		j++;
//		i = i + 4;
//		square.setPosition(square.getPosition());
//		square.getVelocity().x = SCROLL_SPEED;
//	}
//	
//	i = 0;
//	for (Rect rect : world.getRects()) {
//		rect.getPosition().x = i;
//		rect.getPosition().y = 1;
//		i = i + 12;
//		rect.setPosition(rect.getPosition());
//		rect.getVelocity().x = SCROLL_SPEED;
//	}
//	 
//	i = 14;
//	for(Circ circ : world.getCircs()) {
//		circ.getPosition().x = i + (float)0.25;
//		circ.getPosition().y = (float)3.25;
//		i = i + 12;
//		circ.setPosition(circ.getPosition());
//		circ.getVelocity().x = SCROLL_SPEED;
//
//	}
//}

