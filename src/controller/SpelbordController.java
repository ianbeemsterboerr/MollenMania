package controller;

import model.Niveau_Model;
import model.Playboard_Model;
import model.Spel_Model;
import model.Velden.Molshoop_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.util.ArrayList;

/**
 * Created by Wessel on 16-6-2017.
 */
public class SpelbordController {


    public Niveau_Model switchNiveau(Spel_Model spelModel, int huidigNiveau, Playboard_Model spelbord){
        if (huidigNiveau == 1 ){
           spelModel.setHuidigNiveau(huidigNiveau++);
            return spelbord.getNiveau2();
        }
        else if (huidigNiveau == 2) {
            spelModel.setHuidigNiveau(huidigNiveau++);
            return spelbord.getNiveau3();
        }
        else {
            spelModel.setHuidigNiveau(huidigNiveau++);
            return spelbord.getNiveau4();
            }
        }

    // met deze method worden de velden op een niveau vergeleken met de positie van een pion.
    // Als het veld een molshoop is wordt er een 1 gereturnd
    // als het veld een speciaalveld is wordt er een 2 gereturnd
    // als het veld een goudenschep is wordt een een 3 gereturned
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
        for (int i = 0; i < niveau.get(2).getGoudenSchep().size(); i ++) {
            if (niveau.get(2).getGoudenSchep().get(i).getPositie() == coord){
                return 3;
            }
        }
        return 0;
    }


}


