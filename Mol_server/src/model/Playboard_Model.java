package model;

import model.Velden.GoudenSchep_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Bevat alle niveaus die bestaan op het spelbord.
 */
public class Playboard_Model implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Niveau_Model niveau1 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
    private Niveau_Model niveau2 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
    private Niveau_Model niveau3 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
    private Niveau_Model niveau4 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());

    public Niveau_Model getNiveau1() {
        return niveau1;
    }

    public Niveau_Model getNiveau2() {
        return niveau2;
    }

    public Niveau_Model getNiveau3() {
        return niveau3;
    }

    public Niveau_Model getNiveau4() {
        return niveau4;
    }


    public Playboard_Model(){
        //Niveau 1
        this.niveau1.getMolshoop().add(new Molshoop_Veld(3,1,-4));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(4,-1,-3));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(-1,4,-3));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(1,1,-2));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(-1,2,-1));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(2,-2,0));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(0,0,0));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(1,-2,1));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(-4,3,1));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(2,-4,2));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(-1,-1,2));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(-4,1,3));
        this.niveau1.getMolshoop().add(new Molshoop_Veld(-2,-2,4));

        //Niveau 2
        this.niveau2.getMolshoop().add(new Molshoop_Veld(4,0,-4));
        this.niveau2.getMolshoop().add(new Molshoop_Veld(1,2,-3));
        this.niveau2.getMolshoop().add(new Molshoop_Veld(3,-1,-2));
        this.niveau2.getMolshoop().add(new Molshoop_Veld(-2,3,-1));
        this.niveau2.getMolshoop().add(new Molshoop_Veld(3,-3,0));
        this.niveau2.getMolshoop().add(new Molshoop_Veld(4,-4,0));
        this.niveau2.getMolshoop().add(new Molshoop_Veld(-3,1,2));
        this.niveau2.getMolshoop().add(new Molshoop_Veld(-1,-2,3));
        this.niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(0,4,-4));
        this.niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(4,-4,0));
        this.niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(0,-1,1));
        this.niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(-3,0,3));

        //Niveau 3
        this.niveau3.getMolshoop().add(new Molshoop_Veld(1,3,-4));
        this.niveau3.getMolshoop().add(new Molshoop_Veld(2,-1,-1));
        this.niveau3.getMolshoop().add(new Molshoop_Veld(-4,2,2));
        this.niveau3.getMolshoop().add(new Molshoop_Veld(1,-4,3));
        this.niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(4,-2,-2));
        this.niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(-2,4,-2));
        this.niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(-1,1,0));
        this.niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(-1,-3,4));

        //Niveau 4
        this.niveau4.getGoudenSchep().add(new GoudenSchep_Veld(0,0,0));
        this.niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(4,0,-4));
        this.niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(-2,3,-1));
        this.niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(2,-3,1));
        this.niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(-4,0,4));

    }

    public Niveau_Model getHuidigNiveau(int huidigeNiveau) throws RemoteException {
        switch (huidigeNiveau){
            case 1:
                return this.niveau1;
            case 2:
                return this.niveau2;
            case 3:
                return this.niveau3;
            case 4:
                return this.niveau4;
        }
        return null;
    }

}