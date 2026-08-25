package com.g2l.speedg2l.componentes.interfaz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.utilidades.Recursos;

public class Hud {

    private Texto cronometro = new Texto(Recursos.FUENTE_MENU, 60, Color.WHITE);
    private float tiempo;

    private Texto posicion;



    public Hud() {
        tiempo = 0;
    }

    public void actualizar() {
        tiempo += Gdx.graphics.getDeltaTime();
    }

    public void dibujar() {

        int tiempoMinutos = (int) tiempo / 60;
        int tiempoSegundos = (int) tiempo % 60;
        int tiempocentesimasDeSegundos = (int) (tiempo * 100) % 100;

        String textoCronometro = String.format(
            "%02d:%02d:%02d",
            tiempoMinutos,
            tiempoSegundos,
            tiempocentesimasDeSegundos
        );

        cronometro.setTexto(textoCronometro);
        cronometro.dibujar();

    }

    public void dispose() {
    }

}
