package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Screen;
import com.g2l.speedg2l.utilidades.ConfigViewport;
import com.g2l.speedg2l.utilidades.Render;

public abstract class Pantalla implements Screen {
    protected ConfigViewport configViewport;

    protected Pantalla(){
        configViewport = new ConfigViewport();
    }


    @Override
    public void resize(int width, int height){
        configViewport.actualizar(width, height);
    }

    protected void cambiarPantalla(Screen pantallaNueva){
        Render.app.setScreen(pantallaNueva);
    }
}
