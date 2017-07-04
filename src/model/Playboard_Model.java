package model;

import model.Velden.GoudenSchep_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Maakt het model aan waarin alle niveaus staan. Deze niveaus bevat alle spelinformatie die voor die niveaus relevant zijn.
 *
 */
public class Playboard_Model implements Serializable {
	private static final long serialVersionUID = 1L;
	private Niveau_Model niveau1 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
	private Niveau_Model niveau2 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
	private Niveau_Model niveau3 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
	private Niveau_Model niveau4 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());

    /**
     * geeft niveau 1 terug
     * @return Niveau_Model
     */
    public Niveau_Model getNiveau1() {
        return niveau1;
    }

    /**
     * geeft niveau 2 terug
     * @return Niveau_Model
     */
    public Niveau_Model getNiveau2() {
        return niveau2;
    }

    /**
     * geeft niveau 3 terug
     * @return Niveau_Model
     */
    public Niveau_Model getNiveau3() {
        return niveau3;
    }

    /**
     * geeft niveau 4 terug
     * @return Niveau_Model
     */
    public Niveau_Model getNiveau4() {
        return niveau4;
    }

    /**
     * Maakt alle playboards voor alle niveaus aan. Dit is hardcoded, omdat we precies het mollenmania spel namaken.
     */
    public Playboard_Model(){
        //Niveau 1
        niveau1.getMolshoop().add(new Molshoop_Veld(3,1,-4));
        niveau1.getMolshoop().add(new Molshoop_Veld(4,-1,-3));
        niveau1.getMolshoop().add(new Molshoop_Veld(-1,4,-3));
        niveau1.getMolshoop().add(new Molshoop_Veld(1,1,-2));
        niveau1.getMolshoop().add(new Molshoop_Veld(-1,2,-1));
        niveau1.getMolshoop().add(new Molshoop_Veld(2,-2,0));
        niveau1.getMolshoop().add(new Molshoop_Veld(0,0,0));
        niveau1.getMolshoop().add(new Molshoop_Veld(1,-2,1));
        niveau1.getMolshoop().add(new Molshoop_Veld(-4,3,1));
        niveau1.getMolshoop().add(new Molshoop_Veld(2,-4,2));
        niveau1.getMolshoop().add(new Molshoop_Veld(-1,-1,2));
        niveau1.getMolshoop().add(new Molshoop_Veld(-4,1,3));
        niveau1.getMolshoop().add(new Molshoop_Veld(-2,-2,4));

        //Niveau 2
        niveau2.getMolshoop().add(new Molshoop_Veld(4,0,-4));
        niveau2.getMolshoop().add(new Molshoop_Veld(1,2,-3));
        niveau2.getMolshoop().add(new Molshoop_Veld(3,-1,-2));
        niveau2.getMolshoop().add(new Molshoop_Veld(-2,3,-1));
        niveau2.getMolshoop().add(new Molshoop_Veld(3,-3,0));
        niveau2.getMolshoop().add(new Molshoop_Veld(4,-4,0));
        niveau2.getMolshoop().add(new Molshoop_Veld(-3,1,2));
        niveau2.getMolshoop().add(new Molshoop_Veld(-1,-2,3));
        niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(0,4,-4));
        niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(4,-4,0));
        niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(0,-1,1));
        niveau2.getSpeciaal().add(new SpeciaalVeld_Veld(-3,0,3));

        //Niveau 3
        niveau3.getMolshoop().add(new Molshoop_Veld(1,3,-4));
        niveau3.getMolshoop().add(new Molshoop_Veld(2,-1,-1));
        niveau3.getMolshoop().add(new Molshoop_Veld(-4,2,2));
        niveau3.getMolshoop().add(new Molshoop_Veld(1,-4,3));
        niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(4,-2,-2));
        niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(-2,4,-2));
        niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(-1,1,0));
        niveau3.getSpeciaal().add(new SpeciaalVeld_Veld(-1,-3,4));

        //Niveau 4
        niveau4.getGoudenSchep().add(new GoudenSchep_Veld(0,0,0));
        niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(4,0,-4));
        niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(-2,3,-1));
        niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(2,-3,1));
        niveau4.getSpeciaal().add(new SpeciaalVeld_Veld(-4,0,4));

    }

    /**
     * Geeft aan de hand van een int tussen de 1 en 4 het bijbehorende niveau terug.
     * @param huidigeNiveau Het huidige niveau, als integer.
     * @return Niveau_Model
     * @throws RemoteException Wanneer de connectie tussen de server en client verbroken is.
     */
    public Niveau_Model getHuidigNiveau(int huidigeNiveau) throws RemoteException {
        switch (huidigeNiveau){
            case 1:
                return getNiveau1();
            case 2:
                return getNiveau2();
            case 3:
                return getNiveau3();
            case 4:
                return getNiveau1();
        }
        return getNiveau1();
    }

}