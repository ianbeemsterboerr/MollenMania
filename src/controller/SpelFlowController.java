package controller;

import javafx.scene.control.Button;
import model.*;
import model.Velden.VeldKnop;
import view.DashboardView;
import view.SpelbordView;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Wessel on 26-6-2017.
 */


public class SpelFlowController{
   private MolController molController = new MolController();
   private Fiche_Controller ficheController = new Fiche_Controller();
   private SpelbordController sbController = new SpelbordController();
   private Bordspel_Controller bsController = new Bordspel_Controller();
   private Playboard_Model playboard_model = new Playboard_Model();

    public SpelFlowController() {
    }

    public void SpelStart(Bordspel_Interface bs) throws RemoteException {
        // controllers laden en variabelen maken


            //bepaal Welke index de grootste hand heeft.
            //bs.

        //Mollen worden neergezet
        setKnoppenNeerzetten(bs);


        }

    public void setKnoppenNeerzetten( Bordspel_Interface bsInterface) throws RemoteException {
        bsInterface.setBeurtStatus(BeurtStatus.NEERZETTEN);
        for (final VeldKnop buttonBox : SpelbordView.buttonArray) {
            buttonBox.setOnAction(e -> {
                try {
                    if (molController.aantalMollen(bsInterface) <= bsInterface.playerList().get(bsInterface.beurtIndex()).getMol_list().size()) {
                        clearKnoppen();
                        setFicheknoppenAan(bsInterface.playerList().get(bsInterface.beurtIndex()), bsInterface);
                        System.out.println(this.getClass().toString()+": "+"naar fiche");
                        }
                    else if (molController.magMolNeerzetten(buttonBox,bsInterface,playboard_model)){
//                        bsInterface.playerList().get(bsInterface.beurtIndex()).addMolltoList(buttonBox.getCoordinaten());
                        bsInterface.addMolltoList(buttonBox.getCoordinaten());
                        System.out.println(this.getClass().toString()+": "+"Mollen in lijst " +bsInterface.playerList().get(bsInterface.beurtIndex()).getMol_list().size());
                        nextPlayer(bsInterface);
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

    public void setFicheknoppenAan(Speler_Model speler,Bordspel_Interface bs_interface) throws RemoteException {
        bs_interface.setBeurtStatus(BeurtStatus.FICHEDRAAIEN);
        System.out.println(this.getClass().toString()+": "+speler.getUsername() +" Is aan de beurt");
        for (final Button fiche : DashboardView.fiches) {
            fiche.setOnAction(e -> {
                try {
                ficheController.kiesFiche(speler.getFiche_list());
                System.out.println(this.getClass().toString()+": "+"FicheNR = "+ speler.getFiche_list().getFicheNR());
//              DashboardView.open_Fiches.setText(openFiches + ", " +String.valueOf(ficheNR));
                setFicheknoppenUit();
                selecteerMolKnoppen(speler,bs_interface);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    public void setFicheknoppenUit() {

        for (final Button fiche : DashboardView.fiches) {
            fiche.setOnAction(e -> System.out.println(this.getClass().toString()+": "+"UIT"));

        }
    }
    
    public void selecteerMolKnoppen(Speler_Model speler, Bordspel_Interface bs_interface) throws RemoteException{
        bs_interface.setBeurtStatus(BeurtStatus.SELECTEREN);
        for (final VeldKnop buttonBox : SpelbordView.buttonArray){
            buttonBox.setOnAction(e -> {
                try {
                 int molIndex = molController.bepaalOfMolAanwezig(speler, buttonBox);
                  if (molIndex == 42){
                      System.out.println(this.getClass().toString()+": "+"Geen mol aanwezig");
                  }
                  else {
                      System.out.println(this.getClass().toString()+": "+"Mol gevonden");
                      setEindpuntKnoppen(speler,bs_interface,molIndex);
                  }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }

            });
        }

            }

    public void setEindpuntKnoppen (Speler_Model speler, Bordspel_Interface bs_interface,int molIndex) throws RemoteException {
        bs_interface.setBeurtStatus(BeurtStatus.VERPLAATSEN);
        System.out.println("Selecteer eindpunt");
        for (final VeldKnop buttonBox : SpelbordView.buttonArray) {
            buttonBox.setOnAction(e -> {
                try {
                    if (molController.zetGeldig(bs_interface, speler,speler.getMol_list().get(molIndex), buttonBox.getCoordinaten())) {
                        bs_interface.setMolCoord(molIndex,buttonBox.getCoordinaten(),speler);
                        bs_interface.notifyObservers();
                        System.out.println("pion geplaatst op: " +speler.getMol_list().get(molIndex).getCoord());
                        rondeOpruim(speler,bs_interface);
                    }
                    else {
                        System.out.println("Zet Ongeldig");
                    }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

   public void rondeOpruim(Speler_Model speler, Bordspel_Interface bs_interface) throws RemoteException {
              ficheController.fichesCheck(speler.getFiche_list());
//            if (sbController.checkMolshopenBezet(bs_interface)) {
//                //Clearmollen
//                //switchNiveau
//            }
            clearKnoppen();
            bs_interface.veranderBeurt();
            bs_interface.notifyObservers();
            setFicheknoppenAan(bs_interface.playerList().get(bs_interface.beurtIndex()),bs_interface);
        }

    public void clearKnoppen(){
        for (final VeldKnop buttonBox : SpelbordView.buttonArray )
        buttonBox.setOnAction(e -> System.out.println("Disabled"));
    }

    public void nextPlayer(Bordspel_Interface bs_interface) throws RemoteException{
        bs_interface.veranderBeurt();
        System.out.println("na veranderbeurt" + bs_interface.beurtIndex());
        bs_interface.nextObserver();

    }
}
