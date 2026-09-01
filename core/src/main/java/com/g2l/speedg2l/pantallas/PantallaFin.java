package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.g2l.speedg2l.componentes.Boton;
import com.g2l.speedg2l.componentes.PanelBotones;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.componentes.interfaz.Hud;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

public class PantallaFin extends Pantalla {

    private Stage stage;
    private Boton btnSalir;
    private PanelBotones panelBotones;
    private SpriteBatch b;
    private Texto texto, cronometro;

    public PantallaFin(Texto cronometro) {
        this.cronometro = cronometro;
    }

    @java.lang.Override
    public void show() {

        b = Render.batch;

        stage = new Stage(configViewport.getViewport());

        texto = new Texto(Recursos.FUENTE_MENU, 60, Color.RED);
        texto.setTexto("Tiempo final:");
        texto.centrarArriba(100);

        cronometro.centrarArriba(150);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        crearPanelBotones(skin);
        agregarAlStage();
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
                        cambiarPantalla(new PantallaMenu());
                    }
                },
                ANCHO_BOTONES,
                ALTURA_BOTONES
            ),
            PADDING
        );
    }

    @java.lang.Override
    public void render(float delta) {
        Render.limpiarPantalla();

        configViewport.aplicarViewport(b);
        b.begin();
        texto.dibujar();
        cronometro.dibujar();

        b.end();
        stage.act(delta);
        stage.draw();
    }

    @java.lang.Override
    public void resize(int i, int i1) {

    }

    @java.lang.Override
    public void pause() {

    }

    @java.lang.Override
    public void resume() {

    }

    @java.lang.Override
    public void hide() {

    }

    @java.lang.Override
    public void dispose() {

    }

    private void agregarAlStage() {
        stage.addActor(panelBotones.getContenedor());
    }

}
