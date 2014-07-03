package fr.unice.ent.eve.controle.commandes;

import java.util.ArrayList;
import java.util.Collections;

import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.sondages.QuestionSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.vue.VueQuestion;
import fr.unice.ent.eve.vue.VueQuestions;

/**
 * Commande de recuperation des votes au sondage et d'affichage textuel
 * 
 * @author benychou
 * 
 */
public class CommandeConsulterResultats extends AbstractCommande {

  public CommandeConsulterResultats () {
    super ();
  }

  public CommandeConsulterResultats (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];
    final ArrayList<QuestionSondage> lQS = new ArrayList<QuestionSondage> ();
    String res = new String ();

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
      s.consulterResultats ();
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("ConsulterResultats")); //$NON-NLS-1$
    }

    lQS.addAll (s.getQuestionSondage ());
    Collections.sort (lQS);

    for (final QuestionSondage qs : lQS) {
      final Messages mess = new Messages (VueQuestions.class.getName (), abt
          .getContext ().getLocale ());

      try {
        final VueQuestion vq = (VueQuestion) Class.forName (
            "fr.unice.ent.eve.vue.VueQuestion" //$NON-NLS-1$
                + qs.getQuestion ().getType ().getType ()).newInstance ();
        res += vq.vueResultats (qs.getQuestion ());
      } catch (final ClassNotFoundException e) {
        res += "<p><br/>" + mess.getString ("erreur") //$NON-NLS-1$ //$NON-NLS-2$
            + "<br/></p>"; //$NON-NLS-1$
      } catch (final InstantiationException e) {
        res += "<p><br/>" + mess.getString ("erreur") //$NON-NLS-1$ //$NON-NLS-2$
            + "<br/></p>"; //$NON-NLS-1$
      } catch (final IllegalAccessException e) {
        res += "<p><br/>" + mess.getString ("erreur") //$NON-NLS-1$ //$NON-NLS-2$
            + "<br/></p>"; //$NON-NLS-1$
      }
    }

    final Object[] resultat = new Object[5];

    resultat[0] = g.getNom ();

    resultat[1] = s.getTitre ();

    resultat[2] = res;

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return null;
  }
}
