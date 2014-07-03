package fr.unice.ent.eve.controle.erreurs;

import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Erreur renvoyee lors de la reception de l'exception EtatInexistantException
 * 
 * @author benychou
 * @see EtatInexistantException
 */
public class EtatInexistantErreur extends LocalizableError {

  private static final long serialVersionUID = 713111367231832253L;

  /**
   * Constructeur
   * 
   * @param parameter
   *          objets utiles pour la construction du message d'erreur.
   */
  public EtatInexistantErreur (final Object... parameter) {
    super (
        "fr.unice.ent.eve.controle.erreurs.EtatInexistantErreur.message", parameter); //$NON-NLS-1$
  }

}
