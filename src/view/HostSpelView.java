package view;

import controller.HostSpelController;
import controller.molserver;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Robert on 6/19/2017.
 */
public class HostSpelView {
    ChoiceBox<String> cb_aantal_spelers;
    private HostSpelController hostSpelController;
    private TextField username;

    public HostSpelView(HostSpelController hostSpelController){
        this.hostSpelController=hostSpelController;
        double button_width = 150.0;
        Stage connectStage = new Stage();
        HBox hbox_connect = new HBox();

        username = new TextField();

        TextField host_name = new TextField();
        Button btn_host = new Button("HOST");
        Button btn_back = new Button("BACK");
        btn_host.setMaxWidth(button_width);
        btn_back.setMaxWidth(button_width);

        hbox_connect.getChildren().addAll(btn_host, btn_back);
        hbox_connect.setAlignment(Pos.CENTER);
        hbox_connect.setSpacing(10.0);

        cb_aantal_spelers = new ChoiceBox<String>(FXCollections.observableArrayList("2", "3", "4"));
        cb_aantal_spelers.setMaxWidth(100.0);

        btn_host.setOnAction(e -> {
            try{
                new molserver();
                connectStage.close();
                new connect_host_view(this.aantalSpelers());
                //System.out.println(this.aantalSpelers());
            }catch(Exception b){
                b.printStackTrace();
            }});

        btn_back.setOnAction(e -> {
            try{
                hostSpelController.getMainMenuController().inschakelenKnoppen();
                connectStage.close();
            }catch(Exception b){
                b.printStackTrace();
            }});

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("MAX AANTAL SPELERS: "), 0, 0);
        grid.add(cb_aantal_spelers, 1, 0);
        grid.add(new Label("USERNAME: "), 0, 1);
        grid.add(username, 1, 1);
        grid.add(btn_host, 1, 2);
        grid.add(btn_back, 2, 2);

        Scene connect_scene = new Scene(grid, 250, 85);
        //Make scene call up style.css for styling
        connect_scene.getStylesheets().addAll(this.getClass().getResource("main_menu_style.css").toExternalForm());
        connectStage.setTitle("Host een spel");
        connectStage.setScene(connect_scene);
        connectStage.show();
    }

    public void host_controls_show(){

    }

    public String aantalSpelers(){
        return this.cb_aantal_spelers.getValue();
    }
}
