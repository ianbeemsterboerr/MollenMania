package model;

/**
 * Created by Wessel on 29-5-2017.
 */
public class Field_Model{
    private int coord[];
    private Field_Enum field;



    public Field_Model(int[] coord, Field_Enum field) {

        this.coord = coord;
        this.field = field;
    }

    public Field_Model(int[] coord) {
        this.coord = coord;
    }

    public int[] getCoord() {
        return coord;
    }

    public void setCoord(int[] coord) {
        this.coord = coord;
    }
    public Field_Enum getField() {
        return field;
    }

    public void setField(Field_Enum field) {
        this.field = field;
    }

}
