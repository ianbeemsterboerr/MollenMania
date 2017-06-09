package model.Velden;

/**
 * Created by Wessel on 6-6-2017.
 */
public class SpeciaalVeld_Veld implements VeldInterface{


    private int [] positie;

    public SpeciaalVeld_Veld (int x, int y, int z){
        this.positie = new int[]{x,y,z};
    }

    public void opSpeciaalVeld(){

    }

    @Override
    public boolean isBezet() {
        return false;
    }
}
