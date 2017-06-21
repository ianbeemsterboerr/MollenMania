package view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import controller.Bordspel_Controller;
import controller.Bordspel_Interface;
import controller.Player_Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Speler_Model;

public class SpelbordView extends UnicastRemoteObject implements Player_Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Speler_Model> players;
	GridPane player_1;
	GridPane player_2;
	GridPane player_3;
	GridPane player_4;
	
	public SpelbordView(Bordspel_Controller bs_controller, Bordspel_Interface bs_interface) throws RemoteException{
		Stage bordStage = new Stage();
		
		try {
			bs_interface.addObserver(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		players = bs_interface.playerList();
		
		VBox left = new VBox();
		left.setPadding(new Insets(20, 20, 20, 20));
		left.setSpacing(300.0);
		VBox right = new VBox();
		right.setPadding(new Insets(20, 20, 20, 20));
		right.setSpacing(300.0);
		
		if(players.size() == 2){
			this.player_1 = this.createUserPanel(players.get(0));
			this.player_2 = this.createUserPanel(players.get(1));
			left.getChildren().addAll(player_1);
			right.getChildren().addAll(player_2);
			
			this.player_1.setAlignment(Pos.TOP_LEFT);
			this.player_2.setAlignment(Pos.TOP_RIGHT);
		} else if(players.size() == 3){
			this.player_1 = this.createUserPanel(players.get(0));
			this.player_2 = this.createUserPanel(players.get(1));
			this.player_3 = this.createUserPanel(players.get(2));
			
			left.getChildren().addAll(player_1, player_3);
			right.getChildren().addAll(player_2);
			
			this.player_1.setAlignment(Pos.TOP_LEFT);
			this.player_2.setAlignment(Pos.TOP_RIGHT);
			this.player_3.setAlignment(Pos.BOTTOM_LEFT);
		} else if(players.size() == 4){
			this.player_1 = this.createUserPanel(players.get(0));
			this.player_2 = this.createUserPanel(players.get(1));
			this.player_3 = this.createUserPanel(players.get(2));
			this.player_4 = this.createUserPanel(players.get(3));
			
			left.getChildren().addAll(player_1, player_3);
			right.getChildren().addAll(player_2, player_4);
			
			this.player_1.setAlignment(Pos.TOP_LEFT);
			this.player_2.setAlignment(Pos.TOP_RIGHT);
			this.player_3.setAlignment(Pos.BOTTOM_LEFT);
			this.player_4.setAlignment(Pos.BOTTOM_RIGHT);
		} else {
			System.out.println("nope");
		}

//		GridPane player_1 = this.createUserPanel(players.get(0));
//		GridPane player_2 = this.createUserPanel(players.get(1));
//		GridPane player_3 = this.createUserPanel(players.get(2));
//		GridPane player_4 = this.createUserPanel(players.get(3));
//		
//		this.player_1.setAlignment(Pos.TOP_LEFT);
//		this.player_2.setAlignment(Pos.TOP_RIGHT);
//		this.player_3.setAlignment(Pos.BOTTOM_LEFT);
//		this.player_4.setAlignment(Pos.BOTTOM_RIGHT);
		
		
		BorderPane moap = new BorderPane();
		moap.setLeft(left);
		moap.setRight(right);
		
		Scene bord = new Scene(moap, 800,600);
		bordStage.setTitle("play with me");
		bordStage.setScene(bord);
		bordStage.show();
	}
	
	public GridPane createUserPanel(Speler_Model sm){
		/*
		 * maybe each panel should be owned by a user?! watcha think dog.
		 */
		
		String speler_naam = sm.getUsername();
		String mol_count = Integer.toString(sm.getMol_list().size());
		String fiche_count = Integer.toString(sm.getFiches().size());
		
		GridPane grid = new GridPane();
		
		Label username_lbl = new Label(speler_naam);
		Label aantal_fiche_lbl = new Label(fiche_count); 
		Label aantal_mol_lbl = new Label(mol_count);
		
		Button fiche_btn = new Button("Fiche");
		Button mol_btn = new Button("Mol");
		Button klaar_btn = new Button("Klaar");
		
		grid.add(username_lbl, 0, 0);
		grid.add(mol_btn, 0, 1);
		grid.add(aantal_mol_lbl, 1, 1);
		grid.add(fiche_btn, 0, 2);
		grid.add(aantal_fiche_lbl, 1, 2);
		grid.add(klaar_btn, 0, 3);
		grid.setHgap(10.0);
		grid.setVgap(10.0);
	
		return grid;
	}

	@Override
	public void modelChanged(Bordspel_Interface playable) throws RemoteException {
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
		
	}
	
}
