package controller;

import javafx.scene.control.Button;
import model.*;
import model.Velden.VeldKnop;
import view.DashboardView;
import view.SpelbordView;
import java.rmi.RemoteException;

/**
 * Zorgt ervoor dat de flow binnen het spel verloopt zoals moet.
 */


public class SpelFlowController{
   private MolController molController = new MolController();
   private Fiche_Controller ficheController = new Fiche_Controller();
   private Bordspel_Controller bsController = new Bordspel_Controller();
   private Playboard_Model playboard_model = new Playboard_Model();
   private Bordspel_Interface bordspel_interface;

    public SpelFlowController() {
    }

    public void SpelStart(Bordspel_Interface bs) throws RemoteException {
        this.bordspel_interface=bs;
        bs.setBeurtStatus(BeurtStatus.NEERZETTEN);
        // controllers laden en variabelen maken


            //bepaal Welke index de grootste hand heeft.
            //bs.

        //Mollen worden neergezet
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
                        setFicheknoppenAan(spelerAanDeBeurt, bsInterface);
                        bsInterface.notifyObservers();
                        }
                    else if (molController.magMolNeerzetten(buttonBox,bsInterface,playboard_model)){
//                        bsInterface.playerList().get(bsInterface.beurtIndex()).addMolltoList(buttonBox.getCoordinaten());
                        bsInterface.addMolltoList(buttonBox.getCoordinaten());
                        System.out.println(this.getClass().toString()+": "+"Mollen in lijst " +spelerAanDeBeurt.getMol_list().size());
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
        System.out.println(this.getClass().toString()+": setFicheKnoppenAan");

        System.out.println(this.getClass().toString()+": "+speler.getUsername() +" Is aan de beurt");
        System.out.println(this.getClass().toString()+": "+bs_interface.playerList().get(bs_interface.beurtIndex()).getUsername()+" Is aan de beurt volgens Model.");
//        for (final Button fiche : DashboardView.fiches) {
            DashboardView.fiche_btn.setOnAction(e -> {
                Fiche_Model fiche_list = speler.getFiche_list();
                System.out.println(this.getClass().toString()+": ACTION: FICHE DRAAIEN");
                try {
                    ficheController.kiesFiche(fiche_list);
                    System.out.println(this.getClass().toString()+": "+"FicheNR = "+ fiche_list.getFicheNR());
                    DashboardView.fichenrs.appendText(String.valueOf(fiche_list.getFicheNR()) +" : ");
                    bs_interface.setBeurtStatus(BeurtStatus.SELECTEREN);
                    System.out.println(this.getClass().toString()+": "+BeurtStatus.SELECTEREN);
                    setFicheknoppenUit();
                    selecteerMolKnoppen(speler,bs_interface);
                bs_interface.notifyObservers(); //vervangen door notifySelf()?
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            });
    }



    public void setFicheknoppenUit() {
        for (final Button fiche : DashboardView.fiches) {
            fiche.setOnAction(e -> System.out.println(this.getClass().toString()+": "+"UIT"));
        }
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
                    bs_interface.notifyObservers(); //vervangen door notifySelf()?
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
                System.out.println(this.getClass().toString()+": ACTION: VERZETTEN (eindpunt kiezen)");
                try {
                    if(molController.zetGeldig(bs_interface,speler,speler.getMol_list().get(molIndex),buttonBox.getCoordinaten())) {
                        bs_interface.setMolCoord(speler, buttonBox.getCoordinaten(), molIndex);
                        bs_interface.setBeurtStatus(BeurtStatus.FICHEDRAAIEN);
                        //bs_interface.notifyObservers();
                        System.out.println(this.getClass().toString() + ": pion geplaatst op: " + bs_interface.playerList().get(bs_interface.beurtIndex()).getMol_list().get(molIndex).printCoord());
                        rondeOpruim(speler, bs_interface);
                    }

                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

   public void rondeOpruim(Speler_Model speler, Bordspel_Interface bs_interface) throws RemoteException {
       System.out.println(this.getClass().toString()+": rondeOpruim");
              ficheController.fichesCheck(speler.getFiche_list());
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
            this.nextPlayer(bs_interface);
            bs_interface.notifyObservers();
            setFicheknoppenAan(bs_interface.playerList().get(bs_interface.beurtIndex()),bs_interface);
        }


    public void clearKnoppen() throws RemoteException{
        System.out.println(this.getClass().toString()+": clearKnoppen");
        for (final VeldKnop buttonBox : SpelbordView.buttonArray )
        buttonBox.setOnAction(e -> System.out.println("Disabled"));
        this.bordspel_interface.notifyObservers();
    }

    public void nextPlayer(Bordspel_Interface bs_interface) throws RemoteException{
        System.out.println(this.getClass().toString()+": nextPLayer");
        System.out.println("voor veranderbeurt" + bs_interface.beurtIndex());
        bs_interface.veranderBeurt();
        System.out.println("na veranderbeurt" + bs_interface.beurtIndex());
    }

    /**
     * Een ideetje dat ik heb. Deze geeft veranderingen door aan SpelBordView zonder dat de views van andere spelers ook
     * geupdate worden. Deze is bruikbaar bij dingen zoals fiche draaien, mol selecteren.
     *
     * Auteur: Robert den Blaauwen
     * Versie: 28-6-2017
     */
    public void notifySelf(){

    }
}
