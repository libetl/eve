package fr.unice.ent.eve.vue;

import java.util.Iterator;

import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Personne;

/**
 * Renvoit le formattage pour voir la liste des actions possibles pour un groupe
 * 
 * @author benychou
 * 
 */
public class VueActionsGroupe {

  public static String vue (final Groupe g, final Iterator<Commandes> it,
      final Personne p) {
    String res = ""; //$NON-NLS-1$
    while (it.hasNext ()) {
      final Commandes cC = it.next ();
      p.getId ();

      if ("".equals (p.aPermissions (cC.getCope (), g.getId (), 0)) //$NON-NLS-1$
          && (cC.getNiveau () == 2)) {
        res += VueActionsGroupe.vueUn (cC);
      }
    }
    if ("".equals (res)) //$NON-NLS-1$
    { return "<span class=\"actionBouton\">X</span>"; //$NON-NLS-1$
    }
    return res;
  }

  public static String vueUn (final Commandes c) {
    return "<span class=\"actionBouton\" onclick=\"document.location='../action/Redirection?comm=" + c.getCommande () + "'\"" //$NON-NLS-1$ //$NON-NLS-2$
        + " onmouseover=\"this.className='actionBoutonDessus'\" onmouseout=\"this.className='actionBouton'\">" //$NON-NLS-1$
        + "<a href=\"../action/Redirection?comm=" + c.getCommande () + "\">" + c.getDescription () + "</a></span>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  public VueActionsGroupe () {
    super ();
  }
}
