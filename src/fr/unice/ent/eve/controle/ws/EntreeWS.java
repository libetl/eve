package fr.unice.ent.eve.controle.ws;

/**
 * Classe de representation d'une entree d'un service web. <br/>
 * Il s'agit simplement d'un objet de type HashMap<String, Object>
 */
public class EntreeWS extends java.util.HashMap<String, Object> {

  private static final long serialVersionUID = 7489133137632015030L;

  /**
   * Constructeur appellant la classe HashMap
   */
  public EntreeWS () {
    super ();
  }

  /**
   * Constructeur importe de la classe HashMap
   */
  public EntreeWS (final int initialCapacity) {
    super (initialCapacity);
  }

  /**
   * Constructeur importe de la classe HashMap
   */
  public EntreeWS (final int initialCapacity, final float loadFactor) {
    super (initialCapacity, loadFactor);
  }

  /**
   * Constructeur importe de la classe HashMap
   */
  public EntreeWS (final java.util.Map<? extends String, ? extends Object> m) {
    super (m);
  }

}
