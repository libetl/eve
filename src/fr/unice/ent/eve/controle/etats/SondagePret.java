package fr.unice.ent.eve.controle.etats;

/**
 * Etats et transitions possibles a partir de l'etat pret d'un sondage
 * 
 * @author benychou
 * 
 */
public class SondagePret extends AbstractEtatSondage {

  private static final long serialVersionUID = -9052914946468289325L;

  public SondagePret () {
    super ();
  }

  public SondagePret (final Boolean b) {
    super (b);
  }

  @Override
  public void ajoutQuestion () {
    super.changerEtat ("Pret"); //$NON-NLS-1$
  }

  @Override
  public void bloquer () {
  }

  @Override
  public void cacher () {
  }

  @Override
  public void changerPermissions () {
  }

  @Override
  public void consulterResultats () {
  }

  @Override
  public void dupliquer () {
  }

  @Override
  public void editer () {
  }

  @Override
  public void modifierQuestions () {
  }

  @Override
  public void ouvrir () {
    super.changerEtat ("Ouvert"); //$NON-NLS-1$
  }

  @Override
  public void remiseAZero () {
    super.changerEtat ("Pret"); //$NON-NLS-1$
  }

  @Override
  public void supprimer () {

  }
}
