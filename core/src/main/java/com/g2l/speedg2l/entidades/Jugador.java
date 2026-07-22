package com.g2l.speedg2l.entidades;

import com.g2l.speedg2l.utilidades.Entradas;

public class Jugador extends Entidad{
    private final int velocidadX = 1;
    private final int velocidadY = 1;
    private int posicionX = 100;
    private int posicionY = 100;

    public Jugador(float ancho , float alto){
        super(100, 100);
    }

    public void moverJugador(Entradas entradas){
        if (entradas.izquierda()){
            this.posicionX -= velocidadX;
        }

        if (entradas.derecha()){
            this.posicionX += velocidadX;
        }

        if (entradas.abajo()){
            this.posicionY -= velocidadY;
        }

        if (entradas.arriba()){
            this.posicionY += velocidadY;
        }
    }

    public float getPosicionY(){
        return this.posicionY;
    }

    public float getPosicionX(){
        return this.posicionX;
    }
}
