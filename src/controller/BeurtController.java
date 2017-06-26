package controller;

import model.BeurtStatus;
import model.Velden.VeldKnop;
import view.SpelbordView;

import java.rmi.RemoteException;

/**
 * Created by Wessel on 26-6-2017.
 */
public class BeurtController {
   private MolController molController = new MolController();
   private Bordspel_Controller bsController = new Bordspel_Controller();

    public void SpelStart(Bordspel_Interface bs) throws RemoteException {
        // controllers laden en variabelen maken

        int playerIndex = 0;
        //bepaal Welke index de grootste hand heeft.
        //bs.

        //Mollen worden neergezet
        bs.setBeurtStatus(BeurtStatus.NEERZETTEN);

        while (bs.playerList().get(0).getMol_list().size() == molController.aantalMollen(bs)) {


        }


    }


//    public void setKnoppenNeerzetten(SpelbordView sbv, Bordspel_Controller bsController, Bordspel_Interface bsInterface) throws RemoteException {
//        for (int i = 0; i < sbv.getButtonArray().length; i++) {
//            final VeldKnop buttonBox = sbv.getButtonArray()[i];
//            buttonBox.setOnAction(e -> {
//                try {
//                    bsController.setMolCoords(bsInterface.playerList().get(bsInterface.beurtIndex()),buttonBox,molController.aantalMollen(bsInterface));
//                } catch (RemoteException e1) {
//                    e1.printStackTrace();
//                }
//            });
//        }
//    }

}