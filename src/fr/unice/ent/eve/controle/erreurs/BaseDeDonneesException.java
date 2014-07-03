package fr.unice.ent.eve.controle.erreurs;

/**
 * Exception en cas d'erreur dans la couche DAO (data access objects), qui est
 * la couche d'acces a la base de donnees par objet.
 * 
 * @author benychou
 * 
 */
public class BaseDeDonneesException extends RuntimeException {

  private static final long serialVersionUID = -1786380524160228495L;

  public BaseDeDonneesException () {
    super ();
  }

  public BaseDeDonneesException (final String message) {
    super (message);
  }

  public BaseDeDonneesException (final String message, final Throwable cause) {
    super (message, cause);
  }

  public BaseDeDonneesException (final Throwable cause) {
    super (cause);
  }

}
