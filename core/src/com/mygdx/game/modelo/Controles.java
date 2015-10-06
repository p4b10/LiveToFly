package com.mygdx.game.modelo;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by dam204 on 20/02/2015.
 */
public class Controles {

    public final static Rectangle CONTROL = new Rectangle(24, 28, 80, 80);

    public final static Rectangle CONTROL_PAUSE = new Rectangle(35,115,30,30);
   /* public final static Rectangle CONTROL_SAIR = new Rectangle(30,220,20,20);*/
    public final static Rectangle CONTROL_DISPARO=new Rectangle(250,0,250,800);



    public static Rectangle getRectanguloFrechaEsquerda() {
        return new Rectangle(Controles.CONTROL.x, Controles.CONTROL.y
                + Controles.CONTROL.height/ 3, Controles.CONTROL.width / 2,
                Controles.CONTROL.height / 3);
    }

    public static Rectangle getRectanguloFrechaDiagonalSupDereita() {
        return new Rectangle(Controles.CONTROL.x+Controles.CONTROL.width*2/3, Controles.CONTROL.y
                + Controles.CONTROL.height*2 / 3, Controles.CONTROL.width / 3,
                Controles.CONTROL.height / 3);
    }


    public static Rectangle getRectanguloFrechaDiagonalInfDereita() {
        return new Rectangle(Controles.CONTROL.x+Controles.CONTROL.width*2/3, Controles.CONTROL.y
               , Controles.CONTROL.width / 3,
                Controles.CONTROL.height / 3);
    }

    public static Rectangle getRectanguloFrechaDiagonalSupEsquerda() {
        return new Rectangle(Controles.CONTROL.x, Controles.CONTROL.y
                +Controles.CONTROL.height* 2/ 3, Controles.CONTROL.width / 3,
                Controles.CONTROL.height / 3);
    }

    public static Rectangle getRectanguloFrechaDiagonalInfEsquerda() {
        return new Rectangle(Controles.CONTROL.x, Controles.CONTROL.y
                , Controles.CONTROL.width / 3,
                Controles.CONTROL.height / 3);
    }

    public static Rectangle getControlDisparo() {
        return CONTROL_DISPARO;
    }
    public static Rectangle getRectanguloFrechaDereita() {
        return new Rectangle(Controles.CONTROL.x + Controles.CONTROL.width *2/ 3,
                Controles.CONTROL.y + Controles.CONTROL.height / 3,
                Controles.CONTROL.width / 3, Controles.CONTROL.height / 3);
    }

    public static Rectangle getRectanguloFrechaArriba() {
        return new Rectangle(Controles.CONTROL.x+Controles.CONTROL.x*2/3, Controles.CONTROL.y
                + Controles.CONTROL.height * 2 / 3, Controles.CONTROL.width/3,
                Controles.CONTROL.height / 3);

    }

    public static Rectangle getRectanguloFrechaAbaixo() {
        return new Rectangle(Controles.CONTROL.x+Controles.CONTROL.x*2/3, Controles.CONTROL.y,
                Controles.CONTROL.width/3, Controles.CONTROL.height / 3);
    }


}
