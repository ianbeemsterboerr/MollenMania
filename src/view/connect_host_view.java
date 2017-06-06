package view;

import controller.molclient;
import controller.molserver;
import controller.moluser_controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.player_model;

public class connect_host_view {
	
	private String txtValue;
	private TextField ipadress, username;
	
	public void connect_host_show(){
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
		
		btn_connect.setId("btn_hervatspel");
		btn_back.setId("btn_hervatspel");
		
		btn_connect.setOnAction(e -> { 
			try{
				new molclient().ConnectServer();
				connectStage.close();
			}catch(Exception b){
				b.printStackTrace();
		}});
	    
	    btn_back.setOnAction(e -> { 
			try{
				connectStage.close();
			}catch(Exception b){
				b.printStackTrace();
		}});
	    
	    GridPane grid = new GridPane();
	    grid.setVgap(10);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(new Label("IP-ADRRESS: "), 0, 0);
	    grid.add(ipadress, 1, 0);
	    grid.add(new Label("USERNAME: "), 0, 1);
		grid.add(username, 1, 1);
	    grid.add(btn_connect, 1, 2);
	    grid.add(btn_back, 2, 2);
	    Scene connect_scene = new Scene(grid, 355, 140);
		//Make scene call up style.css for styling
		connect_scene.getStylesheets().addAll(this.getClass().getResource("style/main_menu_style.css").toExternalForm());
		connectStage.setTitle("Insert IP to connect");
		connectStage.setScene(connect_scene);
		connectStage.show();
	}

}
