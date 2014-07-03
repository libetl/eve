package fr.unice.ent.eve.modele.groupes;

import java.util.HashMap;
import java.util.List;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class Permission extends Modele<Permission> {
  private static Permission instance;

  public static Permission staticInstance (final GerantTransactions s) {
    if (Permission.instance == null) {
      Permission.instance = new Permission (s);
    }
    return Permission.instance;
  }

  private Groupe         groupe;
  private long           id;
  private long           idGroupe;
  private long           idPersonne;
  private long           idSondage;
  private int            naPe;
  private PermissionType naturePermission;

  private Personne       personne;

  private Sondage        sondage;

  public Permission () {
  }

  public Permission (final GerantTransactions gt) {
    super (gt);
  }

  public Permission (final GerantTransactions s, final long idP,
      final long idGr, final long idSo, final String nP0) {
    super (s);
    this.idPersonne = idP;
    this.idGroupe = idGr;
    this.idSondage = idSo;
    final HashMap<String, String> hm = new HashMap<String, String> ();
    hm.put ("nomPermission", nP0); //$NON-NLS-1$
    final List<PermissionType> lPT = PermissionType.staticInstance (
        this.getGT ()).requete (hm);
    if (lPT.size () > 0) {
      this.naturePermission = lPT.get (0);
    } else {
      this.naturePermission = new PermissionType (this.getGT (), nP0,
          new String ());
      this.naturePermission.ajout ();
      this.mettreAJour ();
    }
    this.naPe = this.naturePermission.getIdPermission ();
  }

  public Permission get (final long idP, final long idG, final long idS,
      final int idPerm) {

    final List<Permission> lP = this.getL (idP, idG, idS, idPerm);

    if (lP.size () == 0) { return null; }
    return lP.get (0);
  }

  @Override
  public Object getComparableId () {
    return new Integer (this.naPe);
  }

  public Groupe getGroupe () {
    return this.groupe;
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

  public List<Permission> getL (final long idP, final long idG, final long idS,
      final int idPerm) {
    final HashMap<String, String> hm = new HashMap<String, String> ();
    if (idP != -1) {
      hm.put ("idPersonne", "" + idP); //$NON-NLS-1$//$NON-NLS-2$
    }
    if (idG != -1) {
      hm.put ("idGroupe", "" + idG); //$NON-NLS-1$//$NON-NLS-2$
    }
    if (idS != -1) {
      hm.put ("idSondage", "" + idS); //$NON-NLS-1$//$NON-NLS-2$
    }
    if (idPerm != -1) {
      hm.put ("naturePermission", "" + idPerm); //$NON-NLS-1$//$NON-NLS-2$
    }
    final List<Permission> lP = this.requete (hm);

    return lP;
  }

  public int getNaPe () {
    return this.naPe;
  }

  public PermissionType getNaturePermission () {
    return this.naturePermission;
  }

  public Personne getPersonne () {
    return this.personne;
  }

  public Sondage getSondage () {
    return this.sondage;
  }

  public void setGroupe (final Groupe gr) {
    this.groupe = gr;
  }

  public void setId (final long id0) {
    this.id = id0;
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

  public void setNaPe (final int np0) {
    this.naPe = np0;
  }

  public void setNaturePermission (final PermissionType nPerm) {
    this.naturePermission = nPerm;
  }

  public void setPersonne (final Personne p) {
    this.personne = p;
  }

  public void setSondage (final Sondage so) {
    this.sondage = so;
  }
}
