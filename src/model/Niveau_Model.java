package model;

import model.Velden.GoudenSpade_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.Normaalveld_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Wessel on 6-6-2017.
 */
public class Niveau_Model  {

    ArrayList<Molshoop_Veld> molshoop;
    ArrayList<Normaalveld_Veld> normaal;
    ArrayList<SpeciaalVeld_Veld> speciaal;




    public int bepaalVeldSoort(ArrayList<Molshoop_Veld> molshoop, ArrayList <SpeciaalVeld_Veld> speciaal, int coord[]){
        for (int i = 0; i < molshoop.size(); i ++) {
            if (molshoop.get(i).getPositie() == coord){
                return 1;
            }

         }
        for (int i = 0; i < speciaal.size(); i++){
            if (speciaal.get(i).getPositie() == coord) {
                return 2;
            }

        }
        return 0;
    }

    public Niveau_Model(ArrayList<Molshoop_Veld> molshoop, ArrayList<SpeciaalVeld_Veld> speciaal) {
        this.molshoop = molshoop;
        this.speciaal = speciaal;
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

    public ArrayList<Molshoop_Veld> getMolshoop() {
        return molshoop;
    }

    public void setMolshoop(ArrayList<Molshoop_Veld> molshoop) {
        this.molshoop = molshoop;
    }

}

