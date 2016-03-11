package com.vectexthegame.vectex.states; //state model class

import com.badlogic.gdx.Gdx; //libgdx import
import com.badlogic.gdx.graphics.OrthographicCamera; //orthographic camera import
import com.badlogic.gdx.graphics.g2d.SpriteBatch; //sprite batch import
import com.badlogic.gdx.math.Vector3; //3d vector import ( I may have used vector 2D, but I wanted to test using vector 3D)
import com.vectexthegame.vectex.Vectex; //import vectex class

public abstract class State extends Vectex {
    //instantiate
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm) {
        this.gsm = gsm; //refer to this gsm
        cam = new OrthographicCamera(Vectex.WIDTH*(Gdx.graphics.getWidth()/WIDTH),Vectex.HEIGHT*(Gdx.graphics.getHeight() / HEIGHT)); // set orthographic camera
        mouse = new Vector3(); //mouse coordinates

    }

    protected abstract void handleInput();
    public abstract void update (float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
