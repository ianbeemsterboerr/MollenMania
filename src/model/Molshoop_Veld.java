package model;

/**
 * Created by Wessel on 6-6-2017.
 */
public class Molshoop_Veld {

    private int[] positie;
    private boolean isBezet;

    public Molshoop_Veld(int x, int y, int z) {
        this.positie = new int[]{x, y, z};
        this.isBezet = false;
    }


    public static void ZetMolvast() {

    }

    public int[] getPositie() {
        return positie;
    }

    public void setPositie(int[] positie) {
        this.positie = positie;
    }

    public boolean isBezet() {
        return isBezet;
    }

    public void setBezet(boolean bezet) {
        isBezet = bezet;
    }
}
