package com.pokemon.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pokemon.game.PokemonGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 720;
		config.useGL30 = true;

		config.resizable= false;
		config.vSyncEnabled = true; //maybe delete
		new LwjglApplication(new PokemonGame(), config);
	}
}
