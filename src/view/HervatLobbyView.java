package view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import controller.Bordspel_Interface;
import controller.InstellingenPanelController;
import controller.Mol_Client;
import controller.Player_Observer;
import controller.SpelHervattenController;
import controller.Bordspel_Controller;
import javafx.application.Platform;
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

public class HervatLobbyView extends UnicastRemoteObject implements Player_Observer {

	private Label meldingen = new Label();
	Button btn_start = new Button("START");

	private String geselecteerdeKleur;
	private Mol_Client mol_client;
	private static final long serialVersionUID = 1L;
	private Bordspel_Controller bs_controller;
	private Bordspel_Interface bs_interface;
	private ObservableList<Speler_Model> data;
	private TableView<Speler_Model> game_table = new TableView<Speler_Model>(); ;
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

	public HervatLobbyView(Bordspel_Interface bs_interface, Bordspel_Controller bs_controller, Mol_Client mol_client) throws RemoteException{
		//Add this view to observer list
		this.bs_interface = bs_interface;
		this.mol_client=mol_client;
		this.bijnaam=mol_client.getBijnaam();
		this.bs_controller = bs_controller;
		
		try {
			bs_interface.addObserver(this, this.bijnaam);
		}catch(Exception e){
			e.printStackTrace();
		}

		//view bullshit
		double button_width = 100.0;
		Stage lobbyStage = new Stage();
		VBox vbox_hervat_options = new VBox();
		GridPane grid = new GridPane();

		//Belangrijkste knoppen
		Button btn_klaar = new Button();
		btn_start.setMaxWidth(button_width);

		btn_klaar.setId("klaar");

		data = FXCollections.observableArrayList(bs_interface.playerList());

		game_table.setEditable(false);

		TableColumn<Speler_Model, Integer> player_id_col = new TableColumn<Speler_Model, Integer>("PLAYER ID");
		TableColumn<Speler_Model, String> player_name_col = new TableColumn<Speler_Model, String>("PLAYER NAME");
		game_table.setItems(data);
		player_id_col.setMinWidth(25.0);
		player_name_col.setMinWidth(25.0);
		game_table.setMaxWidth(300.0);
		game_table.setMaxHeight(200.0);


		player_id_col.setCellValueFactory(
				new PropertyValueFactory<Speler_Model, Integer>("player_id"));
		player_name_col.setCellValueFactory(
				new PropertyValueFactory<Speler_Model, String>("username"));

		game_table.getColumns().addAll(player_id_col, player_name_col);

		vbox_hervat_options.setSpacing(5.0);
		vbox_hervat_options.getChildren().addAll(btn_klaar,btn_start, meldingen);
		meldingen.setText("Waiting on others to get ready..");

		btn_klaar.setOnAction(e -> {
			btn_klaar.setId("klaarClicked");
			try {
				this.bs_controller.startSpel(btn_klaar, bs_interface.playerList(), this.bijnaam);
				meldingen.setText("Waiting for other players.");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		btn_start.setOnAction(e->{
			try {
				if(this.bs_interface.getHervatSpelersList().size() == this.bs_interface.playerList().size()){
					bs_controller.showSpelBordView(new InstellingenPanelController());
					btn_klaar.setDisable(true);
				} 
				//lobbyStage.close();
			} catch (RemoteException e1) {
				System.out.println(this.getClass().toString()+": "+e1);
			}
		});

		int numRows = 4;

		for (int i = 0; i < numRows; i++) {
			RowConstraints rc = new RowConstraints();
			rc.setPercentHeight(100.0 / numRows);
			rc.setValignment(VPos.BOTTOM);
			grid.getRowConstraints().add(rc);
		}

		ColumnConstraints cc1 = new ColumnConstraints();
		cc1.setHalignment(HPos.CENTER);
		cc1.setPercentWidth(10);
		grid.getColumnConstraints().add(cc1);

		ColumnConstraints cc2 = new ColumnConstraints();
		cc2.setHalignment(HPos.CENTER);
		cc2.setPercentWidth(80);
		grid.getColumnConstraints().add(cc2);

		ColumnConstraints cc3 = new ColumnConstraints();
		cc3.setHalignment(HPos.CENTER);
		cc3.setPercentWidth(10);
		grid.getColumnConstraints().add(cc3);

		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(game_table, 1, 1);
		grid.add(vbox_hervat_options, 1, 3);

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
			ObservableList<Speler_Model> data_new = FXCollections.observableArrayList(playable.playerList());
			game_table.setItems(data_new);
			meldingen.setText(Integer.toString(bs_interface.getHervatSpelersList().size()) + " player(s) ready.");
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
