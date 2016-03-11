package com.vectexthegame.vectex.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vectexthegame.vectex.Vectex;

public class HighScoreState extends State {//state of leaderboard

    private BitmapFont font;
    private long[] highScores;
    private String[] names;
    private Texture bg;
    private Texture backbtn;

    public HighScoreState(GameStateManager gsm) {
        super(gsm);// use GameStateManager
        dispose();
        cam.setToOrtho(false, Vectex.WIDTH, Vectex.HEIGHT);//set to orthographic instead of perspective camera
        bg = new Texture("bg.png");
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Hyperspace Bold.otf"));//instantiate the following font
        font = gen.generateFont(40);
        Save.load(); //load save file
        highScores = Save.gd.getHighScores(); //get high scores
        names = Save.gd.getNames(); //get names
        backbtn = new Texture("backbtn.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        {//input
            Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());//collision of mouse click with object
            Rectangle textureBounds = new Rectangle(cam.position.x-((Vectex.WIDTH/2)+240)/2 , cam.position.y-400 , backbtn.getWidth(), backbtn.getHeight());
            if (textureBounds.contains(tmp.x, tmp.y)) {
                gsm.set(new MenuState(gsm));
                dispose();
            }
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
        sb.draw(bg, 0, 0);
        String s;
        s="High Scores";
        font.draw(sb, s, (Vectex.WIDTH) / 2 - 110, 700);
        sb.draw( backbtn, (Vectex.WIDTH/2)-240, 700);
        for(int i = 0; i<highScores.length;i++) {//draw high scores table
            s =String.format("%2d. %7s %s", i+1,highScores[i], names[i] );
            font.draw(sb,s,(Vectex.WIDTH )/2 - 150, 600-50*i);
        }

        sb.end();
    }

    @Override
    public void dispose() { //dispose of assets
    }
}
