package fr.unice.ent.eve.vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.reponses.Reponse;

/**
 * Vue pour le type de questions "Reponse Libre"
 * 
 * @author benychou
 * 
 */
public class VueQuestionReponseLibre implements VueQuestion {

  public VueQuestionReponseLibre () {
    super ();
  }

  @Override
  public String vue (final Question q) {
    q.hashCode ();
    String res = "<div id=\"choix\">"; //$NON-NLS-1$
    res += "<textarea name=\"reponseChoix\" cols=\"40\" rows=\"10\"></textarea>"; //$NON-NLS-1$
    res += "</div>"; //$NON-NLS-1$
    return res;
  }

  @Override
  public String vueEdition (final Question q) {
    q.hashCode ();
    return new String ();
  }

  @Override
  public String vueEditionUn (final List<Choix> c) {
    c.hashCode ();
    return new String ();
  }

  @Override
  public String vueResultats (final Question q) {
    String res = new String ();
    final ArrayList<Reponse> alR = new ArrayList<Reponse> ();
    res += "<p>Question n°" + q.getOrdre () + " : " //$NON-NLS-1$ //$NON-NLS-2$
        + q.getTitre () + "<br/><ul>"; //$NON-NLS-1$
    final Choix c = q.getChoix ().iterator ().next ();

    alR.addAll (c.getReponses ());
    Collections.sort (alR);

    for (final Reponse r : alR) {
      res += "<li>" + r.getTexte ().getTexte () + "</li>"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    res += "</ul></p>"; //$NON-NLS-1$
    return res;
  }
}
