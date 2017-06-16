package model;

import javafx.scene.paint.Color;

/**
 * Created by Wessel on 29-5-2017.
 */
public class Mol_Model {
    private int pionID;
    private Color color;
    private int [] coord;


    public Mol_Model(int playerID, int pionID, Color color, int x, int y, int z) {
        this.pionID = pionID;
        this.color = color;
        this.coord = new int[] {x,y,z};
    }


    public int getPionID() {
        return pionID;
    }

    public void setPionID(int pionID) {
        this.pionID = pionID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int[] getCoord() {
        return coord;
    }

    public void setCoord(int[] coord) {
        this.coord = coord;
    }
}
