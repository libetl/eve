package fr.unice.ent.eve.modele.sondages;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;

public class SondageEtat extends Modele<SondageEtat> implements Serializable {
  private static SondageEtat instance;
  /**
   * 
   */
  private static final long  serialVersionUID = -2050851728386453062L;

  public static SondageEtat staticInstance (final GerantTransactions s) {
    if (SondageEtat.instance == null) {
      SondageEtat.instance = new SondageEtat (s);
    }
    return SondageEtat.instance;
  }

  private int          id;
  private String       nomEtat;

  private Set<Sondage> sondages;

  public SondageEtat () {
  }

  public SondageEtat (final GerantTransactions gt) {
    super (gt);
  }

  public SondageEtat (final GerantTransactions gt, final String nomST) {
    super (gt);
    this.nomEtat = nomST;
  }

  public int getId () {
    return this.id;
  }

  public String getNomEtat () {
    return this.nomEtat;
  }

  public SondageEtat getParNom (final String comm) {
    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("nomEtat", comm); //$NON-NLS-1$
    final List<SondageEtat> l = this.requete (h);
    if (l.size () > 0) { return l.get (0); }
    return null;
  }

  public Set<Sondage> getSondages () {
    return this.sondages;
  }

  public void setId (final int idSt) {
    this.id = idSt;
  }

  public void setNomEtat (final String nST) {
    this.nomEtat = nST;
  }

  public void setSondages (final Set<Sondage> s) {
    this.sondages = s;
  }
}
