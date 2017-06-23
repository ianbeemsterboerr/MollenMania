package view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

import controller.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.MolModel;
import model.Speler_Model;
import model.Velden.VeldKnop;

public class SpelbordView extends UnicastRemoteObject implements Player_Observer{
	
	/**
	 * luhmao productions
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Speler_Model> players;
	private ArrayList<Speler_Model> new_players;
	private Bordspel_Interface bs_interface;
	private BorderPane spelbord_pane;
	private GridPane veld_pane;
	Label aantal_fiche_lbl = new Label(); 
	Label aantal_mol_lbl = new Label();
	private Bordspel_Controller bordspel_controller;

	private MolController molController;

	private VeldKnop[] buttonArray;

	//private Button rmiTest = new Button("RMI Test!");

	DashboardView player_1;
	DashboardView player_2;
	DashboardView player_3;
	DashboardView player_4;
	
	public SpelbordView(Bordspel_Controller bs_controller, Bordspel_Interface bs_interface, MolController molController, Fiche_Controller fiche_controller, String bijnaam) throws RemoteException{
		this.molController=molController;
		this.bordspel_controller=bs_controller;
		Stage bordStage = new Stage();
		
		try {
			//bs_interface.addObserver(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		players = bs_interface.playerList();
		this.bs_interface = bs_interface;

		//spelbord_pane = this.loadPlayers(players);
		veld_pane = this.loadVeld(players);

		spelbord_pane = this.loadPlayers(players, fiche_controller, bijnaam);
		//veld_pane = this.loadVeld();

		spelbord_pane.setCenter(veld_pane);
		spelbord_pane.setId("moap");
		veld_pane.setId("moap");
		
		Scene bord = new Scene(spelbord_pane, 960,760);
		bord.getStylesheets().addAll(this.getClass().getResource("style/SpelbordStyle.css").toExternalForm());
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		bordStage.setX(bounds.getMinX());
		bordStage.setY(bounds.getMinY());
		bordStage.setWidth(bounds.getWidth());
		bordStage.setHeight(bounds.getHeight());
		bordStage.setTitle("play with me");
		bordStage.setScene(bord);
		bordStage.setResizable(false);
		bordStage.show();
	}
	
	public GridPane createUserPanel(Speler_Model sm) throws RemoteException{
		/*
		 * maybe each panel should be owned by a user?! watcha think dog.
		 */
		int ficheNR=0;
        String openFiches = "";
		String speler_naam = sm.getUsername();
		String mol_count = Integer.toString(sm.getMol_list().size());
		String fiche_count = Integer.toString(sm.getFiches().size());
		
		GridPane grid = new GridPane();
		
		Label username_lbl = new Label(speler_naam);
		Label aantal_mol_lbl = new Label(mol_count);
		Label aantal_fiche_lbl = new Label(fiche_count);
		Label open_Fiches = new Label(openFiches);

		username_lbl.setStyle("-fx-font-weight:bold;");
		aantal_fiche_lbl.setStyle("-fx-font-weight:bold;");
		aantal_mol_lbl.setStyle("-fx-font-weight:bold;");
		
		Button fiche_btn = new Button("Fiche");
		Button mol_btn = new Button("Mol");
		Button klaar_btn = new Button("Klaar");
		Button refresh = new Button("Refresh");
		
		refresh.setOnAction(e->{
//			this.new_players = this.bs_interface.playerList();
			aantal_fiche_lbl.setText(Integer.toString(sm.getFiches().size()));
			aantal_mol_lbl.setText(Integer.toString(sm.getMol_list().size()));
			try {
				bordspel_controller.refresh();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});
		
		fiche_btn.setOnAction(e->{
		    //sm.getFiche_list().setFicheNR(new Fiche_Controller().kiesFiche(sm.getFiche_list()));
            System.out.println("SpelbordView.createUserPanel" +ficheNR);
            open_Fiches.setText(openFiches + ", " +String.valueOf(ficheNR));
            new Fiche_Controller().fichesCheck(sm.getFiche_list());
		});
		
		mol_btn.setOnAction(e->{
			sm.getMol_list().remove(1);
			
			
		});
		
		grid.add(username_lbl, 0, 0);
		grid.add(mol_btn, 0, 1);
		grid.add(aantal_mol_lbl, 1, 1);
		grid.add(fiche_btn, 0, 2);
		grid.add(aantal_fiche_lbl, 1, 2);
		grid.add(open_Fiches,1,3);
		grid.add(klaar_btn, 0, 3);
		grid.add(refresh, 0, 4);
		grid.setHgap(10.0);
		grid.setVgap(10.0);
	
		return grid;
	}

