package com.g2l.speedg2l.entidades;

import com.badlogic.gdx.math.Rectangle;
import com.g2l.speedg2l.animaciones.AnimacionEntidad;
import com.g2l.speedg2l.utilidades.Direccion;
import com.g2l.speedg2l.utilidades.Entradas;

import java.util.ArrayList;

public class Jugador extends Entidad{

    private final double velocidadX = 1;
    private final double velocidadY = 500;

    private int posicionTecho = 1000;
    private int posicionSuelo = 0;

    private double gravedad = 800;
    private double velocidadYMenosGravedad = 0;

    private double velocidadConAceleracionDerecha = velocidadX;
    private double velocidadConAceleracionIzquierda = velocidadX;
    private double aceleracion = 80;
    private boolean acelerandoDerecha = false;
    private boolean acelerandoIzquierda = false;
    private final double velocidadMaxima = 1000;
    private AnimacionEntidad animacion;
    boolean saltando = false;

    public Jugador(float ancho , float alto, float posicionX, float posicionY){
        super(ancho, alto, posicionX, posicionY);
        animacion = new AnimacionEntidad("soldado_corriendo.png", 0.09f, 4, 3);
        animacion.iniciar();
    }

    public void animar(float delta) {
        if (acelerandoDerecha || acelerandoIzquierda) {
            animacion.animar(delta);
        }
    }

    public void dibujar() {
        animacion.dibujar(
            posicionX,
            posicionY,
            getAncho(),
            getAlto()
        );
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
            velocidadYMenosGravedad = velocidadY;
            this.saltando = true;
        }
    }

    public void actualizarFisicas(ArrayList<Entidad> listaDeEntidades, float delta){

            actualizarMovimientoVertical(listaDeEntidades, delta);

        if (acelerandoDerecha){
            acelerarDerecha(listaDeEntidades, delta);
        }
        else{
            desAcelerarDerecha(listaDeEntidades, delta);
        }

        if(acelerandoIzquierda){
            acelerarIzquierda(listaDeEntidades, delta);
        }
        else{
            desAcelerarIzquierda(listaDeEntidades, delta);
        }
//        if(!saltando) {
//            int velocidadCayendo = 0;
//            Entidad entidadColisionada = null;
//            entidadColisionada = hayColisionVertical(listaDeEntidades, gravedad);
//            while (entidadColisionada == null) {
//                if(velocidadCayendo <= 10) {velocidadCayendo -= gravedad;}
//                else {velocidadCayendo = 10;}
//                posicionY += velocidadCayendo;
//                entidadColisionada = hayColisionVertical(listaDeEntidades, gravedad);
//            }
//        }
    }

    private void actualizarMovimientoVertical(ArrayList<Entidad> listaDeEntidades, float delta) {

        velocidadYMenosGravedad -= gravedad * delta;

        double desplazamientoY = velocidadYMenosGravedad * delta;

        Entidad entidadColisionada =
            hayColisionVertical(listaDeEntidades, desplazamientoY);

        if (entidadColisionada instanceof Plataforma) {

            if (desplazamientoY < 0) {
                // Está cayendo sobre la plataforma

                posicionY = entidadColisionada.getPosicionY()
                    + entidadColisionada.getAlto();

                velocidadYMenosGravedad = 0;
                saltando = false;
            }
            else {
                // Está subiendo y golpeó la parte inferior

                velocidadYMenosGravedad = 0;
            }

        }
        if (entidadColisionada == null){
            // No hay plataforma: continúa moviéndose
            posicionY += desplazamientoY;
        }

        // Evitar atravesar el suelo
        if (posicionY <= posicionSuelo) {
            posicionY = posicionSuelo;
            velocidadYMenosGravedad = 0;
            saltando = false;
        }

        actualizarHitbox();
    }


    protected void rebotar(ArrayList<Entidad> listaDeEntidades, float delta){
        actualizarMovimientoVertical(listaDeEntidades, delta);
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


    private void acelerarDerecha(ArrayList<Entidad> listaDeEntidades, float delta){
        if(velocidadConAceleracionDerecha < velocidadMaxima){
            this.velocidadConAceleracionDerecha += aceleracion * delta;
            if(velocidadConAceleracionDerecha > velocidadMaxima){
                velocidadConAceleracionDerecha = velocidadMaxima;
            }
        }
        double desplazamientoX = velocidadConAceleracionDerecha * delta;

        Entidad entidadColisionada = hayColisionHorizontal(listaDeEntidades, desplazamientoX);
        trabajarColisionHorizontal(entidadColisionada, Direccion.DERECHA, desplazamientoX);
        actualizarHitbox();
        // System.out.println("La aceleracion del jugador es de: " + velocidadConAceleracionDerecha);
    }

    private void desAcelerarDerecha(ArrayList<Entidad> listaDeEntidades, float delta) {
        if (velocidadConAceleracionDerecha > 0) {
            velocidadConAceleracionDerecha -= aceleracion * delta;
            if(velocidadConAceleracionDerecha < 0){
                velocidadConAceleracionDerecha = 0;
            }
        }
        double desplazamientoX = velocidadConAceleracionDerecha * delta;

        Entidad entidadColisionada = hayColisionHorizontal(listaDeEntidades, desplazamientoX);
        trabajarColisionHorizontal(entidadColisionada, Direccion.DERECHA, desplazamientoX);
        actualizarHitbox();
    }

    private void acelerarIzquierda (ArrayList < Entidad > listaDeEntidades, float delta) {
            if (velocidadConAceleracionIzquierda < velocidadMaxima) {
                velocidadConAceleracionIzquierda += aceleracion * delta;
                if (velocidadConAceleracionIzquierda > velocidadMaxima){
                    velocidadConAceleracionIzquierda = velocidadMaxima;
                }
            }
            double desplazamientoX = velocidadConAceleracionIzquierda * delta;

            Entidad entidadColisionada = hayColisionHorizontal(listaDeEntidades, -desplazamientoX);
            trabajarColisionHorizontal(entidadColisionada, Direccion.IZQUIERDA, desplazamientoX);
            actualizarHitbox();
    }

    private void desAcelerarIzquierda (ArrayList < Entidad > listaDeEntidades, float delta) {
            if (velocidadConAceleracionIzquierda > 0) {
                velocidadConAceleracionIzquierda -= aceleracion * delta;
                if (velocidadConAceleracionIzquierda < 0){
                    velocidadConAceleracionIzquierda = 0;
                }
            }
            double desplazamientoX = velocidadConAceleracionIzquierda * delta;

            Entidad entidadColisionada = hayColisionHorizontal(listaDeEntidades, -desplazamientoX);
            trabajarColisionHorizontal(entidadColisionada, Direccion.IZQUIERDA, desplazamientoX);
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


    private void trabajarColisionHorizontal(Entidad entidadColisionada, Direccion direccionMovimiento, double desplazamientoX) {

        switch (direccionMovimiento) {
            case DERECHA:

                if (entidadColisionada == null){
                    posicionX += desplazamientoX;
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
                    posicionX -= desplazamientoX;
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

    public boolean colisionaCon(Entidad entidad) {
        return getHitbox().overlaps(entidad.getHitbox());
    }

}
