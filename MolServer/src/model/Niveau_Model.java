

        package model;

        import model.GoudenSchep_Veld;
        import model.Normaalveld_Veld;
        import model.SpeciaalVeld_Veld;

        import java.util.ArrayList;

        /**
         * Created by Wessel on 6-6-2017.
         */
        public class Niveau_Model  {

            ArrayList<Molshoop_Veld> molshoop;
            ArrayList<Normaalveld_Veld> normaal;
            ArrayList<SpeciaalVeld_Veld> speciaal;
            ArrayList<GoudenSchep_Veld> goudenSchep;

            public Niveau_Model(ArrayList<Molshoop_Veld> molshoop, ArrayList<SpeciaalVeld_Veld> speciaal,ArrayList<GoudenSchep_Veld> goudenSchep) {
                this.molshoop = molshoop;
                this.speciaal = speciaal;
                this.goudenSchep = goudenSchep;
            }


            public ArrayList<GoudenSchep_Veld> getGoudenSchep() {
                return goudenSchep;
            }

            public void setGoudenSchep(ArrayList<GoudenSchep_Veld> goudenSchep) {
                this.goudenSchep = goudenSchep;
            }

            public ArrayList<Normaalveld_Veld> getNormaal() {
                return normaal;
            }

            public void setNormaal(ArrayList<Normaalveld_Veld> normaal) {
                this.normaal = normaal;
            }

            public ArrayList<SpeciaalVeld_Veld> getSpeciaal() {
                return speciaal;
            }

            public void setSpeciaal(ArrayList<SpeciaalVeld_Veld> speciaal) {
                this.speciaal = speciaal;
            }

            public ArrayList<Molshoop_Veld> getMolshoop() {
                return molshoop;
            }

            public void setMolshoop(ArrayList<Molshoop_Veld> molshoop) {
                this.molshoop = molshoop;
            }

        }

