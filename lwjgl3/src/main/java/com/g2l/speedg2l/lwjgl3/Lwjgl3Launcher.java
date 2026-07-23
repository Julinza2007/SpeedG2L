package com.g2l.speedg2l.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.g2l.speedg2l.SpeedG2L;
import com.g2l.speedg2l.utilidades.Config;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    public static final String TITULO = "SpeedG2L";
    public static int V_WIDTH = Config.ancho;
    public static int V_HEIGHT = Config.alto;
    public static final int SCALE = 1;

    private static void createApplication() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle(TITULO);
        config.setWindowedMode((V_WIDTH * SCALE),(V_HEIGHT * SCALE));

       new Lwjgl3Application(new SpeedG2L(), config);
    }
}
