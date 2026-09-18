package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.g2l.speedg2l.utilidades.ConfigViewport;
import com.g2l.speedg2l.utilidades.Render;

public abstract class Pantalla implements Screen {
    protected ConfigViewport configViewport;
    protected Game juego;

    protected Pantalla(Game juego){
        this.juego = juego;
        configurarInput(null);
        configViewport = new ConfigViewport();
    }

    @Override
    public void resize(int width, int height){
        configViewport.actualizar(width, height);
    }

    protected void cambiarPantalla(Screen pantallaNueva){
        juego.setScreen(pantallaNueva);
    }

    protected void configurarInput(InputProcessor inputProcessor){
        Gdx.input.setInputProcessor(inputProcessor);
    }

    protected static void limpiarPantalla() {
        ScreenUtils.clear(Color.BLACK);
    }
}
