package fr.unice.ent.eve.controle.commandes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.CommandeErreur;
import fr.unice.ent.eve.controle.erreurs.EtatInexistantErreur;
import fr.unice.ent.eve.controle.erreurs.EtatInexistantException;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.groupes.PermissionType;
import fr.unice.ent.eve.modele.sondages.GroupeSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.modele.sondages.SondageEtat;
import fr.unice.ent.eve.modele.sondages.SondageType;

/**
 * Commande d'affichage de formulaire d'ajout, et d'ajout dans la base de
 * donnees
 */
public class CommandeAjouterSondage extends AbstractCommande {

  public CommandeAjouterSondage () {
    super ();
  }

  public CommandeAjouterSondage (final Boolean b) {
    super (b);
  }

  private Resolution ajouterSondage (final ActionBeanTraitant abt,
      final Groupe g, final GerantTransactions gt, final Modele<?> m) {
    final boolean aleatoire = ((Boolean) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionSondage_aleatoire")).booleanValue (); //$NON-NLS-1$
    final boolean dupliquable = ((Boolean) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionSondage_dupliquable")).booleanValue (); //$NON-NLS-1$
    final boolean voteUnique = ((Boolean) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionSondage_voteUnique")).booleanValue (); //$NON-NLS-1$
    final boolean banque = ((Boolean) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionSondage_banque")).booleanValue (); //$NON-NLS-1$
    final boolean publique = ((Boolean) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionSondage_publique")).booleanValue (); //$NON-NLS-1$
    final int type = ((Integer) abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionSondage_type")).intValue (); //$NON-NLS-1$
    final String dateOuverture = (String) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionSondage_dateOuverture"); //$NON-NLS-1$
    final String dateFermeture = (String) abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionSondage_dateFermeture"); //$NON-NLS-1$
    final String titre = (String) abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionSondage_titre"); //$NON-NLS-1$

    try {
      final Sondage s;

      if (abt.getContext ().getRequest ().getSession ()
          .getAttribute ("GestionSondage_editer") != null) //$NON-NLS-1$
      {
        s = (Sondage) gt.getSession ().load (
            Sondage.class,
            new Long ((String) abt.getContext ().getRequest ().getSession ()
                .getAttribute ("s"))); //$NON-NLS-1$
      } else {
        s = new Sondage (gt);
      }

      s.setAffichageAleatoire (aleatoire);
      s.setDateOuverture (new SimpleDateFormat ("dd/MM/yyyy hh:mm").parse (dateOuverture)); //$NON-NLS-1$
      s.setDateFermeture (new SimpleDateFormat ("dd/MM/yyyy hh:mm").parse (dateFermeture)); //$NON-NLS-1$
      s.setDupliquable (dupliquable);
      s.setVoteUnique (voteUnique);
      s.setEnBanque (banque);
      s.setGT (gt);
      s.setIdSondageType (type);
      s.setPublique (publique);
      s.setTitre (titre);

      if (abt.getContext ().getRequest ().getSession ()
          .getAttribute ("GestionSondage_editer") != null) //$NON-NLS-1$
      {
        try {
          s.editer ();
        } catch (final ActionNonPermiseException e) {
          return new EveErreur (abt.getContext ())
              .lancerErreur (new ActionNonPermiseErreur ("Editer")); //$NON-NLS-1$
        }
        m.mettreAJour (s);
        s.mettreAJour (s);
      } else {
        s.setIdEtat (SondageEtat.staticInstance (gt)
            .getParNom ("Vide").getId ()); //$NON-NLS-1$

        s.ajout ();
        m.mettreAJour ();
        m.rafraichir (s);
        final GroupeSondage gs = new GroupeSondage (gt, g.getId (), s.getId ());
        gs.ajout ();
        final Set<PermissionType> cope = new HashSet<PermissionType> ();
        final List<Commandes> comms3 = Commandes.staticInstance (gt)
            .getParNiveau (3);
        for (final Commandes comm : comms3) {
          cope.addAll (comm.getPermissionTypeSet (comm.getCommande ()));
        }

        final List<Commandes> comms4 = Commandes.staticInstance (gt)
            .getParNiveau (4);
        for (final Commandes comm : comms4) {
          cope.addAll (comm.getPermissionTypeSet (comm.getCommande ()));
        }

        final List<Commandes> comms5 = Commandes.staticInstance (gt)
            .getParNiveau (5);
        for (final Commandes comm : comms5) {
          cope.addAll (comm.getPermissionTypeSet (comm.getCommande ()));
        }

        for (final PermissionType pt : cope) {
          new Permission (gt, abt.getP ().getId (), g.getId (), s.getId (),
              pt.getNomPermission ()).ajout ();
        }
      }

    } catch (final EtatInexistantException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new EtatInexistantErreur (e.getMessage ()));
    } catch (final ParseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new CommandeErreur ("Ajouter/Editer : " //$NON-NLS-1$
              + e.getMessage ()));
    }

    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionSondage_aleatoire"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionSondage_dateOuverture"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionSondage_dateFermeture"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionSondage_dupliquable"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionSondage_banque"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionSondage_type"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionSondage_publique"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionSondage_titre"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionSondage_editer"); //$NON-NLS-1$

    return new ForwardResolution (
        "/controleurs/action/Redirection?comm=VoirGroupeAdmin"); //$NON-NLS-1$
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    final Resolution r = abt.traiter (gt);

    if (r != null) { return r; }

    if (abt.getContext ().getRequest ().getSession ().getAttribute ("g") == null)//$NON-NLS-1$
    { return new EveErreur (abt.getContext ())
        .lancerErreur (new ParametreErreur ("g")); //$NON-NLS-1$
    }

    final Object[] resultat = new Object[2];

    final Groupe g = (Groupe) gt.getSession ().load (
        Groupe.class,
        new Long ((String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("g"))); //$NON-NLS-1$

    if ((abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionSondage_aleatoire") == null //$NON-NLS-1$
    )
        || (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionSondage_dateOuverture") == null //$NON-NLS-1$
        )
        || (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionSondage_dateFermeture") == null //$NON-NLS-1$
        )
        || (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionSondage_dupliquable") == null //$NON-NLS-1$
        )
        || (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionSondage_voteUnique") == null //$NON-NLS-1$
        )
        || (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionSondage_banque") == null //$NON-NLS-1$
        )
        || (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionSondage_type") == null //$NON-NLS-1$
        )
        || (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionSondage_publique") == null //$NON-NLS-1$
        )
        || (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionSondage_titre") == null)) //$NON-NLS-1$
    {
      resultat[0] = g.getNom ();
      final List<SondageType> lST = SondageType.staticInstance (gt).requete ();

      Collections.sort (lST);

      resultat[1] = lST;

      abt.getContext ().getRequest ().getSession ()
          .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

      return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
          Commandes.staticInstance (m.getGT ())
              .getParNom ("AjouterSondage").getPartie ().getPartie () + //$NON-NLS-1$
          "/AjouterSondage", true); //$NON-NLS-1$
    }

    return this.ajouterSondage (abt, g, gt, m);
  }
}
