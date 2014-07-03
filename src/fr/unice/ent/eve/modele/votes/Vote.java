package fr.unice.ent.eve.modele.votes;

import java.io.Serializable;
import java.util.Date;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class Vote extends Modele<Vote> implements Serializable {
  private static Vote       instance;
  /**
   * 
   */
  private static final long serialVersionUID = -5558064363884747601L;

  public static Vote staticInstance (final GerantTransactions s) {
    if (Vote.instance == null) {
      Vote.instance = new Vote (s);
    }
    return Vote.instance;
  }

  private Date    date;
  private long    id;
  private long    idGroupe;
  private long    idPersonne;
  private long    idSondage;

  private Sondage sondage;

  public Vote () {
  }

  public Vote (final GerantTransactions gt) {
    super (gt);
  }

  public Vote (final GerantTransactions gt, final long idP, final long idG,
      final long idS, final Date d) {
    super (gt);
    this.idPersonne = idP;
    this.idGroupe = idG;
    this.idSondage = idS;
    this.date = d;
  }

  public Vote (final long idP, final long idG, final long idS, final Date d) {
    super ();
    this.idPersonne = idP;
    this.idGroupe = idG;
    this.idSondage = idS;
    this.date = d;
  }

  public Date getDate () {
    return this.date;
  }

  public long getId () {
    return this.id;
  }

  public long getIdGroupe () {
    return this.idGroupe;
  }

  public long getIdPersonne () {
    return this.idPersonne;
  }

  public long getIdSondage () {
    return this.idSondage;
  }

  public Sondage getSondage () {
    return this.sondage;
  }

  public void setDate (final Date d) {
    this.date = d;
  }

  public void setId (final long idV) {
    this.id = idV;
  }

  public void setIdGroupe (final long idG) {
    this.idGroupe = idG;
  }

  public void setIdPersonne (final long idP) {
    this.idPersonne = idP;
  }

  public void setIdSondage (final long idS) {
    this.idSondage = idS;
  }

  public void setSondage (final Sondage s) {
    this.sondage = s;
  }

}
