package com.g2l.speedg2l.entidades;

import java.util.ArrayList;

public abstract class Obstaculo extends Entidad{

    protected boolean colisionLateral;
    protected boolean colisionVertical;

    public Obstaculo(float ancho , float alto, float posicionX, float posicionY){
        super(ancho, alto, posicionX, posicionY);
    }

    public boolean getColisionLateral(){
        return this.colisionLateral;
    }

    public boolean getColisionVertical(){
        return this.colisionVertical;
    }

    public abstract void alColisionar(Jugador jugador, ArrayList<Entidad> listaDeEntidades);

    public abstract void asignarTipoDeColision();

}
