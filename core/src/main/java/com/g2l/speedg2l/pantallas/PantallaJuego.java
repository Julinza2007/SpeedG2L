package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.g2l.speedg2l.animaciones.AnimacionEntidad;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.componentes.interfaz.Hud;
import com.g2l.speedg2l.entidades.*;
import com.g2l.speedg2l.mundo.Camara;
import com.g2l.speedg2l.mundo.Mapa;
import com.g2l.speedg2l.sonidos.Musica;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Entradas;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

import java.util.ArrayList;

public class PantallaJuego extends Pantalla {

    private Stage stage;

    private Jugador jugador;
    private AnimacionEntidad caminataJugador;    private SpriteBatch b;
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

    private boolean pausado = false;
    private ShapeRenderer pantallaPausa = new ShapeRenderer();
    private Texto textoPausa = new Texto(Recursos.FUENTE_MENU, 60, Color.RED);

    private Meta meta;

    @Override
    public void show() {
        b = Render.batch;

        crearYaplicarMusica();
        camara = new Camara(configViewport);
        mapa = new Mapa(Recursos.NIVEL_1);
        entradas = new Entradas();
        jugador = new Jugador(70.0f, 70.0f, 0.0f, 33.0f);
        hud = new Hud();

        listaDeEntidades = new ArrayList<>();
        listaDeObstaculos = new ArrayList<>();

        plataforma = new Plataforma(221.0f, 31.0f, 250.0f, 200.0f);
        listaDeEntidades.add(plataforma);
        imgPlataforma = new Imagen(Recursos.PLATAFORMA_VERDE);

        pincho = new Pincho(57.0f, 31.0f, 400.0f, 100.0f);
        listaDeObstaculos.add(pincho);
        imgPincho = new Imagen(Recursos.OBSTACULO_PINCHO);

        meta = new Meta(50.0f, 1000.0f, 8000.0f, 100.0f);

        textoPausa.setTexto("PAUSADO");


        stage = new Stage(configViewport.getViewport());
    }

    private void crearYaplicarMusica() {
        musicaJuego = new Musica(Recursos.MUSICA_JUEGO);
        musicaJuego.volumen(0.1f);
        musicaJuego.repetir(true);
        musicaJuego.reproducir();
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();

        camara.seguirJugador(jugador);

        mapa.dibujar(camara.getCamara());

        b.setProjectionMatrix(camara.getCamara().combined);
        b.begin();

        if(entradas.escape()){
            pausado = !pausado;
        }

        if(!pausado) {
            jugador.moverJugador(entradas);
            jugador.actualizarFisicas(listaDeEntidades);
            jugador.animar(delta);
            musicaJuego.reproducir();
            hud.actualizar();
            if(jugador.colisionaCon(meta)){
                cambiarPantalla(new PantallaFin(hud.getCronometro()));
                musicaJuego.cerrar();
            }
        }


        else if(pausado){
            textoPausa.setPosition(
                ((Config.getAnchoJuego() / 2) - (textoPausa.getAncho() / 2)),
                ((Config.getAltoJuego()  / 2) + (textoPausa.getAlto() / 2))
            );

            musicaJuego.pausar();
        }

        jugador.dibujar();

        imgPlataforma.setX(plataforma.getPosicionX());
        imgPlataforma.setY(plataforma.getPosicionY());
        imgPlataforma.dibujar();

        imgPincho.setX(pincho.getPosicionX());
        imgPincho.setY(pincho.getPosicionY());
        imgPincho.dibujar();

        b.end();

        b.setProjectionMatrix(stage.getCamera().combined);

        if(pausado){
            dibujarPuasa();
        }

        b.begin();

        if (pausado) {
            textoPausa.dibujar();
        }

        hud.dibujar();

        b.end();

        stage.act(delta);
        stage.draw();
    }

    private void dibujarPuasa() {

        Gdx.gl.glEnable(GL20.GL_BLEND);

        pantallaPausa.setProjectionMatrix(stage.getCamera().combined);

        pantallaPausa.begin(ShapeRenderer.ShapeType.Filled);

        pantallaPausa.setColor(0, 0, 0, 0.7f);

        pantallaPausa.rect(
            0,
            0,
            Config.getAnchoJuego(),
            Config.getAltoJuego()
        );

        pantallaPausa.end();
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
