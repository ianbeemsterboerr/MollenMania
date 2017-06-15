package model;

import java.util.ArrayList;

/**
 * Created by Wessel on 15-6-2017.
 */
public class Spel_Model {

    private ArrayList<Playboard_Model> spelers;
    private Spelbord_Model spelbord;
    private int huidigNiveau;
    private ArrayList<Player_Observer> playerObservers;
    private int maxAantalObservers;

    public Spel_Model(ArrayList<Playboard_Model> spelers, Spelbord_Model spelbord, int huidigNiveau, ArrayList<Player_Observer> playerObservers, int maxAantalObservers) {
        this.spelers = spelers;
        this.spelbord = spelbord;
        this.huidigNiveau = huidigNiveau;
        this.playerObservers = playerObservers;
        this.maxAantalObservers = maxAantalObservers;
    }

    public void switchNiveau{

    }
    public void setClickable {

    }

    public void setUnclickable {

    }
    public void spelSaven {

    }
    public void notifyObservers{

    }

    public ArrayList<Playboard_Model> getSpelers() {
        return spelers;
    }

    public void setSpelers(ArrayList<Playboard_Model> spelers) {
        this.spelers = spelers;
    }

    public Spelbord_Model getSpelbord() {
        return spelbord;
    }

    public void setSpelbord(Spelbord_Model spelbord) {
        this.spelbord = spelbord;
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
