package com.gravswipe.game;

import net.obviam.gravswipe.screens.GameScreen;
import net.obviam.gravswipe.screens.MenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GravSwipe extends Game {
//	SpriteBatch batch;
//	Texture img;
	public GameScreen play2;
	public Boolean restart = false;
	public Boolean start = false;

	@Override
	public void create () {

//		play2 = new GameScreen(this);
//		setScreen(play2);
		
		setScreen(new MenuScreen(this));
		if (restart) {
			setScreen(new GameScreen(this));
		}
	}
	
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void pause () {
		
	}
	
	@Override
	public void resume () {
		
	}
	
	@Override
	public void dispose () {
		
	}
	
}
