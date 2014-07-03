package fr.unice.ent.eve.controle.erreurs;

import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Erreur renvoyee lors de la reception d'une exception de type
 * ActionNonPermiseException
 * 
 * @author benychou
 * @see ActionNonPermiseException
 */
public class ActionNonPermiseErreur extends LocalizableError {

  private static final long serialVersionUID = 1508016292226327275L;

  /**
   * Constructeur
   * 
   * @param parameter
   *          objets utiles pour la construction du message d'erreur.
   */
  public ActionNonPermiseErreur (final Object... parameter) {
    super (
        "fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur.message", parameter); //$NON-NLS-1$
  }

}
