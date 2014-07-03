package fr.unice.ent.eve.controle.ws;

import java.util.ArrayList;
import java.util.Locale;

import fr.unice.ent.eve.controle.erreurs.ServiceWebException;
import fr.unice.ent.eve.modele.services.ServiceAuth;

/**
 * Classe d'appel a un service web.<br/>
 * Le service web est une application externe repondant a des requetes au format
 * XML. Dans Eve, les librairies de Axis encodent la requete et interpretent la
 * reponse.<br/>
 * Il suffit alors de transformer les donnees recues dans une structure simple.
 * 
 * <br/>
 * <br/>
 * Cette classe effectue une invocation (via la classe InvocateurWS), structure
 * les informations recues (via la classe ConsommateurWS) dans un objet de type
 * DonneesWS, puis les renvoie
 * 
 * <br/>
 * <br/>
 * <u>Comment effectuer un appel au service web :</u>
 * <ul>
 * <li>Creer un service, ou faire une requete pour obtenir un objet de type
 * ServiceAuth <br/>
 * Exemple : <br/>
 * <code>ServiceAuth sa = new ServiceAuth
 * ("http://monadresse.ext/services/MonService",
 * "mon.package.ws.clientmonservice.MonService");</code> <br/>
 * ou alors<br/>
 * <code>List&lt;ServiceAuth&gt; lsa = ServiceAuth.staticInstance (gt).requete ();</code>
 * , puis prendre le service desire dans la liste.</li>
 * <li>Specifier eventuellement un locale a utiliser (
 * <code>Locale l = Locale.FRANCE;</code> par exemple)</li>
 * <li>Faire un appel par l'utilisation de la methode appel : <br/>
 * <code>DonneesWS
 * dws = AppelWS.appel (l, sa, ligneCommande);</code></li>
 * </ul>
 * 
 * @author benychou
 * 
 */
public class AppelWS {

  /**
   * Fonction d'appel au service web.
   * 
   * @param l
   *          le Locale courant de l'application, utilise pour renvoyer des
   *          erreurs uniquement dans la langue adequate
   * @param sa
   *          l'objet encapsulant les donnees sur le service web a appeller
   * @param ligneCommande
   *          la methode a appeller avec ses arguments
   * @return le resultat de l'appel au service web
   * @throws ServiceWebException
   * 
   * @see Locale
   * @see ServiceAuth
   * @see DonneesWS
   */
  public static DonneesWS appel (final Locale l, final ServiceAuth sa,
      final String ligneCommande) throws ServiceWebException {
    final InvocateurWS i = new InvocateurWS (l, sa.getClasse (),
        sa.getAdresse ());
    String[] args = new String[0];
    try {
      args = ligneCommande.split ("\\s"); //$NON-NLS-1$
    } catch (final java.lang.NullPointerException e) {
      throw new ServiceWebException (i.messageChaineIncorrecte ());
    }
    final ArrayList<String> args1 = new ArrayList<String> ();
    Object[] o = new Object[0];
    final DonneesWS result = new DonneesWS ();

    for (int c = 1 ; c < args.length ; c++) {
      args1.add (c - 1, args[c]);
    }

    o = i.invoquer (args[0], args1.toArray ());

    for (final Object element : o) {
      result.add (ConsommateurWS.objetVersMap (element));
    }

    return result;
  }

  /**
   * Fonction d'appel au service web.
   * 
   * @param sa
   *          l'objet encapsulant les donnees sur le service web a appeller
   * @param ligneCommande
   *          la methode a appeller avec ses arguments
   * @return le resultat de l'appel au service web
   * @throws ServiceWebException
   * 
   * @see ServiceAuth
   * @see DonneesWS
   */
  public static DonneesWS appel (final ServiceAuth sa,
      final String ligneCommande) throws ServiceWebException {
    return AppelWS.appel (Locale.getDefault (), sa, ligneCommande);
  }

  /**
   * Constructeur vide
   */
  public AppelWS () {
    super ();
  }
}
