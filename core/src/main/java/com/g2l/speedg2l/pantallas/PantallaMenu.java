package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.g2l.speedg2l.componentes.Boton;
import com.g2l.speedg2l.utilidades.Config;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.Gdx.input;

public class PantallaMenu implements Screen {
    private Stage stage;
    private Boton btnJugar, btnConfig, btnSalir;
    @Override
    public void show() {

        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        crearBotones(skin);

        escucharClicks();

        agregarAlStage();

        input.setInputProcessor(stage);
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
            }
        });

        btnConfig.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Configurando...");
                Config.ancho = 800;
                Config.alto = 600;
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

        table.add(btnJugar).size(200, 60).pad(10).row();
        table.add(btnConfig).size(200, 60).pad(10).row();
        table.add(btnSalir).size(200, 60).pad(10);

        stage.addActor(table);
    }



    @Override
    public void render(float delta) {
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
