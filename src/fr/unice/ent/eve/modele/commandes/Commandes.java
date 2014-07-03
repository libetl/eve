package fr.unice.ent.eve.modele.commandes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.PermissionType;

public class Commandes extends Modele<Commandes> {
  private static Commandes instance;

  public static Commandes staticInstance (final GerantTransactions s) {
    if (Commandes.instance == null) {
      Commandes.instance = new Commandes (s);
    }
    return Commandes.instance;
  }

  private String                  commande;
  private Set<ContrainteCommande> contraintes;
  private Set<CommandePermission> cope;
  private String                  description;
  private int                     idCommande;
  private int                     idPartie;
  private int                     niveau;

  private SitePartie              partie;

  public Commandes () {
  }

  public Commandes (final GerantTransactions gt) {
    super (gt);
  }

  public Commandes (final String c, final int n) {
    super ();
    this.commande = c;
    this.niveau = n;
  }

  public String getCommande () {
    return this.commande;
  }

  @Override
  public Object getComparableId () {
    return new Integer (this.idCommande);
  }

  public Set<ContrainteCommande> getContraintes () {
    return this.contraintes;
  }

  public Set<CommandePermission> getCope () {
    return this.cope;
  }

  public String getDescription () {
    return this.description;
  }

  public int getIdCommande () {
    return this.idCommande;
  }

  public int getIdPartie () {
    return this.idPartie;
  }

  public int getNiveau () {
    return this.niveau;
  }

  public List<Commandes> getParNiveau (final int niv) {
    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("niveau", "" + niv); //$NON-NLS-1$ //$NON-NLS-2$
    final List<Commandes> l = this.requete (h);
    return l;
  }

  public Commandes getParNom (final String comm) {
    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("commande", comm); //$NON-NLS-1$
    final List<Commandes> l = this.requete (h);
    if (l.size () > 0) { return l.get (0); }
    return null;
  }

  public SitePartie getPartie () {
    return this.partie;
  }

  public Set<PermissionType> getPermissionTypeSet (final String comm) {
    final Commandes c = Commandes.staticInstance (this.getGT ()).getParNom (
        comm);
    final HashSet<PermissionType> res = new HashSet<PermissionType> ();
    for (final CommandePermission commandePermission : c.getCope ()) {
      res.add (commandePermission.getP ());
    }
    return res;
  }

  public void setCommande (final String c) {
    this.commande = c;
  }

  public void setContraintes (final Set<ContrainteCommande> cC) {
    this.contraintes = cC;
  }

  public void setCope (final Set<CommandePermission> cp) {
    this.cope = cp;
  }

  public void setDescription (final String ds) {
    this.description = ds;
  }

  public void setIdCommande (final int idC) {
    this.idCommande = idC;
  }

  public void setIdPartie (final int idP) {
    this.idPartie = idP;
  }

  public void setNiveau (final int n) {
    this.niveau = n;
  }

  public void setPartie (final SitePartie p) {
    this.partie = p;
  }
}
