package fr.unice.ent.eve.modele.questions.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Transaction;
import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.reponses.Reponse;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class QuestionGroupeQuestions extends Question {

  /**
   * 
   */
  private static final long serialVersionUID = 3064369972948187174L;

  public QuestionGroupeQuestions () {
    super (true);
  }

  public QuestionGroupeQuestions (final Boolean b) {
    super (b.booleanValue ());
  }

  public QuestionGroupeQuestions (final GerantTransactions gt) {
    super (gt);
  }

  public QuestionGroupeQuestions (final String t) {
    super (t);
  }

  @Override
  public void ajout () {
    this.toQuestion ().ajout ();
  }

  @Override
  protected List<Choix> ajoutChoix (final String nomChoix, final int taille) {
    final ArrayList<Choix> res = new ArrayList<Choix> ();
    final Choix c = new Choix (this.getGT (), this.getId (), taille + 1,
        nomChoix, 1);
    c.ajout ();
    res.add (c);
    return res;
  }

  @Override
  public Reponse[] creerReponse (final Sondage s, final Map<String, Object> m) {
    final ArrayList<Reponse> reponses = new ArrayList<Reponse> ();
    final int taille = this.getNbChoixDifferents ();

    int i = 1;
    while (i <= taille) {
      int valeur = 0;
      valeur = Integer.parseInt (((String[]) m.get ("numChoix" + i))[0]); //$NON-NLS-1$

      reponses.add (new Reponse (this.retrouverChoix (i, valeur).getId (), s
          .getId (), this.getId (), 0));
      i++;
    }

    final Reponse[] res = new Reponse[reponses.size ()];
    for (i = 0 ; i < reponses.size () ; i++) {
      res[i] = reponses.get (i);
    }

    return res;
  }

  private int getNbChoixDifferents () {
    int nb = 0;
    final Iterator<Choix> itC = this.getChoix ().iterator ();

    while (itC.hasNext ()) {
      final Choix ctmp = itC.next ();
      if (ctmp.getNumChoix () > nb) {
        nb = ctmp.getNumChoix ();
      }
    }

    return nb;
  }

  @Override
  public void mettreAJour (final Object o) {
    super.mettreAJour (((Question) o).toQuestion ());
  }

  @Override
  public void modifierChoix (final Map<String, Object> m) {
    final int taille = this.getChoix ().size ();

    final Transaction tx = this.getGT ().debutTransaction ();

    this.supprimerChoix (this.getChoix ());

    int i = 1;
    while (i <= taille) {
      if (m.get ("choix" + i) != null) //$NON-NLS-1$
      {
        final int nbChoix = Integer.parseInt (((String[]) m
            .get ("nbValeurChoix" + i))[0]); //$NON-NLS-1$

        for (int j = 1 ; j <= nbChoix ; j++) {
          final Choix ctmp = new Choix (this.getGT (), this.getId (), i,
              ((String[]) m.get ("choix" + i))[0], j); //$NON-NLS-1$
          this.getChoix ().add (ctmp);
          this.getGT ().enregistrerT (ctmp);
        }
      }
      i++;
    }
    this.getGT ().finTransaction (tx);
    this.mettreAJour ();
  }

  @Override
  public void rafraichir (final Object o) {
    super.rafraichir (((Question) o).toQuestion ());
  }

  private Choix retrouverChoix (final int numChoix, final int valeur) {
    final Iterator<Choix> itC = this.getChoix ().iterator ();
    boolean trouve = false;
    Choix res = null;

    while (!trouve && itC.hasNext ()) {
      final Choix c = itC.next ();
      if ((c.getNumChoix () == numChoix) && (c.getValeur () == valeur)) {
        res = c;
        trouve = true;
      }
    }

    return res;
  }

  private void supprimerChoix (final Set<Choix> lC) {
    for (final Choix c : lC) {
      this.getGT ().supprimerT (c);
    }
    lC.clear ();

  }
}
