package model;


import model.Velden.Molshoop_Veld;
import model.Velden.Normaalveld_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.util.ArrayList;

/**
 * Created by Wessel on 29-5-2017.
 */

public class Playboard_Model  {

    Niveau_Model n1 = new Niveau_Model(new ArrayList<>(), new ArrayList<>(),new ArrayList<>());

    n1.getMolshoop();

}