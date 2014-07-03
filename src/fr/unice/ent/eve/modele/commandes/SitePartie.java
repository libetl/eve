package fr.unice.ent.eve.modele.commandes;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;

public class SitePartie extends Modele<SitePartie> {

  private static SitePartie instance;

  public static SitePartie staticInstance (final GerantTransactions s) {
    if (SitePartie.instance == null) {
      SitePartie.instance = new SitePartie (s);
    }
    return SitePartie.instance;
  }

  private Set<Commandes> commandes;
  private int            id;

  private String         partie;

  public SitePartie () {
    super ();
  }

  public SitePartie (final GerantTransactions gt) {
    super (gt);
  }

  public Set<Commandes> getCommandes () {
    return this.commandes;
  }

  public int getId () {
    return this.id;
  }

  public SitePartie getParNom (final String p) {
    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("partie", p); //$NON-NLS-1$
    final List<SitePartie> l = this.requete (h);
    if (l.size () > 0) { return l.get (0); }
    return null;
  }

  public String getPartie () {
    return this.partie;
  }

  public void setCommandes (final Set<Commandes> c) {
    this.commandes = c;
  }

  public void setId (final int idP) {
    this.id = idP;
  }

  public void setPartie (final String p) {
    this.partie = p;
  }
}
