package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.Gdx;

public class Config {

    private static int ancho = 1280;
    private static int alto = 720;

    private Config() {}

    public static void setAncho(int ancho) {
        if(ancho < 0){
            throw new IllegalArgumentException("Error, el ancho de la resolucion no puede ser menor a cero.");
        }else{
            Config.ancho = ancho;
        }
    }

    public static void setAlto(int alto) {
        if(alto < 0){
            throw new IllegalArgumentException("Error, el alto de la resolucion no puede ser menor a cero.");
        }else{
            Config.alto = alto;
        }
    }

    public static int getAncho() {
        return ancho;
    }
    public static int getAlto() {
        return alto;
    }

//    public static int getAnchoMonitor(){
//        return Gdx.graphics.getDisplayMode().width;
//    }
//
//    public static int getAltoMonitor(){
//        return Gdx.graphics.getDisplayMode().height;
//    }

}
