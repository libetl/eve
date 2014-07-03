package fr.unice.ent.eve.modele.questions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;

public class QuestionType extends Modele<QuestionType> implements Serializable {
  private static QuestionType instance;
  /**
   * 
   */
  private static final long   serialVersionUID = 9043282027385121730L;

  public static QuestionType staticInstance (final GerantTransactions s) {
    if (QuestionType.instance == null) {
      QuestionType.instance = new QuestionType (s);
    }
    return QuestionType.instance;
  }

  private int           id;
  private String        nom;
  private Set<Question> questions;

  private String        type;

  public QuestionType () {
  }

  public QuestionType (final GerantTransactions gt) {
    super (gt);
  }

  public QuestionType (final GerantTransactions gt, final String nomQT) {
    super (gt);
    this.type = nomQT;
  }

  @Override
  public Object getComparableId () {
    return this.type;
  }

  public int getId () {
    return this.id;
  }

  public String getNom () {
    return this.nom;
  }

  public QuestionType getParNom (final String comm) {
    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("type", comm); //$NON-NLS-1$
    final List<QuestionType> l = this.requete (h);
    if (l.size () > 0) { return l.get (0); }
    return null;
  }

  public Set<Question> getQuestions () {
    return this.questions;
  }

  public String getType () {
    return this.type;
  }

  public void setId (final int idSt) {
    this.id = idSt;
  }

  public void setNom (final String n) {
    this.nom = n;
  }

  public void setQuestions (final Set<Question> q) {
    this.questions = q;
  }

  public void setType (final String nQT) {
    this.type = nQT;
  }

}
