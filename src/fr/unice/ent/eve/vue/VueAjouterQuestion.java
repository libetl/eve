package fr.unice.ent.eve.vue;

import java.util.Collections;
import java.util.List;

import fr.unice.ent.eve.modele.questions.QuestionType;

/**
 * Renvoit le formattage pour voir les differents types de questions pouvant
 * etre ajoutes
 * 
 * @author benychou
 * 
 */
public class VueAjouterQuestion {

  public static String vue (final List<QuestionType> lST) {
    String res = new String ();

    Collections.sort (lST);

    for (final QuestionType questionType : lST) {
      res += VueAjouterQuestion.vueUn (questionType);
    }

    return res;
  }

  public static String vueUn (final QuestionType qt) {
    return "<a href=\"javascript:ajouterQuestion ('Question" + qt.getType () + "');\"" //$NON-NLS-1$ //$NON-NLS-2$
        + " title=\"Ajouter une Question de type '" + qt.getNom () + "'\">" //$NON-NLS-1$ //$NON-NLS-2$
        + "<img src=\"../../../vue/_layout/imgs/Question" + qt.getType () + "Ajout.png\"/></a>&nbsp;&nbsp;&nbsp;"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public VueAjouterQuestion () {
    super ();
  }
}
