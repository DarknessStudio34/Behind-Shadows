package com.ds.behsha;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MPScreen implements Screen {
    public String version;
    private Texture mpBg;
    private SpriteBatch batch;
    private BitmapFont font;
    private FreeTypeFontGenerator fontGenerator;

    private Stage stage;
    private Stage stage1;
    private boolean configAct = false;
    private BehSha game;
    private Texture config;


    public MPScreen(BehSha game, Texture mpBg) {
        this.game = game;
        this.mpBg = mpBg;
        stage = new Stage();
        stage1 = new Stage();
        Gdx.input.setInputProcessor(stage);
        //botões
        jogarBtn();
        opcoesBtn();
        xBtn();
        //fonte
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/MedievalSharp.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;  // Tamanho da fonte
        font = fontGenerator.generateFont(parameter);
    }
    public void xBtn(){
        TextureRegionDrawable xBtnImage = new TextureRegionDrawable(new TextureRegion(new Texture("UI/configs/x.png")));
        ImageButton xBtn = new ImageButton(xBtnImage);
        xBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (configAct == true) {
                    configAct = false;
                    Gdx.input.setInputProcessor(stage);
                }
            }
        });
        Table table = new Table();

        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            table.add(xBtn).size(game.hS * 0.08f).pad(10);
        } else {
            table.add(xBtn).size(game.hS * 0.1f).pad(10);
        }
        table.setPosition(game.wS * 0.898f, game.hS * 0.79f);
        stage1.addActor(table);
    }
    public void jogarBtn(){
        TextureRegionDrawable jogarBtnIgm = new TextureRegionDrawable(new TextureRegion(new Texture("UI/menuStart/jogarBtn.png")));
        ImageButton jogar = new ImageButton(jogarBtnIgm);
        jogar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (configAct == false) {
                    game.setScreen(new SceneOne(game));
                }
            }
        });
        Table table = new Table();
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            table.add(jogar).size(game.hS * 0.29f).pad(10);
        } else {
            table.add(jogar).size(game.hS * 0.39f).pad(10);
        }
        table.setPosition(game.wS * 0.9f, game.hS * 0.65f);
        stage.addActor(table);
    }

    public void opcoesBtn(){
        TextureRegionDrawable opcoesBtnIgm = new TextureRegionDrawable(new TextureRegion(new Texture("UI/menuStart/opcoesBtn.png")));
        ImageButton opcoes = new ImageButton(opcoesBtnIgm);
        opcoes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                configAct = true;
                Gdx.input.setInputProcessor(stage1);
            }
        });
        Table table = new Table();
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            table.add(opcoes).size(game.hS * 0.3f).pad(10);
            table.setPosition(game.wS * 0.9f, game.hS * 0.45f);
        } else {
            table.add(opcoes).size(game.hS * 0.4f).pad(10);
            table.setPosition(game.wS * 0.9f, game.hS * 0.35f);
        }



        stage.addActor(table);
    }



    @Override
    public void show() {
        batch = new SpriteBatch();
        config = new Texture("UI/configs/configInterface.png");
        FileHandle fileHandle = Gdx.files.internal("version.txt");
        version = fileHandle.readString();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(mpBg, 0, 0, game.wS, game.hS);
        font.draw(batch, "RESOLUÇÃO: " + game.wS + "X" + game.hS, 120, 25);
        font.draw(batch, version, 10, 25);
        if(configAct == true){
            if(game.propor == 16.9f){
                batch.draw(config, (float) game.wS * 0.321f, (float) game.hS * 0.0222f, game.wS * 0.6042f, game.hS * 0.8797f);
            } else if (game.propor == 16.1f){
                batch.draw(config, (float) game.wS * 0.3245f, (float) game.hS * 0.0972f, game.wS * 0.6042f, game.hS * 0.7917f);
            } else if (game.propor == 20.9){
                batch.draw(config, (float) game.wS * 0.475f, (float) game.hS * 0.0602f, game.wS * 0.4834f, game.hS * 0.8797f);
            } else {
                batch.draw(config, (float) game.wS * 0.321f, (float) game.hS * 0.0222f, game.wS * 0.6042f, game.hS * 0.8797f);
            }

        }
        batch.end();
        if (configAct == false){
            stage.act(delta);
            stage.draw();
        } else {
            stage1.act(delta);
            stage1.draw();
        }
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
        batch.dispose();
        font.dispose();
        mpBg.dispose();
        stage.dispose();
        stage1.dispose();
        config.dispose();
        fontGenerator.dispose();
    }
}

