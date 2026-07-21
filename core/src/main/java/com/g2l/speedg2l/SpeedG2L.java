package com.g2l.speedg2l;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.g2l.speedg2l.entidades.Jugador;

public class SpeedG2L extends Game {

    private SpriteBatch batch;
    private Texture image;
    private Jugador jugador = new Jugador();

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        jugador = new Jugador();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0f, 0f, 0f, 0f);
        jugador.moverJugador();
        // y detectar las colisiones.
        batch.begin();
        batch.draw(image, jugador.getPosicionX(), jugador.getPosicionY());
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
