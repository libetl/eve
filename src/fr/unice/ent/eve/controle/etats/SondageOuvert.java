package fr.unice.ent.eve.controle.etats;

/**
 * Etats et transitions possibles a partir de l'etat ouvert d'un sondage
 * 
 * @author benychou
 * 
 */
public class SondageOuvert extends AbstractEtatSondage {

  private static final long serialVersionUID = -7614803084864075148L;

  public SondageOuvert () {
    super ();
  }

  public SondageOuvert (final Boolean b) {
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
    super.changerEtat ("Pret"); //$NON-NLS-1$
  }

  @Override
  public void consulterResultats () {
  }

  @Override
  public void dupliquer () {
  }

  @Override
  public void editer () {
    super.changerEtat ("Pret"); //$NON-NLS-1$
  }

  @Override
  public void envoyer () {
  }

  @Override
  public void fermer () {
    super.changerEtat ("Pret"); //$NON-NLS-1$
  }

  @Override
  public void lier () {
  }

  @Override
  public void modifierQuestions () {
    super.changerEtat ("Pret"); //$NON-NLS-1$
  }

  @Override
  public void ouvrir () {
    super.changerEtat ("Pret"); //$NON-NLS-1$
  }

  @Override
  public void remiseAZero () {
    super.changerEtat ("Pret"); //$NON-NLS-1$
  }

  @Override
  public void voter () {
  }
}
