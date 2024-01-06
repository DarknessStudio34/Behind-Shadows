package com.ds.behsha;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class dayOne implements Screen {
    BehSha game;
    Stage stage;
    BitmapFont font;
    Texture ATK, DEF, MAG, ARR, INV, MAP, cima, baixo, esquerda, direita;
    float xOffset, yOffset, renderWidth, renderHeight;
    AssetManager manager = new AssetManager();
    private SpriteBatch batch;

    public dayOne(BehSha game, BitmapFont font, Texture ATK, Texture DEF, Texture MAG, Texture ARR, Texture INV, Texture MAP, Texture cima, Texture baixo, Texture esquerda, Texture direita){
        this.game = game;
        stage = new Stage();
        this.font = font;
        this.ATK = ATK;
        this.DEF = DEF;
        this.MAG = MAG;
        this.ARR = ARR;
        this.INV = INV;
        this.MAP = MAP;
        this.cima = cima;
        this.baixo = baixo;
        this.esquerda = esquerda;
        this.direita = direita;
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.0f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
