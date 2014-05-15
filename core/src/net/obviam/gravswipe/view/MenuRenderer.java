package net.obviam.gravswipe.view;

import net.obviam.gravswipe.model.Block;
import net.obviam.gravswipe.model.Circ;
import net.obviam.gravswipe.model.Line;
import net.obviam.gravswipe.model.Obstacle;
import net.obviam.gravswipe.model.MainBlock;
import net.obviam.gravswipe.model.MainBlock.State;
import net.obviam.gravswipe.model.PauseButton;
import net.obviam.gravswipe.model.Rect;
import net.obviam.gravswipe.model.RestartButton;
import net.obviam.gravswipe.model.Square;
import net.obviam.gravswipe.model.World;
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

// takes current state of the world and renders it to screen
public class MenuRenderer {
	
	private static float CAMERA_WIDTH;// = 16F;
	private static float CAMERA_HEIGHT;// = 9F;
	// my method: calculate the aspect ratio of the screen, and adjust camera width and camera height accordingly, eg, for nexus 4 - use 15f x 9f;
	// support different aspect ratioshttp://www.reinmedical.com/en/knowledge-technology/monitor-resolutions-overview.html
	// zoom in when on a screen resolution other than 16:9
	// http://blog.gemserk.com/2013/01/22/our-solution-to-handle-multiple-screen-sizes-in-android-part-one/
	// when drawing blocks, draw them relative to the screen size
	private World world;
	private OrthographicCamera cam;

	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	//** Textures **//
	private Texture mainBlockTexture;
	private Texture playTexture;
	private Texture settingsTexture;
	private Texture playGamesTexture;


	private BitmapFont itemFont;
	private BitmapFont titleFont;

	
	private Sprite mainSprite;
	private Sprite playSprite;
	private Sprite settingsSprite;
	private Sprite playGamesSprite;


