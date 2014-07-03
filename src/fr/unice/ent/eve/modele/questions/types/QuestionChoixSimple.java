package fr.unice.ent.eve.modele.questions.types;

import java.util.Iterator;
import java.util.Map;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.reponses.Reponse;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class QuestionChoixSimple extends Question {

  /**
   * 
   */
  private static final long serialVersionUID = 3129386862776225159L;

  public QuestionChoixSimple () {
    super (true);
  }

  public QuestionChoixSimple (final Boolean b) {
    super (b.booleanValue ());
  }

  public QuestionChoixSimple (final GerantTransactions gt) {
    super (gt);
  }

  public QuestionChoixSimple (final String t) {
    super (t);
  }

  @Override
  public void ajout () {
    this.toQuestion ().ajout ();
  }

  @Override
  public Reponse[] creerReponse (final Sondage s,
      final Map<String, Object> object) {
    if (object.get ("numChoix") == null) //$NON-NLS-1$
    { return new Reponse[0]; }

    final int choixId = Integer
        .parseInt (((String[]) object.get ("numChoix"))[0]); //$NON-NLS-1$
    Choix choix = null;

    final Iterator<Choix> it = this.getChoix ().iterator ();
    boolean trouve = false;
    final Reponse[] res = new Reponse[1];
    final Reponse r;

    while (!trouve && it.hasNext ()) {
      final Choix tmp = it.next ();
      if (tmp.getNumChoix () == choixId) {
        trouve = true;
        choix = tmp;
      }
    }

    if (choix != null) {
      r = new Reponse (choix.getId (), s.getId (), this.getId (), 0);
      res[0] = r;
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
