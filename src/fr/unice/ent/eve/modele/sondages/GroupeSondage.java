package fr.unice.ent.eve.modele.sondages;

import java.io.Serializable;
import java.util.HashMap;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.Groupe;

public class GroupeSondage extends Modele<GroupeSondage> implements
    Serializable {
  private static GroupeSondage instance;
  /**
   * 
   */
  private static final long    serialVersionUID = 2238356562445375698L;

  public static GroupeSondage staticInstance (final GerantTransactions s) {
    if (GroupeSondage.instance == null) {
      GroupeSondage.instance = new GroupeSondage (s);
    }
    return GroupeSondage.instance;
  }

  private Groupe  g;
  private long    idGroupe;
  private long    idSondage;

  private Sondage s;

  public GroupeSondage () {
  }

  public GroupeSondage (final GerantTransactions gt) {
    super (gt);
  }

  public GroupeSondage (final GerantTransactions gt, final Groupe gr,
      final Sondage so) {
    super (gt);
    this.g = gr;
    this.s = so;
  }

  public GroupeSondage (final GerantTransactions gt, final long idG,
      final long idS) {
    super (gt);
    this.idGroupe = idG;
    this.idSondage = idS;
  }

  public boolean existe (final long idG, final long idS) {
    final HashMap<String, String> contraintes = new HashMap<String, String> ();
    contraintes.put ("idGroupe", "" + idG); //$NON-NLS-1$ //$NON-NLS-2$
    contraintes.put ("idSondage", "" + idS); //$NON-NLS-1$ //$NON-NLS-2$
    return this.requete (contraintes).size () > 0;
  }

  public Groupe getG () {
    return this.g;
  }

  public long getIdGroupe () {
    return this.idGroupe;
  }

  public long getIdSondage () {
    return this.idSondage;
  }

  public Sondage getS () {
    return this.s;
  }

  public void setG (final Groupe gr) {
    this.g = gr;
  }

  public void setIdGroupe (final long gr) {
    this.idGroupe = gr;
  }

  public void setIdSondage (final long pe) {
    this.idSondage = pe;
  }

  public void setS (final Sondage so) {
    this.s = so;
  }
}
