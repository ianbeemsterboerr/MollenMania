package view;

import model.MolModel;

/**
 * Created by Wessel on 20-6-2017.
 */
public class MolKnop {

    private MolModel molModel;

    public MolKnop(MolModel molModel) {
        this.molModel = molModel;
    }

    public MolModel getMolModel() {
        return molModel;
    }

    public void setMolModel(MolModel molModel) {
        this.molModel = molModel;
    }
}
