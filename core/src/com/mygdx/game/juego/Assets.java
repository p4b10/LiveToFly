package com.mygdx.game.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by dam204 on 20/02/2015.
 */
public class Assets {
    public static Animation hornetStand;
    public static Texture hs;
    public static Texture hs1;
    public static Texture hs2;
    public static Texture hs3;

    public static Animation hornetDcha;
    public static Texture hd1;
    public static Texture hd2;


    public static Animation hornetIzqu;
    public static Texture hi1;
    public static Texture hi2;

    public static Animation asteroide;
    public static Texture as1;
    public static Texture as2;
    public static Texture as3;
    public static Texture as4;
    public static Texture as5;
    public static Texture as6;

    public static Animation finalBoss;
    public static Texture fb1;
    public static Texture fb2;

    public static Animation laser;
    public static Texture ls1;
    public static Texture ls2;
    public static Texture ls3;

    public static Animation impactoPlayer;
    public static Texture ip1;
    public static Texture ip2;
    public static Texture ip3;
    public static Texture ip4;

    public static Animation impactoEnemgo;
    public static Texture ie1;
    public static Texture ie2;
    public static Texture ie3;
    public static Texture ie4;

    public static Animation propulsion;
    public static Texture pp1;
    public static Texture pp2;
    public static Texture pp3;

    public static Animation chispas;
    public static Texture chd1;
    public static Texture chd2;
    public static Texture[] framesChispasDcha;
    public static Texture chi1;
    public static Texture chi2;


    public static Texture textureBackground;
    public static Texture texturePropEnemy;
    public static Texture textureMiddleground;
    public static Texture textureForeground;
    public static Texture textureLifebar;
    public static Texture textureBossName;
    public static Texture textureHud;
    public static Texture texturePuntoNegro;
    public static Texture textureControlesFlechas;
    public static Texture texturePausa;
    public static Texture textureSair;
    public static Texture textureEnemigoA;
    public static Texture textureEnemigoB;
    public static Texture textureDrone;
    public static Texture textureTurret;
    public static Texture textureDisparoHornet;
    public static Texture textureDisparoEnemigo;
    public static Texture textureDisparoDrone;
    public static Texture textureBoss;


