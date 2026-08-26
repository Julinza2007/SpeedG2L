package com.g2l.speedg2l.componentes;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Boton extends TextButton {
    private float ancho, altura;
    public Boton(String texto, Skin skin, ClickListener listener, float ancho, float altura) {
        super(texto, skin);
        if(listener == null){
            throw new IllegalArgumentException("Error, el listener no puede ser nulo.");
        }else{
            this.addListener(listener);
        }
        if(ancho <= 0){
            throw new IllegalArgumentException("Error, el ancho no puede ser menor o igual a cero al crear el boton.");
        }else{
            this.ancho = ancho;
            this.setWidth(ancho);
        }

        if(altura <= 0){
            throw new IllegalArgumentException("Error, la altura no puede ser menor o igual a cero al crear el boton.");
        }else{
            this.altura = altura;
            this.setHeight(altura);
        }
    }

    public Boton(String texto, Skin skin, ClickListener listener) {
        super(texto, skin);
        if(listener == null){
            throw new IllegalArgumentException("Error, el listener no puede ser nulo.");
        }else{
            this.addListener(listener);
        }
        this.ancho = this.getWidth();
        this.altura = this.getHeight();
    }


    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        this.ancho = width;
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        this.altura = height;
    }

    public float getAncho() {
        return ancho;
    }

    public float getAltura() {
        return altura;
    }
}
