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
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.sondages.GroupeSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;

/**
 * A la difference de la commande dupliquer, cette commande cree seulement un
 * lien entre le groupe dans lequel on se trouve et le sondage en banque. Les
 * resultats sont partages entre les groupes.
 * 
 * @author benychou
 * @see CommandeDupliquer
 */
public class CommandeLier extends AbstractCommande {

  public CommandeLier () {
    super ();
  }

  public CommandeLier (final Boolean b) {
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
      s.lier ();
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("Lier")); //$NON-NLS-1$
    }

    if (!GroupeSondage.staticInstance (gt).existe (g.getId (), s.getId ())) {
      new GroupeSondage (gt, g.getId (), s.getId ()).ajout ();
      m.mettreAJour ();
    }

    return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
        Commandes.staticInstance (m.getGT ())
            .getParNom ("VoirGroupeAdmin").getPartie ().getPartie () + //$NON-NLS-1$
        "/VoirGroupeAdmin", true); //$NON-NLS-1$
  }

}
