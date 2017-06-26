package controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class run extends Application{

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ChoiceBox<Integer> cb_aantal_spelers;
		double button_width = 150.0;
		HBox hbox_connect = new HBox();
		
		Button btn_host = new Button("HOST");
		Button btn_back = new Button("BACK");
		btn_host.setMaxWidth(button_width);
		btn_back.setMaxWidth(button_width);
		
		hbox_connect.getChildren().addAll(btn_host, btn_back);
		hbox_connect.setAlignment(Pos.CENTER);
		hbox_connect.setSpacing(10.0);
		
		cb_aantal_spelers = new ChoiceBox<Integer>(FXCollections.observableArrayList(2, 3, 4));
		cb_aantal_spelers.setMaxWidth(100.0);
		
		
		btn_host.setOnAction(e -> { 
			try{
				new Mol_Server(cb_aantal_spelers.getSelectionModel().getSelectedItem());
			}catch(Exception b){
				b.printStackTrace();
		}});
		
		btn_back.setOnAction(e -> { 
			try{
				System.exit(1); 
			}catch(Exception b){
				b.printStackTrace();
		}});
	    
	    GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(new Label("MAX AANTAL SPELERS: "), 0, 1);
	    grid.add(cb_aantal_spelers, 1, 1);
	    grid.add(btn_host, 1, 2);
	    grid.add(btn_back, 2, 2);
		
		Scene connect_scene = new Scene(grid, 250, 85);
		//Make scene call up style.css for styling
		//connect_scene.getStylesheets().addAll(this.getClass().getResource("main_menu_style.css").toExternalForm());
		primaryStage.setTitle("Host een spel");
		primaryStage.setScene(connect_scene);
		primaryStage.show();
	}

}