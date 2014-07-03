package fr.unice.ent.eve.controle.commandes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ServiceWebErreur;
import fr.unice.ent.eve.controle.erreurs.ServiceWebException;
import fr.unice.ent.eve.controle.ws.AppelWS;
import fr.unice.ent.eve.controle.ws.DonneesWS;
import fr.unice.ent.eve.controle.ws.EntreeWS;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.CommandePermission;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.groupes.PermissionType;
import fr.unice.ent.eve.modele.groupes.Personne;
import fr.unice.ent.eve.modele.services.ServiceAuth;
import fr.unice.ent.eve.vue.VueChangerPermissions;

/**
 * Commande pour afficher le formulaire, ajouter des personnes dans un groupe,
 * changer leurs permissions, supprimer des personnes
 * 
 * Pour ajouter des personnes, il suffit de leur ajouter la permission
 * 'Appartenir'
 * 
 * @author benychou
 * 
 */
public class CommandeOperationsPersonnes extends AbstractCommande {

  public CommandeOperationsPersonnes () {
    super ();
  }

  public CommandeOperationsPersonnes (final Boolean b) {
    super (b);
  }

  @SuppressWarnings ("unchecked")
  private Resolution ajout (final ActionBeanTraitant abt, final Modele<?> m,
      final Groupe g, final List<PermissionType> lPT) {
    final String lnCmd = (String) abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionPersonnes_lnCmd"); //$NON-NLS-1$
    final String adresse = (String) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionPersonnes_adresse"); //$NON-NLS-1$

    if ((lnCmd != null) && (adresse != null)) {
      DonneesWS dws = null;
      try {
        dws = AppelWS.appel (abt.getContext ().getLocale (), ServiceAuth
            .staticInstance (m.getGT ()).get (adresse), lnCmd);
      } catch (final ServiceWebException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new ServiceWebErreur (e.getMessage ()));
      }

      for (final EntreeWS e : dws) {
        for (final String o : e.keySet ()) {
          Resolution r;
          abt.getContext ()
              .getRequest ()
              .getSession ()
              .setAttribute (
                  "GestionPersonnes_uid", ((ArrayList<String>) e.get (o)).get (0)); //$NON-NLS-1$

          r = this.ajoutUn (abt, m, g, lPT);

          if (r != null) { return r; }
        }
      }

      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionPersonnes_adresse"); //$NON-NLS-1$
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionPersonnes_lnCmd"); //$NON-NLS-1$
    }
    return null;
  }

  private Resolution ajoutPermission (final ActionBeanTraitant abt,
      final PermissionType pt, final Modele<?> m, final Groupe g, final Personne p) {

    if (!abt.getP ().isSuperAdmin ()
        && !(g.getContrainte ().contientCommande ("AjouterPersonnes"))) //$NON-NLS-1$
    { return null; }

    long idG = 0;

    if (!pt.getNomPermission ().equals ("EntrerPanneauAdminGroupes")) //$NON-NLS-1$
    {
      idG = g.getId ();
    }

    final Permission perm = new Permission (m.getGT (), p.getId (), idG, 0,
        pt.getNomPermission ());
    p.getPermissions ().add (perm);

    if (p.getId () == abt.getP ().getId ()) {
      abt.getP ().getPermissions ().add (perm);
    }

    perm.ajout ();
    m.mettreAJour ();
    return null;
  }

  private Resolution ajoutUn (final ActionBeanTraitant abt, final Modele<?> m,
      final Groupe g, final List<PermissionType> lPT) {
    final String nom = (String) abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionPersonnes_uid"); //$NON-NLS-1$

    if (nom != null) {
      Personne p;
      p = Personne.staticInstance (m.getGT ()).getParNom (nom);

      if (p == null) {
        p = new Personne (m.getGT (), nom);
        p.ajout ();
        m.mettreAJour ();
        m.rafraichir (p);
      }
      p.setGT (m.getGT ());

      for (final PermissionType pt : lPT) {
        Resolution r = null;
        final Permission perm = Permission.staticInstance (m.getGT ()).get (
            p.getId (), g.getId (), 0, pt.getIdPermission ());

        if ((abt.getContext ().getRequest ()
            .getParameter ("permission" + pt.getNomPermission ()) == null) && (perm != null)) //$NON-NLS-1$
        {
          r = this.suppressionPermission (abt, perm, m, g, p);
        } else if ((abt.getContext ().getRequest ()
            .getParameter ("permission" + pt.getNomPermission ()) != null) && (perm == null)) //$NON-NLS-1$
        {
          r = this.ajoutPermission (abt, pt, m, g, p);
        }
        if (r != null) { return null; }

      }

      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionPersonnes_uid"); //$NON-NLS-1$
      abt.getContext ().getRequest ().getSession ()
          .setAttribute ("GestionPersonnes_fait", new Boolean (true)); //$NON-NLS-1$
    }
    return null;
  }

  @SuppressWarnings ("unchecked")
  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    Resolution r = abt.traiter (gt);

    if (r != null) { return r; }

    final Groupe g = (Groupe) gt.getSession ().load (
        Groupe.class,
        new Long ((String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("g"))); //$NON-NLS-1$

    final List<Commandes> lC = Commandes.staticInstance (gt).getParNiveau (1);
    lC.addAll (Commandes.staticInstance (gt).getParNiveau (2));

    final List<CommandePermission> lcC = new ArrayList<CommandePermission> ();

    for (final Commandes c : lC) {
      lcC.addAll (c.getCope ());
    }

    final List<PermissionType> lPT = abt.getP ().getPermissions (lcC, g);
    Collections.sort (lPT);

    r = this.ajout (abt, m, g, lPT);

    if (r != null) { return r; }

    r = this.ajoutUn (abt, m, g, lPT);

    if (r != null) { return r; }

    final String vlPT = VueChangerPermissions.vue (lPT);

    final Object[] resultat = new Object[3];

    resultat[0] = g.getNom ();

    resultat[1] = ServiceAuth.staticInstance (gt).requete ();
    Collections.sort ((List<ServiceAuth>) resultat[1]);

    resultat[2] = vlPT;

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return null;

  }

  private Resolution suppressionPermission (final ActionBeanTraitant abt,
      final Permission perm, final Modele<?> m, final Groupe g, final Personne p) {

    if (!abt.getP ().isSuperAdmin ()
        && !(g.getContrainte ().contientCommande ("SupprimerPersonnes"))) //$NON-NLS-1$
    { return null; }

    if (p.getId () == abt.getP ().getId ()) {
      abt.getP ().enlever ();
      abt.setP (p);
    }

    perm.setGT (m.getGT ());
    perm.suppression ();

    return null;
  }
}
