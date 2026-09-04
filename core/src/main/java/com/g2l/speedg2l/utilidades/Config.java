package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.Gdx;

public class Config {

    private static final int ANCHO_JUEGO = 1280;
    private static final int ALTO_JUEGO = 720;

    private static float volumenMaster = 0.1f;
    private static boolean sonidoSilenciado = false;

    private Config() {}

    public static int getAnchoJuego() {
        return ANCHO_JUEGO;
    }

    public static int getAltoJuego() {
        return ALTO_JUEGO;
    }

    public static int getAnchoPantallaActual() {
        return Gdx.graphics.getWidth();
    }

    public static int getAltoPantallaActual() {
        return Gdx.graphics.getHeight();
    }

    public static int getAnchoMonitor() {
        return Gdx.graphics.getDisplayMode().width;
    }

    public static int getAltoMonitor() {
        return Gdx.graphics.getDisplayMode().height;
    }

    public static float getVolumenMaster() {
        return volumenMaster;
    }

    public static void setVolumenMaster(float volumen) {
        if (volumen < 0.0f) {
            volumen = 0.0f;
        }

        if (volumen > 1.0f) {
            volumen = 1.0f;
        }

        volumenMaster = volumen;
    }

    public static boolean isSonidoSilenciado() {
        return sonidoSilenciado;
    }

    public static void setSonidoSilenciado(boolean silenciado) {
        sonidoSilenciado = silenciado;
    }
}
