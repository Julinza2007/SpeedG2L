package com.g2l.speedg2l.entidades;

import com.g2l.speedg2l.utilidades.Entradas;

public class Jugador extends Entidad{

    private final double alto;
    private final double ancho;

    private final double velocidadX = 1;
    private final double velocidadY = 10;
    private double posicionX = 100;
    private double posicionY = 100;

    private int posicionTecho = 300;
    private int posicionSuelo = 100;

    private double gravedad = 0.5;
    private double velocidadYMenosGravedad = velocidadY;

    private double velocidadConAceleracionDerecha = velocidadX;
    private double velocidadConAceleracionIzquierda = velocidadX;
    private double aceleracion = 0.1;
    private boolean acelerandoDerecha = false;
    private boolean acelerandoIzquierda = false;
    private final double velocidadMaxima = 10;

    boolean saltando = false;

    public Jugador(float ancho , float alto){
        super(ancho, alto);
        this.ancho = ancho;
        this.alto = alto;
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

    public double getPosicionY(){
        return this.posicionY;
    }

    public double getPosicionX(){
        return this.posicionX;
    }

    public void actualizarFisicas(){
        if (saltando){
            saltar();
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

    private void saltar(){
        velocidadYMenosGravedad -= gravedad;
        this.posicionY += velocidadYMenosGravedad;
        if (posicionY >= posicionTecho /*|| hayColsionArriba()*/) {
                velocidadYMenosGravedad = 0;
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

    /*
    private boolean hayColsionArriba(){
        boolean hayColision = false;

        if (posicionY + alto){

        }

        return hayColision;
    }
    */
}
