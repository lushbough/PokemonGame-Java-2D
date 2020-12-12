package com.pokemon.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameConstants {
    public static final int GAMEWIDTH = 480;
    public static final int GAMEHEIGHT = 720;
    public static final int MAPWIDTH = 800;
    public static final int MAPHEIGHT = 800;


    //Player Images
    public static Texture playerOneImageSet = new Texture("tileset-chars.png");
    public static TextureRegion[][] mainActor= TextureRegion.split((playerOneImageSet), 32, 32);//grabs index of player image for movement
    public static TextureRegion defaultDownStance = mainActor[0][0];
    public static TextureRegion defaultUpStance = mainActor[1][0];
    public static TextureRegion defaultLeftStance = mainActor[1][1];
    public static TextureRegion defaultRightStance = mainActor[0][1];


}
