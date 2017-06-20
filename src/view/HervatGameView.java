package view;

import controller.SpelHervattenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Ian on 6/16/2017.
 */
public class HervatGameView {
    public HervatGameView(SpelHervattenController ctrl) {

    }
    public void show() {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.showOpenDialog(stage);

    }


}
