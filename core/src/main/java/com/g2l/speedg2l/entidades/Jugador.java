package com.g2l.speedg2l.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Jugador extends Entidad{
    private double velocidadX = 1;
    private double velocidadY = 10;
    private double posicionX = 100;
    private double posicionY = 100;

    private int posicionTecho = 300;
    private int posicionSuelo = 100;

    private double gravedad = 0.5;
    private double velocidadYMenosGravedad = velocidadY;

    boolean saltando = false;

    public Jugador(){
        super(100, 100);
    }

    public void moverJugador(){
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            this.posicionX -= velocidadX;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            this.posicionX += velocidadX;
        }

        /*
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            this.posicionY -= velocidadY;
        }
        */

        if (Gdx.input.isKeyPressed(Input.Keys.W) && !saltando){
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
