package com.g2l.speedg2l.pantallas;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.g2l.speedg2l.componentes.Boton;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.componentes.PanelBotones;
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
    private Jugador jugador2;
    private SpriteBatch b;
    private Musica musicaJuego;
    private Hud hud;

    private ArrayList<Entidad> listaDeEntidades;
    private ArrayList<Obstaculo> listaDeObstaculos;

    private Mapa mapa;
    private Camara camara;
    private Entradas entradas;

    private boolean pausado = false;
    private ShapeRenderer pantallaPausa = new ShapeRenderer();
    private Texto textoPausa = new Texto(Recursos.FUENTE_MENU, 60, Color.RED);
    private PanelBotones panelBotones;
    private Skin skinPausa;
    private Meta meta;


    protected PantallaJuego(Game juego) {
        super(juego);
    }

    @Override
    public void show() {
        b = Render.batch;

        crearYaplicarMusica();

        camara = new Camara(configViewport);
        mapa = new Mapa(Recursos.NIVEL_1);
        entradas = new Entradas();
        jugador = new Jugador(70.0f, 70.0f, 0.0f, 33.0f, Recursos.JUGADOR_1);
        jugador2 = new Jugador(70.0f, 70.0f, 25.0f, 33.0f, Recursos.JUGADOR_2);
        hud = new Hud();

        meta = new Meta(50.0f, 1000.0f, 8000.0f, 100.0f);

        listaDeEntidades = new ArrayList<>();
        listaDeObstaculos = new ArrayList<>();

        textoPausa.setTexto("PAUSADO");

        cargarColisionesDesdeMapa();

        stage = new Stage(
            new FitViewport(
                Config.getAnchoJuego(),
                Config.getAltoJuego()
            )
        );


        skinPausa = new Skin(
            Gdx.files.internal("skin/uiskin.json")
        );

        crearPanelBotones(skinPausa);

        panelBotones.centrar();
        panelBotones.getContenedor().setVisible(false);

        stage.addActor(panelBotones.getContenedor());

        Gdx.input.setInputProcessor(stage);
    }

    private void crearYaplicarMusica() {

        if (Render.musicaJuego == null) {
            Render.musicaJuego = new Musica(Recursos.MUSICA_JUEGO);
            Render.musicaJuego.repetir(true);
        }
        if (Config.isSonidoSilenciado()) {
            Render.musicaJuego.setVolumen(0.0f);
        } else {
            Render.musicaJuego.setVolumen(Config.getVolumenMaster());
        }

        musicaJuego = Render.musicaJuego;

        if (!musicaJuego.estaReproduciendo()) {
            musicaJuego.reproducir();
        }
    }

    @Override
    public void render(float delta) {
        delta = Gdx.graphics.getDeltaTime();

        Render.limpiarPantalla();

        camara.seguirJugador(jugador);

        mapa.dibujar(camara.getCamara());

        b.setProjectionMatrix(camara.getCamara().combined);
        b.begin();

        if(entradas.escape()){
            pausado = !pausado;
            panelBotones.getContenedor().setVisible(pausado);
        }

        if(!pausado) {
            if(!musicaJuego.estaReproduciendo()) {
                musicaJuego.reproducir();
            }
            jugador.moverJugador(entradas, Jugadores.JUGADOR_1);
            jugador.actualizarFisicas(listaDeEntidades, delta);
            jugador.animar(delta);
            jugador2.moverJugador(entradas, Jugadores.JUGADOR_2);
            jugador2.actualizarFisicas(listaDeEntidades, delta);
            jugador2.animar(delta);
            hud.actualizar();

            for (Obstaculo o : listaDeObstaculos) {
                if (jugador.colisionaCon(o)) {
                    o.alColisionar(jugador);
                }
                if (jugador2.colisionaCon(o)) {
                    o.alColisionar(jugador2);
                }
            }

            if(jugador.colisionaCon(meta)){
                cambiarPantalla(new PantallaFin(juego, hud.getTiempo()));
                musicaJuego.cerrar();
            }


        } else if(pausado){
            musicaJuego.pausar();
        }

        jugador.dibujar();
        jugador2.dibujar();

        b.end();

        b.setProjectionMatrix(stage.getCamera().combined);
        b.begin();

        hud.dibujar();
        b.end();

        if(pausado){
            dibujarPausa();
            b.begin();

            textoPausa.centrarArriba(200);
            textoPausa.dibujar();

            b.end();
        }
        stage.act(delta);
        stage.draw();
    }

    private void crearPanelBotones(Skin skin) {
        panelBotones = new PanelBotones();

        panelBotones.agregarBoton(
            new Boton(
                "Volver al menu",
                skin,
                new ClickListener() {
                    @Override
                    public void clicked(
                        InputEvent event,
                        float x,
                        float y
                    ) {
                        cambiarPantalla(new PantallaMenu(juego));
                    }
                },
                200,
                60
            ),
            10
        );
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

                            if (propiedades.containsKey("mortal") && propiedades.get("mortal", Boolean.class)) {
                                Lava lava = new Lava(tileAncho, tileAlto, x * tileAncho, y * tileAlto);
                                listaDeObstaculos.add(lava);
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
        musicaJuego.cerrar();
        Render.musicaJuego = null;
        jugador.cerrar();
        stage.dispose();
        skinPausa.dispose();
        pantallaPausa.dispose();
        textoPausa.cerrar();
    }
}
