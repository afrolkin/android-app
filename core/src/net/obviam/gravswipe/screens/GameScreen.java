
package net.obviam.gravswipe.screens;
import net.obviam.gravswipe.screens.GameScreen;
import net.obviam.gravswipe.screens.MenuScreen;



import net.obviam.gravswipe.controller.WorldController;
import net.obviam.gravswipe.model.World;
import net.obviam.gravswipe.model.MainBlock.State;
import net.obviam.gravswipe.view.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.gravswipe.game.GravSwipe;

public class GameScreen implements Screen, InputProcessor {
	
	private World world;
	private WorldRenderer renderer; 
	private WorldController controller;
	private int height, width;
	private boolean pausePressed = false;
	private boolean restartPressed = false;

	GravSwipe game;
	public static Type type = Type.RUN;
	
	public enum Type {
		PAUSE, RUN, RESUME, STOPPED, RESTART
	}
	
	
	
	public GameScreen(GravSwipe game2) {
		// TODO Auto-generated constructor stub
		this.game = game2;
	}

	//rewrite stopped, split it into deaht and stopped?
	// rewrite the way pausing, restarting, etc works
	@Override
	public void render(float delta) {
		switch (type)
		{
		case RUN:
			Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			controller.update(delta);
			renderer.render();
			renderer.resetMainBlock();
//			controller.resetBlocks();
			break;
		case PAUSE:
			Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			renderer.render();
			
			if (!pausePressed) {
				setState(Type.RUN);
			}
			break;
		case STOPPED:
			Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			if(!pausePressed){
//				System.out.println("died");
				controller.deathAnimation(delta);
			}
			renderer.render();
			
			if (restartPressed) {
				restartPressed = false;
				setState(Type.RUN);
			}
			
			break;
		case RESTART:
			break;
		}
		
		//redo the stop method, make it so that you can press buttons and it simply makes all the velocities 0;

		
//        System.out.println(1/delta);
	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height); 
		this.width = width;
		this.height = height;
		
	}
	


	@Override
	public void show() {
		 world = new World();
		 renderer = new WorldRenderer(world, false);
		 controller = new WorldController(world);
		 Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
		renderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.Z)
			controller.jumpPressed();
			renderer.rotateRight();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.Z)
			controller.jumpReleased();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// move this outside of here to pre cache these
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float cameraHeight = renderer.getCameraHeight();
		float cameraWidth = renderer.getCameraWidth();
		float unitsX = cameraWidth*((float)screenX/width); //change this to screen size
		float unitsY = cameraHeight*(((float)-screenY + height)/height);
		
		float pauseX = renderer.pauseButton.getPosition().x;
		float pauseY = renderer.pauseButton.getPosition().y;

		float restartX = renderer.restartButton.getPosition().x;
		float restartY = renderer.restartButton.getPosition().y;

		
		
		System.out.println(screenX);
		
		System.out.println(screenY);
		
		System.out.println(unitsX);
		
		System.out.println(unitsY);

		
		//comvert pixels to units on screen
		
//		if ((unitsX >= 13.5 && unitsX <= 16.0) && (unitsY >= 6.5 && unitsY <= 9.0)) {
//			controller.pausePressed();
//			if (pausePressed) {
//				pausePressed = false;
//				restartPressed = false;
//			}
//			else if (!pausePressed) {				
//				pausePressed = true;
//			}
//		}
//		else if ((unitsX >= 11.5 && unitsX <= 14.0) && (unitsY >= 6.5 && unitsY <= 9.0)) {
//			controller.restartPressed();
//			if (restartPressed) {
//				restartPressed = false;
//				pausePressed = false;
//			}
//			else if (!restartPressed) {				
//				restartPressed = true;
//				pausePressed = false;
//
//			}
//		}
		if ((unitsX >= pauseX - 0.5 && unitsX <= pauseX + 2.0) && (unitsY >= pauseY - 0.5 && unitsY <= pauseY + 2.0)) {
			controller.pausePressed();
			if (pausePressed) {
				pausePressed = false;
				restartPressed = false;
			}
			else if (!pausePressed) {				
				pausePressed = true;
			}
		}
		else if ((unitsX >= restartX - 0.5 && unitsX <= restartX + 2.0) && (unitsY >= restartY - 0.5 && unitsY <= restartY + 2.0)) {
			controller.restartPressed();
			if (restartPressed) {
				restartPressed = false;
				pausePressed = false;
			}
			else if (!restartPressed) {				
				restartPressed = true;
				pausePressed = false;

			}
		}
//		else if ((unitsX >= restartX - 5.5 && unitsX <= restartX -5.5 + 2.0) && (unitsY >= restartY - 0.5 && unitsY <= restartY + 2.0)) {
//			game.setScreen(new MenuScreen(game));
//		}
		else {
			controller.jumpPressed();
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		controller.jumpReleased();
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public static  void setState(Type newState) {
		type = newState;
		
	}
	
	public static Type getType() {
		return type;
	}
	
}
