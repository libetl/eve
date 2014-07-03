package fr.unice.ent.eve.controle.commandes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.CommandeErreur;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.groupes.PermissionType;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.reponses.Reponse;
import fr.unice.ent.eve.modele.sondages.QuestionSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.modele.votes.Vote;
import fr.unice.ent.eve.vue.VueQuestion;
import fr.unice.ent.eve.vue.VueQuestions;

/**
 * Commande de vote.<br/>
 * Le vote distingue trois cas :
 * <ul>
 * <li>L'utilisateur arrive sur la premiere question. La liste des questions non
 * repondues est initialisee</li>
 * <li>L'utilisateur a repondu a une question, et il reste au moins une. La
 * reponse est creee, et l'utilisateur repond a la question d'apres</li>
 * <li>L'utilisateur a repondu a toutes les question. La derniere reponse est
 * creee, et elles sont enregistrees dans la base de donnees avec le vote.</li>
 * </ul>
 * 
 * @see fr.unice.ent.eve.modele.votes.Vote
 * @see fr.unice.ent.eve.modele.reponses.Reponse
 * @author benychou
 * 
 */
public class CommandeVoter extends AbstractCommande {

  public CommandeVoter () {
    super ();
  }

  public CommandeVoter (final Boolean b) {
    super (b);
  }

