package com.mygdx.game.modelo;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by usuario on 21/02/2015.
 */
public class Enemigo extends Mobiles{
    public boolean disparo=false;
    public Enemigo(Vector2 posicion, Vector2 tamano, Vector2 velocidade_max, TIPOS_ELEMENTOS tipo) {
        super(posicion, tamano, velocidade_max, tipo);
    }

    public boolean isDisparo() {

        return disparo;

    }

    public void setDisparo(boolean disparo) {
        this.disparo = disparo;
    }
}
