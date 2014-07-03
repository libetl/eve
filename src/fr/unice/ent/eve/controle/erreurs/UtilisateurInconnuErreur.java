package fr.unice.ent.eve.controle.erreurs;

import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Erreur renvoyee lorsqu'un utilisateur de Eve ne figure pas dans le service de
 * groupes.
 * 
 * @author benychou
 * 
 */
public class UtilisateurInconnuErreur extends LocalizableError {

  private static final long serialVersionUID = 2274714627411751135L;

  /**
   * Constructeur
   * 
   * @param parameter
   *          objets utiles pour la construction du message d'erreur.
   */
  public UtilisateurInconnuErreur (final Object... parameter) {
    super (
        "fr.unice.ent.eve.controle.erreurs.UtilisateurInconnuErreur.message", parameter); //$NON-NLS-1$
  }

}
