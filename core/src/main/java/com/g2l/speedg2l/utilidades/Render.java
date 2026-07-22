package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Render {

    private Render(){}

    public static SpriteBatch batch = new SpriteBatch();

    public static void limpiarPantalla(){
       ScreenUtils.clear(Color.BLACK);
    }

}
