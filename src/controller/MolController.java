package controller;

import model.*;
import model.Velden.Molshoop_Veld;
import model.Velden.VeldKnop;
import view.MolKnop;
import view.SpelbordView;

import java.lang.management.BufferPoolMXBean;
import java.rmi.Remote;
import java.util.ArrayList;

import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Bestuurt alle mollen die in het spel zitten.
 * Created by Wessel on 15-6-2017.
 */
public class MolController {


    /**
     * Geeft terug of er op de gegeven VeldKnop een mol kan worden geplaatst.
     *
     * @param veldKnop Het veld in het spelbord waarvan wordt gevraagd of er een mol kan worden geplaatst.
     * @param bs_interface Het bordspel waarop gewerkt wordt.
     * @param playboard_model De container van alle velden, mollen en speciaalvelden.
     * @return true or false.
     * @throws RemoteException Wanneer de internetconnectie wordt onderbroken tussen client en server.
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

//    // Deze method zal de coordinaten van een mol geupdate worden en worden gereturned. Als een mol niet verplaatst kan worden wordt de huidige positie gereturned.
//    public Spelbord_Model verplaatsMol( sm, int[] eindPunt, int ficheNR, int molIndex) throws RemoteException {
//        if (zetGeldig(sm.getPlayers(), eindPunt, ficheNR, molIndex, sm.beurtIndex())) {
//            sm.getPlayers().get(sm.beurtIndex()).getMol_list().get(molIndex).setCoord(eindPunt);
//            return sm;
//        }
//        return null;
//    }


    /**
     * Geeft terug of er op de gegeven VeldKnop een mol kan worden geplaatst.
     *
     * @param bs_interface Het bordspel waar op gewerkt wordt.
     * @param spelerModel Het SpelerModel van de speler wiens beurt het op dat moment is.
     * @param molGeselecteerd Het geselecteerde MolModel.
     * @param eindPunt De coordinaten waar het MolMModel neer wordt gezet.
     * @return Geeft terug of het een legale zet is.
     * @throws RemoteException wanneer de connectie tussen de client en host verbroken wordt.
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
     * Geeft het aantal mollen die op het bord staan.
     *
     * @param bs_interface Het bordspel waar op gewerkt wordt.
     * @return geeft het aantal mollen terug als een integer.
     * @throws RemoteException wanneer de connectie tussen de client en de server verbroken wordt.
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
     * Vraagt aan het spelbord of er op een bepaald veld een mol aanwezig is.
     *
     * @param bs_interface Het spelbord waar mee gewerkt wordt.
     * @param veldKnop De knop waarover wordt gevraagd of er een mol op staat.
     * @return Het MolModel wanneer deze aanwezig is.
     * @throws RemoteException Wanneer de connectie tussen de server en de client verbroken is.
     */
    public MolModel bepaalOfMolAanwezig(Bordspel_Interface bs_interface, VeldKnop veldKnop) throws RemoteException {
        for (MolModel mol : bs_interface.playerList().get(0).getMol_list()) {
            if (Arrays.equals(mol.getCoord(), veldKnop.getCoordinaten())) {
                return mol;
            }
        }
        return null;
    }

    /**
     * Plaatst in een keer de mollen van een SpelerModel op het spelbord.
     * @param buttonArray De knoppen waarop de MolModels neer moeten worden gezet.
     * @param pm Het SpelerModel waarvan de MolModels op het bord moeten worden gezet.
     * @param niveau Het niveau waarop de MolModels moeten worden neergezet.
     * @throws RemoteException Wanneer de connectie tussen server en client verbroken wordt.
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

