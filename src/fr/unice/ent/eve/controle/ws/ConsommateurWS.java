package fr.unice.ent.eve.controle.ws;

import java.util.ArrayList;

/**
 * Le consommateur est une classe de conversion des donnees recueillies par le
 * Service Web. Elle permet d'afficher les donnees sous forme de chaine, ou de
 * former un objet structure a partir de donnees non structurees.
 * 
 * @author benychou
 * @see AppelWS
 */
public class ConsommateurWS {

  /**
   * Convertit un resultat d'un service Web sous forme de chaine de caracteres
   * 
   * @param al
   *          les donnees a traiter
   * @return un objet de type String contenant des lignes sous la forme "indice"
   *         => "valeur1" "valeur2"
   */
  public static String listeMapsVersString (final DonneesWS al) {
    String result = ""; //$NON-NLS-1$
    for (int i = 0 ; i < al.size () ; i++) {
      result += ConsommateurWS.mapVersString (al.get (i));
    }
    return result;
  }

  /**
   * Convertit une entree d'un resultat d'un service Web sous forme de chaine de
   * caracteres.
   * 
   * @param objet
   *          l'entree a traiter
   * @return un objet de type String contenant une entree sous la forme "indice"
   *         => "valeur1" "valeur2"
   */
  @SuppressWarnings ("unchecked")
  public static String mapVersString (final EntreeWS objet) {
    String sb = ""; //$NON-NLS-1$

    if (objet == null) { return sb; }

    for (final String suiv : objet.keySet ()) {
      final String dec = "\"" + suiv.toString () + "\" => "; //$NON-NLS-1$ //$NON-NLS-2$

      ArrayList<String> al;

      sb += dec;

      if (!(objet.get (suiv) instanceof ArrayList)) {
        ConsommateurWS.mapVersString ((EntreeWS) objet.get (suiv));
      } else {
        al = (ArrayList<String>) objet.get (suiv);

        for (int i = 0 ; i < al.size () ; i++) {
          sb += "\"" + al.get (i) + "\"<br/>"; //$NON-NLS-1$ //$NON-NLS-2$
          if (i + 1 < al.size ()) {
            for (int j = 0 ; j < dec.length () ; j++) {
              sb += "&nbsp;"; //$NON-NLS-1$
            }
          }
        }

      }

    }
    return sb;
  }

  /**
   * Convertit des donnes brutes recueillies du Service Web en donnees sous
   * forme de Liste
   * 
   * @param objet
   *          les donnees a convertir
   * @return un objet de type ArrayList&lt;String&gt;
   */
  private static ArrayList<String> objetVersListe (final Object objet) {

    if (objet == null) { return null; }

    if (!(objet instanceof Object[])) { return null; }

    final Object[] objets = (Object[]) objet;

    final ArrayList<String> l = new ArrayList<String> ();

    for (final Object element : objets) {
      l.add ((String) element);
    }
    return l;
  }

  /**
   * Convertit des donnes brutes recueillies du Service Web en donnees sous
   * forme d'Entree
   * 
   * @param objet
   *          les donnees a convertir
   * @return un objet de type EntreeWS
   * @see EntreeWS
   */
  public static EntreeWS objetVersMap (final Object objet) {
    if (objet == null) { return null; }

    if (!(objet instanceof Object[])) {
      final ArrayList<String> al = new ArrayList<String> ();
      al.add ((String) objet);
      final EntreeWS m = new EntreeWS ();
      m.put ("0", al); //$NON-NLS-1$
      return m;
    }

    final Object[] objets = (Object[]) objet;

    final EntreeWS m = new EntreeWS ();

    for (int i = 0 ; i < objets.length ; i++) {
      if (!(objets[i] instanceof Object[])) {
        final ArrayList<Object> al = new ArrayList<Object> ();
        al.add (objets[i]);
        m.put ("" + i, al); //$NON-NLS-1$
      } else if (!(((Object[]) objets[i])[0] instanceof String)) {
        m.put ("" + i, ConsommateurWS.objetVersMap (objets[i])); //$NON-NLS-1$
      } else {
        m.put ((String) ((Object[]) objets[i])[0],
            ConsommateurWS.objetVersListe (((Object[]) objets[i])[1]));
      }
    }
    return m;
  }

  private static String objetVersString (final int niveau, final Object objet) {
    if (objet == null) { return null; }
    if (!(objet instanceof Object[])) { return "\"" + objet.toString () + "\""; //$NON-NLS-1$ //$NON-NLS-2$
    }
    final Object[] objets = (Object[]) objet;
    String separateur = "\n"; //$NON-NLS-1$
    for (int i = 0 ; i < niveau ; i++) {
      separateur += " "; //$NON-NLS-1$
    }
    final StringBuffer sb = new StringBuffer ();
    for (int i = 0 ; i < objets.length ; i++) {
      sb.append (separateur).append ("[" + i + "]") //$NON-NLS-1$ //$NON-NLS-2$
          .append (" => ").append ( //$NON-NLS-1$ 
              ConsommateurWS.objetVersString (niveau + 1, objets[i]));
    }
    return sb.toString ();
  }

  /**
   * Convertit un objet non structure en chaine de caracteres
   * 
   * @param objet
   *          les donnees a convertir
   * @return un objet de type String
   */
  public static String objetVersString (final Object objet) {
    return ConsommateurWS.objetVersString (0, objet);
  }

  public ConsommateurWS () {
    super ();
  }
}
