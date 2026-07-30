package com.g2l.speedg2l.entidades;

public abstract class Pincho extends Obstaculo{

    private double posicionX ;
    private double posicionY ;

    public Pincho(float ancho , float alto, float posicionX, float posicionY){
        super(ancho, alto, posicionX, posicionY);
    }

}
