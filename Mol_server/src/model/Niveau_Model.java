package model;

import model.Velden.GoudenSchep_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.Normaalveld_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Het NiveauModel is een niveau in het spel waarvan er 4 zijn in totaal. Ze bestaan elk uit een verschillend aantal molshopen en speciaalvelden. In het laatste spelniveau zit ook een gouden schep. Wanneer deze gepakt wordt is het spel beeindigd.
 * Created by Wessel on 6-6-2017.
 */
public class Niveau_Model implements Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<Molshoop_Veld> molshoop;
    ArrayList<Normaalveld_Veld> normaal;
    ArrayList<SpeciaalVeld_Veld> speciaal;
    ArrayList<GoudenSchep_Veld> goudenSchep;

    /**
     * Maakt het NiveauModel aan met de bijbehorende molshopen, speciaalvelden en gouden schep.
     * @param molshoop De ArrayList van alle molshopen die aanwezig zijn op het niveau.
     * @param speciaal De ArrayList van alle speciaalvelden die aanwezig zijn op het niveau.
     * @param goudenSchep De gouden schep die op het laatste niveau staat.
     */
    public Niveau_Model(ArrayList<Molshoop_Veld> molshoop, ArrayList<SpeciaalVeld_Veld> speciaal,ArrayList<GoudenSchep_Veld> goudenSchep) {
        this.molshoop = molshoop;
        this.speciaal = speciaal;
        this.goudenSchep = goudenSchep;
    }

    /**
     * Geeft het GoudenSchep veld object terug.
     * @return GoudenSchep_Veld
     */
    public ArrayList<GoudenSchep_Veld> getGoudenSchep() {
        return goudenSchep;
    }

    /**
     * Set het GoudenSchep_Veld object.
     * @param goudenSchep
     */
    public void setGoudenSchep(ArrayList<GoudenSchep_Veld> goudenSchep) {
        this.goudenSchep = goudenSchep;
    }

    /**
     * geeft alle normale velden terug in een ArrayList.
     * @return ArrayList<Normaalveld_Veld>
     */
    public ArrayList<Normaalveld_Veld> getNormaal() {
        return normaal;
    }

    /**
     * set de normaalvelden arraylist.
     * @param normaal ArrayList<Normaalveld_Veld>
     */
    public void setNormaal(ArrayList<Normaalveld_Veld> normaal) {
        this.normaal = normaal;
    }

    /**
     * geeft alle SpeciaalVelden terug in een ArrayList.
     * @return ArrayList<SpeciaalVeld_Veld>
     */
    public ArrayList<SpeciaalVeld_Veld> getSpeciaal() {
        return speciaal;
    }

    /**
     * set alle speciaalvelden.
     * @param speciaal ArrayList<SpeciaalVeld_Veld>
     */
    public void setSpeciaal(ArrayList<SpeciaalVeld_Veld> speciaal) {
        this.speciaal = speciaal;
    }

    /**
     * Geeft alle molshopen terug in een ArrayList.
     * @return ArrayList<Molshoop_Veld>
     */
    public ArrayList<Molshoop_Veld> getMolshoop() {
        return molshoop;
    }

    /**
     * Set alle molshopen door een ArrayList mee te geven.
     * @param molshoop ArrayList<Molshoop_Veld>
     */
    public void setMolshoop(ArrayList<Molshoop_Veld> molshoop) {
        this.molshoop = molshoop;
    }

}

