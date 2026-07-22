package com.g2l.speedg2l;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.entidades.Entidad;
import com.g2l.speedg2l.entidades.Jugador;
import com.g2l.speedg2l.entidades.Plataforma;
import com.g2l.speedg2l.pantallas.PantallaMenu;
import com.g2l.speedg2l.utilidades.Entradas;
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
        this.setScreen(new PantallaMenu());
        b = Render.batch;

        this.listaDeEntidades = new ArrayList<>();

        jugador = new Jugador(50.0f, 50.0f, 100.0f, 100.0f);
        logo = new Imagen("libgdx.png");
        logo.setSize(jugador.getAncho(), jugador.getAlto());

        plataforma = new Plataforma((float) 221, (float) 31, 250.0f, 200.0f);
        listaDeEntidades.add(plataforma);
        imgPlataforma = new Imagen("plataforma.png");
    }

    @Override
    public void render() {
        Render.limpiarPantalla();
        super.render();

        jugador.moverJugador(new Entradas());
        jugador.actualizarFisicas(listaDeEntidades);

        b.begin();
        logo.setX((float) jugador.getPosicionX());
        logo.setY((float) jugador.getPosicionY());
        logo.dibujar();

        imgPlataforma.setX((float) plataforma.getPosicionX());
        imgPlataforma.setY((float) plataforma.getPosicionY());
        imgPlataforma.dibujar();
        b.end();
    }

    @Override
    public void dispose() {
        b.dispose();
    }
}
