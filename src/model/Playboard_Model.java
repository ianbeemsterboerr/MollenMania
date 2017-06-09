package model;


import model.Velden.Molshoop_Veld;
import model.Velden.Normaalveld_Veld;

import java.util.ArrayList;

/**
 * Created by Wessel on 29-5-2017.
 */

public class Playboard_Model  {

    Niveau_Model niveau1 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
    Niveau_Model niveau2 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
    Niveau_Model niveau3 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());


    public Playboard_Model(){

            niveau1.getMolshoop().add(new Molshoop_Veld(0,0,0));
            

            for (int i = -4; i < 4; i++ ){
                for(int j = -4; i < 4; i++){
                    for( int n = -4; n < 4; i++){
                        niveau1.getNormaal().add(new Normaalveld_Veld(i,j,n));
                        niveau2.getNormaal().add(new Normaalveld_Veld(i,j,n));
                        niveau3.getNormaal().add(new Normaalveld_Veld(i,j,n));
                    }
                }

        }



    }

}