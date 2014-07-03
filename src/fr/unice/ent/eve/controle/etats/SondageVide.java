package fr.unice.ent.eve.controle.etats;

/**
 * Etats et transitions possibles a partir de l'etat vide d'un sondage
 * 
 * @author benychou
 * 
 */
public class SondageVide extends AbstractEtatSondage {

  /**
   * 
   */
  private static final long serialVersionUID = 2699937602372881020L;

  public SondageVide () {
    super ();
  }

  public SondageVide (final Boolean b) {
    super (b);
  }

  @Override
  public void ajoutQuestion () {
    super.changerEtat ("Pret"); //$NON-NLS-1$
  }

  @Override
  public void changerPermissions () {
  }

  @Override
  public void editer () {
  }

  @Override
  public void modifierQuestions () {
  }

  @Override
  public void supprimer () {
  }
}
