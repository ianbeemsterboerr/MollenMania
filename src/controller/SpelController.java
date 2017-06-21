package controller;

import model.Spel_Model;
import model.Speler_Model;

/**
 * Created by Wessel on 20-6-2017.
 */
public class SpelController {


    public void Spel (Spel_Model spelModel){

        //Hierin wordt de beurt logica herhaald
        do {


        }
        //zodra iemand op de goudenschep komt wordt dit true en stopt de loop
        while (!spelModel.getSpelbord().getNiveau4().getGoudenSchep().get(0).isEindeSpel());
        //Laat einde scherm zien
    }






}
