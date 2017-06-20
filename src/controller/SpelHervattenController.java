package controller;

import model.Spel_Model;
import view.HervatGameView;

import java.io.*;

/**
 * Created by Ian on 6/20/2017.
 * @author Ian Beemsterboer
 * Zorgt dat de view wordt laten zien en er een file gekozen kan worden zodat deze als spel kan worden gelaten.
 */
public class SpelHervattenController {
    String filePath;
    HervatGameView view;
    Spel_Model spelModel;

    public SpelHervattenController() {
        view = new HervatGameView(this);
        filePath = view.show();
        //spelModel = getModel(filePath);
    }

    /**
     * Ontvangt een filepath als String en returned een Spel_Model.
     *
     * @param filePath De filepath van de savefile die geopend moet worden.
     * @return een Spel_Model object dat tot leven kan worden geroepen als het Spelbord.
     */
    public Spel_Model getModel(String filePath) {
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            spelModel = (Spel_Model) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return spelModel;
    }
}
