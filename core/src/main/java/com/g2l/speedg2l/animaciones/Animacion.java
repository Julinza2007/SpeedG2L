package com.g2l.speedg2l.animaciones;

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

    public abstract boolean iniciar();

    public boolean isTerminado() {
        return terminado;
    }

    public float getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }
}
