package controller;

import model.*;
import model.Velden.Molshoop_Veld;
import model.Velden.VeldKnop;
import view.MolKnop;

import java.rmi.Remote;
import java.util.ArrayList;

import java.rmi.RemoteException;

/**
 * Created by Wessel on 15-6-2017.
 */
public class MolController {
    // In deze method worden de mollen neergezet aan het start van het spel

    //Robert: Spel_Model in Spel_Interface veranderd om errors te voorkomen.
    public Spel_Interface mollenNeerzetten(VeldKnop veldKnop, Spel_Interface spelModel, int spelerIndex) throws RemoteException {


        for (int i = 0; i < spelModel.getSpelbord().getNiveau1().getMolshoop().size(); )
            if (veldKnop.getCoordinaten() == spelModel.getSpelbord().getNiveau1().getMolshoop().get(i).getPositie()) {
                return null;
            }

        for (Speler_Model speler : spelModel.getSpeler()) {
            for (MolModel mollen : speler.getMol_list()) {
                if (mollen.getCoord() == veldKnop.getCoordinaten()) {
                    return null;
                }
            }
        }
        spelModel.getSpeler().get(spelerIndex).getMol_list().add(new MolModel(veldKnop.getCoordinaten()));

        return spelModel;
    }

    // Deze method zal de coordinaten van een mol geupdate worden en worden gereturned. Als een mol niet verplaatst kan worden wordt de huidige positie gereturned.
    public Spelbord_Model verplaatsMol(Spelbord_Model sm, int[] eindPunt, int ficheNR, int molIndex) throws RemoteException{
        if (zetGeldig(sm, eindPunt, ficheNR, molIndex)) {
            sm.getPlayers().get(sm.beurtIndex()).getMol_list().get(molIndex).setCoord(eindPunt);
            return sm;
        }
        return null;
    }

    public MolModel selecteerMol(MolKnop molKnop, Speler_Model speler) {
        for (MolModel mol : speler.getMol_list()) {
            if (mol == molKnop.getMolModel()) {
                return mol;
            }
        }
        return null;
    }

    public boolean overMol(MolModel molModel, int[] eindPunt, int ficheNR) {
        return false;
    }


       public boolean zetGeldig(Spelbord_Model sm, int[] eindPunt, int ficheNR, int molIndex) throws RemoteException{
        //bepaal delta Coordinaten:
        int deltaCoord[] = new int[3];
        int beginpunt[] = sm.getPlayers().get(sm.beurtIndex()).getMol_list().get(molIndex).getCoord();
        for (int i = 0; i < eindPunt.length; i++) {
            deltaCoord[i] = eindPunt[i] - beginpunt[i];
        }

        //Hierin wordt bepaalt of de mol over een andere mol heen wordt gezet.
        for (int j = 0; j < beginpunt.length; j++) {
            beginpunt[j] = beginpunt[j] + deltaCoord[j] * (1 / ficheNR);
            for (Speler_Model speler : sm.getPlayers())
                for (MolModel mol : speler.getMol_list()) {
                    if (beginpunt == mol.getCoord()) {
                        return false;
                    }
                }
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

public int aantalMollen (Spelbord_Model spelModel) throws RemoteException{
    int  aantalSpelers = spelModel.getSpelers().size();
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
