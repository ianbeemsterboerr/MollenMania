package controller;

import javafx.scene.control.Button;
import model.BeurtStatus;
import model.MolModel;
import model.Spelbord_Model;
import model.Velden.VeldKnop;
import view.DashboardView;
import view.SpelbordView;

import java.rmi.RemoteException;

import static view.DashboardView.fiches;

/**
 * Created by Wessel on 26-6-2017.
 */
public class SpelFlowController{
   private MolController molController = new MolController();
   private Fiche_Controller ficheController = new Fiche_Controller();
   private SpelbordController sbController = new SpelbordController();

    public SpelFlowController() {
    }

    public void SpelStart(Bordspel_Interface bs) throws RemoteException {
        // controllers laden en variabelen maken


            //bepaal Welke index de grootste hand heeft.
            //bs.

        //Mollen worden neergezet
        bs.setBeurtStatus(BeurtStatus.NEERZETTEN);
        setKnoppenNeerzetten(bs);


        }





    public void setKnoppenNeerzetten( Bordspel_Interface bsInterface) throws RemoteException {
        for (final VeldKnop buttonBox : SpelbordView.buttonArray) {
            buttonBox.setOnAction(e -> {
                try {
                    if (molController.aantalMollen(bsInterface) < bsInterface.playerList().get(bsInterface.beurtIndex()).getMol_list().size()) {
                        clearKnoppen();
                        setFicheknoppenAan(bsInterface);
                        System.out.println("naar fiche");
                    }
                    else {
                        bsInterface.addMolField(new MolModel(buttonBox.getCoordinaten()));
                        bsInterface.playerList().get(bsInterface.beurtIndex()).getMol_list().add(new MolModel(buttonBox.getCoordinaten()));
                        bsInterface.playerList().get(bsInterface.beurtIndex()).getMol_list().get(0).getCoord();
                        System.out.println(bsInterface.playerList().get(bsInterface.beurtIndex()).getMol_list().size());
                        nextPlayer(bsInterface);
                        System.out.println("spelerIndex: " +bsInterface.beurtIndex());
                        System.out.println("mol geplaatst en next");
                    }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            });
    }
    }


    public void setFicheknoppenAan(Bordspel_Interface bs_interface) {
        for (final Button fiche : DashboardView.fiches) {
            fiche.setOnAction(e -> {
                try {
                    ficheController.kiesFiche(bs_interface.playerList().get(bs_interface.beurtIndex()).getFiche_list());
                    System.out.println(bs_interface.playerList().get(bs_interface.beurtIndex()).getFiche_list().getFicheNR());
//              DashboardView.open_Fiches.setText(openFiches + ", " +String.valueOf(ficheNR));
                    setFicheknoppenUit();
                    setKnoppenMollen(bs_interface);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }


            });
        }
    }

    public void setFicheknoppenUit() {

        for (final Button fiche : DashboardView.fiches) {
            fiche.setOnAction(e -> System.out.println("UIT"));

        }
    }
    public void setKnoppenMollen (Bordspel_Interface bs_interface){
        for (final VeldKnop buttonBox : SpelbordView.buttonArray){
            buttonBox.setOnAction(e -> {
                try {
                  MolModel mol = molController.bepaalOfMolAanwezig(bs_interface, buttonBox);
                  if (mol == null){
                      System.out.println("Geen mol aanwezig");
                  }
                  else {
                    setEindpuntKnoppen(bs_interface,mol);
                  }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }

            });
        }

            }

    public void setEindpuntKnoppen (Bordspel_Interface bs_interface,MolModel mol) {
        for (final VeldKnop buttonBox : SpelbordView.buttonArray) {
            buttonBox.setOnAction(e -> {
                try {
                    if (molController.zetGeldig(bs_interface, mol, buttonBox.getCoordinaten(), 0)) {
                        mol.setCoord(buttonBox.getCoordinaten());
                        rondeOpruim(bs_interface);
                    }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

        public void rondeOpruim(Bordspel_Interface bs_interface) throws RemoteException {
            ficheController.fichesCheck(bs_interface.playerList().get(bs_interface.beurtIndex()).getFiche_list());
            if (sbController.checkMolshopenBezet(bs_interface)) {
                //Clearmollen
                //switchNiveau
            }
            clearKnoppen();
            bs_interface.veranderBeurt();
            //terug naarFiche
        }



    public void clearKnoppen(){
        for (final VeldKnop buttonBox : SpelbordView.buttonArray )
        buttonBox.setOnAction(e -> {
            System.out.println("Disabled");
        });
    }

    public void nextPlayer(Bordspel_Interface bs_interface) throws RemoteException{
        System.out.println("voor veranderbeurt" + bs_interface.beurtIndex());
        bs_interface.veranderBeurt();
        System.out.println("na veranderbeurt" + bs_interface.beurtIndex());
        bs_interface.nextObserver();
        System.out.println("na NextOBserver" + bs_interface.beurtIndex());

    }
}