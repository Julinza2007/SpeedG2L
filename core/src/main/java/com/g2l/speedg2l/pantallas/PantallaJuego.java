package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.g2l.speedg2l.animaciones.AnimacionEntidad;
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
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

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

    @Override
    public void show() {
        b = Render.batch;

        crearYaplicarMusica();
        camara = new Camara(configViewport);
        mapa = new Mapa(Recursos.NIVEL_1);
        entradas = new Entradas();
        jugador = new Jugador(70.0f, 70.0f, 000.0f, 50.0f);
        hud = new Hud();

        listaDeEntidades = new ArrayList<>();
        listaDeObstaculos = new ArrayList<>();

        cargarColisionesDesdeMapa();


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

        jugador.animar(delta);
        jugador.dibujar();


        hud.dibujar();

        b.end();

        stage.act(delta);
        stage.draw();
    }

    private void cargarColisionesDesdeMapa() {
        if (mapa != null && mapa.getMapa() != null) {
            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getMapa().getLayers().get("Capa de patrones 1");
            if (capa != null) {
                int tileAncho = (int) capa.getTileWidth();
                int tileAlto = (int) capa.getTileHeight();

                for (int x = 0; x < capa.getWidth(); x++) {
                    for (int y = 0; y < capa.getHeight(); y++) {
                        TiledMapTileLayer.Cell cell = capa.getCell(x, y);

                        if (cell != null && cell.getTile() != null) {
                            MapProperties propiedades = cell.getTile().getProperties();

                            if (propiedades.containsKey("solido") && propiedades.get("solido", Boolean.class)) {
                                Plataforma bloque = new Plataforma(tileAncho, tileAlto, x * tileAncho, y * tileAlto);
                                listaDeEntidades.add(bloque);
                            }
                        }
                    }
                }
            }
        }
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
