package fr.unice.ent.eve.controle.commandes;

/**
 * Commande vide pour indexation
 */
public class CommandeDescendreQuestion extends AbstractCommande {

  public CommandeDescendreQuestion () {
    super ();
  }

  public CommandeDescendreQuestion (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    return null;
  }

}
