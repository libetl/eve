package fr.unice.ent.eve.controle.erreurs;

import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Erreur renvoyee lors de la reception de l'exception CASErreur
 * 
 * @see CASErreur
 */
public class CASErreur extends LocalizableError {

  private static final long serialVersionUID = 2274714627411751135L;

  /**
   * Constructeur
   * 
   * @param parameter
   *          objets utiles pour la construction du message d'erreur.
   */
  public CASErreur (final Object... parameter) {
    super ("fr.unice.ent.eve.controle.erreurs.CASErreur.message", parameter); //$NON-NLS-1$
  }

}
