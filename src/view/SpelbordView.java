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
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import model.Velden.GoudenSchep_Veld;
import model.Velden.Molshoop_Veld;
import model.Velden.SpeciaalVeld_Veld;
import model.Velden.VeldKnop;
import javafx.application.Platform;

/**
 * In deze klasse wordt de view van het spelbord gemaakt.
 */
public class SpelbordView extends UnicastRemoteObject implements Player_Observer{

	private static final long serialVersionUID = 1L;
	private ArrayList<Speler_Model> players;
	private Bordspel_Interface bs_interface;
	private BorderPane dashboard_pane;
	private GridPane veld_pane;
	private Bordspel_Controller bordspel_controller;
	private int mol_index = 0;
	private MolController molController;
	private InstInGameView instInGameView;
	private boolean enabled;
	public static VeldKnop[] buttonArray;
	public Label statusMessage = new Label("Het spelbord laadt...");

	DashboardView player_1;
	DashboardView player_2;
	DashboardView player_3;
	DashboardView player_4;

	/**
	 *
	 * @param bs_controller de bordspel controller
	 * @param bs_interface De RMI interface, bordspel interface
	 * @param bijnaam de bijnaam van de speler
	 * @param instInGameView de instellingen view van het bordspel.
	 * @throws RemoteException
	 */
	public SpelbordView(Bordspel_Controller bs_controller, Bordspel_Interface bs_interface, String bijnaam, InstInGameView instInGameView) throws RemoteException{
		this.instInGameView=instInGameView;
		this.bordspel_controller=bs_controller;
		Stage bordStage = new Stage();
		instInGameView.registerStage(bordStage);
		
		players = bs_interface.playerList();
		this.bs_interface = bs_interface;

		dashboard_pane = this.loadPlayers(players, bs_controller, bijnaam);
		veld_pane = this.loadVeld(players);
		veld_pane.setPrefHeight(700);
		veld_pane.setMaxHeight(700);
		veld_pane.setPrefWidth(700);
		veld_pane.setMaxWidth(700);
		veld_pane.setHgap(0);//31
		veld_pane.setVgap(22);//50
		veld_pane.setPadding(new Insets(65,0,0,-68));
		dashboard_pane.setCenter(veld_pane);

		BorderPane topPane = new BorderPane();

		this.statusMessage.setAlignment(Pos.CENTER);
		this.statusMessage.setId("status-message");
		topPane.setCenter(this.statusMessage);
		topPane.setRight(this.instInGameView.getView());
		dashboard_pane.setTop(topPane);


		dashboard_pane.setId("moap");
//		niveau_pane.setLeft(next_stage);
		veld_pane.setId("moap");

		dashboard_pane.getStyleClass().add("background");
		//niveau_pane.setLeft(next_stage);
		veld_pane.setId("nivel1");

		
		Scene bord = new Scene(dashboard_pane, 1600,900);
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

		bordStage.setTitle("Mollenmania");
		bordStage.setScene(bord);
		bordStage.setResizable(false);
		bordStage.initStyle(StageStyle.UNDECORATED);
		bordStage.show();

		DashboardView you;
		if (player_1!=null&&player_1.getIsYou()){
			you=player_1;
			new SpelFlowController().SpelStart(bs_interface,player_1);
			System.out.println(this.getClass().toString()+": player_1 meegegeven aan SpelFlowController");
		}else if(player_2!=null&&player_2.getIsYou()){
			you=player_2;
			new SpelFlowController().SpelStart(bs_interface,player_2);
			System.out.println(this.getClass().toString()+": player_2 meegegeven aan SpelFlowController");
		}else if(player_3!=null&&player_3.getIsYou()){
			you=player_3;
			new SpelFlowController().SpelStart(bs_interface,player_3);
			System.out.println(this.getClass().toString()+": player_3 meegegeven aan SpelFlowController");
		}else if(player_4!=null&&player_4.getIsYou()){
			you=player_4;
			new SpelFlowController().SpelStart(bs_interface,player_4);
			System.out.println(this.getClass().toString()+": player_4 meegegeven aan SpelFlowController");
		}

        //new SpelFlowController().SpelStart(bs_interface);
	}

