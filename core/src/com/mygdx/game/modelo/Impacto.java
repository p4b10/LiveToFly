package com.mygdx.game.modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.juego.Assets;


/**
 * Created by usuario on 24/02/2015.
 */
public class Impacto extends Mobiles {

    private float stateTime;
    private Animation animacion;
    public TextureRegion mostrarAnimacion;
    private boolean animStopped;

    public Impacto(Vector2 posicion,Animation a) {
        super(posicion, new Vector2(Mundo.TAMANO_IMPACTO.x,Mundo.TAMANO_IMPACTO.y), new Vector2(0f,0f),TIPOS_ELEMENTOS.IMPACTO);
        this.animacion=a;
        this.animStopped=false;
    }

    public Animation getAnimacion() {
        return animacion;
    }

    public boolean isAnimStopped() {
        return animStopped;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        stateTime+= Gdx.graphics.getDeltaTime();
        mostrarAnimacion= animacion.getKeyFrame(stateTime,false);
        if(stateTime>animacion.getFrameDuration()*6){
            this.animStopped=true;
        }

    }
}
