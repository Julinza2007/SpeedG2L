package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.entidades.*;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Entradas;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

import java.util.ArrayList;

public class PantallaJuego extends Pantalla {

    private Stage stage;

    private Jugador jugador;
    private Imagen imagenJugador;
    private SpriteBatch b;

    private Plataforma plataforma;
    private Imagen imgPlataforma;

    private Pincho pincho;
    private Imagen imgPincho;

    private ArrayList<Entidad> listaDeEntidades;
    private ArrayList<Obstaculo> listaDeObstaculos;


    @Override
    public void show() {
        b = Render.batch;
        jugador = new Jugador(50.0f, 50.0f, 100.0f, 100.0f);
        imagenJugador = new Imagen("libgdx.png");
        imagenJugador.setSize(jugador.getAncho(), jugador.getAlto());

        listaDeEntidades = new ArrayList<>();
        listaDeObstaculos = new ArrayList<>();

        plataforma = new Plataforma((float) 221, (float) 31, 250.0f, 200.0f);
        listaDeEntidades.add(plataforma);
        imgPlataforma = new Imagen(Recursos.PLATAFORMA_VERDE);

        pincho = new Pincho((float) 57, (float) 31, 400.0f, 100.0f);
        listaDeObstaculos.add(pincho);
        imgPincho = new Imagen(Recursos.OBSTACULO_PINCHO);

        stage = new Stage();


    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();

        jugador.moverJugador(new Entradas());
        jugador.actualizarFisicas(listaDeEntidades);

        b.begin();
        imagenJugador.setX((float) jugador.getPosicionX());
        imagenJugador.setY((float) jugador.getPosicionY());
        imagenJugador.dibujar();

        imgPlataforma.setX((float) plataforma.getPosicionX());
        imgPlataforma.setY((float) plataforma.getPosicionY());
        imgPlataforma.dibujar();

        imgPincho.setX((float) pincho.getPosicionX());
        imgPincho.setY((float) pincho.getPosicionY());
        imgPincho.dibujar();

        b.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
