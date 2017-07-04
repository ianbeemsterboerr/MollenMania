package view;

import controller.Mol_Client;
import controller.SpelJoinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
    private TextField ipServer, username, ipClient;
    private Speler_Model player;
    private SpelJoinController spelJoinController;
    private Label tipLabel = new Label("Hint: als je meerdere spelers wilt maken moet je de client applicatie meerdere keren starten, anders werkt het spel niet.");
    
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

        ipServer = new TextField();
        ipClient = new TextField();
        username = new TextField();

        Button btn_connect = new Button("Verbind");
        Button btn_back = new Button("Terug");
        btn_connect.setMaxWidth(button_width);
        btn_back.setMaxWidth(button_width);

        hbox_connect.getChildren().addAll(btn_connect, btn_back);
        hbox_connect.setAlignment(Pos.CENTER);
        hbox_connect.setSpacing(10.0);

        btn_connect.setOnAction(e -> {
            try{
                spelJoinController.connect(this.ipServer.getText().trim(),this.ipClient.getText().trim(),this.textBoxUsername().trim());
                //new Mol_Client(this.ipServer.getText().trim(), this.username.getText(),spelJoinController.getMainMenuController().getInstellingenPanelController());
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

        Label serverIP = new Label("Server IP adres: ");
        serverIP.setId("overlay_label");
        grid.add(serverIP, 0, 0);
        grid.add(ipServer, 1, 0);

        Label clientIP = new Label("Client IP adres: ");
        clientIP.setId("overlay_label");
        grid.add(clientIP, 0, 1);
        grid.add(ipClient, 1, 1);

        Label gebruikersnaam = new Label("Gebruikersnaam: ");
        gebruikersnaam.setId("overlay_label");
        grid.add(gebruikersnaam, 0, 2);
        grid.add(username, 1, 2);
        grid.setAlignment(Pos.CENTER);

        grid.add(btn_connect, 0, 3);
        grid.add(btn_back, 1, 3);
        grid.setId("overlay_window");

        tipLabel.setId("overlay_label");
        BorderPane joinPane = new BorderPane();
        joinPane.setId("overlay");
        joinPane.setCenter(grid);
        tipLabel.setAlignment(Pos.TOP_CENTER);
        joinPane.setTop(tipLabel);
        Scene connect_scene = new Scene(joinPane, 1000, 600);
        connect_scene.setFill(Color.TRANSPARENT);

        //Make scene call up style.css for styling
        connect_scene.getStylesheets().addAll(getClass().getResource("style/main_menu_style.css").toExternalForm());
        connectStage.setTitle("Mollenmania - Spel joinen");
        connectStage.setScene(connect_scene);
        connectStage.initStyle(StageStyle.TRANSPARENT);
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
