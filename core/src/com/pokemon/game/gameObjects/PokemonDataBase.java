package com.pokemon.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pokemon.game.GameConstants;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class PokemonDataBase {
    public static HashMap<String, Pokemon> pokemonCollection;
    public static ArrayList<Pokemon> pokemonList;
    static {
        pokemonCollection = new HashMap<>();
        pokemonList = new ArrayList<>();

        pokemonList.add( new Pokemon("squirtle", 10, 10, new ImageIcon("assets/pokemonPics/squirtle.png")));
        pokemonList.add( new Pokemon("charmander", 10, 10, new ImageIcon("/pokemonPics/charmander.png")));
        pokemonList.add( new Pokemon("pikachu", 10, 10, new ImageIcon("/pokemonPics/pikachu.png")));
        pokemonList.add( new Pokemon("weezy", 2, 5, new ImageIcon("/pokemonPics/Weezy.png")));
        pokemonList.add( new Pokemon("pidgeotto", 2, 5, new ImageIcon("/pokemonPics/pidgeotto.png")));
        pokemonList.add( new Pokemon("bulbasaur", 2, 5, new ImageIcon("/pokemonPics/bulbasaur.png")));


//        pokemonCollection.put("charzard", new Pokemon("charzard", 20, 5));

    }
}
