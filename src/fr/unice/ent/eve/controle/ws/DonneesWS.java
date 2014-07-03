package fr.unice.ent.eve.controle.ws;

/**
 * Classe de representation du resultat renvoye par un service web. <br/>
 * Il s'agit simplement d'un objet de type ArrayList&lt;EntreeWS&gt;
 */
public class DonneesWS extends java.util.ArrayList<EntreeWS> {
  private static final long serialVersionUID = 2360673091900793541L;

  /**
   * Constructeur appellant la classe ArrayList
   */
  public DonneesWS () {
    super ();
  }

  /**
   * Constructeur importe de la classe ArrayList
   */
  public DonneesWS (final int initialCapacity) {
    super (initialCapacity);
  }

  /**
   * Constructeur importe de la classe ArrayList
   */
  public DonneesWS (final java.util.Collection<? extends EntreeWS> c) {
    super (c);
  }

}
