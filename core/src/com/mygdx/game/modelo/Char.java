package com.mygdx.game.modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dam204 on 20/02/2015.
 */
public abstract class Char {
    public Vector2 velocidade_max;
    protected Vector2 velocidade=new Vector2(0,0);
    protected Vector2 posicion;
    protected  Vector2 tamano;
    protected Rectangle rectangulo;

    public Char(){
        rectangulo=new Rectangle();
    }

    public Char(Vector2 posicion, Vector2 tamaño, Vector2 velocidade_max) {
        this.posicion = posicion;
        this.tamano = tamaño;
        this.velocidade_max = velocidade_max;
        rectangulo = new Rectangle(posicion.x, posicion.y, tamaño.x, tamaño.y);
    }

    public void setTamanoRectangulo(float x, float y){
        rectangulo.width=x;
        rectangulo.height=y;
    }

    public void actualizarRectangulo(){
        rectangulo.x=posicion.x;
        rectangulo.y=posicion.y;

    }

    public Rectangle getRectangulo(){
        return new Rectangle(getPosicion().x,
                getPosicion().y,
                getTamano().x,
                getTamano().y);
    }

    public Vector2 getPosicion(){
        return posicion;
    }

    public void setPosicion(Vector2 posicion){
        this.posicion=posicion;
        actualizarRectangulo();
    }

    public void setPosicion(float x,float y){
        posicion.x=x;
        posicion.y=y;
    }

    public void setRectangulo(Rectangle rectangulo) {
        this.rectangulo = rectangulo;
    }

    public Vector2 getTamano(){
        return tamano;
    }

    public void setTamano(Vector2 tamano){
        this.tamano=tamano;
        setTamanoRectangulo(tamano.x,tamano.y);

    }
    public void setTamano(float x, float y) {
        this.tamano.x = x;
        this.tamano.y = y;
        setTamanoRectangulo(x-5, y-5);
    }

    public Vector2 getVelocidade_max() {
        return velocidade_max;
    }

    public void setVelocidade_max(Vector2 velocidade_max) {
        this.velocidade_max = velocidade_max;
    }


    public void setVelocidade_maxY(float f) {
        this.velocidade_max.y = f;
    }


    public void setVelocidade_maxX(float f) {
        this.velocidade_max.x = f;
    }

    public void setVelocidade(Vector2 v){
        this.velocidade=v;
    }

    public void setVelocidadeX(float v){
        this.velocidade.x=v;
    }

    public void setVelocidadeY(float v){
        this.velocidade.y=v;
    }
    public Vector2 getVelocidade(){
        return this.velocidade;
    }
    /**
     * Actualiza la posición en función de la velocidad.
     * Se tiene que construir fuera cada vez que lo queramos utilizar
     * @param delta velocidad de refresco
     */
    public abstract void update(float delta);



}