    public static void cargarTexturas() {
        FileHandle imageFileHandle;


        imageFileHandle = Gdx.files
                .internal("grafico/fondos/Background.png");
        textureBackground = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/fondos/Foreground.png");
        textureForeground = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/jefe/cuerpo/1.png");
        textureBoss = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/fondos/middleground.png");
        textureMiddleground = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/propulsion/fuegoEnemido.png");
        texturePropEnemy = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/hud/lifebar.png");
        textureLifebar = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/hud/nombre.png");
        textureBossName = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/hud/controles.png");
        textureHud = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/CONTROIS/LIBGDX_itin1_controis.png");
        textureControlesFlechas = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/CONTROIS/LIBGDX_itin1_pausa.png");
        texturePausa = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/CONTROIS/LIBGDX_itin1_sair.png");
        textureSair = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/enemigos/enemigoA.png");
        textureEnemigoA = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/enemigos/enemigoB.png");
        textureEnemigoB = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/jefe/Drone.png");
        textureDrone = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/jefe/Torreta.png");
        textureTurret = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/disparos/DisparoDrone.png");
        textureDisparoDrone = new Texture(imageFileHandle);
        imageFileHandle = Gdx.files
                .internal("grafico/disparos/DisparoEnemigo.png");
        textureDisparoEnemigo = new Texture(imageFileHandle);
        imageFileHandle = Gdx.files
                .internal("grafico/disparos/DisparoHornet.png");
        textureDisparoHornet = new Texture(imageFileHandle);


        //animaciones izquierda y derecha

        imageFileHandle = Gdx.files
                .internal("grafico/hornet/izquierda/1.png");
        hi1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/hornet/izquierda/2.png");
        hi2 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/hornet/derecha/1.png");
        hd1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/hornet/derecha/2.png");
        hd2 = new Texture(imageFileHandle);

        //Animacion Asteroide


        imageFileHandle = Gdx.files
                .internal("grafico/asteroides/1.png");
        as1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/asteroides/2.png");
        as2 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/asteroides/3.png");
        as3 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/asteroides/4.png");
        as4 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/asteroides/5.png");
        as5 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/asteroides/6.png");
        as6 = new Texture(imageFileHandle);

        //Animación Boss

        imageFileHandle = Gdx.files
                .internal("grafico/jefe/cuerpo/1.png");
        fb1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/jefe/cuerpo/2.png");
        fb2 = new Texture(imageFileHandle);

        //Animacion Laser
        imageFileHandle = Gdx.files
                .internal("grafico/disparos/laser/1.png");
        ls1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/disparos/laser/2.png");
        ls2 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/disparos/laser/3.png");
        ls3 = new Texture(imageFileHandle);

        //Animación impactoPlayer
        imageFileHandle = Gdx.files
                .internal("grafico/impactos/hornet/1.png");
        ip1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/impactos/hornet/2.png");
        ip2 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/impactos/hornet/3.png");
        ip3 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/impactos/hornet/4.png");
        ip4 = new Texture(imageFileHandle);

        //Animación impactoEnemigo
        imageFileHandle = Gdx.files
                .internal("grafico/impactos/enemigo/1.png");
        ie1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/impactos/enemigo/2.png");
        ie2 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/impactos/enemigo/3.png");
        ie3 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/impactos/enemigo/4.png");
        ie4 = new Texture(imageFileHandle);

        //Animación chispicas
        imageFileHandle = Gdx.files
                .internal("grafico/propulsion/torreta/dcha1.png");
        chd1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/propulsion/torreta/dcha2.png");
        chd2 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/propulsion/torreta/izq1.png");
        chi1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/propulsion/torreta/izq2.png");
        chi2 = new Texture(imageFileHandle);


        //Animación de propulsion ????????

        imageFileHandle = Gdx.files
                .internal("grafico/propulsion/hornet/1.png");
        pp1 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/propulsion/hornet/2.png");
        pp2 = new Texture(imageFileHandle);

        imageFileHandle = Gdx.files
                .internal("grafico/propulsion/hornet/3.png");
        pp3 = new Texture(imageFileHandle);

        hornetStand = new Animation(0.2f, new TextureRegion[]{
                new Sprite(new Texture(Gdx.files
                        .internal("grafico/hornet/standby/hornet.png"))),
                new Sprite(new Texture(Gdx.files
                        .internal("grafico/hornet/standby/hornet1.png"))),
                new Sprite(new Texture(Gdx.files
                        .internal("grafico/hornet/standby/hornet2.png"))),
                new Sprite(new Texture(Gdx.files
                        .internal("grafico/hornet/standby/hornet3.png")))
        });

        finalBoss = new Animation(0.1f, new TextureRegion[]{
                new Sprite(new Texture(Gdx.files.internal("grafico/jefe/cuerpo/1.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/jefe/cuerpo/2.png")))});

        laser = new Animation(0.2f, new TextureRegion[]{
                new Sprite(new Texture(Gdx.files.internal("grafico/disparos/laser/2.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/disparos/laser/3.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/disparos/laser/1.png")))});

        impactoEnemgo = new Animation(0.1f, new TextureRegion[]{
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/enemigo/1.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/enemigo/2.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/enemigo/3.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/enemigo/4.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/enemigo/3.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/enemigo/4.png")))
        });
        impactoPlayer = new Animation(0.1f, new TextureRegion[]{
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/hornet/1.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/hornet/2.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/hornet/3.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/hornet/4.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/hornet/3.png"))),
                new Sprite(new Texture(Gdx.files.internal("grafico/impactos/hornet/4.png")))
        });
    }


    public static void liberarTexturas() {
        textureBackground.dispose();
        textureBossName.dispose();
        textureBoss.dispose();
        textureLifebar.dispose();
        textureForeground.dispose();
        textureMiddleground.dispose();
        textureSair.dispose();
        texturePausa.dispose();
        textureHud.dispose();
        textureEnemigoA.dispose();
        textureEnemigoB.dispose();
        textureDrone.dispose();
        textureTurret.dispose();
        textureDisparoHornet.dispose();
        textureDisparoEnemigo.dispose();
        textureDisparoDrone.dispose();
        textureControlesFlechas.dispose();

    }
/*
    public static void cargarAnimacion(){
        ArrayList<TextureRegion> TexReArray = new ArrayList<TextureRegion>();
        TextureRegion[] walksFrame;
        TextureRegion currentFrame;
        TexReArray.add();
        for (int i=0;i<walksFrame.length;i++){
            walksFrame[i]=hsa.get(i);
        }
        walkframe.add(hs);
        walkframe.add(hs1);
        walkframe.add(hs2);
        walkframe.add(hs3);
        hornetStand = new Animation(0.4f,walkframe);
    }
*/
}

