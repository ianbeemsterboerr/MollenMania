package model.Velden;

/**
 * Created by Wessel on 6-6-2017.
 */
public class Molshoop_Veld implements VeldInterface{


    private int [] positie;

    public Molshoop_Veld (int x, int y, int z){
        this.positie = new int[]{x,y,z};

    }

    public int[] getPositie() {
        return positie;
    }

    public void setPositie(int[] positie) {
        this.positie = positie;
    }

    @Override
    public boolean isBezet() {
        return false;
    }
}
