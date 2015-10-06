package com.mygdx.game.modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.juego.Assets;

/**
 * Created by dam204 on 20/02/2015.
 */
public class Mundo {
    public static final int TAMANO_MUNDO_ANCHO = 500;
    public static final int TAMANO_MUNDO_ALTO = 800;


    //jugador
    public final static Vector2 TAMANO_HORNET = new Vector2(70, 70);
    //enemigos
    public final static Vector2 TAMANO_ENEMIGO_A = new Vector2(50, 50);
    public final static Vector2 TAMANO_ENEMIGO_B = new Vector2(50, 50);
    //finalBoss
    public final static Vector2 TAMANO_DRON = new Vector2(40, 40);
    public final static Vector2 TAMANO_ARMA = new Vector2(80, 80);
    public final static Vector2 TAMANO_NODRIZA = new Vector2(500, 150);
    public final static Vector2 TAMANO_LIFEBAR = new Vector2(200,10);
    public final static Vector2 TAMANO_NOMBRE=new Vector2(100,20);
    //Disparos
    public final static Vector2 TAMANO_DISPARO = new Vector2(10, 10);
    public final static Vector2 TAMANO_DISPARO_ENEMIGO = new Vector2(14, 14);
    public final static Vector2 TAMANO_DISPARO_DRONE = new Vector2(10, 10);
    public final static Vector2 TAMANO_PROPULSION = new Vector2(8,10);
    public final static Vector2 TAMANO_LASER = new Vector2(40,TAMANO_MUNDO_ALTO);
    //meteoritos
    public final static Vector2 TAMANO_ROCA = new Vector2(40, 40);
    //impactos
    public final static Vector2 TAMANO_IMPACTO = new Vector2(40,40);
    public final static Vector2 TAMANO_HUD=new Vector2(225,96);
    public final static Vector2 TAMANO_PPE=new Vector2(10,14);
    public final static Vector2 TAMANO_PP2=new Vector2(10,16);



    private float cronometro;
    private int vidas=2;
    private int puntuación=0;

    private Array<Impacto> impactosEnemigo;
    private Array<Impacto> impactosAmigo;
    private Array<Enemigo> enemigoAIzq;
    private Array<Enemigo> enemigoADcha;
    private Array<Enemigo> enemigoBIzq; //Dos texturas con bastante distancia entre ellas
    private Array<Enemigo> enemigoBDcha;
    private Array<Mobiles> backgrounds;
    private Array<Mobiles> middlegrounds;
    private Array<Mobiles> foregrounds;
    private Array<Mobiles> disparoDrone1;
    private Array<Mobiles> disparoDrone2;
    private Array<Mobiles> disparosHornet;
    private Array<Mobiles> disparosEnemigos;
    private Array<Mobiles> laser;
    private Mobiles localizador;
    private Mobiles background;
    private Mobiles middleground;
    private Mobiles foreground;
    private Mobiles droneA;
    private Mobiles droneB;
    private Mobiles hud;
    private Mobiles pp2;
    private Mobiles ppE;
    private Mobiles boss;
    private Mobiles turret;
    private Array<Mobiles> localiz;
    private Mobiles lifebar;
    private Mobiles nombre;
    private Hornet player;
    public static int bossLife=5000;
    public int score=00000;


    private Mobiles dispHorn;


