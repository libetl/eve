package fr.unice.ent.eve.controle.commandes;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.CommandeErreur;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.controle.questions.QuestionFactory;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.questions.QuestionType;
import fr.unice.ent.eve.modele.sondages.QuestionSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.vue.VueAjouterQuestion;
import fr.unice.ent.eve.vue.VueQuestions;

/**
 * Commande d'affichage, d'ajout, de modification, de remontee, de descente et
 * de suppression des questions (utilise la technologie Ajax)
 * 
 * @author benychou
 * 
 */
public class CommandeModifierQuestions extends AbstractCommande {

  public CommandeModifierQuestions () {
    super ();
  }

  public CommandeModifierQuestions (final Boolean b) {
    super (b);
  }

  private Resolution ajoutChoix (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Sondage s) {
    List<Choix> c;
    if (abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionQuestion_ajoutChoix") != null) //$NON-NLS-1$
    {
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionQuestion_ajoutChoix"); //$NON-NLS-1$
      s.setGT (gt);
      final Question q = (Question) s.getSession ().load (
          Question.class,
          (Long) abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionQuestion_idQuestion")); //$NON-NLS-1$
      q.setGT (gt);
      try {
        c = q.ajoutChoix ("?"); //$NON-NLS-1$
      } catch (final InstantiationException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      } catch (final IllegalAccessException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      } catch (final ClassNotFoundException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      } catch (final InvocationTargetException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      } catch (final NoSuchMethodException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      }

      final String res = VueQuestions.vueUnChoix (q, c, abt.getContext ()
          .getLocale ());

      return new StreamingResolution ("text/html", res); //$NON-NLS-1$
    }
    return null;
  }

  private Resolution ajouterQuestion (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Sondage s, final Modele<?> m,
      final List<Commandes> lC) {
    if (abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionQuestion_type") != null) //$NON-NLS-1$
    {
      final String type = (String) abt.getContext ().getRequest ()
          .getSession ().getAttribute ("GestionQuestion_type"); //$NON-NLS-1$
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionQuestion_type"); //$NON-NLS-1$
      try {
        s.setGT (gt);
        s.ajoutQuestion ();
        final QuestionFactory qf = QuestionFactory.obtenirFabrique (type);
        qf.setGt (gt);
        qf.setS (s);
        qf.construire ();
        final Question q = qf.obtenirResultat ();
        final QuestionSondage qs;
        qs = new QuestionSondage (gt, q.getId (), s.getId ());
        qs.setQuestion (q);
        qs.setSondage (s);
        qs.ajout ();
        final Question q2 = q.toQuestion ();
        m.rafraichir (q2);
        final String vue = VueQuestions.vueUn2 (q2, lC);
        m.fin ();
        return new StreamingResolution ("text/html", vue); //$NON-NLS-1$
      } catch (final InstantiationException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("")); //$NON-NLS-1$
      } catch (final IllegalAccessException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("")); //$NON-NLS-1$
      } catch (final ClassNotFoundException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("")); //$NON-NLS-1$
      } catch (final ActionNonPermiseException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new ActionNonPermiseErreur ("AjouterQuestion")); //$NON-NLS-1$
      }

    }
    return null;
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

