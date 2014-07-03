package fr.unice.ent.eve.modele.reponses;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;

public class TexteReponse extends Modele<TexteReponse> {
  private static TexteReponse instance;

  public static TexteReponse staticInstance (final GerantTransactions s) {
    if (TexteReponse.instance == null) {
      TexteReponse.instance = new TexteReponse (s);
    }
    return TexteReponse.instance;
  }

  private int     id;
  private Reponse reponse;

  private String  texte;

  public TexteReponse () {
  }

  public TexteReponse (final GerantTransactions gt) {
    super (gt);
  }

  public TexteReponse (final String t) {
    super ();
    this.texte = t;
  }

  public int getId () {
    return this.id;
  }

  public Reponse getReponse () {
    return this.reponse;
  }

  public String getTexte () {
    return this.texte;
  }

  public void setId (final int idT) {
    this.id = idT;
  }

  public void setReponse (final Reponse rep) {
    this.reponse = rep;
  }

  public void setTexte (final String t) {
    this.texte = t;
  }

}
