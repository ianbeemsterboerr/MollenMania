package controller;

import model.Mol_Model;

/**
 * Created by Wessel on 15-6-2017.
 */
public class MolController {

public Mol_Model verplaatsMol (Mol_Model molModel, int [] eindpunt, int ficheNR){
    if (zetGeldig(molModel, eindpunt, ficheNR)){
        molModel.setCoord(eindpunt);
        return molModel;
    }
return molModel;
}

public boolean zetGeldig(Mol_Model mol, int [] eindPunt, int ficheNR){
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
