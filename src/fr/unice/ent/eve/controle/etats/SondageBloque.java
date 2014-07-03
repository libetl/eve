package fr.unice.ent.eve.controle.etats;

/**
 * Etats et transitions possibles a partir de l'etat bloque d'un sondage
 * 
 * @author benychou
 * 
 */
public class SondageBloque extends AbstractEtatSondage {
  private static final long serialVersionUID = -6862856804334248200L;

  public SondageBloque () {
    super ();
  }

  public SondageBloque (final Boolean b) {
    super (b);
  }

  @Override
  public void debloquer () {
  }

  @Override
  public void dupliquer () {
  }

  @Override
  public void lier () {
  }

  @Override
  public void supprimer () {
  }

}
