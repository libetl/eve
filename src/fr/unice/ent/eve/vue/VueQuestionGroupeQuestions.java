package fr.unice.ent.eve.vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;

/**
 * Vues pour le type de question "groupe de questions"
 * 
 * @author benychou
 * 
 */
public class VueQuestionGroupeQuestions implements VueQuestion {
  public VueQuestionGroupeQuestions () {
    super ();
  }

  private ArrayList<Choix> listeUnChoix (final ArrayList<Choix> al,
      final int debut) {
    final ArrayList<Choix> alC = new ArrayList<Choix> ();
    for (int i = debut ; (i < al.size ())
        && (al.get (i).getNumChoix () == al.get (debut).getNumChoix ()) ; i++) {
      alC.add (al.get (i));
    }
    return alC;
  }

  @Override
  public String vue (final Question q) {
    String res = "<div id=\"choix\">"; //$NON-NLS-1$
    final ArrayList<Choix> al = new ArrayList<Choix> ();
    al.addAll (q.getChoix ());
    Collections.sort (al);

    int i = 0;
    while (i < al.size ()) {
      final List<Choix> lC = this.listeUnChoix (al, i);
      res += this.vueUn (lC);
      i += lC.size ();
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

    int i = 0;
    while (i < al.size ()) {
      final List<Choix> lC = this.listeUnChoix (al, i);
      res += this.vueEditionUn (lC);
      i += lC.size ();
    }

    res += "</div><p></p><a href=\"javascript:commande ('AjouterChoix', " //$NON-NLS-1$
        + q.getId () + ")\"" //$NON-NLS-1$
        + "title=\"Ajouter un choix\">Ajouter un choix</a>"; //$NON-NLS-1$
    return res;
  }

  @Override
  public String vueEditionUn (final List<Choix> c) {
    String res = new String ();
    if (c.size () == 0) { return res; }
    final int taille = c.size ();
    final Choix c2 = c.get (0);
    res += "Choix : <input type=\"text\" name=\"choix" + c2.getNumChoix () + "\"" //$NON-NLS-1$ //$NON-NLS-2$
        + "value=\"" + c2.getNomChoix () + "\"/>, nombre de valeurs" //$NON-NLS-1$//$NON-NLS-2$
        + "<input type=\"text\" name=\"nbValeurChoix" + c2.getNumChoix () + "\"" //$NON-NLS-1$ //$NON-NLS-2$
        + "value=\"" + taille + "\"/><br/>"; //$NON-NLS-1$ //$NON-NLS-2$
    return res;
  }

  @Override
  public String vueResultats (final Question q) {
    String res = new String ();
    final ArrayList<Choix> alC = new ArrayList<Choix> ();
    res += "<p>Question n°" + q.getOrdre () + " : " //$NON-NLS-1$ //$NON-NLS-2$
        + q.getTitre () + "<br/><ul>"; //$NON-NLS-1$

    alC.addAll (q.getChoix ());
    Collections.sort (alC);

    for (final Choix c : alC) {
      if (c.getReponses ().size () < 2) {
        res += "<li>" + c.getReponses ().size () + " personne a noté "; //$NON-NLS-1$ //$NON-NLS-2$
      } else {
        res += "<li>" + c.getReponses ().size () + " personnes ont noté "; //$NON-NLS-1$ //$NON-NLS-2$
      }
      res += (c.getValeur () - 1) + " la reponse " + c.getNomChoix (); //$NON-NLS-1$
    }
    res += "</ul></p>"; //$NON-NLS-1$
    return res;
  }

  private String vueUn (final List<Choix> c) {
    String res = new String ();
    final Choix c2 = c.get (0);
    res += c2.getNomChoix () + " : "; //$NON-NLS-1$

    Collections.sort (c);

    for (int i = 0 ; i < c.size () ; i++) {
      res += "<input type=\"radio\" name=\"numChoix" + c.get (i).getNumChoix () //$NON-NLS-1$
          + "\" value=\"" + c.get (i).getValeur () + "\"/>" + i; //$NON-NLS-1$ //$NON-NLS-2$
    }
    res += "<br/>"; //$NON-NLS-1$
    return res;
  }
}
