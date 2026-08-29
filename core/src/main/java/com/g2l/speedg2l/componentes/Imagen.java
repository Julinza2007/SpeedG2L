package com.g2l.speedg2l.componentes.imagenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.g2l.speedg2l.Cerrable;
import com.g2l.speedg2l.utilidades.Config;
import com.g2l.speedg2l.utilidades.Render;

public class Imagen implements Cerrable {
    private Texture t;
    private Sprite s;

    public Imagen(String ruta){
        t = new Texture(ruta);
        s = new Sprite(t);
    }

    public void dibujar(){
        s.draw(Render.batch);
    }

    public void setX(float x){
        s.setX(x);
    }

    public void setY(float y){
        s.setY(y);
    }

    public void setSize(float ancho, float altura){
        s.setSize(ancho, altura);
    }

    public void centrar() {
        setX((Config.getAnchoJuego() / 2.0f) - (s.getWidth() / 2));
        setY((Config.getAltoJuego() / 2.0f) - (s.getHeight() / 2));
    }

    public void setTransparencia(float alpha){
        s.setAlpha(alpha);
    }

    @Override
    public void cerrar() {
        t.dispose();
    }
}
