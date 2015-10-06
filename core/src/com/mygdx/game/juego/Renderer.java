package com.mygdx.game.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.StringBuilder;
import com.mygdx.game.modelo.Controles;
import com.mygdx.game.modelo.Hornet;
import com.mygdx.game.modelo.Impacto;
import com.mygdx.game.modelo.Mobiles;
import com.mygdx.game.modelo.Mundo;

/**
 * Created by dam204 on 20/02/2015.
 */
public class Renderer implements InputProcessor {

    private OrthographicCamera camara2D;
    private Texture grafico;
    private Animation animacion;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Vector3 temporal; //esto es para el touch
    private BitmapFont bitmapFont;
    private StringBuilder stringBuilder;
    private Mundo mundo;
    private Hornet player;
    private Impacto impactoEnemigo;
    private Impacto impactoAmigo;
    private Mobiles bg;
    private Mobiles middleground;
    private Mobiles foreground;
    private Mobiles hud;
    private Mobiles ppHornet;
    private Mobiles boss;
    private Mobiles dronA;
    private Mobiles dronB;
    private Mobiles torreta;
    private Mobiles ppEnemy;
    private Mobiles disparoDron;
    private Mobiles laser;
    private Mobiles disparoEnemy;
    private Mobiles lifebar;
    private Mobiles nombre;
    private Mobiles localiz;


    public Renderer(Mundo mundo) {
        this.mundo = mundo;
        this.lifebar=mundo.getLifebar();
        this.nombre=mundo.getNombre();
        this.torreta=mundo.getTurret();
        this.bg = mundo.getBackground();
        this.middleground = mundo.getMiddleground();
        this.foreground = mundo.getForeground();
        this.player = mundo.getPlayer();
        this.ppHornet = mundo.getPp2();
        this.ppEnemy = mundo.getPpE();
        this.hud = mundo.getHud();
        this.dronA=mundo.getDroneA();
        this.dronB=mundo.getDroneB();
        this.boss=mundo.getBoss();
        this.camara2D = new OrthographicCamera();
        this.camara2D.viewportHeight=Mundo.TAMANO_MUNDO_ALTO;
        this.camara2D.viewportWidth=Mundo.TAMANO_MUNDO_ANCHO;
        this.camara2D.position.set(camara2D.viewportWidth*0.5f, camara2D.viewportHeight*0.5f,0);
        this.camara2D.update();
        this.spriteBatch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        Assets.cargarTexturas();
        //Audio.inicializarMusica();
    }
/*

    private void drawScore(){
        spriteBatch.draw(bitmapFont.draw(spriteBatch, String.valueOf(mundo.getScore()),168f,84f));
    }*/
    private void drawPlayer() {
        spriteBatch.draw(player.mostrarAnimacion, player.getPosicion().x, player.getPosicion().y,
                player.getTamano().x, player.getTamano().y);
    }

    private void drawLaser(){
        for (Mobiles laser:mundo.getLaser()){
            spriteBatch.draw(laser.mostrarAnimacion, laser.getPosicion().x,laser.getPosicion().y,
                    laser.getTamano().x,laser.getTamano().y);
        }
    }
    private void drawImpactoEnemigo(){
        for (Impacto impact:mundo.getImpactosEnemigo()){
        spriteBatch.draw(impact.mostrarAnimacion,impact.getPosicion().x,
                impact.getPosicion().y,impact.getTamano().x,impact.getTamano().y);
        }
    }

    private void drawImpactoAmigo(){
        for (Impacto impact:mundo.getImpactosAmigo()){
            spriteBatch.draw(impact.mostrarAnimacion,impact.getPosicion().x,
                    impact.getPosicion().y,impact.getTamano().x,impact.getTamano().y);
        }
    }
    private void drawBackground() {
        for (Mobiles bg : mundo.getBackgrounds()) {
            spriteBatch.draw(Assets.textureBackground, bg.getPosicion().x,
                    bg.getPosicion().y, bg.getTamano().x, bg.getTamano().y);
        }
    }

    private void drawMiddleground() {
        for (Mobiles mg : mundo.getMiddlegrounds()) {
            spriteBatch.draw(Assets.textureMiddleground, mg.getPosicion().x,
                    mg.getPosicion().y, mg.getTamano().x, mg.getTamano().y);
        }
    }

    private void drawForeround() {
        for (Mobiles fg : mundo.getForegrounds()) {
            spriteBatch.draw(Assets.textureForeground, fg.getPosicion().x,
                    fg.getPosicion().y, fg.getTamano().x, fg.getTamano().y);
        }
    }

    private void drawEnemigoAIzq() {
        for (Mobiles eneA : mundo.getEnemigoAIzq()) {
            spriteBatch.draw(Assets.textureEnemigoA, eneA.getPosicion().x,
                    eneA.getPosicion().y, eneA.getTamano().x, eneA.getTamano().y);
            drawPropEnemy(eneA);
        }
    }

    private void drawEnemigoADcha() {
        for (Mobiles eneA : mundo.getEnemigoADcha()) {
            spriteBatch.draw(Assets.textureEnemigoA, eneA.getPosicion().x,
                    eneA.getPosicion().y, eneA.getTamano().x, eneA.getTamano().y);
            drawPropEnemy(eneA);
        }
    }

