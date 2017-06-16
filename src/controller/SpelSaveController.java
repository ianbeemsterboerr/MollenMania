package controller;

import model.Spel_Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Ian on 6/16/2017.
 */
public class SpelSaveController {
    Spel_Model spelModel;

    public SpelSaveController(Spel_Model spelModel) {
        this.spelModel = spelModel;
    }

    public void saveSpel() {
        try {


            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Savefile.dat"));
            out.writeObject(spelModel);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Er is een exception opgetreden.");
        } catch (IOException e) {
            System.out.println("Er is een File I/O exception opgetreden");
        }
    }
}
