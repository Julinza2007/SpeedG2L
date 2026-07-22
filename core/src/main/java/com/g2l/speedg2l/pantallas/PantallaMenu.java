package com.g2l.speedg2l.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.g2l.speedg2l.componentes.Boton;

public class PantallaMenu implements Screen {
    private Stage stage;
    private Boton[] botones;
    @Override
    public void show() {
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        botones = new Boton[]{new Boton("Hola", skin), new Boton("Adios", skin)};

        for(Boton btn: botones){
            stage.addActor(btn);
        }

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
    }
}
