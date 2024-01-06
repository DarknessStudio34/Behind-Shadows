package com.ds.behsha;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;


public class SceneOne implements Screen {
    private BehSha game;
    private SpriteBatch batch;
    private BitmapFont font, font1;
    private FreeTypeFontGenerator fontGenerator;
    Texture background0, fontAjust, background1, backAlan, backMike, backHO, speechAlan, speechMike, speechHO, backMike2, backAlan2, mapaLoad1;
    private Stage stage1, stage;
    private boolean sensorSpeech = false, dialog = true, isAnimationPlaying, fontIni = true, loaded = false, loadedNext = false;
    private Texture[] frames; // Array para armazenar as texturas dos frames da animação
    private int currentFrameIndex, sizeFont, stateSpeech = -1, falas = 1;
    private float frameDuration, stateTime, xOffset, yOffset, renderWidth, renderHeight, animationTime;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    Texture ATK, DEF, MAG, ARR, INV, MAP, cima, baixo, esquerda, direita;
    AssetManager manager = new AssetManager(), managerNext = new AssetManager();

    public SceneOne(BehSha game) {
        this.game = game;
        stage = new Stage();
        stage1 = new Stage();
        Gdx.input.setInputProcessor(stage1);
        fontAjustBtns();

        addTouchInputListener();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/MedievalSharp.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        // Tamanho da fonte
        parameter.size = 24;
        if (game.propor == 16.9f) {
            int sizeFont = (int) (game.hS * 0.026f);
            parameter.size = sizeFont;
        } else if (game.propor == 16.1f){
            int sizeFont = (int) (game.hS * 0.025f);
            parameter.size = sizeFont;
        }
        font = fontGenerator.generateFont(parameter);
        parameter.size = (int) (game.hS * 0.05f);
        font1 = fontGenerator.generateFont(parameter);


    }




