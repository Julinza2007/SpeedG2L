package com.g2l.speedg2l.entidades;

import com.g2l.speedg2l.utilidades.Entradas;

public class Jugador extends Entidad{

    private final double velocidadX = 1;
    private final double velocidadY = 10;
    private double posicionX = 100;
    private double posicionY = 100;

    private int posicionTecho = 300;
    private int posicionSuelo = 100;

    private double gravedad = 0.5;
    private double velocidadYMenosGravedad = velocidadY;

    boolean saltando = false;

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

        /*
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
=======
        if (entradas.abajo()){
>>>>>>> a3a6fbe29620d4cf94e3e310ea5fd019e698c0ba
            this.posicionY -= velocidadY;
        }
        */

        if (entradas.arriba() && !saltando){
            this.saltando = true;
        }
    }

    public double getPosicionY(){
        return this.posicionY;
    }

    public double getPosicionX(){
        return this.posicionX;
    }

    public void actualizarFisicas(){
        if (saltando){
            saltar();
        }
    }

    private void saltar(){
        velocidadYMenosGravedad -= gravedad;
        this.posicionY += velocidadYMenosGravedad;
        if (posicionY >= posicionTecho) {
                velocidadYMenosGravedad = 0;
        }
        if (posicionY <= posicionSuelo){
            this.saltando = false;
            velocidadYMenosGravedad = velocidadY;
            posicionY = posicionSuelo;
            return;
        }
    }
}
