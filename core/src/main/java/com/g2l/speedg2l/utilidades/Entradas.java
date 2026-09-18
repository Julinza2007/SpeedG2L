package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Entradas {

    public boolean izquierda() {
        return Gdx.input.isKeyPressed(Keys.A);
    }

    public boolean derecha() {
        return Gdx.input.isKeyPressed(Keys.D);
    }

    public boolean abajo() {
        return Gdx.input.isKeyPressed(Keys.S);
    }

    public boolean arriba() {
        return Gdx.input.isKeyPressed(Keys.W);
    }

    public boolean barraEspaciadora() {
        return Gdx.input.isKeyJustPressed(Keys.SPACE);
    }

    public boolean escape() {
        return Gdx.input.isKeyJustPressed(Keys.ESCAPE);
    }
}
