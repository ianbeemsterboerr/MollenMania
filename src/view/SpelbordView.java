package view;

import java.lang.reflect.Array;
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
import model.BeurtStatus;
import model.MolModel;
import model.Playboard_Model;
import model.Speler_Model;
import model.Velden.GoudenSchep_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.SpeciaalVeld_Veld;
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
		
		players = bs_interface.playerList();
		this.bs_interface = bs_interface;
		
//		Button next_stage = new Button("Next!");
//		next_stage.setOnAction(e->{
//			try {
//				
//				bs_interface.changeNiveauInt();
//				System.out.println("Niveau is nu: " + bs_interface.getHuidigeNiveauIndex());
////				System.out.println(bs_interface.molOnField().size());
////				bs_controller.changeNiveau(bs_interface.molOnField(), buttonArray, bs_interface.getHuidigeNiveauIndex());
//				
//			} catch (RemoteException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
		
		veld_pane = this.loadVeld(players);
		spelbord_pane = this.loadPlayers(players, bs_controller, bijnaam);

		spelbord_pane.setTop(this.instInGameView.getView());
		spelbord_pane.setCenter(veld_pane);
		spelbord_pane.setId("nivel1");
		//spelbord_pane.setLeft(next_stage);
		veld_pane.setId("nivel1");
		
		Scene bord = new Scene(spelbord_pane, 1440,810);
		bord.getStylesheets().addAll(this.getClass().getResource("style/SpelbordStyle.css").toExternalForm());
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		//Registreer de view op de server
		try {
			bs_interface.addObserver(this, bordspel_controller.getBijnaam());
			System.out.println(bs_interface.observer_list().size());
		}catch(Exception e){
			e.printStackTrace();
		}

		//bordStage.setX(bounds.getMinX());
		//bordStage.setY(bounds.getMinY());
		//bordStage.setWidth(bounds.getWidth());
		//bordStage.setHeight(bounds.getHeight());
		bordStage.setTitle("play with me");
		bordStage.setScene(bord);
		bordStage.setResizable(false);
		bordStage.show();

        new SpelFlowController().SpelStart(bs_interface);
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
		else if(player_2.getIsYou() == true){
			player_2.getFiche_btn().setDisable(toggle);
			player_2.getMol_btn().setDisable(toggle);
			player_2.getKlaar_btn().setDisable(toggle);
			player_2.getRefresh_btn().setDisable(toggle);
		}
		else if(player_3.getIsYou() == true){
			player_3.getFiche_btn().setDisable(toggle);
			player_3.getMol_btn().setDisable(toggle);
			player_3.getKlaar_btn().setDisable(toggle);
			player_3.getRefresh_btn().setDisable(toggle);
		}
		else if(player_4.getIsYou() == true){
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
		//this.bordspel_controller.loadBoard(buttonArray, bs_interface.molOnField(),new Playboard_Model(), bs_interface.getHuidigeNiveauIndex());

		// init velden wit
		for (VeldKnop veldKnop: buttonArray) {
			veldKnop.setStyle("-fx-background-color: white");
			veldKnop.setDisable(false);
		}
		// Init molshopen
		ArrayList<Molshoop_Veld> molshoop_niveau = new Playboard_Model().getNiveau1().getMolshoop();
		for(Molshoop_Veld m : molshoop_niveau){
			for (VeldKnop veldKnop:this.buttonArray){
				if(Arrays.equals(m.getPositie(), veldKnop.getCoordinaten())){
					veldKnop.setDisable(true);
					veldKnop.setStyle("-fx-background-color: saddlebrown;");
				}
			}
		}
		
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
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*
				 * als een user mag, set coordinaten van zijn mollen, mol_max moet gewijzigd worden met de max aantal mollen 
				 * die speler aan het begin van de spel heeft, ie. variable moet weten hoeveel elke speler mag krijgen.
				 */
				int mol_max = 5;
				mol_index++;
				
				try {
				    //bs_interface.addMolField(mol_geselecteerd);
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
		System.out.println(this.getClass().toString()+": MODELCHANGED"+bordspel_controller.getBijnaam()+" ------------------------------------------------------------------------------");
		boolean jijAanDeBeurt = playable.playerList().get(playable.beurtIndex()).getUsername().trim().equals(bordspel_controller.getBijnaam().trim());

		schoonmakenBord(buttonArray,playable.getBeurtStatus());
		loadGoudenSchep(buttonArray,new Playboard_Model(),playable.getHuidigeNiveauIndex(),playable.getBeurtStatus());
		loadSpecial(buttonArray,new Playboard_Model(),playable.getHuidigeNiveauIndex(),playable.getBeurtStatus());
		loadMolsHoop(buttonArray,new Playboard_Model(),playable.getHuidigeNiveauIndex(),playable.getBeurtStatus());
		loadSpelerMols(buttonArray,playable.playerList(), playable.getBeurtStatus());
		//enableOrDisable(jijAanDeBeurt);
	}

	public void schoonmakenBord(VeldKnop[] buttonArray, BeurtStatus status) throws RemoteException{
		boolean canNotClick = true;
		if(status==BeurtStatus.NEERZETTEN||status==BeurtStatus.VERPLAATSEN){
			canNotClick=false;
		}
		System.out.println(this.getClass().toString()+": schoonmakenBord canNotClick "+canNotClick);
		if(status==BeurtStatus.FICHEDRAAIEN||status==BeurtStatus.NEERZETTEN||status==BeurtStatus.VERPLAATSEN){
			try {
				for (VeldKnop veldKnop: buttonArray) {
					veldKnop.setStyle("-fx-background-color: white");
					veldKnop.setDisable(canNotClick);
					System.out.println(this.getClass().toString()+": schoonmakenBord knop gezett! "+canNotClick);
				}
			}catch (NullPointerException e){
				System.out.println(this.getClass().toString()+": "+e);
			}

		}
	}
	
	public void loadGoudenSchep(VeldKnop[] buttonArray, Playboard_Model pm, int niveau, BeurtStatus status) throws  RemoteException{
		ArrayList<GoudenSchep_Veld> goudenSchep_veld=pm.getHuidigNiveau(niveau).getGoudenSchep();
		boolean canNotClick=true;
		if(status==BeurtStatus.VERPLAATSEN){
			canNotClick=false;
		}
		System.out.println(this.getClass().toString()+": loadGoudenSchep canNotClick "+canNotClick);
		if(goudenSchep_veld.size()!=0&&(status==BeurtStatus.FICHEDRAAIEN||status==BeurtStatus.VERPLAATSEN||status==BeurtStatus.NEERZETTEN)){
			for(GoudenSchep_Veld gouden : goudenSchep_veld){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(gouden.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setDisable(canNotClick);
						buttonArray[x].setStyle("-fx-background-color: darkgoldenrod;");
					}
				}
			}
		}
	}
	
	public void loadSpecial(VeldKnop[] buttonArray, Playboard_Model pm, int niveau, BeurtStatus status) throws RemoteException{
		ArrayList<SpeciaalVeld_Veld> speciaalVeld_velds = pm.getHuidigNiveau(niveau).getSpeciaal();
		boolean canNotClick=true;
		if(status==BeurtStatus.VERPLAATSEN){
			canNotClick=false;
		}
		System.out.println(this.getClass().toString()+": loadSpecial canNotClick "+canNotClick);
		if(status==BeurtStatus.FICHEDRAAIEN||status==BeurtStatus.VERPLAATSEN||status==BeurtStatus.NEERZETTEN){
			for(SpeciaalVeld_Veld speciaal : speciaalVeld_velds){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(speciaal.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setDisable(canNotClick);
						buttonArray[x].setStyle("-fx-background-color: darkcyan;");
					}
				}
			}
		}
	}

	public void loadMolsHoop(VeldKnop[] buttonArray, Playboard_Model pm, int niveau, BeurtStatus status) throws RemoteException{
		ArrayList<Molshoop_Veld> molshoop_niveau = pm.getHuidigNiveau(niveau).getMolshoop();
		boolean canNotClick=true;
		if(status==BeurtStatus.VERPLAATSEN){
			canNotClick=false;
		}
		System.out.println(this.getClass().toString()+": loadMolsHoop canNotClick "+canNotClick);
		if(status==BeurtStatus.FICHEDRAAIEN||status==BeurtStatus.VERPLAATSEN||status==BeurtStatus.NEERZETTEN){
			for(Molshoop_Veld m : molshoop_niveau){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setDisable(canNotClick);
						buttonArray[x].setStyle("-fx-background-color: saddlebrown;");
					}
				}
			}
		}
	}

	public void loadSpelerMols(VeldKnop[] buttonArray, ArrayList<Speler_Model> spelers, BeurtStatus status){
		//set nu alle mollen
		for (Speler_Model speler:spelers) {
			boolean disableMol=false;
//			switch (speler.getKleur()){
//				case "RED":1
//					kleur="red";
//					break;
//				case "GREEN":
//					kleur="green";
//					break;
//				case "YELLOW":
//					kleur="yellow";
//					break;
//				case "BLUE":
//					kleur="blue";
//					break;
//			}
			if(!speler.getUsername().trim().equals(bordspel_controller.getBijnaam().trim())||status!=BeurtStatus.SELECTEREN){
				disableMol=true;
			}
			System.out.println(this.getClass().toString()+": loadSpelerMols disableMol "+disableMol);
			for (MolModel mol: speler.getMol_list()) {
				for (VeldKnop veldKnop:buttonArray) {
					if(Arrays.equals(mol.getCoord(),veldKnop.getCoordinaten())){
						veldKnop.setDisable(disableMol);
						veldKnop.setStyle("-fx-background-color: "+speler.getKleur()+";");
					}
				}
			}
		}
	}

	public void enableOrDisable(boolean jijAanDeBeurt){
		if(!jijAanDeBeurt){
			System.out.println(this.getClass().toString()+": enableOrDisabl "+bordspel_controller.getBijnaam()+" is DISABLED");
			for (VeldKnop veldKnop: buttonArray) {
				veldKnop.setDisable(true);
			}
		}else{
			System.out.println(this.getClass().toString()+": enableOrDisabl "+bordspel_controller.getBijnaam()+" is ENABLED");
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
	@Override
	public String getBijnaam() throws  RemoteException{
		return this.bordspel_controller.getBijnaam();
	}

}
