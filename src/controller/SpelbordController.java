package controller;

import model.Niveau_Model;
import model.Playboard_Model;
import model.Spel_Interface;
import model.Spel_Model;
import model.Velden.VeldKnop;
import model.Velden.Molshoop_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Heeft de verantwoordelijkheid van alles op het spelbord besturen.
 * Created by Wessel on 16-6-2017.
 */
public class SpelbordController {
    /**
     * Wanneer alle molshopen in een niveau vol zitten, switcht deze methode naar het volgende niveau.
     *
     * @param spelModel Het SpelModel waarin het hele spel zit.
     * @param huidigNiveau Het huidige niveau.
     * @param spelbord De container voor alle velden, speciale velden en mollen.
     * @return Geeft het volgende niveau terug.
     */
    public Niveau_Model switchNiveau(Spel_Interface spelModel, int huidigNiveau, Playboard_Model spelbord) {
        if (huidigNiveau == 1) {
            try {
                spelModel.setHuidigNiveau(huidigNiveau++);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return spelbord.getNiveau2();
        } else if (huidigNiveau == 2) {
            try {
                spelModel.setHuidigNiveau(huidigNiveau++);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return spelbord.getNiveau3();
        } else {
            try {
                spelModel.setHuidigNiveau(huidigNiveau++);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return spelbord.getNiveau4();
        }
    }

    /**
     *met deze method worden de velden op een niveau vergeleken met de positie van een pion.
     Als het veld een molshoop is wordt er een 1 gereturnd
     als het veld een speciaalveld is wordt er een 2 gereturnd
     als het veld een goudenschep is wordt een een 3 gereturned
     als het geen speciaalveld of molshoop is, is het een normaal veld en wordt er een 0 gereturned.
     *
     * @param niveau Het huidige spelniveau.
     * @param coord Het coordinaat van de VeldKnop waarvan gecheckt wordt watvoor veld dit is.
     * @return molshoop = 1, speciaalveld = 2, goudenschep = 3, normaal veld = 0.
     */
    public int bepaalVeldSoort(ArrayList<Niveau_Model> niveau, int coord[]) {
        for (int i = 0; i < niveau.get(0).getMolshoop().size(); i++) {
            if (niveau.get(0).getMolshoop().get(i).getPositie() == coord) {
                return 1;
            }
        }
        for (int i = 0; i < niveau.get(1).getSpeciaal().size(); i++) {
            if (niveau.get(1).getSpeciaal().get(i).getPositie() == coord) {
                return 2;
            }
        }
        for (int i = 0; i < niveau.get(2).getGoudenSchep().size(); i++) {
            if (niveau.get(2).getGoudenSchep().get(i).getPositie() == coord) {
                return 3;
            }
        }
        return 0;
    }


}


