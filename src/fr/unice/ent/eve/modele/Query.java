package fr.unice.ent.eve.modele;

import java.util.List;

/**
 * Classe d'encapsulation de l'objet Query de la couche DAO
 * 
 * @author benychou
 * 
 */
public class Query {
  private final Object q;

  /**
   * Constructeur qui affecte le champ de l'objet encapsule
   * 
   * @param qu
   */
  public Query (final Object qu) {
    super ();
    this.q = qu;
  }

  /**
   * Methode pour obtenir une liste a partir d'une requete
   * 
   * @return la liste-resultat
   */
  public List<?> list () {
	  if (this.q instanceof com.mongodb.DBCursor){
        return ((com.mongodb.DBCursor)this.q).toArray ();
	  }
	  if (this.q instanceof org.hibernate.Query){
	    return ((org.hibernate.Query)this.q).list ();
	  }
	  return null;
  }
}
