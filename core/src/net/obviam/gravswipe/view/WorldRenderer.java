package net.obviam.gravswipe.view;

import net.obviam.gravswipe.model.Block;
import net.obviam.gravswipe.model.Circ;
import net.obviam.gravswipe.model.Levels;
import net.obviam.gravswipe.model.Line;
import net.obviam.gravswipe.model.Obstacle;
import net.obviam.gravswipe.model.MainBlock;
import net.obviam.gravswipe.model.MainBlock.State;
import net.obviam.gravswipe.model.PauseButton;
import net.obviam.gravswipe.model.Powerup;
import net.obviam.gravswipe.model.Rect;
import net.obviam.gravswipe.model.RestartButton;
import net.obviam.gravswipe.model.Square;
import net.obviam.gravswipe.model.World;
import net.obviam.gravswipe.model.OriginBlock;
import net.obviam.gravswipe.screens.GameScreen;
import net.obviam.gravswipe.screens.GameScreen.Type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// TODO: remove all of the "new" calls in draw whatever to improve performance
// takes current state of the world and renders it to screen
public class WorldRenderer {
	
	// target resolution 720p or 1080p with units of 20 * 11.25, ie develop with this grid size in mind, this is the largest it will be on any device

	
	private static float CAMERA_WIDTH;// = 16F;
	private static float CAMERA_HEIGHT;// = 9F;
	// my method: calculate the aspect ratio of the screen, and adjust camera width and camera height accordingly, eg, for nexus 4 - use 15f x 9f;
	// support different aspect ratioshttp://www.reinmedical.com/en/knowledge-technology/monitor-resolutions-overview.html
	// zoom in when on a screen resolution other than 16:9
	// http://blog.gemserk.com/2013/01/22/our-solution-to-handle-multiple-screen-sizes-in-android-part-one/
	// when drawing blocks, draw them relative to the screen size
	private World world;
//	public Levels levels;
	private OrthographicCamera cam;

	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	//** Textures **//
	private Texture mainBlockTexture;
	private Texture blockTexture;
	private Texture obstacleTexture;
	private Texture pauseTexture;
	private Texture restartTexture;
	private Texture squareTexture;
	private Texture rectTexture;
	private Texture circTexture;
	private Texture lineTexture;
	private Texture powerupTexture;
	// for debugging
	private Texture originTexture;



	private BitmapFont scoreFont;
	
	private Sprite mainSprite;
//	private Sprite mainSprite;
	private Sprite powerupSprite;


	public PauseButton pauseButton;// = new PauseButton(new Vector2(CAMERA_WIDTH - 2, CAMERA_HEIGHT - 2));
	public RestartButton restartButton;// = new RestartButton(new Vector2(CAMERA_WIDTH - 4, CAMERA_HEIGHT - 2));

	
	private SpriteBatch spriteBatch;
	private boolean debug = false; //debug flag for drawing bound boxes
	private boolean deathAnimationDone = false;
	private int width;
	private int height;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
	
	public float getCameraWidth() {
		return CAMERA_WIDTH;
	}
	
	public float getCameraHeight() {
		return CAMERA_HEIGHT;
	}
	
