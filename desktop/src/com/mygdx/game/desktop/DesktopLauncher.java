package com.mygdx.game.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.juego.Juego;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title="Live to fly";
        Graphics.DisplayMode displaymode =LwjglApplicationConfiguration.getDesktopDisplayMode();
        //new LwjglApplication(new MeuXogoGame(),config);
        config.width=500;
        config.height=800;
        new LwjglApplication(new Juego(), config);
        config.resizable=true;
	}
}
