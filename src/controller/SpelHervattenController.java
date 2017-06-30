package controller;

import model.Spelbord_Model;
import view.HervatGameView;

import java.io.*;

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
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            spelModel = (Spelbord_Model) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Het .SAV bestand was niet van het goede type.");
        }

        return spelModel;
    }

    public void startSpel(Spelbord_Model spelModel) {

    }
}
