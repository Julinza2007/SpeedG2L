package com.g2l.speedg2l.entidades;

public abstract class Caja extends Obstaculo{

    private double posicionX ;
    private double posicionY ;

    public Caja(float ancho , float alto, float posicionX, float posicionY){
        super(ancho, alto, posicionX, posicionY);
    }

}
