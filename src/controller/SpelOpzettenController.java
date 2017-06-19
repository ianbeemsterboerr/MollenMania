package controller;

import javafx.scene.paint.Color;
import model.Playboard_Model;
import model.Spel_Model;
import model.Speler_Model;

import java.util.ArrayList;


/**
 * Created by Wessel on 16-6-2017.
 *
 * Ook wel de "LobbyController"
 */

public class SpelOpzettenController {
ArrayList<Speler_Model> spelerList = new ArrayList<>();

//Mist de OBSERVERS
public Spel_Model spelOpzetten( Spel_Model spelModel, Speler_Model speler, Playboard_Model spelBord){
    spelModel.setSpelbord(spelBord);
    spelModel.setSpelers(spelerList);
    spelModel.setHuidigNiveau(1);
    spelModel.setMaxAantalObservers(spelerList.size());
    return spelModel;
    }

public Speler_Model setHandgrote(int handgrootte, Speler_Model speler) {
    speler.setHandGrootte(handgrootte);
    return speler;
}

public Speler_Model setPionKleur (Color kleur, Speler_Model speler){
    speler.setKleur(kleur);
    return speler;
}

public void addSpelers (Speler_Model speler){
    this.spelerList.add(speler);
}

}