    private void drawEnemigoBDcha() {
        for (Mobiles eneB : mundo.getEnemigoBDcha()) {
            spriteBatch.draw(Assets.textureEnemigoB, eneB.getPosicion().x,
                    eneB.getPosicion().y, eneB.getTamano().x, eneB.getTamano().y);
            drawPropEnemy(eneB);
        }
    }

    private void drawEnemigoBIzq() {
        for (Mobiles eneB : mundo.getEnemigoBIzq()) {
            spriteBatch.draw(Assets.textureEnemigoB, eneB.getPosicion().x,
                    eneB.getPosicion().y, eneB.getTamano().x, eneB.getTamano().y);
            drawPropEnemy(eneB);
        }
    }
    private void drawBoss(){
        spriteBatch.draw(Assets.textureBoss,boss.getPosicion().x,
                boss.getPosicion().y,boss.getTamano().x,boss.getTamano().y);
    }
    private void drawTurret(){
        spriteBatch.draw(Assets.textureTurret,torreta.getPosicion().x,
                torreta.getPosicion().y,torreta.getTamano().x,torreta.getTamano().y);
    }

    private void drawLifebar(){
        spriteBatch.draw(Assets.textureLifebar,lifebar.getPosicion().x,
                lifebar.getPosicion().y,lifebar.getTamano().x,lifebar.getTamano().y);
        spriteBatch.draw(Assets.textureBossName,lifebar.getPosicion().x+20,lifebar.getPosicion().y+Mundo.TAMANO_LIFEBAR.y+10);

    }
    private void drawPropEnemy(Mobiles ene) {
        spriteBatch.draw(Assets.texturePropEnemy, ene.getPosicion().x + ((ene.getTamano().x / 2) - (ppEnemy.getTamano().x / 2))/*+ppHornet.getPosicion().x*/,
                ene.getPosicion().y + ene.getTamano().y/*+ppHornet.getPosicion().y*/,
                ppEnemy.getTamano().x, ppEnemy.getTamano().y);
    }

    private void drawPropNormal() {
        spriteBatch.draw(Assets.pp2, player.getPosicion().x + Mundo.TAMANO_HORNET.x/2-Mundo.TAMANO_PP2.x/2/*+ppHornet.getPosicion().x*/,
                player.getPosicion().y/*+ppHornet.getPosicion().y*/,
                ppHornet.getTamano().x, ppHornet.getTamano().y);
    }

    private void drawDroneA(){
        spriteBatch.draw(Assets.textureDrone,dronA.getPosicion().x,
                dronA.getPosicion().y,dronA.getTamano().x,
                dronA.getTamano().y);
    }

    private void drawDroneB(){
        spriteBatch.draw(Assets.textureDrone,dronB.getPosicion().x,
                dronB.getPosicion().y,dronB.getTamano().x,
                dronB.getTamano().y);
    }




    private void drawHUD() {
        spriteBatch.draw(Assets.textureHud, hud.getPosicion().x,
                hud.getPosicion().y, hud.getTamano().x, hud.getTamano().y);
    }

    private void drawDisparoHornet() {

        for (Mobiles disp : mundo.getDisparosHornet()) {
            spriteBatch.draw(Assets.textureDisparoHornet, disp.getPosicion().x,
                    disp.getPosicion().y, disp.getTamano().x, disp.getTamano().y);
        }


    }

    private void drawDisparoEnemigo(){
        for (Mobiles disp : mundo.getDisparosEnemigos()) {
            spriteBatch.draw(Assets.textureDisparoEnemigo, disp.getPosicion().x,
                    disp.getPosicion().y, disp.getTamano().x, disp.getTamano().y);
        }
    }

    private void drawDisparoDrone1(){
        for (Mobiles disp:mundo.getDisparoDrone1()){
            spriteBatch.draw(Assets.textureDisparoDrone,disp.getPosicion().x,
                    disp.getPosicion().y,disp.getTamano().x,disp.getTamano().y);
        }
    }

    private void drawDisparoDrone2(){
        for (Mobiles disp:mundo.getDisparoDrone2()){
            spriteBatch.draw(Assets.textureDisparoDrone,disp.getPosicion().x,
                    disp.getPosicion().y,disp.getTamano().x,disp.getTamano().y);
        }
    }

    private void drawLocaliz(){
        for (Mobiles localiz:mundo.getLocaliz()){
        spriteBatch.draw(Assets.ls3,localiz.getPosicion().x,localiz.getPosicion().y,
                localiz.getTamano().x,localiz.getPosicion().y);
        }
    }

