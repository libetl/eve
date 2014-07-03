package fr.unice.ent.eve.modele.questions.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.reponses.Reponse;
import fr.unice.ent.eve.modele.reponses.ReponseLibre;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class QuestionReponseLibre extends Question {

  /**
   * 
   */
  private static final long serialVersionUID = 5191641052942316173L;

  public QuestionReponseLibre () {
    super (true);
  }

  public QuestionReponseLibre (final Boolean b) {
    super (b.booleanValue ());
  }

  public QuestionReponseLibre (final GerantTransactions gt) {
    super (gt);
  }

  public QuestionReponseLibre (final String t) {
    super (t);
  }

  @Override
  public void ajout () {
    this.toQuestion ().ajout ();
  }

  @Override
  protected List<Choix> ajoutChoix (final String nomChoix, final int taille) {
    nomChoix.hashCode ();
    final Integer taille2 = new Integer (taille);
    taille2.hashCode ();
    return new ArrayList<Choix> ();
  }

  @Override
  public Reponse[] creerReponse (final Sondage s,
      final Map<String, Object> object) {
    final String texte = ((String[]) object.get ("reponseChoix"))[0]; //$NON-NLS-1$
    final Choix choix = this.getChoix ().iterator ().next ();

    final Reponse[] res = new Reponse[1];
    final ReponseLibre r;

    r = new ReponseLibre (choix.getId (), s.getId (), this.getId (), 0);
    r.setTexteChaine (texte);

    res[0] = r;

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
