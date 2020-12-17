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

import java.util.HashMap;
import java.util.Map;

public class Player extends Sprite implements InputProcessor {
    private Vector2 velocity = new Vector2();
    private float speed = 140;
    private HashMap<String, Pokemon> currentPokemon;

    private TiledMapTileLayer collisionLayer;


    public Player(Sprite batch, TiledMapTileLayer collisionLayer) {
        super(batch);
        this.collisionLayer = collisionLayer;
        currentPokemon = new HashMap<>();
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

    public Pokemon getPokemon(String name) {
        return this.currentPokemon.get(name);
    }

    public HashMap<String, Pokemon> getCurrentPokemon() {
        return currentPokemon;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public float getSpeed() {
        return speed;
    }


    public boolean isCellEnemy(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) x, (int) y);
        if (cell == null) return false;            // not sure if this is needed
        if (cell.getTile() == null) return false;  // probably only tiles can be null

        MapProperties properties = cell.getTile().getProperties();
        if (properties == null) return false;      // also not sure if it can be null

        return properties.containsKey("enemy");
    }


    public boolean isCellGrass(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) x, (int) y);
        if (cell == null) return false;
        if (cell.getTile() == null) return false;

        MapProperties properties = cell.getTile().getProperties();
        if (properties == null) return false;

        return properties.containsKey("catchGrass");
    }

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
    public boolean keyUp(int keycode) {
        switch (keycode) {
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
