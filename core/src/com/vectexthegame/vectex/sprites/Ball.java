package com.vectexthegame.vectex.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.vectexthegame.vectex.Vectex;

public class Ball { //class for ball object
    private static final int GRAVITY = -70; //value of gravity
    private Vector3 position; //3d position
    private Vector3 velocity; //3d velocity
    private Rectangle bounds; //rectangle bounds
    private Texture ball; //ball texture

    public Ball(int x, int y) { //Ball constructor
        position = new Vector3(x, y, 0); //initial position of ball
        velocity = new Vector3(0, 0, 0); //initial velocity
        ball = new Texture("ball.png"); //ball texture
        bounds = new Rectangle(x, y, ball.getWidth(), ball.getHeight()); //collision rectangle
    }

    public void update(float dt){ //Ball Movement bounds and update.
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(velocity.x , velocity.y, 0);
        if(position.y > Vectex.HEIGHT - ball.getHeight()) {
            position.y = Vectex.HEIGHT - ball.getHeight();
            position.y--;
            velocity.y = -2;
        }
        if(position.x < 0) {
            position.x = 0;
            position.x++;
            velocity.x = 3;
        }
        if(position.x > Vectex.WIDTH - ball.getWidth()) {
            position.x = Vectex.WIDTH - ball.getWidth();
            position.x--;
            velocity.x = -3;
        }
        bounds.setPosition(position.x, position.y);
        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return ball;
    }

    public void jumpLeft(){
        velocity.x = -400;
    }

    public void jumpRight(){
        velocity.x = 400;
    }

    public void jumpUp(){
        velocity.y = 1800;
        velocity.x = 0;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void dispose() {
        ball.dispose();
    }

}
