package com.mygdx.game.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.juego.Assets;
import com.mygdx.game.modelo.Enemigo;
import com.mygdx.game.modelo.Hornet;
import com.mygdx.game.modelo.Impacto;
import com.mygdx.game.modelo.Mobiles;
import com.mygdx.game.modelo.Mundo;

import java.util.HashMap;

/**
 * Created by usuario on 21/02/2015.
 */
public class Controller {

    private Mundo mundo;
    private Hornet player;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int contadorBalasDispDronA = 0;
    private int contadorBalasDispDronB = 0;
    private float stateTimeBoss;
    private long tiempoAntTorreta;
    private long tiempoAntDronDisp1;
    private long tiempoAntDronDisp2;
    private long tiempoAntDronDisp1B;
    private long tiempoAntDronDisp2B;
    private long tiempoDispTactil;
    private long tiempoAccurate;
    private long tiempo;
    private TextureRegion mostrarAnimBoss;
    public boolean tap=false;
    private boolean EBhorizontalDcha = false;
    private boolean EBhorizontalIzq = false;
    private boolean DAdirecc = false;
    private boolean DBdirecc = false;
    private boolean retardo = false;
    private boolean localizacion = false;
    private boolean disparoTactil=false;
    private boolean disparoEnemyAIzq = false;
    private boolean disparoEnemyADcha = false;
    private boolean disparoEnemyBIzq = false;
    private boolean disparoEnemyBDcha = false;
    private boolean primeraVezDroneA = true;
    private boolean primeraVezDroneB = true;
    private boolean primeraVezTurret = true;
    private boolean disparoTurret = false;
    private boolean disparoDron1 = false;
    private boolean disparoDron2 = false;
    private int direccionEnemyBIzq = 0;
    private int direccionEnemyBDcha = 0;
    private int accionDroneA = 0;//1 para disparo triple, 2 para disparo doble, 0 para nada
    private int accionDroneB = 0;//1 para disparo triple, 2 para disparo doble, 0 para nada
    private int accionLaser = 0;//1 para rallo indicador, 2 para disparo
    private SpriteBatch spriteBatch = new SpriteBatch();

    public enum Keys {
        IZQ, DER, ARR, ABA, SHOOT;
    }


    HashMap<Keys, Boolean> keyMap = new HashMap<Controller.Keys, Boolean>();

    {
        keyMap.put(Keys.IZQ, false);
        keyMap.put(Keys.DER, false);
        keyMap.put(Keys.ABA, false);
        keyMap.put(Keys.ARR, false);
        keyMap.put(Keys.SHOOT, false);

    }

    public Controller(Mundo mundo) {
        this.mundo = mundo;
        this.player = mundo.getPlayer();
    }

    public void pulsarTecla(Keys key) {
        keyMap.put(key, true);
    }

    public void liberarTecla(Keys key) {
        keyMap.put(key, false);
    }

    private void procesarEntradas() {
        if (keyMap.get(Keys.IZQ)) {
            player.setVelocidadeX(-player.velocidade_max.x);
        }
        if (keyMap.get(Keys.DER)) {
            player.setVelocidadeX(player.velocidade_max.x);
        }
        if (keyMap.get(Keys.ARR)) {
            player.setVelocidadeY(player.velocidade_max.y);
        }
        if (keyMap.get(Keys.ABA)) {
            player.setVelocidadeY(-player.velocidade_max.y);
        }
        if (keyMap.get(Keys.ABA) && keyMap.get(Keys.DER)) {
            player.setVelocidadeX(player.velocidade_max.x);
            player.setVelocidadeY(-player.velocidade_max.y);
        }
        if (keyMap.get(Keys.ARR) && keyMap.get(Keys.DER)) {
            player.setVelocidadeX(player.velocidade_max.x);
            player.setVelocidadeY(player.velocidade_max.y);
        }
        if (keyMap.get(Keys.ABA) && keyMap.get(Keys.IZQ)) {
            player.setVelocidadeX(-player.velocidade_max.x);
            player.setVelocidadeY(-player.velocidade_max.y);
        }
        if (keyMap.get(Keys.ARR) && keyMap.get(Keys.IZQ)) {
            player.setVelocidadeX(-player.velocidade_max.x);
            player.setVelocidadeY(player.velocidade_max.y);
        }

        if (!keyMap.get(Keys.DER) && !keyMap.get(Keys.IZQ)) {
            player.setVelocidadeX(0);
        }
        if (!keyMap.get(Keys.ARR) && !keyMap.get(Keys.ABA)) {
            player.setVelocidadeY(0);
        }

        if (keyMap.get(Keys.SHOOT)) {
            player.setDisparo(true);

        }
        if (!keyMap.get(Keys.SHOOT)) {
            player.setDisparo(false);
        }
    }

    private void controlDispTactil (Float delta){
       if(tap){
        if(!disparoTactil){
            player.setDisparo(true);
            disparoTactil=true;
            tiempoDispTactil=tiempoAccurate;
        }
if(disparoTactil&&tiempoAccurate-tiempoDispTactil>100){
            player.setDisparo(false);
            disparoTactil=false;
        }
       }

    }

