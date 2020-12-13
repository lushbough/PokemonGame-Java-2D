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

public class Player extends Sprite implements InputProcessor {
    private Vector2 velocity = new Vector2();
    private float speed = 140;
    private float currentSpeed;
    private float weight = 40;
    private int row, col;

    private TiledMapTileLayer collisionSpot;
//    private static TextureRegion playerLook = mainActor[0][0];


    public Player(Sprite batch, TiledMapTileLayer collisionLayer) {
        super(batch);
        this.collisionSpot = collisionLayer;
//        this.currentSpeed = playerSpeed;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);


    }


    public void update(float delta) {
        float oldX = getX();
        float oldY = getY();
        float tileWidth = collisionSpot.getTileWidth();
        float tileHeight = collisionSpot.getTileHeight();
        boolean collisionX = false;
        boolean collisionY = false;
        int playerX = (int) (getX() / tileWidth);
        int playerY = (int) (getY() / tileHeight);
        boolean speedStatus;

        setX(getX() + this.velocity.x * delta); //reposition with frames
        setY(getY() + this.velocity.y * delta);

        //Because I'm using if statements, it prioritzes up and left in checking collision

        if(velocity.x < 0) {
           collisionX = isCellBlocked(playerX - 1, playerY);
           if(!collisionX) collisionX = isCellBlocked(playerX - 1, playerY -1);
           if(!collisionX) collisionX = isCellBlocked(playerX - 1, playerY + 1);

        } else if(velocity.x > 0) {
            collisionX = isCellBlocked(playerX + 1, playerY);
            if(!collisionX) collisionX = isCellBlocked(playerX + 1, playerY -1);
            if(!collisionX) collisionX = isCellBlocked(playerX + 1, playerY + 1);
        }

        if(collisionX) {
            setX(oldX);
            setY(oldY);
            this.velocity.x = 0;
        }

        if (velocity.y < 0) {
                collisionY = isCellBlocked(playerX , playerY - 1);
                if(!collisionY) collisionY = isCellBlocked(playerX - 1, playerY -1);
                if(!collisionY) collisionY = isCellBlocked(playerX + 1, playerY - 1);

        }else if (velocity.y > 0) {
                //top left
            collisionY = isCellBlocked(playerX , playerY + 1);
            if(!collisionY) collisionY = isCellBlocked(playerX + 1, playerY + 1);
            if(!collisionX) collisionY = isCellBlocked(playerX - 1, playerY + 1);
            }

        if(collisionY) {
            setY(oldY);
            setX(oldX);
            this.velocity.y = 0;
        }

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


    private boolean isCellBlocked(float x, float y){
        TiledMapTileLayer.Cell cell = collisionSpot.getCell((int)x, (int) y);
        if (cell == null) return false;            // not sure if this is needed
        if (cell.getTile() == null) return false;  // probably only tiles can be null

        MapProperties properties = cell.getTile().getProperties();
        if (properties == null) return false;      // also not sure if it can be null

        return properties.containsKey("blocked");    }

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