    public void drawTextWithOutline(BitmapFont font, SpriteBatch batch, String text, float x, float y, Color textColor, Color outlineColor, float outlineWidth) {
        Color originalColor = font.getColor().cpy();
        for (float offsetX = -outlineWidth; offsetX <= outlineWidth; offsetX += 1) {
            for (float offsetY = -outlineWidth; offsetY <= outlineWidth; offsetY += 1) {
                if (offsetX != 0 || offsetY != 0) {
                    font.setColor(outlineColor);
                    font.draw(batch, text, x + offsetX, y + offsetY);
                }
            }
        }
        font.setColor(textColor);
        font.draw(batch, text, x, y);
        font.setColor(originalColor);
    }
    public void drawText (String textL1, String textL2, String textL3, String textL4, String textL5) {
        float x = game.wS * 0.283335f;
        Color textColor = new Color(45 / 255f, 34 / 255f, 24 / 255f, 1f); //cor da fonte
        Color outlineColor = new Color(30 / 255f, 22 / 255f, 14 / 255f, 1f); //cor contorno
        float outlineWidth = 1f; //largura contorno
        if(game.propor == 20.9f){
            drawTextWithOutline(font, batch, textL1, x, game.hS * 0.235f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL2, x, game.hS * 0.2f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL3, x, game.hS * 0.165f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL4, x, game.hS * 0.13f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL5, x, game.hS * 0.095f, textColor, outlineColor, outlineWidth);
        } else if (game.propor == 16.9f){
            drawTextWithOutline(font, batch, textL1, x, game.hS * 0.29f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL2, x, game.hS * 0.257f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL3, x, game.hS * 0.224f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL4, x, game.hS * 0.191f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL5, x, game.hS * 0.158f, textColor, outlineColor, outlineWidth);
        } else if (game.propor == 16.1f){
            drawTextWithOutline(font, batch, textL1, x, game.hS * 0.313f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL2, x, game.hS * 0.283f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL3, x, game.hS * 0.253f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL4, x, game.hS * 0.223f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL5, x, game.hS * 0.193f, textColor, outlineColor, outlineWidth);
        } else if(game.propor == 19.59f){
            drawTextWithOutline(font, batch, textL1, x, game.hS * 0.229125f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL2, x, game.hS * 0.195f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL3, x, game.hS * 0.160875f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL4, x, game.hS * 0.12675f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, textL5, x, game.hS * 0.092625f, textColor, outlineColor, outlineWidth);
        }
        else {
            drawTextWithOutline(font, batch, "proporção não detectada", x, game.hS * 0.3f, textColor, outlineColor, outlineWidth);
            drawTextWithOutline(font, batch, String.valueOf(game.propor), x, game.hS * 0.2f, textColor, outlineColor, outlineWidth);

        }

    }
    public void setupFont(float delta, int size){
        if(stateSpeech == -1){
            batch.draw(background0, xOffset, yOffset, renderWidth, renderHeight);//x
            batch.draw(fontAjust, xOffset, yOffset, renderWidth, renderHeight);//x
            if(parameter.size != size){
                parameter.size = size;
                font.dispose();
                font = fontGenerator.generateFont(parameter);
            }
            if (game.propor == 20.9f){
                font1.draw(batch, "Ajuste o tamanho da fonte para a caixa de falas", game.wS / 4, game.hS * 0.75f);
            } else if(game.propor == 16.1f){
                font1.draw(batch, "Ajuste o tamanho da fonte para a caixa de falas", game.wS / 6, game.hS * 0.75f);
            } else if(game.propor == 16.9f){
                font1.draw(batch, "Ajuste o tamanho da fonte para a caixa de falas", game.wS / 5.25f, game.hS * 0.75f);
            } else {
                font1.draw(batch, "Ajuste o tamanho da fonte para a caixa de falas", game.wS / 5.25f, game.hS * 0.75f);

            }



            drawText("|--------------------------------------|", "|--------------------------------------|", "|--------------------------------------|", "|--------------------------------------|", "|--------------------------------------|");

            stage1.act(delta);
            stage1.draw();
        }
    }
    public void fontAjustBtns(){
        TextureRegionDrawable maisBtn = new TextureRegionDrawable(new TextureRegion(new Texture("UI/configs/maisBtn.png")));
        ImageButton mais = new ImageButton(maisBtn);
        mais.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sizeFont += 1;
            }
        });
        TextureRegionDrawable menosBtn = new TextureRegionDrawable(new TextureRegion(new Texture("UI/configs/menosBtn.png")));
        ImageButton menos = new ImageButton(menosBtn);
        menos.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sizeFont -= 1;
            }
        });
        TextureRegionDrawable okBtn = new TextureRegionDrawable(new TextureRegion(new Texture("UI/configs/ok.png")));
        ImageButton ok = new ImageButton(okBtn);
        ok.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stateSpeech = 0;
                Gdx.input.setInputProcessor(stage);
            }
        });
        Table table = new Table(), table1 = new Table(), table2 = new Table();

        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            table.add(mais).size(game.hS * 0.1f).pad(10);
            table1.add(menos).size(game.hS * 0.1f).pad(10);
            table2.add(ok).size(game.hS * 0.3f).pad(10);
        } else {
            table.add(mais).size(game.hS * 0.15f).pad(10);
            table1.add(menos).size(game.hS * 0.15f).pad(10);
            table2.add(ok).size(game.hS * 0.4f).pad(10);
        }
        table.setPosition(game.wS * 0.8f, game.hS * 0.5f);
        table1.setPosition(game.wS * 0.2f, game.hS * 0.5f);
        table2.setPosition(game.wS * 0.85f, game.hS * 0.13f);
        stage1.addActor(table);
        stage1.addActor(table1);
        stage1.addActor(table2);
    }

    public void addTouchInputListener() {
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (sensorSpeech == true) {
                    stateSpeech += 1;
                    if (stateSpeech == 27) {
                        stateSpeech = 1;
                    }
                }
                return true;
            }
        });
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        manager.load("scenes/sceneOne/backgrounds/back0.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/back1.png", Texture.class);
        manager.load("scenes/sceneOne/dialogs/backAlan2.png", Texture.class);
        manager.load("scenes/sceneOne/dialogs/backMike2.png", Texture.class);
        manager.load("UI/dialogs/fontAjust.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/a.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/b.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/c.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/d.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/e.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/f.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/g.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/h.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/i.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/j.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/k.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/l.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/m.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/n.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/o.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/p.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/q.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/r.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/s.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/t.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/u.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/v.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/w.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/x.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/y.png", Texture.class);
        manager.load("scenes/sceneOne/backgrounds/z.png", Texture.class);
        manager.load("UI/dialogs/Alan.png", Texture.class);
        manager.load("UI/dialogs/Mike.png", Texture.class);
        manager.load("UI/dialogs/Homem-Misterioso.png", Texture.class);
        manager.load("scenes/sceneOne/dialogs/Alan-back.png", Texture.class);
        manager.load("scenes/sceneOne/dialogs/Mike-back.png", Texture.class);
        manager.load("scenes/sceneOne/dialogs/Homem-Misterioso-back.png", Texture.class);

        animationTime = 3.37f; // Tempo total da animação
        currentFrameIndex = 0;
        frameDuration = animationTime / 37;
        stateTime = 0.0f;
        isAnimationPlaying = false;

        managerNext.load("UI/btns/ATK.png", Texture.class);
        managerNext.load("UI/btns/DEF.png", Texture.class);
        managerNext.load("UI/btns/MAG.png", Texture.class);
        managerNext.load("UI/btns/INV.png", Texture.class);
        managerNext.load("UI/btns/MAP.png", Texture.class);
        managerNext.load("UI/btns/ARR.png", Texture.class);
        managerNext.load("UI/btns/cima.png", Texture.class);
        managerNext.load("UI/btns/baixo.png", Texture.class);
        managerNext.load("UI/btns/esquerda.png", Texture.class);
        managerNext.load("UI/btns/direita.png", Texture.class);
        managerNext.load("gameplay/mapa/Avalest/the_farm_area/the_shed1.png", Texture.class);
    }
    public void loads (){
        if (loaded == false){
            background1 = manager.get("scenes/sceneOne/backgrounds/back1.png", Texture.class);
            background0 = manager.get("scenes/sceneOne/backgrounds/back0.png", Texture.class);
            backAlan2 = manager.get("scenes/sceneOne/dialogs/backAlan2.png", Texture.class);
            backMike2 = manager.get("scenes/sceneOne/dialogs/backMike2.png", Texture.class);
            fontAjust = manager.get("UI/dialogs/fontAjust.png", Texture.class);
            frames = new Texture[]{
                    manager.get("scenes/sceneOne/backgrounds/a.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/b.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/c.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/d.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/e.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/f.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/g.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/h.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/i.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/j.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/k.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/l.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/m.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/n.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/o.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/p.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/q.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/r.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/s.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/t.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/u.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/v.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/v.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/v.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/u.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/t.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/s.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/r.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/q.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/p.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/o.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/n.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/w.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/x.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/y.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/z.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/z.png", Texture.class),
                    manager.get("scenes/sceneOne/backgrounds/z.png", Texture.class)
            };
            backAlan = manager.get("scenes/sceneOne/dialogs/Alan-back.png", Texture.class);
            backMike = manager.get("scenes/sceneOne/dialogs/Mike-back.png", Texture.class);
            backHO = manager.get("scenes/sceneOne/dialogs/Homem-Misterioso-back.png", Texture.class);
            speechAlan = manager.get("UI/dialogs/Alan.png", Texture.class);
            speechMike = manager.get("UI/dialogs/Mike.png", Texture.class);
            speechHO = manager.get("UI/dialogs/Homem-Misterioso.png", Texture.class);

            // Define as proporções de tela alvo
            float targetAspectRatio = 20f / 9f;

            // Calcula as dimensões reais do conteúdo da imagem
            float contentWidth = background1.getWidth();
            float contentHeight = background1.getHeight();
            float imageAspectRatio = contentWidth / contentHeight;

            // Calcula as dimensões finais para renderização

            if (imageAspectRatio > targetAspectRatio) {
                renderHeight = game.hS;
                renderWidth = renderHeight * imageAspectRatio;
            } else {
                renderWidth = game.wS;
                renderHeight = renderWidth / imageAspectRatio;
            }

            // Calcula as bordas pretas
            xOffset = (game.wS - renderWidth) / 2f;
            yOffset = (game.hS - renderHeight) / 2f;
            loaded = true;
        }
    }
    public void falas1(float delta){
        if(stateSpeech == 0){
            batch.draw(background1, xOffset, yOffset, renderWidth, renderHeight);
            font.setColor(new Color(45 / 255f, 34 / 255f, 24 / 255f, 1f));
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    stateSpeech = 1;
                }
            }, 3);
        }
        stage.act(delta);
        stage.draw();

        if(stateSpeech == 1){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Ele finalmente acordou.", " ", " ", " ", " " );
            if(sensorSpeech != true){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        sensorSpeech = true;
                    }
                }, 3);
            }
        }

        else if(stateSpeech == 2){
            batch.draw(backMike, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechMike, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Onde eu estou?", " ", " ", " ", " ");
        }

        else if(stateSpeech == 3){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Acredito que podemos começar.", " ", " ", " ", " ");
        }

        else if(stateSpeech == 4 || stateSpeech == 12 || stateSpeech == 14 || stateSpeech == 22){
            batch.draw(backAlan, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechAlan, xOffset, yOffset, renderWidth, renderHeight);
            drawText("...", " ", " ", " ", " ");
        }
        else if(stateSpeech == 5){
            batch.draw(backMike, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechMike, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Quem é esse cara?", " ", " ", " ", " ");
        }
        else if(stateSpeech == 6){
            batch.draw(backMike, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechMike, xOffset, yOffset, renderWidth, renderHeight);
            drawText("E quem é você?", " ", " ", " ", " ");
        }
        else if(stateSpeech == 7){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Bom, vou lhes contar sobre o que aconteceu.", " ", " ", " ", " ");
        }
        else if(stateSpeech == 8){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Primeiramente, Maicon Jousan: Aos 14 anos,", "4 meses e 22 dias, você estava voltando para", "sua residência e foi atacado por um", "assaltante.", " ");
        }
        else if(stateSpeech == 9){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Impacientemente, o assaltante lhe deferiu um", "golpe com uma faca. Você não resistiu ao", "sangramento.", " ", " ");
        }
        else if(stateSpeech == 10){
            batch.draw(backMike, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechMike, xOffset, yOffset, renderWidth, renderHeight);
            drawText("...", " ", " ", " ", " ");
        }
        else if(stateSpeech == 11){
            batch.draw(backMike, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechMike, xOffset, yOffset, renderWidth, renderHeight);
            drawText("E esse cara?", " ", " ", " ", " ");
        }
        else if(stateSpeech == 13){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Agora, Alan Dount: Aos 17 anos, 8 meses e", "6 dias. Faleceu em decorrência de um infarto ", "do miocárdio.", " ", " ");
        }
        else if(stateSpeech == 15){
            batch.draw(backMike, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechMike, xOffset, yOffset, renderWidth, renderHeight);
            drawText("... Bom ... E agora? ...", " ", " ", " ", " ");
        }
        else if(stateSpeech == 16){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Vou lhes propor duas alternativas à serem", "postos no Vale do Esquecimento.", " ", " ", " ");
        }
        else if(stateSpeech == 17){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("A primeira alternativa: Renacer em uma nova ", "vida e esquecer tudo que vivenciaram até aqui.", " ", " ", " ");
        }
        else if(stateSpeech == 18){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Contrapondo, proponho viverem num mundo ", "paralelo ameaçado pelo Exército das Sombras. ", "Seres cujo integridade é desconhecida, tudo", "que se sabe é que almejam tomar controle de ", "todo o multiverso.");
        }
        else if(stateSpeech == 19){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Para incentivá-los vou coneceder alguns ", "benefícios: Vocês terão suas memórias intactas,", "além de poderem interpretar e se comunicar ", "com os seres deste novo mundo.", "");
        }
        else if(stateSpeech == 20){
            //
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Uma vez que consigam derrotar essas", "entidades, extinguindo-as, concederei qualquer", "desejo a vocês.", "", "");
        }
        else if(stateSpeech == 21){
            batch.draw(backMike, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechMike, xOffset, yOffset, renderWidth, renderHeight);
            drawText("... Eu escolho ... O ... Novo mundo...", " ", " ", " ", " ");
        }
        else if(stateSpeech == 23){
            batch.draw(backHO, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechHO, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Ótima escolha, façam uma boa viagem.", " ", " ", " ", " ");
        }
        else if(stateSpeech == 24){
            batch.draw(backAlan, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechAlan, xOffset, yOffset, renderWidth, renderHeight);
            drawText("Como assim \"façam\"?! Eu não ...", " ", " ", " ", " ");
        }
        if(stateSpeech == 25) {
            isAnimationPlaying = true;
            stateSpeech = 26;
        }
    }
    public void falas2(){
        if(stateSpeech == 0 || stateSpeech == 2 || stateSpeech == 4 || stateSpeech == 5 || stateSpeech == 7 || stateSpeech == 8 || stateSpeech == 9 || stateSpeech == 13 || stateSpeech == 14){
            batch.draw(backMike2, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechMike, xOffset, yOffset, renderWidth, renderHeight);
        } else {
            batch.draw(backAlan2, xOffset, yOffset, renderWidth, renderHeight);
            batch.draw(speechAlan, xOffset, yOffset, renderWidth, renderHeight);
        }
        if(stateSpeech == 0){
            drawText("Bom ... O que fazemos agora?", " ", " ", " ", " ");
        } else if(stateSpeech == 1){
            drawText("[resmunga] Você fala demais ...", " ", " ", " ", " ");
        } else if(stateSpeech == 2){
            drawText("A gente parou no meio de uma vila, tem alguma", " ... ideia?", " ", " ", " ");
        } else if(stateSpeech == 3){
            drawText("Escuta, primeiro, \"a gente\" não existe. Fui", "arrastado para cá sem ao menos falar uma ", "palavra", " ", " ");
        } else if(stateSpeech == 4){
            drawText("Eu pensei que ... Você tinha concordado ... Em", "vir para cá.", " ", " ", " ");
        } else if(stateSpeech == 5){
            drawText("Por isso está aqui agora.", " ", " ", " ", " ");
        } else if(stateSpeech == 6){
            drawText("\"Concordado\"? Me ouviu dizendo que queria", "estar aqui?", " ", " ", " ");
        } else if(stateSpeech == 7){
            drawText("Bom ... Você tá aqui agora ... Nós estamos ", "aqui ...", " ", " ", " ");
        } else if(stateSpeech == 8){
            drawText("Vamos ter que viver então ... ", " ", " ", " ", " ");
        } else if(stateSpeech == 9){
            drawText("Seria melhor se estivesse-mos juntos", " ", " ", " ", " ");
        } else if(stateSpeech == 10){
            drawText("... Sem chance ... ", " ", " ", " ", " ");
        } else if(stateSpeech == 11){
            drawText("Nem vem com essa, cada um pra um lado.", " ", " ", " ", " ");
        } else if(stateSpeech == 12){
            drawText("Não venha até mim. Curta sua \"nova vida \".", " ", " ", " ", " ");
        } else if(stateSpeech == 13){
            drawText("... Ok ...", " ", " ", " ", " ");
        } else if(stateSpeech == 14){
            drawText("Te vejo por aí.", " ", " ", " ", " ");
        } else if(stateSpeech == 15){
            if(!loadedNext){
                Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.begin();
                font.draw(batch, "CARREGANDO, AGUARDE ...", 80, 25);
                batch.end();
            } else {
                game.setScreen(new dayOne(game,font, ATK, DEF, MAG, ARR, INV, MAP, cima, baixo, esquerda, direita));
            }
        }
    }

    @Override
    public void render(float delta) {
        if(manager.update()) {
            if(manager.isLoaded("scenes/sceneOne/backgrounds/a.png")) {
                loads();
                batch.begin();
                if(fontIni == true){
                    sizeFont = (int) (game.hS * 0.05f);
                    fontIni = false;
                }
                setupFont(delta, sizeFont);
                if(falas == 1){
                    falas1(delta);
                } else if (falas == 2){
                    falas2();
                }

                batch.end();
            }
        } else{
            Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            font.draw(batch, "CARREGANDO, AGUARDE ...", 80, 25);
            batch.end();
        }
        if(managerNext.update()) {
            if(loadedNext == false) {
                ATK = managerNext.get("UI/btns/ATK.png", Texture.class);
                DEF = managerNext.get("UI/btns/DEF.png", Texture.class);
                MAG = managerNext.get("UI/btns/MAG.png", Texture.class);
                ARR = managerNext.get("UI/btns/ARR.png", Texture.class);
                INV = managerNext.get("UI/btns/INV.png", Texture.class);
                MAP = managerNext.get("UI/btns/MAP.png", Texture.class);
                cima = managerNext.get("UI/btns/cima.png", Texture.class);
                baixo = managerNext.get("UI/btns/baixo.png", Texture.class);
                esquerda = managerNext.get("UI/btns/esquerda.png", Texture.class);
                direita = managerNext.get("UI/btns/direita.png", Texture.class);
                mapaLoad1 = managerNext.get("gameplay/mapa/Avalest/the_farm_area/the_shed1.png", Texture.class);
                loadedNext = true;
            }
        }
        if (isAnimationPlaying) {
            batch.begin();
            stateTime += delta; // Atualiza o tempo da animação
            if (stateTime >= animationTime) {
                // A animação chegou ao fim

                isAnimationPlaying = false;
                falas = 2;
                stateSpeech = 0;

            }
            currentFrameIndex = (int) (stateTime / frameDuration) % frames.length;
            Texture currentFrame = frames[currentFrameIndex];
            batch.draw(currentFrame, xOffset, yOffset, renderWidth, renderHeight);
            batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        // Handle screen resizing
    }

    @Override
    public void pause() {
        // Handle pausing the game
    }

    @Override
    public void resume() {
        // Handle resuming the game
    }

    @Override
    public void hide() {
        // Handle when this screen is no longer visible
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        font1.dispose();
        stage.dispose();
        stage1.dispose();
        fontGenerator.dispose();
        manager.dispose();
        managerNext.dispose();
    }
}

