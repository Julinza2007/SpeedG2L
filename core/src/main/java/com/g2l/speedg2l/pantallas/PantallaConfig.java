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

        configurarInput(stage);
    }

    private void calcularResoluciones() {
        int anchoMax = Config.getAnchoMonitor();
        int altoMax = Config.getAltoMonitor();

        Resolucion[] resolucionesDisponibles = Resolucion.values();
        Resolucion[] resolucionesSoportadas = new Resolucion[resolucionesDisponibles.length];

        int cantidadResolucionesDisponibles = 0;

        for (int i = 0; i < resolucionesDisponibles.length; i++) {
            Resolucion r = resolucionesDisponibles[i];

            if (r.getAncho() <= anchoMax && r.getAlto() <= altoMax) {
                resolucionesSoportadas[cantidadResolucionesDisponibles] = r;
                cantidadResolucionesDisponibles++;
            }
        }

        resoluciones = new Resolucion[cantidadResolucionesDisponibles];

        for (int i = 0; i < cantidadResolucionesDisponibles; i++) {
            resoluciones[i] = resolucionesSoportadas[i];
        }
    }

    private void crearInterfaz() {
        Table tabla = crearTabla();
        Label titulo = crearTitulo();
        SelectBox<Resolucion> selectorResoluciones = crearSelectorResoluciones();

        SelectBox<String> selectorModoPantalla = crearSelectorModoPantalla();

        configurarListenersPantalla(selectorResoluciones, selectorModoPantalla);

        crearTextosVolumen();

        Boton btnSubirVolumen =
            crearBotonSubirVolumen();

        Boton btnBajarVolumen =
            crearBotonBajarVolumen();

        Boton btnSilenciar =
            crearBotonSilenciar();

        Boton btnVolver =
            crearBotonVolver();

        agregarControlesTabla(
            tabla,
            titulo,
            selectorResoluciones,
            selectorModoPantalla,
            btnSubirVolumen,
            btnBajarVolumen,
            btnSilenciar,
            btnVolver
        );

        stage.addActor(tabla);
    }

    private Table crearTabla() {
        Table tabla = new Table();
        tabla.setFillParent(true);
        tabla.center();

        return tabla;
    }

    private Label crearTitulo() {
        Label titulo = new Label("CONFIGURACION", skin);
        titulo.setFontScale(1.5f);
        return titulo;
    }

    private SelectBox<Resolucion> crearSelectorResoluciones() {
        SelectBox<Resolucion> selectorResoluciones = new SelectBox<>(skin);
        selectorResoluciones.setItems(resoluciones);
        selectorResoluciones.setSelected(resoluciones[0]);
        seleccionarResolucionActual(selectorResoluciones);

        return selectorResoluciones;
    }

    private void seleccionarResolucionActual(SelectBox<Resolucion> selectorResoluciones) {
        boolean encontrada=false;
        int i=0;
        while(i < resoluciones.length && !encontrada){
            Resolucion resolucion = resoluciones[i];
            if (resolucion.getAncho() == Config.getAnchoPantallaActual() &&
                resolucion.getAlto() == Config.getAltoPantallaActual()
            ) {
                selectorResoluciones.setSelected(resolucion);
                encontrada=true;
            }
            i++;
        }
    }

    private SelectBox<String> crearSelectorModoPantalla() {
        SelectBox<String> selectorModoPantalla = new SelectBox<>(skin);

        selectorModoPantalla.setItems(
            ModoPantalla.VENTANA.getNombre(),
            ModoPantalla.PANTALLA_COMPLETA.getNombre()
        );

        selectorModoPantalla.setSelected(ModoPantalla.VENTANA.getNombre());

        return selectorModoPantalla;
    }

    private void configurarListenersPantalla(SelectBox<Resolucion> selectorResoluciones,
        SelectBox<String> selectorModoPantalla) {

        selectorResoluciones.addListener(
            new ChangeListener() {
                @Override
                public void changed(
                    ChangeEvent event,
                    Actor actor
                ) {
                    Resolucion resolucion = selectorResoluciones.getSelected();
                    String modo = selectorModoPantalla.getSelected();
                    aplicarConfiguracionPantalla(resolucion,modo);
                }
            }
        );

        selectorModoPantalla.addListener(
            new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    String modo = selectorModoPantalla.getSelected();
                    Resolucion resolucion = selectorResoluciones.getSelected();
                    aplicarConfiguracionPantalla(resolucion, modo);
                }
            }
        );
    }

    private void aplicarConfiguracionPantalla(Resolucion resolucion, String modo) {
        if (modo.equals(ModoPantalla.VENTANA.getNombre())){
            aplicarModoVentana(resolucion);
        }else {
            aplicarPantallaCompleta(resolucion);
        }
    }

    private void aplicarModoVentana(Resolucion resolucion) {
        Gdx.graphics.setWindowedMode(
            resolucion.getAncho(),
            resolucion.getAlto()
        );
    }

    private void aplicarPantallaCompleta(Resolucion resolucion) {
        boolean encontrado = false;
        int i=0;
        com.badlogic.gdx.Graphics.DisplayMode[] modos = Gdx.graphics.getDisplayModes();
        while (i < modos.length && !encontrado) {
            com.badlogic.gdx.Graphics.DisplayMode modo = modos[i];
            if (modo.width == resolucion.getAncho() &&
                modo.height == resolucion.getAlto()) {
                Gdx.graphics.setFullscreenMode(modo);
                encontrado = true;
            }
            i++;
        }
    }

    private void crearTextosVolumen() {
        textoVolumen = new Label("Volumen: " + Math.round(Config.getVolumenMaster() * 100) + "%", skin);
        textoSilencio = new Label("Sonido: " +(Config.isSonidoSilenciado()? "Silenciado": "Activado"), skin);
    }

    private Boton crearBotonSubirVolumen() {
        return new Boton("Subir volumen", skin,
            new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    float volumenActual = Config.getVolumenMaster();
                    volumenActual += 0.1f;
                    Config.setVolumenMaster(volumenActual);
                    actualizarVolumen();
                    actualizarTextoVolumen();
                }
            },
            250,
            60
        );
    }

    private Boton crearBotonBajarVolumen() {

        return new Boton("Bajar volumen", skin,
            new ClickListener() {

                @Override
                public void clicked(
                    InputEvent event,
                    float x,
                    float y
                ) {
                    float volumenActual = Config.getVolumenMaster();
                    volumenActual -= 0.1f;
                    Config.setVolumenMaster(volumenActual);
                    actualizarVolumen();
                    actualizarTextoVolumen();
                }
            },
            250,
            60
        );
    }

    private Boton crearBotonSilenciar() {
        return new Boton("Silenciar / activar", skin,
            new ClickListener() {

                @Override
                public void clicked(
                    InputEvent event,
                    float x,
                    float y
                ) {
                    boolean silenciado = !Config.isSonidoSilenciado();
                    Config.setSonidoSilenciado(silenciado);
                    actualizarVolumen();
                    actualizarTextoVolumen();
                    actualizarTextoSilencio();
                }
            },
            250,
            60
        );
    }

    private void actualizarVolumen() {
        if (Render.musicaJuego == null) {
            return;
        }else if(Config.isSonidoSilenciado()) {
            Render.musicaJuego.setVolumen(0.0f);
        } else {
            Render.musicaJuego.setVolumen(Config.getVolumenMaster());
        }
    }

    private Boton crearBotonVolver() {
        return new Boton("Volver", skin,
            new ClickListener() {

                @Override
                public void clicked(
                    InputEvent event,
                    float x,
                    float y
                ) {
                    cambiarPantalla(new PantallaMenu());
                }
            },
            250,
            60
        );
    }

    private void agregarControlesTabla(
        Table tabla,
        Label titulo,
        SelectBox<Resolucion> selectorResoluciones,
        SelectBox<String> selectorModoPantalla,
        Boton btnSubirVolumen,
        Boton btnBajarVolumen,
        Boton btnSilenciar,
        Boton btnVolver
    ) {

        Label textoResolucion = new Label("Resolucion:", skin);
        Label textoModoPantalla = new Label("Modo de pantalla:", skin);

        tabla.add(titulo).padBottom(30).row();
        tabla.add(textoResolucion).padBottom(10).row();

        tabla.add(selectorResoluciones).width(250).padBottom(25).row();

        tabla.add(textoModoPantalla).padBottom(10).row();

        tabla.add(selectorModoPantalla).width(250).padBottom(25).row();

        tabla.add(textoVolumen).padBottom(10).row();

        tabla.add(btnSubirVolumen).pad(5).row();

        tabla.add(btnBajarVolumen).pad(5).row();

        tabla.add(textoSilencio).padTop(15).padBottom(10).row();

        tabla.add(btnSilenciar).padBottom(25).row();

        tabla.add(btnVolver).row();
    }

    private void actualizarTextoVolumen() {
        textoVolumen.setText("Volumen: " + Math.round(Config.getVolumenMaster() * 100) + "%");
    }

    private void actualizarTextoSilencio() {
        textoSilencio.setText(
            "Sonido: " + (Config.isSonidoSilenciado() ? "Silenciado": "Activado")
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