    public void drawControles(){
        //fondo negro
        /*
        spriteBatch.draw(Assets.texturePuntoNegro,
                Controles.FONDO_NEGRO.x,
                Controles.FONDO_NEGRO.y,
                Controles.FONDO_NEGRO.width,
                Controles.FONDO_NEGRO.height);*/

        //control direccion
        spriteBatch.draw(Assets.textureControlesFlechas,
                Controles.CONTROL.x,
                Controles.CONTROL.y,
                Controles.CONTROL.width,
                Controles.CONTROL.height);
        //
        spriteBatch.draw(Assets.texturePausa,
                Controles.CONTROL_PAUSE.x,
                Controles.CONTROL_PAUSE.y,
                Controles.CONTROL_PAUSE.width,
                Controles.CONTROL_PAUSE.height);
/*
        spriteBatch.draw(Assets.textureSair,
                Controles.CONTROL_SAIR.x,
                Controles.CONTROL_SAIR.y,
                Controles.CONTROL_SAIR.width,
                Controles.CONTROL_SAIR.height);*/

    }

    private void debugMode(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLUE);
        for (Mobiles coche:mundo.getEnemigoAIzq()){
            shapeRenderer.rect(coche.getPosicion().x,
                    coche.getPosicion().y,coche.getTamano().x,
                    coche.getTamano().y);
        }
        for (Mobiles coche:mundo.getEnemigoBIzq()){
            shapeRenderer.rect(coche.getPosicion().x,
                    coche.getPosicion().y,coche.getTamano().x,
                    coche.getTamano().y);
        }
        for (Mobiles coche:mundo.getEnemigoADcha()){
            shapeRenderer.rect(coche.getPosicion().x,
                    coche.getPosicion().y,coche.getTamano().x,
                    coche.getTamano().y);
        }
        for (Mobiles coche:mundo.getEnemigoBDcha()){
            shapeRenderer.rect(coche.getPosicion().x,
                    coche.getPosicion().y,coche.getTamano().x,
                    coche.getTamano().y);
        }
        for (Mobiles coche:mundo.getDisparoDrone1()){
            shapeRenderer.rect(coche.getPosicion().x,
                    coche.getPosicion().y,coche.getTamano().x,
                    coche.getTamano().y);
        }
        for (Mobiles coche:mundo.getDisparoDrone2()){
            shapeRenderer.rect(coche.getPosicion().x,
                    coche.getPosicion().y,coche.getTamano().x,
                    coche.getTamano().y);
        }
        for (Mobiles coche:mundo.getDisparosEnemigos()){
            shapeRenderer.rect(coche.getPosicion().x,
                    coche.getPosicion().y,coche.getTamano().x,
                    coche.getTamano().y);
        }
        for (Mobiles coche:mundo.getDisparosHornet()){
            shapeRenderer.rect(coche.getPosicion().x,
                    coche.getPosicion().y,coche.getTamano().x,
                    coche.getTamano().y);
        }
        for (Mobiles coche:mundo.getLaser()){
            shapeRenderer.rect(coche.getPosicion().x,
                    coche.getPosicion().y,coche.getTamano().x,
                    coche.getTamano().y);
        }

            shapeRenderer.rect(mundo.getBoss().getPosicion().x,
                    mundo.getBoss().getPosicion().y,mundo.getBoss().getTamano().x,
                    mundo.getBoss().getTamano().y);

        shapeRenderer.rect(mundo.getPlayer().getPosicion().x,
                mundo.getPlayer().getPosicion().y,mundo.getPlayer().getTamano().x,
                mundo.getPlayer().getTamano().y);

       /*PAANOIAS GABINATOR
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLUE)
        Alien alien=meuMundo.getAlien();
        shapeRenderer.rect(alien.getPosicion().x,alien.getPosicion().y,alien.get);

        shapeRenderer.setColor(Color.RED);
        Nave nave=meu
        */

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
      //  shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);
    shapeRenderer.rect(100,100,15,15);


        shapeRenderer.end();
    }


    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camara2D.combined);
        spriteBatch.begin();
        drawBackground();
        drawMiddleground();
        drawPropNormal();
        drawLaser();
        drawEnemigoAIzq();
        drawEnemigoADcha();
        drawEnemigoBIzq();
        drawEnemigoBDcha();
        drawBoss();
        drawTurret();
        drawDisparoHornet();
        drawDisparoEnemigo();
        drawDisparoDrone1();
        drawDisparoDrone2();
        drawLocaliz();
        drawPlayer();
        drawLaser();
        if(!(mundo.getImpactosAmigo().get(0)==null)) {
        drawImpactoAmigo();
         }
        if(!(mundo.getImpactosEnemigo().get(0)==null)) {
            drawImpactoEnemigo();
        }
        drawDroneA();
        drawDroneB();
        drawForeround();
        drawLifebar();
        drawHUD();
        drawControles();
       // debugMode();

        
        spriteBatch.end();
    }

    public void resize() {
        camara2D.setToOrtho(false, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        camara2D.update();

        spriteBatch.setProjectionMatrix(camara2D.combined);
    }

    public void dispose() {
        Gdx.input.setInputProcessor(null);
        Assets.liberarTexturas();
        grafico.dispose();
        Assets.liberarTexturas();
        bitmapFont.dispose();
        Assets.liberarTexturas();
        spriteBatch.dispose();

    }

    public OrthographicCamera getCamara2D() {
        return camara2D;
    }

    public void setCamara2D(OrthographicCamera camara2D) {
        this.camara2D = camara2D;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
