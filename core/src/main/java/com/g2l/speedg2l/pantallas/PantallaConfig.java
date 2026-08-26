package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.g2l.speedg2l.componentes.Boton;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Render;
import com.g2l.speedg2l.utilidades.Resolucion;

public class PantallaConfig extends Pantalla {

    private Stage stage;
    private Skin skin;

    private Label textoVolumen;
    private Label textoSilencio;
    private float volumen = 1.0f;
    private boolean sonidoSilenciado = false;
    private Resolucion[] resoluciones;

    @Override
    public void show() {

        stage = new Stage(configViewport.getViewport());
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        calcularResoluciones();

        crearInterfaz();
        Gdx.input.setInputProcessor(stage);
    }

    private void calcularResoluciones() {
        int anchoMax = Config.getAnchoMonitor();
        int altoMax = Config.getAltoMonitor();

        Resolucion[] resolucionesDisponibles = Resolucion.values();
        Resolucion[] resolucionesSoportadas = new Resolucion[resolucionesDisponibles.length];
        int cantidadResolucionesDisponibles=0;

        for(int i=0; i < resolucionesDisponibles.length; i++){
            Resolucion r = resolucionesDisponibles[i];

            if(r.getAncho() <= anchoMax && r.getAlto() <= altoMax){
                resolucionesSoportadas[cantidadResolucionesDisponibles] = r;
                cantidadResolucionesDisponibles++;
            }
        }

        resoluciones = new Resolucion[cantidadResolucionesDisponibles];

        for(int i=0; i < cantidadResolucionesDisponibles; i++){
            resoluciones[i] = resolucionesSoportadas[i];
        }
    }

    private void crearInterfaz() {
        Table tabla = new Table();
        tabla.setFillParent(true);
        tabla.center();

        Label titulo = new Label("CONFIGURACION", skin);
        titulo.setFontScale(1.5f);

        Label textoResolucion = new Label("Resolucion:", skin);

        SelectBox<Resolucion> selectorResoluciones = new SelectBox<>(skin);
        selectorResoluciones.setItems(resoluciones);
        selectorResoluciones.setSelected(Resolucion.values()[0]);

        for (Resolucion resolucion : resoluciones) {
            if (resolucion.getAncho() == Gdx.graphics.getWidth()
                && resolucion.getAlto() == Gdx.graphics.getHeight()) {

                selectorResoluciones.setSelected(resolucion);
                break;
            }
        }

        selectorResoluciones.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                Resolucion seleccionada = selectorResoluciones.getSelected();

                Gdx.graphics.setWindowedMode(
                    seleccionada.getAncho(),
                    seleccionada.getAlto()
                );
            }
        });

        textoVolumen = new Label(
            "Volumen: " + (int)(volumen * 100) + "%",
            skin
        );

        textoSilencio = new Label(
            "Sonido: " + (sonidoSilenciado ? "Silenciado" : "Activado"),
            skin
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

                    // Aca tambien iria Music.setVolume() / Sound.play().
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

                    // Aca conectariamos Music.setVolume() o Sound.play().
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

                    // Aca despues conectamos lo de Music/Sound.
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
        tabla.add(selectorResoluciones).width(250).padBottom(25).row();

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
