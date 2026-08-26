package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.Gdx;

public class Config {

    private static final int ANCHO_VP = 1280;
    private static final int ALTO_VP = 720;

    private Config() {}

    public static int getAnchoVp() {
        return ANCHO_VP;
    }

    public static int getAltoVp() {
        return ALTO_VP;
    }

    public static int getAnchoMonitor() {
        return Gdx.graphics.getDisplayMode().width;
    }

    public static int getAltoMonitor() {
        return Gdx.graphics.getDisplayMode().height;
    }
}
