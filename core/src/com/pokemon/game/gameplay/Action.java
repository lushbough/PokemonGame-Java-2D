package com.pokemon.game.gameplay;

import com.pokemon.game.gameObjects.Enemy;
import com.pokemon.game.gameObjects.Player;
import com.pokemon.game.gameObjects.Pokemon;
import com.pokemon.game.gameObjects.PokemonDataBase;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Map;

public class Action {

    public static boolean battleEnemy(Player player, Enemy enemy){
        boolean didWin = false;
        boolean exit = true;
        int i = 0;
        int j = 0;
        LinkedList<Pokemon> enemyPokemons = new LinkedList<>();
        LinkedList<Pokemon> playerPokemons = new LinkedList<>();
        for(Map.Entry<String, Pokemon> entry : enemy.getCurrentPokemon().entrySet()) {
            enemyPokemons.add(entry.getValue());
        }
        for(Map.Entry<String, Pokemon> entry : player.getCurrentPokemon().entrySet()) {
            playerPokemons.add(entry.getValue());
        }

//        String[] list = player.generatePokemonList();
//        int pokeChoice = JOptionPane.showOptionDialog(null, "Choose a Pokemon to Fight With!", "PokemonList", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, list, list[0]);
//        Pokemon playerPokemon = player.getPokemon(list[pokeChoice]);


        int playerHealth = playerPokemons.get(i).getHealth();
        int enemyPokemonHealth = enemyPokemons.get(j).getHealth();
        do {


            String[] options = {"Attack", "Flea"};
            int choice = JOptionPane.showOptionDialog(null, "Defeat the enemy! \n" +
                            "When a Pokemon dies the next Pokemon will fight!\n" +
                            "---------------------------------\n" +
                            "Enemy Pokemon: \n" +
                            "Name: " + enemyPokemons.getLast().getName() + "\n" +
                            "Health: " + enemyPokemonHealth + "\n" +
                            "Attack: " + enemyPokemons.getLast().getAttack() + "\n" +
                            "---------------------------------\n" +
                            "\n" +
                            "Your Pokemon: \n" +
                            "Name: " + playerPokemons.getLast().getName() + "\n" +
                            "Health: " + playerHealth + "\n" +
                            "Attack: " + playerPokemons.getLast().getAttack() + "\n" +
                            "---------------------------------\n",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            System.out.println(choice);

            switch (choice) {


                case 0: {
                    enemyPokemonHealth = enemyPokemonHealth - playerPokemons.getLast().getAttack();

                    if (enemyPokemonHealth > 0) {
                        playerHealth = playerHealth - enemyPokemons.getLast().getAttack();
                        JOptionPane.showMessageDialog(null, enemyPokemons.getLast().getName() + " strikes back!");
                    }

                    if (playerHealth <= 0) {
                        playerPokemons.remove();
                        if (playerPokemons.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "You have been defeated!");
                            didWin = false;
                            exit = false;
                        }
                        if (!playerPokemons.isEmpty()) {
                            i++;
                            playerHealth = playerPokemons.getLast().getHealth();
                        }
                    }
                    if (enemyPokemonHealth <= 0) {
                        enemyPokemons.remove();
                        if (enemyPokemons.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "You have won!");
                            didWin = true;
                            exit = false;
                        }
                        if (!enemyPokemons.isEmpty()) {
                            j++;
                            enemyPokemonHealth = enemyPokemons.getLast().getHealth();
                        }
                    }
                    break;
                }
                case 1: {
                    exit = false;
                    break;
                }
            }
        } while (exit); //exit battle sequence
        return didWin;
    }

    public static void catchPokemon(Player player) {
        int rand = (int) (Math.random() * PokemonDataBase.pokemonList.size());
        if (rand == 2) {
            rand = (int) (Math.random() * PokemonDataBase.pokemonList.size());
        }
        Pokemon newPokemon = PokemonDataBase.pokemonList.get(rand);
        boolean exit = true;

        String[] list = player.generatePokemonList();
        int pokeChoice = JOptionPane.showOptionDialog(null, "Choose a Pokemon to Fight With!", "PokemonList", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, list, list[0]);
        Pokemon playerPokemon = player.getPokemon(list[pokeChoice]);


        int playerHealth = playerPokemon.getHealth();
        int enemyHealth = newPokemon.getHealth();
        do {

            String[] options = {"Attack", "Flea"};
            int choice = JOptionPane.showOptionDialog(null, "Defeat this Pokemon To Capture it! \n" +
                            "---------------------------------\n" +
                            "Enemy Pokemon: \n" +
                            "Name: " + newPokemon.getName() + "\n" +
                            "Health: " + enemyHealth + "\n" +
                            "Attack: " + newPokemon.getAttack() + "\n" +
                            "---------------------------------\n" +
                            "\n" +
                            "Your Pokemon: \n" +
                            "Name: " + playerPokemon.getName() + "\n" +
                            "Health: " + playerHealth + "\n" +
                            "Attack: " + playerPokemon.getAttack() + "\n" +
                            "---------------------------------\n",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            System.out.println(choice);

            switch (choice) {


                case 0: {
                    enemyHealth = enemyHealth - playerPokemon.getAttack();
                    if (enemyHealth <= 0) {
                        JOptionPane.showMessageDialog(null, "pokemon caught!");

                        if (player.getCurrentPokemon().size() >= 3) {
                            int input = JOptionPane.showConfirmDialog(null,
                                    "Pokeballs Full! + \n +Press YES to replace a Pokemon + \n +Press NO to Flea!", "Select an Option...", JOptionPane.YES_NO_CANCEL_OPTION);
                            switch (input) {
                                case 0:
                                    int removeThisOne = JOptionPane.showOptionDialog(null, "Choose a Pokemon to Replace!", "PokemonList", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, list, list[0]);
                                    player.getCurrentPokemon().remove(list[removeThisOne]);
                                    JOptionPane.showMessageDialog(null, "pokemon removed!");
                                    break;
                                case 1:
                                    exit = false;
                                    break;
                            }
                        }
                        player.getCurrentPokemon().put(newPokemon.getName(), newPokemon);
                        exit = false;
                    }

                    if (enemyHealth > 0) {
                        playerHealth = playerHealth - newPokemon.getAttack();
                        JOptionPane.showMessageDialog(null, newPokemon.getName() + " strikes back!");
                    }

                    if (playerHealth < 0) {
                        JOptionPane.showMessageDialog(null, "You're defeated!");
                        exit = false;
                    }
                    break;
                }
                case 1: {
                    exit = false;
                    break;
                }
            }
        } while (exit); //exit catch pokemon sequence
    }
}


