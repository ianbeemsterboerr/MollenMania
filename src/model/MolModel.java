package model;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Created by Wessel on 29-5-2017.
 */
public class MolModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int pionID;
    private Color color;
    private int [] coord;

    public MolModel(){
    	
    }
   
    public MolModel(int id){
        this.pionID = id;
    }

//    public MolModel(int playerID, int pionID, Color color, int x, int y, int z) {
//        this.pionID = pionID;
//        this.color = color;
//        this.coord = new int[] {x,y,z};
//    }

    public MolModel(int[] coord) {
        this.coord = coord;
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
