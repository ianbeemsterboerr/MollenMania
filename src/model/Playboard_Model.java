package model;

import model.Velden.Molshoop_Veld;
import model.Velden.Normaalveld_Veld;
import model.Velden.SpeciaalVeld_Veld;

import java.util.ArrayList;

/**
 * Created by Wessel on 29-5-2017.
 */
public class Playboard_Model extends Niveau_Model{
    private int playfieldID;
    private int orientation;
    private Field_Model field;



    public Playboard_Model(int playfieldID, int orientation, Field_Model field) {
        this.playfieldID = playfieldID;
        this.orientation = orientation;
        this.field = field;
    }


    public int getPlayfieldID() {
        return playfieldID;
    }

    public void setPlayfieldID(int playfieldID) {
        this.playfieldID = playfieldID;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public Field_Model getField() {
        return field;
    }

    public void setField(Field_Model field) {
        this.field = field;
    }
}
