package com.tuproyecto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Musica {

    private Music musica;

    public Musica(String ruta) {
        musica = Gdx.audio.newMusic(Gdx.files.internal(ruta));
    }

    public void reproducir() {
        musica.play();
    }

    public void pausar() {
        musica.pause();
    }

    public void detener() {
        musica.stop();
    }

    public void repetir(boolean repetir) {
        musica.setLooping(repetir);
    }

    public void volumen(float volumen) {
        musica.setVolume(volumen);
    }

    public boolean estaReproduciendo() {
        return musica.isPlaying();
    }

    public void dispose() {
        musica.dispose();
    }
}
