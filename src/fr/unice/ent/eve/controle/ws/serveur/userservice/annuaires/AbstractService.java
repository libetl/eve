package fr.unice.ent.eve.controle.ws.serveur.userservice.annuaires;

import fr.unice.ent.eve.controle.bundlemessages.Messages;

/**
 * Interface des sous services d'interrogation d'annuaire.
 * 
 * @author benychou
 * 
 */
public interface AbstractService {
  /**
   * Methode d'interrogation de la source de donnees
   * 
   * @param mess
   *          Ressource associee a un fichier contenant les proprietes
   *          eventuelles
   * @param query
   *          contenu de la requete
   * @return une structure serialisable correspondant au resultat de la requete
   */
  Object[] concreteQuery (final Messages mess, final String query);
}
