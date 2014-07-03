package fr.unice.ent.eve.vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.commandes.ContrainteCommande;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Personne;

/**
 * Renvoit le formattage pour voir la liste des groupes
 * 
 * @author benychou
 * 
 */
public class VueGroupes {

  public static String vue (final Iterator<Groupe> it, final Personne p,
      final List<Commandes> lcSu) {
    String res = ""; //$NON-NLS-1$
    for ( ; it.hasNext () ;) {
      final Groupe g = it.next ();
      ArrayList<Commandes> lC;

      if (p.isSuperAdmin ()) {
        lC = (ArrayList<Commandes>) lcSu;
      } else {
        lC = new ArrayList<Commandes> ();

        final Iterator<ContrainteCommande> iCC = g.getContrainte ()
            .getContrainteCommandes ().iterator ();
        while (iCC.hasNext ()) {
          final Commandes c = iCC.next ().getCommande ();
          if (c.getNiveau () == 1) {
            lC.add (c);
          }
        }
        Collections.sort (lC);
      }

      res += VueGroupes.vueUn (g, lC, p);
    }
    return res;
  }

  public static String vueUn (final Groupe g, final List<Commandes> lC,
      final Personne p) {
    try {
      String comm = ""; //$NON-NLS-1$
      for (final Commandes c : lC) {
        if ("".equals (p.aPermissions (c.getCope (), g.getId (), 0))) //$NON-NLS-1$
        {
          comm += "<a href=\"javascript:commande ('" + c.getCommande () + "', " + g.getId () + ", '" + //$NON-NLS-1$, //$NON-NLS-2$, //$NON-NLS-3$
              g.getNom ()
              + "', " + g.getContrainte ().getId () + ")\" title=\"" + c.getDescription () + //$NON-NLS-1$, //$NON-NLS-2$
              "\"><img src=\"../../vue/_layout/imgs/Commande" //$NON-NLS-1$
              + c.getCommande () + ".png\" alt=\"" + c.getDescription () + //$NON-NLS-1$
              "\" /></a>"; //$NON-NLS-1$
        }
      }

      return "<span class=\"colonneGauche\">" + g.getNom () + "</span><span class=\"colonne\">" + //$NON-NLS-1$ //$NON-NLS-2$
          g.getContrainte ().getNomContrainte ()
          + "</span><span class=\"colonneDroite\">" + comm + //$NON-NLS-1$
          "</span><div class=\"finLigne\"></div>"; //$NON-NLS-1$
    } catch (final NullPointerException e) {
      return ""; //$NON-NLS-1$     
    }
  }

  public static Object vueUtilisateur (final Iterator<Groupe> it,
      final Personne p) {
    String res = ""; //$NON-NLS-1$
    for ( ; it.hasNext () ;) {
      final Groupe g = it.next ();
      final ArrayList<Commandes> lC = new ArrayList<Commandes> ();
      final Iterator<ContrainteCommande> iCC = g.getContrainte ()
          .getContrainteCommandes ().iterator ();
      while (iCC.hasNext ()) {
        final Commandes c = iCC.next ().getCommande ();
        if ((c.getNiveau () == 1)
            && c.getPartie ().getPartie ().contains ("utilisateur")) //$NON-NLS-1$
        {
          lC.add (c);
        }
      }
      Collections.sort (lC);
      res += VueGroupes.vueUn (g, lC, p);
    }
    return res;
  }

  public VueGroupes () {
    super ();
  }

}
