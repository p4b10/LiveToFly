package com.mygdx.game.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.game.modelo.Hornet;

/**
 * Created by usuario on 25/02/2015.
 */
public class TactilPad extends Touchpad {

    private static TouchpadStyle touchpadStyle;
    private static Skin touchpadSkin;
    private Hornet player;//?

    public TactilPad(float positionX,float positionY) {
        super(20, TactilPad.getTouchPadStyle());
        setBounds(positionX, positionY, 133, 133);
    }

    private static TouchpadStyle getTouchPadStyle(){
        touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture(Gdx.files.internal("grafico/hud/joystickfuera.png")));
        touchpadSkin.add("touchKnob", new Texture(Gdx.files.internal("grafico/hud/joystickDentro.png")));

        touchpadStyle = new TouchpadStyle();
        touchpadStyle.background = touchpadSkin.getDrawable("touchBackground");
        touchpadStyle.knob = touchpadSkin.getDrawable("touchKnob");
        return touchpadStyle;
    }

    public Hornet getPlayer() {
        return player;
    }

    public void setPlayer(Hornet player) {
        this.player = player;
    }

    public void act (float delta) {
        super.act(delta);
        if(isTouched()){//?//?//?
            player.setVelocidade(
                    new Vector2(
                            50*getKnobX(),50*getKnobY()
                    ));
            player.setDisparo(true);
        }
    }
}
