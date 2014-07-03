package fr.unice.ent.eve.controle.commandes;

/**
 * Commande vide pour indexation
 * 
 * @author benychou
 * 
 */
public class CommandeSupprimerQuestion extends AbstractCommande {

  public CommandeSupprimerQuestion () {
    super ();
  }

  public CommandeSupprimerQuestion (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    return null;
  }

}
