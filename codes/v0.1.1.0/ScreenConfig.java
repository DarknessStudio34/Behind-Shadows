package com.ds.behsha;

import com.badlogic.gdx.Gdx;

public class ScreenConfig {
    public float proporcao (){
        int wS = Gdx.graphics.getWidth();
        int hs = Gdx.graphics.getHeight();
        float propor = 0;
        if((hs * 20) / 9 >= wS - 50 && (hs * 20) / 9 <= wS + 50){
            propor = 20.9f;
        } else if ((hs * 16) / 9 == wS ){
            propor = 16.9f;
        } else if ((hs * 21) / 9 >= wS - 50 && (hs * 20) / 9 <= wS + 50){
            propor = 21.9f;
        } else if ((hs * 16) / 10 == wS){
            propor = 16.1f;
        } else if ((hs * 19.5f) / 9 == wS || (hs * 19.5f) / 9 == wS + 75){
            propor = 19.59f; // 19,5:9 - proporção mais comum para celulares intermediários
        }
        return propor;
    }
}
