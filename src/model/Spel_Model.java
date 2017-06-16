package model;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Wessel on 15-6-2017.
 */
public class Spel_Model implements Initializable {

    private Playboard_Model spelbord;
    private ArrayList<Speler_Model> spelerModels;
    private int huidigNiveau;
    private ArrayList<Player_Observer> playerObservers;
    private int maxAantalObservers;

    public Spel_Model(Playboard_Model spelbord, ArrayList<Speler_Model> spelerModels, int huidigNiveau, ArrayList<Player_Observer> playerObservers, int maxAantalObservers) {
        this.spelbord = spelbord;
        this.spelerModels = spelerModels;
        this.huidigNiveau = huidigNiveau;
        this.playerObservers = playerObservers;
        this.maxAantalObservers = maxAantalObservers;
    }

    public Niveau_Model switchNiveau(int huidigNiveau, Playboard_Model spelbord){
       if (huidigNiveau == 1 ){
                setHuidigNiveau(huidigNiveau++);
                return spelbord.getNiveau2();
           }
        else if (huidigNiveau == 2) {
           setHuidigNiveau(huidigNiveau++);
           return spelbord.getNiveau3();
        }
        else {
           return spelbord.getNiveau4();
       }
    }

    public void setClickable() {

    }

    public void setUnclickable() {

    }

    public void spelSaven() {

    }

    public void notifyObservers() {

    }

    public Playboard_Model getSpelbord() {
        return spelbord;
    }

    public void setSpelbord(Playboard_Model spelbord) {
        this.spelbord = spelbord;
    }

    public void setSpelbord(ArrayList<Playboard_Model> spelbord) {
        spelbord = spelbord;
    }

    public ArrayList<Speler_Model> getSpeler() {
        return spelerModels;
    }

    public void setSpelers(ArrayList<Speler_Model> spelers) {
        this.spelerModels = spelerModels;
    }

    public int getHuidigNiveau() {
        return huidigNiveau;
    }

    public void setHuidigNiveau(int huidigNiveau) {
        this.huidigNiveau = huidigNiveau;
    }

    public ArrayList<Player_Observer> getPlayerObservers() {
        return playerObservers;
    }

    public void setPlayerObservers(ArrayList<Player_Observer> playerObservers) {
        this.playerObservers = playerObservers;
    }

    public int getMaxAantalObservers() {
        return maxAantalObservers;
    }

    public void setMaxAantalObservers(int maxAantalObservers) {
        this.maxAantalObservers = maxAantalObservers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
