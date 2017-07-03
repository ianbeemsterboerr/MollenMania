package controller;

import model.Spelbord_Model;
import view.HervatGameView;


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
    }

    /**
     * Ontvangt een filepath als String en returned een Spel_Model.
     *
     * @param filePath De filepath van de savefile die geopend moet worden.
     * @return een Spel_Model object dat tot leven kan worden geroepen als het Spelbord.
     */

    public void startSpel(Spelbord_Model spelModel) {
    	
    }
}
