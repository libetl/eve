package fr.unice.ent.eve.controle.commandes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.groupes.PermissionType;
import fr.unice.ent.eve.modele.groupes.Personne;

/**
 * Commande pour l'ajout d'un groupe Cette commande peut facultativement
 * utiliser un argument supplementaire dans le tableau de Object de type
 * Set&lt;Permission&gt;
 */
public class CommandeAjouterGroupe extends AbstractCommande {
  public CommandeAjouterGroupe () {
    super ();
  }

  public CommandeAjouterGroupe (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];
    final Personne p = abt.getP ();

    final Set<PermissionType> cope;

    final String nomGroupe = (String) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionGroupe_nomGroupe"); //$NON-NLS-1$
    final int contrainte = ((Integer) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionGroupe_contrainte")).intValue (); //$NON-NLS-1$

    if (this.getParamsObjet ().length > 2) {
      cope = new HashSet<PermissionType> ();
      for (final String s : (String[]) this.getParamsObjet ()[2]) {
        cope.add (PermissionType.staticInstance (gt).getParNom (s));
      }
    } else {
      cope = Commandes.staticInstance (gt).getPermissionTypeSet (
          "VoirGroupeAdmin"); //$NON-NLS-1$
      final List<Commandes> comms1 = Commandes.staticInstance (gt)
          .getParNiveau (1);
      for (final Commandes comm : comms1) {
        cope.addAll (comm.getPermissionTypeSet (comm.getCommande ()));
      }

      final List<Commandes> comms2 = Commandes.staticInstance (gt)
          .getParNiveau (2);
      for (final Commandes comm : comms2) {
        cope.addAll (comm.getPermissionTypeSet (comm.getCommande ()));
      }
    }

    final HashMap<String, String> contraintes = new HashMap<String, String> ();
    contraintes.clear ();
    contraintes.put ("nom", nomGroupe); //$NON-NLS-1$
    Groupe g = Groupe.staticInstance (gt).getParNom (nomGroupe);

    if (g == null) {
      g = new Groupe (gt, nomGroupe, contrainte);

      g.ajout ();
    }

    for (final PermissionType pt : cope) {
      contraintes.clear ();
      contraintes.put ("idPersonne", "" + p.getId ()); //$NON-NLS-1$ //$NON-NLS-2$
      contraintes.put ("idGroupe", "" + g.getId ()); //$NON-NLS-1$ //$NON-NLS-2$
      contraintes.put ("idSondage", "" + 0); //$NON-NLS-1$ //$NON-NLS-2$
      contraintes.put ("naturePermission", "" + pt.getIdPermission ()); //$NON-NLS-1$//$NON-NLS-2$
      final List<Permission> lpe = Permission.staticInstance (gt).requete (
          contraintes);
      if (lpe.size () == 0) {
        new Permission (gt, p.getId (), g.getId (), 0, pt.getNomPermission ())
            .ajout ();
      }

    }

    return g;
  }
}
