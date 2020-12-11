package com.pokemon.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import javax.xml.soap.Text;

public class Player extends Sprite implements InputProcessor {
    private Vector2 position = new Vector2();
    private float speed = 140;
    private float weight = 40;
    private Texture image = new Texture("tileset-chars.png");
    TextureRegion[][] mainActor = TextureRegion.split((image), 32, 32);


    public Player(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }


    public void update(float delta) {


        setX(getX() + position.x * delta); //reposition with frames
        setY(getY() + position.y * delta);

    }

    public float getSpeed() {
        return speed;
    }

    public float getWeight() {
        return weight;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public TextureRegion[][] getMainActor() {
        return mainActor;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                this.position.y = speed;
                break;
            case Input.Keys.S:
                this.position.y = -speed;
                break;
            case Input.Keys.A:
                this.position.x = -speed;
                break;
            case Input.Keys.D:
                this.position.x = speed;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) { switch (keycode) {
        case Input.Keys.W:
            this.position.y = 0;
            break;
        case Input.Keys.S:
            this.position.y = 0;
            break;
        case Input.Keys.A:
            this.position.x = 0;
            break;
        case Input.Keys.D:
            this.position.x = 0;
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
