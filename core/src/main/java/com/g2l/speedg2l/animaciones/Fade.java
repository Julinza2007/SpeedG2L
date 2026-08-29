package com.g2l.speedg2l.animaciones;

import com.g2l.speedg2l.componentes.Imagen;

public class Fade extends Animacion{

    private boolean fadeInTerminado=false;
    private float transparencia = 0;
    private Imagen imagen;

    public Fade(float duracion, Imagen imagen) {
        super(duracion);

        if(imagen == null){
            throw new IllegalArgumentException("Error, imagen vacia para la animacion.");
        }else{
            this.imagen = imagen;
        }

    }

@Override
    public void iniciar(){
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
                return;
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
