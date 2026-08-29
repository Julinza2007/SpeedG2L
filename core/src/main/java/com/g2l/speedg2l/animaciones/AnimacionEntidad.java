package com.g2l.speedg2l.animaciones;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.g2l.speedg2l.Cerrable;
import com.g2l.speedg2l.utilidades.Render;

public class AnimacionEntidad extends Animacion implements Cerrable {

    private Texture spritesheet;
    private Animation<TextureRegion> animacion;
    private float estadoTiempo;
    private int filas;
    private int columnas;

    public AnimacionEntidad(String ruta, float velocidadFotograma, int columnas, int filas) {
        spritesheet = new Texture(ruta);
        this.columnas = columnas;
        this.filas = filas;
        TextureRegion[][] sprites = dividirSpritesheet();

        TextureRegion[] frames = crearFrames(sprites);

        animacion = new Animation<>(velocidadFotograma, frames);
    }

    public void animar(float delta) {
        estadoTiempo += delta;
    }

    public void dibujar(float x, float y, float ancho, float alto) {
        TextureRegion frame = getFrame();

        Render.batch.draw(
            frame,
            x,
            y,
            ancho,
            alto
        );
    }

    private TextureRegion getFrame() {
        return animacion.getKeyFrame(estadoTiempo);
    }

    private TextureRegion[][] dividirSpritesheet() {
        TextureRegion[][] sprites =  TextureRegion.split(
            spritesheet,
            spritesheet.getWidth() / columnas,
            spritesheet.getHeight() / filas
        );

        return sprites;
    }

    private TextureRegion[] crearFrames(TextureRegion[][] sprites){
        TextureRegion[] frames =  new TextureRegion[columnas * filas];
        int indiceFrame=0;

        for(int i=0; i < filas; i++){
            for(int j=0; j < columnas; j++){
                frames[indiceFrame] = sprites[i][j];
                indiceFrame++;
            }
        }

        return frames;
    }

    @Override
    public void iniciar(){
        animacion.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void cerrar() {
        spritesheet.dispose();
    }

}
