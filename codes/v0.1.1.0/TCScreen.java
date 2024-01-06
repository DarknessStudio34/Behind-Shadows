package com.ds.behsha;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.files.FileHandle;



public class TCScreen implements Screen {
    public String version;
    
    private Texture tcBg;
    private SpriteBatch batch;
    
    private BitmapFont font;
    
    private BehSha game;
    
    private Animation<TextureRegion> animation;
    private float stateTime;
    private boolean animationFinished;
    
    public TCScreen(BehSha game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        if (game.propor == 20.9f){
            tcBg = new Texture("loading/tCbg-20-9.png");
        } else if (game.propor == 16.9f){
            tcBg = new Texture("loading/tCbg-16-9.png");
        } else if (game.propor == 16.1f){
            tcBg = new Texture("loading/tCbg-16-10.png");
        } else {
            tcBg = new Texture("loading/tCbg-16-9.png");
        }

        
        
        //código responsável por ler o arquivo version.txt e repassar seu conteúdo para a variável version.
        
        FileHandle fileHandle = Gdx.files.internal("version.txt");
        version = fileHandle.readString();
        
        
        //animação barra - carregamento
        
        TextureRegion image1 = new TextureRegion(new Texture("loading/br-01.png"));
        TextureRegion image2 = new TextureRegion(new Texture("loading/br-02.png"));
        TextureRegion image3 = new TextureRegion(new Texture("loading/br-03.png"));
        TextureRegion image4 = new TextureRegion(new Texture("loading/br-04.png"));
        TextureRegion image5 = new TextureRegion(new Texture("loading/br-05.png"));

        Array<TextureRegion> images = new Array<>();
        images.addAll(image1, image2, image3, image4, image5);

        animation = new Animation<>(0.5f, images);

        stateTime = 0f;
        animationFinished = false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(game.camera.combined);
        
        stateTime += delta;
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, false);

        batch.begin();
        
        batch.draw(tcBg, 0, 0, game.wS, game.hS);
        
        batch.draw(currentFrame, 0, 0);
        
        font.draw(batch, "resolução:" + game.wS + "x" + game.hS, game.wS, game.hS);
        font.draw(batch, version, 10, 25);
        
        
        batch.end();

        // Verificação do fim da animação
        if (animation.isAnimationFinished(stateTime) && !animationFinished) {
            
            //caso já tenha terminado a variável 'animationFinished' é definida como true e a tela é passada para MPScreen.
            
            animationFinished = true;
            if (game.propor == 20.9f){
                game.setScreen(new MPScreen(game, new Texture("UI/menuStart/mpBg01-20-9.png")));
            } else if (game.propor == 16.9f){
                game.setScreen(new MPScreen(game, new Texture("UI/menuStart/mpBg01-16-9.png")));
            } else if (game.propor == 16.1f){
                game.setScreen(new MPScreen(game, new Texture("UI/menuStart/mpBg01-16-10.png")));
            } else {
                game.setScreen(new MPScreen(game, new Texture("UI/menuStart/mpBg01-16-9.png")));
            }

            
        }
    }

    @Override
    public void resize(int width, int height) {
        game.wS = width;
        game.hS = height;
        game.camera.setToOrtho(false, width, height);
        game.camera.update();
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
        tcBg.dispose();
        // Dispose das texturas das imagens
        for (TextureRegion image : animation.getKeyFrames()) {
            image.getTexture().dispose();
        }
        font.dispose();
    }
}
