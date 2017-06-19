package view;

import controller.SpelSaveController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;


/**
 * Created by Ian on 6/19/2017.
 */
public class SpelSaveView extends FlowPane {
    public SpelSaveView(SpelSaveController ctrl) {
        Button btn = new Button("Opslaan");
        TextArea txt = new TextArea();
        setPrefHeight(100);
        setPrefWidth(300);

        btn.setOnAction(a -> {
            ctrl.saveSpel(txt.getText());
        });
    }

}
