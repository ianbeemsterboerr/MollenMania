package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Wessel on 29-5-2017.
 */
public class Fiche_Model {

    public Fiche_Model() {
        geslotenFiche.add(1);
        geslotenFiche.add(2);
        geslotenFiche.add(2);
        geslotenFiche.add(3);
        geslotenFiche.add(3);
        geslotenFiche.add(4);

    }

    private ArrayList<Integer> openFiche = new ArrayList<Integer>();
    private ArrayList<Integer> geslotenFiche = new ArrayList<Integer>();

    public ArrayList<Integer> getOpenFiche() {
        return openFiche;
    }

    public void setOpenFiche(ArrayList<Integer> openFiche) {
        this.openFiche = openFiche;
    }

    public ArrayList<Integer> getGeslotenFiche() {
        return geslotenFiche;
    }

    public void setGeslotenFiche(ArrayList<Integer> geslotenFiche) {
        this.geslotenFiche = geslotenFiche;
    }
}

