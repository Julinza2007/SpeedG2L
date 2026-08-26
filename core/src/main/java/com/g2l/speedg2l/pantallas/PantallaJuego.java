package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.componentes.interfaz.Hud;
import com.g2l.speedg2l.entidades.*;
import com.g2l.speedg2l.mundo.Camara;
import com.g2l.speedg2l.mundo.Mapa;
import com.g2l.speedg2l.sonidos.Musica;
import com.g2l.speedg2l.utilidades.Entradas;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

import java.util.ArrayList;

public class PantallaJuego extends Pantalla {

    private Stage stage;

    private Jugador jugador;
    private Imagen imagenJugador;
    private SpriteBatch b;
    private Musica musicaJuego;
    private Hud hud;

    private Plataforma plataforma;
    private Imagen imgPlataforma;

    private Pincho pincho;
    private Imagen imgPincho;

    private ArrayList<Entidad> listaDeEntidades;
    private ArrayList<Obstaculo> listaDeObstaculos;

    private Mapa mapa;
    private Camara camara;
    private Entradas entradas;

    @Override
    public void show() {
        b = Render.batch;

        crearYaplicarMusica();
        camara = new Camara(configViewport);
        mapa = new Mapa(Recursos.NIVEL_1);
        entradas = new Entradas();
        jugador = new Jugador(50.0f, 50.0f, 100.0f, 100.0f);
        imagenJugador = new Imagen("libgdx.png");
        imagenJugador.setSize(jugador.getAncho(), jugador.getAlto());

        hud = new Hud();

        listaDeEntidades = new ArrayList<>();
        listaDeObstaculos = new ArrayList<>();

        plataforma = new Plataforma(221.0f, 31.0f, 250.0f, 200.0f);
        listaDeEntidades.add(plataforma);
        imgPlataforma = new Imagen(Recursos.PLATAFORMA_VERDE);

        pincho = new Pincho(57.0f, 31.0f, 400.0f, 100.0f);
        listaDeObstaculos.add(pincho);
        imgPincho = new Imagen(Recursos.OBSTACULO_PINCHO);

        stage = new Stage(configViewport.getViewport());
    }

    private void crearYaplicarMusica() {
        musicaJuego = new Musica(Recursos.MUSICA_JUEGO);
        musicaJuego.volumen(0.5f);
        musicaJuego.repetir(true);
        musicaJuego.reproducir();
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();

        jugador.moverJugador(entradas);
        jugador.actualizarFisicas(listaDeEntidades);

        camara.seguirJugador(jugador);

        mapa.dibujar(camara.getCamara());

        hud.actualizar();

        b.setProjectionMatrix(camara.getCamara().combined);
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

        hud.dibujar();


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
