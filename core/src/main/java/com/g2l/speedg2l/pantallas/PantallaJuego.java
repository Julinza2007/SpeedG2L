package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.entidades.Entidad;
import com.g2l.speedg2l.entidades.Jugador;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Entradas;
import com.g2l.speedg2l.utilidades.Render;

import java.util.ArrayList;

public class PantallaJuego implements Screen {

    private Stage stage;

    private Jugador jugador;
    private Imagen imagenJugador;
    private SpriteBatch b;
    private ArrayList<Entidad> listaDeEntidades;

    @Override
    public void show() {
        b = Render.batch;
        jugador = new Jugador(50.0f, 50.0f, 100.0f, 100.0f);
        imagenJugador = new Imagen("libgdx.png");
        imagenJugador.setSize(jugador.getAncho(), jugador.getAlto());
        listaDeEntidades = new ArrayList<>();
        listaDeEntidades.add(jugador);

        stage = new Stage();

        
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();

        jugador.moverJugador(new Entradas());
        jugador.actualizarFisicas(listaDeEntidades);

        b.begin();
        imagenJugador.setX((float) jugador.getPosicionX());
        imagenJugador.setY((float) jugador.getPosicionY());
        imagenJugador.dibujar();
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

    }
}
