package view;

import controller.InstellingenPanelController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Robert on 6/16/2017.
 */
public class InstellingenView {
    Button btnGeluid;
    Button btnKleurenblindheid;
    Button btnHandleiding;
    Button btnAfsluiten;
    Button btnMinimaliseer;

    HBox hbox;

    InstellingenPanelController controller;
    Stage stage;

    public InstellingenView(InstellingenPanelController controller){
        this.controller=controller;

        this.btnGeluid = new Button();
        this.btnKleurenblindheid =new Button();
        this.btnHandleiding =new Button();
        this.btnAfsluiten =new Button();
        this.btnMinimaliseer=new Button();

        //SET ID's for CSS
        btnGeluid.setId("btn_loudspeaker");
        btnKleurenblindheid.setId("btn_oog");
        btnHandleiding.setId("btn_handleiding");
        btnMinimaliseer.setId("btn_minimize");
        btnAfsluiten.setId("btn_close");

        hbox = new HBox();
        hbox.getChildren().addAll(btnKleurenblindheid,btnGeluid,btnHandleiding,btnMinimaliseer,btnAfsluiten);
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.getStylesheets().addAll(getClass().getResource("style/knoppen_panel_style.css").toExternalForm());

        btnGeluid.setOnAction(e -> {controller.toggleMute();});
        btnKleurenblindheid.setOnAction(e -> {controller.toggleKleurenblindModus();});
        btnHandleiding.setOnAction(e -> {controller.showHandleiding();});
        btnMinimaliseer.setOnAction(e -> {
            stage.setIconified(true);
        });
        btnAfsluiten.setOnAction(e -> {
			try{
				System.exit(0);
			}catch(Exception b){
				b.printStackTrace();
			}});
    }

    public void registerStage(Stage stage){
        this.stage=stage;
    }

    public HBox getView(){
        return hbox;
    }
}