    public Mundo(){
        player=new Hornet(new Vector2((250-(TAMANO_HORNET.x/2)),70),
                new Vector2(TAMANO_HORNET.x,TAMANO_HORNET.y),
                new Vector2(250f,250f)
        );

        this.score=0;
        //BOSS
        boss=new Mobiles(new Vector2(0,TAMANO_MUNDO_ALTO+1500),
                new Vector2(TAMANO_NODRIZA.x,TAMANO_NODRIZA.y),
                new Vector2(0f,-70f),
                Mobiles.TIPOS_ELEMENTOS.BOSS);
        //DRONE_A

        droneA=new Mobiles(new Vector2(60,TAMANO_MUNDO_ALTO+1700),
                new Vector2(TAMANO_DRON.x,TAMANO_DRON.y),new Vector2(0f,-70f),
                Mobiles.TIPOS_ELEMENTOS.DRONE);

        //DRONE_B
        droneB=new Mobiles(new Vector2(TAMANO_MUNDO_ANCHO-60,TAMANO_MUNDO_ALTO+1700),
                new Vector2(TAMANO_DRON.x,TAMANO_DRON.y),new Vector2(0f,-70f),
                Mobiles.TIPOS_ELEMENTOS.DRONE);

        //TORRETA
        turret=new Mobiles(new Vector2(TAMANO_MUNDO_ANCHO/2,TAMANO_MUNDO_ALTO+1500+(TAMANO_NODRIZA.y-TAMANO_ARMA.y-3)),new Vector2(TAMANO_ARMA.x,TAMANO_ARMA.y),new Vector2(0f,-70f), Mobiles.TIPOS_ELEMENTOS.TURRET);

        lifebar=new Mobiles(new Vector2(270,-1700),new Vector2(TAMANO_LIFEBAR.x,TAMANO_LIFEBAR.y),new Vector2(0f,70f), Mobiles.TIPOS_ELEMENTOS.LIFEBAR);


        laser=new Array<Mobiles>();
        localiz=new Array<Mobiles>();
        enemigoAIzq=new Array<Enemigo>();
        enemigoADcha=new Array<Enemigo>();
        enemigoBIzq=new Array<Enemigo>();
        enemigoBDcha=new Array<Enemigo>();
        backgrounds=new Array<Mobiles>();
        middlegrounds=new Array<Mobiles>();
        foregrounds=new Array<Mobiles>();
        disparoDrone1=new Array<Mobiles>();
        disparoDrone2=new Array<Mobiles>();
        disparosEnemigos=new Array<Mobiles>();
        disparosHornet=new Array<Mobiles>();
        impactosEnemigo=new Array<Impacto>();
        impactosAmigo=new Array<Impacto>();

        //OLEADA 1
        enemigoAIzq.add(new Enemigo(new Vector2((0 - 2 * TAMANO_ENEMIGO_A.x), (TAMANO_MUNDO_ALTO + 200)),
                new Vector2(TAMANO_ENEMIGO_A.x, TAMANO_ENEMIGO_A.y),
                new Vector2(0f, -100f), Mobiles.TIPOS_ELEMENTOS.ENEMIGOA));
        enemigoAIzq.add(new Enemigo(new Vector2((0 - 4 * TAMANO_ENEMIGO_A.x), (TAMANO_MUNDO_ALTO + TAMANO_ENEMIGO_A.y + 200)),
                new Vector2(TAMANO_ENEMIGO_A.x, TAMANO_ENEMIGO_A.y),
                new Vector2(0f, -100f), Mobiles.TIPOS_ELEMENTOS.ENEMIGOA));
        enemigoAIzq.add(new Enemigo(new Vector2((0 - 6 * TAMANO_ENEMIGO_A.x), (TAMANO_MUNDO_ALTO + (2 * TAMANO_ENEMIGO_A.y) + 200)),
                new Vector2(TAMANO_ENEMIGO_A.x, TAMANO_ENEMIGO_A.y),
                new Vector2(0f, -100f), Mobiles.TIPOS_ELEMENTOS.ENEMIGOA));
        enemigoAIzq.add(new Enemigo(new Vector2((0 - 8 * TAMANO_ENEMIGO_A.x), (TAMANO_MUNDO_ALTO + (4 * TAMANO_ENEMIGO_A.y) + 200)),
                new Vector2(TAMANO_ENEMIGO_A.x, TAMANO_ENEMIGO_A.y),
                new Vector2(0f, -100f), Mobiles.TIPOS_ELEMENTOS.ENEMIGOA));
        //OLEADA 2 y 4
                //Izquierda
        enemigoBIzq.add(new Enemigo(new Vector2(0,TAMANO_MUNDO_ALTO+500),
                new Vector2(TAMANO_ENEMIGO_B.x,TAMANO_ENEMIGO_B.y),new Vector2(0f,-70), Mobiles.TIPOS_ELEMENTOS.ENEMIGOB));
        enemigoBIzq.add(new Enemigo(new Vector2(0+2*TAMANO_ENEMIGO_B.x,TAMANO_MUNDO_ALTO+500),
                new Vector2(TAMANO_ENEMIGO_B.x,TAMANO_ENEMIGO_B.y),new Vector2(0f,-70), Mobiles.TIPOS_ELEMENTOS.ENEMIGOB));
        enemigoBIzq.add(new Enemigo(new Vector2(0,TAMANO_MUNDO_ALTO+1200),
                new Vector2(TAMANO_ENEMIGO_B.x,TAMANO_ENEMIGO_B.y),new Vector2(0f,-70), Mobiles.TIPOS_ELEMENTOS.ENEMIGOB));
        enemigoBIzq.add(new Enemigo(new Vector2(0+2*TAMANO_ENEMIGO_B.x,TAMANO_MUNDO_ALTO+1200),
                new Vector2(TAMANO_ENEMIGO_B.x,TAMANO_ENEMIGO_B.y),new Vector2(0f,-70), Mobiles.TIPOS_ELEMENTOS.ENEMIGOB));

                //Derecha
        enemigoBDcha.add(new Enemigo(new Vector2(TAMANO_MUNDO_ANCHO-TAMANO_ENEMIGO_B.x,TAMANO_MUNDO_ALTO+430),
                new Vector2(TAMANO_ENEMIGO_B.x,TAMANO_ENEMIGO_B.y),new Vector2(0f,-70), Mobiles.TIPOS_ELEMENTOS.ENEMIGOB));
        enemigoBDcha.add(new Enemigo(new Vector2(TAMANO_MUNDO_ANCHO-3*TAMANO_ENEMIGO_B.x,TAMANO_MUNDO_ALTO+430),
                new Vector2(TAMANO_ENEMIGO_B.x,TAMANO_ENEMIGO_B.y),new Vector2(0f,-70), Mobiles.TIPOS_ELEMENTOS.ENEMIGOB));
        enemigoBDcha.add(new Enemigo(new Vector2(TAMANO_MUNDO_ANCHO-TAMANO_ENEMIGO_B.x,TAMANO_MUNDO_ALTO+1130),
                new Vector2(TAMANO_ENEMIGO_B.x,TAMANO_ENEMIGO_B.y),new Vector2(0f,-70), Mobiles.TIPOS_ELEMENTOS.ENEMIGOB));
        enemigoBDcha.add(new Enemigo(new Vector2(TAMANO_MUNDO_ANCHO-3*TAMANO_ENEMIGO_B.x,TAMANO_MUNDO_ALTO+1130),
                new Vector2(TAMANO_ENEMIGO_B.x,TAMANO_ENEMIGO_B.y),new Vector2(0f,-70), Mobiles.TIPOS_ELEMENTOS.ENEMIGOB));


        //OLEADA 3
        enemigoADcha.add(new Enemigo(new Vector2((TAMANO_MUNDO_ANCHO + 2 * TAMANO_ENEMIGO_A.x), (TAMANO_MUNDO_ALTO + 2000)),
                new Vector2(TAMANO_ENEMIGO_A.x, TAMANO_ENEMIGO_A.y),
                new Vector2(0f, -150f), Mobiles.TIPOS_ELEMENTOS.ENEMIGOA));
        enemigoADcha.add(new Enemigo(new Vector2((TAMANO_MUNDO_ANCHO + 4 * TAMANO_ENEMIGO_A.x), (TAMANO_MUNDO_ALTO + TAMANO_ENEMIGO_A.y +2000)),
                new Vector2(TAMANO_ENEMIGO_A.x, TAMANO_ENEMIGO_A.y),
                new Vector2(0f, -150f), Mobiles.TIPOS_ELEMENTOS.ENEMIGOA));
        enemigoADcha.add(new Enemigo(new Vector2((TAMANO_MUNDO_ANCHO + 6 * TAMANO_ENEMIGO_A.x), (TAMANO_MUNDO_ALTO + 2 * TAMANO_ENEMIGO_A.y + 2000)),
                new Vector2(TAMANO_ENEMIGO_A.x, TAMANO_ENEMIGO_A.y),
                new Vector2(0f, -150f), Mobiles.TIPOS_ELEMENTOS.ENEMIGOA));
        enemigoADcha.add(new Enemigo(new Vector2((TAMANO_MUNDO_ANCHO + 8 * TAMANO_ENEMIGO_A.x), (TAMANO_MUNDO_ALTO + 4 * TAMANO_ENEMIGO_A.y + 2000)),
                new Vector2(TAMANO_ENEMIGO_A.x, TAMANO_ENEMIGO_A.y),
                new Vector2(0f, -150f), Mobiles.TIPOS_ELEMENTOS.ENEMIGOA));

        impactosEnemigo.add(new Impacto(new Vector2(-500,0), Assets.impactoEnemgo));
        impactosAmigo.add(new Impacto(new Vector2(-500,0), Assets.impactoPlayer));


        pp2=new Mobiles(new Vector2(20,12),
                new Vector2(TAMANO_PP2.x,TAMANO_PP2.y),new Vector2(0f,0f),Mobiles.TIPOS_ELEMENTOS.PP);
        ppE=new Mobiles(new Vector2(20,12),
                new Vector2(TAMANO_PPE.x,TAMANO_PPE.y),new Vector2(0f,0f),Mobiles.TIPOS_ELEMENTOS.PP);

        hud=new Mobiles(new Vector2(20,20),
                new Vector2(TAMANO_HUD.x,TAMANO_HUD.y),new Vector2(0f,0f),Mobiles.TIPOS_ELEMENTOS.HUD);
        dispHorn=new Mobiles(new Vector2(TAMANO_HORNET.x / 2 - TAMANO_DISPARO.x / 2, TAMANO_HORNET.y - TAMANO_DISPARO.y),
                new Vector2(TAMANO_DISPARO.x, TAMANO_DISPARO.y),
                new Vector2(0f, 170f),
                Mobiles.TIPOS_ELEMENTOS.DISPARO);


        backgrounds.add(new Mobiles(new Vector2(0,0),
                new Vector2(500,1600),new Vector2(0f,-25f),Mobiles.TIPOS_ELEMENTOS.BG));

        middlegrounds.add(new Mobiles(new Vector2(0,0),
                new Vector2(500,1600),new Vector2(0f,-55f),Mobiles.TIPOS_ELEMENTOS.MG));

        foregrounds.add(new Mobiles(new Vector2(0,0),
                new Vector2(500,1600),new Vector2(0f,-210f),Mobiles.TIPOS_ELEMENTOS.FG));



    }

