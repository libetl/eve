package fr.unice.ent.eve.controle.erreurs;

/**
 * Exception renvoyee si l'etat dans lequel on souhaite faire passer un sondage
 * n'existe pas
 */
public class EtatInexistantException extends Exception {

  private static final long serialVersionUID = -8735156237539265796L;

  public EtatInexistantException (final String message) {
    super (message);
  }

}
