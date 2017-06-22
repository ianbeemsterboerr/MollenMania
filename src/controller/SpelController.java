package controller;

import model.Spel_Model;
import model.Speler_Model;
import model.Velden.VeldKnop;

/**
 * Created by Wessel on 20-6-2017.
 */
public class SpelController {

    MolController molController = new MolController();
    public void Spel (Spel_Model spelModel){
        //spelervolgorde wordt bepaalt
        int spelerAanBeurt = 0;
        //Mollen worden neergezet
        while (spelModel.getSpeler().get(spelerAanBeurt).getMol_list().size() < molController.aantalMollen(spelModel)){

         }
        //Hierin wordt de beurt logica herhaald
        do {


        }
        //zodra iemand op de goudenschep komt wordt dit true en stopt de loop
        while (!spelModel.getSpelbord().getNiveau4().getGoudenSchep().get(0).isEindeSpel());
        //Laat einde scherm zien
    }






}
