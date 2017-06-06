package model.Velden;

/**
 * Created by Wessel on 6-6-2017.
 */
public class GoudenSpade_Veld {

    private int[] positie;

    public GoudenSpade_Veld(int x, int y, int z) {
        this.positie = new int[]{x, y, z};
    }

    public static void bepaalWinnaar() {

    }

    public int[] getPositie() {
        return positie;
    }

    public void setPositie(int[] positie) {
        this.positie = positie;
    }

}
