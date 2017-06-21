package view;

import controller.InstellingenPanelController;
import controller.MainMenuController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.host_controls_view;
import view.connect_host_view;
import view.spelbord_view;
import controller.Bordspel_Interface;
import controller.Bordspel_Controller;

public class MainMenuView {
	private InstellingenView instellingenView;
	private MainMenuController mainMenuController;
	private controller.Bordspel_Controller bs_controller;
	private controller.Bordspel_Interface bs_interface;

	private Button btn_joinSpel;
	private Button btn_hostSpel;
	private Button btn_exitSpel;
	private Button btn_hervatSpel;
	private Button btn_spelSpelen;

	Stage primaryStage;

	public MainMenuView(InstellingenView instellingenView, MainMenuController mainMenuController){
		this.instellingenView=instellingenView;
		this.mainMenuController = mainMenuController;

		//INITLIZE YO MAMA~~
		primaryStage = new Stage();
		BorderPane bp_pane = new BorderPane();
		bp_pane.setPadding(new Insets(20, 20, 20, 20));
		VBox vbox_mid = new VBox();
		//HBox hbox_options = new HBox(); //deprecated
		//host_controls_view hcv = new host_controls_view(); //deprecated
		//hervat_game_view hgv = new hervat_game_view(); //deprecated
		double BUTTON_WIDTH = 150.0;

		//VOILAAA MAKE ZE BUTTONS
		btn_joinSpel = new Button();
		btn_hostSpel = new Button();
		btn_exitSpel = new Button();
		btn_hervatSpel = new Button();
		btn_spelSpelen = new Button("test");
		//Button btn_oog = new Button();
		//Button btn_close = new Button();
		//Button btn_loudspeaker = new Button();
		//Button btn_minimize = new Button();

		//Only for testing purposes, circumvents lobby and goes straight to Spel Spelen

		//BUTTON WIDTHS
		btn_joinSpel.setMaxWidth(BUTTON_WIDTH);
		btn_hostSpel.setMaxWidth(BUTTON_WIDTH);
		btn_exitSpel.setMaxWidth(BUTTON_WIDTH);
		btn_hervatSpel.setMaxWidth(BUTTON_WIDTH);
		btn_spelSpelen.setMaxWidth(BUTTON_WIDTH);

		//SET ID's for CSS
		//btn_loudspeaker.setId("btn_loudspeaker");
		//btn_oog.setId("btn_oog");
		btn_joinSpel.setId("btn_joinspel");
		btn_hostSpel.setId("btn_hostspel");
		btn_exitSpel.setId("btn_exitspel");
		bp_pane.setId("bp_pane");
		//btn_close.setId("btn_close");
		btn_hervatSpel.setId("btn_hervatspel");
		//btn_minimize.setId("btn_minimize");

		btn_spelSpelen.setOnAction(e->{
			try{
				spelbord_view spelBordView = new spelbord_view();
				spelBordView.spelbord_show(bs_controller, bs_interface);
			}catch(Exception b){
				b.printStackTrace();
			}});

		// Button actions
		btn_joinSpel.setOnAction(e -> {
			try{
				//new connect_host_view("0");
				mainMenuController.joinGame();
			}catch(Exception b){
				b.printStackTrace();
			}});

		btn_hostSpel.setOnAction(e -> {
			try{
				//hcv.host_controls_show();
				mainMenuController.hostGame();
			}catch(Exception b){
				b.printStackTrace();
			}});

		btn_hervatSpel.setOnAction(e -> {
			try{
				//hgv.hervat_game_show();
				mainMenuController.hervatSpel();
			}catch(Exception b){
				b.printStackTrace();
			}});

		btn_exitSpel.setOnAction(e -> {
			try{
				System.exit(0);
			}catch(Exception b){
				b.printStackTrace();
			}});

		//EDIT THIS TO TEST SHIT!
		//ADD SHIT TO SHIT
		//hbox_options.getChildren().addAll(btn_oog, btn_loudspeaker, btn_minimize, btn_close);
		//hbox_options.setAlignment(Pos.TOP_RIGHT);

		vbox_mid.getChildren().addAll(btn_joinSpel, btn_hostSpel, btn_hervatSpel, btn_spelSpelen, btn_exitSpel);
		vbox_mid.setAlignment(Pos.CENTER);
		vbox_mid.setSpacing(10.0);

		bp_pane.setCenter(vbox_mid);
		bp_pane.setTop(instellingenView.getView());

		Scene main_scene = new Scene(bp_pane, 1000, 600);

		//Make scene call up style.css for styling
		main_scene.getStylesheets().addAll(getClass().getResource("style/main_menu_style.css").toExternalForm());

		//MAKE SHIT APPEAR
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("MollenMania - Main Menu");
		primaryStage.setScene(main_scene);
		//primaryStage.setFullScreen(true);
		instellingenView.registerStage(primaryStage);
		primaryStage.show();
	}
	public void uitschakelen(){
		btn_joinSpel.setDisable(true);
		btn_hostSpel.setDisable(true);
		btn_exitSpel.setDisable(true);
		btn_hervatSpel.setDisable(true);
	}
	public void inschakelen(){
		btn_joinSpel.setDisable(false);
		btn_hostSpel.setDisable(false);
		btn_exitSpel.setDisable(false);
		btn_hervatSpel.setDisable(false);
	}
	
}
