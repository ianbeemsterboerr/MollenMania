package model;

import model.Velden.GoudenSchep_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.SpeciaalVeld_Veld;


import java.util.ArrayList;

/**
 * Created by Wessel on 29-5-2017.
 */

public class Playboard_Model  {

    Niveau_Model niveau1 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
    Niveau_Model niveau2 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
    Niveau_Model niveau3 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
    Niveau_Model niveau4 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());


    // met deze method worden de velden op een niveau vergeleken met de positie van een pion.
    // Als het veld een molshoop is wordt er een 1 gereturnd
    // als het veld een speciaalveld is wordt er een 2 gereturnd
    // als het geen speciaalveld of molshoop is, is het een normaal veld en wordt er een 0 gereturned.
    public int bepaalVeldSoort(ArrayList<Niveau_Model> niveau, int coord[]){
        for (int i = 0; i < niveau.get(0).getMolshoop().size(); i ++) {
            if (niveau.get(0).getMolshoop().get(i).getPositie() == coord){
                return 1;
            }
        }
        for (int i = 0; i < niveau.get(1).getSpeciaal().size(); i ++) {
            if (niveau.get(1).getSpeciaal().get(i).getPositie() == coord){
                return 2;
            }
        }
        return 0;
    }

    public Niveau_Model getNiveau1() {
        return niveau1;
    }

    public Niveau_Model getNiveau2() {
        return niveau2;
    }

    public Niveau_Model getNiveau3() {
        return niveau3;
    }

    public Playboard_Model(){
        //Niveau 1
        niveau1.getMolshoop().add(new Molshoop_Veld(3,1,-4));
        niveau1.getMolshoop().add(new Molshoop_Veld(4,-1,-3));
        niveau1.getMolshoop().add(new Molshoop_Veld(-1,4,3));
        niveau1.getMolshoop().add(new Molshoop_Veld(1,1,-2));
        niveau1.getMolshoop().add(new Molshoop_Veld(-1,2,-1));
        niveau1.getMolshoop().add(new Molshoop_Veld(2,-2,0));
        niveau1.getMolshoop().add(new Molshoop_Veld(0,0,0));
        niveau1.getMolshoop().add(new Molshoop_Veld(1,-2,1));
        niveau1.getMolshoop().add(new Molshoop_Veld(-4,3,1));
        niveau1.getMolshoop().add(new Molshoop_Veld(2,-4,2));
        niveau1.getMolshoop().add(new Molshoop_Veld(-1,-1,2));
        niveau1.getMolshoop().add(new Molshoop_Veld(-4,1,3));
        niveau1.getMolshoop().add(new Molshoop_Veld(-2,-2,4));

        //Niveau 2
        niveau2.getMolshoop().add(new Molshoop_Veld(4,0,-4));
        niveau2.getMolshoop().add(new Molshoop_Veld(1,2,-3));
        niveau2.getMolshoop().add(new Molshoop_Veld(3,-1,-2));
        niveau2.getMolshoop().add(new Molshoop_Veld(-2,3,-1));
        niveau2.getMolshoop().add(new Molshoop_Veld(3,-3,0));
        niveau2.getMolshoop().add(new Molshoop_Veld(4,-4,0));
        niveau2.getMolshoop().add(new Molshoop_Veld(-3,1,2));
        niveau2.getMolshoop().add(new Molshoop_Veld(-1,-2,3));
        niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(0,4,-4));
        niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(4,-4,0));
        niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(0,-1,1));
        niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(-3,0,3));

        //Niveau 3
        niveau3.getMolshoop().add(new Molshoop_Veld(1,3,-4));
        niveau3.getMolshoop().add(new Molshoop_Veld(2,-1,-1));
        niveau3.getMolshoop().add(new Molshoop_Veld(-4,2,2));
        niveau3.getMolshoop().add(new Molshoop_Veld(1,-4,3));
        niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(4,-2,-2));
        niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(-2,4,-2));
        niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(-1,1,0));
        niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(-1,-3,4));

        //Niveau 4
        niveau4.getGoudenSchep().add(new GoudenSchep_Veld(0,0,0));
        niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(4,0,-4));
        niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(-2,3,-1));
        niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(2,-3,1));
        niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(-4,0,4));











    }



}