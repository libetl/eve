package fr.unice.ent.eve.controle.controleurs;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;
import fr.unice.ent.eve.modele.Modele;

/**
 * ActionBean d'entree dans Eve.
 * 
 * <br/>
 * <br/>
 * Tous les utilisateurs arrivant sur l'application passent par cet ActionBean.
 * 
 * <br/>
 * <br/>
 * Si l'utilisateur a le droit d'y aller, il est redirige vers l'affichage des
 * groupes en tant qu'administrateur, sinon il est redirige vers la page des
 * groupes classique.
 * 
 * <br/>
 * <br/>
 * L'utilisateur est redirige vers la page d'authentification Cas s'il n'est pas
 * authentifie.
 * 
 * <br/>
 * <br/>
 * Enfin, la base de donnees est initialisee si le test du Modele echoue
 * 
 * @author benychou
 * @see Modele
 */
public class Entree extends ActionBeanTraitant {

  public Entree () {
    super ();
  }

  /**
   * Realise la redirection
   */
  @DefaultHandler
  public Resolution entrer () {
    if (!Modele.testModele ()) { return new RedirectResolution (
        "/controleurs/admin/config/Reinit", true); //$NON-NLS-1$
    }

    final Redirection r = new Redirection (this.getContext ());

    r.ajoutParamAdd ("comm", "AfficherGroupesAdmin"); //$NON-NLS-1$ //$NON-NLS-2$
    final String url = r.rediriger ();

    if (!url.equals ("") && url.startsWith ("http") //$NON-NLS-1$ //$NON-NLS-2$
        && (url.indexOf ("://") != -1)) //$NON-NLS-1$
    {
      return new RedirectResolution (url, false);
    } else if (!url.equals ("")) //$NON-NLS-1$
    { return new RedirectResolution (url, true); }

    r.ajoutParamAdd ("comm", "AfficherGroupes"); //$NON-NLS-1$ //$NON-NLS-2$
    return r.faire ();
  }
}
