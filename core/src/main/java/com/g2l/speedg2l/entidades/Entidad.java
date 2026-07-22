package com.g2l.speedg2l.entidades;

public class Entidad {
    protected float ancho;
    protected float alto;

    protected Entidad(float ancho, float alto){
        this.ancho = ancho;
        this.alto = alto;
    }

    public float getAncho(){
        return this.ancho;
    }

    public float getAlto(){
        return this.alto;
    }
}
