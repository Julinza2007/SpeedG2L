package com.g2l.speedg2l.entidades;

import com.badlogic.gdx.math.Rectangle;

public class Entidad {
    private float ancho;
    private float alto;
    protected float posicionX;
    protected float posicionY;

    private Rectangle hitbox;

    protected Entidad(float ancho, float alto, float posicionX, float posicionY){
        this.ancho = ancho;
        this.alto = alto;
        this.posicionX = posicionX;
        this.posicionY = posicionY;

        this.hitbox = new Rectangle(posicionX, posicionY, ancho, alto);
    }

    public float getAncho(){
        return this.ancho;
    }

    public float getAlto(){
        return this.alto;
    }

    public double getPosicionY(){ return this.posicionY; }

    public double getPosicionX(){ return this.posicionX; }

    protected Rectangle getHitbox(){ return this.hitbox; }

    public void actualizarHitbox(){ hitbox.setPosition(posicionX, posicionY); }
}
