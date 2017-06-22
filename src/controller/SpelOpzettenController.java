package controller;

import javafx.scene.paint.Color;
import model.Playboard_Model;
import model.Spel_Model;
import model.Speler_Model;

import java.rmi.RemoteException;
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
    try {
        spelModel.setSpelbord(spelBord);
    } catch (RemoteException e) {
        e.printStackTrace();
    }
    try {
        spelModel.setSpelers(spelerList);
    } catch (RemoteException e) {
        e.printStackTrace();
    }
    try {
        spelModel.setHuidigNiveau(1);
    } catch (RemoteException e) {
        e.printStackTrace();
    }
    try {
        spelModel.setMaxAantalObservers(spelerList.size());
    } catch (RemoteException e) {
        e.printStackTrace();
    }
    return spelModel;
    }

public Speler_Model setHandgrote(int handgrootte, Speler_Model speler) {
    //speler.setHandGrootte(handgrootte);
    return speler;
}


public void addSpelers (Speler_Model speler){
    this.spelerList.add(speler);
}

}
