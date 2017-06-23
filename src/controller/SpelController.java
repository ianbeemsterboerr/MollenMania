package controller;

import model.Spel_Model;
import model.Speler_Model;
import model.Velden.VeldKnop;

import java.rmi.RemoteException;

/**
 * Created by Wessel on 20-6-2017.
 */
public class SpelController {

    public MolController molController = new MolController();
    public void Spel (Spel_Model spelModel){
        //spelervolgorde wordt bepaalt
        int spelerAanBeurt = 0;
        //Mollen worden neergezet

        try {
            while (spelModel.getSpeler().get(spelerAanBeurt).getMol_list().size() < molController.aantalMollen(spelModel)){

             }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //Hierin wordt de beurt logica herhaald
        try {
            do {


            }
            //zodra iemand op de goudenschep komt wordt dit true en stopt de loop
            while (!spelModel.getSpelbord().getNiveau4().getGoudenSchep().get(0).isEindeSpel());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //Laat einde scherm zien
    }

//public void maakVeldknoppen (VeldKnop[] veldknoppen, Spel_Model spelModel){
//    for(int i = 0; i < veldknoppen.length; i++ ){
//        final VeldKnop knop = veldknoppen[i];
//        veldknoppen[i].setOnAction(e -> {
//            new MolController().mollenNeerzetten(knop,spelModel,1);
//
//        });
//    }
//}




}
