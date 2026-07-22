package com.g2l.speedg2l.entidades;

public class Entidad {
    private float ancho;
    private float alto;
    protected float posicionX;
    protected float posicionY;

    protected Entidad(float ancho, float alto, float posicionX, float posicionY){
        this.ancho = ancho;
        this.alto = alto;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public float getAncho(){
        return this.ancho;
    }

    public float getAlto(){
        return this.alto;
    }

    public double getPosicionY(){ return this.posicionY; }

    public double getPosicionX(){ return this.posicionX; }
}
