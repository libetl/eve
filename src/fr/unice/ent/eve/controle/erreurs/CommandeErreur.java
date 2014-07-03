package fr.unice.ent.eve.controle.erreurs;

import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Erreur renvoyee si le lancement d'une commande a echouee
 */
public class CommandeErreur extends LocalizableError {

  private static final long serialVersionUID = -6915030040958541707L;

  /**
   * Constructeur
   * 
   * @param parameter
   *          objets utiles pour la construction du message d'erreur.
   */
  public CommandeErreur (final Object... parameter) {
    super (
        "fr.unice.ent.eve.controle.erreurs.CommandeErreur.message", parameter); //$NON-NLS-1$
  }

}
