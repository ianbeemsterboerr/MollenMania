package controller;

import model.Niveau_Model;
import model.Playboard_Model;
import model.Spel_Model;

/**
 * Created by Wessel on 16-6-2017.
 */
public class SpelbordController {
    public Niveau_Model switchNiveau(Spel_Model spelModel, int huidigNiveau, Playboard_Model spelbord){
        if (huidigNiveau == 1 ){
           spelModel.setHuidigNiveau(huidigNiveau++);
            return spelbord.getNiveau2();
        }
        else if (huidigNiveau == 2) {
            spelModel.setHuidigNiveau(huidigNiveau++);
            return spelbord.getNiveau3();
        }
        else {
            spelModel.setHuidigNiveau(huidigNiveau++);
            return spelbord.getNiveau4();
            }
        }

}

