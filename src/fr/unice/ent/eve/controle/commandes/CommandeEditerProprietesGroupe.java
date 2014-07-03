package fr.unice.ent.eve.controle.commandes;

import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.GroupeContrainte;

/**
 * Commande de modification du nom d'un groupe et de son profil
 * 
 * @author benychou
 * @see fr.unice.ent.eve.modele.groupes.Groupe
 */
public class CommandeEditerProprietesGroupe extends AbstractCommande {

  public CommandeEditerProprietesGroupe () {
    super ();
  }

  public CommandeEditerProprietesGroupe (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    final long idGroupe = ((Long) abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionGroupe_idGroupe")).longValue (); //$NON-NLS-1$
    final String nomGroupe = (String) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionGroupe_nomGroupe"); //$NON-NLS-1$
    final int contrainte = ((Integer) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionGroupe_contrainte")).intValue (); //$NON-NLS-1$

    final Groupe g = (Groupe) gt.getSession ().load (Groupe.class,
        new Long (idGroupe));

    g.setGT (gt);
    g.setNom (nomGroupe);

    final GroupeContrainte c = (GroupeContrainte) gt.getSession ().load (
        GroupeContrainte.class, new Integer (contrainte));
    g.setContrainte (c);

    m.mettreAJour (g);
    return g;
  }
}
