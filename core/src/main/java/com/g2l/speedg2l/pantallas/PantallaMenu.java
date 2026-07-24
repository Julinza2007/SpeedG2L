package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.g2l.speedg2l.componentes.Boton;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.Gdx.input;

public class PantallaMenu implements Screen {
    private Stage stage;
    private Boton btnJugar, btnConfig, btnSalir;
    private int anchoBtn=200, altoBtn=60, paddingBtn=10;
    private Imagen imagenFondo;
    private SpriteBatch b;
    Texto texto;
    @Override
    public void show() {
        b = Render.batch;
        imagenFondo = new Imagen(Recursos.FONDO_MENU);
        imagenFondo.setSize(Config.ancho, Config.alto);
        texto = new Texto(Recursos.FUENTE_MENU, 60, Color.RED);
        texto.setTexto("SpeedG2L");
        texto.centrarArriba(100);

        stage = new Stage();

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        crearBotones(skin);
        escucharClicks();
        agregarAlStage();

        input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();
        b.begin();
        imagenFondo.dibujar();
        texto.dibujar();
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

    private void crearBotones(Skin skin) {
        btnJugar = new Boton("Jugar", skin);
        btnConfig = new Boton("Configuracion", skin);
        btnSalir = new Boton("Salir", skin);
    }

    private void escucharClicks() {

        btnJugar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Jugando...");
                Render.app.setScreen(new PantallaJuego());
            }
        });

        btnConfig.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Configurando...");
                Config.ancho = ((Config.ancho==1280)? (800):(1280));
                Config.alto = ((Config.alto==720)? (600): (720));
                System.out.println("Ancho: " + Config.getAncho());
                System.out.println("Alto: " + Config.getAlto());

                Gdx.graphics.setWindowedMode(Config.ancho, Config.alto);
            }
        });

        btnSalir.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.exit();
            }
        });
    }

    private void agregarAlStage() {
        Table table = new Table();
        table.setFillParent(true);
        table.bottom();

        table.add(btnJugar).size(anchoBtn, altoBtn).pad(paddingBtn).row();
        table.add(btnConfig).size(anchoBtn, altoBtn).pad(paddingBtn).row();
        table.add(btnSalir).size(anchoBtn, altoBtn).pad(paddingBtn);

        stage.addActor(table);
    }


}
