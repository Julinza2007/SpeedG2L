package com.g2l.speedg2l.sonidos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.g2l.speedg2l.Cerrable;

public class EfectoSonido implements Cerrable {

    private Sound sonido;

    public EfectoSonido(String ruta) {
        sonido = Gdx.audio.newSound(Gdx.files.internal(ruta));
    }

    public void reproducir() {
        sonido.play();
    }

    public void setVolumen(float volumen) {
        sonido.setVolume(0, volumen);
    }

    @Override
    public void cerrar() {
        sonido.dispose();
    }
}
