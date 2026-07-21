package com.g2l.speedg2l.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Jugador extends Entidad{
    private int velocidadX = 1;
    private int velocidadY = 1;
    private int posicionX = 100;
    private int posicionY = 100;

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

        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            this.posicionY -= velocidadY;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            this.posicionY += velocidadY;
        }
    }

    public int getPosicionY(){
        return this.posicionY;
    }

    public int getPosicionX(){
        return this.posicionX;
    }
}
