package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.Gdx;

public class Config {

    private static final int ANCHO_JUEGO = 1280;
    private static final int ALTO_JUEGO = 720;

    private Config() {}

    public static int getAnchoJuego() {
        return ANCHO_JUEGO;
    }

    public static int getAltoJuego() {
        return ALTO_JUEGO;
    }

    public static int getAnchoMonitor() {
        return Gdx.graphics.getDisplayMode().width;
    }

    public static int getAltoMonitor() {
        return Gdx.graphics.getDisplayMode().height;
    }
}
