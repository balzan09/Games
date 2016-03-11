package com.vectexthegame.vectex.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication; //lwjgl backend import
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration; //lwjgl settings and config
import com.vectexthegame.vectex.Vectex; //import main game file

public class DesktopLauncher { //main method
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= Vectex.WIDTH; //set width
		config.height= Vectex.HEIGHT; //set height
		config.title= Vectex.TITLE; //set title of window
		new LwjglApplication(new Vectex(), config); //create a new Lwjgl project
	}
}
