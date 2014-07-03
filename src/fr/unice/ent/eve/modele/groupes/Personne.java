package fr.unice.ent.eve.modele.groupes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.CommandePermission;

public class Personne extends Modele<Personne> {
  private static Personne instance;
  private static String   typeUid;

  public static String getTypeUid () {
    return Personne.typeUid;
  }

  public static Personne staticInstance (final GerantTransactions gt) {
    if (Personne.instance == null) {
      Personne.instance = new Personne (gt);
    }
    return Personne.instance;
  }

  private long            id;

  private Set<Permission> permissions;

  private String          valeurUid;

  public Personne () {

  }

  public Personne (final GerantTransactions gt) {
    super (gt);
  }

  public Personne (final GerantTransactions gt, final String val) {
    super (gt);
    this.valeurUid = val;
  }

  public Personne (final String val) {
    super ();
    this.valeurUid = val;
  }

  public boolean aPermission (final String nomPermission, final long groupe,
      final long sondage) {
    boolean permis = false;

    if (this.permissions == null) { return false; }

    final Iterator<Permission> it = this.permissions.iterator ();

    while (!permis && it.hasNext ()) {
      final Permission perm = it.next ();
      if ("SuperUtilisation".equals (perm.getNaturePermission ().getNomPermission ()) || //$NON-NLS-1$
          (nomPermission.equals (perm.getNaturePermission ()
              .getNomPermission ())
              && ((perm.getIdGroupe () == 0) || (perm.getIdGroupe () == groupe)) && ((perm
              .getIdSondage () == 0) || (perm.getIdSondage () == sondage)))) {
        permis = true;
      }
    }

    return permis;
  }

  public String aPermissions (final Set<CommandePermission> p,
      final long idGroupe, final long idSondage) {
    boolean res = true;
    final Iterator<CommandePermission> it = p.iterator ();
    while (res && it.hasNext ()) {
      final PermissionType perm = it.next ().getP ();
      res &= this.aPermission (perm.getNomPermission (), idGroupe, idSondage);
      if (!res) { return perm.getNomPermission (); }
    }
    return new String ();
  }

  public long getId () {
    return this.id;
  }

  public Personne getParNom (final String nom) {
    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("valeurUid", nom); //$NON-NLS-1$
    final List<Personne> l = this.requete (h);
    if (l.size () > 0) { return l.get (0); }
    return null;
  }

  public Set<Permission> getPermissions () {
    return this.permissions;
  }

  public List<PermissionType> getPermissions (
      final List<CommandePermission> lcC, final Groupe g) {
    final List<PermissionType> res = new ArrayList<PermissionType> ();

    for (final CommandePermission cC : lcC) {
      if (this.aPermission (cC.getP ().getNomPermission (), g.getId (), 0)) {
        if (!res.contains (cC.getP ())) {
          res.add (cC.getP ());
        }
      }
    }

    return res;
  }

  public String getValeurUid () {
    return this.valeurUid;
  }

  public List<Groupe> groupes () {
    final ArrayList<Groupe> res = new ArrayList<Groupe> ();
    final HashMap<String, String> c = new HashMap<String, String> ();

    if (!this.isSuperAdmin ()) {
      c.put ("idPersonne", "" + this.id); //$NON-NLS-1$ //$NON-NLS-2$
      final List<Permission> l = Permission.staticInstance (this.getGT ())
          .requete (c);
      for (final Permission p : l) {
        if (p.getNaturePermission ().getNomPermission ().equals ("Appartenir")//$NON-NLS-1$
            && (p.getIdSondage () == 0) && (p.getGroupe () != null)) {
          final Groupe g = (Groupe) this.getGT ().getSession ()
              .load (Groupe.class, new Long (p.getGroupe ().getId ()));
          res.add (g);
        }
      }
    } else {
      for (final Groupe g : Groupe.staticInstance (this.getGT ()).requete ()) {
        final Groupe g2 = (Groupe) this.getGT ().getSession ()
            .load (Groupe.class, new Long (g.getId ()));
        res.add (g2);
      }
    }

    return res;
  }

  public boolean isSuperAdmin () {
    return this.aPermission ("SuperUtilisation", 0, 0); //$NON-NLS-1$
  }

  public void setId (final long id0) {
    this.id = id0;
  }

  public void setPermissions (final Set<Permission> p) {
    this.permissions = p;
  }

  public void setTypeUid (final String type) {
    Personne.typeUid = type;
  }

  public void setValeurUid (final String val) {
    this.valeurUid = val;
  }

  @Override
  public String toString () {
    return this.id + ", " + this.valeurUid + " "; //$NON-NLS-1$//$NON-NLS-2$
  }
}
