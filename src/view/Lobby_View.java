package view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controller.Bordspel_Interface;
import controller.Player_Observer;
import controller.Bordspel_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Speler_Model;

public class Lobby_View extends UnicastRemoteObject implements Player_Observer {

	//what do i need??!?!?!?

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Bordspel_Controller bs_controller;
	Bordspel_Interface bs_interface;
	ObservableList<Speler_Model> data;

	public Lobby_View() throws RemoteException{

	}

	public Lobby_View(Bordspel_Interface bs_interface, Bordspel_Controller bs_controller) throws RemoteException{

		//Add this view to observer list

		this.bs_interface = bs_interface;
		this.bs_controller = bs_controller;

		//view bullshit
		double button_width = 100.0;
		Stage lobbyStage = new Stage();
		VBox vbox_hervat_options = new VBox();
		GridPane grid = new GridPane();
		TableView<Speler_Model> game_table = new TableView<Speler_Model>();

		Button btn_pion = new Button("KIES PION");
		Button btn_kleur = new Button("KIES KLEUR");
		Button btn_klaar = new Button("KLAAR");
		Button btn_refresh = new Button("REFRESH");

		ObservableList<Speler_Model> data = FXCollections.observableArrayList(bs_interface.playerList());
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

		btn_pion.setMaxWidth(button_width);
		btn_kleur.setMaxWidth(button_width);
		btn_refresh.setMaxWidth(button_width);
		btn_klaar.setMaxWidth(button_width);

		vbox_hervat_options.setSpacing(5.0);
		vbox_hervat_options.getChildren().addAll(btn_pion, btn_kleur, btn_klaar, btn_refresh);

		btn_kleur.setOnAction(e->{
			spelbord_view test;
			try {
				test = new spelbord_view();
				test.spelbord_show(bs_controller, bs_interface);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		btn_refresh.setOnAction(e -> {
			try{
				ObservableList<Speler_Model> data_new = FXCollections.observableArrayList(bs_interface.playerList());
				game_table.setItems(data_new);
				this.bs_controller.spelerReady(this.bs_interface.readyList());
			}catch(Exception b){
				b.printStackTrace();
		}});

		btn_klaar.setOnAction(e -> {
			try{
				//new SpelbordView(this.bs_controller, this.bs_interface);
				this.bs_interface.addSpelerReady(game_table.getSelectionModel().getSelectedItem().getMyself());
			}catch(Exception b){
				b.printStackTrace();
		}});

		grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(game_table, 0, 0);
	    grid.add(vbox_hervat_options, 1, 0);

		Scene lobby_scene = new Scene(grid, 380, 250);

		lobbyStage.setTitle("Lobby");
		lobbyStage.setScene(lobby_scene);
		lobbyStage.show();
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
