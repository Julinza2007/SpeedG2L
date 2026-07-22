package com.g2l.speedg2l.componentes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.g2l.speedg2l.utilidades.Render;

public class Imagen {
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
}
