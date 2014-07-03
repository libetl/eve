package fr.unice.ent.eve.modele;

/**
 * Classe d'encapsulation de l'objet Transaction de la couche DAO
 * 
 * @author benychou
 * 
 */
public class Transaction {

  private final Object transaction;

  /**
   * Constructeur qui affecte le champ de l'objet encapsule
   * 
   * @param tr
   */
  public Transaction (final Object tr) {
    super ();
    this.transaction = tr;
  }

  /**
   * Declenche les actions en attente dans la base de donnees
   */
  public void commit () {
	  if (this.transaction instanceof org.hibernate.Transaction){
        ((org.hibernate.Transaction)this.transaction).commit ();
	  }
  }

}
