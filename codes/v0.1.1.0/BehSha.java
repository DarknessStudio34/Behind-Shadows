package com.ds.behsha;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BehSha extends Game {
    private Screen tCScreen, mPScreen, sceneOne, dayOne;
    private SpriteBatch batch;
    public float propor;
    BitmapFont font;
    public ScreenConfig screenConfig;

    public int wS, hS;
    Texture ATK, DEF, MAG, ARR, INV, MAP, cima, baixo, esquerda, direita;

    public OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        screenConfig = new ScreenConfig();
        wS = Gdx.graphics.getWidth();
        hS = Gdx.graphics.getHeight();
        propor = screenConfig.proporcao();

        // Configurar a câmera com base na proporção da tela
        configureCamera(wS, hS);

        Texture mpBgTexture;
        if(propor == 20.9f){
            mpBgTexture = new Texture("UI/menuStart/mpBg01-20-9.png");
        } else if (propor == 16.9f){
            mpBgTexture = new Texture("UI/menuStart/mpBg01-16-9.png");
        } else if (propor == 16.1f){
            mpBgTexture = new Texture("UI/menuStart/mpBg01-16-10.png");
        } else {
            mpBgTexture = new Texture("UI/menuStart/mpBg01-16-9.png");
        }


        tCScreen = new TCScreen(this);
        setScreen(tCScreen);
        mPScreen = new MPScreen(this, mpBgTexture);
        sceneOne = new SceneOne(this);
        dayOne = new dayOne(this, font, ATK, DEF, MAG, ARR, INV, MAP, cima, baixo, esquerda, direita);
    }

    @Override
    public void render() {
        // Limpar a tela

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Configurar a matriz de projeção da câmera
        batch.setProjectionMatrix(camera.combined);

        super.render();
    }

    @Override
    public void resize(int width, int height) {
        // Atualizar a câmera ao redimensionar a tela
        configureCamera(width, height);

        // Atualizar o tamanho da tela para os Screens
        tCScreen.resize(width, height);
        mPScreen.resize(width, height);
        sceneOne.resize(width, height);
        dayOne.resize(width, height);

    }

    @Override
    public void dispose() {
        super.dispose();
        tCScreen.dispose();
        mPScreen.dispose();
        sceneOne.dispose();
        dayOne.dispose();
        batch.dispose();
    }

    public void setTC() {
        setScreen(tCScreen);
    }

    public void toggleFullScreen() {
        // Verificar se o jogo está em modo de tela cheia
        if (Gdx.graphics.isFullscreen()) {
            // Trocar para o modo janela
            Gdx.graphics.setWindowedMode(wS, hS);
        } else {
            // Trocar para o modo de tela cheia
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

            // Ocultar a barra de tarefas em modo de tela cheia
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
                Gdx.graphics.setWindowedMode(1280, 720); // Mova a janela para fora da tela
            }
        }

        // Reconfigurar a câmera após a troca
        configureCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void configureCamera(int screenWidth, int screenHeight) {
        float targetAspectRatio = 20f / 9f;

        // Calcular a proporção da tela antes da mudança
        float previousAspectRatio = (float) wS / hS;

        // Verificar se a proporção da tela mudou
        if (previousAspectRatio != targetAspectRatio) {
            // Recalcular a largura da câmera para manter a proporção da tela
            float newCameraWidth = targetAspectRatio * screenHeight;
            camera = new OrthographicCamera(newCameraWidth, screenHeight);
            camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
            camera.update();
        }
        // Se a proporção não mudou, manter a câmera existente

        // Atualizar as dimensões da tela original
        wS = screenWidth;
        hS = screenHeight;
    }
}
