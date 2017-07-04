package view;

import controller.SpelHervattenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Het scherm dat vraagt om een naam voor de savefile die gemaakt moet worden.
 *
 * Created by Ian on 6/16/2017.
 */
public class HervatGameView {
    SpelHervattenController ctrl;

    public HervatGameView(SpelHervattenController ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * Geeft de filechooser weer waarin alleen .sav files kunnen worden geselecteerd en returned een filepath string.
     *
     * @return filePath String van de geselecteerde file.
     */
    public String show() {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();

        //geef de filechooser een duidelijke naam zodat de gebruiker weet wat hij/zij  moet doen.
        fc.setTitle("Selecteer een eerder opgeslagen .SAV bestand.");

        //zorg dat de filechooser alleen .sav files accepteert.
        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("SAV files .sav", "*.sav");
        fc.getExtensionFilters().add(extentionFilter);

        //initial directory = de home folder van de user.
        String currentDir = System.getProperty("user.home");
        File directoryPath = new File(currentDir);
        fc.setInitialDirectory(directoryPath);
        String filePath = fc.showOpenDialog(stage).getAbsolutePath();
        return filePath;
    }
}
