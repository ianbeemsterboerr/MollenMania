package view;

import controller.Bordspel_Controller;
import controller.Bordspel_Interface;
import controller.Fiche_Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Speler_Model;

import java.util.ArrayList;

/**
 * Created by Robert on 6/22/2017.
 *
 * Heeft nog een manier nodig om te zeggen tegen Bordspel_COntroller of SpelController dat model veranderd is.
 * Moet ook 6 fiches krijgen ipv 1 knop. Elke krijgt een aciotnlistener die naar bordspel_controller verwijst.
 * Mss moet deze klasse STring bijnaam krijgen zodat deze aan Fiche_controller meegegeven kan worden op hetmoment
 * dat je iets verandert, als bewijs van wie je bent?
 *
 * En wtf is dit buttonarray thingy?
 */
public class DashboardView {
    private Bordspel_Controller bordspel_controller;
    private GridPane grid;
    private Speler_Model speler_model;
    private Pos alignment;
    private boolean isYou=false;
    private Fiche_Controller ficheController = new Fiche_Controller();
    private Button fiche_btn = new Button("Fiche");
    private Button mol_btn = new Button("Mol");
    private Button klaar_btn = new Button("Klaar");
    private Button refresh_btn = new Button("Refresh");

    private Button fiche_1 = new Button("1");
    private Button fiche_2 = new Button("2");
    private Button fiche_3 = new Button("3");
    private Button fiche_4 = new Button("4");
    private Button fiche_5 = new Button("5");
    private Button fiche_6 = new Button("6");

    private ArrayList<Button> buttons = new ArrayList<Button>();

    public DashboardView(Speler_Model sm, Bordspel_Controller bs_controller, Pos alignment, String bijnaam){
        this.alignment=alignment;
        this.bordspel_controller = bordspel_controller;
        this.speler_model=sm;

        System.out.println("DashboardView: bijnaam = "+bijnaam+" sm.getUserName = "+sm.getUsername());
        if(sm.getUsername().trim().equals(bijnaam.trim())) {
            this.isYou = true;
            System.out.println("DashboardView: "+bijnaam+" werkt!");
        }
        int ficheNR=0;
        String openFiches = "";
        String speler_naam = sm.getUsername();
        String mol_count = Integer.toString(sm.getMol_list().size());
        String fiche_count = Integer.toString(sm.getFiches().size());

        this.grid = new GridPane();

        Label username_lbl = new Label(speler_naam);
        Label aantal_mol_lbl = new Label(mol_count);
        Label aantal_fiche_lbl = new Label(fiche_count);
        Label open_Fiches = new Label(openFiches);

        username_lbl.setStyle("-fx-font-weight:bold;");
        aantal_fiche_lbl.setStyle("-fx-font-weight:bold;");
        aantal_mol_lbl.setStyle("-fx-font-weight:bold;");

        buttons.add(fiche_btn);
        buttons.add(mol_btn);
        buttons.add(klaar_btn);
        buttons.add(refresh_btn);
        buttons.add(fiche_1);
        buttons.add(fiche_2);
        buttons.add(fiche_3);
        buttons.add(fiche_4);
        buttons.add(fiche_5);
        buttons.add(fiche_6);

        VBox ficheBox = new VBox();
        ficheBox.getChildren().addAll(fiche_1,fiche_2,fiche_3,fiche_4,fiche_5, fiche_6);

        if(!isYou){
            for (Button button:buttons) {
                button.setDisable(true);
            }
        }

        refresh_btn.setOnAction(e->{
            aantal_fiche_lbl.setText(Integer.toString(sm.getFiches().size()));
            aantal_mol_lbl.setText(Integer.toString(sm.getMol_list().size()));
        });


        mol_btn.setOnAction(e->{
            System.out.println("KnopVerwijderen");
//            sm.getMol_list().remove(1);
        });

        grid.add(username_lbl, 0, 0);
        grid.add(mol_btn, 0, 1);
        grid.add(aantal_mol_lbl, 1, 1);
        grid.add(fiche_btn, 0, 2);
        grid.add(aantal_fiche_lbl, 1, 2);
        grid.add(open_Fiches,1,3);
        grid.add(klaar_btn, 0, 3);
        grid.add(refresh_btn, 0, 4);
        grid.add(ficheBox,0,5);
        grid.setHgap(10.0);
        grid.setVgap(10.0);
    }


    public Button getFiche_btn() {
        return fiche_btn;
    }

    public Button getMol_btn() {
        return mol_btn;
    }

    public Button getKlaar_btn() {
        return klaar_btn;
    }

    public Button getRefresh_btn() {
        return refresh_btn;
    }

    public void setFiche_btn(Button fiche_btn) {
        this.fiche_btn = fiche_btn;
    }

    public void setMol_btn(Button mol_btn) {
        this.mol_btn = mol_btn;
    }

    public void setKlaar_btn(Button klaar_btn) {
        this.klaar_btn = klaar_btn;
    }

    public void setRefresh_btn(Button refresh_btn) {
        this.refresh_btn = refresh_btn;
    }


    public void setIsYou(boolean isYou){
        this.isYou = isYou;
    }
    public boolean getIsYou(){
        return isYou;
    }

    public void setDisabled(){

    }


    public GridPane get(){
        return this.grid;
    }
}
