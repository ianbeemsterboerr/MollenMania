package view;

import controller.Mol_Client;
import controller.SpelJoinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Speler_Model;

/**
 * Created by Robert on 6/18/
 *
 * Neemt de plaats in van connect_host_view, gebruikt veel van de code van connect_host_view
 */
public class JoinSpelView {
    private String txtValue;
    private TextField ipadress, username;
    private Speler_Model player;
    private SpelJoinController spelJoinController;
    
    /**
     * Constructor die ervoor zorgt dat de scherm om een spel te joinen geladen wordt.
     * @param spelJoinController wordt gegeven om in de volgende scherm controller werk te kunnen.
     * 			
     */
    public JoinSpelView(SpelJoinController spelJoinController){
        this.spelJoinController=spelJoinController;
        double button_width = 150.0;
        Stage connectStage = new Stage();
        HBox hbox_connect = new HBox();

        ipadress = new TextField();
        username = new TextField();

        Button btn_connect = new Button("CONNECT");
        Button btn_back = new Button("BACK");
        btn_connect.setMaxWidth(button_width);
        btn_back.setMaxWidth(button_width);

        hbox_connect.getChildren().addAll(btn_connect, btn_back);
        hbox_connect.setAlignment(Pos.CENTER);
        hbox_connect.setSpacing(10.0);

        btn_connect.setOnAction(e -> {
            try{
                new Mol_Client(this.ipadress.getText(), this.textBoxUsername(),spelJoinController.getMainMenuController().getInstellingenPanelController());
                //new molclient(this.textBoxUsername(), this.maxHostSpelers); //Robert: waarom zou deze view moeten aangeven hoeveel spelers er zijn? Das toch alleen de taak van de Host?
                connectStage.close();
            }catch(Exception b){
                b.printStackTrace();
            }});

        btn_back.setOnAction(e -> {
            try{
                connectStage.close();
                spelJoinController.getMainMenuController().inschakelenKnoppen();
            }catch(Exception b){
                b.printStackTrace();
            }});

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("IP adres: "), 0, 0);
        grid.add(ipadress, 1, 0);
        grid.add(new Label("Gebruikersnaam: "), 0, 1);
        grid.add(username, 1, 1);
        grid.add(btn_connect, 1, 2);
        grid.add(btn_back, 2, 2);
        Scene connect_scene = new Scene(grid, 400, 140);

        //Make scene call up style.css for styling
        //connect_scene.getStylesheets().addAll(this.getClass().getResource("main_menu_style.css").toExternalForm());
        connectStage.setTitle("Insert IP to connect");
        connectStage.setScene(connect_scene);
        connectStage.initStyle(StageStyle.UNDECORATED);
        connectStage.show();
        //WinView winView = new WinView();
    }
    
    /**
     * om de textbox zijn text value te krijgen
     * @return krijgt een string terug, die toegewezen wordt aan de speler.
     * 		
     */		
    
    public String textBoxUsername(){
        return this.username.getText();
    }

}
