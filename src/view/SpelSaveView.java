package view;

import controller.SpelSaveController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * Created by Ian on 6/19/2017.
 */
public class SpelSaveView {
    public void show() {
        Stage stage = new Stage();
        FlowPane fp = new FlowPane();
        Button btn = new Button("Opslaan");
        TextArea txt = new TextArea();

        fp.getChildren().add(txt);
        fp.getChildren().add(btn);


        Scene scene = new Scene(fp, 300, 100);

        stage.setScene(scene);
        stage.setTitle("Spel saven");
        stage.show();
    }

}
