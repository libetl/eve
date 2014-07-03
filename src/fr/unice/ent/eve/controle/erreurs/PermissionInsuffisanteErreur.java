package fr.unice.ent.eve.controle.erreurs;

import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Erreur renvoyee si l'utilisateur n'a pas les permissions suffisante pour
 * executer une commande
 */
public class PermissionInsuffisanteErreur extends LocalizableError {

  private static final long serialVersionUID = 1508016292226327275L;

  public PermissionInsuffisanteErreur (final Object... parameter) {
    super (
        "fr.unice.ent.eve.controle.erreurs.PermissionInsuffisanteErreur.message", parameter); //$NON-NLS-1$
  }

}
