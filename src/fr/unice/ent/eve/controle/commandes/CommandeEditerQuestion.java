package fr.unice.ent.eve.controle.commandes;

/**
 * Commande vide pour indexation
 */
public class CommandeEditerQuestion extends AbstractCommande {

  public CommandeEditerQuestion () {
    super ();
  }

  public CommandeEditerQuestion (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    return null;
  }

}
