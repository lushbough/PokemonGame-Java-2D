package com.pokemon.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pokemon.game.GameConstants;
import com.pokemon.game.PokemonGame;
import com.pokemon.game.gameObjects.Player;
import com.pokemon.game.gameObjects.Pokemon;
import com.pokemon.game.gameObjects.PokemonDataBase;

import java.awt.image.RenderedImage;

public class CatchScreen { }
    /*
    private PokemonGame game;
    private TextureRegion s;
    private OrthographicCamera cam;
    private Pokemon appearing;
    private Sprite mapSprite;



    public CatchScreen(PokemonGame game) {
        this.game = game;

    }

    @Override
    public void show() {

        cam = new OrthographicCamera(30, 30);
        cam.position.set(cam.viewportWidth/1f, cam.viewportWidth/1f, 0);
        mapSprite = new Sprite(new Texture((TextureData) GameConstants.defaultDownStance));
        mapSprite.setPosition(200, 200);
        mapSprite.setSize(32, 32);


//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean keyDown(int keyCode) {
//                if (keyCode == Input.Keys.ENTER) {
//                    if(MainGameScreen.player.getPlayerPokemons() == null) return false;
//
//                    MainGameScreen.player.getPlayerPokemons().put(appearing.getName(), appearing);
//                    game.setScreen(new MainGameScreen(game));
//                }
//                return true;
//            }
//        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        cam.update();
//        game.batch.setProjectionMatrix(cam.combined);

        this.appearing = PokemonDataBase.pokemonCollection.get("charzard");


        game.getBatch().begin();

        game.font.draw(game.getBatch(), "Pokemon " + appearing.getName() +" with attack= " + appearing.getAttack() + ", health= " + appearing.getHealth() + ", appears!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.getBatch(), "Press enter to catch or press spacebar to go back", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
//        s.draw(game.batch);
        game.getBatch().draw(GameConstants.defaultDownStance, Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);

//        game.batch.draw(GameConstants.defaultDownStance, 400, 380);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.update();
//        game.batch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }



    @Override
    public void dispose() {
        game.dispose();
    }
}
   */