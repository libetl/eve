package fr.unice.ent.eve.modele.groupes;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.CommandePermission;

public class PermissionType extends Modele<PermissionType> {
  private static PermissionType instance;

  public static PermissionType get (final GerantTransactions gt, final String s) {
    final HashMap<String, String> contraintes = new HashMap<String, String> ();
    contraintes.put ("nomPermission", s); //$NON-NLS-1$
    final List<PermissionType> l = PermissionType.staticInstance (gt).requete (
        contraintes);

    if (l.size () > 0) { return l.get (0); }
    return null;
  }

  public static PermissionType staticInstance (final GerantTransactions s) {
    if (PermissionType.instance == null) {
      PermissionType.instance = new PermissionType (s);
    }
    return PermissionType.instance;
  }

  private Set<CommandePermission> cope;
  private String                  description;
  private int                     idPermission;

  private String                  nomPermission;

  private Set<Permission>         permissions;

  public PermissionType () {
  }

  public PermissionType (final GerantTransactions gt) {
    super (gt);
  }

  public PermissionType (final GerantTransactions gt, final String permNom,
      final String descr) {
    super (gt);
    this.nomPermission = permNom;
    this.description = descr;
  }

  public PermissionType (final String nomP) {
    this.nomPermission = nomP;
  }

  @Override
  public Object getComparableId () {
    return this.nomPermission;
  }

  public Set<CommandePermission> getCope () {
    return this.cope;
  }

  public String getDescription () {
    return this.description;
  }

  public int getIdPermission () {
    return this.idPermission;
  }

  public String getNomPermission () {
    return this.nomPermission;
  }

  public PermissionType getParNom (final String comm) {
    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("nomPermission", comm); //$NON-NLS-1$
    final List<PermissionType> l = this.requete (h);
    if (l.size () > 0) { return l.get (0); }
    return null;
  }

  public Set<Permission> getPermissions () {
    return this.permissions;
  }

  public void setCope (final Set<CommandePermission> cp) {
    this.cope = cp;
  }

  public void setDescription (final String ds) {
    this.description = ds;
  }

  public void setIdPermission (final int idPt) {
    this.idPermission = idPt;
  }

  public void setNomPermission (final String nP) {
    this.nomPermission = nP;
  }

  public void setPermissions (final Set<Permission> p) {
    this.permissions = p;
  }
}
