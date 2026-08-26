package com.g2l.speedg2l.utilidades;

public enum Resolucion {

    RES_800X600(800, 600),
    RES_1280X720(1280, 720),
    RES_1366X768(1366, 768),
    RES_1440X900(1440, 900),
    RES_1920X1080(1920, 1080);

    private final int ancho;
    private final int alto;

    Resolucion(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    @Override
    public String toString() {
        return (ancho + "x" + alto);
    }
}
