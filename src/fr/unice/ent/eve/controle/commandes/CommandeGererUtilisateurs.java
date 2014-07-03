package fr.unice.ent.eve.controle.commandes;

import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.groupes.PermissionType;
import fr.unice.ent.eve.modele.groupes.Personne;

/**
 * Commande de gestion des utilisateurs. Permet d'ajouter ou supprimer des super
 * utilisateurs
 * 
 * @author benychou
 */
public class CommandeGererUtilisateurs extends AbstractCommande {

  public CommandeGererUtilisateurs () {
    super ();
  }

  public CommandeGererUtilisateurs (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];
    final String nom = (String) abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionUtilisateurs_uid"); //$NON-NLS-1$

    if ((nom != null)
        && (abt.getContext ().getRequest ().getParameter ("sur") != null)) //$NON-NLS-1$
    {
      Personne p;
      p = Personne.staticInstance (gt).getParNom (nom);

      if (p == null) {
        p = new Personne (gt, nom);
        p.ajout ();
        m.mettreAJour ();
        m.rafraichir (p);
      }
      p.setGT (gt);

      Permission perm = Permission.staticInstance (gt).get (p.getId (), 0L, 0L,
          PermissionType.staticInstance (gt).getParNom ("SuperUtilisation") //$NON-NLS-1$
              .getIdPermission ());
      if (perm != null) {
        p.getPermissions ().remove (perm);
        perm.setGT (gt);
        perm.suppression ();
      } else {
        perm = new Permission (gt, p.getId (), 0, 0, "SuperUtilisation"); //$NON-NLS-1$
        perm.ajout ();
        p.getPermissions ().add (perm);
      }
      m.mettreAJour ();

      p = (Personne) gt.getSession ().load (Personne.class,
          new Long (p.getId ()));

      abt.getContext ().getRequest ().getSession ()
          .setAttribute ("GestionUtilisateurs_fait", new Boolean (true)); //$NON-NLS-1$

    }

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", new boolean[] { true }); //$NON-NLS-1$

    return null;
  }
}
