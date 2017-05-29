package model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Wessel on 29-5-2017.
 */
public class Player_Model {

    private String playerName;
    private int playerID;
    private javafx.scene.paint.Color color;
    private Fiche_Model fiche;


    public Player_Model(String playerName, int playerID, Color color, Fiche_Model fiche) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.color = color;
        this.fiche = fiche;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Fiche_Model getFiche() {
        return fiche;
    }

    public void setFiche(Fiche_Model fiche) {
        this.fiche = fiche;
    }
}