	public BorderPane loadPlayers(ArrayList<Speler_Model> players, Fiche_Controller fiche_controller, String bijnaam) throws RemoteException{

		VBox left = new VBox();
		left.setPadding(new Insets(20, 20, 20, 20));
		left.setSpacing(350.0);
		VBox right = new VBox();
		right.setPadding(new Insets(20, 20, 20, 20));
		right.setSpacing(350.0);
		
		if(players.size() == 2){
			this.player_1 = new DashboardView(players.get(0),fiche_controller, Pos.TOP_LEFT, bijnaam);
			this.player_2 = new DashboardView(players.get(1),fiche_controller, Pos.TOP_RIGHT, bijnaam);
			left.getChildren().addAll(player_1.get());
			right.getChildren().addAll(player_2.get());
		} else if(players.size() == 3){
			this.player_1 = new DashboardView(players.get(0),fiche_controller, Pos.TOP_LEFT, bijnaam);
			this.player_2 = new DashboardView(players.get(1),fiche_controller, Pos.TOP_RIGHT, bijnaam);
			this.player_3 = new DashboardView(players.get(2),fiche_controller, Pos.BOTTOM_LEFT, bijnaam);
			left.getChildren().addAll(player_1.get(), player_3.get());
			right.getChildren().addAll(player_2.get());
		} else if(players.size() == 4){
			this.player_1 = new DashboardView(players.get(0),fiche_controller, Pos.TOP_LEFT, bijnaam);
			this.player_2 = new DashboardView(players.get(1),fiche_controller, Pos.TOP_RIGHT, bijnaam);
			this.player_3 = new DashboardView(players.get(2),fiche_controller, Pos.BOTTOM_LEFT, bijnaam);
			this.player_4 = new DashboardView(players.get(3),fiche_controller, Pos.BOTTOM_RIGHT, bijnaam);
			left.getChildren().addAll(player_1.get(), player_3.get());
			right.getChildren().addAll(player_2.get(), player_4.get());
		} else {
			System.out.println("nope");
		}
		
		BorderPane moap = new BorderPane();
		moap.setLeft(left);
		moap.setRight(right);
		return moap;
	}
	
