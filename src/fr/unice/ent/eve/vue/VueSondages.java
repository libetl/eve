package fr.unice.ent.eve.vue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Personne;
import fr.unice.ent.eve.modele.sondages.GroupeSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class VueSondages {

  public static Object vue (final Groupe g, final Iterator<Sondage> itS,
      final ArrayList<Commandes> lcp, final Personne p,
      final String doitContenir) {
    return VueSondages.vue (g, itS, lcp, p, doitContenir, null);
  }

  public static String vue (final Groupe g, final Iterator<Sondage> itS,
      final List<Commandes> lcp, final Personne p) {
    return VueSondages.vue (g, itS, lcp, p, new String (), null);
  }

  public static Object vue (final Groupe g, final Iterator<Sondage> itS,
      final List<Commandes> lcp, final Personne p, final List<Integer> niveau) {
    return VueSondages.vue (g, itS, lcp, p, null, niveau);
  }

  public static String vue (final Groupe g, final Iterator<Sondage> itS,
      final List<Commandes> lcp, final Personne p, final String doitContenir,
      final List<Integer> niveau) {
    String res = ""; //$NON-NLS-1$
    List<Integer> niveau2;
    if (niveau == null) {
      niveau2 = new ArrayList<Integer> ();
      niveau2.add (new Integer (3));
    } else {
      niveau2 = niveau;
    }

    for ( ; itS.hasNext () ;) {
      res += VueSondages.vueUn (g, itS.next (), lcp, p, doitContenir, niveau2);
    }
    return res;
  }

  public static String vueListe (final List<GroupeSondage> lS) {
    String res = new String ();
    for (final GroupeSondage s : lS) {
      res += "<li>" + s.getS ().getTitre ()//$NON-NLS-1$
          + " - <i>" + s.getG ().getNom () //$NON-NLS-1$
          + "</i>" //$NON-NLS-1$
          + "&nbsp;&nbsp;<a href=\"../action/Redirection?comm=Voter&g=" //$NON-NLS-1$
          + s.getG ().getId ()
          + "&s=" //$NON-NLS-1$
          + s.getS ().getId ()
          + "\" title=\"Voter\">Voter</a>&nbsp;&nbsp;" //$NON-NLS-1$
          + "<a href=\"../action/Redirection?comm=ConsulterResultats&g=" //$NON-NLS-1$
          + s.getG ().getId ()
          + "&s=" //$NON-NLS-1$
          + s.getS ().getId ()
          + "\" title=\"Consulter les resultats\">Consulter les resultats" //$NON-NLS-1$
          + "</a>&nbsp;&nbsp;"; //$NON-NLS-1$
    }

    if (res.equals (new String ())) {
      res += "<li>Aucun sondage actuellement</li>"; //$NON-NLS-1$
    }
    return res;
  }

  public static String vueUn (final Groupe g, final Sondage s,
      final List<Commandes> lC, final Personne p, final String doitContenir,
      final List<Integer> niveau) {
    String comm = ""; //$NON-NLS-1$
    for (final Commandes c : lC) {
      if ("".equals (p.aPermissions (c.getCope (), g.getId (), s.getId ())) //$NON-NLS-1$
          && (niveau.contains (new Integer (c.getNiveau ())))
          && c.getPartie ().getPartie ().contains (doitContenir)) {
        comm += "<a href=\"../../action/Redirection?comm=" + c.getCommande () + "&s=" + s.getId () + "\"" //$NON-NLS-1$, //$NON-NLS-2$, //$NON-NLS-3$
            + " title=\"" + c.getDescription ()//$NON-NLS-1$
            + "\"><img src=\"../../../vue/_layout/imgs/Commande"//$NON-NLS-1$
            + c.getCommande () + ".png\" alt=\"" + c.getDescription () + //$NON-NLS-1$
            "\" /></a>"; //$NON-NLS-1$
      }
    }

    return "<span class=\"colonneGauche\">" //$NON-NLS-1$
        + "<img src=\"../../../vue/_layout/imgs/Sondage" //$NON-NLS-1$ 
        + s.getEtatN ().getNomEtat ()
        + ".png\" alt=\"" + s.getEtatN ().getNomEtat () //$NON-NLS-1$ 
        + "\" class=\"imageEtat\"/>" + s.getTitre () //$NON-NLS-1$ 
        + "</span><span class=\"colonne\">" + //$NON-NLS-1$ 
        s.getType ().getNomSondageType ()
        + "</span><span class=\"colonneDroite\">" + comm + //$NON-NLS-1$
        "</span><div class=\"finLigne\"></div>"; //$NON-NLS-1$
  }

  public VueSondages () {
    super ();
  }

}
