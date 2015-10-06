package com.mygdx.game.juego;

import com.badlogic.gdx.Game;
import com.mygdx.game.pantalla.Level1;

/**
 * Created by dam204 on 20/02/2015.
 */
public class Juego extends Game{
   // private PantallaPresentacion pantallaPresentacion;
    private Level1 pantalla;
    public void create(){
        Assets.cargarTexturas();
        pantalla=new Level1(this);
        setScreen(pantalla);
        //Audio.inicializarMusica();

    }

    public void dispose(){
        super.dispose();
        pantalla.dispose();
        Assets.liberarTexturas();
        //Audio.stopMusica();
    }

}
