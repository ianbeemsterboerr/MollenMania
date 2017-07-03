package view;

import controller.Bordspel_Controller;
import controller.InstellingenPanelController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Robert on 6/16/2017.
 */
public class InstInGameView {
    private Button btnGeluid;
    private Button btnKleurenblindheid;
    private Button btnHandleiding;
    private Button btnOpslaan;
    private Button btnAfsluiten;
    private Button btnMinimaliseer;

    private HBox hbox;

    private InstellingenPanelController instellingenPanelController;
    private Bordspel_Controller bordspel_controller;
    private Stage stage;

    public InstInGameView(InstellingenPanelController controller, Bordspel_Controller bordspel_controller){
        this.instellingenPanelController =controller;
        this.bordspel_controller=bordspel_controller;

        this.btnGeluid = new Button();
        this.btnKleurenblindheid =new Button();
        this.btnHandleiding =new Button();
        this.btnOpslaan = new Button();
        this.btnAfsluiten =new Button();
        this.btnMinimaliseer=new Button();

        //SET ID's for CSS
        btnGeluid.setId("btn_loudspeaker");
        btnKleurenblindheid.setId("btn_oog");
        btnHandleiding.setId("btn_handleiding");
        btnOpslaan.setId("btn_opslaan");
        btnMinimaliseer.setId("btn_minimize");
        btnAfsluiten.setId("btn_close");

        hbox = new HBox();
        hbox.getChildren().addAll(btnGeluid,btnHandleiding,btnOpslaan, btnMinimaliseer,btnAfsluiten);
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.getStylesheets().addAll(getClass().getResource("style/knoppen_panel_style.css").toExternalForm());

        btnGeluid.setOnAction(e -> {controller.toggleMute();});
        btnKleurenblindheid.setOnAction(e -> {controller.toggleKleurenblindModus();});
        btnHandleiding.setOnAction(e -> {controller.showHandleiding();});
        btnOpslaan.setOnAction(e->{
            bordspel_controller.opslaan();
        });
        btnMinimaliseer.setOnAction(e -> {
            stage.setIconified(true);
        });
        btnAfsluiten.setOnAction(e -> {
            System.exit(0);
        });
    }

    public void registerStage(Stage stage){
        this.stage=stage;
    }

    public HBox getView(){
        return hbox;
    }
}
