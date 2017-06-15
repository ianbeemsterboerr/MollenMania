package model;

/**
 * Created by Wessel on 15-6-2017.
 */
public class Speler_Model {

    private Fiche_Model fiches;
    private Pawn_Model[] mollen;
    private String naam;
    private int handGrote;

    public Speler_Model(Fiche_Model fiches, Pawn_Model[] mollen, String naam, int handGrote) {
        this.fiches = fiches;
        this.mollen = mollen;
        this.naam = naam;
        this.handGrote = handGrote;
    }


    public Fiche_Model getFiches() {
        return fiches;
    }

    public void setFiches(Fiche_Model fiches) {
        this.fiches = fiches;
    }

    public Pawn_Model[] getMollen() {
        return mollen;
    }

    public void setMollen(Pawn_Model[] mollen) {
        this.mollen = mollen;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getHandGrote() {
        return handGrote;
    }

    public void setHandGrote(int handGrote) {
        this.handGrote = handGrote;
    }


}
