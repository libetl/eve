package fr.unice.ent.eve.modele.sondages;

import java.io.Serializable;
import java.util.HashMap;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.questions.Question;

public class QuestionSondage extends Modele<QuestionSondage> implements
    Serializable {
  private static QuestionSondage instance;
  /**
   * 
   */
  private static final long      serialVersionUID = 6446483854565294206L;

  public static QuestionSondage staticInstance (final GerantTransactions s) {
    if (QuestionSondage.instance == null) {
      QuestionSondage.instance = new QuestionSondage (s);
    }
    return QuestionSondage.instance;
  }

  private long     idQuestion;

  private long     idSondage;
  private Question question;

  private Sondage  sondage;

  public QuestionSondage () {
  }

  public QuestionSondage (final GerantTransactions gt) {
    super (gt);
  }

  public QuestionSondage (final GerantTransactions gt, final long idQ,
      final long idS) {
    super (gt);
    this.idQuestion = idQ;
    this.idSondage = idS;
  }

  public QuestionSondage (final GerantTransactions gt, final Question q,
      final Sondage s) {
    super (gt);
    this.question = q;
    this.sondage = s;
  }

  public QuestionSondage get (final long idQ, final long idS) {
    final HashMap<String, String> contraintes = new HashMap<String, String> ();
    contraintes.put ("idQuestion", "" + idQ); //$NON-NLS-1$ //$NON-NLS-2$
    contraintes.put ("idSondage", "" + idS); //$NON-NLS-1$ //$NON-NLS-2$
    return this.requete (contraintes).get (0);
  }

  @Override
  public Object getComparableId () {
    return this.question.getComparableId ();
  }

  public long getIdQuestion () {
    return this.idQuestion;
  }

  public long getIdSondage () {
    return this.idSondage;
  }

  public Question getQuestion () {
    return this.question;
  }

  public Sondage getSondage () {
    return this.sondage;
  }

  public void setIdQuestion (final long idQ) {
    this.idQuestion = idQ;
  }

  public void setIdSondage (final long idS) {
    this.idSondage = idS;
  }

  public void setQuestion (final Question qu) {
    this.question = qu;
  }

  public void setSondage (final Sondage so) {
    this.sondage = so;
  }
}
