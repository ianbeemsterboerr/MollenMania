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
import model.Spelbord_Model;
import view.HervatGameView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;

/**
 * De runklasse zorgt ervoor dat de server wordt opgestart.
 */
public class run extends Application{

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().toString());
		// TODO Auto-generated method stub
		ChoiceBox<Integer> cb_aantal_spelers;
		double button_width = 150;
		HBox hbox_connect = new HBox();

		
		TextField host_name = new TextField();
		host_name.setText("");
		Button btn_host = new Button("Host");
		Button btn_back = new Button("Terug");
		Button btn_spelHervatten = new Button("Hervats");

		btn_host.setMaxWidth(button_width);
		btn_back.setMaxWidth(button_width);
		btn_spelHervatten.setMaxWidth(button_width);
		
		hbox_connect.getChildren().addAll(btn_host, btn_back,btn_spelHervatten);
		hbox_connect.setAlignment(Pos.CENTER);
		hbox_connect.setSpacing(10.0);
		
		cb_aantal_spelers = new ChoiceBox<Integer>(FXCollections.observableArrayList(2, 3, 4));
		cb_aantal_spelers.getSelectionModel().selectFirst();
		cb_aantal_spelers.setMaxWidth(100.0);
		
		
		btn_host.setOnAction(e -> { 
			try{
				System.setProperty("java.rmi.server.hostname",host_name.getText());
				System.out.println(this.getClass().toString()+": hostname: "+host_name.getText());
				new Mol_Server(cb_aantal_spelers.getSelectionModel().getSelectedItem());
			}catch(Exception b){
				b.printStackTrace();
		}});
		
		btn_back.setOnAction(e -> { 
			try{
				System.out.println("Server shutdown..");
				System.exit(1); 
			}catch(Exception b){
				b.printStackTrace();
		}});

		btn_spelHervatten.setOnAction(a->{
			if(host_name.getText()!=""&&host_name.getText()!=null&&host_name.getText()!=" "){
				System.out.println(this.getClass().toString()+": rmi.server.hostname gezet");
				System.setProperty("java.rmi.server.hostname",host_name.getText());
			}

			File saveFile  = new HervatGameView().getFile();
			File file = saveFile;
			Spelbord_Model spelModel;
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				spelModel = (Spelbord_Model) ois.readObject();
				
				new Mol_Server(spelModel);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Het .SAV bestand was niet van het goede type.");
			}


		});
	    
	    GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(new Label("MAX AANTAL SPELERS: "), 0, 1);
	    grid.add(cb_aantal_spelers, 1, 1);
		grid.add(new Label("RMI interface IP: "), 0, 2);
		grid.add(host_name, 1, 2);
	    grid.add(btn_host, 0, 3);
	    grid.add(btn_back, 1, 3);
	    grid.add(btn_spelHervatten,2,3);
		
		Scene connect_scene = new Scene(grid, 470, 120);
		//Make scene call up style.css for styling
		grid.setId("grid");
		connect_scene.getStylesheets().addAll(this.getClass().getResource("/view/ServerStyle.css").toExternalForm());


		primaryStage.setOnCloseRequest(a->{
			System.out.println("Server shutdown..");
			System.exit(1);
		});


		primaryStage.setTitle("Host een spel");
		primaryStage.setScene(connect_scene);
		primaryStage.show();
	}

}
