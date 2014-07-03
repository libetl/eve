package fr.unice.ent.eve.modele.sondages;

import java.io.Serializable;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;

public class SondageType extends Modele<SondageType> implements Serializable {
  private static SondageType instance;
  /**
   * 
   */
  private static final long  serialVersionUID = 1381216389681781068L;

  public static SondageType staticInstance (final GerantTransactions s) {
    if (SondageType.instance == null) {
      SondageType.instance = new SondageType (s);
    }
    return SondageType.instance;
  }

  private int          id;
  private String       nomSondageType;

  private Set<Sondage> sondages;

  public SondageType () {
  }

  public SondageType (final GerantTransactions gt) {
    super (gt);
  }

  public SondageType (final GerantTransactions gt, final String nomST) {
    super (gt);
    this.nomSondageType = nomST;
  }

  @Override
  public Object getComparableId () {
    return new Integer (this.id);
  }

  public int getId () {
    return this.id;
  }

  public String getNomSondageType () {
    return this.nomSondageType;
  }

  public Set<Sondage> getSondages () {
    return this.sondages;
  }

  public void setId (final int idSt) {
    this.id = idSt;
  }

  public void setNomSondageType (final String nST) {
    this.nomSondageType = nST;
  }

  public void setSondages (final Set<Sondage> s) {
    this.sondages = s;
  }

}
