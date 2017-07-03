package controller;

import com.sun.org.apache.xpath.internal.SourceTree;
import model.Spelbord_Model;
import view.SpelSaveView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.Spelbord_Model;

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
<<<<<<< HEAD
            Spelbord_Model sbm = new Spelbord_Model();
            sbm.setPlayers(bs_interface.playerList());
            
            out.writeObject(sbm);
=======
            Spelbord_Model model = new Spelbord_Model(bs_interface.maxSpelers());

            model.setMol_onbord(bs_interface.getMollen());
            model.setBeurtStatus(bs_interface.getBeurtStatus());
            model.setPlayers(bs_interface.playerList());
            model.setBeurtIndex(bs_interface.beurtIndex());
            model.setPlayerModel(bs_interface.getPlayboardModel());
            model.setHuidigeNiveau(bs_interface.getHuidigeNiveauIndex());
            model.setMaxMollen(bs_interface.getMaxMollen());

            out.writeObject(model);
>>>>>>> 0be9c089c06e99eef65462ad94e9dd0927e06d52
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Er is een exception opgetreden.");
        } catch (IOException e) {
            System.out.println("Er is een File I/O exception opgetreden");
        }
    }
}