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
import com.g2l.speedg2l.utilidades.ModoPantalla;
import com.g2l.speedg2l.utilidades.Render;
import com.g2l.speedg2l.utilidades.Resolucion;

public class PantallaConfig extends Pantalla {

    private Stage stage;
    private Skin skin;

    private Label textoVolumen;
    private Label textoSilencio;

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

        Resolucion[] resolucionesSoportadas =
            new Resolucion[resolucionesDisponibles.length];

        int cantidadResolucionesDisponibles = 0;

        for (int i = 0; i < resolucionesDisponibles.length; i++) {

            Resolucion r = resolucionesDisponibles[i];

            if (r.getAncho() <= anchoMax
                && r.getAlto() <= altoMax) {

                resolucionesSoportadas[cantidadResolucionesDisponibles] = r;
                cantidadResolucionesDisponibles++;
            }
        }

        resoluciones =
            new Resolucion[cantidadResolucionesDisponibles];

        for (int i = 0; i < cantidadResolucionesDisponibles; i++) {

            resoluciones[i] = resolucionesSoportadas[i];
        }
    }

    private void crearInterfaz() {

        Table tabla = new Table();
        tabla.setFillParent(true);
        tabla.center();

        Label titulo =
            new Label("CONFIGURACION", skin);

        titulo.setFontScale(1.5f);

        Label textoResolucion =
            new Label("Resolucion:", skin);

        SelectBox<Resolucion> selectorResoluciones =
            new SelectBox<>(skin);

        selectorResoluciones.setItems(resoluciones);

        selectorResoluciones.setSelected(resoluciones[0]);

        for (Resolucion resolucion : resoluciones) {

            if (resolucion.getAncho() == Gdx.graphics.getWidth()
                && resolucion.getAlto() == Gdx.graphics.getHeight()) {

                selectorResoluciones.setSelected(resolucion);
                break;
            }
        }

        Label textoModoPantalla =
            new Label("Modo de pantalla:", skin);

        SelectBox<String> selectorModoPantalla =
            new SelectBox<>(skin);

        selectorModoPantalla.setItems(
            ModoPantalla.VENTANA.getNombre(),
            ModoPantalla.PANTALLA_COMPLETA.getNombre()
        );

        selectorModoPantalla.setSelected(
            ModoPantalla.VENTANA.getNombre()
        );

        selectorResoluciones.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

                Resolucion seleccionada =
                    selectorResoluciones.getSelected();

                String modo =
                    selectorModoPantalla.getSelected();

                if (modo.equals(
                    ModoPantalla.VENTANA.getNombre())) {

                    Gdx.graphics.setWindowedMode(
                        seleccionada.getAncho(),
                        seleccionada.getAlto()
                    );

                } else {

                    for (
                        com.badlogic.gdx.Graphics.DisplayMode displayMode :
                        Gdx.graphics.getDisplayModes()
                    ) {

                        if (
                            displayMode.width == seleccionada.getAncho()
                                && displayMode.height == seleccionada.getAlto()
                        ) {

                            Gdx.graphics.setFullscreenMode(
                                displayMode
                            );

                            break;
                        }
                    }
                }
            }
        });

        selectorModoPantalla.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

                String seleccionado =
                    selectorModoPantalla.getSelected();

                Resolucion resolucion =
                    selectorResoluciones.getSelected();

                if (seleccionado.equals(
                    ModoPantalla.VENTANA.getNombre())) {

                    Gdx.graphics.setWindowedMode(
                        resolucion.getAncho(),
                        resolucion.getAlto()
                    );

                } else {

                    for (
                        com.badlogic.gdx.Graphics.DisplayMode modo :
                        Gdx.graphics.getDisplayModes()
                    ) {

                        if (
                            modo.width == resolucion.getAncho()
                                && modo.height == resolucion.getAlto()
                        ) {

                            Gdx.graphics.setFullscreenMode(modo);

                            break;
                        }
                    }
                }
            }
        });

        textoVolumen =
            new Label(
                "Volumen: " +
                    Math.round(Config.getVolumenMusica() * 100) +
                    "%",
                skin
            );

        textoSilencio =
            new Label(
                "Sonido: " +
                    (
                        Config.isSonidoSilenciado()
                            ? "Silenciado"
                            : "Activado"
                    ),
                skin
            );

        Boton btnSubirVolumen =
            new Boton(
                "Subir volumen",
                skin,

                new ClickListener() {

                    @Override
                    public void clicked(
                        InputEvent event,
                        float x,
                        float y
                    ) {

                        float volumenActual =
                            Config.getVolumenMusica();

                        volumenActual += 0.1f;

                        Config.setVolumenMusica(volumenActual);

                        if (!Config.isSonidoSilenciado()) {

                            if (Render.musicaJuego != null) {

                                Render.musicaJuego.volumen(
                                    Config.getVolumenMusica()
                                );
                            }
                        }

                        actualizarTextoVolumen();
                    }
                },

                250,
                60
            );

        Boton btnBajarVolumen =
            new Boton(
                "Bajar volumen",
                skin,

                new ClickListener() {

                    @Override
                    public void clicked(
                        InputEvent event,
                        float x,
                        float y
                    ) {

                        float volumenActual =
                            Config.getVolumenMusica();

                        volumenActual -= 0.1f;

                        Config.setVolumenMusica(volumenActual);

                        if (!Config.isSonidoSilenciado()) {

                            if (Render.musicaJuego != null) {

                                Render.musicaJuego.volumen(
                                    Config.getVolumenMusica()
                                );
                            }
                        }

                        actualizarTextoVolumen();
                    }
                },

                250,
                60
            );

        Boton btnSilenciar =
            new Boton(
                "Silenciar / activar",
                skin,

                new ClickListener() {

                    @Override
                    public void clicked(
                        InputEvent event,
                        float x,
                        float y
                    ) {

                        boolean silenciado =
                            !Config.isSonidoSilenciado();

                        Config.setSonidoSilenciado(silenciado);

                        if (Render.musicaJuego != null) {

                            if (silenciado) {

                                Render.musicaJuego.volumen(0.0f);

                            } else {

                                Render.musicaJuego.volumen(
                                    Config.getVolumenMusica()
                                );
                            }
                        }

                        actualizarTextoVolumen();
                        actualizarTextoSilencio();
                    }
                },

                250,
                60
            );

        // VOLVER
        Boton btnVolver =
            new Boton(
                "Volver",
                skin,

                new ClickListener() {

                    @Override
                    public void clicked(
                        InputEvent event,
                        float x,
                        float y
                    ) {

                        cambiarPantalla(
                            new PantallaMenu()
                        );
                    }
                },

                250,
                60
            );

        tabla.add(titulo)
            .padBottom(30)
            .row();

        tabla.add(textoResolucion)
            .padBottom(10)
            .row();

        tabla.add(selectorResoluciones)
            .width(250)
            .padBottom(25)
            .row();

        tabla.add(textoModoPantalla)
            .padBottom(10)
            .row();

        tabla.add(selectorModoPantalla)
            .width(250)
            .padBottom(25)
            .row();

        tabla.add(textoVolumen)
            .padBottom(10)
            .row();

        tabla.add(btnSubirVolumen)
            .pad(5)
            .row();

        tabla.add(btnBajarVolumen)
            .pad(5)
            .row();

        tabla.add(textoSilencio)
            .padTop(15)
            .padBottom(10)
            .row();

        tabla.add(btnSilenciar)
            .padBottom(25)
            .row();

        tabla.add(btnVolver)
            .row();

        stage.addActor(tabla);
    }

    private void actualizarTextoVolumen() {

        textoVolumen.setText(
            "Volumen: " +
                Math.round(Config.getVolumenMusica() * 100) +
                "%"
        );
    }

    private void actualizarTextoSilencio() {

        textoSilencio.setText(
            "Sonido: " +
                (
                    Config.isSonidoSilenciado()
                        ? "Silenciado"
                        : "Activado"
                )
        );
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
