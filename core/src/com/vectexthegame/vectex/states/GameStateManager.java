package com.vectexthegame.vectex.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch; // import spritebatch
import java.util.Stack; //last in first out stack of objects

public class GameStateManager { //State manager

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>(); //create object array of states
    }

    public void push(State state) {//pushing a state onto the viewport
        states.push(state); //put in stack of states
    }

    public void pop(){//using a state
        states.pop();
    }

    public void set(State state) {//setting a state
        states.pop();
        states.push(state);
    }

    public void update(float dt){//update the screen
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){// render at FPS
        states.peek().render(sb);
    }
}
