package fr.unice.ent.eve.controle.controleurs;

import java.util.ArrayList;

import fr.unice.ent.eve.controle.erreurs.ServiceWebException;
import fr.unice.ent.eve.controle.ws.AppelWS;
import fr.unice.ent.eve.controle.ws.DonneesWS;
import fr.unice.ent.eve.controle.ws.EntreeWS;
import fr.unice.ent.eve.modele.groupes.Personne;
import fr.unice.ent.eve.modele.services.ServiceAuth;

/**
 * Surcharge de ActionBeanCassifie. Fournit une fonction protegee pour
 * interroger le service de groupes
 * 
 * <br/>
 * <br/>
 * Les donnees sont enregistrees (via l'appel d'un service web) dans la session,
 * si elles n'y sont pas deja.<br/>
 * 
 * @author benychou
 * 
 */
public class ActionBeanInforme extends ActionBeanCassifie {

  private DonneesWS donneesGroupes;
  private EntreeWS  donneesPerso;

  /**
   * Appelle le constructeur ActionBeanCassifie
   */
  public ActionBeanInforme () {
    super ();
  }

  /**
   * Obtient les groupes d'un utilisateur.
   * 
   * @return un objet de type DonneesWS
   * @see fr.unice.ent.eve.controle.ws.DonneesWS
   */
  public DonneesWS getDonneesGroupes () {
    return this.donneesGroupes;
  }

  /**
   * Obtient les informations sur un utilisateur.
   * 
   * @return un objet de type EntreeWS
   * @see fr.unice.ent.eve.controle.ws.EntreeWS
   */
  public EntreeWS getDonneesPerso () {
    return this.donneesPerso;
  }

  @SuppressWarnings ("unchecked")
  protected void obtenirProfil (final Personne p, final ServiceAuth sa)
      throws ServiceWebException {
    if ((this.getContext ().getRequest ().getSession ()
        .getAttribute ("donneesGroupes") != null //$NON-NLS-1$
    )
        && (this.getContext ().getRequest ().getSession ()
            .getAttribute ("donneesPerso") != null)) //$NON-NLS-1$
    {
      this.donneesGroupes = (DonneesWS) this.getContext ().getRequest ()
          .getSession ().getAttribute ("donneesGroupes"); //$NON-NLS-1$
      this.donneesPerso = (EntreeWS) this.getContext ().getRequest ()
          .getSession ().getAttribute ("donneesPerso"); //$NON-NLS-1$
      return;
    }

    if (sa.getAdresse ().equals (new String ())) { return; }

    this.donneesGroupes = AppelWS.appel (this.getContext ().getLocale (), sa,
        "getUserGroups " + p.getValeurUid ()); //$NON-NLS-1$
    this.donneesPerso = AppelWS.appel (this.getContext ().getLocale (), sa,
        "getUser " + p.getValeurUid ()).get (1); //$NON-NLS-1$

    this.getContext ().getRequest ().getSession ()
        .setAttribute ("donneesGroupes", this.donneesGroupes); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("donneesPerso", this.donneesPerso); //$NON-NLS-1$
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute ("nomAffichage", //$NON-NLS-1$
            ((ArrayList<String>) this.donneesPerso.get ("displayName")).get (0)); //$NON-NLS-1$
  }

  protected void setDonneesGroupes (final DonneesWS dG) {
    this.donneesGroupes = dG;
  }

  protected void setDonneesPerso (final EntreeWS dP) {
    this.donneesPerso = dP;
  }
}
