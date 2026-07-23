package com.g2l.speedg2l.componentes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.g2l.speedg2l.utilidades.Render;

public class Texto {
    private BitmapFont fuente;
    private float x=0, y=0;
    private String texto="";

    public Texto(String rutaFuente, int tamanio, Color color){
        FreeTypeFontGenerator generador = new FreeTypeFontGenerator(Gdx.files.internal(rutaFuente));
        FreeTypeFontParameter parametros = new FreeTypeFontParameter();

        parametros.size = tamanio;
        parametros.color = color;

        fuente = generador.generateFont(parametros);

    }

    public void dibujar(){
        fuente.draw(Render.batch, texto, x, y);
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    // ver si conviene hacer un setTamanio tambien.

}
