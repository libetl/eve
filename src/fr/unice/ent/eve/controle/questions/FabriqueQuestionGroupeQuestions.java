package fr.unice.ent.eve.controle.questions;

import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.questions.QuestionType;
import fr.unice.ent.eve.modele.questions.types.QuestionGroupeQuestions;

/**
 * Fabrique de questions pour les questions ou chaque choix est a evaluer
 * 
 * @author benychou
 * 
 */
public class FabriqueQuestionGroupeQuestions extends QuestionFactory {
  private QuestionGroupeQuestions q;
  private Question                qsuper;

  /**
   * Constructeur de fabrique. Appelle lors de l'obtention de la fabrique
   */
  public FabriqueQuestionGroupeQuestions () {
    super ();
  }

  @Override
  protected void actualiser () {
    this.q.mettreAJour (this.q.toQuestion ());
  }

  @Override
  protected void construireChoix () {
  }

  @Override
  protected void construireId () {
    this.q.setId (this.qsuper.getId ());
  }

  @Override
  protected void construireOrdre () {
    this.q.setOrdre (this.getS ().getQuestionSondage ().size () + 1);
  }

  @Override
  protected void construireTitre () {
    this.q.setTitre ("Question : " + this.q.getClass ().getSimpleName ()); //$NON-NLS-1$
  }

  @Override
  protected void construireType () {
    this.q
        .setIdType (QuestionType.staticInstance (this.getGt ())
            .getParNom (this.q.getClass ().getSimpleName ().substring (8))
            .getId ());
  }

  @Override
  protected void creerInstanceQuestion () {
    this.q = new QuestionGroupeQuestions (this.getGt ());
    this.qsuper = this.q.toQuestion ();
    this.qsuper.ajout ();
    this.qsuper.mettreAJour (this.qsuper);
    this.qsuper.rafraichir (this.qsuper);
  }

  /**
   * Methode concrete d'obtention de resultat
   * 
   * @return un objet de type QuestionGroupeQuestions
   */
  @Override
  public Question obtenirResultat () {
    return this.q;
  }

}
