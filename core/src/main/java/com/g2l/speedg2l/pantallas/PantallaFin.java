package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.g2l.speedg2l.componentes.Boton;
import com.g2l.speedg2l.componentes.PanelBotones;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.sonidos.EfectoSonido;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

public class PantallaFin extends Pantalla {

    private Stage stage;
    private Boton btnSalir;
    private PanelBotones panelBotones;
    private SpriteBatch b;
    private EfectoSonido efectoMeta;
    private Texto cronometro;
    private float tiempo;

    public PantallaFin(Game juego, float tiempo) {
        super(juego);
        this.tiempo = tiempo;
    }

    @Override
    public void show() {

        b = Render.batch;
        efectoMeta = new EfectoSonido(Recursos.EFECTO_META);
        if (Config.isSonidoSilenciado()) {
            efectoMeta.setVolumen(0.0f);
        } else {
            efectoMeta.setVolumen(Config.getVolumenMaster());
            efectoMeta.reproducir();
        }

        stage = new Stage(configViewport.getViewport());

        cronometro = new Texto(Recursos.FUENTE_MENU, 60, Color.MAROON);

        int minutos = (int) tiempo / 60;
        int segundos = (int) tiempo % 60;
        int centesimas = (int) (tiempo * 100) % 100;

        cronometro.setTexto("Tiempo final:\n" + "min: " + minutos + " seg: " + segundos + "," + centesimas);

        cronometro.centrarArriba(150);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        crearPanelBotones(skin);
        agregarAlStage();
        configurarInput(stage);
    }

    private void crearPanelBotones(Skin skin) {
        panelBotones = new PanelBotones();

        final int PADDING = 10;
        final int ANCHO_BOTONES = 200;
        final int ALTURA_BOTONES = 60;

        panelBotones.agregarBoton(
            new Boton(
                "Menu",
                skin,
                new ClickListener() {
                    @Override
                    public void clicked(
                        com.badlogic.gdx.scenes.scene2d.InputEvent event,
                        float x,
                        float y) {
                        cambiarPantalla(new PantallaMenu(juego));
                    }
                },
                ANCHO_BOTONES,
                ALTURA_BOTONES
            ),
            PADDING
        );
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();

        configViewport.aplicarViewport(b);
        b.begin();
        cronometro.dibujar();

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
        efectoMeta.cerrar();
    }

    private void agregarAlStage() {
        stage.addActor(panelBotones.getContenedor());
    }

}
