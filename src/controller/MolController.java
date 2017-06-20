package controller;

import model.Speler_Model;
import view.MolKnop;
import model.MolModel;

/**
 * Created by Wessel on 15-6-2017.
 */
public class MolController {

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
}
