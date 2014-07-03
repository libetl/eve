package fr.unice.ent.eve.controle.erreurs;

import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Erreur renvoyee si un argument manque pour executer une requete.
 */
public class ParametreErreur extends LocalizableError {

  private static final long serialVersionUID = 5233083987377085054L;

  /**
   * Constructeur
   * 
   * @param parameter
   *          objets utiles pour la construction du message d'erreur.
   */
  public ParametreErreur (final Object... parameter) {
    super (
        "fr.unice.ent.eve.controle.erreurs.ParametreErreur.message", parameter); //$NON-NLS-1$
  }

}
