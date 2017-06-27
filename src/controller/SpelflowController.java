package controller;

import model.BeurtStatus;
import model.MolModel;
import model.Spelbord_Model;
import model.Velden.VeldKnop;
import view.SpelbordView;

import java.rmi.RemoteException;

/**
 * Created by Wessel on 26-6-2017.
 */
public class SpelflowController {
   private MolController molController = new MolController();
   private Bordspel_Controller bsController = new Bordspel_Controller();
   private boolean loopControl = false;

    public void SpelStart(Bordspel_Interface bs) throws RemoteException {
        // controllers laden en variabelen maken

        int playerIndex = 0;
        //bepaal Welke index de grootste hand heeft.
        //bs.

        //Mollen worden neergezet
        bs.setBeurtStatus(BeurtStatus.NEERZETTEN);
//        setKnoppenNeerzetten(bsController,bs);


        }





//    public void setKnoppenNeerzetten(Bordspel_Controller bsController, Bordspel_Interface bsInterface) throws RemoteException {
//        for (final VeldKnop buttonBox : SpelbordView.buttonArray) {
//            buttonBox.setOnAction(e -> {
//
//                try {
//                    if (molController.aantalMollen(bsInterface) > bsInterface.playerList().get(bsInterface.getBeurtIndex()).getMol_list().size()) {
//                        //nextShit
//                        System.out.println("NextButton");
//                    }
//                    else{
//                            bsController.setMolCoords(bsInterface.playerList().get(bsInterface.getBeurtIndex()),buttonBox,molController.aantalMollen(bsInterface));
//                            beurtNext(bsInterface);
//                        System.out.println("mol geplaatst en next");
//                        }
//                } catch (RemoteException e1) {
//                    e1.printStackTrace();
//                }
//
//
//            });
//    }
//    }

    public void setKnoppenMollen (Bordspel_Interface bs_interface){
        for (final VeldKnop buttonBox : SpelbordView.buttonArray){
            buttonBox.setOnAction(e -> {
                try {
                  MolModel mol = molController.bepaalOfMolAanwezig(bs_interface, buttonBox);
                  if (mol == null){
                      System.out.println("Geen mol aanwezig");
                  }
                  else {
                      //Naar eindpunt bepalen
                  }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }

            });
        }
    }

    public void setEindpuntKnoppen (Bordspel_Interface bs_interface,MolModel mol){
        for (final VeldKnop buttonBox : SpelbordView.buttonArray){

        }
    }

}