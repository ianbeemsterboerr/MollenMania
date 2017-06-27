package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * De ArrayList die bestaat uit 6 fiches.
 * Created by Wessel on 29-5-2017.
 */
public class  Fiche_Model implements Serializable{
    private static final long serialVersionUID = 1L;
    private ArrayList<Integer> openFiche = new ArrayList<Integer>();
    private ArrayList<Integer> geslotenFiche = new ArrayList<Integer>();
    private int ficheNR;

    /**
     * Maakt 6 fiches aan, met de waarden die mogelijk zijn in het spel.
     */
    public Fiche_Model() {
        geslotenFiche.add(1);
        geslotenFiche.add(2);
        geslotenFiche.add(2);
        geslotenFiche.add(3);
        geslotenFiche.add(3);
        geslotenFiche.add(4);

    }

    /**
     * ficheNR houdt de waarde vast van het fiche dat op dat moment omgedraaid wordt. Met deze methode wordt deze waarde verkregen.
     * @return Het fichenummer, int.
     */
    public int getFicheNR() {
        return ficheNR;
    }

    /**
     * Zet de waarde van ficheNR.
     * @param ficheNR
     */
    public void setFicheNR(int ficheNR) {
        this.ficheNR = ficheNR;
    }

    /**
     * Geeft de waarde van een open fiche.
     * @return int, waarde tussen 1 en 4.
     */
    public ArrayList<Integer> getOpenFiche() {
        return openFiche;
    }

    /**
     * Zet de waarde van een openFiche. Deze setter dient in het spel nooit te worden gebruikt.
     * @param openFiche
     */
    public void setOpenFiche(ArrayList<Integer> openFiche) {
        this.openFiche = openFiche;
    }

    /**
     * Geeft de waarde van een gesloten fiche terug.
     * @return
     */
    public ArrayList<Integer> getGeslotenFiche() {
        return geslotenFiche;
    }

    /**
     * Zet de waarde van een geslotenFiche. Deze setter dient in het spel nooit te worden gebruikt.
     * @param geslotenFiche
     */
    public void setGeslotenFiche(ArrayList<Integer> geslotenFiche) {
        this.geslotenFiche = geslotenFiche;
    }
}

