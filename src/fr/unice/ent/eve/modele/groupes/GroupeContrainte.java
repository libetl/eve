package fr.unice.ent.eve.modele.groupes;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.ContrainteCommande;

public class GroupeContrainte extends Modele<GroupeContrainte> implements
    Serializable {
  private static GroupeContrainte instance;
  /**
   * 
   */
  private static final long       serialVersionUID = 1636674480814365232L;

  public static GroupeContrainte staticInstance (final GerantTransactions s) {
    if (GroupeContrainte.instance == null) {
      GroupeContrainte.instance = new GroupeContrainte (s);
    }
    return GroupeContrainte.instance;
  }

  private Set<ContrainteCommande> contrainteCommandes;

  private Set<Groupe>             groupe;

  private int                     id;

  private String                  nomContrainte;

  public GroupeContrainte () {
    this.id = 0;
  }

  public GroupeContrainte (final GerantTransactions gt) {
    super (gt);
  }

  public GroupeContrainte (final GerantTransactions gt, final String nomC) {
    super (gt);
    this.nomContrainte = nomC;
  }

  public boolean contientCommande (final String string) {
    for (final ContrainteCommande cc : this.contrainteCommandes) {
      if (cc.getCommande ().getCommande ().equals (string)) { return true; }
    }
    return false;
  }

  @Override
  public Object getComparableId () {
    return new Integer (this.id);
  }

  public Set<ContrainteCommande> getContrainteCommandes () {
    return this.contrainteCommandes;
  }

  public GroupeContrainte getDefault () {
    final List<GroupeContrainte> lGC = this.requete ();
    Collections.sort (lGC);
    if (lGC.size () == 0) { return null; }
    return lGC.get (0);
  }

  public Set<Groupe> getGroupe () {
    return this.groupe;
  }

  public int getId () {
    return this.id;
  }

  public String getNomContrainte () {
    return this.nomContrainte;
  }

  public void setContrainteCommandes (final Set<ContrainteCommande> cC) {
    this.contrainteCommandes = cC;
  }

  public void setGroupe (final Set<Groupe> g) {
    this.groupe = g;
  }

  public void setId (final int idC) {
    this.id = idC;
  }

  public void setNomContrainte (final String nC) {
    this.nomContrainte = nC;
  }

}
