package model;

import model.Velden.GoudenSpade_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.Normaalveld_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.util.ArrayList;

/**
 * Created by Wessel on 6-6-2017.
 */
public class Niveau_Model  {

    ArrayList<Molshoop_Veld> molshopen = new ArrayList<Molshoop_Veld>();
    ArrayList<Normaalveld_Veld> normaal = new ArrayList<Normaalveld_Veld>();
    ArrayList<SpeciaalVeld_Veld> speciaal = new ArrayList<SpeciaalVeld_Veld>();


    public Niveau_Model(ArrayList<Molshoop_Veld> molshopen, ArrayList<Normaalveld_Veld> normaal, ArrayList<SpeciaalVeld_Veld> speciaal) {
        this.molshopen = molshopen;
        this.normaal = normaal;
        this.speciaal = speciaal;
    }
}
