package model.Velden;

import java.io.Serializable;

import model.Niveau_Model;

/**
 * Is een molshoop.
 * Created by Wessel on 6-6-2017.
 */
public class Molshoop_Veld implements Serializable{
	private static final long serialVersionUID = 1L;
	private int [] positie;

    /**
     * Constructor met de x, y en z-waarden.
     * @param x Coordinaat
     * @param y Coordinaat
     * @param z Coordinaat
     */
    public Molshoop_Veld (int x, int y, int z){
        this.positie = new int[]{x,y,z};
    }

    /**
     * Geeft de positie van zichzelf.
     * @return x, y, z in een Array.
     */
    public int[] getPositie() {
        return positie;
    }

    /**
     * Set de positie van de molshoop.
     * @param positie De positie waarop de molshoop moet komen.
     */

}
