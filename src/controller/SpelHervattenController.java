package controller;

import model.Spelbord_Model;
import model.Speler_Model;
import view.HervatGameView;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * Zorgt dat de view wordt laten zien en er een file gekozen kan worden zodat deze als spel kan worden gelaten.
 */
public class SpelHervattenController {
    String filePath;
    HervatGameView view;
    Spelbord_Model spelModel;

    public SpelHervattenController() {
        view = new HervatGameView(this);
        filePath = view.show();
        spelModel = getModel(filePath);
    }

    /**
     * Ontvangt een filepath als String en returned een Spel_Model.
     *
     * @param filePath De filepath van de savefile die geopend moet worden.
     * @return een Spel_Model object dat tot leven kan worden geroepen als het Spelbord.
     */
    public Spelbord_Model getModel(String filePath) {
        File file = new File(filePath);
        ArrayList<Speler_Model> test_list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            test_list = (ArrayList<Speler_Model>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Het .SAV bestand was niet van het goede type.");
        }
        return spelModel;
    }
    
    public void loadSpel() throws RemoteException{
    	try {
            ObjectInputStream model_object = new ObjectInputStream(new FileInputStream("")); //load object stream
            spelModel = (Spelbord_Model) model_object.readObject(); //type cast object and read
            model_object.close(); //close to prevent leaks
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	//test out
    	System.out.println(spelModel.playerList().size());
    }

    public void startSpel(Spelbord_Model spelModel) {
    	
    }
}