	public PauseButton pauseButton;// = new PauseButton(new Vector2(CAMERA_WIDTH - 2, CAMERA_HEIGHT - 2));
	public RestartButton restartButton;// = new RestartButton(new Vector2(CAMERA_WIDTH - 4, CAMERA_HEIGHT - 2));

	
	private SpriteBatch spriteBatch;
	private boolean debug = false; //debug flag for drawing bound boxes
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
			CAMERA_HEIGHT = 9f;
			CAMERA_WIDTH = 16f;
		}
		//nexus 4
		else if ((height == 768.0 && width == 1280.0) || (height == 1200.0 && width == 1920.0)) {
			this.CAMERA_HEIGHT = 9f;
			this.CAMERA_WIDTH = 15f;
		}
		
		pauseButton = new PauseButton(new Vector2(CAMERA_WIDTH - 2, CAMERA_HEIGHT - 2));
		restartButton = new RestartButton(new Vector2(CAMERA_WIDTH - 4, CAMERA_HEIGHT - 2));
	}
		
	public MenuRenderer(boolean debug) {
		setCam();
		world = new World();
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		// the grid is comprised of 16:9 units
		// todo: support multiple aspect ratios
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0); // we want camera in middle
		this.cam.update();
		spriteBatch = new SpriteBatch();
		loadTextures();
		loadFonts();
	}
	
	private void loadTextures() {
		// use this method to assign textures to elements
		mainBlockTexture = new  Texture(Gdx.files.internal("images/mainblock.png"), true);
		playTexture = new  Texture(Gdx.files.internal("images/play button.png"), true);
		settingsTexture = new  Texture(Gdx.files.internal("images/settings button.png"), true);
		playGamesTexture = new  Texture(Gdx.files.internal("images/play games button.png"), true);
		
		playGamesTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		playTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);
		settingsTexture.setFilter(TextureFilter.MipMapLinearLinear, TextureFilter.Linear);

	}
	
	
	private void loadFonts() {
		Texture item = new Texture(Gdx.files.internal("fonts/opensans.png"), true); // true enables mipmaps
		Texture title = new Texture(Gdx.files.internal("fonts/opensansb.png"), true); // true enables mipmaps
		title.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);
		item.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);

		itemFont = new BitmapFont (Gdx.files.internal("fonts/opensans.fnt"),new TextureRegion(item),false); itemFont.setScale(0.7f, 0.7f);
		titleFont = new BitmapFont (Gdx.files.internal("fonts/opensansb.fnt"), new TextureRegion(title),false); titleFont.setScale(1f, 1f);

	}
	
	private void drawMainBlock() {
		MainBlock mainBlock = world.getMainBlock();
		mainSprite = new Sprite(mainBlockTexture);
		mainSprite.setPosition(mainBlock.getPosition().x * ppuX, mainBlock.getPosition().y * ppuY);
		mainSprite.setSize( mainBlock.SIZE * ppuX, mainBlock.SIZE * ppuY);
		
//	    mainSprite.rotate((float) 45.0);

		mainSprite.draw(spriteBatch);

//		spriteBatch.draw(mainSprite, mainBlock.getPosition().x * ppuX, mainBlock.getPosition().y * ppuY, mainBlock.SIZE * ppuX, mainBlock.SIZE * ppuY);	
	}
	
	private void drawPlayGamesButton() {
		playGamesSprite = new Sprite(playGamesTexture);
		playGamesSprite.setPosition(2f * ppuX, 1.2f * ppuY);
		playGamesSprite.setSize( 1f * ppuX, 1f * ppuY);
		
//	    mainSprite.rotate((float) 45.0);

		playGamesSprite.draw(spriteBatch);

//		spriteBatch.draw(mainSprite, mainBlock.getPosition().x * ppuX, mainBlock.getPosition().y * ppuY, mainBlock.SIZE * ppuX, mainBlock.SIZE * ppuY);	
	}
	
	private void drawSettingsButton() {
		settingsSprite = new Sprite(settingsTexture);
		settingsSprite.setPosition(2f * ppuX, 2.7f * ppuY);
		settingsSprite.setSize( 1f * ppuX, 1f * ppuY);
		
//	    mainSprite.rotate((float) 45.0);

		settingsSprite.draw(spriteBatch);

//		spriteBatch.draw(mainSprite, mainBlock.getPosition().x * ppuX, mainBlock.getPosition().y * ppuY, mainBlock.SIZE * ppuX, mainBlock.SIZE * ppuY);	
	}
	
	private void drawPlayButton() {
		playSprite = new Sprite(playTexture);
		playSprite.setPosition(2f * ppuX, 4.2f * ppuY);
		playSprite.setSize( 1f * ppuX, 1f * ppuY);
		
//	    mainSprite.rotate((float) 45.0);

		playSprite.draw(spriteBatch);

//		spriteBatch.draw(mainSprite, mainBlock.getPosition().x * ppuX, mainBlock.getPosition().y * ppuY, mainBlock.SIZE * ppuX, mainBlock.SIZE * ppuY);	
	}
	
	public void render() {
		spriteBatch.begin();
		// todo: make seperate methods for drawing title, and combine drawing buttons + text together
			titleFont.setColor(new Color(0, 0, 0, 1));
			titleFont.draw(spriteBatch, "title here",  2f * ppuX, 8f * ppuY);
			
			drawPlayButton();
			itemFont.setColor(new Color(0, 0, 0, 1));
			itemFont.draw(spriteBatch, "play",  3.5f * ppuX, 5f * ppuY);
			
			drawSettingsButton();
			itemFont.setColor(new Color(0, 0, 0, 1));
			itemFont.draw(spriteBatch, "options",  3.5f * ppuX, 3.5f * ppuY);
			
			drawPlayGamesButton();
			itemFont.setColor(new Color(0, 0, 0, 1));
			itemFont.draw(spriteBatch, "google play games",  3.5f * ppuX, 2f * ppuY);
//			itemFont.draw(spriteBatch, "fps:"+Gdx.graphics.getFramesPerSecond(), 3.5f * ppuX, 2f * ppuY);

		
		
//			drawMainBlock();
//			scoreFont.setColor(new Color(0, 0, 0, 0.5f));
//			scoreFont.setScale(0.5f, 0.5f);
//			scoreFont.draw(spriteBatch, "score ",  1f * ppuX, 8f * ppuY);
//			scoreFont.setColor(new Color(0, 0, 0, 1));
//			scoreFont.setScale(1f, 1f);
//			scoreFont.draw(spriteBatch, Integer.toString(world.getMainBlock().getScore()),  3f * ppuX, 8f * ppuY);

		spriteBatch.end();
	}
	
	// dont need this
	public void dispose() {
		spriteBatch.dispose();
	}
}
