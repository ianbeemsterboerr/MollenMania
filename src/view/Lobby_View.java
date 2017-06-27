package view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controller.Bordspel_Interface;
import controller.Mol_Client;
import controller.Player_Observer;
import controller.Bordspel_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.BeurtStatus;
import model.Speler_Model;

public class Lobby_View extends UnicastRemoteObject implements Player_Observer {

	private Button btn_blauw = new Button("Blauw");
	private Button btn_rood = new Button("Rood");
	private Button btn_groen = new Button("Groen");
	private Button btn_geel = new Button("Geel");
	private Label meldingen = new Label();
	private Color geselecteerdeKleur;
	private Mol_Client mol_client;
	private static final long serialVersionUID = 1L;
	Bordspel_Controller bs_controller;
	Bordspel_Interface bs_interface;
	ObservableList<Speler_Model> data;
	TableView<Speler_Model> game_table;

	/**
	 * Lobby's view, here you set some settings for the upcoming game.
	 */

	public Lobby_View(Bordspel_Interface bs_interface, Bordspel_Controller bs_controller, Mol_Client mol_client) throws RemoteException{
		//Add this view to observer list
		this.bs_interface = bs_interface;
		this.mol_client=mol_client;
		try {
			bs_interface.addObserver(this);
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
			geselecteerdeKleur=Color.BLUE;
		});
		btn_geel.setOnAction(e->{
			geselecteerdeKleur=Color.YELLOW;
		});
		btn_groen.setOnAction(e->{
			geselecteerdeKleur=Color.GREEN;
		});
		btn_rood.setOnAction(e->{
			geselecteerdeKleur=Color.RED;
		});
		
		btn_klaar.setOnAction(e -> {
			if(geselecteerdeKleur!=null){
				try{
					Speler_Model speler_model;
					for (Speler_Model speler: data) {
						if (speler.getUsername().trim().equals(mol_client.getBijnaam().trim())){
							speler_model=speler;
							speler_model.setHandgrootte((int)slider_hand.getValue());
							speler_model.setKleur(geselecteerdeKleur.toString());
							
							System.out.println(this.getClass().toString()+" kleur: "+geselecteerdeKleur+" handgrootte: "+slider_hand.getValue());
							if(this.bs_interface.setSpelerReady(speler_model)){
								this.bs_controller.showSpelBordView();
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
				this.bs_controller.showSpelBordView();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		grid.add(b, 0, 2);
		grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(game_table, 0, 0);
	    grid.add(vbox_hervat_options, 0, 1);

		Scene lobby_scene = new Scene(grid, 380, 250);

		lobbyStage.setTitle("Lobby");
		lobbyStage.setScene(lobby_scene);
		lobbyStage.show();
	}

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
}
