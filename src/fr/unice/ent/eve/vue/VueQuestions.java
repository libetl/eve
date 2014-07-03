package fr.unice.ent.eve.vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;
import fr.unice.ent.eve.modele.sondages.QuestionSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;

/**
 * Renvoit le formattage pour voir la liste des questions d'un sondage en mode
 * edition ou resultats
 * 
 * @author benychou
 * 
 */
public class VueQuestions {
  public static String vue (final Sondage s, final List<Commandes> lC) {
    String res = new String ();
    final ArrayList<QuestionSondage> listeQuestions = new ArrayList<QuestionSondage> ();
    listeQuestions.addAll (s.getQuestionSondage ());
    Collections.sort (listeQuestions);
    for (final QuestionSondage qs : listeQuestions) {
      res += VueQuestions.vueUn (qs.getQuestion (), lC);
    }
    return res;
  }

  public static String vueEditionQuestion (final Question q, final Locale l) {
    String res = new String ();

    final Messages m = new Messages (VueQuestions.class.getName (), l);

    try {
      final VueQuestion vq = (VueQuestion) Class.forName (
          "fr.unice.ent.eve.vue.VueQuestion" //$NON-NLS-1$
              + q.getType ().getType ()).newInstance ();
      res += vq.vueEdition (q);
    } catch (final ClassNotFoundException e) {
      res += "<p><br/>" + m.getString ("erreur") //$NON-NLS-1$ //$NON-NLS-2$
          + "<br/></p>"; //$NON-NLS-1$
    } catch (final InstantiationException e) {
      res += "<p><br/>" + m.getString ("erreur") //$NON-NLS-1$ //$NON-NLS-2$
          + "<br/></p>"; //$NON-NLS-1$
    } catch (final IllegalAccessException e) {
      res += "<p><br/>" + m.getString ("erreur") //$NON-NLS-1$ //$NON-NLS-2$
          + "<br/></p>"; //$NON-NLS-1$
    }
    return res;
  }

  public static String vueFormulaire (final Question q, final Locale l) {
    String res = new String ();
    final String action = "\"javascript:commande ('ValiderFormulaire', " + q.getId () //$NON-NLS-1$
        + ")\""; //$NON-NLS-1$
    res += "<form action=\"#\">" //$NON-NLS-1$
        + "<fieldset style=\"width:80%;margin:auto\">" //$NON-NLS-1$
        + "<legend>Edition de question</legend>" //$NON-NLS-1$
        + "<p>Titre : <input type=\"text\" name=\"titre\" value=\"" + q.getTitre () //$NON-NLS-1$ 
        + "\"/></p><p>Obligatoire : <input type=\"checkbox\" name=\"obligatoire\" ";//$NON-NLS-1$

    if (q.isObligatoire ()) {
      res += "checked=\"checked\""; //$NON-NLS-1$
    }
    res += "/></p>"; //$NON-NLS-1$

    final Messages m = new Messages (VueQuestions.class.getName (), l);

    res += VueQuestions.vueEditionQuestion (q, l);

    res += "</div><p></p><p></p><input type=\"button\" value=\"" + m.getString ("modifier") //$NON-NLS-1$ //$NON-NLS-2$
        + "\" onclick=" + action + " " //$NON-NLS-1$//$NON-NLS-2$
        + "/><input type=\"reset\" value=\"" + m.getString ("fermer") //$NON-NLS-1$ //$NON-NLS-2$
        + "\"onclick=\"document.getElementById ('editerQuestion').style.display = 'none'\"/>" //$NON-NLS-1$
        + "</fieldset></form>"; //$NON-NLS-1$
    return res;
  }

  public static String vueNonObligatoire (final Question q) {
    if (!q.isObligatoire ()) { return "<input name=\"passer\" value=\"Passer &gt;&gt;\" type=\"submit\" />"; //$NON-NLS-1$
    }
    return new String ();
  }

  public static String vueOrdreQuestion (final Sondage s) {
    String res = new String ();
    int i = 0;
    res += "\njsOrdre = new Array ();\n"; //$NON-NLS-1$
    final ArrayList<QuestionSondage> listeQuestions = new ArrayList<QuestionSondage> ();
    listeQuestions.addAll (s.getQuestionSondage ());
    Collections.sort (listeQuestions);
    for (final QuestionSondage qs : listeQuestions) {
      res += "jsOrdre [" + i++ + "] = " + qs.getQuestion ().getId () + ";\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    return res;
  }

  private static String vueUn (final Question q, final List<Commandes> lC) {
    String slC = new String ();
    for (final Commandes c : lC) {
      slC += "<a href=\"javascript:commande ('" + c.getCommande () + "', " + q.getId () //$NON-NLS-1$ //$NON-NLS-2$
          + ")\" title=\"" + c.getDescription () + //$NON-NLS-1$  
          "\"><img src=\"../../../vue/_layout/imgs/Commande" + c.getCommande () //$NON-NLS-1$
          + ".png\" alt=\"" + c.getDescription () + //$NON-NLS-1$
          "\" /></a>"; //$NON-NLS-1$
    }

    return "<div class=\"liQuestions\"><div id=\"idCache\" class=\"cache\">" + q.getId () + "</div>" //$NON-NLS-1$ //$NON-NLS-2$
        + "<span class=\"colonneActionsQuestion\">" //$NON-NLS-1$
        + "<img src=\"../../../vue/_layout/imgs/Question" + q.getType ().getType () //$NON-NLS-1$
        + ".png\" alt=\"" + q.getType ().getNom () //$NON-NLS-1$
        + "\" />&nbsp;&nbsp;&nbsp;</span><span class=\"colonneNomQuestion\"" //$NON-NLS-1$
        + " id=\"questionTitre" + q.getId () + "\">" + q.getTitre () //$NON-NLS-1$ //$NON-NLS-2$  
        + "</span></span><span class=\"colonneActionsQuestion\">" + slC + "</span>" //$NON-NLS-1$  //$NON-NLS-2$
        + "</div>"; //$NON-NLS-1$
  }

  public static String vueUn2 (final Question q, final List<Commandes> lC) {
    String res = VueQuestions.vueUn (q, lC);
    res = res.substring (25, res.length () - 7);
    return res;
  }

  public static String vueUnChoix (final Question q, final List<Choix> c,
      final Locale l) {
    String res = new String ();

    final Messages m = new Messages (VueQuestions.class.getName (), l);

    try {

      final VueQuestion vq = (VueQuestion) Class.forName (
          "fr.unice.ent.eve.vue.VueQuestion" //$NON-NLS-1$
              + q.getType ().getType ()).newInstance ();
      res += vq.vueEditionUn (c);
    } catch (final ClassNotFoundException e) {
      res += "<p><br/>" + m.getString ("erreur") //$NON-NLS-1$ //$NON-NLS-2$
          + "<br/></p>"; //$NON-NLS-1$
    } catch (final InstantiationException e) {
      res += "<p><br/>" + m.getString ("erreur") //$NON-NLS-1$ //$NON-NLS-2$
          + "<br/></p>"; //$NON-NLS-1$
    } catch (final IllegalAccessException e) {
      res += "<p><br/>" + m.getString ("erreur") //$NON-NLS-1$ //$NON-NLS-2$
          + "<br/></p>"; //$NON-NLS-1$
    }
    return res;
  }

  public VueQuestions () {
    super ();
  }
}
