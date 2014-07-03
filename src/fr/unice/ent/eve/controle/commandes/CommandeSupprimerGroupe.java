package fr.unice.ent.eve.controle.commandes;

import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.Groupe;

/**
 * Commande pour supprimer un groupe. Action irreversible.
 * 
 * @author benychou
 * 
 */
public class CommandeSupprimerGroupe extends AbstractCommande {

  public CommandeSupprimerGroupe () {
    super ();
  }

  public CommandeSupprimerGroupe (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    final Long idGroupe = (Long) abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionGroupe_idGroupe"); //$NON-NLS-1$

    m.fin ();
    m.getGT ();
    final Groupe g = (Groupe) gt.getSession ().load (Groupe.class, idGroupe);
    g.setGT (gt);
    g.suppression ();
    return null;
  }
}