    private void controlPlayer(Float delta) {
        player.update(delta);

        if (player.getPosicion().x <= 0) {
            player.setPosicion(0, player.getPosicion().y);
        } else {
            if (player.getPosicion().x >= (Mundo.TAMANO_MUNDO_ANCHO - player.getTamano().x)) {
                player.setPosicion((Mundo.TAMANO_MUNDO_ANCHO - player.getTamano().x), player.getPosicion().y);
            }
        }

        if (player.getPosicion().y <= 20) {
            player.setPosicion(player.getPosicion().x, 20);
        } else {
            if (player.getPosicion().y >= Mundo.TAMANO_MUNDO_ALTO - player.getTamano().y) {
                player.setPosicion(player.getPosicion().x, Mundo.TAMANO_MUNDO_ALTO - player.getTamano().y);
            }
        }
        for (Mobiles enemA1 : mundo.getEnemigoAIzq()) {
            if (Intersector.overlaps(enemA1.getRectangulo(), player.getRectangulo())&&!enemA1.isImpactado()) {
                mundo.getImpactosEnemigo().add(new Impacto
                        (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                                player.getPosicion().y + player.getTamano().y / 2),
                                Assets.impactoEnemgo));
                enemA1.setImpactado(true);
                if (player.lifes > 0) {
                    player.inicializarHornet();
                }
            }
            for (Mobiles enemA2 : mundo.getEnemigoADcha()) {
                if (Intersector.overlaps(enemA2.getRectangulo(), player.getRectangulo())&&!enemA1.isImpactado()) {
                    mundo.getImpactosEnemigo().add(new Impacto
                            (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                                    player.getPosicion().y + player.getTamano().y / 2),
                                    Assets.impactoEnemgo));
                    enemA1.setImpactado(true);
                    if (player.lifes > 0) {
                        player.inicializarHornet();
                    }
                }

            }

        }
        for (Mobiles enemB1 : mundo.getEnemigoBDcha()) {
            if (Intersector.overlaps(enemB1.getRectangulo(), player.getRectangulo())&&!enemB1.isImpactado()) {
                mundo.getImpactosEnemigo().add(new Impacto
                        (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                                player.getPosicion().y + player.getTamano().y / 2),
                                Assets.impactoEnemgo));
                enemB1.setImpactado(true);
                if (player.lifes > 0) {
                    player.inicializarHornet();
                }
            }

        }

        for (Mobiles enemB2 : mundo.getEnemigoBIzq()) {
            if (Intersector.overlaps(enemB2.getRectangulo(), player.getRectangulo())&&!enemB2.isImpactado()) {
                mundo.getImpactosEnemigo().add(new Impacto
                        (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                                player.getPosicion().y + player.getTamano().y / 2),
                                Assets.impactoEnemgo));
                enemB2.setImpactado(true);
                if (player.lifes > 0) {
                    player.inicializarHornet();
                }
            }

        }
        for (Mobiles dispDron1 : mundo.getDisparoDrone1()) {
            if (Intersector.overlaps(dispDron1.getRectangulo(), player.getRectangulo())&&!dispDron1.isImpactado()) {
                mundo.getImpactosEnemigo().add(new Impacto
                        (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                                player.getPosicion().y + player.getTamano().y / 2),
                                Assets.impactoEnemgo));
                dispDron1.setImpactado(true);
                if (player.lifes > 0) {
                    player.inicializarHornet();
                }
            }

        }
        for (Mobiles dispDron2 : mundo.getDisparoDrone2()) {
            if (Intersector.overlaps(dispDron2.getRectangulo(), player.getRectangulo())&&!dispDron2.isImpactado()) {
                mundo.getImpactosEnemigo().add(new Impacto
                        (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                                player.getPosicion().y + player.getTamano().y / 2),
                                Assets.impactoEnemgo));
                dispDron2.setImpactado(true);
                if (player.lifes > 0) {
                    player.inicializarHornet();
                }
            }

        }
        for (Mobiles disp : mundo.getDisparosEnemigos()) {
            if (Intersector.overlaps(disp.getRectangulo(), player.getRectangulo())&&!disp.isImpactado()) {
                mundo.getImpactosEnemigo().add(new Impacto
                        (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                                player.getPosicion().y + player.getTamano().y / 2),
                                Assets.impactoEnemgo));
                disp.setImpactado(true);
                if (player.lifes > 0) {
                    player.inicializarHornet();
                }
            }

        }


        for (Mobiles laser : mundo.getLaser()) {
            if (Intersector.overlaps(laser.getRectangulo(), player.getRectangulo())&&!laser.isImpactado()) {
                mundo.getImpactosEnemigo().add(new Impacto
                        (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                                player.getPosicion().y + player.getTamano().y / 2),
                                Assets.impactoEnemgo));
                laser.setImpactado(true);
                if (player.lifes > 0) {
                    player.inicializarHornet();
                }
            }
        }

        if (Intersector.overlaps(mundo.getBoss().getRectangulo(), player.getRectangulo())&&!mundo.getBoss().isImpactado()) {
            mundo.getImpactosEnemigo().add(new Impacto
                    (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                            player.getPosicion().y + player.getTamano().y / 2),
                            Assets.impactoEnemgo));
            mundo.getBoss().setImpactado(true);
            if (player.lifes > 0) {
                player.inicializarHornet();
            }
        }
        if (Intersector.overlaps(mundo.getDroneA().getRectangulo(), player.getRectangulo())) {
            mundo.getImpactosEnemigo().add(new Impacto
                    (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                            player.getPosicion().y + player.getTamano().y / 2),
                            Assets.impactoEnemgo));
            if (player.lifes > 0) {
                player.inicializarHornet();
            }
        }
        if (Intersector.overlaps(mundo.getDroneB().getRectangulo(), player.getRectangulo())) {
            mundo.getImpactosEnemigo().add(new Impacto
                    (new Vector2(player.getPosicion().x + player.getTamano().x / 2,
                            player.getPosicion().y + player.getTamano().y / 2),
                            Assets.impactoEnemgo));
            if (player.lifes > 0) {
                player.inicializarHornet();
            }
        }
        for (Impacto impacEn : mundo.getImpactosEnemigo()) {
            if (impacEn.getPosicion().x > -400 && impacEn.isAnimStopped()) {
                mundo.getImpactosEnemigo().removeValue(impacEn, true);
            }

        }
    }


    private void controlEnemigoAIzq(Float delta) {
        for (Enemigo eneA : mundo.getEnemigoAIzq()) {

            if (eneA.getPosicion().y <= Mundo.TAMANO_MUNDO_ALTO) {
                eneA.setVelocidade_maxY(-100f);
                eneA.setVelocidade_maxX(150f);
            }
            if (eneA.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO / 2) {
                if (eneA.getPosicion().x < 1 + Mundo.TAMANO_MUNDO_ANCHO / 2) {
                    disparoEnemyAIzq = true;
                }
                eneA.setVelocidade_maxX(600f);
            }


            if (eneA.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO) {
                mundo.getEnemigoAIzq().removeValue((Enemigo) eneA, true);
            }
            eneA.update(delta);

            for (Mobiles disp : mundo.getDisparosHornet()) {
                if (Intersector.overlaps(disp.getRectangulo(), eneA.getRectangulo())
                        && eneA.getPosicion().x > 0
                        && eneA.getPosicion().y > 0
                        && eneA.getPosicion().x < Mundo.TAMANO_MUNDO_ANCHO
                        && eneA.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {

                    mundo.getImpactosAmigo().add(new Impacto
                            (new Vector2(eneA.getPosicion().x + eneA.getTamano().x / 2,
                                    eneA.getPosicion().y + eneA.getTamano().y / 2),
                                    Assets.impactoPlayer));

                    eneA.setDestruido(true);
                }
            }

            if (eneA.isDestruido()) {
                mundo.getEnemigoAIzq().removeValue(eneA, true);
                mundo.score = mundo.score + 50;
            }
        }
        if (disparoEnemyAIzq) {
            for (Enemigo eneA : mundo.getEnemigoAIzq()) {
                mundo.getDisparosEnemigos().add(new Mobiles(new Vector2(eneA.getPosicion().x/*+Mundo.TAMANO_ENEMIGO_A.x*(2/3)*/,
                        eneA.getPosicion().y),
                        new Vector2(Mundo.TAMANO_DISPARO_ENEMIGO.x, Mundo.TAMANO_DISPARO_ENEMIGO.y), new Vector2(0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));
            }
            disparoEnemyAIzq = false;
        }
        for (Impacto impacPl : mundo.getImpactosAmigo()) {
            if (impacPl.getPosicion().x > -400 && impacPl.isAnimStopped()) {
                mundo.getImpactosAmigo().removeValue(impacPl, true);
            }

        }

    }

    private void controlEnemigoADcha(Float delta) {
        for (Enemigo eneA : mundo.getEnemigoADcha()) {

            if (eneA.getPosicion().y <= Mundo.TAMANO_MUNDO_ALTO) {
                eneA.setVelocidade_maxY(-100f);
                eneA.setVelocidade_maxX(-150f);
            }
            if (eneA.getPosicion().x < Mundo.TAMANO_MUNDO_ANCHO / 2) {
                if (eneA.getPosicion().x > (Mundo.TAMANO_MUNDO_ANCHO / 2) - 10) {
                    disparoEnemyADcha = true;
                }
                eneA.setVelocidade_maxX(-600f);
            }

            if (eneA.getPosicion().x < 0) {
                mundo.getEnemigoADcha().removeValue(eneA, true);
            }
            eneA.update(delta);

            for (Mobiles disp : mundo.getDisparosHornet()) {
                if (Intersector.overlaps(disp.getRectangulo(), eneA.getRectangulo())
                        && eneA.getPosicion().x > 0
                        && eneA.getPosicion().y > 0
                        && eneA.getPosicion().x < Mundo.TAMANO_MUNDO_ANCHO
                        && eneA.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {

                    mundo.getImpactosAmigo().add(new Impacto
                            (new Vector2(eneA.getPosicion().x + eneA.getTamano().x / 2,
                                    eneA.getPosicion().y + eneA.getTamano().y / 2),
                                    Assets.impactoPlayer));

                    eneA.setDestruido(true);
                }
            }

            if (eneA.isDestruido()) {
                mundo.getEnemigoADcha().removeValue(eneA, true);
                mundo.score = mundo.score + 50;
            }


        }

        if (disparoEnemyADcha) {
            for (Enemigo eneA : mundo.getEnemigoADcha()) {
                mundo.getDisparosEnemigos().add(new Mobiles(new Vector2(eneA.getPosicion().x/*+Mundo.TAMANO_ENEMIGO_A.x*(2/3)*/,
                        eneA.getPosicion().y),
                        new Vector2(Mundo.TAMANO_DISPARO_ENEMIGO.x, Mundo.TAMANO_DISPARO_ENEMIGO.y), new Vector2(0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));
            }
            disparoEnemyADcha = false;
        }

        for (Impacto impacPl : mundo.getImpactosAmigo()) {
            if (impacPl.getPosicion().x > -400 && impacPl.isAnimStopped()) {
                mundo.getImpactosAmigo().removeValue(impacPl, true);
            }

        }
    }

    private void controlEnemigoBIzq(Float delta) {
        for (Enemigo eneB : mundo.getEnemigoBIzq()) {

            if (eneB.getPosicion().y <= Mundo.TAMANO_MUNDO_ALTO - 150 && eneB.getPosicion().y >= Mundo.TAMANO_MUNDO_ALTO - 250 && !EBhorizontalDcha) {
                direccionEnemyBIzq = 3;


                if (eneB.getPosicion().x < 0 + 1) {
                    disparoEnemyBIzq = true;
                }
            }
            eneB.update(delta);
            if (eneB.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO / 2 && eneB.getPosicion().x < Mundo.TAMANO_MUNDO_ANCHO / 2 + 1) {
                disparoEnemyBIzq = true;
            }
            if (eneB.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO - eneB.getTamano().x && EBhorizontalDcha) {


                direccionEnemyBIzq = 1;
            }
            if (eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO - 250) {
                EBhorizontalDcha = false;
                direccionEnemyBIzq = 2;
            }

            if (eneB.getPosicion().x < 0 - Mundo.TAMANO_ENEMIGO_B.x) {
                mundo.getEnemigoBIzq().removeValue((Enemigo) eneB, true);
            }

            for (Mobiles disp : mundo.getDisparosHornet()) {
                if (Intersector.overlaps(disp.getRectangulo(), eneB.getRectangulo())
                        && eneB.getPosicion().x >= 0
                        && eneB.getPosicion().y > 0
                        && eneB.getPosicion().x <= Mundo.TAMANO_MUNDO_ANCHO
                        && eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO - 150) {

                    mundo.getImpactosAmigo().add(new Impacto
                            (new Vector2(eneB.getPosicion().x + eneB.getTamano().x / 2,
                                    eneB.getPosicion().y + eneB.getTamano().y / 2),
                                    Assets.impactoPlayer));

                    eneB.setDestruido(true);
                }
            }

            if (eneB.isDestruido()) {
                mundo.getEnemigoBIzq().removeValue(eneB, true);
                mundo.score = mundo.score + 50;
            }

        }


        switch (direccionEnemyBIzq) {
            case 3:
                for (Mobiles eneB : mundo.getEnemigoBIzq()) {
                    if (eneB.getPosicion().x >= 0 && eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {
                        eneB.setVelocidade_maxY(0f);
                        eneB.setVelocidade_maxX(150f);
                    }
                }
                direccionEnemyBIzq = 0;
                EBhorizontalDcha = true;
                break;

            case 1:
                for (Mobiles eneB : mundo.getEnemigoBIzq()) {
                    if (eneB.getPosicion().x > 0 && eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {
                        eneB.setVelocidade_maxX(0f);
                        eneB.setVelocidade_maxY(-150f);
                        //eneB.update(delta);
                    }
                    eneB.update(delta);
                }
                direccionEnemyBIzq = 0;
                break;
            case 2:
                for (Mobiles eneB : mundo.getEnemigoBIzq()) {
                    if (eneB.getPosicion().x > 0 - eneB.getTamano().x && eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {
                        eneB.setVelocidade_maxX(-150f);
                        eneB.setVelocidade_maxY(0f);

                    }

                }

                direccionEnemyBIzq = 0;
                break;
        }
        if (disparoEnemyBIzq) {
            for (Mobiles eneB : mundo.getEnemigoBIzq()) {
                if (eneB.getPosicion().x > 0 - 5 && eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {
                    mundo.getDisparosEnemigos().add(new Mobiles(new Vector2(eneB.getPosicion().x/*+Mundo.TAMANO_ENEMIGO_A.x*(2/3)*/,
                            eneB.getPosicion().y),
                            new Vector2(Mundo.TAMANO_DISPARO_ENEMIGO.x, Mundo.TAMANO_DISPARO_ENEMIGO.y), new Vector2(0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));
                }
            }
            disparoEnemyBIzq = false;
        }

        for (Impacto impacPl : mundo.getImpactosAmigo()) {
            if (impacPl.getPosicion().x > -400 && impacPl.isAnimStopped()) {
                mundo.getImpactosAmigo().removeValue(impacPl, true);
            }

        }


    }

    private void controlEnemigoBDcha(Float delta) {
        for (Enemigo eneB : mundo.getEnemigoBDcha()) {

            if (eneB.getPosicion().y <= Mundo.TAMANO_MUNDO_ALTO - 220 && eneB.getPosicion().y >= Mundo.TAMANO_MUNDO_ALTO - 320 && !EBhorizontalIzq) {
                direccionEnemyBDcha = 3;


                if (eneB.getPosicion().x < Mundo.TAMANO_MUNDO_ANCHO + 1) {
                    disparoEnemyBDcha = true;
                }
            }
            eneB.update(delta);
            if (eneB.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO / 2 - 1 && eneB.getPosicion().x < Mundo.TAMANO_MUNDO_ANCHO / 2) {
                disparoEnemyBDcha = true;
            }
            if (eneB.getPosicion().x < 0 && EBhorizontalIzq) {


                direccionEnemyBDcha = 1;
            }
            if (eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO - 320) {
                EBhorizontalIzq = false;
                direccionEnemyBDcha = 2;
            }

            if (eneB.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO) {
                mundo.getEnemigoBDcha().removeValue((Enemigo) eneB, true);
            }


            for (Mobiles disp : mundo.getDisparosHornet()) {
                if (Intersector.overlaps(disp.getRectangulo(), eneB.getRectangulo())
                        && eneB.getPosicion().x >= 0
                        && eneB.getPosicion().y > 0
                        && eneB.getPosicion().x <= Mundo.TAMANO_MUNDO_ANCHO
                        && eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO - 150) {

                    mundo.getImpactosAmigo().add(new Impacto
                            (new Vector2(eneB.getPosicion().x + eneB.getTamano().x / 2,
                                    eneB.getPosicion().y + eneB.getTamano().y / 2),
                                    Assets.impactoPlayer));

                    eneB.setDestruido(true);
                }
            }

            if (eneB.isDestruido()) {
                mundo.getEnemigoBDcha().removeValue(eneB, true);
                mundo.score = mundo.score + 50;
            }


        }


        switch (direccionEnemyBDcha) {
            case 3:
                for (Mobiles eneB : mundo.getEnemigoBDcha()) {
                    if (eneB.getPosicion().x > 0 && eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {
                        eneB.setVelocidade_maxY(0f);
                        eneB.setVelocidade_maxX(-150f);
                    }
                }
                direccionEnemyBDcha = 0;
                EBhorizontalIzq = true;
                break;

            case 1:
                for (Mobiles eneB : mundo.getEnemigoBDcha()) {
                    if (/*eneB.getPosicion().x >=-1 && */eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {
                        eneB.setVelocidade_maxX(0f);
                        eneB.setVelocidade_maxY(-150f);
                    }
                    eneB.update(delta);
                }
                direccionEnemyBDcha = 0;
                break;
            case 2:
                for (Mobiles eneB : mundo.getEnemigoBDcha()) {
                    if (eneB.getPosicion().x >= 0 - eneB.getTamano().x && eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {
                        eneB.setVelocidade_maxX(150f);
                        eneB.setVelocidade_maxY(0f);

                    }

                }

                direccionEnemyBDcha = 0;
                break;
        }
        if (disparoEnemyBDcha) {
            for (Mobiles eneB : mundo.getEnemigoBDcha()) {
                if (eneB.getPosicion().x > 0 - 5 && eneB.getPosicion().y < Mundo.TAMANO_MUNDO_ALTO) {
                    mundo.getDisparosEnemigos().add(new Mobiles(new Vector2(eneB.getPosicion().x/*+Mundo.TAMANO_ENEMIGO_A.x*(2/3)*/,
                            eneB.getPosicion().y),
                            new Vector2(Mundo.TAMANO_DISPARO_ENEMIGO.x, Mundo.TAMANO_DISPARO_ENEMIGO.y), new Vector2(0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));
                }
            }
            disparoEnemyBDcha = false;
        }

        for (Impacto impacPl : mundo.getImpactosAmigo()) {
            if (impacPl.getPosicion().x > -400 && impacPl.isAnimStopped()) {
                mundo.getImpactosAmigo().removeValue(impacPl, true);
            }

        }

    }

    private void controlBoss(Float delta) {
        stateTimeBoss += Gdx.graphics.getDeltaTime();
        mostrarAnimBoss = Assets.hornetStand.getKeyFrame(stateTimeBoss, true);
        spriteBatch.begin();
        spriteBatch.draw(mostrarAnimBoss, mundo.getBoss().getPosicion().x, mundo.getBoss().getPosicion().y, mundo.getBoss().getTamano().x, mundo.getBoss().getTamano().y);
        spriteBatch.end();
        mundo.getBoss().update(delta);
        if (mundo.getBoss().getPosicion().y <= Mundo.TAMANO_MUNDO_ALTO - Mundo.TAMANO_NODRIZA.y) {
            mundo.getBoss().setVelocidadeY(0f);
        }

        for (Mobiles disp : mundo.getDisparosHornet()) {
            if (Intersector.overlaps(disp.getRectangulo(), mundo.getBoss().getRectangulo())
                    && mundo.getBoss().getPosicion().y < Mundo.TAMANO_MUNDO_ALTO
                    && !disp.isImpactado()) {
                mundo.bossLife = mundo.bossLife - 50;
                disp.setImpactado(true);
                mundo.getImpactosAmigo().add(new Impacto
                        (new Vector2(mundo.getBoss().getPosicion().x + mundo.getBoss().getTamano().x / 2,
                                mundo.getBoss().getPosicion().y + mundo.getBoss().getTamano().y / 2),
                                Assets.impactoPlayer));

            }


            if (mundo.bossLife <= 0) {
                mundo.getBoss().setDestruido(true);
            }

            if (mundo.getBoss().isDestruido()) {
                mundo.getBoss().setPosicion(new Vector2(0f, Mundo.TAMANO_MUNDO_ANCHO + 400));
                mundo.score = mundo.score + 500;
                mundo.getTurret().setPosicion(new Vector2(0f, Mundo.TAMANO_MUNDO_ANCHO + 400));
                mundo.score = mundo.score + 200;
                mundo.getDroneA().setPosicion(new Vector2(0f, Mundo.TAMANO_MUNDO_ANCHO + 400));
                mundo.score = mundo.score + 100;
                mundo.getDroneB().setPosicion(new Vector2(0f, Mundo.TAMANO_MUNDO_ANCHO + 400));
                mundo.score = mundo.score + 100;
            }


            //si el contador de vida desaparece desaparece y ya

        }
    }

    private void controlTorreta(Float delta) {
        mundo.getTurret().update(delta);

        if (mundo.getTurret().getPosicion().y < Mundo.TAMANO_MUNDO_ALTO - 78) {//si llega a su posición

            if (primeraVezTurret) {
                tiempoAntTorreta = tiempo;
                mundo.getTurret().setVelocidadeY(0f);
                primeraVezTurret = false;
            }
            if (!disparoTurret) {
                if (/*player.getVelocidadX() == 0*/player.getPosicion().x == mundo.getTurret().getPosicion().x) { //seguir al jugador con algo de retraso
                    mundo.getTurret().setVelocidadeX(player.getVelocidadX());
                } else {
                    if (/*player.getVelocidadX() > 0*/ player.getPosicion().x - mundo.getTurret().getPosicion().x < 0) {
                        mundo.getTurret().setVelocidadeX(player.getVelocidadX() - 50);
                    } else {
                        if (/*player.getVelocidadX() < 0*/player.getPosicion().x - mundo.getTurret().getPosicion().x > 0) {
                            mundo.getTurret().setVelocidadeX(player.getVelocidadX() + 50);
                        }
                    }
                }
            }

            if (tiempo - tiempoAntTorreta > 2 && disparoTurret) {//devolver todo a la normalidad
                disparoTurret = false;
                localizacion = false;
                tiempoAntTorreta = tiempo;
                for (Mobiles laser : mundo.getLaser()) {
                    mundo.getLaser().removeValue(laser, true);
                }
            }
            if (localizacion && tiempo - tiempoAntTorreta > 2) {//si pasan X segundos una vez esté activa la localización creamos un objeto para la textura del laser previamente borrando la localizacion
                for (Mobiles localiz : mundo.getLocaliz()) {
                    mundo.getLocaliz().removeValue(localiz, true);
                }
                tiempoAntTorreta = tiempo;
                mundo.getTurret().setVelocidadeX(0f);
                disparoTurret = true;
                mundo.getLaser().add(new Mobiles(
                        new Vector2(mundo.getTurret().getPosicion().x + (Mundo.TAMANO_ARMA.x / 2 - Mundo.TAMANO_LASER.x / 2), mundo.getTurret().getPosicion().y-Mundo.TAMANO_LASER.y),
                        new Vector2(Mundo.TAMANO_LASER.x, Mundo.TAMANO_LASER.y),
                        new Vector2(0f, 0f),
                        Mobiles.TIPOS_ELEMENTOS.LASER));

            }


            if (tiempo - tiempoAntTorreta > 4 && !localizacion) {//si pasan X segundos y no está activa la localización creamos un objeto para la textura de la localizacion
                mundo.getLocaliz().add(new Mobiles(
                        new Vector2(mundo.getTurret().getPosicion().x + (Mundo.TAMANO_ARMA.x / 2 - Mundo.TAMANO_LASER.x / 2), mundo.getTurret().getPosicion().y - Mundo.TAMANO_LASER.y),
                        new Vector2(Mundo.TAMANO_LASER.x, Mundo.TAMANO_LASER.y),
                        new Vector2(0f, 0f),
                        Mobiles.TIPOS_ELEMENTOS.LOCALIZ));
                localizacion = true;
                tiempoAntTorreta = tiempo;
            }

        }

        /*Controla que no se vaya para fuera de la pantalla*/
        if (mundo.getTurret().getPosicion().x < 40) {
            mundo.getTurret().setPosicion(new Vector2(40, mundo.getTurret().getPosicion().y));
        }
        if (mundo.getTurret().getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO -
                (mundo.getTurret().getTamano().x + 40)) {
            mundo.getTurret().setPosicion(new Vector2(Mundo.TAMANO_MUNDO_ANCHO -
                    (mundo.getTurret().getTamano().x + 40), mundo.getTurret().getPosicion().y));
        }


    }

    private void controlLaser(Float delta) {
        for (Mobiles laser : mundo.getLaser()) {
            laser.update(delta);
        }
        for (Mobiles localiz : mundo.getLocaliz()) {
            localiz.update(delta);
        }
    }

    private void controlImpacto(Float delta) {
        for (Impacto impact : mundo.getImpactosEnemigo()) {
            impact.update(delta);
        }
        for (Impacto impactPl : mundo.getImpactosAmigo()) {
            impactPl.update(delta);
        }


    }

    private void controlLifebar(Float delta) {
        mundo.getLifebar().update(delta);
        if (mundo.getLifebar().getPosicion().y > 30) {
            mundo.getLifebar().setVelocidadeY(0f);
        }


    }

    private void controlDroneA(Float delta) {
        mundo.getDroneA().update(delta);
        if (mundo.getDroneA().getPosicion().y
                <= Mundo.TAMANO_MUNDO_ALTO - (Mundo.TAMANO_NODRIZA.y + Mundo.TAMANO_DRON.y + 40)) {
            if (primeraVezDroneA) {
                tiempoAntDronDisp1 = tiempo;
                tiempoAntDronDisp2 = tiempo + 2;
                mundo.getDroneA().setVelocidadeY(0f);
                primeraVezDroneA = false;
            }


            if (mundo.getDroneA().getPosicion().x <= 60/*||mundo.getDroneA().getPosicion().x ==Mundo.TAMANO_MUNDO_ANCHO - 60&&!DAdirecc*/) {
                mundo.getDroneA().setVelocidadeX(90f);
            }
            if (mundo.getDroneA().getPosicion().x >= Mundo.TAMANO_MUNDO_ANCHO - (60 + Mundo.TAMANO_DRON.x)) {
                mundo.getDroneA().setVelocidadeX(-90f);

            }

            if (tiempo - tiempoAntDronDisp1 > 4) {
                this.accionDroneA = 1; //si pasan dos segundos de la última vez que disparo esto, ponemos la accion a uno
                tiempoAntDronDisp1 = tiempo;
            }

            if (tiempo - tiempoAntDronDisp2 > 4) {
                this.accionDroneA = 2; //si pasan dos segundos de la última vez que disparo esto, ponemos la accion a uno
                tiempoAntDronDisp2 = tiempo;
            }

            switch (accionDroneA) {
                case 1:
                    if (contadorBalasDispDronA < 3) {
                        mundo.getDisparoDrone1().add(
                                new Mobiles(
                                        new Vector2(mundo.getDroneA().getPosicion().x - Mundo.TAMANO_DRON.x / 2,
                                                mundo.getDroneA().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronA * 40)),
                                        new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                        new Vector2(0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));
                        mundo.getDisparoDrone1().add(
                                new Mobiles(
                                        new Vector2(mundo.getDroneA().getPosicion().x - Mundo.TAMANO_DRON.x - (contadorBalasDispDronA * 40),
                                                mundo.getDroneA().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronA * 40)),
                                        new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                        new Vector2(-60f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));
                        mundo.getDisparoDrone1().add(
                                new Mobiles(
                                        new Vector2(mundo.getDroneA().getPosicion().x + Mundo.TAMANO_DRON.x + (contadorBalasDispDronA * 40),
                                                mundo.getDroneA().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronA * 40)),
                                        new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                        new Vector2(60f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));

                        contadorBalasDispDronA++;
                    } else {
                        this.accionDroneA = 0;
                        contadorBalasDispDronA = 0;
                    }
                    break;
                case 2:
                    if (contadorBalasDispDronA < 3) {
                        if (mundo.getDroneA().getPosicion().x < Mundo.TAMANO_MUNDO_ANCHO / 2) {
                            mundo.getDisparoDrone2().add(
                                    new Mobiles(
                                            new Vector2(mundo.getDroneA().getPosicion().x + Mundo.TAMANO_DRON.x / 2,
                                                    mundo.getDroneA().getPosicion().y - Mundo.TAMANO_DRON.y + (contadorBalasDispDronA * 40)),
                                            new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                            new Vector2(0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO)
                            );
                            mundo.getDisparoDrone2().add(
                                    new Mobiles(
                                            new Vector2(mundo.getDroneA().getPosicion().x - Mundo.TAMANO_DRON.x + (contadorBalasDispDronA * 40),
                                                    mundo.getDroneA().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronA * 40)),
                                            new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                            new Vector2(60f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO)
                            );

                            contadorBalasDispDronA++;
                        } else {
                            if (mundo.getDroneA().getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO / 2) {
                                mundo.getDisparoDrone2().add(
                                        new Mobiles(
                                                new Vector2(mundo.getDroneA().getPosicion().x + Mundo.TAMANO_DRON.x,
                                                        mundo.getDroneA().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronA * 40)),
                                                new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                                new Vector2(0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO)
                                );
                                mundo.getDisparoDrone2().add(
                                        new Mobiles(
                                                new Vector2(mundo.getDroneA().getPosicion().x - (contadorBalasDispDronA * 40),
                                                        mundo.getDroneA().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronA * 40)),
                                                new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                                new Vector2(-60f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO)
                                );

                                contadorBalasDispDronA++;


                            }
                        }
                    } else {
                        this.accionDroneA = 0;
                        contadorBalasDispDronA = 0;
                    }

                    break;


            }
        }


        //cuando se muera el boss que se muera tambien
    }

    private void controlDroneB(Float delta) {

        mundo.getDroneB().update(delta);
        if (mundo.getDroneB().getPosicion().y
                <= Mundo.TAMANO_MUNDO_ALTO - (Mundo.TAMANO_NODRIZA.y + Mundo.TAMANO_DRON.y + 40)) {
            if (primeraVezDroneB) {
                tiempoAntDronDisp1B = tiempo;
                tiempoAntDronDisp2B = tiempo + 2;
                mundo.getDroneB().setVelocidadeY(0f);
                primeraVezDroneB = false;
            }


            if (mundo.getDroneB().getPosicion().x <= 60/*||mundo.getDroneA().getPosicion().x ==Mundo.TAMANO_MUNDO_ANCHO - 60&&!DAdirecc*/) {
                mundo.getDroneB().setVelocidadeX(90f);
            }
            if (mundo.getDroneB().getPosicion().x >= Mundo.TAMANO_MUNDO_ANCHO - (60 + Mundo.TAMANO_DRON.x)) {
                mundo.getDroneB().setVelocidadeX(-90f);

            }

            if (tiempo - tiempoAntDronDisp1B > 4) {
                accionDroneB = 1; //si pasan dos segundos de la última vez que disparo esto, ponemos la accion a uno
                tiempoAntDronDisp1B = tiempo;
            }

            if (tiempo - tiempoAntDronDisp2B > 4) {
                accionDroneB = 2; //si pasan dos segundos de la última vez que disparo esto, ponemos la accion a uno
                tiempoAntDronDisp2B = tiempo;
            }

            switch (accionDroneB) {
                case 1:
                    if (contadorBalasDispDronB < 3) {
                        mundo.getDisparoDrone1().add(
                                new Mobiles(
                                        new Vector2(mundo.getDroneB().getPosicion().x - Mundo.TAMANO_DRON.x / 2,
                                                mundo.getDroneB().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronB * 40)),
                                        new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                        new Vector2(0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));
                        mundo.getDisparoDrone1().add(new Mobiles(
                                new Vector2(mundo.getDroneB().getPosicion().x - Mundo.TAMANO_DRON.x - (contadorBalasDispDronB * 40),
                                        mundo.getDroneB().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronB * 40)),
                                new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                new Vector2(-60f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));
                        mundo.getDisparoDrone1().add(new Mobiles(
                                new Vector2(mundo.getDroneB().getPosicion().x + Mundo.TAMANO_DRON.x + (contadorBalasDispDronB * 40),
                                        mundo.getDroneB().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronB * 40)),
                                new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                new Vector2(60f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO));

                        contadorBalasDispDronB++;
                    } else {
                        accionDroneB = 0;
                        contadorBalasDispDronB = 0;
                    }
                    break;
                case 2:
                    if (contadorBalasDispDronB < 3) {
                        if (mundo.getDroneB().getPosicion().x < Mundo.TAMANO_MUNDO_ANCHO / 2) {
                            mundo.getDisparoDrone2().add(
                                    new Mobiles(
                                            new Vector2(mundo.getDroneB().getPosicion().x + Mundo.TAMANO_DRON.x / 2,
                                                    mundo.getDroneB().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronB * 40)),
                                            new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                            new Vector2(0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO)
                            );
                            mundo.getDisparoDrone2().add(
                                    new Mobiles(
                                            new Vector2(mundo.getDroneB().getPosicion().x + Mundo.TAMANO_DRON.x + (contadorBalasDispDronB * 40),
                                                    mundo.getDroneB().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronB * 40)),
                                            new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                            new Vector2(60f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO)
                            );

                            contadorBalasDispDronB++;
                        } else {
                            if (mundo.getDroneB().getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO / 2) {
                                mundo.getDisparoDrone2().add(
                                        new Mobiles(
                                                new Vector2(mundo.getDroneB().getPosicion().x - Mundo.TAMANO_DRON.x / 2,
                                                        mundo.getDroneB().getPosicion().y - Mundo.TAMANO_DRON.y + (contadorBalasDispDronB * 40)),
                                                new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                                new Vector2(-0f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO)
                                );
                                mundo.getDisparoDrone2().add(
                                        new Mobiles(
                                                new Vector2(mundo.getDroneB().getPosicion().x - Mundo.TAMANO_DRON.x - (contadorBalasDispDronB * 40),
                                                        mundo.getDroneB().getPosicion().y - Mundo.TAMANO_DRON.y - (contadorBalasDispDronB * 40)),
                                                new Vector2(Mundo.TAMANO_DISPARO_DRONE.x, Mundo.TAMANO_DISPARO_DRONE.y),
                                                new Vector2(-60f, -170f), Mobiles.TIPOS_ELEMENTOS.DISPARO)
                                );

                                contadorBalasDispDronB++;


                            }
                        }
                    } else {
                        this.accionDroneB = 0;
                        contadorBalasDispDronB = 0;
                    }

                    break;


            }
        }
    }

    private void controlFondo(Float delta) {
        for (Mobiles bg : mundo.getBackgrounds()) {
            bg.update(delta);
            if (bg.getPosicion().y + bg.getTamano().y < Mundo.TAMANO_MUNDO_ALTO && c < 1) {
                mundo.getBackgrounds().add(new Mobiles(
                                new Vector2(
                                        bg.getPosicion().x, bg.getPosicion().y + bg.getTamano().y),
                                new Vector2(500, 1600),
                                bg.velocidade_max, Mobiles.TIPOS_ELEMENTOS.BG)
                );
                c++;
            }
            if (bg.getPosicion().y < 0 - bg.getTamano().y && d > 0) {
                mundo.getBackgrounds().removeValue(bg, true);
                c--;
            }
        }
    }

    private void controlDispEnemigo(Float delta) {
        for (Mobiles disp : mundo.getDisparosEnemigos()) {
            disp.update(delta);
            if (disp.getPosicion().y < 0 || disp.getPosicion().x < 0 || disp.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO || disp.getPosicion().y > Mundo.TAMANO_MUNDO_ALTO) {
                mundo.getDisparosHornet().removeValue(disp, true);
            }
        }
    }

    private void controlDispDrone1(Float delta) {
        for (Mobiles disp : mundo.getDisparoDrone1()) {
            disp.update(delta);
            if (disp.getPosicion().y < 0 || disp.getPosicion().x < 0 || disp.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO || disp.getPosicion().y > Mundo.TAMANO_MUNDO_ALTO) {
                mundo.getDisparosHornet().removeValue(disp, true);
            }
        }
    }

    private void controlDispDrone2(Float delta) {
        for (Mobiles disp : mundo.getDisparoDrone2()) {
            disp.update(delta);
            if (disp.getPosicion().y < 0 || disp.getPosicion().x < 0 || disp.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO || disp.getPosicion().y > Mundo.TAMANO_MUNDO_ALTO) {
                mundo.getDisparosHornet().removeValue(disp, true);
            }
        }
    }

    private void disparoPlayer(Float delta) {

        for (Mobiles disp : mundo.getDisparosHornet()) {
            disp.update(delta);
            if (disp.getPosicion().y >= Mundo.TAMANO_MUNDO_ALTO || disp.getPosicion().x < 0 || disp.getPosicion().x > Mundo.TAMANO_MUNDO_ANCHO) {
                mundo.getDisparosHornet().removeValue(disp, true);
            }

        }
        if (player.isDisparo() && !retardo) {
            mundo.getDisparosHornet().add((new Mobiles(
                    new Vector2(
                            player.getPosicion().x + Mundo.TAMANO_HORNET.x / 2 - Mundo.TAMANO_DISPARO.x / 2,
                            player.getPosicion().y + Mundo.TAMANO_HORNET.y - Mundo.TAMANO_DISPARO.y - Mundo.TAMANO_DISPARO.y),
                    new Vector2(Mundo.TAMANO_DISPARO.x, Mundo.TAMANO_DISPARO.y),
                    new Vector2(0f, 170f),
                    Mobiles.TIPOS_ELEMENTOS.DISPARO)));
            if (keyMap.get(Keys.SHOOT).booleanValue()) {
                keyMap.replace(Keys.SHOOT, true, false);
            }
        }
    }

    private void controlMedioFondo(Float delta) {
        for (Mobiles mg : mundo.getMiddlegrounds()) {
            mg.update(delta);
            if (mg.getPosicion().y < 0 && d < 1) {
                mundo.getMiddlegrounds().add(new Mobiles(new Vector2(mg.getPosicion().x, mg.getPosicion().y + mg.getTamano().y),
                        new Vector2(500, 1600),
                        mg.velocidade_max, Mobiles.TIPOS_ELEMENTOS.MG));
                d++;

            }
            if (mg.getPosicion().y < 0 - mg.getTamano().y && d > 0) {
                mundo.getMiddlegrounds().removeValue(mg, true);
                d--;
            }
        }
    }

    private void controlNubes(Float delta) {
        for (Mobiles fg : mundo.getForegrounds()) {
            fg.update(delta);
            if (fg.getPosicion().y < 0 && e < 1) {
                mundo.getForegrounds().add(new Mobiles(new Vector2(fg.getPosicion().x, fg.getPosicion().y + fg.getTamano().y),
                        new Vector2(500, 1600),
                        fg.velocidade_max, Mobiles.TIPOS_ELEMENTOS.FG));
                e++;

            }
            if (fg.getPosicion().y < 0 - fg.getTamano().y && e > 0) {
                mundo.getForegrounds().removeValue(fg, true);
                e--;
            }
        }
    }


    public void update(float delta) {
        tiempo = TimeUtils.millis() / 1000;
        tiempoAccurate=TimeUtils.millis();
        controlPlayer(delta);
        controlDispTactil(delta);
        controlEnemigoAIzq(delta);
        controlEnemigoADcha(delta);
        controlEnemigoBIzq(delta);
        controlEnemigoBDcha(delta);
        controlBoss(delta);
        controlLifebar(delta);
        controlTorreta(delta);
        controlDroneA(delta);
        controlDispDrone1(delta);
        controlDispDrone2(delta);
        controlDroneB(delta);
        controlFondo(delta);
        controlLaser(delta);
        controlMedioFondo(delta);
        controlNubes(delta);
        disparoPlayer(delta);
        controlImpacto(delta);
        controlDispEnemigo(delta);
        procesarEntradas();

    }

}
