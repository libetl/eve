package fr.unice.ent.eve.modele.questions.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.reponses.Reponse;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class QuestionChoixMultiple extends Question {

  /**
   * 
   */
  private static final long serialVersionUID = 3612737112885809540L;

  public QuestionChoixMultiple () {
    super (true);
  }

  public QuestionChoixMultiple (final Boolean b) {
    super (b.booleanValue ());
  }

  public QuestionChoixMultiple (final GerantTransactions gt) {
    super (gt);
  }

  public QuestionChoixMultiple (final String t) {
    super (t);
  }

  @Override
  public void ajout () {
    this.toQuestion ().ajout ();
  }

  @Override
  public Reponse[] creerReponse (final Sondage s,
      final Map<String, Object> object) {
    final ArrayList<Reponse> reponses = new ArrayList<Reponse> ();
    final Iterator<Choix> it = this.getChoix ().iterator ();
    Reponse r;

    while (it.hasNext ()) {
      final Choix tmp = it.next ();
      if (object.get ("numChoix" + tmp.getNumChoix ()) != null) //$NON-NLS-1$
      {
        r = new Reponse (tmp.getId (), s.getId (), this.getId (), 0);
        reponses.add (r);
      }
    }

    final Reponse[] res = new Reponse[reponses.size ()];
    for (int i = 0 ; i < reponses.size () ; i++) {
      res[i] = reponses.get (i);
    }

    return res;
  }

  @Override
  public void mettreAJour (final Object o) {
    super.mettreAJour (((Question) o).toQuestion ());
  }

  @Override
  public void rafraichir (final Object o) {
    super.rafraichir (((Question) o).toQuestion ());
  }
}
