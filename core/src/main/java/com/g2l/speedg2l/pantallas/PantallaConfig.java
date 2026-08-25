package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.g2l.speedg2l.componentes.Boton;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Render;

public class PantallaConfig extends Pantalla {

    private Stage stage;
    private Skin skin;

    private Label textoVolumen;
    private Label textoSilencio;
    private Label textoResolucion;

    private float volumen = 1.0f;
    private boolean sonidoSilenciado = false;

    @Override
    public void show() {

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        crearInterfaz();

        Gdx.input.setInputProcessor(stage);
    }

    private void crearInterfaz() {

        Table tabla = new Table();
        tabla.setFillParent(true);
        tabla.center();

        Label titulo = new Label("CONFIGURACIÓN", skin);
        titulo.setFontScale(1.5f);

        textoResolucion = new Label(
            "Resolución: " + Config.getAncho() + "x" + Config.getAlto(),
            skin
        );

        textoVolumen = new Label(
            "Volumen: " + (int)(volumen * 100) + "%",
            skin
        );

        textoSilencio = new Label(
            "Sonido: " + (sonidoSilenciado ? "Silenciado" : "Activado"),
            skin
        );

        Boton btnResolucion = new Boton(
            "Cambiar resolución",
            skin,
            new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    int ancho;
                    int alto;

                    if (Config.getAncho() == 1280) {
                        ancho = 800;
                        alto = 600;
                    } else {
                        ancho = 1280;
                        alto = 720;
                    }

                    Config.setAncho(ancho);
                    Config.setAlto(alto);

                    Gdx.graphics.setWindowedMode(ancho, alto);

                    textoResolucion.setText(
                        "Resolución: " + ancho + "x" + alto
                    );
                }
            },
            250,
            60
        );

        Boton btnSubirVolumen = new Boton(
            "Subir volumen",
            skin,
            new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    if (volumen < 1.0f) {
                        volumen += 0.1f;
                    }

                    if (volumen > 1.0f) {
                        volumen = 1.0f;
                    }

                    textoVolumen.setText(
                        "Volumen: " + (int)(volumen * 100) + "%"
                    );

                    // Acá después conectamos Music.setVolume() / Sound.play().
                }
            },
            250,
            60
        );

        Boton btnBajarVolumen = new Boton(
            "Bajar volumen",
            skin,
            new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    if (volumen > 0.0f) {
                        volumen -= 0.1f;
                    }

                    if (volumen < 0.0f) {
                        volumen = 0.0f;
                    }

                    textoVolumen.setText(
                        "Volumen: " + (int)(volumen * 100) + "%"
                    );

                    // Acá después conectamos Music.setVolume() / Sound.play().
                }
            },
            250,
            60
        );

        Boton btnSilenciar = new Boton(
            "Silenciar / activar",
            skin,
            new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    sonidoSilenciado = !sonidoSilenciado;

                    textoSilencio.setText(
                        "Sonido: " +
                            (sonidoSilenciado ? "Silenciado" : "Activado")
                    );

                    // Acá después conectamos la lógica de Music/Sound.
                }
            },
            250,
            60
        );

        Boton btnVolver = new Boton(
            "Volver",
            skin,
            new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    cambiarPantalla(new PantallaMenu());
                }
            },
            250,
            60
        );

        tabla.add(titulo).padBottom(30).row();

        tabla.add(textoResolucion).padBottom(10).row();
        tabla.add(btnResolucion).padBottom(25).row();

        tabla.add(textoVolumen).padBottom(10).row();
        tabla.add(btnSubirVolumen).pad(5).row();
        tabla.add(btnBajarVolumen).pad(5).row();

        tabla.add(textoSilencio).padTop(15).padBottom(10).row();
        tabla.add(btnSilenciar).padBottom(25).row();

        tabla.add(btnVolver).row();

        stage.addActor(tabla);
    }

    @Override
    public void render(float delta) {

        Render.limpiarPantalla();

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
        skin.dispose();
    }
}
