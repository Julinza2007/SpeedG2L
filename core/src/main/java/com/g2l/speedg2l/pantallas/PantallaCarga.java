package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.utilidades.Animaciones.Fade;
import com.g2l.speedg2l.utilidades.Entradas;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

public class PantallaCarga implements Screen {

    private Imagen imagenFondo;
    private SpriteBatch b;
    private Texto coords;
    private Entradas entradas;
    private Fade fade;
    @Override
    public void show() {
        b = Render.batch;
        imagenFondo = new Imagen(Recursos.FONDO_CARGA);
        imagenFondo.setSize(405, 512);
        imagenFondo.centrar();
        imagenFondo.setTransparencia(0);
        fade = new Fade(5.0f, imagenFondo, new PantallaMenu());

        coords = new Texto(Recursos.FUENTE_MENU, 20, Color.WHITE);
        entradas = new Entradas();
        Gdx.input.setInputProcessor(entradas);
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();
        coords.setTexto("Coord x: " + entradas.getMouseX() + " Coord y: " + entradas.getMouseY());
        coords.centrar();
        System.out.println("Coord x: " + entradas.getMouseX() + " Coord y: " + entradas.getMouseY());
        fade.mostrarFade();
        b.begin();
        coords.dibujar();
        imagenFondo.dibujar();
        b.end();
    }



    @Override
    public void resize(int width, int height) {

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
    }
}
