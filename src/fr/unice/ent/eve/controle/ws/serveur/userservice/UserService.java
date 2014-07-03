package fr.unice.ent.eve.controle.ws.serveur.userservice;

import java.util.Locale;

import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.controle.ws.serveur.userservice.annuaires.AbstractService;

/**
 * Service Web d'interrogation des utilisateurs
 * 
 * <br/>
 * Ce service comprend des fonctions concretes d'interrogation d'annuaires
 * regroupes autour d'une interface (AbstractService) <br/>
 * <br/>
 * <u>Protocole :</u> <br/>
 * Le service renvoie un objet de type Object [] []. Chaque entree du tableau
 * est un tableau qui a deux valeurs : une valeur d'indice (objet [x] [0]), et
 * un String contenant l'uid (objet [x] [1]. <br/>
 * <br/>
 * <br/>
 * Cette classe a pour role d'appeller le service correspondant a la requete.
 */
public class UserService {

  /**
   * Constructeur vide
   */
  public UserService () {
    super ();
  }

  /**
   * Fonction du service web d'interrogation
   * 
   * @param annuaireType
   *          classe contenant le code metier a appeller Exemple : si
   *          annuaireType vaut Ldap, la methode appellee sera
   *          userservice.annuaires.LdapService.concretequery (query)
   * @param query
   * @return le resultat de la requete par le service appelle
   */
  public Object[] query (final String annuaireType, final String query) {

    try {
      final AbstractService as = (AbstractService) Class.forName (
          "fr.unice.ent.eve.controle.ws.serveur.userservice.annuaires." //$NON-NLS-1$
              + annuaireType + "Service").newInstance (); //$NON-NLS-1$
      final Messages mess = new Messages ("EVE", Locale.getDefault ()); //$NON-NLS-1$

      if (as == null) { throw new java.lang.ClassNotFoundException (); }

      return as.concreteQuery (mess, query);
    } catch (final ClassNotFoundException e) {
      return new String[] { e.toString () };
    } catch (final InstantiationException e) {
      return new String[] { e.toString () };
    } catch (final IllegalAccessException e) {
      return new String[] { e.toString () };
    } catch (final IllegalArgumentException e) {
      return new String[] { e.toString () };
    }
  }

}
