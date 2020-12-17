package com.pokemon.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pokemon.game.GameConstants;
import com.pokemon.game.PokemonGame;
import com.pokemon.game.gameObjects.Player;
import com.pokemon.game.gameObjects.Pokemon;
import com.pokemon.game.gameObjects.PokemonDataBase;
import com.pokemon.game.gameplay.Collisions;

import javax.swing.*;
import java.awt.*;

public class MainGameScreen implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    public static Player player;
    private int grassCounter;
    private PokemonGame game;
    boolean grassCheck;
    Skin skin;
    private Stage stage;
    Dialog dialog;

    public MainGameScreen(PokemonGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("newmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        player = new Player(new Sprite( (GameConstants.defaultDownStance)), (TiledMapTileLayer) map.getLayers().get(1));
        player.getPlayerPokemons().put("Pikachu",PokemonDataBase.pokemonList.get(2));
        player.setPosition(((TiledMapTileLayer) map.getLayers().get(0)).getTileWidth() * 7, ((TiledMapTileLayer) map.getLayers().get(0)).getTileHeight() * 8);
        grassCounter = 0;
        grassCheck = false;
        skin = new Skin(Gdx.files.internal("skin/vhs-ui.json"));
        stage = new Stage();
        //extend viewport or

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(player);
        Gdx.input.setInputProcessor(multiplexer);

        JOptionPane.showMessageDialog(null, "Your first pokemon is Pikachu! +\n" +
                "Go in the grass to fight/catch a new Pokemon  \n" +
                "You can flea if you do not want the pokemon  \n" +
                "Remember, you can only have 3 Pokemon  \n" +
                "Fight the boss when you're ready");
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        camera.position.set(player.getX() + player.getWidth() /2, player.getY() + player.getHeight() /2,0);
        camera.update();
        stage.act(delta);
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
        getStage().draw();

        this.grassCheck = player.isCellGrass(player.getX() /player.getCollisionLayer().getTileWidth(), player.getY() /player.getCollisionLayer().getTileHeight());
        if (grassCheck) {
            grassCounter++;
            System.out.println(grassCounter);

            if(grassCounter == 100) {
                int rand = (int) (Math.random() * PokemonDataBase.pokemonList.size());
                if(rand == 2) {rand = (int) (Math.random() * PokemonDataBase.pokemonList.size());}
                Pokemon newPokemon = PokemonDataBase.pokemonList.get(rand);
                boolean exit = true;

                String[] list = player.generatePokemonList();
                int pokeChoice = JOptionPane.showOptionDialog(null,"Choose a Pokemon to Fight With!", "PokemonList", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, list, list[0]);
                do {
                    String[] options = {"Flea", "Punch!"};
                    int choice = JOptionPane.showOptionDialog(null, "Punch this Pokemon To Capture it! \n" +
                                    "Name: " + newPokemon.getName() +"\n" +
                                    "Health: " + newPokemon.getHealth() +"\n"+
                                    "Attack: " + newPokemon.getAttack() +"\n",
                            "Click a button",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    System.out.println(choice);

                    switch (choice) {
                        case 0:
                            exit = false;
                            break;
                        case 1:
                            newPokemon.setHealth(newPokemon.getHealth() - 2);
                            if(newPokemon.getHealth() <= 0) {
                                JOptionPane.showMessageDialog(null, "pokemon caught!");
                                exit = false;
                                break;
                    }

                    }
                } while (exit);
                }
            } else grassCounter = 0; //reset counter to reenter grass

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height /1f;
        camera.viewportWidth = width /1f;
//        stage.getViewport().update(width, height);
//        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //above line helps show the whole game in the screen, also shows the second layer for some reason
        stage.getCamera().viewportWidth = Gdx.graphics.getWidth();
        stage.getCamera().viewportHeight = Gdx.graphics.getHeight();
        camera.update();
    }

    public Stage getStage() {
        return stage;
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
        stage.dispose();
    }
}
