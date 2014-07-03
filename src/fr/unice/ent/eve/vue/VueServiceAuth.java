package fr.unice.ent.eve.vue;

import java.util.List;

import fr.unice.ent.eve.modele.services.ServiceAuth;

/**
 * Renvoit le formattage pour voir la liste des services d'authentification
 * 
 * @author benychou
 * 
 */
public class VueServiceAuth {

  public static Object vue (final List<ServiceAuth> lsa) {
    String res = new String ();
    for (final ServiceAuth sa : lsa) {
      res += VueServiceAuth.vueUn (sa);
    }
    return res;
  }

  public static String vueUn (final ServiceAuth sa) {
    return "<span class=\"colonneGauche\" onclick=\"javascript:commande ('EditerSupprimer', '" //$NON-NLS-1$
        + sa.getAdresse ()
        + "')\" id=\"" //$NON-NLS-1$
        + sa.getAdresse ()
        + "A\"><p id=\"" //$NON-NLS-1$
        + sa.getAdresse ()
        + "AP\">" //$NON-NLS-1$
        + sa.getAdresse ()
        + "</p></span><span class=\"colonne\" onclick=\"javascript:commande ('EditerSupprimer', '" //$NON-NLS-1$
        + sa.getAdresse () + "')\" id=\"" //$NON-NLS-1$
        + sa.getAdresse () + "C\"><p id=\"" //$NON-NLS-1$
        + sa.getAdresse () + "CP\">" //$NON-NLS-1$
        + sa.getClasse () + "</p></span><span class=\"colonneDroite\" id=\"" //$NON-NLS-1$
        + sa.getAdresse () + "ES\">" //$NON-NLS-1$
        + "<a href=\"javascript:commande ('EditerSupprimer', '" //$NON-NLS-1$
        + sa.getAdresse () + "')\">Editer/Supprimer</a></span>" //$NON-NLS-1$
        + "<div class=\"finLigne\" id=\"" //$NON-NLS-1$
        + sa.getAdresse () + "FL\"></div>"; //$NON-NLS-1$
  }

  public VueServiceAuth () {
    super ();
  }

}
