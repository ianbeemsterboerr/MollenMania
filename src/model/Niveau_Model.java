package model;


import model.Velden.GoudenSchep_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.Normaalveld_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.util.ArrayList;

/**
 * Created by Wessel on 6-6-2017.
 */
public class Niveau_Model  {

    ArrayList<Molshoop_Veld> molshoop;
    ArrayList<Normaalveld_Veld> normaal;
    ArrayList<SpeciaalVeld_Veld> speciaal;

    public Niveau_Model(ArrayList<Molshoop_Veld> molshoop, ArrayList<Normaalveld_Veld> normaal, ArrayList<SpeciaalVeld_Veld> speciaal) {
        this.molshoop = molshoop;
        this.normaal = normaal;
        this.speciaal = speciaal;
    }


    public ArrayList<Molshoop_Veld> getMolshoop() {
        return molshoop;
    }

    public void setMolshoop(ArrayList<Molshoop_Veld> molshoop) {
        this.molshoop = molshoop;
    }

    public ArrayList<Normaalveld_Veld> getNormaal() {
        return normaal;
    }

    public void setNormaal(ArrayList<Normaalveld_Veld> normaal) {
        this.normaal = normaal;
    }

    public ArrayList<SpeciaalVeld_Veld> getSpeciaal() {
        return speciaal;
    }

    public void setSpeciaal(ArrayList<SpeciaalVeld_Veld> speciaal) {
        this.speciaal = speciaal;
    }
}
