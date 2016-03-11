package com.vectexthegame.vectex.states;

import com.badlogic.gdx.Gdx;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Save {//file to load and save in highscores.sav

    public static GameData gd; //instantiate game data class

    //save inside file
    public static void save(){
        try{
            //this is used so that file is saved as binary file,
            //therefore user can't manually edit the scores
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("highscores.sav"));
            out.writeObject(gd); //writing an instance of a class into a file
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    //load from file
    public static void load() {// same as save
        try{
            if(!saveFileExists()){
                init();
                return;
            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("highscores.sav"));
            gd = (GameData) in.readObject();
            in.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public static boolean saveFileExists(){//if file exists return
        File f = new File("highscores.sav");
        return f.exists();
    }

    public static void init(){//initiate the saved game data
        gd = new GameData();
        gd.init();
        save();
    }
}
