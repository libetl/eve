package fr.unice.ent.eve.controle.etats;

/**
 * Etats et transitions possibles a partir de l'etat cache d'un sondage
 * 
 * @author benychou
 * 
 */
public class SondageCache extends AbstractEtatSondage {

  private static final long serialVersionUID = 5625653967666939691L;

  public SondageCache () {
    super ();
  }

  public SondageCache (final Boolean b) {
    super (b);
  }

  @Override
  public void restaurer () {
  }

  @Override
  public void supprimer () {

  }
}
