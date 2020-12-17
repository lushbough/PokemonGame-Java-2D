package com.pokemon.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.pokemon.game.GameConstants;
import com.pokemon.game.PokemonGame;
import com.pokemon.game.screens.CatchScreen;
import com.pokemon.game.screens.MainGameScreen;

import java.util.HashMap;

public class Player extends Sprite implements InputProcessor {
    private Vector2 velocity = new Vector2();
    private float speed = 140;
    private float currentSpeed;
    private float weight = 40;
    private int row, col;
    private int grassCounter = 0;
    private PokemonGame game;
    private HashMap<String, Pokemon> playerPokemons;

    private TiledMapTileLayer collisionSpot;
//    private static TextureRegion playerLook = mainActor[0][0];


    public Player(Sprite batch, TiledMapTileLayer collisionLayer) {
        super(batch);
        this.collisionSpot = collisionLayer;
        playerPokemons = new HashMap<>();
//        this.currentSpeed = playerSpeed;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }


    public void update(float delta) {

    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public String[] generatePokemonList(){
        String[] list = new String[0];
        int i = 0;
        getPlayerPokemons().size();
        for(Pokemon p : this.getPlayerPokemons().values()) {
           list[i] = p.getName();
           i++;
        }

        return list;
    }

    public void getPokemon(String name){

    }
    public HashMap<String, Pokemon> getPlayerPokemons() {
        return playerPokemons;
    }

    public void setPlayerPokemons(HashMap<String, Pokemon> playerPokemons) {
        this.playerPokemons = playerPokemons;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionSpot;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionSpot = collisionLayer;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public float getWeight() {
        return weight;
    }

    public boolean isCellEnemy(float x, float y){
        TiledMapTileLayer.Cell cell = collisionSpot.getCell((int)x, (int) y);
        if (cell == null) return false;            // not sure if this is needed
        if (cell.getTile() == null) return false;  // probably only tiles can be null

        MapProperties properties = cell.getTile().getProperties();
        if (properties == null) return false;      // also not sure if it can be null

        return properties.containsKey("firstPokemon");    }

    public boolean isCellFirstPokeMon(float x, float y){
        TiledMapTileLayer.Cell cell = collisionSpot.getCell((int)x, (int) y);
        if (cell == null) return false;            // not sure if this is needed
        if (cell.getTile() == null) return false;  // probably only tiles can be null

        MapProperties properties = cell.getTile().getProperties();
        if (properties == null) return false;      // also not sure if it can be null

        return properties.containsKey("firstPokemon");    }

    public boolean isCellGrass(float x, float y){
        TiledMapTileLayer.Cell cell = collisionSpot.getCell((int)x, (int) y);
        if (cell == null) return false;            // not sure if this is needed
        if (cell.getTile() == null) return false;  // probably only tiles can be null

        MapProperties properties = cell.getTile().getProperties();
        if (properties == null) return false;      // also not sure if it can be null

        return properties.containsKey("catchGrass");    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                this.setRegion(GameConstants.defaultUpStance);
                this.velocity.y = speed;
                break;
            case Input.Keys.S:
                this.setRegion(GameConstants.defaultDownStance);
                this.velocity.y = -speed;
                break;
            case Input.Keys.A:
                this.setRegion(GameConstants.defaultLeftStance);
                this.velocity.x = -speed;
                break;
            case Input.Keys.D:
                this.setRegion(GameConstants.defaultRightStance);
                this.velocity.x = speed;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) { switch (keycode) {
        case Input.Keys.W:
        case Input.Keys.S:
            this.velocity.y = 0;
            break;
        case Input.Keys.A:
        case Input.Keys.D:
            this.velocity.x = 0;
            break;
    }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
