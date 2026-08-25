package com.g2l.speedg2l.componentes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Render;

public class Texto {
    private BitmapFont fuente;
    private float x=0, y=0;
    private String texto;
    private GlyphLayout layout;

    public Texto(String rutaFuente, int tamanio, Color color) {
        FreeTypeFontGenerator generador = new FreeTypeFontGenerator(Gdx.files.internal(rutaFuente));
        FreeTypeFontParameter parametros = new FreeTypeFontParameter();

        parametros.size = tamanio;
        parametros.color = color;

        fuente = generador.generateFont(parametros);

        layout = new GlyphLayout();
    }

    public void dibujar() {
        fuente.draw(Render.batch, texto, x, y);
    }

    public void setTexto(String texto) {
        this.texto = texto;
        layout.setText(fuente, texto);
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

    public void centrar(){
        this.setPosition((Config.getAncho()/2.0f) - (getAncho()/2), (Config.getAncho()/2.0f) - (getAlto()/2));
    }

    public void centrarArriba(int paddingTop){
        this.setPosition((Config.getAncho()/2.0f) - (getAncho()/2), Config.getAlto() - paddingTop);
    }

    public void centrarAbajo(int paddingBottom){
        this.setPosition((Config.getAncho()/2.0f) - (getAncho()/2), getAlto() + paddingBottom);
    }

    public String getTexto() {
        return texto;
    }

    public float getAncho(){
        return layout.width;
    }

    public float getAlto(){
        return layout.height;
    }

    // ver si conviene hacer un setTamanio tambien.

}
