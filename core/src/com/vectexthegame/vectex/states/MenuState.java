package com.vectexthegame.vectex.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MenuState extends State{
    private Texture bg; //background
    private Texture playBtn;
    private Texture leaderbtn;
    private Texture logo;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        Save.load(); // load the saved file
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        leaderbtn = new Texture("leaderbtn.png");
        logo = new Texture("VECTEXLOGO-temp.png");
    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched()) {//input
            Vector2 tmp= new Vector2(Gdx.input.getX(),Gdx.input.getY());
            Rectangle textureBounds=new Rectangle( cam.position.x- playBtn.getWidth()/2  ,cam.position.y- playBtn.getHeight()/2 ,playBtn.getWidth(),playBtn.getHeight());
            Rectangle textureBounds2=new Rectangle( cam.position.x - playBtn.getWidth()/2  , cam.position.y + playBtn.getHeight() ,leaderbtn.getWidth(),leaderbtn.getHeight());
            if(textureBounds.contains(tmp.x,tmp.y)){
                gsm.set(new PlayState(gsm));
                dispose();
            }
            if(textureBounds2.contains(tmp.x,tmp.y)){
                gsm.set(new HighScoreState(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }


    @Override
    public void render(SpriteBatch sb) { // draw icons

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2  , cam.position.y - playBtn.getHeight()/2 );
        sb.draw(leaderbtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y - playBtn.getHeight()*2  );
        sb.draw(logo, cam.position.x-100,cam.position.y+ 100);
        sb.end();
    }


    @Override
    public void dispose() { //dispose of assets
        bg.dispose();
        playBtn.dispose();
        leaderbtn.dispose();
    }
}
