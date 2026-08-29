package com.g2l.speedg2l;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.entidades.Entidad;
import com.g2l.speedg2l.entidades.Jugador;
import com.g2l.speedg2l.entidades.Plataforma;
import com.g2l.speedg2l.pantallas.PantallaJuego;
import com.g2l.speedg2l.pantallas.PantallaMenu;
import com.g2l.speedg2l.utilidades.Render;

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

    }

    @Override
    public void render() {
        Render.limpiarPantalla();
        super.render();
    }

    @Override
    public void dispose() {
        b.dispose();
    }
}
