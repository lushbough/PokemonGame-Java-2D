package com.pokemon.game.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;



public class GameDialog extends Dialog {
    private static Skin skin = new Skin(Gdx.files.internal("skin/vhs-ui.json"));

    public GameDialog(String title) {
        super(title, skin);
        padTop(60); // set padding on top of the dialog title
        getButtonTable().defaults().height(60); // set buttons height
        setModal(true);
        setMovable(false);
        setResizable(false);
    }

    @Override
    public GameDialog text(String text) {
        super.text(new Label(text, skin, "medium-green"));
        return this;
    }
    private void initialize() {

    }

    public GameDialog button(String buttonText, InputListener listener) {
        TextButton button = new TextButton(buttonText, skin);
        button.addListener(listener);
        button(button);
        return this;
    }

    @Override
    public float getPrefWidth() {
        // force dialog width
        return 480f;
    }

    @Override
    public float getPrefHeight() {
        // force dialog height
        return 240f;
    }
}
