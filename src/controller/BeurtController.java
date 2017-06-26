package controller;

import java.rmi.RemoteException;

/**
 * Created by Wessel on 26-6-2017.
 */
public class BeurtController {



    public void SpelStart (Bordspel_Interface bs) throws RemoteException{
        // controllers laden en variabelen maken
        MolController molController = new MolController();
        Bordspel_Controller bsController = new Bordspel_Controller();
        int playerIndex = 0;
        //bepaal Welke index de grootste hand heeft.
        //bs.

        //Mollen worden neergezet

        while (bs.playerList().get(0).getMol_list().size() == molController.aantalMollen(bs)){


        }


    }

}
