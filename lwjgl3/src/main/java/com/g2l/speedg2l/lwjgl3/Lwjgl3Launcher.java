package com.g2l.speedg2l.lwjgl3;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.g2l.speedg2l.SpeedG2L;

public class Lwjgl3Launcher {

    public static final String TITULO = "SpeedG2L";

    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return;
        createApplication();
    }

    private static void createApplication() {
        Lwjgl3ApplicationConfiguration config =
            new Lwjgl3ApplicationConfiguration();

        DisplayMode modoMonitor =
            Lwjgl3ApplicationConfiguration.getDisplayMode();

        config.setTitle(TITULO);

        config.setFullscreenMode(modoMonitor);

        new Lwjgl3Application(new SpeedG2L(), config);
    }
}
