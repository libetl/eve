package fr.unice.ent.eve.modele.reponses;

import fr.unice.ent.eve.modele.GerantTransactions;

public class ReponseLibre extends Reponse {

  /**
   * 
   */
  private static final long serialVersionUID = -5806009178479651988L;
  private String            texte;
  private TexteReponse      tr;

  public ReponseLibre () {
    super ();
  }

  public ReponseLibre (final GerantTransactions gt) {
    super (gt);
  }

  public ReponseLibre (final long idC, final long idS, final long idQ,
      final int rep) {
    super (idC, idS, idQ, rep);
  }

  @Override
  public void ajout () {
    this.tr = new TexteReponse (this.getGT ());
    this.tr.setTexte (this.texte);
    this.tr.ajout ();
    this.rafraichir (this.tr);
    this.toReponse ().ajout ();
  }

  public void setTexteChaine (final String t) {
    this.texte = t;
  }

  @Override
  protected Reponse toReponse () {
    final Reponse r = new Reponse (this.getGT (), this.getIdChoix (),
        this.getIdSondage (), this.getIdQuestion (), this.tr.getId ());
    return r;
  }
}
