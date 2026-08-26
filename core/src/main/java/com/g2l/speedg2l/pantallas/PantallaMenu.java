package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.g2l.speedg2l.componentes.Boton;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.componentes.PanelBotones;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.Gdx.input;

public class PantallaMenu extends Pantalla {
    private Stage stage;
    private PanelBotones panelBotones;
    private Imagen imagenFondo;
    private SpriteBatch b;
    private Texto texto;

    @Override
    public void show() {
        b = Render.batch;
        imagenFondo = new Imagen(Recursos.FONDO_MENU);
        imagenFondo.setSize(Config.getAnchoJuego(), Config.getAltoJuego());
        texto = new Texto(Recursos.FUENTE_MENU, 60, Color.RED);
        texto.setTexto("SpeedG2L");
        texto.centrarArriba(100);

        stage = new Stage(configViewport.getViewport());

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        crearPanelBotones(skin);
        panelBotones.centrar();


        agregarAlStage();

        input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();

        configViewport.aplicarViewport(b);
        b.begin();
        imagenFondo.dibujar();
        texto.dibujar();
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


    private void crearPanelBotones(Skin skin) {
        panelBotones = new PanelBotones();

        final int PADDING = 10;
        final int ANCHO_BOTONES = 200;
        final int ALTURA_BOTONES = 60;

        panelBotones.agregarBoton(
            new Boton(
                "Jugar",
                skin,
                new ClickListener() {
                    @Override
                    public void clicked(
                        com.badlogic.gdx.scenes.scene2d.InputEvent event,
                        float x,
                        float y) {
                        cambiarPantalla(new PantallaJuego());
                    }
                },
                ANCHO_BOTONES,
                ALTURA_BOTONES
            ),
            PADDING
        );

        panelBotones.agregarBoton(
            new Boton(
                "Configuracion",
                skin,
                new ClickListener() {
                    @Override
                    public void clicked(
                        com.badlogic.gdx.scenes.scene2d.InputEvent event,
                        float x,
                        float y) {
                        cambiarPantalla(new PantallaConfig());
                    }
                },
                ANCHO_BOTONES,
                ALTURA_BOTONES
            ),
            PADDING
        );

        panelBotones.agregarBoton(
            new Boton(
                "Salir",
                skin,
                new ClickListener() {
                    @Override
                    public void clicked(
                        com.badlogic.gdx.scenes.scene2d.InputEvent event,
                        float x,
                        float y) {
                        app.exit();
                    }
                },
                ANCHO_BOTONES,
                ALTURA_BOTONES
            ),
            PADDING
        );

    }

    private void agregarAlStage() {
      stage.addActor(panelBotones.getContenedor());
    }

}
