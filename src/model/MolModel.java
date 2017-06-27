package model;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Het model van de mol. bevat alle data die een mol nodig heeft in het spel.
 * Created by Wessel on 29-5-2017.
 */
public class MolModel implements Serializable{
    private static final long serialVersionUID = 1L;
    private int pionID;
    private Color color;
    private int [] coord;

    public MolModel(){
    	
    }

    /**
     * Maakt een MolModel aan met x, y en z coordinaten.
     * @param coord
     */
    public MolModel(int[] coord) {
        this.coord = coord;
    }

    /**
     * Geeft het ID van een MolModel.
     * @return integer.
     */
    public int getPionID() {
        return pionID;
    }

    /**
     * Zet de ID van een mol.
     * @param pionID
     */
    public void setPionID(int pionID) {
        this.pionID = pionID;
    }

    /**
     * Geeft de kleur van een MolModel terug.
     * @return kleur van type Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Geef het MolModel een kleur.
     * @param color van type Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Geeft de coordinaten van een MolModel terug.
     * @return int[x, y, z]
     */
    public int[] getCoord() {
        return coord;
    }

    /**
     * Set de x, y en z coordinaten van een MolModel.
     * @param coord int[x, y, z]
     */
    public void setCoord(int[] coord) {
        this.coord = coord;
    }

}