  @SuppressWarnings ("unchecked")
  private boolean autreQuestion (final ActionBeanTraitant abt) {
    return ((Iterator<QuestionSondage>) (abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionVote_DernierVote"))).hasNext (); //$NON-NLS-1$
  }

  @SuppressWarnings ("unchecked")
  private Resolution creerReponse (final ActionBeanTraitant abt,
      final Sondage s, final Question q) {
    try {

      if (this.isReponsesNull (abt)) {
        this.initReponses (abt);
      }

      Question qtmp;

      qtmp = q.instanceTypeQuestion ();
      final Reponse[] r = qtmp.creerReponse (s, abt.getContext ().getRequest ()
          .getParameterMap ());

      ((HashSet<Reponse[]>) abt.getContext ().getRequest ().getSession ()
          .getAttribute ("GestionVote_reponses")).add (r); //$NON-NLS-1$

      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionVote_valider"); //$NON-NLS-1$

      return null;

    } catch (final InstantiationException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new CommandeErreur ("Voter")); //$NON-NLS-1$
    } catch (final IllegalAccessException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new CommandeErreur ("Voter")); //$NON-NLS-1$
    } catch (final ClassNotFoundException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new CommandeErreur ("Voter")); //$NON-NLS-1$
    } catch (final InvocationTargetException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new CommandeErreur ("Voter")); //$NON-NLS-1$
    } catch (final NoSuchMethodException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new CommandeErreur ("Voter")); //$NON-NLS-1$
    }
  }

  @SuppressWarnings ("unchecked")
  private Resolution enregistrerVote (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Modele<?> m, final Groupe g,
      final Sondage s) {
    final Iterator<Reponse[]> itRep = ((HashSet<Reponse[]>) abt.getContext ()
        .getRequest ().getSession ().getAttribute ("GestionVote_reponses")).iterator (); //$NON-NLS-1$
    while (itRep.hasNext ()) {
      final Reponse[] tabReponse = itRep.next ();
      for (final Reponse element : tabReponse) {
        element.setGT (gt);
        element.ajout ();
      }
    }
    new Vote (gt, abt.getP ().getId (), g.getId (), s.getId (), new Date ())
        .ajout ();

    if (s.isVoteUnique ()) {
      final PermissionType pt = PermissionType.staticInstance (gt).getParNom (
          "Voter"); //$NON-NLS-1$
      Permission perm = Permission.staticInstance (gt).get (
          abt.getP ().getId (), g.getId (), s.getId (), pt.getIdPermission ());

      if (perm == null) {
        perm = Permission.staticInstance (gt).get (abt.getP ().getId (),
            g.getId (), -1, pt.getIdPermission ());
      }

      if (perm != null) {
        abt.getP ().getPermissions ().remove (perm);
        perm.setGT (gt);
        perm.suppression ();
        m.mettreAJour ();
      }
    }

    m.mettreAJour ();
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionVote_QuestionCourante"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionVote_reponses"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionVote_DernierVote"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionVote_DernierVoteS"); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .removeAttribute ("GestionVote_valider"); //$NON-NLS-1$

    final String partie = Commandes.staticInstance (m.getGT ())
        .getParNom ("Merci") //$NON-NLS-1$
        .getPartie ().getPartie ();

    return new RedirectResolution ("/controleurs/" //$NON-NLS-1$
        + partie + "/Merci", true); //$NON-NLS-1$
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];
    Question q;

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

    try {
      s.voter ();
    } catch (final ActionNonPermiseException e1) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("Voter")); //$NON-NLS-1$
    }

    if (this.isNouveauVote (abt, s)) {
      this.nouveauVote (abt, s);
    }

    if (this.isQuestionNulle (abt)) {
      q = this.setQuestion (abt, gt);
    } else {
      q = this.loadQuestion (abt, gt);
    }

    if (this.validerQuestion (abt)) {
      final Resolution res = this.creerReponse (abt, s, q);
      if (res != null) { return res; }

      if (this.autreQuestion (abt)) {
        q = this.questionSuivante (abt, gt);
      } else {
        return this.enregistrerVote (abt, gt, m, g, s);
      }
    } else if (this.passerQuestion (abt) && !q.isObligatoire ()) {
      if (this.autreQuestion (abt)) {
        q = this.questionSuivante (abt, gt);
      } else {
        return this.enregistrerVote (abt, gt, m, g, s);
      }
    }

    final Messages mess = new Messages (VueQuestions.class.getName (), abt
        .getContext ().getLocale ());

    String choix = new String ();

    try {
      final VueQuestion vq = (VueQuestion) Class
          .forName (
              "fr.unice.ent.eve.vue.VueQuestion" + q.getType ().getType ()).newInstance (); //$NON-NLS-1$
      choix += vq.vue (q);
    } catch (final ClassNotFoundException e) {
      choix += mess.getString ("erreur"); //$NON-NLS-1$
    } catch (final InstantiationException e) {
      choix += mess.getString ("erreur"); //$NON-NLS-1$
    } catch (final IllegalAccessException e) {
      choix += mess.getString ("erreur"); //$NON-NLS-1$
    }

    final Object[] resultat = new Object[5];

    resultat[0] = g.getNom ();

    resultat[1] = s.getTitre ();

    resultat[2] = "" + q.getOrdre () + "°) " + q.getTitre (); //$NON-NLS-1$ //$NON-NLS-2$

    resultat[3] = choix;

    resultat[4] = VueQuestions.vueNonObligatoire (q);

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return null;
  }

  private void initReponses (final ActionBeanTraitant abt) {
    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionVote_reponses", new HashSet<Reponse[]> ()); //$NON-NLS-1$
  }

  private boolean isNouveauVote (final ActionBeanTraitant abt, final Sondage s) {
    return (abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionVote_DernierVote") == null //$NON-NLS-1$
    )
        || !abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionVote_DernierVoteS").equals ( //$NON-NLS-1$
                new Long (s.getId ()));
  }

  private boolean isQuestionNulle (final ActionBeanTraitant abt) {
    return abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionVote_QuestionCourante") == null; //$NON-NLS-1$
  }

  private boolean isReponsesNull (final ActionBeanTraitant abt) {
    return abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionVote_reponses") == null; //$NON-NLS-1$
  }

  private Question loadQuestion (final ActionBeanTraitant abt,
      final GerantTransactions gt) {
    Question q;
    q = (Question) Question
        .staticInstance (gt)
        .getSession ()
        .load (
            Question.class,
            new Long (((Question) abt.getContext ().getRequest ().getSession ()
                .getAttribute ("GestionVote_QuestionCourante")).getId ())); //$NON-NLS-1$
    q.setGT (gt);
    return q;
  }

  private void nouveauVote (final ActionBeanTraitant abt, final Sondage s) {
    final ArrayList<QuestionSondage> al = new ArrayList<QuestionSondage> ();
    al.addAll (s.getQuestionSondage ());

    if (!s.isAffichageAleatoire ()) {
      Collections.sort (al);
    }
    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionVote_DernierVote", al.iterator ()); //$NON-NLS-1$
    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionVote_DernierVoteS", new Long (s.getId ())); //$NON-NLS-1$
    this.initReponses (abt);
  }

  private boolean passerQuestion (final ActionBeanTraitant abt) {
    return abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionVote_passer") != null; //$NON-NLS-1$
  }

  @SuppressWarnings ("unchecked")
  private Question questionSuivante (final ActionBeanTraitant abt,
      final GerantTransactions gt) {
    QuestionSondage qs;
    Question q;
    qs = ((Iterator<QuestionSondage>) (abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionVote_DernierVote"))).next (); //$NON-NLS-1$
    q = (Question) Question.staticInstance (gt).getSession ()
        .load (Question.class, new Long (qs.getQuestion ().getId ()));
    q.setGT (gt);
    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionVote_QuestionCourante", q); //$NON-NLS-1$
    return q;
  }

  @SuppressWarnings ("unchecked")
  private Question setQuestion (final ActionBeanTraitant abt,
      final GerantTransactions gt) {
    Question q;
    QuestionSondage qs;
    qs = ((Iterator<QuestionSondage>) (abt.getContext ().getRequest ()
        .getSession ().getAttribute ("GestionVote_DernierVote"))).next (); //$NON-NLS-1$
    q = qs.getQuestion ();
    q.setGT (gt);
    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionVote_QuestionCourante", q); //$NON-NLS-1$
    return q;
  }

  private boolean validerQuestion (final ActionBeanTraitant abt) {
    return abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionVote_valider") != null; //$NON-NLS-1$
  }
}
