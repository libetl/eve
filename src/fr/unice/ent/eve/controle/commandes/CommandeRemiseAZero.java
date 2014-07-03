package fr.unice.ent.eve.controle.commandes;

import java.util.Set;

import net.sourceforge.stripes.action.RedirectResolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.Transaction;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.reponses.Reponse;
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.modele.votes.Vote;

/**
 * Supprimer les votes et les reponses d'un sondage. Utile pour recommencer un
 * sondage.
 */
public class CommandeRemiseAZero extends AbstractCommande {

  public CommandeRemiseAZero () {
    super ();
  }

  public CommandeRemiseAZero (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    if (abt.getContext ().getRequest ().getSession ().getAttribute ("g") == null)//$NON-NLS-1$
    { return new EveErreur (abt.getContext ())
        .lancerErreur (new ParametreErreur ("g")); //$NON-NLS-1$
    }

    if (abt.getContext ().getRequest ().getSession ().getAttribute ("s") == null)//$NON-NLS-1$
    { return new EveErreur (abt.getContext ())
        .lancerErreur (new ParametreErreur ("s")); //$NON-NLS-1$
    }

    final Sondage s = (Sondage) gt.getSession ().load (
        Sondage.class,
        new Long ((String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("s"))); //$NON-NLS-1$
    s.setGT (gt);

    final Set<Vote> lV = s.getVotes ();

    try {
      s.remiseAZero ();
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("RemiseAZero")); //$NON-NLS-1$
    }

    final Transaction t = gt.debutTransaction ();

    for (final Vote v : lV) {
      v.setGT (gt);
      gt.supprimerT (v);
    }

    final Set<Reponse> lR = s.getReponses ();

    for (final Reponse r : lR) {
      r.setGT (gt);
      gt.supprimerT (r);
    }

    s.getVotes ().clear ();
    s.getReponses ().clear ();

    gt.finTransaction (t);

    m.mettreAJour (s);

    return new RedirectResolution ("/controleurs/" //$NON-NLS-1$
        + Commandes.staticInstance (m.getGT ()).getParNom ("VoirGroupeAdmin") //$NON-NLS-1$
            .getPartie ().getPartie () + "/VoirGroupeAdmin", true); //$NON-NLS-1$
  }
}
