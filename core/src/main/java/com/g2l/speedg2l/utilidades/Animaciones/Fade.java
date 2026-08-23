package com.g2l.speedg2l.utilidades.Animaciones;

import com.badlogic.gdx.Screen;
import com.g2l.speedg2l.componentes.Imagen;
import com.g2l.speedg2l.utilidades.Render;

public class Fade extends Animacion{

    private Screen pantallaNueva;
    private boolean fadeInTerminado=false;
    private float transparencia = 0;
    private Imagen imagen;

    public Fade(float duracion, Imagen imagen, Screen pantallaNueva) {
        super(duracion);

        if(imagen == null){
            throw new IllegalArgumentException("Error, imagen vacia para la animacion.");
        }else{
            this.imagen = imagen;
        }

        if (pantallaNueva == null) {
            throw new IllegalArgumentException("Error, pantalla nueva está nula en la animación.");
        }else{
            this.pantallaNueva = pantallaNueva;
        }
    }

    public void mostrarFade(){
        if(fadeInTerminado) {

            if(tiempoTranscurrido < duracion){
                tiempoTranscurrido += 0.03f;
            }else{
                transparencia-=0.003f;
                if(transparencia<0) {
                    transparencia = 0.0f;
                    terminado=true;
                }
            }

            if(terminado){
                Render.app.setScreen(pantallaNueva);
            }

        }else{
            transparencia+=0.003f;
            if(transparencia>1){
                transparencia=1.0f;
                fadeInTerminado=true;
            }
        }

        imagen.setTransparencia(transparencia);

    }
}