	public GridPane loadVeld(ArrayList<Speler_Model> players) throws RemoteException{
		GridPane root = new GridPane();
		int numRows = 12;
		int numCols = 29;

		buttonArray = new VeldKnop[61];

		for (int i = 0; i < numRows; i++) {
			RowConstraints rc = new RowConstraints();
			rc.setPercentHeight(100.0 / numRows);
			rc.setValignment(VPos.BOTTOM);
			root.getRowConstraints().add(rc);
		}
		
		for (int i = 0; i < numCols; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHalignment(HPos.CENTER);
			cc.setPercentWidth(100 / numCols);
			root.getColumnConstraints().add(cc);
		}

		// loop voor buttons 1 t/m 5
		for (int column = 13; column < 23; column = column + 2) {
			VeldKnop veld = new VeldKnop((8 - ((column + 1) / 2)) , ((column + 1) / 2 - 4), -4);
			root.add(veld, column, 1);
			buttonArray[(column + 1) / 2 - 7] = veld;
		}
		// loop voor buttons 6 t/m 11
		for (int column = 12; column < 24; column = column + 2) {
			VeldKnop veld = new VeldKnop((7 - (column / 2)) , (column / 2 - 4), -3);
			root.add(veld, column, 2);
			buttonArray[column / 2 - 1] = veld;
		}
		// loop voor buttons 12 t/m 18
		for (int column = 11; column < 25; column = column + 2) {
			VeldKnop veld = new VeldKnop((7 - ((column + 1) / 2)) , ((column + 1) / 2 - 5), -2);
			root.add(veld, column, 3);
			buttonArray[(column + 1) / 2 + 5] = veld;
		}
		// loop voor buttons 19 t/m 26
		for (int column = 10; column < 25; column = column + 2) {
			VeldKnop veld = new VeldKnop((6 - (column / 2)) , (column / 2 - 5), -1);
			root.add(veld, column, 4);
			buttonArray[column / 2 + 13] = veld;
		}
		// loop voor buttons 27 t/m 35
		for (int column = 9; column < 26; column = column + 2) {
			VeldKnop veld = new VeldKnop((6 - ((column + 1) / 2)) , ((column + 1) / 2 - 6), 0);
			root.add(veld, column, 5);
			buttonArray[(column + 1) / 2 + 21] = veld;
		}
		// loop voor buttons 36 t/m 43
		for (int column = 10; column < 25; column = column + 2) {
			VeldKnop veld = new VeldKnop((5 - (column / 2)) , (column / 2 - 6), 1);
			root.add(veld, column, 6);
			buttonArray[column / 2 + 30] = veld;
		}
		// buttons 44 t/m 50
		for (int column = 11; column < 25; column = column + 2) {
			VeldKnop veld = new VeldKnop((5 - ((column + 1) / 2)) , ((column + 1) / 2 - 7), 2);
			root.add(veld, column, 7);
			buttonArray[(column + 1) / 2 + 37] = veld;
		}
		// buttons 51 t/m 56
		for (int column = 12; column < 24; column = column + 2) {
			VeldKnop veld = new VeldKnop(4 - (column / 2) , (column / 2 - 7), 3);
			root.add(veld, column, 8);
			buttonArray[column / 2 + 44] = veld;
		}
		// buttons 57 t/m 61
		for (int column = 13; column < 23; column = column + 2) {
			VeldKnop veld = new VeldKnop((4 - (column + 1) / 2) , ((column + 1) / 2 - 8), 4);
			root.add(veld, column, 9);
			buttonArray[(column + 1) / 2 + 49] = veld;
		}
		
		/*
		 * final used to be used inside lamba. reason: jah knows.
		 */
		for(int i=0;i<this.buttonArray.length;i++){
			final VeldKnop buttonBox;
			buttonBox = buttonArray[i];
			buttonBox.setOnAction(e->{
				/*
				 * button changes
				 */
				//buttonBox.setDisable(true);
				buttonBox.setBezet(true);
				buttonBox.setStyle("-fx-background-color: #ff0000;");
				
				/*
				 * model changes
				 * to be done:
				 * 		1. whose turn is it?
				 */
				int mol_index = 0;
				Speler_Model empty = new Speler_Model();
				MolModel holdme = new MolModel();
				empty = players.get(0); // replace 0 with index aan de beurt.
				holdme = empty.getMol_list().get(mol_index); // index is always 0 -> always take first mol
				
				try {
					if(Arrays.equals(holdme.getCoord(), buttonBox.getCoordinaten())){
						System.out.println("you can't do that silly.");
					} else{
						System.out.println("mol added");
						holdme.setCoord(buttonBox.getCoordinaten()); //mol 0 now has coords, MOVE
						this.bs_interface.addMolField(holdme);
						mol_index++;
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}
		
//		buttonArray[0].setOnAction(e->{
//			buttonArray[0]
//			System.out.println("fail");
//		});
		
    	return root;
	}
	
	public void changeLabels(Label lbl, String str){
		lbl.setText(str);
	}
//	@OverrideV
//	public void modelChanged(Bordspel_Interface playable) throws RemoteException {
//		// TODO Auto-generated method stub
//		//
//
//		playable.addObserver(this);
//	}

	@Override
	public void modelChanged(Bordspel_Interface playable) throws RemoteException {
		ArrayList<Speler_Model> spelers = playable.spelModel().getPlayers();
		for (Speler_Model speler:spelers) {
			System.out.println(speler.getPlayer_id());
		}
	}

	public void playerDataTest(ArrayList<Speler_Model> spelers) throws RemoteException{
		for (Speler_Model speler:spelers) {
			System.out.println(speler.getPlayer_id());
		}
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
