package view;

import controller.SpelSaveController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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
        Stage stage = new Stage();
        GridPane gp_save = new GridPane();
        Button btn_opslaan = new Button("Opslaan");
        TextField txt_save = new TextField();
        Label lbl_save = new Label("Give your save game a name!");
        
        gp_save.add(lbl_save, 0, 1);
        gp_save.add(txt_save, 0, 0);
        gp_save.add(btn_opslaan, 1, 0);
        
        btn_opslaan.setOnAction(a->{
            ctrl.saveSpel(txt_save.getText());
            stage.close();
        });
        gp_save.setId("gridpane");
        gp_save.getStylesheets().addAll(getClass().getResource("style/saveStyle.css").toExternalForm());
        Scene scene = new Scene(gp_save, 300, 75);

        stage.setScene(scene);
        stage.setTitle("Mollenmania - Spel opslaan");
        stage.show();
    }
}
