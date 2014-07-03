package fr.unice.ent.eve.controle.erreurs;

/**
 * Exception levee si le Service Web a mal repondu
 * 
 * @author benychou
 * 
 */
public class ServiceWebException extends Exception {

  private static final long serialVersionUID = 6416179480057479487L;

  public ServiceWebException (final String message) {
    super (message);
  }

}
