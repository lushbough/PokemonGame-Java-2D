package com.pokemon.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pokemon.game.screens.MainGameScreen;

public class PokemonGame extends Game {
//	SpriteBatch batch;
//	Texture img;
	
	@Override
	public void create () {
		setScreen(new MainGameScreen());
	}

	@Override
	public void render () {

		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void pause(){
		super.pause();
	}

	@Override
	public void resume(){
		super.resume();
	}
}
