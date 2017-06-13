package model.Velden;

/**
 * Created by Wessel on 6-6-2017.
 */
public class GoudenSchep_Veld {

    private int[] positie;

    public void spelAfgelopen(){

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


}
