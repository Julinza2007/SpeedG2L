package com.g2l.speedg2l.entidades;

public abstract class Obstaculo extends Entidad{

    private double posicionX ;
    private double posicionY ;

    public Obstaculo(float ancho , float alto, float posicionX, float posicionY){
        super(ancho, alto, posicionX, posicionY);
    }


    public abstract void colisionar(Jugador jugador);

}
