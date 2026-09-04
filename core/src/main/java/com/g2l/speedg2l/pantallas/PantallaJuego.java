package com.g2l.speedg2l.pantallas;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    private SpriteBatch b;
    private Musica musicaJuego;
    private Hud hud;

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

        meta = new Meta(50.0f, 1000.0f, 8000.0f, 100.0f);

        listaDeEntidades = new ArrayList<>();
        listaDeObstaculos = new ArrayList<>();

        textoPausa.setTexto("PAUSADO");

        cargarColisionesDesdeMapa();

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
            if(!musicaJuego.estaReproduciendo()) {
                musicaJuego.reproducir();
            }
            jugador.moverJugador(entradas);
            jugador.actualizarFisicas(listaDeEntidades);
            jugador.animar(delta);
            hud.actualizar();
            if(jugador.colisionaCon(meta)){
                cambiarPantalla(new PantallaFin(hud.getCronometro()));
                musicaJuego.cerrar();
            }
        } else if(pausado){
            textoPausa.setPosition(
                (Config.getAnchoJuego() / 2) - (textoPausa.getAncho() / 2),
                (Config.getAltoJuego() / 2) + (textoPausa.getAlto() / 2)
            );

            musicaJuego.pausar();
        }

        jugador.dibujar();

        b.end();

        b.setProjectionMatrix(stage.getCamera().combined);

        b.begin();

        if (pausado) {
            dibujarPausa();
        }

        hud.dibujar();

        b.end();

        stage.act(delta);
        stage.draw();
    }

    private void dibujarPausa() {

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

        Gdx.gl.glDisable(GL20.GL_BLEND);
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
