package com.g2l.speedg2l.entidades;

import java.util.ArrayList;

public class Pincho extends Obstaculo{

    public Pincho(float ancho , float alto, float posicionX, float posicionY){
        super(ancho, alto, posicionX, posicionY);
    }

    @Override
    public void alColisionar(Jugador jugador, ArrayList<Entidad> listaDeEntidades){
        jugador.rebotar(listaDeEntidades);
    }

    @Override
    public void asignarTipoDeColision(){
        colisionLateral = false;
        colisionVertical = true;
    }

}
