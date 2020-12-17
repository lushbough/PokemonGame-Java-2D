package com.pokemon.game.gameObjects;
import javax.swing.*;

public class Pokemon {
    private int health;
    private int xp;
    private int attack;
    private String name;
    private ImageIcon pic;

    public Pokemon(String name, int health, int attack, ImageIcon pic) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.pic = pic;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }


    public String getName() {
        return name;
    }

}
