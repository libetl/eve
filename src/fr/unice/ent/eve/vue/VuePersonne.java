package fr.unice.ent.eve.vue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import fr.unice.ent.eve.controle.ws.EntreeWS;
import fr.unice.ent.eve.modele.groupes.Personne;

/**
 * Renvoit le formattage pour voir la liste des personnes
 * 
 * @author benychou
 * 
 */
public class VuePersonne {
  public static String vue (final Iterator<Personne> it) {
    String res = ""; //$NON-NLS-1$
    for ( ; it.hasNext () ;) {
      res += VuePersonne.vueUn (it.next ());
    }
    return res;
  }

  /**
   * @param iterator
   * @param uidCourriel
   * @return
   */
  public static Object vue (final Iterator<Personne> it,
      final HashMap<String, EntreeWS> uidCourriel) {
    String res = ""; //$NON-NLS-1$
    for ( ; it.hasNext () ;) {
      final Personne p = it.next ();
      res += VuePersonne.vueUn (p, uidCourriel.get (p.getValeurUid ()));
    }
    return res;
  }

  public static String vueUn (final Personne p) {
    return "<span class=\"colonneGauche\">" + p.getId () + "</span><span class=\"colonneDroite\">" + //$NON-NLS-1$ //$NON-NLS-2$
        p.getValeurUid () + "</span><div class=\"finLigne\"></div>"; //$NON-NLS-1$
  }

  /**
   * @param p
   * @param entreeWS
   * @return
   */
  @SuppressWarnings ("unchecked")
  private static String vueUn (final Personne p, final EntreeWS entreeWS) {
    return "<div id=\"" + p.getValeurUid () + "\"><span class=\"colonneGauche\">" //$NON-NLS-1$ //$NON-NLS-2$
        + p.getValeurUid () + "</span><span class=\"colonneDroite\">" + //$NON-NLS-1$ 
        ((ArrayList<String>) entreeWS.get ("displayName")).get (0) //$NON-NLS-1$
        + "</span><div class=\"finLigne\"></div></div>"; //$NON-NLS-1$ 

  }

  public VuePersonne () {
    super ();
  }
}
