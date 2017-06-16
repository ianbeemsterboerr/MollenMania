package model;

import javafx.scene.paint.Color;

/**
 * Created by Wessel on 15-6-2017.
 */
public class Speler_Model {

    private Fiche_Model fiches;
    private Mol_Model[] mollen;
    private String naam;
    private int handGrootte;
    private Color kleur;

    public Speler_Model(Fiche_Model fiches, Mol_Model[] mollen, String naam, int handGrote, Color kleur) {
        this.fiches = fiches;
        this.mollen = mollen;
        this.naam = naam;
        this.handGrootte = handGrote;
        this.kleur = kleur;
    }


    public Fiche_Model getFiches() {
        return fiches;
    }

    public void setFiches(Fiche_Model fiches) {
        this.fiches = fiches;
    }

    public Mol_Model[] getMollen() {
        return mollen;
    }

    public void setMollen(Mol_Model[] mollen) {
        this.mollen = mollen;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getHandGrootte() {
        return handGrootte;
    }

    public void setHandGrootte(int handGrootte) {
        this.handGrootte = handGrootte;
    }

    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }
}
