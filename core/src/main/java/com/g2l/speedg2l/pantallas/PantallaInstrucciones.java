package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

public class PantallaInstrucciones extends Pantalla{
    private Texto texto;
    private Batch b;

    @Override
    public void show() {
        b = Render.batch;
        texto = new Texto(Recursos.FUENTE_MENU, 20, Color.WHITE);
        texto.setTexto("Instrucciones");
        texto.centrar();
    }

    @Override
    public void render(float delta) {
        b.begin();
        texto.dibujar();
        b.end();
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
        texto.cerrar();
        b.dispose();
    }
}
