package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class host_controls_view {
	public void host_controls_show(){
		double button_width = 150.0;					
		Stage connectStage = new Stage();
		HBox hbox_connect = new HBox();
		
		Button btn_connect = new Button("HOST");
		Button btn_back = new Button("BACK");
		btn_connect.setMaxWidth(button_width);
		btn_back.setMaxWidth(button_width);
		
		hbox_connect.getChildren().addAll(btn_connect, btn_back);
		hbox_connect.setAlignment(Pos.CENTER);
		hbox_connect.setSpacing(10.0);
		
		ChoiceBox<String> cb_aantal_spelers = new ChoiceBox<String>(FXCollections.observableArrayList("2", "3", "4"));
		cb_aantal_spelers.setMaxWidth(100.0);
		
		btn_back.setOnAction(e -> { 
			try{
				connectStage.close(); 
			}catch(Exception b){
				b.printStackTrace();
		}});
	    
	    GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(new Label("MAX AANTAL SPELERS: "), 0, 0);
	    grid.add(cb_aantal_spelers, 1, 0);
	    grid.add(btn_connect, 0, 1);
	    grid.add(btn_back, 1, 1);
		
		Scene connect_scene = new Scene(grid, 250, 75);
		
		connectStage.setTitle("Host een spel");
		connectStage.setScene(connect_scene);
		connectStage.show();
		}
}
