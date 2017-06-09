package model;

import model.Velden.GoudenSpade_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.Normaalveld_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Wessel on 6-6-2017.
 */
public class Niveau_Model  {

        Molshoop_Veld molshopen[];
        Normaalveld_Veld normaleVelden[];

    public Niveau_Model(Molshoop_Veld[] molshopen, Normaalveld_Veld[] normaleVelden) {
        this.molshopen = molshopen;
        this.normaleVelden = normaleVelden;
    }
}