	// add more screen sizes and check aspect ratio instead of resolution
	private void setCam() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		if ((height == 720.0 && width == 1280.0) || (height == 1080.0 && width == 1920.0)) {
			CAMERA_HEIGHT = 9f *1.25f;
			CAMERA_WIDTH = 16f * 1.25f;
		}
		//nexus 4
		else if ((height == 768.0 && width == 1280.0) || (height == 1200.0 && width == 1920.0)) {
			this.CAMERA_HEIGHT = 9f * 1.25f;
			this.CAMERA_WIDTH = 15f * 1.25f;
		}
		
	
		pauseButton = new PauseButton(new Vector2(CAMERA_WIDTH - 2, CAMERA_HEIGHT - 2));
		restartButton = new RestartButton(new Vector2(CAMERA_WIDTH - 4, CAMERA_HEIGHT - 2));
	}
		
	public void resetMainBlock(){
		deathAnimationDone = false;
		mainSprite.setAlpha(1);
		mainSprite.scale(0);

	}
	
	public WorldRenderer(World world, boolean debug) {
		setCam();
		this.world = world;
//		this.levels = new Levels(world);
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		// the grid is comprised of 16:9 units
		// todo: support multiple aspect ratios
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0); // we want camera in middle
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
		loadFonts();
	}
	
	private void loadTextures() {
		// use this method to assign textures to elements
		mainBlockTexture = new  Texture(Gdx.files.internal("images/mainblock.png"), true);
		blockTexture = new Texture(Gdx.files.internal("images/background.png"), true);
		obstacleTexture = new Texture(Gdx.files.internal("images/obstacle.png"), true);
		pauseTexture = new Texture(Gdx.files.internal("images/pause button.png"), true);
		restartTexture = new Texture(Gdx.files.internal("images/restart button.png"), true);
		squareTexture = new Texture(Gdx.files.internal("images/square.png"), true);
		rectTexture = new Texture(Gdx.files.internal("images/rect.png"), true);
		circTexture = new Texture(Gdx.files.internal("images/circle.png"), true);
		lineTexture = new Texture(Gdx.files.internal("images/floorline.png"), true);
		powerupTexture = new Texture(Gdx.files.internal("images/powerup.png"), true);
		originTexture = new Texture(Gdx.files.internal("images/powerup.png"));


		blockTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		pauseTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		mainBlockTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		obstacleTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		restartTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		squareTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		rectTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		circTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		lineTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		powerupTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
	}
	
	private void loadFonts() {
		Texture score = new Texture(Gdx.files.internal("fonts/opensans.png"), true); // true enables mipmaps
		score.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		scoreFont = new BitmapFont (Gdx.files.internal("fonts/opensans.fnt"), new TextureRegion(score) ,false); scoreFont.setScale(1f, 1f);
	}

	// todo: draw only the blocks that are visible;
	private void drawBlocks() {
		// draw all the blocks
		for (Block block : world.getBlocks()) {
			spriteBatch.draw(blockTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY, Block.SIZE * ppuX * 7, Block.SIZE * ppuY, 0, 1, 7, 0);
		}
	}
	
	private void drawPauseButton() {
		// draw the pause button
		spriteBatch.draw(pauseTexture, pauseButton.getPosition().x * ppuX, pauseButton.getPosition().y * ppuY, PauseButton.SIZE * ppuX, PauseButton.SIZE * ppuY);
	}
	
	private void drawRestartButton() {
		// draw the pause button
		spriteBatch.draw(restartTexture, restartButton.getPosition().x * ppuX, restartButton.getPosition().y * ppuY, RestartButton.SIZE * ppuX, RestartButton.SIZE * ppuY);
	}
	
	private void drawObstacles() {
		// draw all the obstacles
		for (Obstacle obstacle : world.getObstacles()) {
			spriteBatch.draw(obstacleTexture, obstacle.getPosition().x * ppuX, obstacle.getPosition().y * ppuY, Obstacle.SIZE * ppuX, Obstacle.SIZE * ppuY);
		}
	}
	
	private void drawSquares() {
		// draw all the obstacles
		for (Square square : world.getSquares()) {
			spriteBatch.draw(squareTexture, square.getPosition().x * ppuX, square.getPosition().y * ppuY, Square.SIZE * ppuX, Square.SIZE * ppuY);
		}
	}
	
	private void drawRects() {
		// draw all the obstacles
		for (Rect rect : world.getRects()) {
			spriteBatch.draw(rectTexture, rect.getPosition().x * ppuX, (rect.getPosition().y + Rect.SIZE /2) * ppuY, Rect.SIZE * ppuX, Rect.SIZE / 2 * ppuY);
		}
	}
	
	private void drawCircs() {
		// draw all the obstacles
		for (Circ circ : world.getCircs()) {
//			spriteBatch.draw(circTexture, circ.getPosition().x * ppuX, circ.getPosition().y * ppuY, Circ.SIZE * ppuX, Circ.SIZE * ppuY);
			Sprite circSprite = new Sprite(circTexture);
			circSprite.setPosition(circ.getPosition().x * ppuX, circ.getPosition().y * ppuY);
			circSprite.setOrigin(circ.SIZE/2 * ppuX, circ.SIZE/2 *  ppuY);
			circSprite.setSize( circ.SIZE * ppuX, circ.SIZE * ppuY);
			
			float alphaCirc = circSprite.getColor().a;
			float newAlphaCirc = alphaCirc - ((float)4.0 * circ.getStateTime());
			if (circ.collected){
//				System.out.println("circ collected");
				
				circSprite.scale(((float)10.0 * circ.getStateTime()));
			}
			if (newAlphaCirc <= (float)0.0){
				circ.animationDone = true;
			}
			else if (!circ.animationDone){
				circSprite.setAlpha(newAlphaCirc);
			}
			
			if (circ.animationDone){
				circSprite.setAlpha(0);
			}
			
			circSprite.draw(spriteBatch);

			
		}
	}
	
	private void drawLines() {
		// draw all the obstacles
		for (Line line : world.getLines()) {
			spriteBatch.draw(lineTexture, line.getPosition().x * ppuX, (line.getPosition().y + line.SIZE - (float)0.01)* ppuY, line.SIZE * ppuX, line.SIZE / 50* ppuY);
		}
	}
	
	//change all drawing to this format
	public void rotateRight() {
	    spriteBatch.begin();
	    mainSprite.rotate((float) 45.0);
	    mainSprite.draw(spriteBatch);
	    spriteBatch.end();
	    
	}
	
	private void drawMainBlock() {
		MainBlock mainBlock = world.getMainBlock();
		mainSprite = new Sprite(mainBlockTexture);
		mainSprite.setPosition(mainBlock.getPosition().x * ppuX, mainBlock.getPosition().y * ppuY);
		mainSprite.setOrigin(mainBlock.SIZE/2 * ppuX, mainBlock.SIZE/2 *  ppuY);
		mainSprite.setSize( mainBlock.SIZE * ppuX, mainBlock.SIZE * ppuY);

		if (GameScreen.getType().equals(Type.STOPPED) || (mainBlock.getState().equals(State.DYING))) {
//			mainSprite.rotate(mainSprite.getRotation() - (float)400.0 * mainBlock.getStateTime());

//			mainSprite.setSize( (mainBlock.SIZE + (float)10.0 * mainBlock.getStateTime()) * ppuX, (mainBlock.SIZE + (float)10.0 * mainBlock.getStateTime()) * ppuY);
			float alpha = mainSprite.getColor().a;
			float newAlpha = alpha - ((float)5.0 * mainBlock.getStateTime());
			if (!deathAnimationDone){
				mainSprite.scale(((float)10.0 * mainBlock.getStateTime()));
			}
			if (newAlpha <= (float)0.0){
//				System.out.println("white");
				deathAnimationDone = true;
			}
			else if (!deathAnimationDone){
				mainSprite.setAlpha(newAlpha);
			}

		}
		else if ((!(GameScreen.getType().equals(Type.STOPPED) && !(mainBlock.getState().equals(State.DYING)))) && mainBlock.getState().equals(State.JUMPING)) {
			// rotate animation
			// add trailing squares
			mainSprite.rotate(mainSprite.getRotation() - (float)400.0 * mainBlock.getStateTime());
		}
		if (deathAnimationDone){
			mainSprite.setAlpha(0);
		}
		if (mainBlock.getState().equals(State.IDLE)){
			resetMainBlock();
		}
		mainSprite.draw(spriteBatch);
//	    mainSprite.rotate((float) 45.0);

//		spriteBatch.draw(mainSprite, mainBlock.getPosition().x * ppuX, mainBlock.getPosition().y * ppuY, mainBlock.SIZE * ppuX, mainBlock.SIZE * ppuY);	
	}
	
	private void drawOriginBlock() {
		OriginBlock originBlock = world.getOriginBlock();
		spriteBatch.draw(originTexture, originBlock.getPosition().x * ppuX, originBlock.getPosition().y * ppuY, OriginBlock.SIZE * ppuX, OriginBlock.SIZE * ppuY);
//		spriteBatch.draw(mainSprite, mainBlock.getPosition().x * ppuX, mainBlock.getPosition().y * ppuY, mainBlock.SIZE * ppuX, mainBlock.SIZE * ppuY);	
	}
	
	
	private void drawPowerup() {
		Powerup powerup = world.getPowerup();
		powerupSprite = new Sprite(powerupTexture);
		powerupSprite.setPosition(powerup.getPosition().x * ppuX, powerup.getPosition().y * ppuY);
		powerupSprite.setOrigin(powerup.SIZE/2 * ppuX, powerup.SIZE/2 *  ppuY);
		// rotate animation
		// add trailing squares
		powerupSprite.rotate(powerupSprite.getRotation() - (float)100.0 * powerup.getStateTime());
		powerupSprite.setSize( powerup.SIZE * ppuX, powerup.SIZE * ppuY);
		
//	    mainSprite.rotate((float) 45.0);

		powerupSprite.draw(spriteBatch);

//		spriteBatch.draw(mainSprite, mainBlock.getPosition().x * ppuX, mainBlock.getPosition().y * ppuY, mainBlock.SIZE * ppuX, mainBlock.SIZE * ppuY);	
	}
	
	private void drawDebug() {
		// method for drawing bound boxes
		// render blocks
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
//		for (Block block : world.getBlocks()) {
//			Rectangle rect = block.getBounds();
//			float x1 = block.getPosition().x + rect.x;
//			float y1 = block.getPosition().y + rect.y;
//			debugRenderer.setColor(new Color(1, 0, 0, 1));
//			debugRenderer.rect(x1, y1, rect.width, rect.height);
//		}
		
		//render obstacles
		for (Obstacle obstacle : world.getObstacles()) {
			Rectangle rect = obstacle.getBounds();
			float x1 = obstacle.getPosition().x + rect.x;
			float y1 = obstacle.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(7, 0, 0, 7));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		// render the mainBlock
		MainBlock mainBlock = world.getMainBlock();
		Rectangle rect = mainBlock.getBounds();
		float x1 = mainBlock.getPosition().x + rect.x;
		float y1 = mainBlock.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}
	
	public void render() {
		spriteBatch.begin();
//			spriteBatch.setProjectionMatrix(cam.combined);
			drawBlocks();
			drawObstacles();
//			mainSprite.draw(spriteBatch);
			drawPauseButton();
			drawRestartButton();
			drawSquares();
			drawLines();
			drawRects();
//			drawPowerup();
//			drawOriginBlock();
			drawCircs();
			drawMainBlock();
			scoreFont.setColor(new Color(0, 0, 0, 0.5f));
			scoreFont.setScale(0.5f, 0.5f);

			scoreFont.draw(spriteBatch, "score ",  1f * ppuX, 10.5f * ppuY);
			scoreFont.setColor(new Color(0, 0, 0, 1));
			scoreFont.setScale(1f, 1f);
			scoreFont.draw(spriteBatch, Integer.toString(world.getMainBlock().getScore()),  4f * ppuX, 10.5f * ppuY);
			//fps
//			scoreFont.draw(spriteBatch, "fps:"+Gdx.graphics.getFramesPerSecond(), 4f * ppuX, 10.5f * ppuY);



		spriteBatch.end();
		if (debug)
			drawDebug();
	}
	
	// dont need this
	public void dispose() {
		spriteBatch.dispose();
		pauseTexture.dispose();
	}
}
