package com.mygdx.game.modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.juego.Assets;

/**
 * Created by dam204 on 20/02/2015.
 */
public class Hornet extends Char {
    public Vector2 speed;
    private final float SHOOT_SPEED=5;//se resta a la otra
    public boolean vivo;
    public boolean disparo;
    public int lifes=3;
    private float stateTime;
    public boolean impacto;
    public TextureRegion mostrarAnimacion;

    public Hornet (Vector2 posicion, Vector2 tamano,
                   Vector2 velocidade_max){
        super(posicion,tamano, velocidade_max);
        stateTime=0;
        speed=new Vector2(0,0);
        vivo=true;
        disparo=false;
        getRectangulo().setSize(tamano.x-20,tamano.y);
        getRectangulo().setPosition(posicion.x-10,posicion.y);
    }

    @Override
    public void update(float delta) {
        setPosicion(getPosicion().x+
                speed.x*delta,
                getPosicion().y+speed.y*delta);
        getRectangulo().setPosition(posicion);

        stateTime+= Gdx.graphics.getDeltaTime();
        mostrarAnimacion= Assets.hornetStand.getKeyFrame(stateTime,true);
    }


    public float getVelocidadX(){
        return speed.x;
    }
    public float getVelocidadY(){
        return speed.y;
    }

    public void setVelocidade(Vector2 velocidad) {

        this.speed = velocidad;
    }



    public void inicializarHornet() {
        setPosicion(new Vector2((250-(Mundo.TAMANO_HORNET.x/2)),70));
        setTamano(Mundo.TAMANO_HORNET.x, Mundo.TAMANO_HORNET.y);
        stateTime=0;
        speed=new Vector2(0,0);
        vivo=true;
        disparo=false;
        lifes--;
        getRectangulo().setSize(tamano.x-20,tamano.y);
        getRectangulo().setPosition(posicion.x - 10, posicion.y);
    }
    public void actualizarRectangulo() {

        getRectangulo().x = getPosicion().x + getTamano().x / 4;
        getRectangulo().y = getPosicion().y + getTamano().y / 4;

    }

    public void setVelocidadeX(float x) {
        speed.x = x;

    }

    public void setVelocidadeY(float y) {
        speed.y = y;
    }

    public boolean isDisparo() {
        return disparo;
    }

    public void setDisparo(boolean disparo) {
        this.disparo = disparo;
    }
}


