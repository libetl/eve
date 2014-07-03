package fr.unice.ent.eve.controle.commandes;

import net.sourceforge.stripes.action.RedirectResolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.sondages.Sondage;

/**
 * Commande qui supprime un sondage, les votes correspondants et les questions
 * correspondantes
 * 
 * @author benychou
 * 
 */
public class CommandeSupprimer extends AbstractCommande {

  public CommandeSupprimer () {
    super ();
  }

  public CommandeSupprimer (final Boolean b) {
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

    m.getGT ();

    final Sondage s = (Sondage) gt.getSession ().load (
        Sondage.class,
        new Long ((String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("s"))); //$NON-NLS-1$
    s.setGT (gt);

    try {
      s.supprimer ();
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("Supprimer")); //$NON-NLS-1$
    }

    abt.getP ().getPermissions ().removeAll (s.getPermissions ());

    s.suppression ();

    m.mettreAJour ();

    final String partie = Commandes.staticInstance (m.getGT ())
        .getParNom ("VoirGroupeAdmin").getPartie ().getPartie (); //$NON-NLS-1$

    return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
        partie + "/VoirGroupeAdmin", true); //$NON-NLS-1$
  }
}
