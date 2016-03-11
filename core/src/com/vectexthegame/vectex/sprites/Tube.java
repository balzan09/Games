package com.vectexthegame.vectex.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tube { //class for tube object
    public static final int TUBE_WIDTH = 60; //tube width
    private Texture tube;
    private Vector2 posTube; //tube 3d position
    private Rectangle boundsTop;
    private Vector2 velocity;

    public Tube(float x,float y){
        tube = new Texture("tube.png"); //tube texture
        posTube = new Vector2(x,y);
        boundsTop = new Rectangle(posTube.x, posTube.y, tube.getWidth(), tube.getHeight()); //collision rectangle
        velocity = new Vector2(0, 0);
    }

    public Vector2 getPosTube() {
        return posTube;
    }

    public Texture getTube() {
        return tube;
    }
//tube movement
    public void tubeDown(float dt) {
        velocity.scl(dt);
        posTube.add(0, velocity.y);
        velocity.add(0, -2000);
        if (posTube.y < -300){
            velocity.set(0,1);
        }
    }

    public void tubeUp(float dt) {
        velocity.scl(dt);
        posTube.add(0,velocity.y);
        velocity.add(0,2000);
        if(posTube.y > -20){
            velocity.set(0,-1);
        }
    }

    public void displace(float dt) {
//tube movement bounds
        boundsTop.setPosition(posTube.x,posTube.y);
        if (velocity.y <= 0) {
            tubeDown(dt);
        }
        if (velocity.y > 0){
            tubeUp(dt);
        }
    }
//collision
   public boolean collides(Rectangle player){
        return player.overlaps(boundsTop);
    }

    public void dispose() {
        tube.dispose();
    }
}
