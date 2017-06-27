package model.Velden;

import java.io.Serializable;

/**
 * Is een normaal veld.
 * Created by Wessel on 6-6-2017.
 */

public class Normaalveld_Veld implements Serializable{
	private static final long serialVersionUID = 1L;

public Normaalveld_Veld () {
    }

    /**
     * Geeft aan of hij bezet is.
     * @return true wanneer bezet.
     */
  public boolean isBezet() {
    return false;
  }
}
