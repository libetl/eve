package fr.unice.ent.eve.controle.commandes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
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
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.vue.VueChangerPermissions;

/**
 * Commande pour afficher le formulaire, modifier les permissions sur un sondage
 * specifique.
 * 
 * Cette commande ne donne pas le droit d'ajouter ou de supprimer une personne
 * dans le groupe.
 * 
 * @author benychou
 * 
 */
public class CommandeOperationsPersonnesSondage extends AbstractCommande {

  public CommandeOperationsPersonnesSondage () {
    super ();
  }

  public CommandeOperationsPersonnesSondage (final Boolean b) {
    super (b);
  }

  @SuppressWarnings ("unchecked")
  private Resolution ajout (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Modele<?> m, final Groupe g,
      final Sondage s, final List<PermissionType> lPT) {
    final String lnCmd = (String) abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionPersonnes_lnCmd"); //$NON-NLS-1$
    final String adresse = (String) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionPersonnes_adresse"); //$NON-NLS-1$

    if ((lnCmd != null) && (adresse != null)) {
      DonneesWS dws = null;
      try {
        dws = AppelWS.appel (abt.getContext ().getLocale (), ServiceAuth
            .staticInstance (gt).get (adresse), lnCmd);
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

          r = this.ajoutUn (abt, gt, m, g, s, lPT);

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

  private Resolution ajoutPermission (final PermissionType pt, final Modele<?> m,
      final GerantTransactions gt, final Groupe g, final Sondage s,
      final Personne p) {
    final Permission perm = new Permission (gt, p.getId (), g.getId (),
        s.getId (), pt.getNomPermission ());
    perm.setGT (gt);
    p.getPermissions ().add (perm);
    perm.ajout ();
    m.mettreAJour ();
    return null;
  }

  private Resolution ajoutUn (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Modele<?> m, final Groupe g,
      final Sondage s, final List<PermissionType> lPT) {
    final String nom = (String) abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionPersonnes_uid"); //$NON-NLS-1$

    if (nom != null) {
      Personne p;
      p = Personne.staticInstance (gt).getParNom (nom);

      if (p == null) {
        p = new Personne (gt, nom);
        p.ajout ();
        m.mettreAJour ();
        m.rafraichir (p);
      }

      for (final PermissionType pt : lPT) {
        Resolution r = null;
        final Permission perm = Permission.staticInstance (gt).get (p.getId (),
            g.getId (), s.getId (), pt.getIdPermission ());

        p.setGT (gt);

        if ((abt.getContext ().getRequest ()
            .getParameter ("permission" + pt.getNomPermission ()) == null) && (perm != null)) //$NON-NLS-1$
        {
          r = this.suppressionPermission (perm, p, gt);
        } else if ((abt.getContext ().getRequest ()
            .getParameter ("permission" + pt.getNomPermission ()) != null) && (perm == null)) //$NON-NLS-1$
        {
          r = this.ajoutPermission (pt, m, gt, g, s, p);
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

    final Sondage s = (Sondage) gt.getSession ().load (
        Sondage.class,
        new Long ((String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("s"))); //$NON-NLS-1$

    s.setGT (gt);

    try {
      s.changerPermissions ();
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("ChangerPermissions")); //$NON-NLS-1$
    }

    final List<Commandes> lC = Commandes.staticInstance (gt).getParNiveau (3);
    lC.addAll (Commandes.staticInstance (gt).getParNiveau (4));

    final List<CommandePermission> lcC = new ArrayList<CommandePermission> ();

    for (final Commandes c : lC) {
      lcC.addAll (c.getCope ());
    }

    final List<PermissionType> lPT = abt.getP ().getPermissions (lcC, g);

    lC.clear ();
    lC.addAll (Commandes.staticInstance (gt).getParNiveau (1));
    lC.addAll (Commandes.staticInstance (gt).getParNiveau (2));

    lcC.clear ();

    for (final Commandes c : lC) {
      lcC.addAll (c.getCope ());
    }

    lPT.removeAll (abt.getP ().getPermissions (lcC, g));

    Collections.sort (lPT);

    r = this.ajout (abt, gt, m, g, s, lPT);

    if (r != null) { return r; }

    r = this.ajoutUn (abt, gt, m, g, s, lPT);

    if (r != null) { return r; }

    final String vlPT = VueChangerPermissions.vue (lPT);

    final Object[] resultat = new Object[4];

    resultat[0] = g.getNom ();

    resultat[1] = s.getTitre ();

    resultat[2] = ServiceAuth.staticInstance (gt).requete ();
    Collections.sort ((List<ServiceAuth>) resultat[2]);

    resultat[3] = vlPT;

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return null;

  }

  private Resolution suppressionPermission (final Permission perm,
      final Personne p, final GerantTransactions gt) {
    p.getPermissions ().remove (perm);
    perm.setGT (gt);
    perm.suppression ();
    return null;
  }
}
