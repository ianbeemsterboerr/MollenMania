package model.Velden;

/**
 * Created by Wessel on 6-6-2017.
 */
public class Normaalveld_Veld implements VeldInterface {


  private int [] positie;

  public Normaalveld_Veld (int x, int y, int z){
    this.positie = new int[]{x,y,z};

  }




  @Override
  public boolean isBezet() {
    return false;
  }


}
