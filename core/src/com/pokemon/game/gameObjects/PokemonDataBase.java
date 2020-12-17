package com.pokemon.game.gameObjects;


import javax.swing.*;
import java.util.ArrayList;

public class PokemonDataBase {
    public static ArrayList<Pokemon> pokemonList;

    static {
        pokemonList = new ArrayList<>();
        pokemonList.add(new Pokemon("squirtle", 15, 5, new ImageIcon("assets/pokemonPics/squirtle.png")));
        pokemonList.add(new Pokemon("charmander", 15, 5, new ImageIcon("/pokemonPics/charmander.png")));
        pokemonList.add(new Pokemon("pikachu", 15, 5, new ImageIcon("/pokemonPics/pikachu.png")));
        pokemonList.add(new Pokemon("weezy", 10, 8, new ImageIcon("/pokemonPics/Weezy.png")));
        pokemonList.add(new Pokemon("pidgeotto", 18, 4, new ImageIcon("/pokemonPics/pidgeotto.png")));
        pokemonList.add(new Pokemon("bulbasaur", 18, 4, new ImageIcon("/pokemonPics/bulbasaur.png")));

    }
}
