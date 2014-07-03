package fr.unice.ent.eve.modele.questions;

import java.io.Serializable;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.reponses.Reponse;

public class Choix extends Modele<Choix> implements Serializable {
  private static Choix      instance;
  /**
   * 
   */
  private static final long serialVersionUID = -5875780702809196531L;

  public static Choix staticInstance (final GerantTransactions s) {
    if (Choix.instance == null) {
      Choix.instance = new Choix (s);
    }
    return Choix.instance;
  }

  private long         id;
  private long         idQuestion;
  private String       nomChoix;
  private int          numChoix;
  private Question     question;

  private Set<Reponse> reponses;

  private int          valeur;

  public Choix () {
  }

  public Choix (final GerantTransactions gt) {
    super (gt);
  }

  public Choix (final GerantTransactions gt, final long idQ, final int idC,
      final String nomC, final int val) {
    super (gt);
    this.idQuestion = idQ;
    this.numChoix = idC;
    this.nomChoix = nomC;
    this.valeur = val;
  }

  @Override
  public Object getComparableId () {
    return new Integer (this.numChoix * 1000 + this.valeur);
  }

  public long getId () {
    return this.id;
  }

  public long getIdQuestion () {
    return this.idQuestion;
  }

  public String getNomChoix () {
    return this.nomChoix;
  }

  public int getNumChoix () {
    return this.numChoix;
  }

  public Question getQuestion () {
    return this.question;
  }

  public Set<Reponse> getReponses () {
    return this.reponses;
  }

  public int getValeur () {
    return this.valeur;
  }

  public void setId (final long idC) {
    this.id = idC;
  }

  public void setIdQuestion (final long idQ) {
    this.idQuestion = idQ;
  }

  public void setNomChoix (final String nomC) {
    this.nomChoix = nomC;
  }

  public void setNumChoix (final int numC) {
    this.numChoix = numC;
  }

  public void setQuestion (final Question q) {
    this.question = q;
  }

  public void setReponses (final Set<Reponse> r) {
    this.reponses = r;
  }

  public void setValeur (final int val) {
    this.valeur = val;
  }
}
