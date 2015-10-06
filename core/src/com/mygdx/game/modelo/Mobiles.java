package com.mygdx.game.modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.juego.Assets;

/**
 * Created by dam204 on 20/02/2015.
 */
public class Mobiles extends Char {

    private float stateTime;
    private Animation animacion;
    public Rectangle rectangle;
    public TextureRegion mostrarAnimacion;
    private boolean destruido;
    private boolean impactado;
    private boolean animStopped;
    private int i;

    public static enum TIPOS_ELEMENTOS {
        ENEMIGOA,ENEMIGOB,BG,MG,FG,AST,BOSS,LIFEBAR,TURRET,DRONE,PP,HUD,DISPARO,LASER,LOCALIZ,IMPACTO
    }

    private TIPOS_ELEMENTOS tipo;
    public Mobiles(){

    }
    public Mobiles(Vector2 posicion, Vector2 tamano,
                 Vector2 velocidade_max,
                 TIPOS_ELEMENTOS tipo){
        super(posicion,tamano,velocidade_max);
        this.animacion=Assets.laser;
        this.destruido=false;
        this.animStopped=true;
        this.impactado=false;

        if(tipo==TIPOS_ELEMENTOS.BOSS){
            getRectangulo().setSize(tamano.x-80,tamano.y);
            getRectangulo().setPosition(posicion.x+20,posicion.y);
        }
        if(tipo==TIPOS_ELEMENTOS.DISPARO){
            getRectangulo().setSize(tamano.x-10,tamano.y-5);
            getRectangulo().setPosition(posicion.x+5,posicion.y);
        }
        if(tipo==TIPOS_ELEMENTOS.ENEMIGOA){
            getRectangulo().setSize(tamano.x-15,tamano.y);
            getRectangulo().setPosition(posicion.x+14,posicion.y);
        }
        if(tipo==TIPOS_ELEMENTOS.ENEMIGOB){
            getRectangulo().setSize(tamano.x-20,tamano.y-20);
            getRectangulo().setPosition(posicion.x+10,posicion.y+10);
        }
        if(tipo==TIPOS_ELEMENTOS.TURRET){
            getRectangulo().setSize(tamano.x,tamano.y);
            getRectangulo().setPosition(posicion.x,posicion.y);
        }
        if(tipo==TIPOS_ELEMENTOS.DRONE){
            getRectangulo().setSize(tamano.x,tamano.y);
            getRectangulo().setPosition(posicion.x,posicion.y);
        }
        if(tipo==TIPOS_ELEMENTOS.LASER){
            getRectangulo().setSize(tamano.x-20,tamano.y-80);
            getRectangulo().setPosition(posicion.x+10,posicion.y+80);
        }

        if(tipo==TIPOS_ELEMENTOS.BG||tipo==TIPOS_ELEMENTOS.FG
                ||tipo==TIPOS_ELEMENTOS.MG
                ||tipo==TIPOS_ELEMENTOS.AST){
        setVelocidadeY(velocidade_max.y);
        }
        if (tipo==TIPOS_ELEMENTOS.TURRET){
            setVelocidadeX(velocidade_max.x);
        }
        if(tipo==TIPOS_ELEMENTOS.ENEMIGOA){
            setVelocidadeX(velocidade_max.x);
            setVelocidadeY(velocidade_max.y);
        }
        setVelocidade(velocidade_max);
        this.tipo=tipo;

    }

    public TIPOS_ELEMENTOS getTipo(){
        return tipo;
    }

    public boolean isDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    public boolean isImpactado() {
        return impactado;
    }

    public void setImpactado(boolean impactado) {
        this.impactado = impactado;
    }

    public void update(float delta){

        posicion.add((velocidade.x*delta),(velocidade.y*delta));
        getRectangulo().setPosition(posicion);
        if(tipo==TIPOS_ELEMENTOS.LASER){
            stateTime+= Gdx.graphics.getDeltaTime();
            mostrarAnimacion= Assets.laser.getKeyFrame(stateTime,false);
            if(stateTime>animacion.getFrameDuration()*4){
                this.animStopped=true;
            }
        }
/*
        if(tipo==TIPOS_ELEMENTOS.LIFEBAR){

            i=(50000-Mundo.bossLife)/200;

            setTamano(new Vector2(200-i,getTamano().y));
        }*/
    }
}
