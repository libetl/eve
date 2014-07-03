package fr.unice.ent.eve.controle.commandes;

/**
 * Commande vide pour indexation
 * 
 * @author benychou
 * 
 */
public class CommandeSupprimerPersonnes extends AbstractCommande {

  public CommandeSupprimerPersonnes () {
    super ();
  }

  public CommandeSupprimerPersonnes (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    return null;
  }

}
