package fr.unice.ent.eve.modele.config;

import java.util.HashMap;
import java.util.List;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;

public class EveConfig extends Modele<EveConfig> {
  private static EveConfig instance;

  public static EveConfig staticInstance (final GerantTransactions s) {
    if (EveConfig.instance == null) {
      EveConfig.instance = new EveConfig (s);
    }
    return EveConfig.instance;
  }

  private String propriete;

  private String valeur;

  public EveConfig () {
    super ();
  }

  public EveConfig (final boolean construire) {
    super (construire);
  }

  public EveConfig (final GerantTransactions gt) {
    super (gt);
  }

  public EveConfig (final GerantTransactions gt, final String p, final String v) {
    super (gt);
    this.propriete = p;
    this.valeur = v;
  }

  public String get (final String p) {
    final HashMap<String, String> hm = new HashMap<String, String> ();
    hm.put ("propriete", p); //$NON-NLS-1$
    final List<EveConfig> lec = this.requete (hm);
    if (lec.size () == 0) { return new String (); }

    return lec.get (0).getValeur ();
  }

  public String getPropriete () {
    return this.propriete;
  }

  public String getValeur () {
    return this.valeur;
  }

  public void set (final String p, final String v) {
    final HashMap<String, String> hm = new HashMap<String, String> ();
    hm.put ("propriete", p); //$NON-NLS-1$
    final EveConfig ec = this.requete (hm).get (0);
    ec.setValeur (v);
    this.mettreAJour (ec);
  }

  public void setPropriete (final String p) {
    this.propriete = p;
  }

  public void setValeur (final String v) {
    this.valeur = v;
  }

}
