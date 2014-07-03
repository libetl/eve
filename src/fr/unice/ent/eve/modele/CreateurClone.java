package fr.unice.ent.eve.modele;

import java.io.Serializable;


/**
 * Classe permettant de recopier un objet en utilisant la serialisation
 * 
 * @author benychou
 * 
 */
public class CreateurClone {

  /**
   * Fonction de recopie
   * 
   * @param o
   *          un objet serialisable
   * @return un objet clone
   */
  public static Object recopie (final Serializable o) {
    return org.apache.commons.lang3.SerializationUtils.clone (o);
  }

  /**
   * Constructeur vide
   */
  public CreateurClone () {
    super ();
  }

}
