package com.pokemon.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.pokemon.game.GameConstants;
import com.pokemon.game.gameObjects.Player;

public class MainGameScreen implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Player player;

    @Override
    public void show() {
        map = new TmxMapLoader().load("newmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        player = new Player(new Sprite(new Texture("player.png")));
        player.setPosition(300, 300);

        Gdx.input.setInputProcessor(player);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        camera.position.set(player.getX() + player.getWidth() /2, player.getY() + player.getHeight() /2,0);
        camera.update();
        renderer.setView(camera);
        if(player.getX() < 0 ) {
            player.setX(player.getX() + player.getSpeed());
        }
        if(player.getX() > GameConstants.MAPWIDTH) {
            player.setX(player.getX() - player.getSpeed());
        }
        if(player.getY() > GameConstants.MAPHEIGHT) {
            player.setY(player.getY() - player.getSpeed());
        }
        if(player.getY() < 0) {
            player.setY(player.getY() + player.getSpeed());
        }


        renderer.getBatch().begin();

        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("botLayer"));

        //draw player
        player.draw(renderer.getBatch());
        //end draw player
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("topLayer"));
        renderer.getBatch().end();



    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height /2f;
        camera.viewportWidth = width /2f;
//        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //above line helps show the whole game in the screen, also shows the second layer for some reason
        camera.update();
    }



    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        player.getTexture().dispose();
        renderer.dispose();
    }
}
