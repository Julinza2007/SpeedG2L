package com.g2l.speedg2l.entidades;

import com.g2l.speedg2l.utilidades.Entradas;

import java.util.ArrayList;

public class Jugador extends Entidad{

    private final double velocidadX = 1;
    private final double velocidadY = 10;

    private int posicionTecho = 300;
    private int posicionSuelo = 100;

    private double gravedad = 0.4;
    private double velocidadYMenosGravedad = velocidadY;

    private double velocidadConAceleracionDerecha = velocidadX;
    private double velocidadConAceleracionIzquierda = velocidadX;
    private double aceleracion = 0.1;
    private boolean acelerandoDerecha = false;
    private boolean acelerandoIzquierda = false;
    private final double velocidadMaxima = 10;

    boolean saltando = false;

    public Jugador(float ancho , float alto, float posicionX, float posicionY){
        super(ancho, alto, posicionX, posicionY);
    }

    public void moverJugador(Entradas entradas){
        if (entradas.izquierda()){
            acelerandoIzquierda = true;
        }
        else{
            acelerandoIzquierda = false;
        }

        if (entradas.derecha()){
            acelerandoDerecha = true;
        }
        else{
            acelerandoDerecha = false;
        }

        /*
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
=======
        if (entradas.abajo()){
>>>>>>> a3a6fbe29620d4cf94e3e310ea5fd019e698c0ba
            this.posicionY -= velocidadY;
        }
        */

        if (entradas.arriba() && !saltando){
            this.saltando = true;
        }
    }

    public void actualizarFisicas(ArrayList<Entidad> listaDeEntidades){
        if (saltando){
            saltar(listaDeEntidades);
        }
        if (acelerandoDerecha){
            acelerarDerecha();
        }
        else{
            desAcelerarDerecha();
        }

        if(acelerandoIzquierda){
            acelerarIzquierda();
        }
        else{
            desAcelerarIzquierda();
        }
    }

    private void saltar(ArrayList<Entidad> listaDeEntidades){
        velocidadYMenosGravedad -= gravedad;
        this.posicionY += velocidadYMenosGravedad;
        int indiceColision = detectarColsionArriba(listaDeEntidades);
        if (posicionY >= posicionTecho || indiceColision != -1) {
                velocidadYMenosGravedad = 0;
                if (indiceColision != -1){
                    posicionY = (float) listaDeEntidades.get(indiceColision).getPosicionY();
                }
        }
        if (posicionY <= posicionSuelo){
            this.saltando = false;
            velocidadYMenosGravedad = velocidadY;
            posicionY = posicionSuelo;
            return;
        }
    }

    private void acelerarDerecha(){
        if(velocidadConAceleracionDerecha <= velocidadMaxima){
            this.velocidadConAceleracionDerecha += aceleracion;
        }
        posicionX += velocidadConAceleracionDerecha;
        // System.out.println("La aceleracion del jugador es de: " + velocidadConAceleracionDerecha);
    }

    private void desAcelerarDerecha(){
        if (velocidadConAceleracionDerecha > 0){
            velocidadConAceleracionDerecha -= aceleracion;
        }
        posicionX += velocidadConAceleracionDerecha;
    }

    private void acelerarIzquierda(){
        if(velocidadConAceleracionIzquierda <= velocidadMaxima){
            velocidadConAceleracionIzquierda += aceleracion;
        }
        posicionX -= velocidadConAceleracionIzquierda;
    }

    private void desAcelerarIzquierda(){
        if (velocidadConAceleracionIzquierda > 0){
            velocidadConAceleracionIzquierda -= aceleracion;
        }
        posicionX -= velocidadConAceleracionIzquierda;
    }


    private int detectarColsionArriba(ArrayList<Entidad> listaDeEntidades){
        boolean hayColision = false;
        int indiceColision = -1;
        int i=0;
        while(i<listaDeEntidades.size() && !hayColision){
            if ((posicionY - getAlto()) == listaDeEntidades.get(i).getPosicionX()) {
                hayColision = true;
                indiceColision = i;
            }
            i++;
        }

        return indiceColision;
    }

}
