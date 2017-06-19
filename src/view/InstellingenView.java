package view;

import controller.InstellingenPanelController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

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

    public InstellingenView(InstellingenPanelController controller){
        this.controller=controller;

        this.btnGeluid = new Button();
        this.btnKleurenblindheid =new Button();
        this.btnHandleiding =new Button("hndleid");
        this.btnAfsluiten =new Button();
        this.btnMinimaliseer=new Button();

        //SET ID's for CSS
        btnGeluid.setId("btn_loudspeaker");
        btnKleurenblindheid.setId("btn_oog");
        // nog geen style voor btnHandleiding
        btnMinimaliseer.setId("btn_minimize");
        btnAfsluiten.setId("btn_close");

        hbox = new HBox();
        hbox.getChildren().addAll(btnKleurenblindheid,btnGeluid,btnHandleiding,btnMinimaliseer,btnAfsluiten);
        hbox.setAlignment(Pos.TOP_RIGHT);

        btnGeluid.setOnAction(e -> {controller.toggleVolume();});
        btnKleurenblindheid.setOnAction(e -> {controller.toggleKleurenblindModus();});
        btnAfsluiten.setOnAction(e -> {
			try{
				System.exit(0);
			}catch(Exception b){
				b.printStackTrace();
			}});
    }

    public HBox getView(){
        return hbox;
    }
}