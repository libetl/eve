package fr.unice.ent.eve.controle.commandes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.modele.sondages.SondageType;

/**
 * Commande d'affichage du formulaire d'edition, et de mise a jour dans la base
 * de donnees
 */
public class CommandeEditer extends AbstractCommande {
  public CommandeEditer () {
    super ();
  }

  public CommandeEditer (final Boolean b) {
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

    g.setGT (gt);
    s.setGT (gt);

    try {
      s.editer ();
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("Editer")); //$NON-NLS-1$
    }

    final Object[] resultat = new Object[10];

    final DateFormat df = new SimpleDateFormat ("dd/MM/yyyy hh:mm"); //$NON-NLS-1$

    resultat[0] = new Boolean (s.isAffichageAleatoire ());
    resultat[1] = df.format (s.getDateOuverture ());
    resultat[2] = df.format (s.getDateFermeture ());
    resultat[3] = new Boolean (s.isVoteUnique ());
    resultat[4] = new Boolean (s.isDupliquable ());
    resultat[5] = new Boolean (s.isEnBanque ());
    resultat[6] = new Integer (s.getType ().getId ());
    resultat[7] = new Boolean (s.isPublique ());
    resultat[8] = s.getTitre ();

    final List<SondageType> lST = SondageType.staticInstance (gt).requete ();

    Collections.sort (lST);

    resultat[9] = lST;

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return null;
  }
}
