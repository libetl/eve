package fr.unice.ent.eve.vue;

import java.util.List;

import fr.unice.ent.eve.modele.questions.Choix;
import fr.unice.ent.eve.modele.questions.Question;

/**
 * Interface implementee par chaque type de question. Contient les differentes
 * vues de la question (mode edition, mode vote, mode resultats)
 * 
 * @author benychou
 * 
 */
public interface VueQuestion {
  /**
   * Vue en mode vote
   * 
   * @param q
   *          la question a afficher
   * @return la chaine contenant le formattage
   */
  String vue (Question q);

  /**
   * Vue en mode edition
   * 
   * @param q
   *          la question a afficher
   * @return la chaine contenant le formattage
   */
  String vueEdition (Question q);

  /**
   * Vue en mode edition d'un choix
   * 
   * @param c
   *          la liste des choix a afficher
   * @return la chaine contenant le formattage
   */
  String vueEditionUn (final List<Choix> c);

  /**
   * Vue en mode resultats
   * 
   * @param q
   *          la question a afficher
   * @return la chaine contenant le formattage
   */
  String vueResultats (final Question q);
}
