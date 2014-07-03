package fr.unice.ent.eve.vue;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Classe de renvoi a l'utilisateur d'un probleme
 * 
 * @author benychou
 * 
 */
public class VueErreur {

  private static String erreurMessage;

  /**
   * Methode statique d'obtention du dernier message d'erreur
   * 
   * @return le message d'erreur
   */
  public static String getErreurMessage () {
    return VueErreur.erreurMessage;
  }

  private final ActionBeanContext abc;

  private final LocalizableError  erreur;

  /**
   * Constructeur initialisant les champs
   * 
   * @param c
   *          le contexte de la requete
   * @param err
   *          l'erreur utilisateur rencontree lors de l'execution de la requete
   */
  public VueErreur (final ActionBeanContext c, final LocalizableError err) {
    this.abc = c;
    this.erreur = err;
    VueErreur.erreurMessage = err.getMessage (c.getLocale ());
  }

  /**
   * Renvoit la reponse (le probleme) a l'utilisateur
   * 
   * @return un objet de type ForwardResolution
   * @see ForwardResolution
   */
  public ForwardResolution voir () {
    this.abc.getMessages ().add (this.erreur);
    if (this.abc.getRequest ().getRequestURL ().toString ().contains ("admin/")) //$NON-NLS-1$
    { return new ForwardResolution ("/vue/jsp/admin/erreur/erreur.jsp"); //$NON-NLS-1$
    }
    return new ForwardResolution ("/vue/jsp/utilisateur/erreur/erreur.jsp"); //$NON-NLS-1$
  }

}
