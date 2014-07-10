package fr.unice.ent.eve.modele;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

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
  public List<?> list (Class<?> c) {
	  if (this.q instanceof com.mongodb.DBCursor){
		  List<Object> l = new LinkedList<Object> ();
		  while (((com.mongodb.DBCursor) this.q).hasNext ()){
			  DBObject current = ((com.mongodb.DBCursor) this.q).next ();
			l.add (new Gson ().fromJson (JSON.serialize (current), c));  
		  }
        return l;
	  }
	  if (this.q instanceof org.hibernate.Query){
	    return ((org.hibernate.Query)this.q).list ();
	  }
	  return null;
  }
}
