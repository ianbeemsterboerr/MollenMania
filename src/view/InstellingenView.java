package view;

import controller.InstellingenPanelController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Created by Robert on 6/16/2017.
 */
public class InstellingenView {
    Button geluid;
    Button kleurenblindheid;
    Button handleiding;
    Button afsluiten;

    InstellingenPanelController controller;

    public InstellingenView(InstellingenPanelController controller){
        this.controller=controller;
        this.geluid = new Button("geluid");
        this.kleurenblindheid=new Button("kleuren blind");
        this.handleiding=new Button("handleiding");
        this.afsluiten=new Button("X");

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().addAll(geluid,kleurenblindheid,handleiding,afsluiten);
        flowPane.setAlignment(Pos.TOP_RIGHT);
        BorderPane pane = new BorderPane();
        pane.setCenter(flowPane);
        Scene scene = new Scene(pane,300,300);
        Stage stage = new Stage();
        stage.centerOnScreen();
        stage.setScene(scene);

        geluid.setOnAction(e -> {controller.toggleVolume();});
        kleurenblindheid.setOnAction(e -> {controller.toggleKleurenblindModus();});


        stage.show();
    }
}
