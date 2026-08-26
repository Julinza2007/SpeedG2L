package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.componentes.interfaz.Hud;
import com.g2l.speedg2l.entidades.*;
import com.g2l.speedg2l.sonidos.Musica;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Entradas;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

public class PantallaJuego implements Screen {

    private Stage stage;

    private Jugador jugador;
    private Imagen imagenJugador;
    private SpriteBatch b;

    private Hud hud;

    private Plataforma plataforma;
    private Imagen imgPlataforma;

    private Pincho pincho;
    private Imagen imgPincho;

    private ArrayList<Entidad> listaDeEntidades;
    private ArrayList<Obstaculo> listaDeObstaculos;

    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderMapa;
    private OrthographicCamera camara;


    @Override
    public void show() {
        b = Render.batch;

        Musica musicaMenu = new Musica("assets/sonidos/musicaInGame/musicaNivel1.wav");

        musicaMenu.volumen(0.5f);
        musicaMenu.repetir(true);
        musicaMenu.reproducir();

        TmxMapLoader loader = new TmxMapLoader();

        mapa = loader.load("nivel/mapaPrueba.tmx");

        renderMapa = new OrthogonalTiledMapRenderer(mapa);

        jugador = new Jugador(50.0f, 50.0f, 100.0f, 100.0f);
        imagenJugador = new Imagen("libgdx.png");
        imagenJugador.setSize(jugador.getAncho(), jugador.getAlto());

        hud = new Hud();

        camara = new OrthographicCamera();
        camara.setToOrtho(false, Config.ancho, Config.alto);
        camara.update();

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

        camara.position.x = (float) jugador.getPosicionX();
        camara.position.y = (float) jugador.getPosicionY();

        camara.update();
        renderMapa.setView(camara);
        renderMapa.render();

        hud.actualizar();

        b.setProjectionMatrix(camara.combined);
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
    public void resize(int width, int height) {
        Config.ancho = width;
        Config.alto = height;

        stage.getViewport().update(width, height, true);
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
