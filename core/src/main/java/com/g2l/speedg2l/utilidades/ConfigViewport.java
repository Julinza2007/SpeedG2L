package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class ConfigViewport {
    private FitViewport viewport;

    public ConfigViewport(){
        viewport = new FitViewport(Config.getAncho(), Config.getAlto());
    }

    public void aplicarViewport(SpriteBatch b) {
        viewport.apply();
        b.setProjectionMatrix(viewport.getCamera().combined);
    }

    public void actualizar(int ancho, int alto){
        viewport.update(ancho, alto, true);
    }

    public FitViewport getViewport() {
        return viewport;
    }



}
