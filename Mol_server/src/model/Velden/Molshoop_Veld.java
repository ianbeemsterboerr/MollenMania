package model.Velden;


import java.io.Serializable;

/**
 * Is een molshoop.
 * Created by Wessel on 6-6-2017.
 */
public class Molshoop_Veld implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int [] positie;

    public Molshoop_Veld (int x, int y, int z){
        this.positie = new int[]{x,y,z};
    }


    public int[] getPositie() {
        return positie;
    }


}
