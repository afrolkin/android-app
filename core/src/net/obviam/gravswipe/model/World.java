package net.obviam.gravswipe.model;
// the problem with the jumping is blocks that are not sequential, ie, if i place square 5, then square 1, square 1 will not work with jumping for some reason 

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	/** The blocks making up the obstacle blocks **/
	Array<Obstacle> obstacles = new Array<Obstacle>();
	/** The blocks making up the obstacle blocks **/
	Array<Square> squares = new Array<Square>();
	/** The blocks making up the world **/
	Array<Block> blocks = new Array<Block>();
	/** Our player controlled block **/
	MainBlock mainBlock;
	/** block used to determine when to generate new level **/
	OriginBlock originBlock;
	/** The purple pits **/
	Array<Rect> rects = new Array<Rect>();
	/** The circles **/
	Array<Circ> circs = new Array<Circ>();
	/** The floor line **/
	Array<Line> lines = new Array<Line>();
	/** The powerups **/
	Powerup powerup;
	
	int start = 0;
	int end = 0;
	float SCROLL_SPEED = -6.5f;
	// this must be hardcoded in and maintained
	int numLevels = 28;

	// Getters -----------
	public int getNumLevels() {
		return numLevels;
	}
	public Array<Block> getBlocks() {
		return blocks;
	}
	public Array<Rect> getRects() {
		return rects;
	}
	public Array<Circ> getCircs() {
		return circs;
	}
	public Array<Square> getSquares() {
		return squares;
	}
	public Array<Obstacle> getObstacles() {
		return obstacles;
	}
	public MainBlock getMainBlock() {
		return mainBlock;
	}
	public OriginBlock getOriginBlock() {
		return originBlock;
	}
	public Array<Line> getLines() {
		return lines;
	}
	public Powerup getPowerup() {
		return powerup;
	}
	// --------------------

	public World() {
		createDemoWorld();
	}
	
	public void drawLevel(int levelNum, int setNum) {
		// x corressponds to the level being loaded
		// setnum corressponds to the set number, set 1 uses item 0-19 of each array, while set 2 uses items 20-39
		//world represents the world set which the level is being loaded with
		originBlock.getPosition().x = 20;
		originBlock.getPosition().y = 1;
		originBlock.setPosition(originBlock.getPosition());
		originBlock.getVelocity().x = SCROLL_SPEED;
		for (Square square : getSquares()) {
			if (square.carryingBlock == true){
				System.out.println("fuckingnunfdbfudbfdbfdbfjsnbfjsnbfsnfks");
			}
		}

		
		if (setNum == 2){
			start = 20;
			end = 39;
		}
		else{
			start = 0;
			end = 19;
		}
		
		int i = start;
		
		if (levelNum == 0){
			//blank level for start;
		}
		else if (levelNum == 1){
			obstacles.get(start).getPosition().x = 2f + 20f;
			obstacles.get(start).getPosition().y = 2f;
			obstacles.get(start).setPosition(obstacles.get(start).getPosition());
			
			obstacles.get(start+1).getPosition().x = 6f + 20f;
			obstacles.get(start+1).getPosition().y = 2f;
			obstacles.get(start+1).setPosition(obstacles.get(start+1).getPosition());
			
			obstacles.get(start+2).getPosition().x = 10f + 20f;
			obstacles.get(start+2).getPosition().y = 2f;
			obstacles.get(start+2).setPosition(obstacles.get(start+2).getPosition());
			
			obstacles.get(start+3).getPosition().x = 11f + 20f;
			obstacles.get(start+3).getPosition().y = 2f;
			obstacles.get(start+3).setPosition(obstacles.get(start+3).getPosition());
			
			obstacles.get(start+4).getPosition().x = 15f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
//			obstacles.get(start).getVelocity().x = SCROLL_SPEED;
		}
		else if (levelNum == 2){
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 3f + 20f;
			squares.get(start).getPosition().y = 2f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
//			squares.get(start).getVelocity().x = SCROLL_SPEED;
			
			rects.get(start).getPosition().x = 4f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
			rects.get(start+1).getPosition().x = 5f + 20f;
			rects.get(start+1).getPosition().y = 1f;
			rects.get(start+1).setPosition(rects.get(start+1).getPosition());
			
			obstacles.get(start+2).getPosition().x = 9f + 20f;
			obstacles.get(start+2).getPosition().y = 2f;
			obstacles.get(start+2).setPosition(obstacles.get(start+2).getPosition());
			
			circs.get(start + 1).getPosition().x = 9.25f + 20f;
			circs.get(start + 1).getPosition().y = 4.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());
			
			obstacles.get(start+3).getPosition().x = 13f + 20f;
			obstacles.get(start+3).getPosition().y = 2f;
			obstacles.get(start+3).setPosition(obstacles.get(start+3).getPosition());
			
			circs.get(start + 2).getPosition().x = 13.25f + 20f;
			circs.get(start + 2).getPosition().y = 4.25f;
			circs.get(start + 2).getVelocity().x = -6.5f;
			circs.get(start + 2).setPosition(circs.get(start + 2).getPosition());
		}
		else if (levelNum == 3){
			
			circs.get(start + 1).getPosition().x = 0f + 20f;
			circs.get(start + 1).getPosition().y = 2.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());

			circs.get(start + 2).getPosition().x = 1f + 20f;
			circs.get(start + 2).getPosition().y = 2.25f;
			circs.get(start + 2).getVelocity().x = -6.5f;
			circs.get(start + 2).setPosition(circs.get(start + 2).getPosition());
			
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 2f + 20f;
			squares.get(start).getPosition().y = 2f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 2f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 2f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());

			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 5f + 20f;
			squares.get(start + 3).getPosition().y = 2f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			obstacles.get(start).getPosition().x = 5f + 20f;
			obstacles.get(start).getPosition().y = 3f;
			obstacles.get(start).setPosition(obstacles.get(start).getPosition());
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 6f + 20f;
			squares.get(start + 4).getPosition().y = 2f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 7f + 20f;
			squares.get(start + 5).getPosition().y = 2f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			squares.get(start + 6).carryingBlock = false;
			squares.get(start + 6).visible = true;
			squares.get(start + 6).getPosition().x = 8f + 20f;
			squares.get(start + 6).getPosition().y = 2f;
			squares.get(start + 6).getVelocity().x = -6.5f;
			squares.get(start + 6).setPosition(squares.get(start + 6).getPosition());
			
			rects.get(start).getPosition().x = 9f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
			rects.get(start+1).getPosition().x = 10f + 20f;
			rects.get(start+1).getPosition().y = 1f;
			rects.get(start+1).setPosition(rects.get(start+1).getPosition());
			
			circs.get(start + 3).getPosition().x = 9.75f + 20f;
			circs.get(start + 3).getPosition().y = 5.25f;
			circs.get(start + 3).getVelocity().x = -6.5f;
			circs.get(start + 3).setPosition(circs.get(start + 3).getPosition());
			
			squares.get(start + 7).carryingBlock = false;
			squares.get(start + 7).visible = true;
			squares.get(start + 7).getPosition().x = 11f + 20f;
			squares.get(start + 7).getPosition().y = 2f;
			squares.get(start + 7).getVelocity().x = -6.5f;
			squares.get(start + 7).setPosition(squares.get(start + 7).getPosition());
			
			squares.get(start + 8).carryingBlock = false;
			squares.get(start + 8).visible = true;
			squares.get(start + 8).getPosition().x = 12f + 20f;
			squares.get(start + 8).getPosition().y = 2f;
			squares.get(start + 8).getVelocity().x = -6.5f;
			squares.get(start + 8).setPosition(squares.get(start + 8).getPosition());
			
			circs.get(start + 4).getPosition().x = 14f + 20f;
			circs.get(start + 4).getPosition().y = 2.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
			circs.get(start + 5).getPosition().x = 15f + 20f;
			circs.get(start + 5).getPosition().y = 2.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());
			
			circs.get(start + 6).getPosition().x = 16f + 20f;
			circs.get(start + 6).getPosition().y = 2.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 7).getPosition().x = 17f + 20f;
			circs.get(start + 7).getPosition().y = 2.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
		}
		else if (levelNum == 4) {
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 2f + 20f;
			squares.get(start).getPosition().y = 2f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
			
			circs.get(start + 1).getPosition().x = 2.25f + 20f;
			circs.get(start + 1).getPosition().y = 3.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());
			
			circs.get(start + 6).getPosition().x = 4.25f + 20f;
			circs.get(start + 6).getPosition().y = 5.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());

			rects.get(start).getPosition().x = 3f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
			obstacles.get(start).getPosition().x = 5f + 20f;
			obstacles.get(start).getPosition().y = 2f;
			obstacles.get(start).setPosition(obstacles.get(start).getPosition());
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 6f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			circs.get(start + 5).getPosition().x = 8.25f + 20f;
			circs.get(start + 5).getPosition().y = 6.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());

			circs.get(start + 2).getPosition().x = 6.25f + 20f;
			circs.get(start + 2).getPosition().y = 4.25f;
			circs.get(start + 2).getVelocity().x = -6.5f;
			circs.get(start + 2).setPosition(circs.get(start + 2).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 10f + 20f;
			squares.get(start + 2).getPosition().y = 4f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());
			
			circs.get(start + 3).getPosition().x = 10.25f + 20f;
			circs.get(start + 3).getPosition().y = 5.25f;
			circs.get(start + 3).getVelocity().x = -6.5f;
			circs.get(start + 3).setPosition(circs.get(start + 3).getPosition());
			
			circs.get(start + 7).getPosition().x = 12.25f + 20f;
			circs.get(start + 7).getPosition().y = 7.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());

			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 14f + 20f;
			squares.get(start + 3).getPosition().y = 5f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			circs.get(start + 4).getPosition().x = 14.25f + 20f;
			circs.get(start + 4).getPosition().y = 6.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
		}
		else if (levelNum == 5) {
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = false;
			squares.get(start).getPosition().x = 2f + 20f;
			squares.get(start).getPosition().y = 2f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = false;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 2f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = false;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 2f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());

			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = false;
			squares.get(start + 3).getPosition().x = 5f + 20f;
			squares.get(start + 3).getPosition().y = 2f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = false;
			squares.get(start + 5).getPosition().x = 6f + 20f;
			squares.get(start + 5).getPosition().y = 2f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 6f + 20f;
			squares.get(start + 4).getPosition().y = 4f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			circs.get(start + 1).getPosition().x = 6.25f + 20f;
			circs.get(start + 1).getPosition().y = 5.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());
			
			squares.get(start + 7).carryingBlock = false;
			squares.get(start + 7).visible = true;
			squares.get(start + 7).getPosition().x = 7f + 20f;
			squares.get(start + 7).getPosition().y = 2f;
			squares.get(start + 7).getVelocity().x = -6.5f;
			squares.get(start + 7).setPosition(squares.get(start + 7).getPosition());
			
			squares.get(start + 6).carryingBlock = false;
			squares.get(start + 6).visible = true;
			squares.get(start + 6).getPosition().x = 7f + 20f;
			squares.get(start + 6).getPosition().y = 4f;
			squares.get(start + 6).getVelocity().x = -6.5f;
			squares.get(start + 6).setPosition(squares.get(start + 6).getPosition());
			
			circs.get(start + 2).getPosition().x = 7.25f + 20f;
			circs.get(start + 2).getPosition().y = 5.25f;
			circs.get(start + 2).getVelocity().x = -6.5f;
			circs.get(start + 2).setPosition(circs.get(start + 2).getPosition());
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 8f + 20f;
			squares.get(start + 9).getPosition().y = 2f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			squares.get(start + 8).carryingBlock = false;
			squares.get(start + 8).visible = true;
			squares.get(start + 8).getPosition().x = 8f + 20f;
			squares.get(start + 8).getPosition().y = 4f;
			squares.get(start + 8).getVelocity().x = -6.5f;
			squares.get(start + 8).setPosition(squares.get(start + 8).getPosition());
			
			circs.get(start + 3).getPosition().x = 8.25f + 20f;
			circs.get(start + 3).getPosition().y = 5.25f;
			circs.get(start + 3).getVelocity().x = -6.5f;
			circs.get(start + 3).setPosition(circs.get(start + 3).getPosition());
			
			squares.get(start + 11).carryingBlock = false;
			squares.get(start + 11).visible = true;
			squares.get(start + 11).getPosition().x = 9f + 20f;
			squares.get(start + 11).getPosition().y = 2f;
			squares.get(start + 11).getVelocity().x = -6.5f;
			squares.get(start + 11).setPosition(squares.get(start + 11).getPosition());
			
			squares.get(start + 10).carryingBlock = false;
			squares.get(start + 10).visible = true;
			squares.get(start + 10).getPosition().x = 9f + 20f;
			squares.get(start + 10).getPosition().y = 4f;
			squares.get(start + 10).getVelocity().x = -6.5f;
			squares.get(start + 10).setPosition(squares.get(start + 10).getPosition());
			
			circs.get(start + 4).getPosition().x = 9.25f + 20f;
			circs.get(start + 4).getPosition().y = 5.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
			squares.get(start + 13).carryingBlock = false;
			squares.get(start + 13).visible = true;
			squares.get(start + 13).getPosition().x = 10f + 20f;
			squares.get(start + 13).getPosition().y = 2f;
			squares.get(start + 13).getVelocity().x = -6.5f;
			squares.get(start + 13).setPosition(squares.get(start + 13).getPosition());
			
			squares.get(start + 12).carryingBlock = false;
			squares.get(start + 12).visible = true;
			squares.get(start + 12).getPosition().x = 10f + 20f;
			squares.get(start + 12).getPosition().y = 4f;
			squares.get(start + 12).getVelocity().x = -6.5f;
			squares.get(start + 12).setPosition(squares.get(start + 12).getPosition());
			
			circs.get(start + 5).getPosition().x = 10.25f + 20f;
			circs.get(start + 5).getPosition().y = 5.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());

			squares.get(start + 15).carryingBlock = false;
			squares.get(start + 15).visible = true;
			squares.get(start + 15).getPosition().x = 11f + 20f;
			squares.get(start + 15).getPosition().y = 2f;
			squares.get(start + 15).getVelocity().x = -6.5f;
			squares.get(start + 15).setPosition(squares.get(start + 15).getPosition());
			
			squares.get(start + 14).carryingBlock = false;
			squares.get(start + 14).visible = true;
			squares.get(start + 14).getPosition().x = 11f + 20f;
			squares.get(start + 14).getPosition().y = 4f;
			squares.get(start + 14).getVelocity().x = -6.5f;
			squares.get(start + 14).setPosition(squares.get(start + 14).getPosition());
			
			circs.get(start + 6).getPosition().x = 11.25f + 20f;
			circs.get(start + 6).getPosition().y = 5.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			squares.get(start + 16).carryingBlock = false;
			squares.get(start + 16).visible = true;
			squares.get(start + 16).getPosition().x = 12f + 20f;
			squares.get(start + 16).getPosition().y = 2f;
			squares.get(start + 16).getVelocity().x = -6.5f;
			squares.get(start + 16).setPosition(squares.get(start + 16).getPosition());
			
			squares.get(start + 17).carryingBlock = false;
			squares.get(start + 17).visible = true;
			squares.get(start + 17).getPosition().x = 13f + 20f;
			squares.get(start + 17).getPosition().y = 2f;
			squares.get(start + 17).getVelocity().x = -6.5f;
			squares.get(start + 17).setPosition(squares.get(start + 17).getPosition());
			
			rects.get(start).getPosition().x = 1f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
		}
		else if (levelNum == 6) {
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 2f + 20f;
			squares.get(start).getPosition().y = 2f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());

			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 5f + 20f;
			squares.get(start + 3).getPosition().y = 4f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 6f + 20f;
			squares.get(start + 4).getPosition().y = 4f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 7f + 20f;
			squares.get(start + 5).getPosition().y = 4f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			squares.get(start + 6).carryingBlock = false;
			squares.get(start + 6).visible = true;
			squares.get(start + 6).getPosition().x = 8f + 20f;
			squares.get(start + 6).getPosition().y = 4f;
			squares.get(start + 6).getVelocity().x = -6.5f;
			squares.get(start + 6).setPosition(squares.get(start + 6).getPosition());
			
			squares.get(start + 7).carryingBlock = false;
			squares.get(start + 7).visible = true;
			squares.get(start + 7).getPosition().x = 9f + 20f;
			squares.get(start + 7).getPosition().y = 4f;
			squares.get(start + 7).getVelocity().x = -6.5f;
			squares.get(start + 7).setPosition(squares.get(start + 7).getPosition());
			
			squares.get(start + 8).carryingBlock = false;
			squares.get(start + 8).visible = true;
			squares.get(start + 8).getPosition().x = 10f + 20f;
			squares.get(start + 8).getPosition().y = 4f;
			squares.get(start + 8).getVelocity().x = -6.5f;
			squares.get(start + 8).setPosition(squares.get(start + 8).getPosition());

			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 11f + 20f;
			squares.get(start + 9).getPosition().y = 4f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			circs.get(start).getPosition().x = 5.25f + 20f;
			circs.get(start).getPosition().y = 5.25f;
			circs.get(start).getVelocity().x = -6.5f;
			circs.get(start).setPosition(circs.get(start).getPosition());
		
			circs.get(start + 1).getPosition().x = 6.25f + 20f;
			circs.get(start + 1).getPosition().y = 5.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());
			
			circs.get(start + 2).getPosition().x = 7.25f + 20f;
			circs.get(start + 2).getPosition().y = 5.25f;
			circs.get(start + 2).getVelocity().x = -6.5f;
			circs.get(start + 2).setPosition(circs.get(start + 2).getPosition());
			
			circs.get(start + 3).getPosition().x = 8.25f + 20f;
			circs.get(start + 3).getPosition().y = 5.25f;
			circs.get(start + 3).getVelocity().x = -6.5f;
			circs.get(start + 3).setPosition(circs.get(start + 3).getPosition());
			
			circs.get(start + 4).getPosition().x = 9.25f + 20f;
			circs.get(start + 4).getPosition().y = 5.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
			circs.get(start + 5).getPosition().x = 10.25f + 20f;
			circs.get(start + 5).getPosition().y = 5.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());
			
			circs.get(start + 6).getPosition().x = 11.25f + 20f;
			circs.get(start + 6).getPosition().y = 5.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			rects.get(start + 4).getPosition().x = 6f + 20f;
			rects.get(start + 4).getPosition().y = 3f;
			rects.get(start + 4).getVelocity().x = -6.5f;
			rects.get(start + 4).setPosition(rects.get(start + 4).getPosition());
			
			rects.get(start + 5).getPosition().x = 7f + 20f;
			rects.get(start + 5).getPosition().y = 3f;
			rects.get(start + 5).getVelocity().x = -6.5f;
			rects.get(start + 5).setPosition(rects.get(start + 5).getPosition());
			
			rects.get(start + 6).getPosition().x = 8f + 20f;
			rects.get(start + 6).getPosition().y = 3f;
			rects.get(start + 6).getVelocity().x = -6.5f;
			rects.get(start + 6).setPosition(rects.get(start + 6).getPosition());
			
			rects.get(start + 7).getPosition().x = 9f + 20f;
			rects.get(start + 7).getPosition().y = 3f;
			rects.get(start + 7).getVelocity().x = -6.5f;
			rects.get(start + 7).setPosition(rects.get(start + 7).getPosition());
			
			rects.get(start + 8).getPosition().x = 10f + 20f;
			rects.get(start + 8).getPosition().y = 3f;
			rects.get(start + 8).getVelocity().x = -6.5f;
			rects.get(start + 8).setPosition(rects.get(start + 8).getPosition());

			rects.get(start + 9).getPosition().x = 11f + 20f;
			rects.get(start + 9).getPosition().y = 3f;
			rects.get(start + 9).getVelocity().x = -6.5f;
			rects.get(start + 9).setPosition(rects.get(start + 9).getPosition());
		}
		else if (levelNum == 7) {
			obstacles.get(start).getPosition().x = 2f + 20f;
			obstacles.get(start).getPosition().y = 2f;
			obstacles.get(start).setPosition(obstacles.get(start).getPosition());
			
			obstacles.get(start+1).getPosition().x = 6f + 20f;
			obstacles.get(start+1).getPosition().y = 2f;
			obstacles.get(start+1).setPosition(obstacles.get(start+1).getPosition());
			
			obstacles.get(start+2).getPosition().x = 10f + 20f;
			obstacles.get(start+2).getPosition().y = 2f;
			obstacles.get(start+2).setPosition(obstacles.get(start+2).getPosition());
			
			obstacles.get(start+4).getPosition().x = 14f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
			
			obstacles.get(start+5).getPosition().x = 18f + 20f;
			obstacles.get(start+5).getPosition().y = 2f;
			obstacles.get(start+5).setPosition(obstacles.get(start+5).getPosition());
			
		}
		else if (levelNum == 8) {
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 2f + 20f;
			squares.get(start).getPosition().y = 3f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 3f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());

			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 5f + 20f;
			squares.get(start + 3).getPosition().y = 3f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 6f + 20f;
			squares.get(start + 4).getPosition().y = 3f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 7f + 20f;
			squares.get(start + 5).getPosition().y = 3f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			squares.get(start + 6).carryingBlock = false;
			squares.get(start + 6).visible = true;
			squares.get(start + 6).getPosition().x = 8f + 20f;
			squares.get(start + 6).getPosition().y = 3f;
			squares.get(start + 6).getVelocity().x = -6.5f;
			squares.get(start + 6).setPosition(squares.get(start + 6).getPosition());
			
			squares.get(start + 7).carryingBlock = false;
			squares.get(start + 7).visible = true;
			squares.get(start + 7).getPosition().x = 9f + 20f;
			squares.get(start + 7).getPosition().y = 3f;
			squares.get(start + 7).getVelocity().x = -6.5f;
			squares.get(start + 7).setPosition(squares.get(start + 7).getPosition());
			
			squares.get(start + 8).carryingBlock = false;
			squares.get(start + 8).visible = true;
			squares.get(start + 8).getPosition().x = 10f + 20f;
			squares.get(start + 8).getPosition().y = 3f;
			squares.get(start + 8).getVelocity().x = -6.5f;
			squares.get(start + 8).setPosition(squares.get(start + 8).getPosition());

			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 11f + 20f;
			squares.get(start + 9).getPosition().y = 3f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			rects.get(start + 3).getPosition().x = 3f + 20f;
			rects.get(start + 3).getPosition().y = 1f;
			rects.get(start + 3).getVelocity().x = -6.5f;
			rects.get(start + 3).setPosition(rects.get(start + 3).getPosition());
			
			rects.get(start).getPosition().x = 3f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).getVelocity().x = -6.5f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
			rects.get(start + 2).getPosition().x = 4f + 20f;
			rects.get(start + 2).getPosition().y = 1f;
			rects.get(start + 2).getVelocity().x = -6.5f;
			rects.get(start + 2).setPosition(rects.get(start + 2).getPosition());
			
			rects.get(start + 1).getPosition().x = 5f + 20f;
			rects.get(start + 1).getPosition().y = 1f;
			rects.get(start + 1).getVelocity().x = -6.5f;
			rects.get(start + 1).setPosition(rects.get(start + 1).getPosition());
			
			rects.get(start + 4).getPosition().x = 6f + 20f;
			rects.get(start + 4).getPosition().y = 1f;
			rects.get(start + 4).getVelocity().x = -6.5f;
			rects.get(start + 4).setPosition(rects.get(start + 4).getPosition());
			
			rects.get(start + 5).getPosition().x = 7f + 20f;
			rects.get(start + 5).getPosition().y = 1f;
			rects.get(start + 5).getVelocity().x = -6.5f;
			rects.get(start + 5).setPosition(rects.get(start + 5).getPosition());
			
			rects.get(start + 6).getPosition().x = 8f + 20f;
			rects.get(start + 6).getPosition().y = 1f;
			rects.get(start + 6).getVelocity().x = -6.5f;
			rects.get(start + 6).setPosition(rects.get(start + 6).getPosition());
			
			rects.get(start + 7).getPosition().x = 9f + 20f;
			rects.get(start + 7).getPosition().y = 1f;
			rects.get(start + 7).getVelocity().x = -6.5f;
			rects.get(start + 7).setPosition(rects.get(start + 7).getPosition());
			
			rects.get(start + 8).getPosition().x = 10f + 20f;
			rects.get(start + 8).getPosition().y = 1f;
			rects.get(start + 8).getVelocity().x = -6.5f;
			rects.get(start + 8).setPosition(rects.get(start + 8).getPosition());

			rects.get(start + 9).getPosition().x = 11f + 20f;
			rects.get(start + 9).getPosition().y = 1f;
			rects.get(start + 9).getVelocity().x = -6.5f;
			rects.get(start + 9).setPosition(rects.get(start + 9).getPosition());
			
			circs.get(start).getPosition().x = 5.25f + 20f;
			circs.get(start).getPosition().y = 4.25f;
			circs.get(start).getVelocity().x = -6.5f;
			circs.get(start).setPosition(circs.get(start).getPosition());
		
			circs.get(start + 1).getPosition().x = 4.25f + 20f;
			circs.get(start + 1).getPosition().y = 4.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());
			
			circs.get(start + 2).getPosition().x = 5.25f + 20f;
			circs.get(start + 2).getPosition().y = 4.25f;
			circs.get(start + 2).getVelocity().x = -6.5f;
			circs.get(start + 2).setPosition(circs.get(start + 2).getPosition());
			
			circs.get(start + 3).getPosition().x = 6.25f + 20f;
			circs.get(start + 3).getPosition().y = 4.25f;
			circs.get(start + 3).getVelocity().x = -6.5f;
			circs.get(start + 3).setPosition(circs.get(start + 3).getPosition());
			
			circs.get(start + 4).getPosition().x = 7.25f + 20f;
			circs.get(start + 4).getPosition().y = 4.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
			circs.get(start + 5).getPosition().x = 8.25f + 20f;
			circs.get(start + 5).getPosition().y = 4.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());
			
			circs.get(start + 6).getPosition().x = 9.25f + 20f;
			circs.get(start + 6).getPosition().y = 4.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 7).getPosition().x = 10.25f + 20f;
			circs.get(start + 7).getPosition().y = 4.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
			
			circs.get(start + 8).getPosition().x = 11.25f + 20f;
			circs.get(start + 8).getPosition().y = 4.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 2.25f + 20f;
			circs.get(start + 9).getPosition().y = 4.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 10).getPosition().x = 3.25f + 20f;
			circs.get(start + 10).getPosition().y = 4.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
			
		}
		else if (levelNum == 9) {
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 2f + 20f;
			squares.get(start).getPosition().y = 3f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 3f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());

			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 5f + 20f;
			squares.get(start + 3).getPosition().y = 3f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 6f + 20f;
			squares.get(start + 4).getPosition().y = 3f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 7f + 20f;
			squares.get(start + 5).getPosition().y = 3f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			squares.get(start + 6).carryingBlock = false;
			squares.get(start + 6).visible = true;
			squares.get(start + 6).getPosition().x = 8f + 20f;
			squares.get(start + 6).getPosition().y = 3f;
			squares.get(start + 6).getVelocity().x = -6.5f;
			squares.get(start + 6).setPosition(squares.get(start + 6).getPosition());
			
			squares.get(start + 7).carryingBlock = false;
			squares.get(start + 7).visible = true;
			squares.get(start + 7).getPosition().x = 9f + 20f;
			squares.get(start + 7).getPosition().y = 3f;
			squares.get(start + 7).getVelocity().x = -6.5f;
			squares.get(start + 7).setPosition(squares.get(start + 7).getPosition());
			
			squares.get(start + 8).carryingBlock = false;
			squares.get(start + 8).visible = true;
			squares.get(start + 8).getPosition().x = 10f + 20f;
			squares.get(start + 8).getPosition().y = 3f;
			squares.get(start + 8).getVelocity().x = -6.5f;
			squares.get(start + 8).setPosition(squares.get(start + 8).getPosition());

			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 11f + 20f;
			squares.get(start + 9).getPosition().y = 3f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			rects.get(start + 3).getPosition().x = 3f + 20f;
			rects.get(start + 3).getPosition().y = 1f;
			rects.get(start + 3).getVelocity().x = -6.5f;
			rects.get(start + 3).setPosition(rects.get(start + 3).getPosition());
			
			rects.get(start).getPosition().x = 3f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).getVelocity().x = -6.5f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
			rects.get(start + 2).getPosition().x = 4f + 20f;
			rects.get(start + 2).getPosition().y = 1f;
			rects.get(start + 2).getVelocity().x = -6.5f;
			rects.get(start + 2).setPosition(rects.get(start + 2).getPosition());
			
			rects.get(start + 1).getPosition().x = 5f + 20f;
			rects.get(start + 1).getPosition().y = 1f;
			rects.get(start + 1).getVelocity().x = -6.5f;
			rects.get(start + 1).setPosition(rects.get(start + 1).getPosition());
			
			rects.get(start + 4).getPosition().x = 6f + 20f;
			rects.get(start + 4).getPosition().y = 1f;
			rects.get(start + 4).getVelocity().x = -6.5f;
			rects.get(start + 4).setPosition(rects.get(start + 4).getPosition());
			
			rects.get(start + 5).getPosition().x = 7f + 20f;
			rects.get(start + 5).getPosition().y = 1f;
			rects.get(start + 5).getVelocity().x = -6.5f;
			rects.get(start + 5).setPosition(rects.get(start + 5).getPosition());
			
			rects.get(start + 6).getPosition().x = 8f + 20f;
			rects.get(start + 6).getPosition().y = 1f;
			rects.get(start + 6).getVelocity().x = -6.5f;
			rects.get(start + 6).setPosition(rects.get(start + 6).getPosition());
			
			rects.get(start + 7).getPosition().x = 9f + 20f;
			rects.get(start + 7).getPosition().y = 1f;
			rects.get(start + 7).getVelocity().x = -6.5f;
			rects.get(start + 7).setPosition(rects.get(start + 7).getPosition());
			
			rects.get(start + 8).getPosition().x = 10f + 20f;
			rects.get(start + 8).getPosition().y = 1f;
			rects.get(start + 8).getVelocity().x = -6.5f;
			rects.get(start + 8).setPosition(rects.get(start + 8).getPosition());

			rects.get(start + 9).getPosition().x = 11f + 20f;
			rects.get(start + 9).getPosition().y = 1f;
			rects.get(start + 9).getVelocity().x = -6.5f;
			rects.get(start + 9).setPosition(rects.get(start + 9).getPosition());
			
			obstacles.get(start).getPosition().x = 7f + 20f;
			obstacles.get(start).getPosition().y = 4f;
			obstacles.get(start).setPosition(obstacles.get(start).getPosition());
			
			obstacles.get(start+1).getPosition().x = 11f + 20f;
			obstacles.get(start+1).getPosition().y = 4f;
			obstacles.get(start+1).setPosition(obstacles.get(start+1).getPosition());
			
			circs.get(start + 9).getPosition().x = 7.25f + 20f;
			circs.get(start + 9).getPosition().y = 6.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 10).getPosition().x = 11.25f + 20f;
			circs.get(start + 10).getPosition().y = 6.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
			
		}
		else if (levelNum == 10) {
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 2f + 20f;
			squares.get(start).getPosition().y = 3f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 3f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());

			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 5f + 20f;
			squares.get(start + 3).getPosition().y = 3f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 6f + 20f;
			squares.get(start + 4).getPosition().y = 3f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 7f + 20f;
			squares.get(start + 5).getPosition().y = 3f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			squares.get(start + 6).carryingBlock = false;
			squares.get(start + 6).visible = true;
			squares.get(start + 6).getPosition().x = 8f + 20f;
			squares.get(start + 6).getPosition().y = 3f;
			squares.get(start + 6).getVelocity().x = -6.5f;
			squares.get(start + 6).setPosition(squares.get(start + 6).getPosition());
			
			squares.get(start + 7).carryingBlock = false;
			squares.get(start + 7).visible = true;
			squares.get(start + 7).getPosition().x = 9f + 20f;
			squares.get(start + 7).getPosition().y = 3f;
			squares.get(start + 7).getVelocity().x = -6.5f;
			squares.get(start + 7).setPosition(squares.get(start + 7).getPosition());
			
			squares.get(start + 8).carryingBlock = false;
			squares.get(start + 8).visible = true;
			squares.get(start + 8).getPosition().x = 10f + 20f;
			squares.get(start + 8).getPosition().y = 3f;
			squares.get(start + 8).getVelocity().x = -6.5f;
			squares.get(start + 8).setPosition(squares.get(start + 8).getPosition());

			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 11f + 20f;
			squares.get(start + 9).getPosition().y = 3f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			obstacles.get(start).getPosition().x = 7f + 20f;
			obstacles.get(start).getPosition().y = 4f;
			obstacles.get(start).setPosition(obstacles.get(start).getPosition());
			
			obstacles.get(start+1).getPosition().x = 11f + 20f;
			obstacles.get(start+1).getPosition().y = 4f;
			obstacles.get(start+1).setPosition(obstacles.get(start+1).getPosition());
			
			circs.get(start).getPosition().x = 5.25f + 20f;
			circs.get(start).getPosition().y = 4.25f;
			circs.get(start).getVelocity().x = -6.5f;
			circs.get(start).setPosition(circs.get(start).getPosition());
		
			circs.get(start + 1).getPosition().x = 4.25f + 20f;
			circs.get(start + 1).getPosition().y = 4.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());
			
			circs.get(start + 2).getPosition().x = 5.25f + 20f;
			circs.get(start + 2).getPosition().y = 4.25f;
			circs.get(start + 2).getVelocity().x = -6.5f;
			circs.get(start + 2).setPosition(circs.get(start + 2).getPosition());
			
			
			circs.get(start + 4).getPosition().x = 7.25f + 20f;
			circs.get(start + 4).getPosition().y = 6.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
			circs.get(start + 6).getPosition().x = 9.25f + 20f;
			circs.get(start + 6).getPosition().y = 4.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 8).getPosition().x = 11.25f + 20f;
			circs.get(start + 8).getPosition().y = 6.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 2.25f + 20f;
			circs.get(start + 9).getPosition().y = 4.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 10).getPosition().x = 3.25f + 20f;
			circs.get(start + 10).getPosition().y = 4.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
			
		}
		else if (levelNum == 11){
			//maybe remove this one
			
			rects.get(start).getPosition().x = 4f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
			obstacles.get(start+2).getPosition().x = 3f + 20f;
			obstacles.get(start+2).getPosition().y = 2f;
			obstacles.get(start+2).setPosition(obstacles.get(start+2).getPosition());
			
			obstacles.get(start+3).getPosition().x = 8f + 20f;
			obstacles.get(start+3).getPosition().y = 2f;
			obstacles.get(start+3).setPosition(obstacles.get(start+3).getPosition());
			
			
			obstacles.get(start+4).getPosition().x = 13f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
			
			circs.get(start + 6).getPosition().x = 5.5f + 20f;
			circs.get(start + 6).getPosition().y = 2.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 8).getPosition().x = 10.5f + 20f;
			circs.get(start + 8).getPosition().y = 2.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			
			circs.get(start + 9).getPosition().x = 15.25f + 20f;
			circs.get(start + 9).getPosition().y = 2.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 10).getPosition().x = 16.25f + 20f;
			circs.get(start + 10).getPosition().y = 2.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
			
			circs.get(start + 11).getPosition().x = 17.25f + 20f;
			circs.get(start + 11).getPosition().y = 2.25f;
			circs.get(start + 11).getVelocity().x = -6.5f;
			circs.get(start + 11).setPosition(circs.get(start + 11).getPosition());
			
			circs.get(start + 12).getPosition().x = 18.25f + 20f;
			circs.get(start + 12).getPosition().y = 2.25f;
			circs.get(start + 12).getVelocity().x = -6.5f;
			circs.get(start + 12).setPosition(circs.get(start + 12).getPosition());
		}
		else if (levelNum == 12){
			
			obstacles.get(start+2).getPosition().x = 3f + 20f;
			obstacles.get(start+2).getPosition().y = 2f;
			obstacles.get(start+2).setPosition(obstacles.get(start+2).getPosition());
			
			rects.get(start+3).getPosition().x = 8f + 20f;
			rects.get(start+3).getPosition().y = 1f;
			rects.get(start+3).setPosition(rects.get(start+3).getPosition());
			
			obstacles.get(start+4).getPosition().x = 13f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
			
			rects.get(start+2).getPosition().x = 18f + 20f;
			rects.get(start+2).getPosition().y = 1f;
			rects.get(start+2).setPosition(rects.get(start+2).getPosition());
		}
		else if (levelNum == 13){
			
			obstacles.get(start+2).getPosition().x = 3f + 20f;
			obstacles.get(start+2).getPosition().y = 2f;
			obstacles.get(start+2).setPosition(obstacles.get(start+2).getPosition());
			
			rects.get(start+3).getPosition().x = 8f + 20f;
			rects.get(start+3).getPosition().y = 1f;
			rects.get(start+3).setPosition(rects.get(start+3).getPosition());
			
			obstacles.get(start+4).getPosition().x = 14f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
			
			rects.get(start+2).getPosition().x = 9f + 20f;
			rects.get(start+2).getPosition().y = 1f;
			rects.get(start+2).setPosition(rects.get(start+2).getPosition());
			
			rects.get(start+1).getPosition().x = 18f + 20f;
			rects.get(start+1).getPosition().y = 1f;
			rects.get(start+1).setPosition(rects.get(start+1).getPosition());
		}
		else if (levelNum == 14){
			//maybe change this one
			
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 2f + 20f;
			squares.get(start).getPosition().y = 2f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 2f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 2f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());
			
			rects.get(start+3).getPosition().x = 5f + 20f;
			rects.get(start+3).getPosition().y = 1f;
			rects.get(start+3).setPosition(rects.get(start+3).getPosition());
			
			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 11f + 20f;
			squares.get(start + 3).getPosition().y = 2f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 12f + 20f;
			squares.get(start + 4).getPosition().y = 2f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 13f + 20f;
			squares.get(start + 5).getPosition().y = 2f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			circs.get(start + 6).getPosition().x = 2.25f + 20f;
			circs.get(start + 6).getPosition().y = 4.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 8).getPosition().x = 3.25f + 20f;
			circs.get(start + 8).getPosition().y = 4.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			
			circs.get(start + 9).getPosition().x = 4.25f + 20f;
			circs.get(start + 9).getPosition().y = 4.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 10).getPosition().x = 11.25f + 20f;
			circs.get(start + 10).getPosition().y = 4.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
			
			circs.get(start + 11).getPosition().x = 12.25f + 20f;
			circs.get(start + 11).getPosition().y = 4.25f;
			circs.get(start + 11).getVelocity().x = -6.5f;
			circs.get(start + 11).setPosition(circs.get(start + 11).getPosition());
			
			circs.get(start + 12).getPosition().x = 13.25f + 20f;
			circs.get(start + 12).getPosition().y = 4.25f;
			circs.get(start + 12).getVelocity().x = -6.5f;
			circs.get(start + 12).setPosition(circs.get(start + 12).getPosition());
			
			rects.get(start+2).getPosition().x = 14f + 20f;
			rects.get(start+2).getPosition().y = 1f;
			rects.get(start+2).setPosition(rects.get(start+2).getPosition());
			
			obstacles.get(start+4).getPosition().x = 18f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
		}
		else if (levelNum == 15){
			//maybe remove this one
			
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 2f + 20f;
			squares.get(start).getPosition().y = 2f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 2f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 2f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());
			
			rects.get(start+3).getPosition().x = 5f + 20f;
			rects.get(start+3).getPosition().y = 1f;
			rects.get(start+3).setPosition(rects.get(start+3).getPosition());
			
			rects.get(start+2).getPosition().x = 6f + 20f;
			rects.get(start+2).getPosition().y = 1f;
			rects.get(start+2).setPosition(rects.get(start+2).getPosition());
			
			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 7f + 20f;
			squares.get(start + 3).getPosition().y = 2f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 8f + 20f;
			squares.get(start + 4).getPosition().y = 2f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 9f + 20f;
			squares.get(start + 5).getPosition().y = 2f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			rects.get(start+1).getPosition().x = 10f + 20f;
			rects.get(start+1).getPosition().y = 1f;
			rects.get(start+1).setPosition(rects.get(start+1).getPosition());
			
			rects.get(start).getPosition().x = 11f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
			obstacles.get(start+4).getPosition().x = 16f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
			
			circs.get(start + 6).getPosition().x = 2.25f + 20f;
			circs.get(start + 6).getPosition().y = 3.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 8).getPosition().x = 3.25f + 20f;
			circs.get(start + 8).getPosition().y = 3.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			
			circs.get(start + 9).getPosition().x = 4.25f + 20f;
			circs.get(start + 9).getPosition().y = 3.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 10).getPosition().x = 7.25f + 20f;
			circs.get(start + 10).getPosition().y = 3.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
			
			circs.get(start + 11).getPosition().x = 8.25f + 20f;
			circs.get(start + 11).getPosition().y = 3.25f;
			circs.get(start + 11).getVelocity().x = -6.5f;
			circs.get(start + 11).setPosition(circs.get(start + 11).getPosition());
			
			circs.get(start + 12).getPosition().x = 9.25f + 20f;
			circs.get(start + 12).getPosition().y = 3.25f;
			circs.get(start + 12).getVelocity().x = -6.5f;
			circs.get(start + 12).setPosition(circs.get(start + 12).getPosition());
		}
		else if (levelNum == 16){
			
			obstacles.get(start+4).getPosition().x = 2f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());

			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 3f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());
			
			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 5f + 20f;
			squares.get(start + 3).getPosition().y = 3f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
			
			squares.get(start).carryingBlock = false;
			squares.get(start).visible = true;
			squares.get(start).getPosition().x = 6f + 20f;
			squares.get(start).getPosition().y = 3f;
			squares.get(start).getVelocity().x = -6.5f;
			squares.get(start).setPosition(squares.get(start).getPosition());
			
			
			squares.get(start + 6).carryingBlock = false;
			squares.get(start + 6).visible = true;
			squares.get(start + 6).getPosition().x = 7f + 20f;
			squares.get(start + 6).getPosition().y = 3f;
			squares.get(start + 6).getVelocity().x = -6.5f;
			squares.get(start + 6).setPosition(squares.get(start + 6).getPosition());
			
			obstacles.get(start+5).getPosition().x = 8f + 20f;
			obstacles.get(start+5).getPosition().y = 2f;
			obstacles.get(start+5).setPosition(obstacles.get(start+5).getPosition());
			
			obstacles.get(start+6).getPosition().x = 9f + 20f;
			obstacles.get(start+6).getPosition().y = 2f;
			obstacles.get(start+6).setPosition(obstacles.get(start+6).getPosition());
			
			circs.get(start + 8).getPosition().x = 8.25f + 20f;
			circs.get(start + 8).getPosition().y = 5.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 9.25f + 20f;
			circs.get(start + 9).getPosition().y = 5.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 10f + 20f;
			squares.get(start + 4).getPosition().y = 3f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 11f + 20f;
			squares.get(start + 5).getPosition().y = 3f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			squares.get(start + 7).carryingBlock = false;
			squares.get(start + 7).visible = true;
			squares.get(start + 7).getPosition().x = 12f + 20f;
			squares.get(start + 7).getPosition().y = 3f;
			squares.get(start + 7).getVelocity().x = -6.5f;
			squares.get(start + 7).setPosition(squares.get(start + 7).getPosition());
			
			obstacles.get(start+7).getPosition().x = 13f + 20f;
			obstacles.get(start+7).getPosition().y = 2f;
			obstacles.get(start+7).setPosition(obstacles.get(start+7).getPosition());
			
			obstacles.get(start+8).getPosition().x = 18f + 20f;
			obstacles.get(start+8).getPosition().y = 2f;
			obstacles.get(start+8).setPosition(obstacles.get(start+8).getPosition());
		
		}
		else if (levelNum == 17){
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 3f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());
			
			squares.get(start + 3).carryingBlock = false;
			squares.get(start + 3).visible = true;
			squares.get(start + 3).getPosition().x = 5f + 20f;
			squares.get(start + 3).getPosition().y = 3f;
			squares.get(start + 3).getVelocity().x = -6.5f;
			squares.get(start + 3).setPosition(squares.get(start + 3).getPosition());
	
			
			obstacles.get(start+5).getPosition().x = 6f + 20f;
			obstacles.get(start+5).getPosition().y = 2f;
			obstacles.get(start+5).setPosition(obstacles.get(start+5).getPosition());
			
			obstacles.get(start+6).getPosition().x = 7f + 20f;
			obstacles.get(start+6).getPosition().y = 2f;
			obstacles.get(start+6).setPosition(obstacles.get(start+6).getPosition());
			
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 8f + 20f;
			squares.get(start + 4).getPosition().y = 4f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 9f + 20f;
			squares.get(start + 5).getPosition().y = 4f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			squares.get(start + 7).carryingBlock = false;
			squares.get(start + 7).visible = true;
			squares.get(start + 7).getPosition().x = 10f + 20f;
			squares.get(start + 7).getPosition().y = 4f;
			squares.get(start + 7).getVelocity().x = -6.5f;
			squares.get(start + 7).setPosition(squares.get(start + 7).getPosition());
			
			obstacles.get(start+7).getPosition().x = 11f + 20f;
			obstacles.get(start+7).getPosition().y = 2f;
			obstacles.get(start+7).setPosition(obstacles.get(start+7).getPosition());
			
			obstacles.get(start+8).getPosition().x = 12f + 20f;
			obstacles.get(start+8).getPosition().y = 2f;
			obstacles.get(start+8).setPosition(obstacles.get(start+8).getPosition());
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 13f + 20f;
			squares.get(start + 9).getPosition().y = 5f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			squares.get(start + 8).carryingBlock = false;
			squares.get(start + 8).visible = true;
			squares.get(start + 8).getPosition().x = 14f + 20f;
			squares.get(start + 8).getPosition().y = 5f;
			squares.get(start + 8).getVelocity().x = -6.5f;
			squares.get(start + 8).setPosition(squares.get(start + 8).getPosition());
			
			squares.get(start + 10).carryingBlock = false;
			squares.get(start + 10).visible = true;
			squares.get(start + 10).getPosition().x = 15f + 20f;
			squares.get(start + 10).getPosition().y = 5f;
			squares.get(start + 10).getVelocity().x = -6.5f;
			squares.get(start + 10).setPosition(squares.get(start + 10).getPosition());
			
			obstacles.get(start+4).getPosition().x = 16f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
			
			obstacles.get(start+3).getPosition().x = 17f + 20f;
			obstacles.get(start+3).getPosition().y = 2f;
			obstacles.get(start+3).setPosition(obstacles.get(start+3).getPosition());
			
			circs.get(start + 8).getPosition().x = 6.75f + 20f;
			circs.get(start + 8).getPosition().y = 6.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 11.75f + 20f;
			circs.get(start + 9).getPosition().y = 7.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 10).getPosition().x = 16.75f + 20f;
			circs.get(start + 10).getPosition().y = 8.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
			
			
		
		}
		else if (levelNum == 18){
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 3f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());
			
	
			rects.get(start+5).getPosition().x = 5f + 20f;
			rects.get(start+5).getPosition().y = 1f;
			rects.get(start+5).setPosition(rects.get(start+5).getPosition());
			
			rects.get(start+6).getPosition().x = 6f + 20f;
			rects.get(start+6).getPosition().y = 1f;
			rects.get(start+6).setPosition(rects.get(start+6).getPosition());
			
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 7f + 20f;
			squares.get(start + 4).getPosition().y = 4f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 8f + 20f;
			squares.get(start + 5).getPosition().y = 4f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			
			rects.get(start+7).getPosition().x = 9f + 20f;
			rects.get(start+7).getPosition().y = 1f;
			rects.get(start+7).setPosition(rects.get(start+7).getPosition());
			
			rects.get(start+8).getPosition().x = 10f + 20f;
			rects.get(start+8).getPosition().y = 1f;
			rects.get(start+8).setPosition(rects.get(start+8).getPosition());
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 11f + 20f;
			squares.get(start + 9).getPosition().y = 5f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			squares.get(start + 8).carryingBlock = false;
			squares.get(start + 8).visible = true;
			squares.get(start + 8).getPosition().x = 12f + 20f;
			squares.get(start + 8).getPosition().y = 5f;
			squares.get(start + 8).getVelocity().x = -6.5f;
			squares.get(start + 8).setPosition(squares.get(start + 8).getPosition());
			
			
			rects.get(start+4).getPosition().x = 13f + 20f;
			rects.get(start+4).getPosition().y = 1f;
			rects.get(start+4).setPosition(rects.get(start+4).getPosition());
			
			rects.get(start+3).getPosition().x = 14f + 20f;
			rects.get(start+3).getPosition().y = 1f;
			rects.get(start+3).setPosition(rects.get(start+3).getPosition());
			
			obstacles.get(start).getPosition().x = 19f + 20f;
			obstacles.get(start).getPosition().y = 2f;
			obstacles.get(start).setPosition(obstacles.get(start).getPosition());
			
			circs.get(start + 8).getPosition().x = 5.75f + 20f;
			circs.get(start + 8).getPosition().y = 6.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 9.75f + 20f;
			circs.get(start + 9).getPosition().y = 7.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 10).getPosition().x = 13.75f + 20f;
			circs.get(start + 10).getPosition().y = 8.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
		
		}
		else if (levelNum == 19){
			
			rects.get(start+3).getPosition().x = 2f + 20f;
			rects.get(start+3).getPosition().y = 1f;
			rects.get(start+3).setPosition(rects.get(start+3).getPosition());
			
			rects.get(start+1).getPosition().x = 3f + 20f;
			rects.get(start+1).getPosition().y = 1f;
			rects.get(start+1).setPosition(rects.get(start+1).getPosition());
			
			rects.get(start+2).getPosition().x = 4f + 20f;
			rects.get(start+2).getPosition().y = 1f;
			rects.get(start+2).setPosition(rects.get(start+2).getPosition());
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			squares.get(start + 2).carryingBlock = false;
			squares.get(start + 2).visible = true;
			squares.get(start + 2).getPosition().x = 4f + 20f;
			squares.get(start + 2).getPosition().y = 3f;
			squares.get(start + 2).getVelocity().x = -6.5f;
			squares.get(start + 2).setPosition(squares.get(start + 2).getPosition());
			
	
			rects.get(start+5).getPosition().x = 5f + 20f;
			rects.get(start+5).getPosition().y = 1f;
			rects.get(start+5).setPosition(rects.get(start+5).getPosition());
			
			rects.get(start+6).getPosition().x = 6f + 20f;
			rects.get(start+6).getPosition().y = 1f;
			rects.get(start+6).setPosition(rects.get(start+6).getPosition());
			
			rects.get(start+9).getPosition().x = 7f + 20f;	
			rects.get(start+9).getPosition().y = 1f;
			rects.get(start+9).setPosition(rects.get(start+9).getPosition());
			
			rects.get(start+10).getPosition().x = 8f + 20f;
			rects.get(start+10).getPosition().y = 1f;
			rects.get(start+10).setPosition(rects.get(start+10).getPosition());
			
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 7f + 20f;
			squares.get(start + 4).getPosition().y = 4f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 5).carryingBlock = false;
			squares.get(start + 5).visible = true;
			squares.get(start + 5).getPosition().x = 8f + 20f;
			squares.get(start + 5).getPosition().y = 4f;
			squares.get(start + 5).getVelocity().x = -6.5f;
			squares.get(start + 5).setPosition(squares.get(start + 5).getPosition());
			
			
			rects.get(start+7).getPosition().x = 9f + 20f;	
			rects.get(start+7).getPosition().y = 1f;
			rects.get(start+7).setPosition(rects.get(start+7).getPosition());
			
			rects.get(start+8).getPosition().x = 10f + 20f;
			rects.get(start+8).getPosition().y = 1f;
			rects.get(start+8).setPosition(rects.get(start+8).getPosition());
			
			rects.get(start+11).getPosition().x =11f + 20f;
			rects.get(start+11).getPosition().y = 1f;
			rects.get(start+11).setPosition(rects.get(start+11).getPosition());
			
			rects.get(start+12).getPosition().x = 12f + 20f;
			rects.get(start+12).getPosition().y = 1f;
			rects.get(start+12).setPosition(rects.get(start+12).getPosition());
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 11f + 20f;
			squares.get(start + 9).getPosition().y = 3f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			squares.get(start + 10).carryingBlock = false;
			squares.get(start + 10).visible = true;
			squares.get(start + 10).getPosition().x = 12f + 20f;
			squares.get(start + 10).getPosition().y = 3f;
			squares.get(start + 10).getVelocity().x = -6.5f;
			squares.get(start + 10).setPosition(squares.get(start + 10).getPosition());
			
			
			rects.get(start+4).getPosition().x = 13f + 20f;
			rects.get(start+4).getPosition().y = 1f;
			rects.get(start+4).setPosition(rects.get(start+4).getPosition());
	
			
			obstacles.get(start).getPosition().x = 19f + 20f;
			obstacles.get(start).getPosition().y = 2f;
			obstacles.get(start).setPosition(obstacles.get(start).getPosition());
			
			circs.get(start + 8).getPosition().x = 5.75f + 20f;
			circs.get(start + 8).getPosition().y = 6.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 9.75f + 20f;
			circs.get(start + 9).getPosition().y = 5.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 7).getPosition().x = 13.75f + 20f;
			circs.get(start + 7).getPosition().y = 4.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
			
		
		}
		else if (levelNum == 20){
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 6f + 20f;
			squares.get(start + 4).getPosition().y = 4f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 9f + 20f;
			squares.get(start + 9).getPosition().y = 3f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			squares.get(start + 10).carryingBlock = false;
			squares.get(start + 10).visible = true;
			squares.get(start + 10).getPosition().x = 12f + 20f;
			squares.get(start + 10).getPosition().y = 4f;
			squares.get(start + 10).getVelocity().x = -6.5f;
			squares.get(start + 10).setPosition(squares.get(start + 10).getPosition());
			
			squares.get(start + 11).carryingBlock = false;
			squares.get(start + 11).visible = true;
			squares.get(start + 11).getPosition().x = 15f + 20f;
			squares.get(start + 11).getPosition().y = 3f;
			squares.get(start + 11).getVelocity().x = -6.5f;
			squares.get(start + 11).setPosition(squares.get(start + 11).getPosition());
			
			circs.get(start + 8).getPosition().x = 3.25f + 20f;
			circs.get(start + 8).getPosition().y = 4.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 6.25f + 20f;
			circs.get(start + 9).getPosition().y = 5.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 7).getPosition().x = 9.25f + 20f;
			circs.get(start + 7).getPosition().y = 4.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
			
			circs.get(start + 6).getPosition().x = 12.25f + 20f;
			circs.get(start + 6).getPosition().y = 5.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 5).getPosition().x = 15.25f + 20f;
			circs.get(start + 5).getPosition().y = 4.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());
			
		}
		else if (levelNum == 21){
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
			
			
			
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 7f + 20f;
			squares.get(start + 4).getPosition().y = 4f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 11f + 20f;
			squares.get(start + 9).getPosition().y = 5f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			squares.get(start + 10).carryingBlock = false;
			squares.get(start + 10).visible = true;
			squares.get(start + 10).getPosition().x = 13f + 20f;
			squares.get(start + 10).getPosition().y = 4f;
			squares.get(start + 10).getVelocity().x = -6.5f;
			squares.get(start + 10).setPosition(squares.get(start + 10).getPosition());
			
			squares.get(start + 11).carryingBlock = false;
			squares.get(start + 11).visible = true;
			squares.get(start + 11).getPosition().x = 14f + 20f;
			squares.get(start + 11).getPosition().y = 4f;
			squares.get(start + 11).getVelocity().x = -6.5f;
			squares.get(start + 11).setPosition(squares.get(start + 11).getPosition());
			
			squares.get(start + 12).carryingBlock = false;
			squares.get(start + 12).visible = true;
			squares.get(start + 12).getPosition().x = 15f + 20f;
			squares.get(start + 12).getPosition().y = 4f;
			squares.get(start + 12).getVelocity().x = -6.5f;
			squares.get(start + 12).setPosition(squares.get(start + 12).getPosition());
			
			circs.get(start + 8).getPosition().x = 3.25f + 20f;
			circs.get(start + 8).getPosition().y = 4.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 7.25f + 20f;
			circs.get(start + 9).getPosition().y = 5.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 7).getPosition().x = 11.25f + 20f;
			circs.get(start + 7).getPosition().y = 6.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
			
			circs.get(start + 6).getPosition().x = 13.25f + 20f;
			circs.get(start + 6).getPosition().y = 5.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 5).getPosition().x = 14.25f + 20f;
			circs.get(start + 5).getPosition().y = 5.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());
			
			circs.get(start + 4).getPosition().x = 15.25f + 20f;
			circs.get(start + 4).getPosition().y = 5.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
		}
		else if (levelNum == 22){
			
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
				
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 4f + 20f;
			squares.get(start + 4).getPosition().y = 3f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 5f + 20f;
			squares.get(start + 9).getPosition().y = 3f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			squares.get(start + 10).carryingBlock = false;
			squares.get(start + 10).visible = true;
			squares.get(start + 10).getPosition().x = 8f + 20f;
			squares.get(start + 10).getPosition().y = 5f;
			squares.get(start + 10).getVelocity().x = -6.5f;
			squares.get(start + 10).setPosition(squares.get(start + 10).getPosition());
			
			squares.get(start + 11).carryingBlock = false;
			squares.get(start + 11).visible = true;
			squares.get(start + 11).getPosition().x = 9f + 20f;
			squares.get(start + 11).getPosition().y = 5f;
			squares.get(start + 11).getVelocity().x = -6.5f;
			squares.get(start + 11).setPosition(squares.get(start + 11).getPosition());
			
			squares.get(start + 12).carryingBlock = false;
			squares.get(start + 12).visible = true;
			squares.get(start + 12).getPosition().x = 10f + 20f;
			squares.get(start + 12).getPosition().y = 5f;
			squares.get(start + 12).getVelocity().x = -6.5f;
			squares.get(start + 12).setPosition(squares.get(start + 12).getPosition());
			
			
			squares.get(start + 16).carryingBlock = false;
			squares.get(start + 16).visible = true;
			squares.get(start + 16).getPosition().x = 14f + 20f;
			squares.get(start + 16).getPosition().y = 5f;
			squares.get(start + 16).getVelocity().x = -6.5f;
			squares.get(start + 16).setPosition(squares.get(start + 16).getPosition());


			squares.get(start + 17).carryingBlock = false;
			squares.get(start + 17).visible = true;
			squares.get(start + 17).getPosition().x = 15f + 20f;
			squares.get(start + 17).getPosition().y = 5f;
			squares.get(start + 17).getVelocity().x = -6.5f;
			squares.get(start + 17).setPosition(squares.get(start + 17).getPosition());
			
			squares.get(start + 18).carryingBlock = false;
			squares.get(start + 18).visible = true;
			squares.get(start + 18).getPosition().x = 16f + 20f;
			squares.get(start + 18).getPosition().y = 5f;
			squares.get(start + 18).getVelocity().x = -6.5f;
			squares.get(start + 18).setPosition(squares.get(start + 18).getPosition());
			
			obstacles.get(start+4).getPosition().x = 10f + 20f;
			obstacles.get(start+4).getPosition().y = 2f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
			
			obstacles.get(start+3).getPosition().x = 14f + 20f;
			obstacles.get(start+3).getPosition().y = 2f;
			obstacles.get(start+3).setPosition(obstacles.get(start+3).getPosition());
			
			circs.get(start + 8).getPosition().x = 8.25f + 20f;
			circs.get(start + 8).getPosition().y = 6.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 9.25f + 20f;
			circs.get(start + 9).getPosition().y = 6.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 7).getPosition().x = 10.25f + 20f;
			circs.get(start + 7).getPosition().y = 6.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
			
			circs.get(start + 6).getPosition().x = 14.25f + 20f;
			circs.get(start + 6).getPosition().y = 6.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 5).getPosition().x = 15.25f + 20f;
			circs.get(start + 5).getPosition().y = 6.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());
			
			circs.get(start + 4).getPosition().x = 16.25f + 20f;
			circs.get(start + 4).getPosition().y = 6.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
		}
		
		else if (levelNum == 23){
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
				
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 4f + 20f;
			squares.get(start + 4).getPosition().y = 3f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 5f + 20f;
			squares.get(start + 9).getPosition().y = 3f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			squares.get(start + 10).carryingBlock = false;
			squares.get(start + 10).visible = true;
			squares.get(start + 10).getPosition().x = 6f + 20f;
			squares.get(start + 10).getPosition().y = 3f;
			squares.get(start + 10).getVelocity().x = -6.5f;
			squares.get(start + 10).setPosition(squares.get(start + 10).getPosition());
			
			squares.get(start + 11).carryingBlock = false;
			squares.get(start + 11).visible = true;
			squares.get(start + 11).getPosition().x = 7f + 20f;
			squares.get(start + 11).getPosition().y = 3f;
			squares.get(start + 11).getVelocity().x = -6.5f;
			squares.get(start + 11).setPosition(squares.get(start + 11).getPosition());
			
			squares.get(start + 12).carryingBlock = false;
			squares.get(start + 12).visible = true;
			squares.get(start + 12).getPosition().x = 10f + 20f;
			squares.get(start + 12).getPosition().y = 5f;
			squares.get(start + 12).getVelocity().x = -6.5f;
			squares.get(start + 12).setPosition(squares.get(start + 12).getPosition());
			
			squares.get(start + 13).carryingBlock = false;
			squares.get(start + 13).visible = true;
			squares.get(start + 13).getPosition().x = 11f + 20f;
			squares.get(start + 13).getPosition().y = 5f;
			squares.get(start + 13).getVelocity().x = -6.5f;
			squares.get(start + 13).setPosition(squares.get(start + 13).getPosition());

			squares.get(start + 14).carryingBlock = false;
			squares.get(start + 14).visible = true;
			squares.get(start + 14).getPosition().x = 12f + 20f;
			squares.get(start + 14).getPosition().y = 5f;
			squares.get(start + 14).getVelocity().x = -6.5f;
			squares.get(start + 14).setPosition(squares.get(start + 14).getPosition());
			
			squares.get(start + 15).carryingBlock = false;
			squares.get(start + 15).visible = true;
			squares.get(start + 15).getPosition().x = 13f + 20f;
			squares.get(start + 15).getPosition().y = 5f;
			squares.get(start + 15).getVelocity().x = -6.5f;
			squares.get(start + 15).setPosition(squares.get(start + 15).getPosition());
			
			squares.get(start + 16).carryingBlock = false;
			squares.get(start + 16).visible = true;
			squares.get(start + 16).getPosition().x = 14f + 20f;
			squares.get(start + 16).getPosition().y = 5f;
			squares.get(start + 16).getVelocity().x = -6.5f;
			squares.get(start + 16).setPosition(squares.get(start + 16).getPosition());


			squares.get(start + 17).carryingBlock = false;
			squares.get(start + 17).visible = true;
			squares.get(start + 17).getPosition().x = 15f + 20f;
			squares.get(start + 17).getPosition().y = 5f;
			squares.get(start + 17).getVelocity().x = -6.5f;
			squares.get(start + 17).setPosition(squares.get(start + 17).getPosition());
			
			squares.get(start + 18).carryingBlock = false;
			squares.get(start + 18).visible = true;
			squares.get(start + 18).getPosition().x = 16f + 20f;
			squares.get(start + 18).getPosition().y = 5f;
			squares.get(start + 18).getVelocity().x = -6.5f;
			squares.get(start + 18).setPosition(squares.get(start + 18).getPosition());
			
			squares.get(start + 19).carryingBlock = false;
			squares.get(start + 19).visible = true;
			squares.get(start + 19).getPosition().x = 17f + 20f;
			squares.get(start + 19).getPosition().y = 5f;
			squares.get(start + 19).getVelocity().x = -6.5f;
			squares.get(start + 19).setPosition(squares.get(start + 19).getPosition());
			
			obstacles.get(start+4).getPosition().x = 5f + 20f;
			obstacles.get(start+4).getPosition().y = 4f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
			
			obstacles.get(start+3).getPosition().x = 12f + 20f;
			obstacles.get(start+3).getPosition().y = 6f;
			obstacles.get(start+3).setPosition(obstacles.get(start+3).getPosition());
			
			obstacles.get(start+5).getPosition().x = 16f + 20f;
			obstacles.get(start+5).getPosition().y = 6f;
			obstacles.get(start+5).setPosition(obstacles.get(start+5).getPosition());
			
			circs.get(start + 8).getPosition().x = 3.75f + 20f;
			circs.get(start + 8).getPosition().y = 4.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 6.75f + 20f;
			circs.get(start + 9).getPosition().y = 4.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 7).getPosition().x = 10.75f + 20f;
			circs.get(start + 7).getPosition().y = 6.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
			
			circs.get(start + 6).getPosition().x = 14.25f + 20f;
			circs.get(start + 6).getPosition().y = 6.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			circs.get(start + 5).getPosition().x = 18.25f + 20f;
			circs.get(start + 5).getPosition().y = 6.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());
			
		}
		
		else if (levelNum == 24){
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
				
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 4f + 20f;
			squares.get(start + 4).getPosition().y = 3f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 5f + 20f;
			squares.get(start + 9).getPosition().y = 3f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			squares.get(start + 10).carryingBlock = false;
			squares.get(start + 10).visible = true;
			squares.get(start + 10).getPosition().x = 6f + 20f;
			squares.get(start + 10).getPosition().y = 3f;
			squares.get(start + 10).getVelocity().x = -6.5f;
			squares.get(start + 10).setPosition(squares.get(start + 10).getPosition());
			
			squares.get(start + 11).carryingBlock = false;
			squares.get(start + 11).visible = true;
			squares.get(start + 11).getPosition().x = 7f + 20f;
			squares.get(start + 11).getPosition().y = 3f;
			squares.get(start + 11).getVelocity().x = -6.5f;
			squares.get(start + 11).setPosition(squares.get(start + 11).getPosition());
			
			squares.get(start + 12).carryingBlock = false;
			squares.get(start + 12).visible = true;
			squares.get(start + 12).getPosition().x = 8f + 20f;
			squares.get(start + 12).getPosition().y = 3f;
			squares.get(start + 12).getVelocity().x = -6.5f;
			squares.get(start + 12).setPosition(squares.get(start + 12).getPosition());
			
			squares.get(start + 13).carryingBlock = false;
			squares.get(start + 13).visible = true;
			squares.get(start + 13).getPosition().x = 9f + 20f;
			squares.get(start + 13).getPosition().y = 3f;
			squares.get(start + 13).getVelocity().x = -6.5f;
			squares.get(start + 13).setPosition(squares.get(start + 13).getPosition());

			squares.get(start + 14).carryingBlock = false;
			squares.get(start + 14).visible = true;
			squares.get(start + 14).getPosition().x = 10f + 20f;
			squares.get(start + 14).getPosition().y = 3f;
			squares.get(start + 14).getVelocity().x = -6.5f;
			squares.get(start + 14).setPosition(squares.get(start + 14).getPosition());
			
			squares.get(start + 15).carryingBlock = false;
			squares.get(start + 15).visible = true;
			squares.get(start + 15).getPosition().x = 11f + 20f;
			squares.get(start + 15).getPosition().y = 3f;
			squares.get(start + 15).getVelocity().x = -6.5f;
			squares.get(start + 15).setPosition(squares.get(start + 15).getPosition());
			
			squares.get(start + 16).carryingBlock = false;
			squares.get(start + 16).visible = true;
			squares.get(start + 16).getPosition().x = 12f + 20f;
			squares.get(start + 16).getPosition().y = 3f;
			squares.get(start + 16).getVelocity().x = -6.5f;
			squares.get(start + 16).setPosition(squares.get(start + 16).getPosition());


			squares.get(start + 17).carryingBlock = false;
			squares.get(start + 17).visible = true;
			squares.get(start + 17).getPosition().x = 13f + 20f;
			squares.get(start + 17).getPosition().y = 3f;
			squares.get(start + 17).getVelocity().x = -6.5f;
			squares.get(start + 17).setPosition(squares.get(start + 17).getPosition());
			
			squares.get(start + 18).carryingBlock = false;
			squares.get(start + 18).visible = true;
			squares.get(start + 18).getPosition().x = 14f + 20f;
			squares.get(start + 18).getPosition().y = 3f;
			squares.get(start + 18).getVelocity().x = -6.5f;
			squares.get(start + 18).setPosition(squares.get(start + 18).getPosition());
			
			squares.get(start + 19).carryingBlock = false;
			squares.get(start + 19).visible = true;
			squares.get(start + 19).getPosition().x = 15f + 20f;
			squares.get(start + 19).getPosition().y = 3f;
			squares.get(start + 19).getVelocity().x = -6.5f;
			squares.get(start + 19).setPosition(squares.get(start + 19).getPosition());
			
			rects.get(start + 3).getPosition().x = 3f + 20f;
			rects.get(start + 3).getPosition().y = 1f;
			rects.get(start + 3).getVelocity().x = -6.5f;
			rects.get(start + 3).setPosition(rects.get(start + 3).getPosition());
			
			rects.get(start).getPosition().x = 12f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).getVelocity().x = -6.5f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
			rects.get(start + 2).getPosition().x = 4f + 20f;
			rects.get(start + 2).getPosition().y = 1f;
			rects.get(start + 2).getVelocity().x = -6.5f;
			rects.get(start + 2).setPosition(rects.get(start + 2).getPosition());
			
			rects.get(start + 1).getPosition().x = 5f + 20f;
			rects.get(start + 1).getPosition().y = 1f;
			rects.get(start + 1).getVelocity().x = -6.5f;
			rects.get(start + 1).setPosition(rects.get(start + 1).getPosition());
			
			rects.get(start + 4).getPosition().x = 6f + 20f;
			rects.get(start + 4).getPosition().y = 1f;
			rects.get(start + 4).getVelocity().x = -6.5f;
			rects.get(start + 4).setPosition(rects.get(start + 4).getPosition());
			
			rects.get(start + 5).getPosition().x = 7f + 20f;
			rects.get(start + 5).getPosition().y = 1f;
			rects.get(start + 5).getVelocity().x = -6.5f;
			rects.get(start + 5).setPosition(rects.get(start + 5).getPosition());
			
			rects.get(start + 6).getPosition().x = 8f + 20f;
			rects.get(start + 6).getPosition().y = 1f;
			rects.get(start + 6).getVelocity().x = -6.5f;
			rects.get(start + 6).setPosition(rects.get(start + 6).getPosition());
			
			rects.get(start + 7).getPosition().x = 9f + 20f;
			rects.get(start + 7).getPosition().y = 1f;
			rects.get(start + 7).getVelocity().x = -6.5f;
			rects.get(start + 7).setPosition(rects.get(start + 7).getPosition());
			
			rects.get(start + 8).getPosition().x = 10f + 20f;
			rects.get(start + 8).getPosition().y = 1f;
			rects.get(start + 8).getVelocity().x = -6.5f;
			rects.get(start + 8).setPosition(rects.get(start + 8).getPosition());

			rects.get(start + 9).getPosition().x = 11f + 20f;
			rects.get(start + 9).getPosition().y = 1f;
			rects.get(start + 9).getVelocity().x = -6.5f;
			rects.get(start + 9).setPosition(rects.get(start + 9).getPosition());
			
			rects.get(start + 10).getPosition().x = 12f + 20f;
			rects.get(start + 10).getPosition().y = 1f;
			rects.get(start + 10).getVelocity().x = -6.5f;
			rects.get(start + 10).setPosition(rects.get(start + 10).getPosition());
			
			rects.get(start + 11).getPosition().x = 13f + 20f;
			rects.get(start + 11).getPosition().y = 1f;
			rects.get(start + 11).getVelocity().x = -6.5f;
			rects.get(start + 11).setPosition(rects.get(start + 11).getPosition());
			
			rects.get(start + 12).getPosition().x = 14f + 20f;
			rects.get(start + 12).getPosition().y = 1f;
			rects.get(start + 12).getVelocity().x = -6.5f;
			rects.get(start + 12).setPosition(rects.get(start + 12).getPosition());
			
			rects.get(start + 13).getPosition().x = 15f + 20f;
			rects.get(start + 13).getPosition().y = 1f;
			rects.get(start + 13).getVelocity().x = -6.5f;
			rects.get(start + 13).setPosition(rects.get(start + 13).getPosition());
			
			obstacles.get(start+4).getPosition().x = 5f + 20f;
			obstacles.get(start+4).getPosition().y = 4f;
			obstacles.get(start+4).setPosition(obstacles.get(start+4).getPosition());
			
			obstacles.get(start+3).getPosition().x = 9f + 20f;
			obstacles.get(start+3).getPosition().y = 4f;
			obstacles.get(start+3).setPosition(obstacles.get(start+3).getPosition());
			
			obstacles.get(start+5).getPosition().x = 13f + 20f;
			obstacles.get(start+5).getPosition().y = 4f;
			obstacles.get(start+5).setPosition(obstacles.get(start+5).getPosition());
			
			circs.get(start + 8).getPosition().x = 3.75f + 20f;
			circs.get(start + 8).getPosition().y = 4.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 7.25f + 20f;
			circs.get(start + 9).getPosition().y = 4.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 7).getPosition().x = 11.25f + 20f;
			circs.get(start + 7).getPosition().y = 4.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
			
			circs.get(start + 6).getPosition().x = 14.75f + 20f;
			circs.get(start + 6).getPosition().y = 4.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
		}
		else if (levelNum == 25){
			squares.get(start + 1).carryingBlock = false;
			squares.get(start + 1).visible = true;
			squares.get(start + 1).getPosition().x = 3f + 20f;
			squares.get(start + 1).getPosition().y = 3f;
			squares.get(start + 1).getVelocity().x = -6.5f;
			squares.get(start + 1).setPosition(squares.get(start + 1).getPosition());
				
			squares.get(start + 4).carryingBlock = false;
			squares.get(start + 4).visible = true;
			squares.get(start + 4).getPosition().x = 4f + 20f;
			squares.get(start + 4).getPosition().y = 3f;
			squares.get(start + 4).getVelocity().x = -6.5f;
			squares.get(start + 4).setPosition(squares.get(start + 4).getPosition());
			
			squares.get(start + 9).carryingBlock = false;
			squares.get(start + 9).visible = true;
			squares.get(start + 9).getPosition().x = 5f + 20f;
			squares.get(start + 9).getPosition().y = 3f;
			squares.get(start + 9).getVelocity().x = -6.5f;
			squares.get(start + 9).setPosition(squares.get(start + 9).getPosition());
			
			
			squares.get(start + 12).carryingBlock = false;
			squares.get(start + 12).visible = true;
			squares.get(start + 12).getPosition().x = 8f + 20f;
			squares.get(start + 12).getPosition().y = 3f;
			squares.get(start + 12).getVelocity().x = -6.5f;
			squares.get(start + 12).setPosition(squares.get(start + 12).getPosition());
			
			squares.get(start + 13).carryingBlock = false;
			squares.get(start + 13).visible = true;
			squares.get(start + 13).getPosition().x = 9f + 20f;
			squares.get(start + 13).getPosition().y = 3f;
			squares.get(start + 13).getVelocity().x = -6.5f;
			squares.get(start + 13).setPosition(squares.get(start + 13).getPosition());

			squares.get(start + 14).carryingBlock = false;
			squares.get(start + 14).visible = true;
			squares.get(start + 14).getPosition().x = 10f + 20f;
			squares.get(start + 14).getPosition().y = 3f;
			squares.get(start + 14).getVelocity().x = -6.5f;
			squares.get(start + 14).setPosition(squares.get(start + 14).getPosition());
			
			squares.get(start + 17).carryingBlock = false;
			squares.get(start + 17).visible = true;
			squares.get(start + 17).getPosition().x = 13f + 20f;
			squares.get(start + 17).getPosition().y = 3f;
			squares.get(start + 17).getVelocity().x = -6.5f;
			squares.get(start + 17).setPosition(squares.get(start + 17).getPosition());
			
			squares.get(start + 18).carryingBlock = false;
			squares.get(start + 18).visible = true;
			squares.get(start + 18).getPosition().x = 14f + 20f;
			squares.get(start + 18).getPosition().y = 3f;
			squares.get(start + 18).getVelocity().x = -6.5f;
			squares.get(start + 18).setPosition(squares.get(start + 18).getPosition());
			
			squares.get(start + 19).carryingBlock = false;
			squares.get(start + 19).visible = true;
			squares.get(start + 19).getPosition().x = 15f + 20f;
			squares.get(start + 19).getPosition().y = 3f;
			squares.get(start + 19).getVelocity().x = -6.5f;
			squares.get(start + 19).setPosition(squares.get(start + 19).getPosition());
			
			rects.get(start + 3).getPosition().x = 3f + 20f;
			rects.get(start + 3).getPosition().y = 1f;
			rects.get(start + 3).getVelocity().x = -6.5f;
			rects.get(start + 3).setPosition(rects.get(start + 3).getPosition());
			
			rects.get(start).getPosition().x = 12f + 20f;
			rects.get(start).getPosition().y = 1f;
			rects.get(start).getVelocity().x = -6.5f;
			rects.get(start).setPosition(rects.get(start).getPosition());
			
			rects.get(start + 2).getPosition().x = 4f + 20f;
			rects.get(start + 2).getPosition().y = 1f;
			rects.get(start + 2).getVelocity().x = -6.5f;
			rects.get(start + 2).setPosition(rects.get(start + 2).getPosition());
			
			rects.get(start + 1).getPosition().x = 5f + 20f;
			rects.get(start + 1).getPosition().y = 1f;
			rects.get(start + 1).getVelocity().x = -6.5f;
			rects.get(start + 1).setPosition(rects.get(start + 1).getPosition());
			
			rects.get(start + 4).getPosition().x = 6f + 20f;
			rects.get(start + 4).getPosition().y = 1f;
			rects.get(start + 4).getVelocity().x = -6.5f;
			rects.get(start + 4).setPosition(rects.get(start + 4).getPosition());
			
			rects.get(start + 5).getPosition().x = 7f + 20f;
			rects.get(start + 5).getPosition().y = 1f;
			rects.get(start + 5).getVelocity().x = -6.5f;
			rects.get(start + 5).setPosition(rects.get(start + 5).getPosition());
			
			rects.get(start + 6).getPosition().x = 8f + 20f;
			rects.get(start + 6).getPosition().y = 1f;
			rects.get(start + 6).getVelocity().x = -6.5f;
			rects.get(start + 6).setPosition(rects.get(start + 6).getPosition());
			
			rects.get(start + 7).getPosition().x = 9f + 20f;
			rects.get(start + 7).getPosition().y = 1f;
			rects.get(start + 7).getVelocity().x = -6.5f;
			rects.get(start + 7).setPosition(rects.get(start + 7).getPosition());
			
			rects.get(start + 8).getPosition().x = 10f + 20f;
			rects.get(start + 8).getPosition().y = 1f;
			rects.get(start + 8).getVelocity().x = -6.5f;
			rects.get(start + 8).setPosition(rects.get(start + 8).getPosition());

			rects.get(start + 9).getPosition().x = 11f + 20f;
			rects.get(start + 9).getPosition().y = 1f;
			rects.get(start + 9).getVelocity().x = -6.5f;
			rects.get(start + 9).setPosition(rects.get(start + 9).getPosition());
			
			rects.get(start + 10).getPosition().x = 12f + 20f;
			rects.get(start + 10).getPosition().y = 1f;
			rects.get(start + 10).getVelocity().x = -6.5f;
			rects.get(start + 10).setPosition(rects.get(start + 10).getPosition());
			
			rects.get(start + 11).getPosition().x = 13f + 20f;
			rects.get(start + 11).getPosition().y = 1f;
			rects.get(start + 11).getVelocity().x = -6.5f;
			rects.get(start + 11).setPosition(rects.get(start + 11).getPosition());
			
			rects.get(start + 12).getPosition().x = 14f + 20f;
			rects.get(start + 12).getPosition().y = 1f;
			rects.get(start + 12).getVelocity().x = -6.5f;
			rects.get(start + 12).setPosition(rects.get(start + 12).getPosition());
			
			rects.get(start + 13).getPosition().x = 15f + 20f;
			rects.get(start + 13).getPosition().y = 1f;
			rects.get(start + 13).getVelocity().x = -6.5f;
			rects.get(start + 13).setPosition(rects.get(start + 13).getPosition());
			
			circs.get(start + 8).getPosition().x = 4.25f + 20f;
			circs.get(start + 8).getPosition().y = 4.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 9).getPosition().x = 7.75f + 20f;
			circs.get(start + 9).getPosition().y = 6.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 7).getPosition().x = 10.75f + 20f;
			circs.get(start + 7).getPosition().y = 6.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
			
			circs.get(start + 6).getPosition().x = 14.25f + 20f;
			circs.get(start + 6).getPosition().y = 4.25f;
			circs.get(start + 6).getVelocity().x = -6.5f;
			circs.get(start + 6).setPosition(circs.get(start + 6).getPosition());
			
			
		}
		else if (levelNum == 26){
			circs.get(start + 1).getPosition().x = 3f + 20f;
			circs.get(start + 1).getPosition().y = 2.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());
				
			circs.get(start + 4).getPosition().x = 4f + 20f;
			circs.get(start + 4).getPosition().y = 2.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
			circs.get(start + 9).getPosition().x = 5f + 20f;
			circs.get(start + 9).getPosition().y = 2.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
		}
		else if (levelNum == 27){
			circs.get(start + 1).getPosition().x = 3f + 20f;
			circs.get(start + 1).getPosition().y = 2.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());
				
			circs.get(start + 4).getPosition().x = 4f + 20f;
			circs.get(start + 4).getPosition().y = 2.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
			circs.get(start + 9).getPosition().x = 5f + 20f;
			circs.get(start + 9).getPosition().y = 2.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			circs.get(start + 2).getPosition().x = 3f + 20f;
			circs.get(start + 2).getPosition().y = 3.25f;
			circs.get(start + 2).getVelocity().x = -6.5f;
			circs.get(start + 2).setPosition(circs.get(start + 2).getPosition());
				
			circs.get(start + 3).getPosition().x = 4f + 20f;
			circs.get(start + 3).getPosition().y = 3.25f;
			circs.get(start + 3).getVelocity().x = -6.5f;
			circs.get(start + 3).setPosition(circs.get(start + 3).getPosition());
			
			circs.get(start + 5).getPosition().x = 5f + 20f;
			circs.get(start + 5).getPosition().y = 3.25f;
			circs.get(start + 5).getVelocity().x = -6.5f;
			circs.get(start + 5).setPosition(circs.get(start + 5).getPosition());
			
			
			circs.get(start + 11).getPosition().x = 8f + 20f;
			circs.get(start + 11).getPosition().y = 2.25f;
			circs.get(start + 11).getVelocity().x = -6.5f;
			circs.get(start + 11).setPosition(circs.get(start + 11).getPosition());
				
			circs.get(start + 14).getPosition().x = 9f + 20f;
			circs.get(start + 14).getPosition().y = 2.25f;
			circs.get(start + 14).getVelocity().x = -6.5f;
			circs.get(start + 14).setPosition(circs.get(start + 14).getPosition());
			
			circs.get(start + 19).getPosition().x = 10f + 20f;
			circs.get(start + 19).getPosition().y = 2.25f;
			circs.get(start + 19).getVelocity().x = -6.5f;
			circs.get(start + 19).setPosition(circs.get(start + 19).getPosition());
			
			circs.get(start + 12).getPosition().x = 8f + 20f;
			circs.get(start + 12).getPosition().y = 3.25f;
			circs.get(start + 12).getVelocity().x = -6.5f;
			circs.get(start + 12).setPosition(circs.get(start + 12).getPosition());
				
			circs.get(start + 13).getPosition().x = 9f + 20f;
			circs.get(start + 13).getPosition().y = 3.25f;
			circs.get(start + 13).getVelocity().x = -6.5f;
			circs.get(start + 13).setPosition(circs.get(start + 13).getPosition());
			
			circs.get(start + 15).getPosition().x = 10f + 20f;
			circs.get(start + 15).getPosition().y = 3.25f;
			circs.get(start + 15).getVelocity().x = -6.5f;
			circs.get(start + 15).setPosition(circs.get(start + 15).getPosition());
			
			
			circs.get(start + 10).getPosition().x = 13f + 20f;
			circs.get(start + 10).getPosition().y = 2.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
				
			circs.get(start + 18).getPosition().x = 14f + 20f;
			circs.get(start + 18).getPosition().y = 2.25f;
			circs.get(start + 18).getVelocity().x = -6.5f;
			circs.get(start + 18).setPosition(circs.get(start + 18).getPosition());
			
			circs.get(start + 17).getPosition().x = 15f + 20f;
			circs.get(start + 17).getPosition().y = 2.25f;
			circs.get(start + 17).getVelocity().x = -6.5f;
			circs.get(start + 17).setPosition(circs.get(start + 17).getPosition());
			
			circs.get(start + 7).getPosition().x = 13f + 20f;
			circs.get(start + 7).getPosition().y = 3.25f;
			circs.get(start + 7).getVelocity().x = -6.5f;
			circs.get(start + 7).setPosition(circs.get(start + 7).getPosition());
				
			circs.get(start + 8).getPosition().x = 14f + 20f;
			circs.get(start + 8).getPosition().y = 3.25f;
			circs.get(start + 8).getVelocity().x = -6.5f;
			circs.get(start + 8).setPosition(circs.get(start + 8).getPosition());
			
			circs.get(start + 16).getPosition().x = 15f + 20f;
			circs.get(start + 16).getPosition().y = 3.25f;
			circs.get(start + 16).getVelocity().x = -6.5f;
			circs.get(start + 16).setPosition(circs.get(start + 16).getPosition());
			
		}
		else if (levelNum == 28){
			circs.get(start + 1).getPosition().x = 3f + 20f;
			circs.get(start + 1).getPosition().y = 2.25f;
			circs.get(start + 1).getVelocity().x = -6.5f;
			circs.get(start + 1).setPosition(circs.get(start + 1).getPosition());
				
			circs.get(start + 4).getPosition().x = 4f + 20f;
			circs.get(start + 4).getPosition().y = 2.25f;
			circs.get(start + 4).getVelocity().x = -6.5f;
			circs.get(start + 4).setPosition(circs.get(start + 4).getPosition());
			
			circs.get(start + 9).getPosition().x = 5f + 20f;
			circs.get(start + 9).getPosition().y = 2.25f;
			circs.get(start + 9).getVelocity().x = -6.5f;
			circs.get(start + 9).setPosition(circs.get(start + 9).getPosition());
			
			
			circs.get(start + 12).getPosition().x = 8f + 20f;
			circs.get(start + 12).getPosition().y = 3.25f;
			circs.get(start + 12).getVelocity().x = -6.5f;
			circs.get(start + 12).setPosition(circs.get(start + 12).getPosition());
				
			circs.get(start + 13).getPosition().x = 9f + 20f;
			circs.get(start + 13).getPosition().y = 3.25f;
			circs.get(start + 13).getVelocity().x = -6.5f;
			circs.get(start + 13).setPosition(circs.get(start + 13).getPosition());
			
			circs.get(start + 15).getPosition().x = 10f + 20f;
			circs.get(start + 15).getPosition().y = 3.25f;
			circs.get(start + 15).getVelocity().x = -6.5f;
			circs.get(start + 15).setPosition(circs.get(start + 15).getPosition());
			
			
			circs.get(start + 10).getPosition().x = 13f + 20f;
			circs.get(start + 10).getPosition().y = 2.25f;
			circs.get(start + 10).getVelocity().x = -6.5f;
			circs.get(start + 10).setPosition(circs.get(start + 10).getPosition());
				
			circs.get(start + 18).getPosition().x = 14f + 20f;
			circs.get(start + 18).getPosition().y = 2.25f;
			circs.get(start + 18).getVelocity().x = -6.5f;
			circs.get(start + 18).setPosition(circs.get(start + 18).getPosition());
			
			circs.get(start + 17).getPosition().x = 15f + 20f;
			circs.get(start + 17).getPosition().y = 2.25f;
			circs.get(start + 17).getVelocity().x = -6.5f;
			circs.get(start + 17).setPosition(circs.get(start + 17).getPosition());
			
			
		}

			

		
	}
	
	public void resetWorld() {
		for (Obstacle obstacle : obstacles) {
			obstacle = null;
		}
		
		mainBlock = null;
		createDemoWorld();
	}
	
	// creates set of blocks - only need to call this once in the entire program
	// for every array item 0-19 is used for 1 level, and 20-39 are used for out of view level 
	private void createDemoWorld() {
		mainBlock = new MainBlock(new Vector2(2, 2));
		
		
 // move blocks out of world
		for (int i = 0; i < 20; i = i + 1) {
			blocks.add(new Block(new Vector2(i, 2)));
		}
		for (int i = 0; i < 20; i = i + 1) {
			blocks.add(new Block(new Vector2(i, 3)));
		}
		for (int i = 0; i < 20; i = i + 1) {
			blocks.add(new Block(new Vector2(i, 4)));
		}
		for (int i = 0; i < 20; i = i + 1) {
			blocks.add(new Block(new Vector2(i, 5)));
		}
		for (int i = 0; i < 20; i = i + 1) {
			blocks.add(new Block(new Vector2(i, 6)));
		}
		for (int i = 0; i < 20; i = i + 1) {
			blocks.add(new Block(new Vector2(i, 7)));
		}
		
		originBlock = new OriginBlock(new Vector2(1, 1));
		
		powerup = new Powerup(new Vector2(11, 4));
		
		for (int i = 0; i < 40; i = i + 1) {
			obstacles.add(new Obstacle(new Vector2(0, 100)));
		}
		
		for (int i = 0; i < 40; i = i + 1) {
			squares.add(new Square(new Vector2(-50, 100)));
		}
		
		for (int i = 0; i < 40; i = i + 1) {
			rects.add(new Rect(new Vector2(i, -1)));
		}
		
		for (int i = 0; i < 40; i = i + 1) {
//			circs.add(new Circ(new Vector2(i + (float)2.25, (float)3.25)));	
			circs.add(new Circ(new Vector2(i + (float)2.25, -1)));		
		}
		
//		for (int i = 0; i < 40; i = i + 1) {
//			lines.add(new Line(new Vector2(i, 1)));
//		}
		
	}
}
