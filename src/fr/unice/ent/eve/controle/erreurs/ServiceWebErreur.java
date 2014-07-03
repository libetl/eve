package fr.unice.ent.eve.controle.erreurs;

import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Erreur renvoyee lors de la reception d'une exception de type
 * ServiceWebException
 * 
 * @author benychou
 * 
 */
public class ServiceWebErreur extends LocalizableError {

  private static final long serialVersionUID = 1665857785973683927L;

  /**
   * Constructeur
   * 
   * @param parameter
   *          objets utiles pour la construction du message d'erreur.
   */
  public ServiceWebErreur (final Object... parameter) {
    super (
        "fr.unice.ent.eve.controle.erreurs.ServiceWebErreur.message", parameter); //$NON-NLS-1$
  }

}
