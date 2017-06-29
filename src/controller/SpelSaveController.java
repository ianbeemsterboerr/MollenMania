package controller;

import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.*;
import view.SpelSaveView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Ian Beemsterboer.
 * Heeft de verantwoordelijkheid om het spel op te slaan.
 */
public class SpelSaveController {
    Spelbord_Model spelModel;


    /**
     * Start een SpelSaveView op waarin de naam wordt gekozen voor het SpelbordModel dat wordt ogeslagen.
     * @param spelModel Het Spel_Model object dat opgeslagen moet worden.
     */
    public SpelSaveController(Spelbord_Model spelModel) {
        this.spelModel = spelModel;
        SpelSaveView view = new SpelSaveView(this);
    }

    /**
     * Zorgt dat er een bestand wordt aangemaakt op de schijf, waarin het SpelbordModel wordt opgeslagen.
     * @param naam  De naam waarmee het savebestand op de schijf wordt weggeschreven.
     */
    public void saveSpel(String naam) {
        try {


            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(naam + ".dat"));
            out.writeObject(spelModel);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Er is een exception opgetreden.");
        } catch (IOException e) {
            System.out.println("Er is een File I/O exception opgetreden");
        }
    }
}
