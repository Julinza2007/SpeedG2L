package com.g2l.speedg2l.entidades;

public class Lava extends Obstaculo {

    public Lava(float ancho, float alto, float posicionX, float posicionY) {
        super(ancho, alto, posicionX, posicionY);
        asignarTipoDeColision();
    }

    @Override
    public void alColisionar(Jugador jugador) {
        jugador.aplicarEfectoLava();
    }

    @Override
    public void asignarTipoDeColision() {
        colisionLateral = false;
        colisionVertical = false;
    }
}
