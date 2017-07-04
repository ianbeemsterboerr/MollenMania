package view;

import javafx.scene.control.Button;

/**
 *
 *
 * Deze klasse houdt bij of een fiche gedraaid is of niet om het makkeljk te maken om dashboardview te updaten.
 * Kan in de toekomst meer functionaliteit krijgen, zoals een index zodat je direct op de knop kan drukken.
 */
public class FicheButton extends Button {
    boolean gedraaid=false;
//    int waarde=0;
//
//    public int getWaarde() {
//        return waarde;
//    }
//
//    public void setWaarde(int waarde) {
//        this.waarde = waarde;
//    }
    /**
     * @return  boolean gedraaid.
     *
     * author   Robert den Blaauwen
     * Versie   3-7-2017
     */
    public boolean isGedraaid() {
        return gedraaid;
    }
    /**
     * author   Robert den Blaauwen
     * Versie   3-7-2017
     */
    public void setGedraaid(boolean gedraaid) {
        this.gedraaid = gedraaid;
    }
}
