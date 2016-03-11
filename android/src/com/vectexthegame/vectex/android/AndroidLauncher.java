package com.vectexthegame.vectex.android;

import android.os.Bundle; //necessary classes to run an android app.

import com.badlogic.gdx.backends.android.AndroidApplication; //import underlying LIBGDX Android app backend
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration; //import underlying LIBGDX Android app configuration backend
import com.vectexthegame.vectex.Vectex; //import Vectex

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // access the superclass and create a saved instance state
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration(); //access config
		initialize(new Vectex(), config); //create new version of vectex
	}
}
