package fr.unice.ent.eve.controle.commandes;

/**
 * Commande vide pour indexation
 */
public class CommandeAjouterPersonnes extends AbstractCommande {

  public CommandeAjouterPersonnes () {
    super ();
  }

  public CommandeAjouterPersonnes (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    return null;
  }

}
