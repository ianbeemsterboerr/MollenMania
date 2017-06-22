package controller;

import model.Spel_Model;
import model.Speler_Model;
import model.Velden.VeldKnop;
import view.MolKnop;
import model.MolModel;
import model.Spel_Interface;

import java.rmi.RemoteException;

/**
 * Created by Wessel on 15-6-2017.
 */
public class MolController {
    // In deze method worden de mollen neergezet aan het start van het spel

    //Robert: Spel_Model in Spel_Interface veranderd om errors te voorkomen.
 public Spel_Interface mollenNeerzetten(VeldKnop veldKnop, Spel_Interface spelModel, int spelerIndex) {

     //Robert: heb try/catch toegevoegd om RemoteException Bullshit te voorkomen
     try {
         for (int i = 0; i < spelModel.getSpelbord().getNiveau1().getMolshoop().size(); )
             if (veldKnop.getCoordinaten() == spelModel.getSpelbord().getNiveau1().getMolshoop().get(i).getPositie()){
                 return null;
             }
     } catch (RemoteException e) {
         e.printStackTrace();
     }

     //Robert: heb try/catch toegevoegd om RemoteException Bullshit te voorkomen
     try {
         for(Speler_Model speler : spelModel.getSpeler()){
                for (MolModel mollen : speler.getMol_list()){
                    if (mollen.getCoord() == veldKnop.getCoordinaten()){
                        return null;
                    }
                }
            }
     } catch (RemoteException e) {
         e.printStackTrace();
     }

     //Robert: heb try/catch toegevoegd om RemoteException Bullshit te voorkomen
     try {
         spelModel.getSpeler().get(spelerIndex).getMol_list().add(new MolModel(veldKnop.getCoordinaten()));
     } catch (RemoteException e) {
         e.printStackTrace();
     }

     return spelModel;
    }

// Deze method zal de coordinaten van een mol geupdate worden en worden gereturned. Als een mol niet verplaatst kan worden wordt de huidige positie gereturned.
public MolModel verplaatsMol (MolModel molModel, int [] eindPunt, int ficheNR){
    if (zetGeldig(molModel, eindPunt, ficheNR)){
        molModel.setCoord(eindPunt);
        return molModel;
    }
return molModel;
}

public MolModel selecteerMol(MolKnop molKnop, Speler_Model speler) {
    for(MolModel mol : speler.getMol_list()){
        if (mol == molKnop.getMolModel()){
            return mol;
        }
    }
    return null;
    }

public boolean overMol(MolModel molModel, int [] eindPunt, int ficheNR){
    return false;
}


public boolean zetGeldig(MolModel mol, int [] eindPunt, int ficheNR){
    //bepaal delta Coordinaten:
    int deltaCoord[] = new int[3];
    for (int i = 0; i < eindPunt.length;i++ ){
        deltaCoord[i] = eindPunt[i] - mol.getCoord()[i];
    }
    //Bepaal of Rechte lijn en stappen gelijk aan ficheNR
    if (deltaCoord[0] == 0 || deltaCoord[1] == 0 || deltaCoord[2] == 0) {
        if (deltaCoord[0] == ficheNR || deltaCoord[1] == ficheNR || deltaCoord[2] == ficheNR) {
            if (deltaCoord[0] == (-1 * ficheNR) || deltaCoord[1] == (-1 * -ficheNR) || deltaCoord[2] == (-1 * ficheNR)) {
                return true;
            }
        }

    }
        return false;


}

public int aantalMollen (Spel_Interface spelModel){
    int aantalSpelers = 0;
    try {
        aantalSpelers = spelModel.getSpeler().size();
    } catch (RemoteException e) {
        e.printStackTrace();
    }

    if (aantalSpelers == 2){
        return 10;
    }
    else if (aantalSpelers == 3){
        return 8;
    }
    else {
        return 6;
    }

}

}
