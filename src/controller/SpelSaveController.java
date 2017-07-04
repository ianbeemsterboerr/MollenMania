package controller;

import model.Spelbord_Model;
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
   Bordspel_Interface bs_interface;


    /**
     * Start een SpelSaveView op waarin de naam wordt gekozen voor het SpelbordModel dat wordt ogeslagen.
     * @param bs_interface Kan het SpelModel object uit de server halen zodat deze kan worden opgeslagen.
     */
    public SpelSaveController(Bordspel_Interface bs_interface) {
        this.bs_interface = bs_interface;
        new SpelSaveView(this);
    }

    /**
     * Zorgt dat er een bestand wordt aangemaakt op de schijf, waarin het SpelbordModel wordt opgeslagen.
     * @param naam De naam waarmee het savebestand op de schijf wordt weggeschreven.
     * @throws ClassNotFoundException wanneer Spelbord_Model en MolModel op de serverside verschillen met de clientside
     */
    public void saveSpel(String naam) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(naam + ".sav"));
            
            Spelbord_Model model = new Spelbord_Model();

            model.setMol_onbord(bs_interface.getMollen());
            model.setBeurtStatus(bs_interface.getBeurtStatus());
            model.setPlayers(bs_interface.playerList());
            model.setBeurtIndex(bs_interface.beurtIndex());
            model.setHuidigeNiveau(bs_interface.getHuidigeNiveauIndex());
            model.setMaxMollen(bs_interface.getMaxMollen());
            model.setHervatStatus(true);

            out.writeObject(model);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Er is een exception opgetreden.");
        } catch (IOException e) {
            System.out.println("Er is een File I/O exception opgetreden");
        }
    }
}