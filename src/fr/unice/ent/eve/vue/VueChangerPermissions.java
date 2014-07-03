package fr.unice.ent.eve.vue;

import java.util.List;

import fr.unice.ent.eve.modele.groupes.PermissionType;

/**
 * Renvoit le formattage pour voir la liste des permissions pouvant etre donnees
 * 
 * @author benychou
 * 
 */
public class VueChangerPermissions {

  public static String vue (final List<PermissionType> lpt) {
    String res = new String ();

    for (final PermissionType pt : lpt) {
      res += "&nbsp;&nbsp;<input type=\"checkbox\" name=\"permission" //$NON-NLS-1$
          + pt.getNomPermission () + "\" />" + pt.getNomPermission (); //$NON-NLS-1$
    }

    return res;
  }

  public VueChangerPermissions () {
    super ();
  }

}
