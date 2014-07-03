package fr.unice.ent.eve.controle.actionresolver;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.controller.NameBasedActionResolver;
import net.sourceforge.stripes.exception.StripesServletException;
import fr.unice.ent.eve.controle.controleurs.Entree;

/**
 * Actionresolver propre a l'application Eve. <br/>
 * Celui ci permet aux classes dont le nom du package commence par
 * "fr.unice.ent.eve.controle.controleurs" d'etre reconnues comme des
 * ActionBean, c'est a dire des classes "front end" pouvant etre appellees a
 * partir du navigateur
 * 
 * Eve repose sur Stripes, le filtre propose ces fonctions dans ses classes
 * d'actionResolver. Elles sont surchargees afin d'avoir un meilleur controle de
 * l'application.
 * 
 * @author benychou
 * @see net.sourceforge.stripes.controller.NameBasedActionResolver
 */
public class EveActionResolver extends NameBasedActionResolver {

  /**
   * Ce constructeur appelle la superClasse NamedBasedActionResolver
   * 
   * @see net.sourceforge.stripes.controller.NameBasedActionResolver
   */
  public EveActionResolver () {
    super ();
  }

  /**
   * Retourne l'actionBean a appeller en fonction du chemin dans la barre
   * d'adresse et du contexte.
   * 
   * Dans Eve, si un actionBean inexistant est appelle, l'utilisateur est
   * redirige sur la page d'accueil.
   * 
   * @return l'actionBean
   * @see net.sourceforge.stripes.controller.NameBasedActionResolver
   */
  @Override
  public ActionBean getActionBean (final ActionBeanContext context,
      final String path) {
    try {
      return super.getActionBean (context, path);
    } catch (final StripesServletException e) {
      final Entree entree = new Entree ();
      entree.setContext (context);
      return entree;
    } catch (final StringIndexOutOfBoundsException e) {
      final Entree entree = new Entree ();
      entree.setContext (context);
      return entree;
    }
  }

  /**
   * Obtient la liste des noms des noeuds des packages dans lesquels les
   * actionBeans sont recherches.
   * 
   * Dans Eve, renvoit ["eve", "controle"].
   * 
   * @return liste des noms de noeuds des packages
   * @see net.sourceforge.stripes.controller.NameBasedActionResolver
   */
  @Override
  public Set<String> getBasePackages () {
    final Set<String> resultat = new HashSet<String> ();
    resultat.add ("eve"); //$NON-NLS-1$
    resultat.add ("controle"); //$NON-NLS-1$

    return resultat;
  }

  /**
   * Obtient le suffixe supprime de la barre d'url lors de la recherche de
   * l'actionBean Dans Eve, c'est "".
   * 
   * @return le suffixe
   * @see net.sourceforge.stripes.controller.NameBasedActionResolver
   */
  @Override
  public String getBindingSuffix () {
    return ""; //$NON-NLS-1$
  }
}
