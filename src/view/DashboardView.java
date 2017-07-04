package view;

import controller.Bordspel_Controller;
import controller.Bordspel_Interface;
import controller.Fiche_Controller;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.BeurtStatus;
import model.Fiche_Model;
import model.Speler_Model;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Robert on 6/22/2017.
 *
 * Houdt alle knoppen die een speler op het spelbord nodig heeft bij. Houdt ook bij bij welke speler die hoort.
 * Wordt gebruikt in SpelBordView.Een dashboard staat voor 1 speler.
 */
public class DashboardView {
    private Bordspel_Controller bordspel_controller;
    private GridPane grid;
    private Speler_Model speler_model;
    private Pos alignment;
    private boolean isYou=false;
    private Button fiche_btn = new Button("Fiche draaien");
    private Fiche_Controller ficheController = new Fiche_Controller();
    public FicheButton[] fiches = new FicheButton[6];
    public static TextField fichenrs = new TextField();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private int buttonNR = 0;

    /**
     * De constructor, maakt een Grid object met alle knoppen en labels erin en slaat die op.
     * Bepaalt of het DashboardView van de speler is door isYou optioneel op true te zetten.
     *
     * @param sm    Het Speler_Model, laat zien van welke speler het dashboard is.
     * @param bs_controller
     * @param alignment Geeft de positie weer op het bord.
     * @param bijnaam Ter identificatie of het dashboard van jou(degene die het scherm ziet) is.
     *
     * auteur: Robert den Blaauwen
     * Versie: 22-6-2017
     */
    public DashboardView(Speler_Model sm, Bordspel_Controller bs_controller, Pos alignment, String bijnaam){
        this.alignment=alignment;
        this.bordspel_controller = bs_controller;
        this.speler_model=sm;

        String molPath="";
        switch (sm.getKleur()){
            case "red":
                molPath="CLBDSpelerRood.png";
                break;
            case "blue":
                molPath="CLBDSpelerBlauw.png";
                break;
            case "yellow":
                molPath="CLBDSpelerGeel.png";
                break;
            case "green":
                molPath="CLBDSpelerGroen.png";
        }
        System.out.println("DashboardView: bijnaam = "+bijnaam+" sm.getUserName = "+sm.getUsername());

        String openFiches = "";
        String speler_naam = sm.getUsername();
        String mol_count = Integer.toString(sm.getMol_list().size());
        String fiche_count = Integer.toString(sm.getFiche_list().getGeslotenFiche().size());

        this.grid = new GridPane();
        if(sm.getUsername().trim().equals(bijnaam.trim())) {
            this.isYou = true;
            System.out.println("DashboardView: "+bijnaam+" werkt!");
            grid.setStyle("-fx-background-color: "+speler_model.getKleur());
        }

        Label username_lbl = new Label(speler_naam);
        Label aantal_mol_lbl = new Label(mol_count);
        Label aantal_fiche_lbl = new Label(fiche_count);

        username_lbl.setStyle("-fx-font-weight:bold;");
        aantal_fiche_lbl.setStyle("-fx-font-weight:bold;");
        aantal_mol_lbl.setStyle("-fx-font-weight:bold;");

        for (int i = 0; i < 6; i++){
            fiches[i] = new FicheButton();
            fiches[i].setId("Fiche_nietgedraaid");
        }


        GridPane ficheGrid = new GridPane();
        ficheGrid.add(fiches[0],0,0);
        ficheGrid.add(fiches[1],1,0);
        ficheGrid.add(fiches[2],0,1);
        ficheGrid.add(fiches[3],1,1);
        ficheGrid.add(fiches[4],0,2);
        ficheGrid.add(fiches[5],1,2);


        VBox mol_en_fichebtnBox = new VBox();
        aantal_mol_lbl.setText(Integer.toString(sm.getMol_list().size()));
        mol_en_fichebtnBox.getChildren().addAll(aantal_mol_lbl,fiche_btn);

        GridPane stateGrid = new GridPane();
        stateGrid.add(ficheGrid,0,0);
        stateGrid.add(mol_en_fichebtnBox,0,1);

        buttons.add(fiche_btn);

        for (FicheButton fiche:fiches) {
            buttons.add(fiche);
        }
        if(!isYou){
            for (FicheButton fiche:fiches) {
                fiche.setDisable(true);
                fiche.setShape(new Circle(30));
            }
            for (Button button:buttons) {
                button.setDisable(true);
            }
        }

        grid.add(username_lbl, 0, 0);
        grid.add(stateGrid,0,1);
        grid.setHgap(10.0);
        grid.setVgap(10.0);
    }

    /**
     *
     * @return
     * author: Robert den Blaauwen
     * versie: 4-7-2017
     */
    public FicheButton[] getFiches() {
        return fiches;
    }

    /**
     * Geeft de knop waarmee je een fiche() kan draaien.
     *
     * @return Button fiche_btn
     */
    public Button getFiche_btn() {
        return this.fiche_btn;
    }

    /**
     * Geeft de boolean die aangeeft of het dashboard de speler voorstelt.
     *
     * @return boolean isYou
     */
    public boolean getIsYou(){
        return isYou;
    }

    /**
     * geeft de gridpane met de inhoud terug zodat SPelBordView hem kan gebruiken.
     * @return
     */
    public GridPane get(){
        return this.grid;
    }

    public Speler_Model getSpeler_model() {
        return speler_model;
    }

    public void updateFiches(Speler_Model speler, Bordspel_Interface bs_interface) throws RemoteException{
        Platform.runLater(()->{
                System.out.println(this.getClass().toString()+": updateFiches");
                Fiche_Model fichesModel = speler.getFiche_list();

                String kleur="";
                switch (speler.getKleur()){
                    case "blue":
                        kleur="blauw";
                        break;
                    case "yellow":
                        kleur="geel";
                        break;
                    case "green":
                        kleur="groen";
                        break;
                    case "red":
                        kleur="red";
                        break;
                }

                System.out.println(this.getClass().toString()+": updateFiches - fiche op nr zetten");
                int ficheNr=fichesModel.getFicheNR();
                System.out.println(this.getClass().toString()+": updateFiches - fiche_"+kleur+"_"+ficheNr);
                this.fiches[this.buttonNR].setId("fiche_"+kleur+"_"+ficheNr);
                this.fiches[this.buttonNR].setGedraaid(true);
                this.fiches[this.buttonNR].setText(String.valueOf(ficheNr));
                this.buttonNR++;
                if (this.buttonNR == 6){
                    this.buttonNR = 0;
                }
        });

    }
//    /**
//     * Zorgt ervoor dat de knoppen disabled zijn wanneer de speler niet aan de beurt is of wanneer het spelbord niet
//     * van de speler is.
//     */
//    public void setDisabled(boolean disabled){
//
//    }

    public void setToggleFicheEnabled(Bordspel_Interface bs_interface) throws RemoteException{
        boolean disabled=true;
        if(isYou&&bs_interface.getBeurtStatus()==BeurtStatus.FICHEDRAAIEN
                &&bs_interface.playerList().get(bs_interface.beurtIndex()).getUsername().trim().equals(speler_model.getUsername().trim())){
            disabled=false;
        }
        for (Button button:buttons) {
            button.setDisable(disabled);
        }
    }
}

