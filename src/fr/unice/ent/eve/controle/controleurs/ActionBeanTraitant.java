package fr.unice.ent.eve.controle.controleurs;

import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.CASErreur;
import fr.unice.ent.eve.controle.erreurs.CASException;
import fr.unice.ent.eve.controle.erreurs.ServiceWebErreur;
import fr.unice.ent.eve.controle.erreurs.ServiceWebException;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.groupes.Personne;
import fr.unice.ent.eve.modele.services.ServiceAuth;

/**
 * Classe de simplification de l'appel a cas et au service web. <br/>
 * Tous les ActionBean de Eve surchargent cette classe.
 * 
 * @author benychou
 * 
 */
public class ActionBeanTraitant extends ActionBeanInforme {
  private Personne p;

  /**
   * Appelle le constructeur ActionBeanInforme
   */
  public ActionBeanTraitant () {
    super ();
  }

  /**
   * Renvoie l'instance de Personne correspondant a l'utilisateur en cours
   * 
   * @return un objet de type Personne
   * @see fr.unice.ent.eve.modele.groupes.Personne
   */
  public Personne getP () {
    return this.p;
  }

  /**
   * Affecte une nouvelle instance de Personne correspondant a l'utilisateur en
   * cours
   * 
   * @return un objet de type Personne
   * @see fr.unice.ent.eve.modele.groupes.Personne
   */
  public void setP (final Personne pe) {
    this.p = pe;
  }

  /**
   * Il est commode d'appeller cette methode pour chaque ActionBean sur Eve. <br/>
   * Elle se charge de l'appel a cas et au service Web.
   * 
   * @param gt
   *          GerantTransactions, la classe de gestion d'une session en base de
   *          donnees (requete dans la table EveConfig)
   * @return un objet de type Resolution, en cas d'erreur, sinon null.
   */
  public Resolution traiter (final GerantTransactions gt) {
    try {
      if (super.casAppel (gt).equals (""))//$NON-NLS-1$
      { return this.casRedirect (); }
    } catch (final CASException e) {
      return new EveErreur (this.getContext ()).lancerErreur (new CASErreur (e
          .getMessage ()));
    }

    this.p = this.getPersonne (gt);
    this.p.setGT (gt);

    try {
      this.obtenirProfil (this.p, ServiceAuth.getGroupsService (gt));
    } catch (final ServiceWebException e) {
      return new EveErreur (this.getContext ())
          .lancerErreur (new ServiceWebErreur (e.getMessage ()));
    }

    return null;
  }
}
