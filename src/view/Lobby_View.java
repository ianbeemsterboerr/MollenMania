package view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controller.Bordspel_Interface;
import controller.Mol_Client;
import controller.Player_Observer;
import controller.Bordspel_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.BeurtStatus;
import model.Speler_Model;
import model.Velden.VeldKnop;

public class Lobby_View extends UnicastRemoteObject implements Player_Observer {

	private Button btn_blauw = new Button();
	private Button btn_rood = new Button();
	private Button btn_groen = new Button();
	private Button btn_geel = new Button();
	private Label meldingen = new Label();
	private String geselecteerdeKleur;
	private Mol_Client mol_client;
	private static final long serialVersionUID = 1L;
	Bordspel_Controller bs_controller;
	Bordspel_Interface bs_interface;
	ObservableList<Speler_Model> data;
	TableView<Speler_Model> game_table;
	private String bijnaam;

	/**
	 * Lobby's view, here you set some settings for the upcoming game.
	 * @param bs_interface
	 * 			omdat view deel is van de observer lijst wordt een implementatie van interface meegegeven om server side functies te kunnen gebruiken
	 * @param bs_controller
	 * 			om controller werk die model data (of niet) nodig heeft.
	 * @param mol_client
	 * 			als de lobby gemaakt wordt, begint de verbinding met de server.
	 */

	public Lobby_View(Bordspel_Interface bs_interface, Bordspel_Controller bs_controller, Mol_Client mol_client) throws RemoteException{
		//Add this view to observer list
		this.bs_interface = bs_interface;
		this.mol_client=mol_client;
		this.bijnaam=mol_client.getBijnaam();
		try {
			bs_interface.addObserver(this,this.bijnaam);
		}catch(Exception e){
			e.printStackTrace();
		}

		this.bs_controller = bs_controller;

		//view bullshit
		double button_width = 100.0;
		Stage lobbyStage = new Stage();
		VBox vbox_hervat_options = new VBox();
		GridPane grid = new GridPane();
		game_table = new TableView<Speler_Model>();

		//Belangrijkste knoppen
		Slider slider_hand = new Slider();
		Button btn_klaar = new Button("KLAAR");
		Button btn_open = new Button("OPEN");

		//Knoppen die te maken hebben met een kleur
		HBox kleurOpties = new HBox();
		kleurOpties.getChildren().addAll(btn_blauw,btn_geel,btn_groen,btn_rood);


//		slider_hand.setMaxWidth(button_width);
//		kleurOpties.setMaxWidth(button_width);
//		btn_klaar.setMaxWidth(button_width);
		slider_hand.setMin(5);
		slider_hand.setMax(25);
		slider_hand.setValue(15);
		slider_hand.setShowTickLabels(true);
		slider_hand.setShowTickMarks(true);

		data = FXCollections.observableArrayList(bs_interface.playerList());

		game_table.setEditable(false);

		TableColumn<Speler_Model, Integer> player_id_col = new TableColumn<Speler_Model, Integer>("PLAYER ID");
		TableColumn<Speler_Model, String> player_name_col = new TableColumn<Speler_Model, String>("PLAYER NAME");
		game_table.setItems(data);
		player_id_col.setMinWidth(25.0);
		player_name_col.setMinWidth(25.0);
		game_table.setMaxWidth(175.0);
		game_table.setMaxHeight(135.0);


		player_id_col.setCellValueFactory(
				new PropertyValueFactory<Speler_Model, Integer>("player_id"));
		player_name_col.setCellValueFactory(
				new PropertyValueFactory<Speler_Model, String>("username"));

		game_table.getColumns().addAll(player_id_col, player_name_col);


		vbox_hervat_options.setSpacing(5.0);
		vbox_hervat_options.getChildren().addAll(kleurOpties,slider_hand, btn_klaar, meldingen);


		btn_blauw.setOnAction(e->{
			geselecteerdeKleur="blue";
			btn_geel.setId("geel");
			btn_groen.setId("groen");
			btn_rood.setId("rood");
			btn_blauw.setId("blauwClicked");
		});
		btn_geel.setOnAction(e->{
			geselecteerdeKleur="yellow";
			btn_geel.setId("geelClicked");
			btn_groen.setId("groen");
			btn_rood.setId("rood");
			btn_blauw.setId("blauw");
		});
		btn_groen.setOnAction(e->{
			geselecteerdeKleur="green";
			btn_geel.setId("geel");
			btn_groen.setId("groenClicked");
			btn_rood.setId("rood");
			btn_blauw.setId("blauw");
		});
		btn_rood.setOnAction(e->{
			geselecteerdeKleur="red";
			btn_geel.setId("geel");
			btn_groen.setId("groen");
			btn_rood.setId("roodClicked");
			btn_blauw.setId("blauw");
		});

		btn_klaar.setOnAction(e -> {
			btn_klaar.setId("klaarClicked");
			if(geselecteerdeKleur!=null){
				try{
					Speler_Model speler_model;
					for (Speler_Model speler: data) {
						if (speler.getUsername().trim().equals(mol_client.getBijnaam().trim())){
							speler_model=speler;
							speler_model.setHandgrootte((int)slider_hand.getValue());
							speler_model.setKleur(geselecteerdeKleur);

							System.out.println(this.getClass().toString()+" kleur: "+geselecteerdeKleur+" handgrootte: "+slider_hand.getValue());
							if(this.bs_interface.setSpelerReady(speler_model)){
								this.mol_client.naarSpelBord();
							}
						}
					}
					//Speler_Model speler_model = game_table.getSelectionModel().getSelectedItem().getMyself();

				}catch(Exception b){
					b.printStackTrace();
				}
			}else{
				meldingen.setText("Kies eerst een kleur.");
			}
		});

		Button b = new Button("lmao");
		b.setOnAction(e->{
			try {
				this.mol_client.naarSpelBord();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});
		int numCols = 3;
		int numRows = 5;


		for (int i = 0; i < numRows; i++) {
			RowConstraints rc = new RowConstraints();
			rc.setPercentHeight(100.0 / numRows);
			rc.setValignment(VPos.BOTTOM);
			grid.getRowConstraints().add(rc);
		}

		for (int i = 0; i < numCols; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHalignment(HPos.CENTER);
			cc.setPercentWidth(100 / numCols);
			grid.getColumnConstraints().add(cc);
		}

		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(game_table, 1, 1);
		grid.add(vbox_hervat_options, 1, 3);


		btn_geel.setId("geel");
		btn_groen.setId("groen");
		btn_rood.setId("rood");
		btn_blauw.setId("blauw");
		btn_klaar.setId("klaar");
		grid.setId("gridder");
		Scene lobby_scene = new Scene(grid, 400, 540);
		lobby_scene.getStylesheets().addAll(this.getClass().getResource("style/Lobby_View_Style.css").toExternalForm());

		lobbyStage.setTitle("Lobby");
		lobbyStage.setScene(lobby_scene);
		lobbyStage.show();
	}

	/**
	 * Observer methods
	 * @param playable
	 * 		zo kan de observer model informatie van de server ophalen
	 */
	@Override
	public void modelChanged(Bordspel_Interface playable) throws RemoteException {
		ObservableList<Speler_Model> data_new = FXCollections.observableArrayList(bs_interface.playerList());
		try{
			game_table.setItems(data_new);
		} catch(NullPointerException e){

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
	@Override
	public String getBijnaam() throws  RemoteException{
		return this.bijnaam;
	}
}
