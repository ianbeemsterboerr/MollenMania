package model;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Created by Wessel on 29-5-2017.
 */
public class MolModel implements Serializable{

	private static final long serialVersionUID = 1L;
    private String kleur;
    private int [] coord;

    /**
     * Robert
     *
     * Heb van kleur een String gemaakt ipv een Color, want anders krijg je marshalling errors met RMI
     * @param coord
     * @param kleur
     */
   public MolModel(int[] coord, String kleur){
       this.kleur=kleur;
       this.coord=coord;
   }

    public String getKleur() {
        return this.kleur;
    }

    public int[] getCoord() {
        return coord;
    }

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
