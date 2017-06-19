package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Wessel on 29-5-2017.
 */
public class Fiche_Model implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Fiche_Model() {
    }

    private Random random = new Random();
    private ArrayList<Integer> openFiche = new ArrayList<Integer>();
    private ArrayList<Integer> geslotenFiche = new ArrayList<Integer>();

    public void initFiches(){
        geslotenFiche.add(1);
        geslotenFiche.add(2);
        geslotenFiche.add(2);
        geslotenFiche.add(3);
        geslotenFiche.add(3);
        geslotenFiche.add(4);
    }

    public int kiesFiche() {
        int getal = random.nextInt(geslotenFiche.size());
        int fiche = geslotenFiche.get(getal);
        openFiche.add(geslotenFiche.get(getal));
        geslotenFiche.remove(getal);
        return fiche;

    }

    public void fichesCheck(){
        if(geslotenFiche.size() == 0){
            System.out.println("Fiches resetten");
            resetFiches();
        }

    }

    public void resetFiches(){
        for(int ficheNr : openFiche){
            geslotenFiche.add(ficheNr);
        }
        openFiche.clear();
    }
}

