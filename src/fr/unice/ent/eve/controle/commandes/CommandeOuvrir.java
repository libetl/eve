package fr.unice.ent.eve.controle.commandes;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
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
 * Commande pour ouvrir ou fermer un sondage aux votants.
 * 
 * <br/>
 * Change l'etat du sondage specifie
 * 
 * @author benychou
 * 
 */
public class CommandeOuvrir extends AbstractCommande {

  public CommandeOuvrir () {
    super ();
  }

  public CommandeOuvrir (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    final Resolution r = abt.traiter (gt);

    if (r != null) { return r; }

    if (abt.getContext ().getRequest ().getSession ().getAttribute ("g") == null)//$NON-NLS-1$
    { return new EveErreur (abt.getContext ())
        .lancerErreur (new ParametreErreur ("g")); //$NON-NLS-1$
    }

    if (abt.getContext ().getRequest ().getSession ().getAttribute ("s") == null)//$NON-NLS-1$
    { return new EveErreur (abt.getContext ())
        .lancerErreur (new ParametreErreur ("s")); //$NON-NLS-1$
    }

    gt = m.getGT ();

    final Sondage s = (Sondage) gt.getSession ().load (
        Sondage.class,
        new Long ((String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("s"))); //$NON-NLS-1$

    s.setGT (gt);

    try {
      s.ouvrir ();
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("Ouvrir")); //$NON-NLS-1$
    }

    m.mettreAJour (s);
    m.rafraichir (s);

    return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
        Commandes.staticInstance (m.getGT ())
            .getParNom ("VoirGroupeAdmin").getPartie ().getPartie () + //$NON-NLS-1$
        "/VoirGroupeAdmin", true); //$NON-NLS-1$
  }

}
