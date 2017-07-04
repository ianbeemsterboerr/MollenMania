package view;

import controller.HostSpelController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Robert on 6/19/2017.
 */
public class HostSpelView {
    ChoiceBox<String> cb_aantal_spelers;
    private HostSpelController hostSpelController;
    private TextField username;
   
     /**
      * maakt een panel waar de user kan kiezen of hij/zij een spel wilt hosten
      * @param hostSpelController krijgt een controller mee om in de view controller werk te kunnen doen, zoals server opstarten.
      */
    public HostSpelView(HostSpelController hostSpelController){
        this.hostSpelController=hostSpelController;
        double button_width = 150.0;
        Stage connectStage = new Stage();
        HBox hbox_connect = new HBox();

        username = new TextField();

        TextField host_name = new TextField();
        Button btn_host = new Button("HOST");
        Button btn_back = new Button("BACK");
        btn_host.setMaxWidth(button_width);
        btn_back.setMaxWidth(button_width);

        hbox_connect.getChildren().addAll(btn_host, btn_back);
        hbox_connect.setAlignment(Pos.CENTER);
        hbox_connect.setSpacing(10.0);

        cb_aantal_spelers = new ChoiceBox<String>(FXCollections.observableArrayList("2", "3", "4"));
        cb_aantal_spelers.setMaxWidth(100.0);

        btn_host.setOnAction(e -> {
            try{
                hostSpelController.startServer();
                connectStage.close();
                //new connect_host_view(this.aantalSpelers());
                //System.out.println(this.aantalSpelers());
            }catch(Exception b){
                b.printStackTrace();
            }});

        btn_back.setOnAction(e -> {
            try{
                hostSpelController.getMainMenuController().inschakelenKnoppen();
                connectStage.close();
            }catch(Exception b){
                b.printStackTrace();
            }});

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        Label spelers = new Label("Maximaal aantal spelers: ");
        spelers.setId("overlay_label");
        grid.add(spelers, 0, 0);
        grid.add(cb_aantal_spelers, 1, 0);

        Label gebruikersnaam = new Label("Gebruikersnaam: ");
        gebruikersnaam.setId("overlay_label");
        grid.add(gebruikersnaam, 0, 1);
        grid.add(username, 1, 1);
        grid.add(btn_host, 0, 2);
        grid.add(btn_back, 1, 2);
        grid.setAlignment(Pos.CENTER);
        grid.setId("overlay_window");

        BorderPane hostPane = new BorderPane();
        hostPane.setId("overlay_window");
        hostPane.setId("overlay");
        hostPane.setCenter(grid);

        Scene host_scene = new Scene(hostPane, 1000, 600);
        host_scene.setFill(Color.TRANSPARENT);
        //Make scene call up style.css for styling
        host_scene.getStylesheets().addAll(this.getClass().getResource("style/main_menu_style.css").toExternalForm());
        connectStage.setTitle("Mollenmania - Host een spel");
        connectStage.setScene(host_scene);
        connectStage.initStyle(StageStyle.TRANSPARENT);
        connectStage.show();
    }
    
}
