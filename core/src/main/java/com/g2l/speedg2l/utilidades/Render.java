package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.g2l.speedg2l.SpeedG2L;

public class Render {

    private Render(){}

    public static SpriteBatch batch = new SpriteBatch();
    public static SpeedG2L app;
    public static void limpiarPantalla(){
       ScreenUtils.clear(Color.BLACK);
    }

}
