package fr.unice.ent.eve.controle.commandes;

/**
 * Commande vide pour indexation.
 */
public class CommandeRemonterQuestion extends AbstractCommande {

  public CommandeRemonterQuestion () {
    super ();
  }

  public CommandeRemonterQuestion (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    return null;
  }

}
