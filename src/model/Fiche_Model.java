package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Wessel on 29-5-2017.
 */
public class Fiche_Model {

    public Fiche_Model() {
    }

    private Random random = new Random();
    private ArrayList<Integer> openFiche = new ArrayList<Integer>();
    private ArrayList<Integer> closedFiche = new ArrayList<Integer>();

    public void initFiches(){
        closedFiche.add(1);
        closedFiche.add(2);
        closedFiche.add(2);
        closedFiche.add(3);
        closedFiche.add(3);
        closedFiche.add(4);
    }

    public int chooseFiche() {
        int getal = random.nextInt(closedFiche.size());
        int fiche = closedFiche.get(getal);
        openFiche.add(closedFiche.get(getal));
        closedFiche.remove(getal);
        return fiche;

    }

    public void ficheCheck(){
        if(closedFiche.size()==0){
            System.out.println("Fiches resetten");
            resetFiches();
        }

    }

    public void resetFiches(){
        for(int ficheNr : openFiche){
            closedFiche.add(ficheNr);
        }
        openFiche.clear();
    }
}

