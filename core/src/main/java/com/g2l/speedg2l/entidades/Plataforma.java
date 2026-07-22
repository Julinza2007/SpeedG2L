package com.g2l.speedg2l.entidades;

public class Plataforma extends Entidad{

    private double posicionX = 250;
    private double posicionY = 200;

    public Plataforma(float ancho , float alto){
        super(ancho, alto);
    }

    public double getPosicionY(){
        return this.posicionY;
    }

    public double getPosicionX(){
        return this.posicionX;
    }

}
