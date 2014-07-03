package fr.unice.ent.eve.modele.commandes;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.GroupeContrainte;

public class ContrainteCommande extends Modele<ContrainteCommande> {

  private static ContrainteCommande instance;

  public static ContrainteCommande staticInstance (final GerantTransactions s) {
    if (ContrainteCommande.instance == null) {
      ContrainteCommande.instance = new ContrainteCommande (s);
    }
    return ContrainteCommande.instance;
  }

  private Commandes        commande;
  private GroupeContrainte contrainte;

  private long             id;

  private int              idCommande;

  private int              idContrainte;

  public ContrainteCommande () {
  }

  public ContrainteCommande (final GerantTransactions gt) {
    super (gt);
  }

  public ContrainteCommande (final GerantTransactions gt, final int idCont,
      final int idComm) {
    super (gt);
    this.idContrainte = idCont;
    this.idCommande = idComm;
  }

  public Commandes getCommande () {
    return this.commande;
  }

  @Override
  public Object getComparableId () {
    return new Integer (this.idCommande);
  }

  public GroupeContrainte getContrainte () {
    return this.contrainte;
  }

  public long getId () {
    return this.id;
  }

  public int getIdCommande () {
    return this.idCommande;
  }

  public int getIdContrainte () {
    return this.idContrainte;
  }

  public void setCommande (final Commandes comm) {
    this.commande = comm;
  }

  public void setContrainte (final GroupeContrainte gC) {
    this.contrainte = gC;
  }

  public void setId (final long idCC) {
    this.id = idCC;
  }

  public void setIdCommande (final int idC) {
    this.idCommande = idC;
  }

  public void setIdContrainte (final int idC) {
    this.idContrainte = idC;
  }
}
