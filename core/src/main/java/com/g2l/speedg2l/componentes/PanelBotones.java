package com.g2l.speedg2l.componentes;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PanelBotones{
    private Table contenedor;

    public PanelBotones() {
        contenedor = new Table();
        contenedor.setFillParent(true);
    }

    public void agregarBoton(Boton boton, int padding){
        contenedor.add(boton)
            .size(boton.getAncho(), boton.getAltura())
            .pad(padding)
            .row();
    }

    public void centrar(){
        contenedor.center();
    }

    public Table getContenedor() {
        return contenedor;
    }
}
