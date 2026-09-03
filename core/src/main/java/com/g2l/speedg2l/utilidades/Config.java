package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.Gdx;

public class Config {

    private static final int ANCHO_JUEGO = 1280;
    private static final int ALTO_JUEGO = 720;

    private static float volumenMusica = 0.1f;
    private static boolean sonidoSilenciado = false;

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

    public static float getVolumenMusica() {
        return volumenMusica;
    }

    public static void setVolumenMusica(float volumen) {
        if (volumen < 0.0f) {
            volumen = 0.0f;
        }

        if (volumen > 1.0f) {
            volumen = 1.0f;
        }

        volumenMusica = volumen;
    }

    public static boolean isSonidoSilenciado() {
        return sonidoSilenciado;
    }

    public static void setSonidoSilenciado(boolean silenciado) {
        sonidoSilenciado = silenciado;
    }
}
