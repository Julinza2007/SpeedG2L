package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.animaciones.Fade;
import com.g2l.speedg2l.utilidades.Entradas;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

public class PantallaCarga extends Pantalla {

    private Imagen imagenFondo;
    private SpriteBatch b;
    private Entradas entradas;
    private Fade fade;
    @Override

    public void show() {
        b = Render.batch;
        imagenFondo = new Imagen(Recursos.FONDO_CARGA);
        imagenFondo.setSize(405, 512);
        imagenFondo.centrar();
        imagenFondo.setTransparencia(0);
        fade = new Fade(5.0f, imagenFondo);
        entradas = new Entradas();
//        Gdx.input.setInputProcessor(entradas);
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();
        fade.iniciar();
        configViewport.aplicarViewport(b);
        b.begin();
        imagenFondo.dibujar();
        b.end();
        if(fade.isTerminado()){
            cambiarPantalla(new PantallaMenu());
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
        b.dispose();
        imagenFondo.cerrar();
    }
}