    public float getCronometro() {
        return cronometro;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Array<Impacto> getImpactosEnemigo() {
        return impactosEnemigo;
    }

    public void setImpactosEnemigo(Array<Impacto> impactosEnemigo) {
        this.impactosEnemigo = impactosEnemigo;
    }

    public Array<Mobiles> getLocaliz() {
        return localiz;
    }

    public void setLocaliz(Array<Mobiles> localiz) {
        this.localiz = localiz;
    }

    public Mobiles getLocalizador() {
        return localizador;
    }

    public void setLocalizador(Mobiles localizador) {
        this.localizador = localizador;
    }

    public Array<Impacto> getImpactosAmigo() {
        return impactosAmigo;
    }

    public void setImpactosAmigo(Array<Impacto> impactosAmigo) {
        this.impactosAmigo = impactosAmigo;
    }

    public Mobiles getNombre() {
        return nombre;
    }

    public Array<Mobiles> getLaser() {
        return laser;
    }

    public void setLaser(Array<Mobiles> laser) {
        this.laser = laser;
    }

    public Mobiles getTurret() {
        return turret;
    }

    public void setTurret(Mobiles turret) {
        this.turret = turret;
    }

    public void setNombre(Mobiles nombre) {
        this.nombre = nombre;
    }

    public Mobiles getLifebar() {
        return lifebar;
    }

    public void setLifebar(Mobiles lifebar) {
        this.lifebar = lifebar;
    }

    public Mobiles getDroneA() {
        return droneA;
    }

    public void setDroneA(Mobiles droneA) {
        this.droneA = droneA;
    }

    public Mobiles getDroneB() {
        return droneB;
    }

    public void setDroneB(Mobiles droneB) {
        this.droneB = droneB;
    }

    public Array<Enemigo> getEnemigoBDcha() {
        return enemigoBDcha;
    }

    public Mobiles getBoss() {
        return boss;
    }

    public void setBoss(Mobiles boss) {
        this.boss = boss;
    }

    public void setEnemigoBDcha(Array<Enemigo> enemigoBDcha) {
        this.enemigoBDcha = enemigoBDcha;
    }

    public Array<Enemigo> getEnemigoADcha() {
        return enemigoADcha;
    }

    public void setEnemigoADcha(Array<Enemigo> enemigoADcha) {
        this.enemigoADcha = enemigoADcha;
    }

    public Array<Mobiles> getBackgrounds() {
        return backgrounds;
    }

    public Mobiles getDispHorn() {
        return dispHorn;
    }

    public void setDispHorn(Mobiles dispHorn) {
        this.dispHorn = dispHorn;
    }

    public void setBackgrounds(Array<Mobiles> backgrounds) {
        this.backgrounds = backgrounds;
    }

    public Array<Mobiles> getDisparoDrone1() {
        return disparoDrone1;
    }

    public void setDisparoDrone1(Array<Mobiles> disparoDrone1) {
        this.disparoDrone1 = disparoDrone1;
    }

    public Array<Mobiles> getDisparoDrone2() {
        return disparoDrone2;
    }

    public void setDisparoDrone2(Array<Mobiles> disparoDrone2) {
        this.disparoDrone2 = disparoDrone2;
    }

    public Array<Mobiles> getDisparosHornet() {
        return disparosHornet;
    }

    public void setDisparosHornet(Array<Mobiles> disparosHornet) {
        this.disparosHornet = disparosHornet;
    }

    public Array<Mobiles> getDisparosEnemigos() {
        return disparosEnemigos;
    }

    public void setDisparosEnemigos(Array<Mobiles> disparosEnemigos) {
        this.disparosEnemigos = disparosEnemigos;
    }

    public Array<Mobiles> getMiddlegrounds() {
        return middlegrounds;
    }

    public void setMiddlegrounds(Array<Mobiles> middlegrounds) {
        this.middlegrounds = middlegrounds;
    }

    public Array<Mobiles> getForegrounds() {
        return foregrounds;
    }

    public void setForegrounds(Array<Mobiles> foregrounds) {
        this.foregrounds = foregrounds;
    }

    public Mobiles getPpE() {
        return ppE;
    }

    public void setPpE(Mobiles ppE) {
        this.ppE = ppE;
    }

    public Mobiles getHud() {
        return hud;
    }

    public void setHud(Mobiles hud) {
        this.hud = hud;
    }

    public Mobiles getPp2() {
        return pp2;
    }

    public void setPp2(Mobiles pp2) {
        this.pp2 = pp2;
    }

    public Mobiles getBackground() {
        return background;
    }

    public void setBackground(Mobiles background) {
        this.background = background;
    }

    public Mobiles getMiddleground() {
        return middleground;
    }

    public void setMiddleground(Mobiles middleground) {
        this.middleground = middleground;
    }

    public Mobiles getForeground() {
        return foreground;
    }

    public void setForeground(Mobiles foreground) {
        this.foreground = foreground;
    }

    public void setCronometro(float cronometro) {
        this.cronometro = cronometro;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getPuntuación() {
        return puntuación;
    }

    public void setPuntuación(int puntuación) {
        this.puntuación = puntuación;
    }

    public Array<Enemigo> getEnemigoAIzq() {
        return enemigoAIzq;
    }

    public void setEnemigoAIzq(Array<Enemigo> enemigoA) {
        this.enemigoAIzq = enemigoA;
    }

    public Array<Enemigo> getEnemigoBIzq() {
        return enemigoBIzq;
    }

    public void setEnemigoBIzq(Array<Enemigo> enemigoB) {
        this.enemigoBIzq = enemigoB;
    }

    public Hornet getPlayer() {
        return player;
    }

    public void setPlayer(Hornet player) {
        this.player = player;
    }
}