	/**
	 *
	 * @param players lijst van spelers die in de model zitten
	 * @param bs_controller bordspel controller om controller werk in de model te doen
	 * @param bijnaam bijnaam is de username die de speler opgeeft aan het begin van de spel
	 * @return krijgt een borderpane terug die in de constructor van de SpelbordView gebruikt wordt.
	 * @throws RemoteException
	 */
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

	/**
	 *
	 * @param players spelers uit de model krijgen
	 * @return krijgt een gridpane terug om in de constructor gebruikt te worden
	 * @throws RemoteException
	 */
	public GridPane loadVeld(ArrayList<Speler_Model> players) throws RemoteException{
		GridPane root = new GridPane();
		int width=40;
		int height=60;
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
			veld.setMinWidth(width);
			veld.setMinHeight(height);
			veld.setStyle("-fx-background-color: transparent;");
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
			veld.setMinWidth(width);
			veld.setMinHeight(height);
			veld.setStyle("-fx-background-color: transparent;");
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
			veld.setMinWidth(width);
			veld.setMinHeight(height);
			veld.setStyle("-fx-background-color: transparent;");
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
			veld.setMinWidth(width);
			veld.setMinHeight(height);
			veld.setStyle("-fx-background-color: transparent;");
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
			veld.setMinWidth(width);
			veld.setMinHeight(height);
			veld.setStyle("-fx-background-color: transparent;");
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
			veld.setMinWidth(width);
			veld.setMinHeight(height);
			veld.setStyle("-fx-background-color: transparent;");
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
			veld.setMinWidth(width);
			veld.setMinHeight(height);
			veld.setStyle("-fx-background-color: transparent;");
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
			veld.setMinWidth(width);
			veld.setMinHeight(height);
			veld.setStyle("-fx-background-color: transparent;");
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
			veld.setMinWidth(width);
			veld.setMinHeight(height);
			veld.setStyle("-fx-background-color: transparent;");
			root.add(veld, column, 9);
			buttonArray[(column + 1) / 2 + 49] = veld;
		}

