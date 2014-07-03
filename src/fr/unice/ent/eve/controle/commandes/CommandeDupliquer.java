package fr.unice.ent.eve.controle.commandes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.stripes.action.RedirectResolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.modele.CreateurClone;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.groupes.PermissionType;
import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.sondages.GroupeSondage;
import fr.unice.ent.eve.modele.sondages.QuestionSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;

/**
 * Commande pour cloner un sondage avec ses questions dans le groupe dans lequel
 * on se trouve
 * 
 * @author benychou
 * 
 */
public class CommandeDupliquer extends AbstractCommande {

  public CommandeDupliquer () {
    super ();
  }

  public CommandeDupliquer (final Boolean b) {
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
      s.dupliquer ();
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("Dupliquer")); //$NON-NLS-1$
    }

    Sondage s2;

    s2 = (Sondage) CreateurClone.recopie (s);
    s2.setGT (gt);
    s2.setEnBanque (false);
    s2.ajout ();

    s2.rafraichir (s2);

    new GroupeSondage (gt, g.getId (), s2.getId ()).ajout ();

    final Set<PermissionType> cope = new HashSet<PermissionType> ();
    final List<Commandes> comms3 = Commandes.staticInstance (gt).getParNiveau (
        3);
    for (final Commandes comm : comms3) {
      cope.addAll (comm.getPermissionTypeSet (comm.getCommande ()));
    }

    final List<Commandes> comms4 = Commandes.staticInstance (gt).getParNiveau (
        4);
    for (final Commandes comm : comms4) {
      cope.addAll (comm.getPermissionTypeSet (comm.getCommande ()));
    }

    final List<Commandes> comms5 = Commandes.staticInstance (gt).getParNiveau (
        5);
    for (final Commandes comm : comms5) {
      cope.addAll (comm.getPermissionTypeSet (comm.getCommande ()));
    }

    for (final PermissionType pt : cope) {
      new Permission (gt, abt.getP ().getId (), g.getId (), s.getId (),
          pt.getNomPermission ()).ajout ();
    }

    for (final QuestionSondage qs : s.getQuestionSondage ()) {
      Question q;
      final Set<Choix> sc = new HashSet<Choix> ();
      final Question qorig = qs.getQuestion ();
      sc.addAll (qorig.getChoix ());
      qorig.getQuestionSondage ().clear ();
      qorig.getChoix ().clear ();
      qorig.setGT (gt);
      q = (Question) CreateurClone.recopie (qorig);
      q.setGT (gt);
      q.ajout ();
      q.rafraichir (q);

      for (final Choix ctmp : sc) {
        final Choix c;
        c = (Choix) CreateurClone.recopie (ctmp);
        c.setIdQuestion (q.getId ());
        c.setGT (gt);
        c.ajout ();
      }

      new QuestionSondage (gt, q.getId (), s2.getId ()).ajout ();
    }

    m.mettreAJour ();

    return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
        Commandes.staticInstance (m.getGT ())
            .getParNom ("VoirGroupeAdmin").getPartie ().getPartie () + //$NON-NLS-1$
        "/VoirGroupeAdmin", true); //$NON-NLS-1$
  }

}
