package com.g2l.speedg2l.entidades;

public class Entidad {
    protected int ancho;
    protected int alto;

    protected Entidad(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getAncho(){
        return this.ancho;
    }

    public int getAlto(){
        return this.alto;
    }
}
