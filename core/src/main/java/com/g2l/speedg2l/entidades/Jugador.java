package com.g2l.speedg2l.entidades;

import com.badlogic.gdx.math.Rectangle;
import com.g2l.speedg2l.utilidades.Direccion;
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
        Entidad entidadColisionada = null;
        entidadColisionada = hayColisionVertical(listaDeEntidades, velocidadYMenosGravedad);
        if (posicionY >= posicionTecho || entidadColisionada instanceof Plataforma) {
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

    protected void rebotar(ArrayList<Entidad> listaDeEntidades){
        saltar(listaDeEntidades);
        if (velocidadConAceleracionDerecha > 5){
            variacionVelocidad(0.5);
        }
        else if (velocidadConAceleracionDerecha > 0){
            velocidadConAceleracionDerecha = 2.5;
        }
        else if (velocidadConAceleracionIzquierda > 5){
            variacionVelocidad(0.5);
        }
        else if (velocidadConAceleracionIzquierda > 0){
            velocidadConAceleracionIzquierda = 5;
        }
        else if (velocidadConAceleracionIzquierda == 0 && velocidadConAceleracionDerecha == 0){
            velocidadConAceleracionDerecha = 2.5;
        }
    }

    protected void variacionVelocidad(double multiplicadorDeVariacion){
        if (velocidadConAceleracionDerecha > 0){
            velocidadConAceleracionDerecha *= multiplicadorDeVariacion;
        }
        else if (velocidadConAceleracionIzquierda > 0){
            velocidadConAceleracionIzquierda *= multiplicadorDeVariacion;
        }
    }

    private Entidad hayColisionVertical(ArrayList<Entidad> listaDeEntidades, double aceleracion) {

        boolean hayColision = false;
        Entidad entidadColisionada = null;

        Rectangle futuraHitbox = new Rectangle(
            (float) getPosicionX(),
            (float) getPosicionY() + (float) aceleracion,
            getAncho(),
            getAlto()
        );

        int i = 0;

        while(i<listaDeEntidades.size() && !hayColision){

            if(futuraHitbox.overlaps(listaDeEntidades.get(i).getHitbox())){
                hayColision = true;
                entidadColisionada = listaDeEntidades.get(i);
            }

            i++;
        }

        return entidadColisionada;
    }


    private void acelerarDerecha(ArrayList<Entidad> listaDeEntidades){
        if(velocidadConAceleracionDerecha <= velocidadMaxima){
            this.velocidadConAceleracionDerecha += aceleracion;
        }
        Entidad entidadColisionada = hayColisionHorizontal(listaDeEntidades, velocidadConAceleracionDerecha);
        trabajarColisionHorizontal(entidadColisionada, Direccion.DERECHA);
        actualizarHitbox();
        // System.out.println("La aceleracion del jugador es de: " + velocidadConAceleracionDerecha);
    }

    private void desAcelerarDerecha(ArrayList<Entidad> listaDeEntidades) {
        if (velocidadConAceleracionDerecha > 0) {
            velocidadConAceleracionDerecha -= aceleracion;
        }
        Entidad entidadColisionada = hayColisionHorizontal(listaDeEntidades, velocidadConAceleracionDerecha);
        trabajarColisionHorizontal(entidadColisionada, Direccion.DERECHA);
        actualizarHitbox();
    }

    private void acelerarIzquierda (ArrayList < Entidad > listaDeEntidades) {
            if (velocidadConAceleracionIzquierda <= velocidadMaxima) {
                velocidadConAceleracionIzquierda += aceleracion;
            }
            Entidad entidadColisionada = hayColisionHorizontal(listaDeEntidades, velocidadConAceleracionDerecha);
            trabajarColisionHorizontal(entidadColisionada, Direccion.IZQUIERDA);
            actualizarHitbox();
    }

    private void desAcelerarIzquierda (ArrayList < Entidad > listaDeEntidades) {
            if (velocidadConAceleracionIzquierda > 0) {
                velocidadConAceleracionIzquierda -= aceleracion;
            }
            Entidad entidadColisionada = hayColisionHorizontal(listaDeEntidades, velocidadConAceleracionDerecha);
            trabajarColisionHorizontal(entidadColisionada, Direccion.IZQUIERDA);
            actualizarHitbox();
    }

    private Entidad hayColisionHorizontal (ArrayList < Entidad > listaDeEntidades,double aceleracion){

            boolean hayColision = false;
            Entidad entidadColisionada = null;

            Rectangle futuraHitbox = new Rectangle(
                getPosicionX() + (float) aceleracion,
                getPosicionY(),
                getAncho(),
                getAlto()
            );

            int i = 0;

            while (i < listaDeEntidades.size() && !hayColision) {

                if (futuraHitbox.overlaps(listaDeEntidades.get(i).getHitbox())) {
                    hayColision = true;
                    entidadColisionada = listaDeEntidades.get(i);
                }

                i++;
            }

            return entidadColisionada;
    }


    private void trabajarColisionHorizontal(Entidad entidadColisionada, Direccion direccionMovimiento) {

        switch (direccionMovimiento) {
            case DERECHA:

                if (entidadColisionada == null){
                    posicionX += velocidadConAceleracionDerecha;
                }
                else if (entidadColisionada instanceof Plataforma) {
                    velocidadConAceleracionDerecha = 0;
                }
                else if (entidadColisionada instanceof Obstaculo){
                    Obstaculo obstaculoColisionado = (Obstaculo) entidadColisionada;
                    if (obstaculoColisionado.getColisionLateral()){
                        obstaculoColisionado.alColisionar(this);
                    }
                }

                break;


            case IZQUIERDA:

                if (entidadColisionada == null){
                    posicionX -= velocidadConAceleracionIzquierda;
                }
                else if (entidadColisionada instanceof Plataforma) {
                    velocidadConAceleracionIzquierda = 0;
                }
                else if (entidadColisionada instanceof Obstaculo){
                    Obstaculo obstaculoColisionado = (Obstaculo) entidadColisionada;
                    if (obstaculoColisionado.getColisionLateral()){
                        obstaculoColisionado.alColisionar(this);
                    }
                }
        }
    }

}
