package fr.unice.ent.eve.modele.reponses;

import java.io.Serializable;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class Reponse extends Modele<Reponse> implements Serializable {
  private static Reponse    instance;
  /**
   * 
   */
  private static final long serialVersionUID = 9094071815758721469L;

  public static Reponse staticInstance (final GerantTransactions s) {
    if (Reponse.instance == null) {
      Reponse.instance = new Reponse (s);
    }
    return Reponse.instance;
  }

  private Choix        choix;
  private long         id;
  private long         idChoix;
  private long         idQuestion;

  private long         idSondage;
  private int          reponse;
  private Sondage      sondage;

  private TexteReponse texte;

  public Reponse () {
  }

  public Reponse (final GerantTransactions gt) {
    super (gt);
  }

  public Reponse (final GerantTransactions gt, final long idC, final long idS,
      final long idQ, final int rep) {
    super (gt);
    this.idChoix = idC;
    this.idSondage = idS;
    this.idQuestion = idQ;
    this.reponse = rep;
  }

  public Reponse (final long idC, final long idS, final long idQ, final int rep) {
    super ();
    this.idChoix = idC;
    this.idSondage = idS;
    this.idQuestion = idQ;
    this.reponse = rep;
  }

  public Choix getChoix () {
    return this.choix;
  }

  public long getId () {
    return this.id;
  }

  public long getIdChoix () {
    return this.idChoix;
  }

  public long getIdQuestion () {
    return this.idQuestion;
  }

  public long getIdSondage () {
    return this.idSondage;
  }

  public int getReponse () {
    return this.reponse;
  }

  public Sondage getSondage () {
    return this.sondage;
  }

  public TexteReponse getTexte () {
    return this.texte;
  }

  public void setChoix (final Choix c) {
    this.choix = c;
  }

  public void setId (final long id0) {
    this.id = id0;
  }

  public void setIdChoix (final long idC) {
    this.idChoix = idC;
  }

  public void setIdQuestion (final long idQ) {
    this.idQuestion = idQ;
  }

  public void setIdSondage (final long idSo) {
    this.idSondage = idSo;
  }

  public void setReponse (final int rep) {
    this.reponse = rep;
  }

  public void setSondage (final Sondage so) {
    this.sondage = so;
  }

  public void setTexte (final TexteReponse t) {
    this.texte = t;
  }

  protected Reponse toReponse () {
    final Reponse r = new Reponse (this.getGT (), this.getIdChoix (),
        this.getIdSondage (), this.getIdQuestion (), 0);
    return r;
  }

}
