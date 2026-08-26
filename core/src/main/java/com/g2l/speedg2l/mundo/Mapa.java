package com.g2l.speedg2l.mundo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Mapa implements Cerrable{
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderMapa;
    private Nivel nivelActual;

    public Mapa(String ruta){
        if(ruta.isBlank()){
            throw new IllegalArgumentException("Error, ruta del mapa vacia");
        }else{
            TmxMapLoader cargador = new TmxMapLoader();
            mapa = cargador.load(ruta);
            renderMapa = new OrthogonalTiledMapRenderer(mapa);
        }
    }

    public void dibujar(OrthographicCamera camara){
        renderMapa.setView(camara);
        renderMapa.render();
    }

    @Override
    public void cerrar() {
            mapa.dispose();
            renderMapa.dispose();
    }
}
