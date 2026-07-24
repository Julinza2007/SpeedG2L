package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.componentes.Texto;
import com.g2l.speedg2l.utilidades.Entradas;
import com.g2l.speedg2l.utilidades.Recursos;
import com.g2l.speedg2l.utilidades.Render;

public class PantallaCarga implements Screen {

    private Imagen imagenFondo;
    private SpriteBatch b;
    private float a = 0;
    private boolean fadeInTerminado=false;
    private boolean terminado=false;
    private float contTiempo;
    private Texto coords;
    private Entradas entradas;
    @Override
    public void show() {
        b = Render.batch;
        imagenFondo = new Imagen(Recursos.FONDO_CARGA);
        imagenFondo.setSize(405, 512);
        imagenFondo.centrar();
        imagenFondo.setTransparencia(a);
        coords = new Texto(Recursos.FUENTE_MENU, 20, Color.WHITE);
        entradas = new Entradas();
        Gdx.input.setInputProcessor(entradas);
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();
        coords.setTexto("Coord x: " + entradas.getMouseX() + " Coord y: " + entradas.getMouseY());
        coords.centrar();
        System.out.println("Coord x: " + entradas.getMouseX() + " Coord y: " + entradas.getMouseY());
        mostrarFade();
        b.begin();
        coords.dibujar();
        imagenFondo.dibujar();
        b.end();
    }

    private void mostrarFade(){
        if(fadeInTerminado) {
            final float TIEMPO_ESPERA = 5.0f;
            if(contTiempo < TIEMPO_ESPERA){
                contTiempo+=0.03f;
            }else{
                a-=0.003f;
                if(a<0) {
                    a = 0.0f;
                    terminado=true;
                }
            }

            if(terminado){
                Render.app.setScreen(new PantallaMenu());
            }

        }else{
            a+=0.003f;
            if(a>1){
                a=1.0f;
                fadeInTerminado=true;
            }
        }


        imagenFondo.setTransparencia(a);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        b.dispose();
    }
}
