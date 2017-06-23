package model.Velden;

import java.io.Serializable;

/**
 * Created by Wessel on 6-6-2017.
 */
public class GoudenSchep_Veld implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] positie;
    private boolean eindeSpel = false;

    public void spelAfgelopen(){
        this.eindeSpel = true;
    }
    public GoudenSchep_Veld(int x, int y, int z) {
        this.positie = new int[]{x,y,z};
    }

    public int[] getPositie() {
        return positie;
    }

    public void setPositie(int[] positie) {
        this.positie = positie;
    }

    public boolean isEindeSpel() {
        return eindeSpel;
    }


}
