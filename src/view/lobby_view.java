package view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controller.molclient;
import controller.moluser_controller;
import controller.moluser_interface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.player_model;
import model.player_observer;

public class lobby_view extends UnicastRemoteObject implements player_observer {
	
	//what do i need??!?!?!?
	private molclient client;
	private player_model player;
	private boolean enabled = false;
	private moluser_controller mu_controller;
	
	public lobby_view(moluser_controller mu_control, moluser_interface mu_interface) throws RemoteException{
		//this.player = molplayer;
		//this.client = new molclient(molplayer);
		try {
			mu_interface.addObserver(this);
			player = mu_interface.player();
		}catch(Exception e){
			e.printStackTrace();
		}
		this.mu_controller = mu_control;
		
		double button_width = 100.0;									
		Stage lobbyStage = new Stage();
		VBox vbox_hervat_options = new VBox();
		GridPane grid = new GridPane();
		TableView<player_model> game_table = new TableView();
		
		Button btn_pion = new Button("KIES PION");
		Button btn_kleur = new Button("KIES KLEUR");
		Button btn_refresh = new Button("REFRESH");
		
		//ObservableList<player_model> data = FXCollections.observableArrayList(player.player());
		game_table.setEditable(false);
		
		TableColumn<player_model, String> player_id_col = new TableColumn("PLAYER ID");
		TableColumn<player_model, String> player_name_col = new TableColumn("PLAYER NAME");
		//game_table.setItems(data);
		player_id_col.setMinWidth(25.0);
		player_name_col.setMinWidth(195.0);
		
		/**
		player_id_col.setCellValueFactory(
                new PropertyValueFactory<player_model, String>("player_id"));
		player_name_col.setCellValueFactory(
                new PropertyValueFactory<player_model, String>("player_name"));
		**/
        game_table.getColumns().addAll(player_id_col,player_name_col);
        
        
		btn_pion.setMaxWidth(button_width);
		btn_kleur.setMaxWidth(button_width);
		btn_refresh.setMaxWidth(button_width);
		
		vbox_hervat_options.setSpacing(5.0);
		vbox_hervat_options.getChildren().addAll(btn_pion, btn_kleur, btn_refresh);
		
		btn_refresh.setOnAction(e -> { 
			try{
				lobbyStage.close(); 
			}catch(Exception b){
				b.printStackTrace();
		}});
		
		grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(game_table, 0, 0);
	    grid.add(vbox_hervat_options, 1, 0);
		
		Scene lobby_scene = new Scene(grid, 365, 250);
		
		lobbyStage.setTitle("Lobby");
		lobbyStage.setScene(lobby_scene);
		lobbyStage.show();
	}
	
	/**
	public void lobby_show() throws RemoteException{
		
	}
	**/

	@Override
	public void modelChanged(moluser_interface mu_interface) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEnabled(boolean enabled) throws RemoteException {
		// TODO Auto-generated method stub
		this.enabled = enabled;
	}
}
