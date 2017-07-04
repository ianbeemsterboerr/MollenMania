package view;

import controller.MainMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * De view die opgestart wordt wanneer het spel wordt opgestart.
 */
public class MainMenuView {
	private InstellingenView instellingenView;
	private MainMenuController mainMenuController;

	private Button btn_joinSpel;
	private Button btn_hostSpel;
	private Button btn_exitSpel;
	private Button btn_hervatSpel;

	private Stage primaryStage;

	/**
	 * Maak een stage aan die gebruikt wordt om de main menu te laden
	 * @param instellingenView om de instellingen panel in de main menu toe te voegen
	 * @param mainMenuController controller om views ta laden
	 */
	public MainMenuView(InstellingenView instellingenView, MainMenuController mainMenuController){
		this.instellingenView=instellingenView;
		this.mainMenuController = mainMenuController;

		//maak alle visuele objecten.
		double BUTTON_WIDTH = 150.0;
		primaryStage = new Stage();
		BorderPane bp_pane = new BorderPane();
		bp_pane.setPadding(new Insets(20, 20, 20, 20));
		VBox vbox_mid = new VBox();

		//maak alle buttons.
		btn_joinSpel = new Button();
		btn_hostSpel = new Button();
		btn_exitSpel = new Button();
		btn_hervatSpel = new Button();

		//zet de maximale breedte van de knoppen.
		btn_joinSpel.setMaxWidth(BUTTON_WIDTH);
		btn_hostSpel.setMaxWidth(BUTTON_WIDTH);
		btn_exitSpel.setMaxWidth(BUTTON_WIDTH);
		btn_hervatSpel.setMaxWidth(BUTTON_WIDTH);

		//zet alle ID's zodat in de css de style van deze attributen bepaald kan worden.
		btn_joinSpel.setId("btn_joinspel");
		btn_hostSpel.setId("btn_hostspel");
		btn_exitSpel.setId("btn_exitspel");
		bp_pane.setId("bp_pane");
		btn_hervatSpel.setId("btn_hervatspel");


		//configureer wat de Join Spel knop doet.
		btn_joinSpel.setOnAction(e -> {
			try{
				//new connect_host_view("0");
				mainMenuController.joinGame();
			}catch(Exception b){
				b.printStackTrace();
			}});

		//configureer wat de Host Spel knop doet.
		btn_hostSpel.setOnAction(e -> {
			try{
				mainMenuController.hostGame();
			}catch(Exception b){
				b.printStackTrace();
			}});

		//configureer wat de spel hervatten knop doet.
		btn_hervatSpel.setOnAction(e -> {
			try{
				
			}catch(Exception b){
				b.printStackTrace();
			}});

		//configureer wat de Spel Afsluiten knop doet.
		btn_exitSpel.setOnAction(e -> {
			try{
				System.exit(0);
			}catch(Exception b){
				b.printStackTrace();
			}});

		//zet alle knoppen in een virtualbox.
		vbox_mid.getChildren().addAll(btn_joinSpel,  btn_exitSpel);
		vbox_mid.setAlignment(Pos.CENTER);
		vbox_mid.setSpacing(10.0);

		//Zet de knoppen in het midden van de applicatie.
		bp_pane.setCenter(vbox_mid);
		bp_pane.setTop(instellingenView.getView());

		Scene main_scene = new Scene(bp_pane, 1000, 600);

		//Connect de stylesheets met de view.
		main_scene.getStylesheets().addAll(getClass().getResource("style/main_menu_style.css").toExternalForm());

		//set de titel, zorg dat de windows title bar weg wordt gehaald.
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("MollenMania - Main Menu");
		primaryStage.setScene(main_scene);
		instellingenView.registerStage(primaryStage);
		primaryStage.show();
	}

	/**
	 * Maakt alle buttons onklikbaar
	 */
	public void uitschakelen(){
		btn_joinSpel.setDisable(true);
		btn_hostSpel.setDisable(true);
		btn_exitSpel.setDisable(true);
		btn_hervatSpel.setDisable(true);
	}

	/**
	 * Maakt alle buttons klikbaar
	 */
	public void inschakelen(){
		btn_joinSpel.setDisable(false);
		btn_hostSpel.setDisable(false);
		btn_exitSpel.setDisable(false);
		btn_hervatSpel.setDisable(false);
	}
	
}
