package controller;

import model.Fiche_Model;

import java.util.Random;

/**
 * Created by Wessel on 15-6-2017.
 */
public class Fiche_Controller extends Random {


    public int kiesFiche(Fiche_Model fiche) {
        int getal = this.nextInt(fiche.getGeslotenFiche().size());
        int ficheNR = fiche.getGeslotenFiche().get(getal);
        fiche.getOpenFiche().add(fiche.getGeslotenFiche().get(getal));
        fiche.getGeslotenFiche().remove(getal);
        System.out.println(ficheNR);
        return ficheNR;
    }

    public void fichesCheck(Fiche_Model fiche){
        if(fiche.getGeslotenFiche().size()==0){
            System.out.println("Fiches resetten");
            for(int ficheNr : fiche.getOpenFiche()){
                fiche.getGeslotenFiche().add(ficheNr);
            }
            fiche.getOpenFiche().clear();
        }
        }

    }