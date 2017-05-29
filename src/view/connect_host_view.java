package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class connect_host_view {
	public void connect_host_show(){
		double button_width = 150.0;									
		Stage connectStage = new Stage();
		HBox hbox_connect = new HBox();
		
		Button btn_connect = new Button("CONNECT");
		Button btn_back = new Button("BACK");
		btn_connect.setMaxWidth(button_width);
		btn_back.setMaxWidth(button_width);
		
		hbox_connect.getChildren().addAll(btn_connect, btn_back);
		hbox_connect.setAlignment(Pos.CENTER);
		hbox_connect.setSpacing(10.0);
		
		TextField notification = new TextField ();
	    notification.clear();
	    
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
	    grid.add(notification, 1, 0);
	    grid.add(btn_connect, 1, 1);
	    grid.add(btn_back, 3, 1);
		
		Scene connect_scene = new Scene(grid, 325, 75);
		
		connectStage.setTitle("Insert IP to connect");
		connectStage.setScene(connect_scene);
		connectStage.show();
	}
}
