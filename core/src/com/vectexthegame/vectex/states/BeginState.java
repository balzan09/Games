package com.vectexthegame.vectex.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vectexthegame.vectex.Vectex;

public class BeginState extends State {

    private Sound sound;
    private Texture splash;

    public BeginState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Vectex.WIDTH, Vectex.HEIGHT);
        sound = Gdx.audio.newSound(Gdx.files.internal("music.mp3")); //assign audio file
        sound.loop(); //loop music
        splash = new Texture("splash.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();//begin sprite batch
        sb.draw(splash, 0, 0);
        sb.end();
    }

    @Override
    public void dispose() {
        splash.dispose();
    }
}
