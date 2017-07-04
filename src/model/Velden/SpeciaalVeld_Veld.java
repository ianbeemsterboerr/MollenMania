package model.Velden;

import java.io.Serializable;

/**
 * Is een speciaalveld. Als hier een mol op eindigt na een beurt, mag deze nog eens een fiche omdraaien.
 * Created by Wessel on 6-6-2017.
 */
public class SpeciaalVeld_Veld implements Serializable{
	private static final long serialVersionUID = 1L;
	private int [] positie;

    /**
     * Maakt een speciaalveld aan met x, y en z coordinaat.
     * @param x Coordinaat
     * @param y Coordinaat
     * @param z Coordinaat
     */
    public SpeciaalVeld_Veld (int x, int y, int z){
        this.positie = new int[]{x,y,z};
    }

    /**
     * Geeft de positie van het veld terug.
     * @return De positie als int array.
     */
    public int[] getPositie() {
        return positie;
    }

    /**
     * Set de positie van de het veld.
     * @param positie de x, y en z coordinaat.
     */

}
