package com.vectexthegame.vectex.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.vectexthegame.vectex.Vectex;
import com.vectexthegame.vectex.sprites.Ball;
import com.vectexthegame.vectex.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_COUNT = 8; //amount of tubes

    private Ball ball; //ball texture
    private Texture bg;
    private Texture line;
    private Texture hud;
    private Sound hit;
    private long score; //score
    private String yourScoreName; //score space
    BitmapFont yourBitmapFontName;

    private Array<Tube> tubes; //object array of tubes

    public PlayState(GameStateManager gsm) {
        super(gsm);
        Save.load();
        ball = new Ball(50, 300);
        cam.setToOrtho(false, Vectex.WIDTH, Vectex.HEIGHT);
        bg = new Texture("bg.png");
        line = new Texture("line.png"); // line in game
        hud = new Texture("hud.png"); // heads up display
        tubes = new Array<Tube>();
        score = 0; //score set to 0
        hit = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
        yourBitmapFontName = new BitmapFont();
        yourScoreName = "0";



        for(int i =0; i < TUBE_COUNT; i++){ //add tubes
            tubes.add(new Tube(i * Tube.TUBE_WIDTH, 0));
        }
    }

    @Override
    protected void handleInput() {//input
        if(Gdx.input.isTouched()) {
                if (Gdx.input.getX() < cam.position.x) {
                    ball.jumpLeft();
                }
                if (Gdx.input.getX() > cam.position.x) {
                    ball.jumpRight();
                }
            }
        }


    @Override
    public void update(float dt) {//update
        handleInput();
        ball.update(dt);
        System.out.println(dt);
        tubes.random().displace(dt);
        for(Tube tube : tubes) {// if collision happens, play hit and jump up
            if(tube.collides(ball.getBounds())) {
                ball.jumpUp();
                hit.play();
                score++;
                yourScoreName = score+"";
            }
            }
            cam.update();
        }



    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(line, 0, 150);
        sb.draw(hud, (Vectex.WIDTH / 2) - (hud.getWidth() / 2), 700);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        yourBitmapFontName.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        yourBitmapFontName.draw(sb, yourScoreName, (Vectex.WIDTH / 2), 750);
        for(Tube tube : tubes) {//draw tubes
            sb.draw(tube.getTube(), tube.getPosTube().x, tube.getPosTube().y);
        }
        sb.end();
        if (ball.getPosition().y< 150){ //end game save score enter EndGameState
            Save.gd.setTentativeScore(score);
            gsm.set(new EndGameState(gsm));
            return;
        }

    }

    @Override
    public void dispose() {//dispose assets
        ball.dispose();
        bg.dispose();
        for(Tube tube : tubes) {
            tube.dispose();
        }
        hit.dispose();
        line.dispose();
        hud.dispose();
    }
}
