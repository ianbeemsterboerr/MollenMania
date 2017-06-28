package controller;

import model.*;
import model.Velden.Molshoop_Veld;
import model.Velden.VeldKnop;

import java.lang.management.BufferPoolMXBean;
import java.rmi.Remote;
import java.util.ArrayList;

import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Created by Wessel on 15-6-2017.
 */
public class MolController {

    /**
     * In deze method worden de mollen neergezet aan het start van het spel.
     * @param veldKnop Het veld waar het MolModel in moet worden gezet.
     * @param bs_interface Het spelbord waar op gewerkt wordt.
     * @param playboard_model De container van alle VeldKnoppen.
     * @return true of false.
     * @throws RemoteException Wanneer de connectie tussen de client en de server verbroken wordt.
     */
    public boolean magMolNeerzetten(VeldKnop veldKnop, Bordspel_Interface bs_interface, Playboard_Model playboard_model) throws RemoteException {

        for (int i = 0; i < playboard_model.getNiveau1().getMolshoop().size(); i++ )
            if (veldKnop.getCoordinaten() == playboard_model.getNiveau1().getMolshoop().get(i).getPositie()) {
                    return false;
                }

        for (Speler_Model speler : bs_interface.playerList()) {
            for (MolModel mollen : speler.getMol_list()) {
                if (mollen.getCoord() == veldKnop.getCoordinaten()) {
                    return false;
                }
            }
        }
        return true;
    }

//    public MolModel selecteerMol(MolKnop molKnop, Speler_Model speler) {
//        for (MolModel mol : speler.getMol_list()) {
//            if (mol == molKnop.getMolModel()) {
//                return mol;
//            }
//        }
//        return null;
//    }

    /**
     * Controleert of een zet geldig is.
     *
     * @param bs_interface Het spelbord woor op gewerkt wordt.
     * @param spelerModel Het SpelerModel van welke een mol verplaatst gaat worden.
     *
     * @param molGeselecteerd De mol die verplaatst wordt.
     * @param eindPunt Het coordinaat van de plek waar de mol neergezet gaat worden.
     * @return true wanneer de zet geldig is.
     * @throws RemoteException
     */
    public boolean zetGeldig(Bordspel_Interface bs_interface, Speler_Model spelerModel, MolModel molGeselecteerd, int[] eindPunt) throws RemoteException {
        //als lijst nodig haal deze uit de interface.
        //bepaal delta Coordinaten:
        int deltaCoord[] = new int[3];
        int ficheNR = spelerModel.getFiche_list().getFicheNR();
        int beginpunt[] = molGeselecteerd.getCoord();
        for (int i = 0; i < eindPunt.length; i++) {
            deltaCoord[i] = eindPunt[i] - beginpunt[i];
        }

        //Hierin wordt bepaalt of de mol over een andere mol heen wordt gezet.
        for (int j = 0; j < beginpunt.length; j++) {
            beginpunt[j] = beginpunt[j] + deltaCoord[j] * (1 / ficheNR);
            for (Speler_Model speler : bs_interface.playerList())
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

    /**
     * Geeft het totaal aantal mollen in het spel.
     *
     * @param bs_interface Het bordspel waar op gewerkt wordt.
     * @return de hoeveelheid mollen die op het bord staan, als integer.
     * @throws RemoteException Wanneer de connectie tussen de client en server is verbroken.
     */
    public int aantalMollen(Bordspel_Interface bs_interface) throws RemoteException {
        int aantalSpelers = bs_interface.maxSpelers();
        if (aantalSpelers == 2) {
            return 10;
        } else if (aantalSpelers == 3) {
            return 8;
        } else {
            return 6;
        }

    }

    /**
     * Kijkt op een VeldKnop of er een MolModel aanwezig is.
     *
     * @param speler Het SpelerModel van het MolModel waarvan gecheckt moet worden of er al een MolModel staat.
     * @param veldKnop De VeldKnop waarvan gecheckt moet worden of er een mol staat.
     * @return Het MolModel, als deze aanwezig is.
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public MolModel bepaalOfMolAanwezig(Speler_Model speler, VeldKnop veldKnop) throws RemoteException {
        for (MolModel mol : speler.getMol_list()) {
            if (Arrays.equals(mol.getCoord(), veldKnop.getCoordinaten())) {
                return mol;
            }
        }
        return null;
    }

    /**
     * Laad alle Molshopen op het spelbord.
     * @param buttonArray Alle VeldButtons op het spelbord.
     * @param pm De container van de VeldKnoppen.
     * @param niveau Het niveau wat op dat moment actief is.
     * @throws RemoteException Wanneer de connectie tussen de client en de server verbroken is.
     */
    public void loadMolsHoop2(VeldKnop[] buttonArray, Playboard_Model pm, int niveau) throws RemoteException{
        ArrayList<Molshoop_Veld> molshoop_niveau = pm.getHuidigNiveau(niveau).getMolshoop();

            for(Molshoop_Veld m : molshoop_niveau){
                for(int x = 0; x < buttonArray.length; x++){
                    if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
                        buttonArray[x].setDisable(true);
                        buttonArray[x].setStyle("-fx-background-color: blue;");
                    }
                }
            }
    }

}

