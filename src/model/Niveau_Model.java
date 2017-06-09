package model;


import model.Velden.Molshoop_Veld;
import model.Velden.Normaalveld_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.util.ArrayList;

/**
 * Created by Wessel on 6-6-2017.
 */
public class Niveau_Model implements Molshoop_Veld,Normaalveld_Veld,SpeciaalVeld_Veld {


    private int positie = new int [];
    private



    @Override
    public void opSpecVeld() {

    }

    @Override
    public boolean isBezet() {
        return false;
    }
}
}
