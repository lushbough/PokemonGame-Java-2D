package com.pokemon.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.pokemon.game.GameConstants;
import com.pokemon.game.PokemonGame;
import com.pokemon.game.gameObjects.Enemy;
import com.pokemon.game.gameObjects.Player;
import com.pokemon.game.gameObjects.PokemonDataBase;
import com.pokemon.game.gameplay.Action;
import com.pokemon.game.gameplay.Collisions;

import javax.swing.*;

public class MainGameScreen implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    public Player player;
    private int grassCounter;
    private PokemonGame game;
    boolean grassCheck;
    boolean enemyCheck;
    private Music platformMusic;

    public MainGameScreen(PokemonGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("newmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        platformMusic = Gdx.audio.newMusic(Gdx.files.internal("pokeBG.mp3"));
        platformMusic.setLooping(true);
        platformMusic.play();

        player = new Player(new Sprite( (GameConstants.defaultDownStance)), (TiledMapTileLayer) map.getLayers().get(1));
        player.getCurrentPokemon().put("pikachu",PokemonDataBase.pokemonList.get(2));
        player.setPosition(250, 665);
        grassCounter = 0;
        grassCheck = false;
        enemyCheck = false;

        //Adding Multiplexer just in case we need multiple class InputProcessors
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(player);
        Gdx.input.setInputProcessor(multiplexer);


        //Give the User some basic instructions on how to play.
        JOptionPane.showMessageDialog(null, "Your first pokemon is Pikachu! \n" +
                "\n" +
                "Go in the grass to catch a new Pokemon  \n" +
                "You can flea if you do not want the pokemon  \n" +
                "You must exit then reenter the grass area to catch another pokemon.  \n" +
                "Remember, you can only have 3 Pokemon  \n" +
                "\n" +
                "Fight the boss when you're ready");
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        camera.update();
        renderer.setView(camera);

        Collisions.checkBorders(player);
        Collisions.checkBlockedCollisions(player);

        renderer.getBatch().begin();

        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("botLayer"));

        //draw player
        player.draw(renderer.getBatch());
        //end draw player
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("topLayer"));
        renderer.getBatch().end();

        this.grassCheck = player.isCellGrass(player.getX() / player.getCollisionLayer().getTileWidth(), player.getY() / player.getCollisionLayer().getTileHeight());
        if (grassCheck) {
            grassCounter++;
            System.out.println(grassCounter);

            if (grassCounter == 100) {
                Action.catchPokemon(player);
            }
        }else grassCounter = 0; //reset counter to reenter grass

        this.enemyCheck = player.isCellEnemy(player.getX() / player.getCollisionLayer().getTileWidth(), player.getY() / player.getCollisionLayer().getTileHeight());
        if(enemyCheck) {
            if(!(player.getCurrentPokemon().size() < 3)) {
                boolean isVictory = Action.battleEnemy(player, new Enemy());
                if (isVictory){
                    JOptionPane.showMessageDialog(null, "You have won the game! + \n Keep playing or close the application");
                } else {
                    JOptionPane.showMessageDialog(null, "You have lost the game. + \n Catch stronger Pokemon or close the application! ");
                }
            } else JOptionPane.showMessageDialog(null, "You must have 3 pokemon to battle  \n" + "You currently have " + player.getCurrentPokemon().size());
        }
    }


    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height /1f;
        camera.viewportWidth = width /1f;
        camera.update();
    }


    public PokemonGame getGame() {
        return game;
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
        platformMusic.dispose();
    }
}
