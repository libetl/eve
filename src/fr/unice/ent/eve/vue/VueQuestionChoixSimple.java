package fr.unice.ent.eve.vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;

/**
 * Vues pour le type de question a choix simple
 * 
 * @author benychou
 * 
 */
public class VueQuestionChoixSimple implements VueQuestion {

  public VueQuestionChoixSimple () {
    super ();
  }

  @Override
  public String vue (final Question q) {
    String res = "<div id=\"choix\">"; //$NON-NLS-1$
    final ArrayList<Choix> al = new ArrayList<Choix> ();
    al.addAll (q.getChoix ());
    Collections.sort (al);

    for (int i = 0 ; i < al.size () ; i++) {
      res += this.vueUn (al.get (i));
    }

    res += "</div>"; //$NON-NLS-1$
    return res;
  }

  @Override
  public String vueEdition (final Question q) {
    String res = "<div id=\"choixEdition\">"; //$NON-NLS-1$
    final ArrayList<Choix> al = new ArrayList<Choix> ();
    al.addAll (q.getChoix ());
    Collections.sort (al);

    for (int i = 0 ; i < al.size () ; i++) {
      final ArrayList<Choix> tmp = new ArrayList<Choix> ();
      tmp.add (al.get (i));
      res += this.vueEditionUn (tmp);
    }

    res += "</div><p></p><a href=\"javascript:commande ('AjouterChoix', " //$NON-NLS-1$
        + q.getId () + ")\"" //$NON-NLS-1$
        + "title=\"Ajouter un choix\">Ajouter un choix</a>"; //$NON-NLS-1$
    return res;
  }

  @Override
  public String vueEditionUn (final List<Choix> c) {
    String res = new String ();
    final Choix c2 = c.get (0);
    res += "Choix : <input type=\"text\" name=\"choix" + c2.getNumChoix () + "\"" //$NON-NLS-1$ //$NON-NLS-2$
        + "value=\"" + c2.getNomChoix () + "\"/>, de valeur" //$NON-NLS-1$//$NON-NLS-2$
        + "<input type=\"text\" name=\"valeurChoix" + c2.getNumChoix () + "\"" //$NON-NLS-1$ //$NON-NLS-2$
        + "value=\"" + c2.getValeur () + "\"/><br/>"; //$NON-NLS-1$ //$NON-NLS-2$
    return res;
  }

  @Override
  public String vueResultats (final Question q) {
    String res = new String ();
    int total;
    final ArrayList<Choix> alC = new ArrayList<Choix> ();
    res += "<p>Question n°" + q.getOrdre () + " : " //$NON-NLS-1$ //$NON-NLS-2$
        + q.getTitre () + "<br/><ul>"; //$NON-NLS-1$

    alC.addAll (q.getChoix ());
    Collections.sort (alC);

    total = 0;

    for (final Choix c : alC) {
      total += c.getReponses ().size ();
    }

    if (total > 0) {
      for (final Choix c : alC) {
        final double pourc = c.getReponses ().size () / (total * 1.0) * 100.0;
        res += "<li>" + c.getNomChoix () //$NON-NLS-1$
            + ": <img src=\"../../../vue/_layout/imgs/pourcentageGauche.png\"" //$NON-NLS-1$
            + " width=\"32\" height=\"16\" />" //$NON-NLS-1$
            + "<img src=\"../../../vue/_layout/imgs/pourcentage.png\"" //$NON-NLS-1$
            + " width=\"" + 2 * pourc + "\" height=\"16\" />" //$NON-NLS-1$//$NON-NLS-2$
            + "<img src=\"../../../vue/_layout/imgs/pourcentageDroite.png\"" //$NON-NLS-1$
            + " width=\"32\" height=\"16\" />" + (Math.round (pourc * 100)) / 100.0 + "%</li>"; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    res += "</ul></p>"; //$NON-NLS-1$
    return res;
  }

  private String vueUn (final Choix c) {
    String res = new String ();
    res += "<input type=\"radio\" name=\"numChoix" //$NON-NLS-1$
        + "\" value=\"" + c.getNumChoix () + "\"/>" + c.getNomChoix () //$NON-NLS-1$ //$NON-NLS-2$
        + "<br/>"; //$NON-NLS-1$
    return res;
  }
}
