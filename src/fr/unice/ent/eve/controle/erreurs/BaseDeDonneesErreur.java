package fr.unice.ent.eve.controle.erreurs;

import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Erreur renvoyee lors de la reception d'une exception de type
 * BaseDeDonneesException
 * 
 * @author benychou
 * @see BaseDeDonneesException
 */
public class BaseDeDonneesErreur extends LocalizableError {

  private static final long serialVersionUID = 2274714627411751135L;

  /**
   * Constructeur
   * 
   * @param parameter
   *          objets utiles pour la construction du message d'erreur.
   */
  public BaseDeDonneesErreur (final Object... parameter) {
    super (
        "fr.unice.ent.eve.controle.erreurs.BaseDeDonneesErreur.message", parameter); //$NON-NLS-1$
  }

}
