package com.mygdx.game.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.controlador.Controller;
import com.mygdx.game.controlador.TactilPad;
import com.mygdx.game.juego.Juego;
import com.mygdx.game.juego.Renderer;
import com.mygdx.game.modelo.Controles;
import com.mygdx.game.modelo.Mundo;

/**
 * Created by dam204 on 20/02/2015.
 */
public class Level1 implements Screen, InputProcessor {
    private boolean pause;
    private boolean finxogo;
    private boolean sair;
    private TactilPad pad;

    private Vector3 temp;
    private Circle dedo;

    private Mundo mundo;
    private Controller controlador;
    private Juego juego;
    private Renderer renderer;

    public Level1(Juego juego) {
        this.juego = juego;
        mundo = new Mundo();
        controlador = new Controller(mundo);
        renderer = new Renderer(mundo);

        temp = new Vector3();
        dedo = new Circle();

        //Audio.playMusic;
    }

    @Override
    /*Dislle cal é a clase que implementa o interface e
    polo tanto detecta os listeners. Ponse aqui e non no constructor
    para que se active só cando se mostra esta pantalla, se temos outra
    o collerá a outra*/
    public void show() {
        Gdx.input.setInputProcessor(this);
        // if(pause){
        // Audio.pararClaxon();
        //Audio.stopMusica();
        //   pause=true;
        //}

    }

    /*DELTA ES UN PARÁMETRO DE REFRESCO DE PANTALLA*/
    @Override
    public void render(float delta) {
        controlador.update(delta);
        renderer.render(delta);
       /* if (mundo.getAlien().getNumVidas().size<=15){
            finxogo=true;
        }
        if(pause){
            juego.setScreen(new Pause(juego, this));
            return;
        }
        if(finxogo){

        }*/
    }

    @Override
    public void resize(int width, int height) {
        //renderer.resize(width,height);
        renderer.resize();

    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(this);
        //pause=false;
        //Audio.playMusic();
        //Audio.iniciarClaxon();
    }

    @Override
    /*Para que cando quede oculta no segundo plano ao sacar outra pantalla
    * temos que facer que os controles non afecten a esta*/
    public void hide() {
        Gdx.input.setInputProcessor(null);
        if (finxogo || sair) dispose();
        pause = false;
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        //liberar as teclas para evitar que cando arrastremos o dedo ou outro control sen soltar se
        //manteña o efecto
        /*
        controlador.liberarTecla(Controller.Keys.ABA);
        controlador.liberarTecla(Controller.Keys.ARR);
        controlador.liberarTecla(Controller.Keys.DER);
        controlador.liberarTecla(Controller.Keys.IZQ);*/
        ////
        switch (keycode) {
            case Input.Keys.UP:
                controlador.pulsarTecla(Controller.Keys.ARR);
                break;
            case Input.Keys.DOWN:
                controlador.pulsarTecla(Controller.Keys.ABA);
                break;
            case Input.Keys.RIGHT:
                controlador.pulsarTecla(Controller.Keys.DER);
                break;
            case Input.Keys.LEFT:
                controlador.pulsarTecla(Controller.Keys.IZQ);
                break;
            case Input.Keys.SPACE:
                controlador.pulsarTecla(Controller.Keys.SHOOT);
                break;
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                controlador.liberarTecla(Controller.Keys.ARR);
                break;
            case Input.Keys.DOWN:
                controlador.liberarTecla(Controller.Keys.ABA);
                break;
            case Input.Keys.RIGHT:
                controlador.liberarTecla(Controller.Keys.DER);
                break;
            case Input.Keys.LEFT:
                controlador.liberarTecla(Controller.Keys.IZQ);
                break;
            case Input.Keys.SPACE:
                controlador.liberarTecla(Controller.Keys.SHOOT);
                break;
        }
        /*
        controlador.liberarTecla(Controller.Keys.ABA);
        controlador.liberarTecla(Controller.Keys.ARR);
        controlador.liberarTecla(Controller.Keys.DER);
        controlador.liberarTecla(Controller.Keys.IZQ);*/
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        OrthographicCamera camara2d = renderer.getCamara2D();
        temp.set(screenX, screenY, 0);
        camara2d.unproject(temp); //para hacer una proyección de la cámara o algo asin
        dedo.set(temp.x, temp.y, 2f);//eje vertical, eje horizontal y radio;
       /* controlador.pad=new TactilPad(temp.x,temp.y);*/

        controlador.liberarTecla(Controller.Keys.ABA);
        controlador.liberarTecla(Controller.Keys.ARR);
        controlador.liberarTecla(Controller.Keys.DER);
        controlador.liberarTecla(Controller.Keys.IZQ);
        controlador.liberarTecla(Controller.Keys.SHOOT);
        if (Intersector.overlaps(dedo, Controles.getRectanguloFrechaEsquerda())) {
            controlador.pulsarTecla(Controller.Keys.IZQ);
            controlador.tap = true;
        } else {
            if (Intersector.overlaps(dedo, Controles.getRectanguloFrechaDereita())) {
                controlador.pulsarTecla(Controller.Keys.DER);
                controlador.tap = true;
            } else {
                if (Intersector.overlaps(dedo, Controles.getRectanguloFrechaAbaixo())) {
                    controlador.pulsarTecla(Controller.Keys.ABA);
                    controlador.tap = true;

                } else {
                    if (Intersector.overlaps(dedo, Controles.getRectanguloFrechaArriba())) {
                        controlador.pulsarTecla(Controller.Keys.ARR);
                        controlador.tap = true;
                    } else {
                        if (Intersector.overlaps(dedo, Controles.CONTROL_PAUSE)) {
                            controlador.tap = false;
                            pause = true;
                        } else {
                            if (Intersector.overlaps(dedo, Controles.getRectanguloFrechaDiagonalInfDereita())) {
                                controlador.pulsarTecla(Controller.Keys.ABA);
                                controlador.pulsarTecla(Controller.Keys.DER);
                                controlador.tap = true;
                            } else {
                                if (Intersector.overlaps(dedo, Controles.getRectanguloFrechaDiagonalSupEsquerda())) {
                                    controlador.pulsarTecla(Controller.Keys.ARR);
                                    controlador.pulsarTecla(Controller.Keys.IZQ);
                                    controlador.tap = true;
                                } else {
                                    if (Intersector.overlaps(dedo, Controles.getRectanguloFrechaDiagonalInfEsquerda())) {
                                        controlador.pulsarTecla(Controller.Keys.ABA);
                                        controlador.pulsarTecla(Controller.Keys.IZQ);
                                        controlador.tap = true;
                                    } else {
                                        if (Intersector.overlaps(dedo, Controles.getRectanguloFrechaDiagonalSupDereita())) {
                                            controlador.pulsarTecla(Controller.Keys.ARR);
                                            controlador.pulsarTecla(Controller.Keys.DER);
                                            controlador.tap = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        controlador.liberarTecla(Controller.Keys.ABA);
        controlador.liberarTecla(Controller.Keys.ARR);
        controlador.liberarTecla(Controller.Keys.DER);
        controlador.liberarTecla(Controller.Keys.IZQ);
        controlador.liberarTecla(Controller.Keys.SHOOT);
        controlador.tap = false;
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
    public boolean scrolled(int amount) {
        return false;
    }
}
