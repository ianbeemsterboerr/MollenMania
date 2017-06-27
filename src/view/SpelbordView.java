package view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import controller.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
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
	private Bordspel_Interface bs_interface;
	private BorderPane spelbord_pane;
	private GridPane veld_pane;
	private Bordspel_Controller bordspel_controller;
	private int mol_index = 0;
	private MolController molController;
	private InstInGameView instInGameView;
	private boolean enabled;

	public static VeldKnop[] buttonArray;

	DashboardView player_1;
	DashboardView player_2;
	DashboardView player_3;
	DashboardView player_4;
	
	public SpelbordView(Bordspel_Controller bs_controller, Bordspel_Interface bs_interface, MolController molController, Fiche_Controller fiche_controller, String bijnaam) throws RemoteException{
		this.molController=molController;
	}
	
	public SpelbordView(Bordspel_Controller bs_controller, Bordspel_Interface bs_interface, String bijnaam, InstInGameView instInGameView) throws RemoteException{
		this.instInGameView=instInGameView;
		this.bordspel_controller=bs_controller;
		Stage bordStage = new Stage();
		instInGameView.registerStage(bordStage);
		
		try {
			bs_interface.addObserver(this);
			System.out.println(bs_interface.observer_list().size());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		players = bs_interface.playerList();
		this.bs_interface = bs_interface;
		
		veld_pane = this.loadVeld(players);
		spelbord_pane = this.loadPlayers(players, bs_controller, bijnaam);

		spelbord_pane.setTop(this.instInGameView.getView());
		spelbord_pane.setCenter(veld_pane);
		spelbord_pane.setId("moap");
		veld_pane.setId("moap");
		
		Scene bord = new Scene(spelbord_pane, 1440,900);
		bord.getStylesheets().addAll(this.getClass().getResource("style/SpelbordStyle.css").toExternalForm());
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		//
		//bordStage.setX(bounds.getMinX());
		//bordStage.setY(bounds.getMinY());
		//bordStage.setWidth(bounds.getWidth());
		//bordStage.setHeight(bounds.getHeight());
		bordStage.setTitle("play with me");
		bordStage.setScene(bord);
		bordStage.setResizable(false);
		bordStage.show();
	}

	public BorderPane loadPlayers(ArrayList<Speler_Model> players, Bordspel_Controller bs_controller, String bijnaam) throws RemoteException{

		VBox left = new VBox();
		left.setPadding(new Insets(20, 20, 20, 20));
		left.setSpacing(350.0);
		VBox right = new VBox();
		right.setPadding(new Insets(20, 20, 20, 20));
		right.setSpacing(350.0);
		
		if(players.size() == 2){
			this.player_1 = new DashboardView(players.get(0),bs_controller, Pos.TOP_LEFT, bijnaam);
			this.player_2 = new DashboardView(players.get(1),bs_controller, Pos.TOP_RIGHT, bijnaam);
			left.getChildren().addAll(player_1.get());
			right.getChildren().addAll(player_2.get());

		} else if(players.size() == 3){
			this.player_1 = new DashboardView(players.get(0),bs_controller, Pos.TOP_LEFT, bijnaam);
			this.player_2 = new DashboardView(players.get(1),bs_controller, Pos.TOP_RIGHT, bijnaam);
			this.player_3 = new DashboardView(players.get(2),bs_controller, Pos.BOTTOM_LEFT, bijnaam);
			left.getChildren().addAll(player_1.get(), player_3.get());
			right.getChildren().addAll(player_2.get());

		} else if(players.size() == 4){
			this.player_1 = new DashboardView(players.get(0),bs_controller, Pos.TOP_LEFT, bijnaam);
			this.player_2 = new DashboardView(players.get(1),bs_controller, Pos.TOP_RIGHT, bijnaam);
			this.player_3 = new DashboardView(players.get(2),bs_controller, Pos.BOTTOM_LEFT, bijnaam);
			this.player_4 = new DashboardView(players.get(3),bs_controller, Pos.BOTTOM_RIGHT, bijnaam);
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

	public void disableProperty(boolean toggle){
		for (int i = 0; i < buttonArray.length; i++){
			buttonArray[i].setDisable(true);
		}
		if(player_1.getIsYou() == true){
			player_1.getFiche_btn().setDisable(toggle);
			player_1.getMol_btn().setDisable(toggle);
			player_1.getKlaar_btn().setDisable(toggle);
			player_1.getRefresh_btn().setDisable(toggle);
		}
		if(player_2.getIsYou() == true){
			player_2.getFiche_btn().setDisable(toggle);
			player_2.getMol_btn().setDisable(toggle);
			player_2.getKlaar_btn().setDisable(toggle);
			player_2.getRefresh_btn().setDisable(toggle);
		}
		if(player_3.getIsYou() == true){
			player_3.getFiche_btn().setDisable(toggle);
			player_3.getMol_btn().setDisable(toggle);
			player_3.getKlaar_btn().setDisable(toggle);
			player_3.getRefresh_btn().setDisable(toggle);
		}
		if(player_4.getIsYou() == true){
			player_4.getFiche_btn().setDisable(toggle);
			player_4.getMol_btn().setDisable(toggle);
			player_4.getKlaar_btn().setDisable(toggle);
			player_4.getRefresh_btn().setDisable(toggle);
		}
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
		int x = 4;
		int y = 0;
		int z = -4;
		// loop voor buttons 1 t/m 5
		for (int column = 13; column < 23; column = column + 2) {
			VeldKnop veld = new VeldKnop(x , y, z);
			x--;
			y++;
			root.add(veld, column, 1);
			buttonArray[(column + 1) / 2 - 7] = veld;
		}
		x = 4;
		y = -1;
		z = -3;
		// loop voor buttons 6 t/m 11
		for (int column = 12; column < 24; column = column + 2) {
			VeldKnop veld = new VeldKnop(x , y, z);
			x--;
			y++;
			root.add(veld, column, 2);
			buttonArray[column / 2 - 1] = veld;
		}
		x = 4;
		y = -2;
		z = -2;
		// loop voor buttons 12 t/m 18
		for (int column = 11; column < 25; column = column + 2) {
			VeldKnop veld = new VeldKnop(x , y, z);
			x--;
			y++;
			root.add(veld, column, 3);
			buttonArray[(column + 1) / 2 + 5] = veld;
		}
		x = 4;
		y = -3;
		z = -1;
		// loop voor buttons 19 t/m 26
		for (int column = 10; column < 25; column = column + 2) {
			VeldKnop veld = new VeldKnop(x , y, z);
			x--;
			y++;
			root.add(veld, column, 4);
			buttonArray[column / 2 + 13] = veld;
		}
		x = 4;
		y = -4;
		z = 0;
		// loop voor buttons 27 t/m 35
		for (int column = 9; column < 26; column = column + 2) {
			VeldKnop veld = new VeldKnop(x , y, z);
			x--;
			y++;
			root.add(veld, column, 5);
			buttonArray[(column + 1) / 2 + 21] = veld;
		}
		x = 3;
		y = -4;
		z = 1;
		// loop voor buttons 36 t/m 43
		for (int column = 10; column < 25; column = column + 2) {
			VeldKnop veld = new VeldKnop(x , y, z);
			x--;
			y++;
			root.add(veld, column, 6);
			buttonArray[column / 2 + 30] = veld;
		}
		x = 2;
		y = -4;
		z = 2;
		// buttons 44 t/m 50
		for (int column = 11; column < 25; column = column + 2) {
			VeldKnop veld = new VeldKnop(x , y, z);
			x--;
			y++;
			root.add(veld, column, 7);
			buttonArray[(column + 1) / 2 + 37] = veld;
		}
		x = 1;
		y = -4;
		z = 3;
		// buttons 51 t/m 56
		for (int column = 12; column < 24; column = column + 2) {
			VeldKnop veld = new VeldKnop(x , y, z);
			x--;
			y++;
			root.add(veld, column, 8);
			buttonArray[column / 2 + 44] = veld;
		}
		x = 0;
		y = -4;
		z = 4;
		// buttons 57 t/m 61
		for (int column = 13; column < 23; column = column + 2) {
			VeldKnop veld = new VeldKnop(x , y, z);
			x--;
			y++;
			root.add(veld, column, 9);
			buttonArray[(column + 1) / 2 + 49] = veld;
		}
		
		/*
		 * load board.
		 * VOILAAA
		 * check niveau to determine list to be added. - check
		 * should be rewritten in a better function outside of this class. - check
		 */
		
		this.bordspel_controller.loadBoard(buttonArray, bs_interface.molOnField(), bs_interface.pm(), bs_interface.getHuidigeNiveauIndex());
		
		/*
		 * final used to be used inside lamba. reason: jah knows.
		 * all buttons become "active" in this for loop
		 */
		for(int i=0;i<this.buttonArray.length;i++){
			final VeldKnop buttonBox;
			buttonBox = buttonArray[i];
			buttonBox.setOnAction(e->{
				try {
					this.bordspel_controller.clickAction(buttonBox.getCoordinaten());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				/*
				 * button changes
				 * let's check if there are any buttons in the onboard list 
				 * and we will register them on the board.
				 */
				buttonBox.setDisable(true);
				buttonBox.setBezet(true);
				buttonBox.setStyle("-fx-background-color: #ff0000;");
				/*
				 * model changes
				 * to be done:
				 * 		1. whose turn is it?
				 */
				Speler_Model player_aanDeBeurt = new Speler_Model();
				MolModel mol_geselecteerd = new MolModel();
				/*
				 * 1. we must get whose turn it is.
				 * 2. use that motherfucker to play, until he is done with his mols
				 * 
				 * PHASE 2:
				 * 
				 */
				try {
					// WE ARE USING YOU WHOEVER YOU ARE
					System.out.println(this.getClass().toString()+": Player " + bs_interface.beurtIndex() + " is aan de beurt.");
					player_aanDeBeurt = players.get(bs_interface.beurtIndex());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*
				 * als een user mag, set coordinaten van zijn mollen, mol_max moet gewijzigd worden met de max aantal mollen 
				 * die speler aan het begin van de spel heeft, ie. variable moet weten hoeveel elke speler mag krijgen.
				 */
				int mol_max = 5;
				this.bordspel_controller.setMolCoords(player_aanDeBeurt, mol_geselecteerd, buttonBox, mol_max, mol_index);
				mol_index++;
				try {
					bs_interface.notifyObservers();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
		}
		
    	return root;
	}
	
	public void changeLabels(Label lbl, String str){
		lbl.setText(str);
	}

	@Override
	public void modelChanged(Bordspel_Interface playable) throws RemoteException {
		this.bordspel_controller.loadBoard(buttonArray, bs_interface.molOnField(), bs_interface.pm(), bs_interface.getHuidigeNiveauIndex());
		System.out.println(this.getClass().toString()+": beurt: "+bs_interface.beurtIndex());
		disableProperty(enabled);
	}

	public void playerDataTest(ArrayList<Speler_Model> spelers) throws RemoteException{
		for (Speler_Model speler:spelers) {
			System.out.println(speler.getPlayer_id());
		}
	}

	@Override
	public boolean isEnabled() throws RemoteException {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) throws RemoteException {
		this.enabled = enabled;
		
	}
}
