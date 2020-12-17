package com.pokemon.game.gameObjects;

import java.util.HashMap;
import java.util.Map;

public class Enemy {
    private HashMap<String, Pokemon> currentPokemon;

    public HashMap<String, Pokemon> getCurrentPokemon() {
        return currentPokemon;
    }

    public Enemy() {
        currentPokemon = new HashMap<>();
        loadPokemon();
    }

    private void loadPokemon() {
        currentPokemon.put("squirtle", PokemonDataBase.pokemonList.get(0));
        currentPokemon.put("charmander", PokemonDataBase.pokemonList.get(1));
        currentPokemon.put("pikachu", PokemonDataBase.pokemonList.get(2));
    }

    public String[] generatePokemonList() {
        String[] list = new String[getCurrentPokemon().size()];
        int i = 0;
        getCurrentPokemon().size();
        for (Map.Entry<String, Pokemon> entry : this.getCurrentPokemon().entrySet()) {
            list[i] = entry.getValue().getName();
            i++;
        }

        return list;
    }

}
