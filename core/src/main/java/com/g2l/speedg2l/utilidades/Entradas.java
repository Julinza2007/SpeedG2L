package com.g2l.speedg2l.utilidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Entradas implements InputProcessor {

    private boolean abajo=false, arriba=false;

    public boolean izquierda(){
        return Gdx.input.isKeyPressed(Keys.A);
    }

    public boolean derecha(){
        return Gdx.input.isKeyPressed(Keys.D);
    }

    public boolean abajo(){
        return Gdx.input.isKeyPressed(Keys.S);
    }
    public boolean arriba(){
        return Gdx.input.isKeyPressed(Keys.W);
    }

    public boolean barraEspaciadora(){
        return Gdx.input.isKeyJustPressed(Keys.SPACE);
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Keys.UP){
            arriba = true;
            return true;
        }else if(keycode == Keys.DOWN){
            abajo = true;
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.UP){
            arriba = false;
            return true;
        }else if(keycode == Keys.DOWN){
            abajo = false;
            return true;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean isArriba() {
        return arriba;
    }

    public boolean isAbajo() {
        return abajo;
    }
}
