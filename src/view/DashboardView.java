package view;

import controller.Bordspel_Controller;
import controller.Fiche_Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Fiche_Model;
import model.Speler_Model;

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
    private Fiche_Controller ficheController = new Fiche_Controller();
    public static Button fiche_btn = new Button("Fiche");
    public static FicheButton[] fiches = new FicheButton[6];
    public static TextField fichenrs = new TextField();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ImageView molPane;

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
        Image molPlaatje = new Image(getClass().getResource("img/"+molPath).toString());
        this.molPane =new ImageView(molPlaatje);

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
        Label open_Fiches = new Label(openFiches);

        username_lbl.setStyle("-fx-font-weight:bold;");
        aantal_fiche_lbl.setStyle("-fx-font-weight:bold;");
        aantal_mol_lbl.setStyle("-fx-font-weight:bold;");

        buttons.add(fiche_btn);

        FicheButton fiche1 = new FicheButton();
        FicheButton fiche2 = new FicheButton();
        FicheButton fiche3 = new FicheButton();
        FicheButton fiche4 = new FicheButton();
        FicheButton fiche5 = new FicheButton();
        FicheButton fiche6 = new FicheButton();

        fiche1.setId("fiche_nietgedraaid");
        fiche2.setId("fiche_nietgedraaid");
        fiche3.setId("fiche_nietgedraaid");
        fiche4.setId("fiche_nietgedraaid");
        fiche5.setId("fiche_nietgedraaid");
        fiche6.setId("fiche_nietgedraaid");

        fiches[0]=fiche1;
        fiches[1]=fiche2;
        fiches[2]=fiche3;
        fiches[3]=fiche4;
        fiches[4]=fiche5;
        fiches[5]=fiche6;

        GridPane ficheGrid = new GridPane();
        ficheGrid.add(fiche1,0,0);
        ficheGrid.add(fiche2,1,0);
        ficheGrid.add(fiche3,0,1);
        ficheGrid.add(fiche4,1,1);
        ficheGrid.add(fiche5,0,2);
        ficheGrid.add(fiche6,1,2);


        VBox mol_en_fichebtnBox = new VBox();
        mol_en_fichebtnBox.getChildren().add(molPane);
        aantal_mol_lbl.setText(Integer.toString(sm.getMol_list().size()));
        mol_en_fichebtnBox.getChildren().add(aantal_mol_lbl);
        mol_en_fichebtnBox.getChildren().add(fiche_btn);

        GridPane stateGrid = new GridPane();
        stateGrid.add(ficheGrid,0,0);
        stateGrid.add(mol_en_fichebtnBox,1,0);

        for (Button fiche:fiches) {
            buttons.add(fiche);
        }

        grid.add(username_lbl, 0, 0);
        grid.add(stateGrid,0,1);
        grid.setHgap(10.0);
        grid.setVgap(10.0);
    }

    /**
     * Geeft de knop waarmee je een fiche() kan draaien.
     *
     * @return Button fiche_btn
     */
    public Button getFiche_btn() {
        return fiche_btn;
    }

    /**
     * Kan de knop waarmee je een fiche kunt draaien zetten.
     *
     * @param fiche_btn
     */
    public void setFiche_btn(Button fiche_btn) {
        this.fiche_btn = fiche_btn;
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
     * Zorgt ervoor dat de knoppen disabled zijn wanneer de speler niet aan de beurt is of wanneer het spelbord niet
     * van de speler is.
     */
    public void setDisabled(){

    }

    /**
     * geeft de gridpane met de inhoud terug zodat SPelBordView hem kan gebruiken.
     * @return
     */
    public GridPane get(){
        return this.grid;
    }

    public void modelChanged(){

    }

    public void updateFiches(Fiche_Model fichesModel){
        String kleur="";
        switch (speler_model.getKleur()){
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
        if(fichesModel.getGeslotenFiche().size()==6){
            for (FicheButton fiche:this.fiches) {
                fiche.setId("fiche_"+kleur+"_nietgedraaid");
            }
        }else{
            for (FicheButton fiche:this.fiches) {
                if(!fiche.isGedraaid()){
                    System.out.println(this.getClass().toString()+": updateFiches - fiche_"+kleur+"_"+fichesModel.getFicheNR());
                    fiche.setId("fiche_"+kleur+"_"+fichesModel.getFicheNR());
                }
            }
        }
    }
}