		// init velden wit
		for (VeldKnop veldKnop: buttonArray) {
			veldKnop.setStyle("-fx-background-color: transparent");
			//veldKnop.setDisable(false);
		}
		// Init molshopen
		ArrayList<Molshoop_Veld> molshoop_niveau = new Playboard_Model().getNiveau1().getMolshoop();
		for(Molshoop_Veld m : molshoop_niveau){
			for (VeldKnop veldKnop:this.buttonArray){
				if(Arrays.equals(m.getPositie(), veldKnop.getCoordinaten())){
					//veldKnop.setDisable(true);
					veldKnop.setStyle("-fx-background-color: transparent;");
				}
			}
		}
    	return root;
	}

	/**
	 * Model changed wordt gebruikt in de Observer Pattern, om veranderingen direct naar alle schermen te sturen
	 * @param playable implementatie van interface om data vanuit te server te kunnen ophalen
	 *            the model that is changed
	 * @throws RemoteException
	 */
	@Override
	public void modelChanged(Bordspel_Interface playable) throws RemoteException {
		//boolean jijAanDeBeurt = playable.playerList().get(playable.beurtIndex()).getUsername().trim().equals(bordspel_controller.getBijnaam().trim());

		// Variabelen maken zodat de server geen miljard keer aangeroepen hoeft te worden.
		Playboard_Model playboard_model = new Playboard_Model();
		String aanDeBeurt = playable.playerList().get(playable.beurtIndex()).getUsername();
		BeurtStatus beurtStatus = playable.getBeurtStatus();
		int niveauIndex = playable.getHuidigeNiveauIndex();
		Niveau_Model huidigNiveau = playboard_model.getHuidigNiveau(niveauIndex);
		ArrayList<Speler_Model> spelers = playable.playerList();

		System.out.println(this.getClass().toString()+": MODELCHANGED "+bordspel_controller.getBijnaam()+" ------------------------------------------------------------------------------");
		System.out.println(this.getClass().toString()+": MODELCHANGED status is "+beurtStatus);

		schoonmakenBord(buttonArray,beurtStatus);
		loadGoudenSchep(buttonArray,huidigNiveau,beurtStatus);
		loadSpecial(buttonArray,huidigNiveau,beurtStatus);
		loadMolsHoop(buttonArray,huidigNiveau,beurtStatus);
		loadSpelerMols(buttonArray,spelers, beurtStatus);
		enableOrDisable(aanDeBeurt, beurtStatus);
		changeLabels(aanDeBeurt,beurtStatus);
		niveauLaden(niveauIndex);

	}

	/**
	 * niveau laden als het tijd is
	 * @param niveauIndex wordt gebruikt om de huidige spel niveau te halen.
	 */
	public void niveauLaden(int niveauIndex){
		Platform.runLater(()->{
			this.veld_pane.getStyleClass().add("nivel"+niveauIndex);
			System.out.println(this.getClass().toString()+": niveauLaden nivel"+niveauIndex);
		});
	}

	/**
	 * deze methode wordt gebruikt om de bord te verversen
	 * @param buttonArray je hebt de knoppen die op de veld staan nodig om ze te manipuleren
	 * @param status enum van status wordt gebruikt om bij te houden in welke state van het spel je nu zit
	 * @throws RemoteException
	 */
	public void schoonmakenBord(VeldKnop[] buttonArray, BeurtStatus status) throws RemoteException{
		boolean canNotClick = true;
		if(status==BeurtStatus.NEERZETTEN||status==BeurtStatus.VERPLAATSEN){
			canNotClick=false;
		}
	//
		// .println(this.getClass().toString()+": schoonmakenBord canNotClick "+canNotClick);
		if(status==BeurtStatus.FICHEDRAAIEN||status==BeurtStatus.NEERZETTEN||status==BeurtStatus.VERPLAATSEN||status==BeurtStatus.BORDSTARTEN){
			try {
				for (VeldKnop veldKnop: buttonArray) {
					//veldKnop.setStyle("-fx-background-color: transparent;");
					veldKnop.setId(" ");
					veldKnop.setDisable(false);
				//	System.out.println(this.getClass().toString()+": schoonmakenBord knop gezett! "+canNotClick);
				}
			}catch (NullPointerException e){
				System.out.println(this.getClass().toString()+": "+e);
			}

		}
	}

	/**
	 * Deze methode wordt gebruikt om de goudenschep te laden, dit is het laatste deel van het spel
	 * @param buttonArray je hebt de knoppen die op de veld staan nodig om ze te manipuleren
	 * @param huidigNiveau de huidige niveau van het spel om te weten waar de knoppen geladen moeten worden
	 * @param status enum van status wordt gebruikt om bij te houden in welke state van het spel je nu zit
	 * @throws RemoteException
	 */
	public void loadGoudenSchep(VeldKnop[] buttonArray, Niveau_Model huidigNiveau, BeurtStatus status) throws  RemoteException{
		ArrayList<GoudenSchep_Veld> goudenSchep_veld=huidigNiveau.getGoudenSchep();
		boolean canNotClick=true;
		if(status==BeurtStatus.VERPLAATSEN){
			canNotClick=false;
		}
//		System.out.println(this.getClass().toString()+": loadGoudenSchep canNotClick "+canNotClick);
		if(goudenSchep_veld.size()!=0&&(status==BeurtStatus.FICHEDRAAIEN||status==BeurtStatus.VERPLAATSEN||status==BeurtStatus.NEERZETTEN)){
			for(GoudenSchep_Veld gouden : goudenSchep_veld){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(gouden.getPositie(), buttonArray[x].getCoordinaten())){
					//	buttonArray[x].setDisable(canNotClick);
						buttonArray[x].setStyle("-fx-background-color: darkgoldenrod;");
					}
				}
			}
		}
	}

	/**
	 * Deze methode wordt gebruikt om de speciale velden op het bord te laden
	 * @param buttonArray je hebt de knoppen die op de veld staan nodig om ze te manipuleren
	 * @param huidigNiveau de huidige niveau van het spel om te weten waar de knoppen geladen moeten worden
	 * @param status enum van status wordt gebruikt om bij te houden in welke state van het spel je nu zit
	 * @throws RemoteException
	 */
	public void loadSpecial(VeldKnop[] buttonArray, Niveau_Model huidigNiveau, BeurtStatus status) throws RemoteException{
		ArrayList<SpeciaalVeld_Veld> speciaalVeld_velds = huidigNiveau.getSpeciaal();
		boolean canNotClick=true;
		if(status==BeurtStatus.VERPLAATSEN){
			canNotClick=false;
		}
//		System.out.println(this.getClass().toString()+": loadSpecial canNotClick "+canNotClick);
		if(status==BeurtStatus.FICHEDRAAIEN||status==BeurtStatus.VERPLAATSEN||status==BeurtStatus.NEERZETTEN){
			for(SpeciaalVeld_Veld speciaal : speciaalVeld_velds){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(speciaal.getPositie(), buttonArray[x].getCoordinaten())){
						//buttonArray[x].setDisable(canNotClick);
						buttonArray[x].setStyle("-fx-background-color: darkcyan;");
					}
				}
			}
		}
	}

	/**
	 * Deze methode wordt gebruikt om de molshopen op het bord te laden
	 * @param buttonArray je hebt de knoppen die op de veld staan nodig om ze te manipuleren
	 * @param huidigNiveau de huidige niveau van het spel om te weten waar de knoppen geladen moeten worden
	 * @param status enum van status wordt gebruikt om bij te houden in welke state van het spel je nu zit
	 * @throws RemoteException
	 */
	public void loadMolsHoop(VeldKnop[] buttonArray, Niveau_Model huidigNiveau, BeurtStatus status) throws RemoteException{
		ArrayList<Molshoop_Veld> molshoop_niveau = huidigNiveau.getMolshoop();
		boolean canNotClick=true;
		if(status==BeurtStatus.VERPLAATSEN){
			canNotClick=false;
		}
		if(status==BeurtStatus.FICHEDRAAIEN||status==BeurtStatus.VERPLAATSEN||status==BeurtStatus.NEERZETTEN){
			for(Molshoop_Veld m : molshoop_niveau){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						//buttonArray[x].setDisable(canNotClick);
						buttonArray[x].setStyle("-fx-background-color: transparent;");
					}
				}
			}
		}
	}

	/**
	 * Deze methode wordt gebruikt om de mollen van de spelers op de bord te plaatsen
	 * @param buttonArray je hebt de knoppen die op de veld staan nodig om ze te manipuleren
	 * @param huidigNiveau de huidige niveau van het spel om te weten waar de knoppen geladen moeten worden
	 * @param status enum van status wordt gebruikt om bij te houden in welke state van het spel je nu zit
	 */
	public void loadSpelerMols(VeldKnop[] buttonArray, ArrayList<Speler_Model> spelers, BeurtStatus status) throws RemoteException{
		//set nu alle mollen
		for (Speler_Model speler:spelers) {
			boolean disableMol=false;

			if(player_1.getSpeler_model().getUsername().trim().equals(speler.getUsername().trim())){
				//player_1.updateFiches(speler,bs_interface);
				player_1.setToggleFicheEnabled(bs_interface);
			}else if(player_2.getSpeler_model().getUsername().trim().equals(speler.getUsername().trim())){
				//player_2.updateFiches(speler,bs_interface);
				player_2.setToggleFicheEnabled(bs_interface);
			}else if(player_3!=null&&player_3.getSpeler_model().getUsername().trim().equals(speler.getUsername().trim())){
				//player_3.updateFiches(speler,bs_interface);
				player_3.setToggleFicheEnabled(bs_interface);
				if(player_4!=null&&player_4.getSpeler_model().getUsername().trim().equals(speler.getUsername().trim())){
					//player_4.updateFiches(speler,bs_interface);
					player_4.setToggleFicheEnabled(bs_interface);
				}
			}

			if(!speler.getUsername().trim().equals(bordspel_controller.getBijnaam().trim())||status!=BeurtStatus.SELECTEREN){
				disableMol=true;
			}
//			System.out.println(this.getClass().toString()+": loadSpelerMols disableMol "+disableMol);
			for (MolModel mol: speler.getMol_list()) {
				for (VeldKnop veldKnop:buttonArray) {
					if(Arrays.equals(mol.getCoord(),veldKnop.getCoordinaten())){
						//veldKnop.setDisable(disableMol);
						//veldKnop.setStyle("-fx-background-color: "+speler.getKleur()+";");
						veldKnop.setStyle("-fx-background-color: transparent;");
						veldKnop.setId(speler.getKleur()+"Pion");
					}
				}
			}
		}
	}

	/**
	 * Deze methode wordt gebruikt om de knoppen aan en uit te zetteen als het jou beurt is
	 * @param aanDeBeurt houdt de index bij van wie aan de beurt is
	 * @param beurtStatus enum om status van het spel bij de houden
	 */
	public void enableOrDisable(String aanDeBeurt, BeurtStatus beurtStatus){

		boolean jijAanDeBeurt = aanDeBeurt.trim().equals(this.bordspel_controller.getBijnaam().trim());
		if(!jijAanDeBeurt||beurtStatus==BeurtStatus.BORDSTARTEN){
			System.out.println(this.getClass().toString()+": enableOrDisabl "+bordspel_controller.getBijnaam()+" is DISABLED");
			for (VeldKnop veldKnop: buttonArray) {
				veldKnop.setDisable(true);
			}
		}else{
			System.out.println(this.getClass().toString()+": enableOrDisabl "+bordspel_controller.getBijnaam()+" is ENABLED");
		}

	}

	/**
	 * Zorgt ervoor dat er een boodschap bovenaan het scherm komt te staan dat de speler verteld wat hij moet doen
	 * en wie er aan de beurt is.
	 *
	 * @param beurtStatus
	 * @param aanDeBeurt
	 *
	 * Author	Robert den Blaauwen
	 * Versie	1-7-017
	 */
	public void changeLabels(String aanDeBeurt,BeurtStatus beurtStatus){
		//Gebruikt runLater() om RMI errors te voorkomen. javaFX kan er nameljk niet tegen als hij wordt aangeroepen via RMI
		Platform.runLater(()->{
			String beurtMessage;
			String statusMessage;
			if(beurtStatus==BeurtStatus.BORDSTARTEN){
				beurtMessage="";
				statusMessage="Wacht tot alle spelers in het spel zitten.";
			}else{
				if(aanDeBeurt.trim().equals(this.bordspel_controller.getBijnaam().trim())){
					beurtMessage="Jij bent aan de beurt.";
					switch (beurtStatus){
						case NEERZETTEN:
							statusMessage="Zet een mol neer door op een van de velden te klikken.";
							break;
						case FICHEDRAAIEN:
							statusMessage="Draai een fiche.";
							break;
						case SELECTEREN:
							statusMessage="Selecteer een mol door er op een te klikken.";
							break;
						case VERPLAATSEN:
							statusMessage="Verplaats een mol door op een leeg veld of een molshoop te stappen.";
							break;
						default:
							statusMessage="Er ging iets mis met het ophalen van het status bericht.";
					}
				}else{
					beurtMessage=aanDeBeurt;
					switch (beurtStatus){
						case NEERZETTEN:
							statusMessage="is een mol aan het neerzetten.";
							break;
						case FICHEDRAAIEN:
							statusMessage="is een fiche aan het draaien.";
							break;
						case SELECTEREN:
							statusMessage="is een mol aan het selecteren.";
							break;
						case VERPLAATSEN:
							statusMessage="is een mol aan het verplaatsen.";
							break;
						default:
							statusMessage="Er ging iets mis met het ophalen van het status bericht.";
					}
				}
			}
			this.statusMessage.setText(beurtMessage+" "+statusMessage);
		});
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
