package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.host_controls_view;
import view.connect_host_view;

public class main_menu {
	public Stage main_menu_show(){
		
	//INITLIZE YO MAMA~~
	Stage primaryStage = new Stage();
	BorderPane bp_pane = new BorderPane();
	bp_pane.setPadding(new Insets(20, 20, 20, 20));
	VBox vbox_mid = new VBox();
	HBox hbox_options = new HBox();		
	host_controls_view hcv = new host_controls_view();
	hervat_game_view hgv = new hervat_game_view();
	double BUTTON_WIDTH = 150.0;
	
	//VOILAAA MAKE ZE BUTTONS
	Button btn_joinSpel = new Button();
	Button btn_hostSpel = new Button(); 
	Button btn_exitSpel = new Button();
	Button btn_hervatSpel = new Button();
	Button btn_oog = new Button();
	Button btn_close = new Button();
	Button btn_loudspeaker = new Button();
	Button btn_minimize = new Button();
	
	//BUTTON WIDTHS
	btn_joinSpel.setMaxWidth(BUTTON_WIDTH);
	btn_hostSpel.setMaxWidth(BUTTON_WIDTH);
	btn_exitSpel.setMaxWidth(BUTTON_WIDTH);
	btn_hervatSpel.setMaxWidth(BUTTON_WIDTH);
	
	//SET ID's for CSS
	btn_loudspeaker.setId("btn_loudspeaker");
	btn_oog.setId("btn_oog");
	btn_joinSpel.setId("btn_joinspel");
	btn_hostSpel.setId("btn_hostspel");
	btn_exitSpel.setId("btn_exitspel");
	bp_pane.setId("bp_pane");
	btn_close.setId("btn_close");
	btn_hervatSpel.setId("btn_hervatspel");
	btn_minimize.setId("btn_minimize");
	
	// Button actions
	btn_joinSpel.setOnAction(e -> { 
	try{
		new connect_host_view("0"); 
	}catch(Exception b){
		b.printStackTrace();
	}});
	
	btn_hostSpel.setOnAction(e -> { 
	try{
		hcv.host_controls_show(); 
	}catch(Exception b){
		b.printStackTrace();
	}});
	
	btn_hervatSpel.setOnAction(e -> { 
		try{
			hgv.hervat_game_show(); 
		}catch(Exception b){
			b.printStackTrace();
		}});
	
	btn_exitSpel.setOnAction(e -> { 
		try{
			System.exit(0);
		}catch(Exception b){
			b.printStackTrace();
	}});
	
	btn_close.setOnAction(e -> { 
		try{
			System.exit(0);
		}catch(Exception b){
			b.printStackTrace();
	}});
	
	btn_minimize.setOnAction(e -> { 
		try{
			primaryStage.setIconified(true);
		}catch(Exception b){
			b.printStackTrace();
	}});
	
	//ADD SHIT TO SHIT
	hbox_options.getChildren().addAll(btn_oog, btn_loudspeaker, btn_minimize, btn_close);
	hbox_options.setAlignment(Pos.TOP_RIGHT);
	
	vbox_mid.getChildren().addAll(btn_joinSpel, btn_hostSpel, btn_hervatSpel, btn_exitSpel);
	vbox_mid.setAlignment(Pos.CENTER);
	vbox_mid.setSpacing(10.0);
	
	bp_pane.setCenter(vbox_mid);
	bp_pane.setTop(hbox_options);
	
	Scene main_scene = new Scene(bp_pane, 1000, 600);
	
	//Make scene call up style.css for styling
	main_scene.getStylesheets().addAll(this.getClass().getResource("style/main_menu_style.css").toExternalForm());
	
	//MAKE SHIT APPEAR
	primaryStage.initStyle(StageStyle.UNDECORATED);
	primaryStage.setTitle("MollenMania - Main Menu");
	primaryStage.setScene(main_scene);
	
	return primaryStage;
	}
	
}
