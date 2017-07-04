package controller;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.*;
import model.Velden.VeldKnop;
import view.DashboardView;
import view.SpelbordView;
import java.rmi.RemoteException;

import static java.sql.DriverManager.println;

/**
 * Zorgt ervoor dat de flow binnen het spel verloopt zoals moet.
 */


public class SpelFlowController{
   private MolController molController = new MolController();
   private Fiche_Controller ficheController = new Fiche_Controller();
   private Bordspel_Controller bsController = new Bordspel_Controller();
   private Playboard_Model playboard_model = new Playboard_Model();
   private Bordspel_Interface bordspel_interface;
   private DashboardView dashboardView;

    public SpelFlowController() {
    }

    public void SpelStart(Bordspel_Interface bs, DashboardView dashboardView) throws RemoteException {
        this.dashboardView=dashboardView;
        this.bordspel_interface=bs;
        bs.setBeurtStatus(BeurtStatus.NEERZETTEN);
        setKnoppenNeerzetten(bs);
    }

    public void setKnoppenNeerzetten(Bordspel_Interface bsInterface) throws RemoteException {
        System.out.println(this.getClass().toString()+": setKnoppenNeerzetten");
        for (final VeldKnop buttonBox : SpelbordView.buttonArray) {
            buttonBox.setOnAction(e -> {
                System.out.println(this.getClass().toString()+": ACTION: NEERZETTEN");
                try {
                    Speler_Model spelerAanDeBeurt = bsInterface.playerList().get(bsInterface.beurtIndex());
                    if (molController.aantalMollen(bsInterface) <= spelerAanDeBeurt.getMol_list().size()) {
                        clearKnoppen();
                        bsInterface.setBeurtStatus(BeurtStatus.FICHEDRAAIEN);
                        System.out.println(this.getClass().toString()+": "+"naar fiche "+BeurtStatus.FICHEDRAAIEN);
                        setFicheknoppenAan(bsInterface);
                        bsInterface.notifyObservers();
                        }
                    else if (molController.magMolNeerzetten(buttonBox,bsInterface,playboard_model)){
                        bsInterface.addMolltoList(buttonBox.getCoordinaten());
                        System.out.println(this.getClass().toString()+": "+"Mollen in lijst " +spelerAanDeBeurt.getMol_list().size());
                        bsInterface.veranderBeurt();
                        System.out.println(this.getClass().toString()+": "+"spelerIndex: " +bsInterface.beurtIndex());
                        System.out.println(this.getClass().toString()+": "+"mol geplaatst en next");
                        bsInterface.notifyObservers();
                    }
                    else {
                        System.out.println(this.getClass().toString()+": "+"Plek bezet of een molshoop");
                    }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    public void setFicheknoppenAan(Bordspel_Interface bs_interface) throws RemoteException {
        System.out.println(this.getClass().toString()+": setFicheKnoppenAan");
        System.out.println(this.getClass().toString()+": "+bs_interface.playerList().get(bs_interface.beurtIndex()).getUsername()+" Is aan de beurt volgens Model.");
            this.dashboardView.getFiche_btn().setOnAction(e -> {
                try {
                    System.out.println(this.getClass().toString() + ": ACTION: FICHE DRAAIEN");

                    Speler_Model speler = bs_interface.playerList().get(bs_interface.beurtIndex());
                    Fiche_Model fiche_list = speler.getFiche_list();

                    ficheController.kiesFiche(fiche_list);
                    System.out.println(this.getClass().toString() + ": " + "FicheNR = " + fiche_list.getFicheNR());
                    DashboardView.fichenrs.appendText(String.valueOf(fiche_list.getFicheNR()) + " : ");
                    System.out.println(this.getClass().toString() + ": " + BeurtStatus.SELECTEREN);
                    setFicheknoppenUit();
                    this.dashboardView.updateFiches(speler);
                    selecteerMolKnoppen(speler, bs_interface);
                    bs_interface.setBeurtStatus(BeurtStatus.SELECTEREN);
                    bs_interface.notifyObservers();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }

            });
    }

    public void setFicheknoppenUit() {
         this.dashboardView.getFiche_btn().setOnAction(e -> System.out.println(this.getClass().toString()+": "+"UIT"));
        }

    
    public void selecteerMolKnoppen(Speler_Model speler, Bordspel_Interface bs_interface) throws RemoteException{
        System.out.println(this.getClass().toString()+": selecteerMolKnoppen");
        for (final VeldKnop buttonBox : SpelbordView.buttonArray){
            buttonBox.setOnAction(e -> {
                System.out.println(this.getClass().toString()+": ACTION: MOL SELECTEREN");
                try {
                 int molIndex = molController.bepaalOfMolAanwezig(speler, buttonBox);
                  if (molIndex == 42){
                      System.out.println(this.getClass().toString()+": Geen mol aanwezig");
                  }
                  else {
                      System.out.println(this.getClass().toString()+": Mol gevonden "+BeurtStatus.SELECTEREN);
                      bs_interface.setBeurtStatus(BeurtStatus.VERPLAATSEN);
                    setEindpuntKnoppen(speler,bs_interface,molIndex);
                    bs_interface.notifyObservers();
                  }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    public void setEindpuntKnoppen (Speler_Model speler, Bordspel_Interface bs_interface,int molIndex) throws RemoteException {
        System.out.println(this.getClass().toString()+": setEindpuntKnoppen");
        System.out.println(this.getClass().toString()+": Selecteer eindpunt");
        for (final VeldKnop buttonBox : SpelbordView.buttonArray) {
            buttonBox.setOnAction(e -> {
                buttonBox.setId(speler.getMol_list().get(molIndex).getKleur()+"PionGroot");
                System.out.println(this.getClass().toString()+": ACTION: VERZETTEN (eindpunt kiezen)");
                try {
                    if(molController.zetGeldig(bs_interface,speler,speler.getMol_list().get(molIndex),buttonBox.getCoordinaten())) {
                        bs_interface.setMolCoord(speler, buttonBox.getCoordinaten(), molIndex);
                        bs_interface.setBeurtStatus(BeurtStatus.FICHEDRAAIEN);
                        System.out.println(this.getClass().toString() + ": pion geplaatst op: " + bs_interface.playerList().get(bs_interface.beurtIndex()).getMol_list().get(molIndex).printCoord());
                        rondeOpruim(speler, bs_interface, molIndex);
                    }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

   public void rondeOpruim(Speler_Model speler, Bordspel_Interface bs_interface, int molIndex) throws RemoteException {
       System.out.println(this.getClass().toString()+": rondeOpruim");
              ficheController.fichesCheck(speler.getFiche_list());
            if(bs_interface.getHuidigeNiveauIndex() >1 && bsController.isSpeciaal(speler.getMol_list().get(molIndex).getCoord(),playboard_model.getHuidigNiveau(bs_interface.getHuidigeNiveauIndex()))){
                System.out.println("SpeciaalVeld gespot");
                clearKnoppen();
                setFicheknoppenAan(bs_interface);
                this.bordspel_interface.notifyObservers();
            }

            if (bs_interface.getHuidigeNiveauIndex() != 4 && molController.molshopenBezetCheck(bs_interface)) {
                System.out.println(" mollen verwijderen die niet op molshoop staan");
                bs_interface.deleteMollfromList();
                System.out.println("Verander niveau");
                bs_interface.changeNiveauInt();
            }
            else if (molController.bepaalOfWinnaar(bs_interface,speler)){
                //einde spel
            }
            clearKnoppen();
            bs_interface.veranderBeurt();
            bs_interface.notifyObservers();
            setFicheknoppenAan(bs_interface);
        }


    public void clearKnoppen() throws RemoteException{
        System.out.println(this.getClass().toString()+": clearKnoppen");
        for (final VeldKnop buttonBox : SpelbordView.buttonArray )
        buttonBox.setOnAction(e -> System.out.println("Disabled"));
        this.bordspel_interface.notifyObservers();
    }



}