    try {
      s.setGT (gt);
      s.modifierQuestions ();
    } catch (final ActionNonPermiseException e1) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("ModifierQuestions")); //$NON-NLS-1$
    }

    Resolution r = abt.traiter (gt);
    if (r != null) { return r; }

    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("niveau", "4"); //$NON-NLS-1$ //$NON-NLS-2$
    final List<Commandes> lC = Commandes.staticInstance (gt).requete (h);

    r = this.validerFormulaire (abt, gt, s, m);
    if (r != null) { return r; }

    r = this.ajoutChoix (abt, gt, s);
    if (r != null) { return r; }

    r = this.voirFormulaire (abt, s, m);
    if (r != null) { return r; }

    r = this.remonterDescendreQuestion (abt, gt, m, s);
    if (r != null) { return r; }

    r = this.ajouterQuestion (abt, gt, s, m, lC);
    if (r != null) { return r; }

    r = this.supprimerQuestion (abt, gt, s, m);
    if (r != null) { return r; }

    final List<QuestionType> lST = QuestionType.staticInstance (gt).requete ();
    Collections.sort (lST);

    final Object[] resultat = new Object[5];

    resultat[0] = g.getNom ();

    resultat[1] = s.getTitre ();

    resultat[2] = VueAjouterQuestion.vue (lST);

    resultat[3] = VueQuestions.vue (s, lC);

    resultat[4] = VueQuestions.vueOrdreQuestion (s);

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return null;

  }

  private Resolution remonterDescendreQuestion (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Modele<?> m, final Sondage s) {
    if (abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionQuestion_direction") != null) //$NON-NLS-1$
    {
      final Long id = (Long) abt.getContext ().getRequest ().getSession ()
          .getAttribute ("GestionQuestion_idQuestion"); //$NON-NLS-1$
      final String direction = (String) abt.getContext ().getRequest ()
          .getSession ().getAttribute ("GestionQuestion_direction"); //$NON-NLS-1$
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionQuestion_direction"); //$NON-NLS-1$
      Question q = null;

      Iterator<QuestionSondage> itQs = s.getQuestionSondage ().iterator ();

      while (itQs.hasNext () && (q == null)) {
        final QuestionSondage qstmp = itQs.next ();
        if (qstmp.getIdQuestion () == id.longValue ()) {
          q = qstmp.getQuestion ();
        }
      }

      if (q == null) {
        m.fin ();

        return new StreamingResolution ("text/html", new String ()); //$NON-NLS-1$
      }

      itQs = s.getQuestionSondage ().iterator ();

      Question q2 = new Question (gt);
      if (direction.equals ("+")) //$NON-NLS-1$
      {
        QuestionSondage qs = itQs.next ();
        while ((qs.getQuestion ().getOrdre () != q.getOrdre () + 1)
            && itQs.hasNext ()) {
          qs = itQs.next ();
        }
        q2 = qs.getQuestion ();
        final int tmp = q.getOrdre ();
        q2.setOrdre (tmp);
        q.setOrdre (tmp + 1);
      } else if (direction.equals ("-")) //$NON-NLS-1$
      {
        QuestionSondage qs = itQs.next ();
        while ((qs.getQuestion ().getOrdre () != q.getOrdre () - 1)
            && itQs.hasNext ()) {
          qs = itQs.next ();
        }
        q2 = qs.getQuestion ();
        final int tmp = q.getOrdre ();
        q2.setOrdre (tmp);
        q.setOrdre (tmp - 1);
      }
      q.setGT (gt);
      q2.setGT (gt);
      m.mettreAJour (q);
      q2.mettreAJour (q2);

      m.fin ();

      return new StreamingResolution ("text/html", new String ()); //$NON-NLS-1$
    }
    return null;
  }

  private Resolution supprimerQuestion (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Sondage s, final Modele<?> m) {
    if (abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionQuestion_supprimer") != null) //$NON-NLS-1$
    {
      final Long id = (Long) abt.getContext ().getRequest ().getSession ()
          .getAttribute ("GestionQuestion_idQuestion"); //$NON-NLS-1$X
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionQuestion_supprimer"); //$NON-NLS-1$

      QuestionSondage qs1 = null;

      final Iterator<QuestionSondage> it = s.getQuestionSondage ().iterator ();
      while ((qs1 == null) && it.hasNext ()) {
        final QuestionSondage qs = it.next ();
        if (qs.getIdQuestion () == id.longValue ()) {
          qs1 = qs;
        }
      }

      s.getQuestionSondage ().remove (qs1);
      if (qs1 != null) {
        qs1.setGT (gt);
        qs1.suppression ();
        m.mettreAJour ();
      }

      for (final QuestionSondage qs : s.getQuestionSondage ()) {
        if ((qs1 != null)
            && (qs.getQuestion ().getOrdre () > qs1.getQuestion ().getOrdre ())) {
          final Question q2 = qs.getQuestion ();
          q2.setOrdre (q2.getOrdre () - 1);
          m.mettreAJour (q2);
        }
      }

      return new StreamingResolution ("text/html", new String ()); //$NON-NLS-1$
    }
    return null;
  }

  @SuppressWarnings ("unchecked")
  private Resolution validerFormulaire (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Sondage s, final Modele<?> m) {
    if (abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionQuestion_valider") != null) //$NON-NLS-1$
    {
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionQuestion_valider"); //$NON-NLS-1$
      s.setGT (m.getGT ());
      final Question q = (Question) s.getSession ().load (
          Question.class,
          (Long) abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionQuestion_idQuestion")); //$NON-NLS-1$
      q.setGT (gt);
      if (abt.getContext ().getRequest ().getParameter ("titre") != null) //$NON-NLS-1$
      {
        q.setTitre (abt.getContext ().getRequest ().getParameter ("titre")); //$NON-NLS-1$
      }

      q.setObligatoire (abt.getContext ().getRequest ()
          .getParameter ("obligatoire") != null); //$NON-NLS-1$

      try {
        q.instanceTypeQuestion ().modifierChoix (
            abt.getContext ().getRequest ().getParameterMap ());
      } catch (final InstantiationException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      } catch (final IllegalAccessException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      } catch (final ClassNotFoundException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      } catch (final InvocationTargetException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      } catch (final NoSuchMethodException e) {
        return new EveErreur (abt.getContext ())
            .lancerErreur (new CommandeErreur ("Choix")); //$NON-NLS-1$
      }

      q.mettreAJour (q);
      final String res = q.getTitre ();

      return new StreamingResolution ("text/html", res); //$NON-NLS-1$
    }
    return null;
  }

  private Resolution voirFormulaire (final ActionBeanTraitant abt,
      final Sondage s, final Modele<?> m) {
    if (abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionQuestion_voir") != null) //$NON-NLS-1$
    {
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionQuestion_voir"); //$NON-NLS-1$
      s.setGT (m.getGT ());
      final Question q = (Question) s.getSession ().load (
          Question.class,
          (Long) abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionQuestion_idQuestion")); //$NON-NLS-1$
      final String res = VueQuestions.vueFormulaire (q, abt.getContext ()
          .getLocale ());

      return new StreamingResolution ("text/html", res); //$NON-NLS-1$
    }
    return null;
  }
}
