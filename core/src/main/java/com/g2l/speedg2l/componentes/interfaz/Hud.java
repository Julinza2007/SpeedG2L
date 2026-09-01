package com.g2l.speedg2l.componentes.interfaz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.Cerrable;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Recursos;

public class Hud implements Cerrable {

    private Texto cronometro;
    private float tiempo;


    public Hud() {
        tiempo = 0.0f;
    }

    public void actualizar() {
        tiempo += Gdx.graphics.getDeltaTime();
    }

    public void dibujar() {
        crearCronometro();
        cronometro.setPosition(((Config.getAnchoJuego() / 2) - (cronometro.getAncho() / 2)), ((Config.getAltoJuego()) - (cronometro.getAlto() / 2)));
        cronometro.dibujar();
    }

    private void crearCronometro(){
        cronometro = new Texto(Recursos.FUENTE_MENU, 60, Color.WHITE);

        int minutos = (int) tiempo / 60;
        int segundos = (int) tiempo % 60;
        int centesimas = (int) (tiempo * 100) % 100;

        cronometro.setTexto( "m: " + minutos + " seg: " + segundos + "," + centesimas);
    }

    @Override
    public void cerrar() {
        cronometro.cerrar();
    }

    public Texto getCronometro() {
        return cronometro;
    }
}
