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
            if (Arrays.equals(veldKnop.getCoordinaten(),playboard_model.getNiveau1().getMolshoop().get(i).getPositie())) {
                System.out.println("een mol mag niet op een molshoop worden geplaatst");
                    return false;
                }

        for (Speler_Model speler : bs_interface.playerList()) {
            for (MolModel mollen : speler.getMol_list()) {
                if (Arrays.equals(mollen.getCoord(), veldKnop.getCoordinaten())) {
                    System.out.println("Al een mol aanwezig");
                    return false;
                }
            }
        }
        return true;
    }



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
                    if (Arrays.equals(beginpunt,mol.getCoord())) {
                        System.out.println("je mag niet over een andere mol heen!");
                        return false;
                    }
                }
        }
        //Bepaal of Rechte lijn en stappen gelijk aan ficheNR
        if (deltaCoord[0] == 0 || deltaCoord[1] == 0 || deltaCoord[2] == 0) {
            if (deltaCoord[0] == ficheNR || deltaCoord[1] == ficheNR || deltaCoord[2] == ficheNR) {
                if (deltaCoord[0] == (-1 * ficheNR) || deltaCoord[1] == (-1 * -ficheNR) || deltaCoord[2] == (-1 * ficheNR)) {
                    System.out.println("Zet geldig");
                    return true;
                }
            }
        }
        System.out.println("geen rechte lijn");
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
    public int bepaalOfMolAanwezig(Speler_Model speler, VeldKnop veldKnop) throws RemoteException {
        for (MolModel mol : speler.getMol_list()) {
            System.out.println("Mol coords: " +mol.printCoord() + " Veld coords " +veldKnop.getX() +" " + veldKnop.getY() +" " +veldKnop.getZ());
            if (Arrays.equals(mol.getCoord(), veldKnop.getCoordinaten())) {
                int molIndex = speler.getMol_list().indexOf(mol);
                System.out.println("de index van de mol is: " +molIndex );
                return molIndex;
            }
        }
        return 42;
    }


    public boolean molshopenBezetCheck(Bordspel_Interface bs_interface)throws RemoteException {
        Playboard_Model playboardModel = new Playboard_Model();
        int huidigNiveau = bs_interface.getHuidigeNiveauIndex();
        Niveau_Model niveauModel = playboardModel.getHuidigNiveau(bs_interface.getHuidigeNiveauIndex());
        int molshoopCounter = 0;

        for (Molshoop_Veld molshoopVeld : niveauModel.getMolshoop()) {
            for (Speler_Model speler : bs_interface.playerList()) {
                for (MolModel molModel : speler.getMol_list()) {
                    if (Arrays.equals(molshoopVeld.getPositie(), molModel.getCoord())) {
                        molshoopCounter++;
                        System.out.println("aantal molshopen bezet: " +molshoopCounter +"/" +niveauModel);
                    }
                }
            }
            System.out.println("aantal molshopen bezet: " +molshoopCounter +"/" +niveauModel.getMolshoop().size());
            if (niveauModel.getMolshoop().size() == molshoopCounter) {
                return true;
            }
        }
        return false;
    }

public boolean bepaalOfWinnaar(Bordspel_Interface bs_interface, Speler_Model spelerModel)throws RemoteException {
        Playboard_Model playboardModel = new Playboard_Model();

        if (bs_interface.getHuidigeNiveauIndex() != 4){
            return false;
        }
        else{
            for (MolModel molModel : spelerModel.getMol_list()){
                if(Arrays.equals(molModel.getCoord(),playboardModel.getNiveau4().getGoudenSchep().get(0).getPositie() ));{
                    return true;
                }
            }
        }
        return false;
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

