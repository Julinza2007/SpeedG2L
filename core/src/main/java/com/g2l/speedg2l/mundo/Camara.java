package com.g2l.speedg2l.mundo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.g2l.speedg2l.entidades.Jugador;
import com.g2l.speedg2l.utilidades.ConfigViewport;

public class Camara {

    private OrthographicCamera camara;

    public Camara(ConfigViewport configViewport) {
        camara = configViewport.getCamara();
    }

    public void seguirJugador(Jugador jugador) {
        camara.position.set(
            jugador.getPosicionX() + jugador.getAncho() / 2,
            jugador.getPosicionY() + jugador.getAlto() / 2,
            0
        );

        camara.update();
    }

    public OrthographicCamera getCamara() {
        return camara;
    }
}
