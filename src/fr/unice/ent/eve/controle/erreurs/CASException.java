package fr.unice.ent.eve.controle.erreurs;

/**
 * Exception renvoyee si cas a mal repondu.
 */
public class CASException extends Exception {

  private static final long serialVersionUID = 1068836078457513187L;

  public CASException (final String message) {
    super (message);
  }
}
