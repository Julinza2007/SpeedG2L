package com.g2l.speedg2l.utilidades.Animaciones;

import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.pantallas.PantallaMenu;
import com.g2l.speedg2l.utilidades.Render;

public abstract class Animacion {

    protected boolean terminado=false;
    protected float duracion;
    protected float tiempoTranscurrido;

    protected Animacion(float duracion){
        if(duracion < 0){
            throw new IllegalArgumentException("Error, el tiempo de la animacion no puede ser negativo.");
        }else{
            this.duracion = duracion;
        }
    }

    public float getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

}
