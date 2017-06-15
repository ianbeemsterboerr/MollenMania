package model;

import java.util.ArrayList;

/**
 * Created by Wessel on 15-6-2017.
 */
public class Spel_Model {

    private ArrayList<Playboard_Model> Spelbord;
    private Speler_Model speler;
    private int huidigNiveau;
    private ArrayList<Player_Observer> playerObservers;
    private int maxAantalObservers;

    public Spel_Model(ArrayList<Playboard_Model> spelbord, Speler_Model speler, int huidigNiveau, ArrayList<Player_Observer> playerObservers, int maxAantalObservers) {
        Spelbord = spelbord;
        this.speler = speler;
        this.huidigNiveau = huidigNiveau;
        this.playerObservers = playerObservers;
        this.maxAantalObservers = maxAantalObservers;
    }

    public void switchNiveau(i){

    }
    public void setClickable {

    }

    public void setUnclickable {

    }
    public void spelSaven {

    }
    public void notifyObservers{

    }

    public ArrayList<Playboard_Model> getSpelbord() {
        return Spelbord;
    }

    public void setSpelbord(ArrayList<Playboard_Model> spelbord) {
        Spelbord = spelbord;
    }

    public Speler_Model getSpeler() {
        return speler;
    }

    public void setSpeler(Speler_Model speler) {
        this.speler = speler;
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
}
