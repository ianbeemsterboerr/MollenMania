package controller;

import model.Fiche_Model;

import java.util.Random;

/**
 * Bestuurt wat er met de fiches gebeurt.
 * Created by Wessel on 15-6-2017.
 */
public class Fiche_Controller extends Random {

    /**
     * Pakt een fiche met een waarde van 1, 2, 3 of 4. Is random maar er zijn 2 fiches van 2 en 3.
     * @param fiche De lijst van fiches waarvan er een omgedraaid moet worden.
     */
    public void kiesFiche(Fiche_Model fiche) {
        int getal = this.nextInt(fiche.getGeslotenFiche().size());
        fiche.setFicheNR(fiche.getGeslotenFiche().get(getal));
        fiche.getOpenFiche().add(fiche.getGeslotenFiche().get(getal));
        fiche.getGeslotenFiche().remove(getal);
        System.out.println(fiche.getFicheNR());
    }

    /**
     * Checkt of alle fiches omgedraaid zijn. Wanneer ze allemaal omgedraaid zijn worden ze ge reset.
     * @param fiche De lijst van fiches die omgedraaid moeten worden.
     */
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