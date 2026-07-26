package com.g2l.speedg2l.entidades;

import com.badlogic.gdx.math.Rectangle;
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
            acelerarDerecha(listaDeEntidades);
        }
        else{
            desAcelerarDerecha(listaDeEntidades);
        }

        if(acelerandoIzquierda){
            acelerarIzquierda(listaDeEntidades);
        }
        else{
            desAcelerarIzquierda(listaDeEntidades);
        }
    }

    private void saltar(ArrayList<Entidad> listaDeEntidades){
        velocidadYMenosGravedad -= gravedad;
        if (posicionY >= posicionTecho || hayColisionVertical(listaDeEntidades, velocidadYMenosGravedad)) {
            velocidadYMenosGravedad = 0;
        }

        this.posicionY += velocidadYMenosGravedad;
        actualizarHitbox();

        if (posicionY <= posicionSuelo){
            this.saltando = false;
            velocidadYMenosGravedad = velocidadY;
            posicionY = posicionSuelo;
            actualizarHitbox();
            return;
        }
    }

    private boolean hayColisionVertical(ArrayList<Entidad> listaDeEntidades, double aceleracion) {

        boolean hayColision = false;

        Rectangle futuraHitbox = new Rectangle(
            (float) getPosicionX(),
            (float) getPosicionY() + (float) aceleracion,
            getAncho(),
            getAlto()
        );

        int i =0;

        while(i<listaDeEntidades.size() && !hayColision){

            if(futuraHitbox.overlaps(listaDeEntidades.get(i).getHitbox())){
                hayColision = true;
            }

            i++;
        }

        return hayColision;
    }

    private void acelerarDerecha(ArrayList<Entidad> listaDeEntidades){
        if(velocidadConAceleracionDerecha <= velocidadMaxima){
            this.velocidadConAceleracionDerecha += aceleracion;
        }
        if(!hayColisionHorizontal(listaDeEntidades, velocidadConAceleracionDerecha)){
            posicionX += velocidadConAceleracionDerecha;
        }
        else{
            velocidadConAceleracionDerecha = 0;
        }
        actualizarHitbox();
        // System.out.println("La aceleracion del jugador es de: " + velocidadConAceleracionDerecha);
    }

    private void desAcelerarDerecha(ArrayList<Entidad> listaDeEntidades){
        if (velocidadConAceleracionDerecha > 0){
            velocidadConAceleracionDerecha -= aceleracion;
        }
        if (!hayColisionHorizontal(listaDeEntidades, velocidadConAceleracionDerecha)) {
            posicionX += velocidadConAceleracionDerecha;
        }
        else{
            velocidadConAceleracionDerecha = 0;
        }
        actualizarHitbox();
    }

    private void acelerarIzquierda(ArrayList<Entidad> listaDeEntidades){
        if(velocidadConAceleracionIzquierda <= velocidadMaxima){
            velocidadConAceleracionIzquierda += aceleracion;
        }
        if(!hayColisionHorizontal(listaDeEntidades, -velocidadConAceleracionIzquierda)){ //Como acelera hacia la izquierda se pone la velocidad negativa
            posicionX -= velocidadConAceleracionIzquierda;
        }
        else{
            velocidadConAceleracionIzquierda = 0;
        }
        actualizarHitbox();
    }

    private void desAcelerarIzquierda(ArrayList<Entidad> listaDeEntidades){
        if (velocidadConAceleracionIzquierda > 0){
            velocidadConAceleracionIzquierda -= aceleracion;
        }
        if (!hayColisionHorizontal(listaDeEntidades, -velocidadConAceleracionIzquierda)) { //Como acelera hacia la izquierda se pone la velocidad negativa
            posicionX -= velocidadConAceleracionIzquierda;
        }
        else{
            velocidadConAceleracionDerecha = 0;
        }
        actualizarHitbox();
    }

    private boolean hayColisionHorizontal(ArrayList<Entidad> listaDeEntidades, double aceleracion) {

        boolean hayColision = false;

        Rectangle futuraHitbox = new Rectangle(
            (float) getPosicionX() + (float) aceleracion,
            (float) getPosicionY(),
            getAncho(),
            getAlto()
        );

        int i =0;

        while(i<listaDeEntidades.size() && !hayColision){

            if(futuraHitbox.overlaps(listaDeEntidades.get(i).getHitbox())){
                hayColision = true;
            }

            i++;
        }

        return hayColision;
    }


    private int detectarColsionArriba(ArrayList<Entidad> listaDeEntidades){
        boolean hayColision = false;
        int indiceColision = -1;
        int i=0;
        while(i<listaDeEntidades.size() && !hayColision){
            Entidad entidadIndiceI = listaDeEntidades.get(i);
            if ((posicionY + getAlto()) >= entidadIndiceI.getPosicionY()
                && (posicionY + getAlto()) <= (entidadIndiceI.getPosicionY() + entidadIndiceI.getAlto())) {

                if ((posicionX + getAncho()) >= entidadIndiceI.getPosicionX()
                    && (posicionX) <= (entidadIndiceI.getPosicionX() + entidadIndiceI.getAncho())) {

                    hayColision = true;
                    indiceColision = i;

                }
            }
            i++;
        }

        return indiceColision;
    }

    private int detectarColsion(ArrayList<Entidad> listaDeEntidades){
        boolean hayColision = false;
        int indiceColision = -1;
        int i=0;
        while(i<listaDeEntidades.size() && !hayColision){
            Entidad entidadIndiceI = listaDeEntidades.get(i);
            if (getHitbox().overlaps(entidadIndiceI.getHitbox())){
                hayColision = true;
                indiceColision = i;
            }
            i++;
        }

        return indiceColision;
    }

}
