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
 * Wordt getoond wanneer er op "Save Spel" wordt gedrukt vanuit de game.
 */
public class SpelSaveView {
    SpelSaveController ctrl;
    public SpelSaveView(SpelSaveController ctrl){
        this.ctrl = ctrl;
    }
    public void show() {
        Stage stage = new Stage();
        FlowPane fp = new FlowPane();
        Button btn = new Button("Opslaan");
        TextArea txt = new TextArea();

        txt.setMaxSize(10, 50);
        fp.getChildren().add(txt);
        fp.getChildren().add(btn);
        btn.setOnAction(a->{
            ctrl.saveSpel(txt.getText());
        });


        Scene scene = new Scene(fp, 300, 100);

        stage.setScene(scene);
        stage.setTitle("Spel saven");
        stage.show();
    }

}
