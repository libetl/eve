package fr.unice.ent.eve.controle.questions;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.sondages.Sondage;

/**
 * Fabrique abstraite de question.
 * 
 * <br/>
 * <br/>
 * Cette fabrique permet de creer une question dans un sondage. <br/>
 * Une question pouvant etre de plusieurs types et ayant plusieurs
 * presentations, cette classe renvoit un resultat abstrait.
 * 
 * <br/>
 * <br/>
 * <u>Methode de creation d'une question :</u>
 * <ul>
 * <li>QuestionFactory qf = QuestionFactory.obtenirFabrique (type);</li>
 * <li>passer les parametres eventuels</li>
 * <li>qf.construire ();</li>
 * <li>QuestionConcrete q = qf.obtenirResultat ();</li>
 * </ul>
 * QuestionConcrete etant une instance du type de question souhaite
 * 
 * @author benychou
 * 
 */
public abstract class QuestionFactory {
  /**
   * Methode d'obtention d'une fabrique concrete a partir du nom
   * 
   * @param fabrique
   *          le nom de la fabrique
   * @return la fabrique souhaitee
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   */
  public static QuestionFactory obtenirFabrique (final String fabrique)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException {
    final QuestionFactory qf = (QuestionFactory) Class
        .forName ("fr.unice.ent.eve.controle.questions.Fabrique" + fabrique).newInstance (); //$NON-NLS-1$
    return qf;
  }

  private GerantTransactions gt;

  private Sondage            s;

  protected abstract void actualiser ();

  /**
   * Methode de construction d'une question
   */
  public void construire () {
    this.creerInstanceQuestion ();
    this.construireId ();
    this.construireTitre ();
    this.construireType ();
    this.construireOrdre ();
    this.construireChoix ();
    this.actualiser ();
  }

  protected abstract void construireChoix ();

  protected abstract void construireId ();

  protected abstract void construireOrdre ();

  protected abstract void construireTitre ();

  protected abstract void construireType ();

  protected abstract void creerInstanceQuestion ();

  /**
   * Obtient la classe de gestion de session bdd liee a la fabrique
   */
  public GerantTransactions getGt () {
    return this.gt;
  }

  /**
   * Obtient le sondage lie a la fabrique
   * 
   * @return un objet de type Sondage
   */
  public Sondage getS () {
    return this.s;
  }

  /**
   * Methode abstraite d'obtention du resultat
   * 
   * @return un objet de type "sous-classe de Question"
   */
  public abstract Question obtenirResultat ();

  /**
   * Fixer la classe de gestion de session bdd
   */
  public void setGt (final GerantTransactions gT) {
    this.gt = gT;
  }

  /**
   * Fixer le sondage lie a la fabrique
   * 
   * @param so
   *          le sondage
   */
  public void setS (final Sondage so) {
    this.s = so;
  }

}
