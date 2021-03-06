package model;

import java.io.Serializable;

/**
 * Het model van de mol. bevat alle data die een mol nodig heeft in het spel.
 *
 */
public class MolModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pionID;
    private String kleur;
    private int [] coord;

    /**
     * Maakt een MolModel aan met x, y en z coordinaten.
     *
     * @param coord
     */
    public MolModel(int[] coord, String kleur){
        this.kleur=kleur;
        this.coord=coord;
    }

    /**
     * Geeft het ID van een MolModel.
     *
     * @return integer.
     */
    public int getPionID() {
        return pionID;
    }

    /**
     * Zet de ID van een mol.
     *
     * @param pionID
     */
    public void setPionID(int pionID) {
        this.pionID = pionID;
    }

    /**
     * Geeft de kleur van een MolModel terug.
     *
     * @return kleur van type Color.
     */
    public String getKleur() {
        return kleur;
    }

    /**
     * Geef het MolModel een kleur.
     * @param kleur van type String
     */
    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    /**
     * Geeft de coordinaten van een MolModel terug.
     *
     * @return int[x, y, z]
     */
    public int[] getCoord() {
        return coord;
    }

    /**
     * Set de x, y en z coordinaten van een MolModel.
     *
     * @param coord int[x, y, z]
     */
    public void setCoord(int[] coord) {
        this.coord = coord;
    }

    public String printCoord() {
        String coords = "";
        for (int xyz : this.coord) {
            coords += Integer.toString(xyz) +" ";
        }
        return coords;
    }
}


