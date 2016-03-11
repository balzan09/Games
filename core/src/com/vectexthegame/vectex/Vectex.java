package com.vectexthegame.vectex;

import com.badlogic.gdx.ApplicationAdapter; //when application is paused
import com.badlogic.gdx.Gdx; //libgdx main classes
import com.badlogic.gdx.graphics.Camera; //camera import (may be orthographic or perspective)
import com.badlogic.gdx.graphics.GL20; // import lwjgl library 20
import com.badlogic.gdx.graphics.OrthographicCamera; //orthographic camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch; //import code for spritebatch creation
import com.vectexthegame.vectex.states.BeginState; //import begin state
import com.vectexthegame.vectex.states.GameStateManager; //import game state manager

public class Vectex extends ApplicationAdapter {//main game class
	public static final int WIDTH = 480; //width of game
	public static final int HEIGHT = 800;//height of game

	public static final String TITLE = "Vectex";//title
	//instantiate
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Camera camera;



	@Override
	public void create() {
		camera = new OrthographicCamera(WIDTH*(Gdx.graphics.getWidth()/WIDTH), HEIGHT*(Gdx.graphics.getHeight()/HEIGHT)); //create ortho cam
		gsm = new GameStateManager(); //create gsm
		batch = new SpriteBatch(); //create spirtebatch
		Gdx.gl.glClearColor(0, 0, 0, 1); // put black colour as background
		gsm.push(new BeginState(gsm));
	}

	public void render() {
		camera.update();//update orthocam
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//clear colour
		gsm.update(Gdx.graphics.getDeltaTime());//update according to delta time
		gsm.render(batch);//render spritebatch
	}

}


