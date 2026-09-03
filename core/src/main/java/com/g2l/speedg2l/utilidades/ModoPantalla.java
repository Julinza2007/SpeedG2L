package com.g2l.speedg2l.utilidades;

public enum ModoPantalla {

    VENTANA("Ventana"),
    PANTALLA_COMPLETA("Pantalla completa");

    private String nombre;

    ModoPantalla(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
