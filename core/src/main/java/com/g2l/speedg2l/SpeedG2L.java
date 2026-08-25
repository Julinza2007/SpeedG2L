package com.g2l.speedg2l;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.entidades.Entidad;
import com.g2l.speedg2l.entidades.Jugador;
import com.g2l.speedg2l.entidades.Plataforma;
import com.g2l.speedg2l.pantallas.PantallaCarga;
import com.g2l.speedg2l.pantallas.PantallaMenu;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Render;

import java.awt.*;
import java.util.ArrayList;

public class SpeedG2L extends Game {

    private SpriteBatch b;
    private Imagen logo;
    private Imagen imgPlataforma;
    private Plataforma plataforma;
    private Jugador jugador;

    private ArrayList<Entidad> listaDeEntidades;

    @Override
    public void create() {
        Render.app = this;

        this.setScreen(new PantallaMenu());
        b = Render.batch;

//        jugador = new Jugador(100.0f, 100.0f);
//        logo = new Imagen("libgdx.png");
//        logo.setSize(jugador.getAncho(), jugador.getAlto());

    }

    @Override
    public void render() {
        Render.limpiarPantalla();
        super.render();

//        jugador.moverJugador(new Entradas());
//        b.begin();
//        logo.setX(jugador.getPosicionX());
//        logo.setY(jugador.getPosicionY());
//        logo.dibujar();
//        b.end();
    }

    @Override
    public void dispose() {
        b.dispose();
    }
}
