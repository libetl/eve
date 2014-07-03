package fr.unice.ent.eve.modele.groupes;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.sondages.GroupeSondage;

public class Groupe extends Modele<Groupe> {
  private static Groupe instance;

  public static Groupe staticInstance (final GerantTransactions s) {
    if (Groupe.instance == null) {
      Groupe.instance = new Groupe (s);
    }
    return Groupe.instance;
  }

  private GroupeContrainte   contrainte;
  private Set<GroupeSondage> gs;
  private long               id;
  private String             nom;

  private Set<Permission>    permissions;

  public Groupe () {

  }

  public Groupe (final GerantTransactions gt) {
    super (gt);
  }

  public Groupe (final GerantTransactions gt, final String n,
      final GroupeContrainte c) {
    super (gt);
    this.nom = n;
    this.contrainte = c;
  }

  public Groupe (final GerantTransactions gt, final String n, final int idC) {
    super (gt);
    this.nom = n;
    final GroupeContrainte c = (GroupeContrainte) gt.getSession ().load (
        GroupeContrainte.class, new Integer (idC));
    this.contrainte = c;
  }

  public Groupe (final String n, final GroupeContrainte c) {
    this.nom = n;
    this.contrainte = c;
  }

  @Override
  public Object getComparableId () {
    return this.nom;
  }

  public GroupeContrainte getContrainte () {
    return this.contrainte;
  }

  public Set<GroupeSondage> getGs () {
    return this.gs;
  }

  public long getId () {
    return this.id;
  }

  public String getNom () {
    return this.nom;
  }

  public Groupe getParNom (final String n) {
    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("nom", n); //$NON-NLS-1$
    final List<Groupe> l = this.requete (h);
    if (l.size () > 0) { return l.get (0); }
    return null;
  }

  public Set<Permission> getPermissions () {
    return this.permissions;
  }

  public void setContrainte (final GroupeContrainte c) {
    this.contrainte = c;
  }

  public void setGs (final Set<GroupeSondage> gS) {
    this.gs = gS;
  }

  public void setId (final long idG) {
    this.id = idG;
  }

  public void setNom (final String n) {
    this.nom = n;
  }

  public void setPermissions (final Set<Permission> p) {
    this.permissions = p;
  }
}
