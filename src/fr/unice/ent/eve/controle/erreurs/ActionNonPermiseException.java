package fr.unice.ent.eve.controle.erreurs;

/**
 * Exception levee lorsque l'etat d'un sondage ou le profil d'un groupe ne
 * permet pas l'action en cours
 */
public class ActionNonPermiseException extends Exception {

  private static final long serialVersionUID = 4626902666180120416L;

  public ActionNonPermiseException (final String message) {
    super (message);
  }
}
