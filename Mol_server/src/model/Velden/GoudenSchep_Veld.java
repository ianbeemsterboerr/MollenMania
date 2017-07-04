package model.Velden;

import java.io.Serializable;

/**
 * Is het veld dat, wanneer er een mol op staat, zegt dat de eindconditie van het spel is behaald.
 * Created by Wessel on 6-6-2017.
 */
public class GoudenSchep_Veld implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int[] positie;

    public GoudenSchep_Veld(int x, int y, int z) {
        this.positie = new int[]{x,y,z};
    }

    public int[] getPositie() {
        return positie;
    }



}

