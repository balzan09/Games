package com.vectexthegame.vectex.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vectexthegame.vectex.Vectex;

public class EndGameState extends State { //end game state

    private Texture bg;//background
    private Texture box;//box
    private Texture playBtn;//play button
    private Texture leaderbtn; //leaderboard button
    private boolean newHighScore;//high score check
    private char[] newName;// input new name
    private int currentChar;// current char selected
    private BitmapFont gameOverFont; //game over font
    private BitmapFont font; //input font
    private ShapeRenderer sr; //shape renderer

    public EndGameState(GameStateManager gsm) { //intantiations
        super(gsm);
        dispose();
        cam.setToOrtho(false, Vectex.WIDTH, Vectex.HEIGHT);
        bg = new Texture("bg.png");
        box = new Texture("box.png");
        playBtn = new Texture("replaybtn.png");
        leaderbtn = new Texture("leaderbtn.png");
        newHighScore = Save.gd.isHighScore(Save.gd.getTentativeScore());
        sr = new ShapeRenderer();
        if(newHighScore){ // high score check
            newName = new char[] {
               'A', 'A', 'A'
            };
            currentChar = 0;
        }
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Hyperspace Bold.otf")); //generate this font
        gameOverFont = gen.generateFont(32);
        font = gen.generateFont(20);
    }


    @Override
    protected void handleInput() { //input of state
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if(newHighScore) {
                Save.gd.addHighScore(
                        Save.gd.getTentativeScore(),
                        new String(newName)
                );
                Save.save();
            }
            gsm.set(new MenuState(gsm));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(newName[currentChar] == ' ') {
                newName[currentChar] = 'Z';
            }
            else {
                newName[currentChar]--;
                if(newName[currentChar] < 'A') {
                    newName[currentChar] = ' ';
                }
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(newName[currentChar] == ' ') {
                newName[currentChar] = 'A';
            }
            else {
                newName[currentChar]++;
                if(newName[currentChar] > 'Z') {
                    newName[currentChar] = ' ';
                }
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if(currentChar < newName.length - 1) {
                currentChar++;
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if(currentChar > 0) {
                currentChar--;
            }
        }

        if(Gdx.input.justTouched())

    {
        Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());//collision of mouse click with object
            Rectangle textureBounds = new Rectangle((cam.position.x - box.getWidth() /2) + 45  ,  cam.position.y-playBtn.getHeight(), playBtn.getWidth(), playBtn.getHeight());
            Rectangle textureBounds2 = new Rectangle((cam.position.x - box.getWidth() /2) + 255  , cam.position.y- leaderbtn.getHeight(), leaderbtn.getWidth(), leaderbtn.getHeight());
            if (textureBounds.contains(tmp.x, tmp.y)) {
                gsm.set(new PlayState(gsm));
                dispose();
            }
        if (textureBounds2.contains(tmp.x, tmp.y)) {
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
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined); //set orthographic camera to cam
        sb.begin();// spritebatch begin
        sb.draw(bg, 0, 0); //draw the following things onto the camera (viewport)
        sb.draw(box,cam.position.x - box.getWidth() /2 , cam.position.y);
        sb.draw(playBtn,(cam.position.x - box.getWidth() /2) + 45 , cam.position.y+25 );
        sb.draw(leaderbtn, (cam.position.x - box.getWidth() /2) + 255 , cam.position.y+25);
        String s;
        s = "Game Over";
        gameOverFont.draw(sb, s, (Vectex.WIDTH)/2-85,680);
        if(!newHighScore){
            sb.end();
            return;
        }
        s= "New High Score: "+ Save.gd.getTentativeScore();
        font.draw(sb, s, Vectex.WIDTH / 2, 180);
        for(int i = 0; i< newName.length; i++) {
            font.draw(sb, Character.toString(newName[i]),230+14*i,120 );
        }

        sb.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.line(230+14*currentChar,100,244+14*currentChar,100);
        sr.end();
    }

    @Override
    public void dispose() { //dispose of the assets, in order to prevent memory leakages
    }
}
