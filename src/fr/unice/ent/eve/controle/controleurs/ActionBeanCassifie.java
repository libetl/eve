package fr.unice.ent.eve.controle.controleurs;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

import org.xml.sax.SAXException;

import edu.yale.its.tp.cas.client.ServiceTicketValidator;
import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.controle.erreurs.CASException;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.config.EveConfig;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.groupes.Personne;

/**
 * Cette classe surcharge l'interface ActionBean de stripes, qui permet de
 * generer des classes accessibles par l'url.
 * 
 * <br/>
 * <br/>
 * Cette surcharge permet de lancer un appel a cas pour valider
 * l'authentification de l'utilisateur, et obtenir son nom.
 * 
 * @author benychou
 * 
 */
public class ActionBeanCassifie implements ActionBean {
  private ActionBeanContext context;
  private EveConfig         ec;
  private String            loginUrl    = ""; //$NON-NLS-1$
  private Messages          mCAS;
  private String            nom;
  private String            nomUt       = ""; //$NON-NLS-1$
  private String            validateUrl = ""; //$NON-NLS-1$

  /**
   * Appelle le constructeur Object ()
   */
  public ActionBeanCassifie () {
    super ();
  }

  protected String casAppel (final GerantTransactions gt) throws CASException {

    if (this.getContext ().getRequest ().getSession ().getAttribute ("nomUt") != null) //$NON-NLS-1$
    {
      this.nomUt = (String) this.getContext ().getRequest ().getSession ()
          .getAttribute ("nomUt"); //$NON-NLS-1$
      return this.nomUt;
    }

    this.ec = EveConfig.staticInstance (gt);

    this.mCAS = new Messages (
        "fr.unice.ent.eve.controle.controleurs.ActionBeanCassifie", this.getContext ().getLocale ()); //$NON-NLS-1$

    this.loginUrl = this.ec.get ("cas_login"); //$NON-NLS-1$
    this.validateUrl = this.ec.get ("cas_validate"); //$NON-NLS-1$

    if (this.loginUrl.equals (new String ())) {
      this.nomUt = "AdminEve_DefaultUid"; //$NON-NLS-1$
      this.getContext ().getRequest ().getSession ()
          .setAttribute ("nomUt", this.nomUt); //$NON-NLS-1$
      return this.nomUt;
    }

    if (this.getContext ().getRequest ().getParameter ("ticket") == null) //$NON-NLS-1$
    { return this.nomUt; }

    final ServiceTicketValidator sv = new ServiceTicketValidator ();
    sv.setCasValidateUrl (this.validateUrl);
    sv.setService (this.getContext ().getRequest ().getRequestURL ()
        .toString ());
    sv.setServiceTicket (this.getContext ().getRequest ()
        .getParameter ("ticket")); //$NON-NLS-1$

    try {
      sv.validate ();
    } catch (final IOException e) {
      throw new CASException (this.mCAS.getString ("CAS.IO")); //$NON-NLS-1$
    } catch (final SAXException e) {
      throw new CASException (this.mCAS.getString ("CAS.XML")); //$NON-NLS-1$
    } catch (final ParserConfigurationException e) {
      throw new CASException (this.mCAS.getString ("CAS.Parser")); //$NON-NLS-1$
    }
    sv.getResponse ();

    if (sv.isAuthenticationSuccesful ()) {
      this.nomUt = sv.getUser ();
    }

    this.getContext ().getRequest ().getSession ()
        .setAttribute ("nomUt", this.nomUt); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("nomAffichage", this.nom); //$NON-NLS-1$

    return this.nomUt;
  }

  protected Resolution casRedirect () {
    return new RedirectResolution (this.loginUrl
        + this.getContext ().getRequest ().getRequestURL ().toString (), false);
  }

  /**
   * Retourne le context de l'ActionBean, fonction de l'interface ActionBean de
   * stripes
   */
  @Override
  public ActionBeanContext getContext () {
    return this.context;
  }

  protected String getNom () {
    return this.nom;
  }

  protected String getNomUt () {
    if ("".equals (this.nomUt)) //$NON-NLS-1$
    {
      this.nomUt = (String) this.getContext ().getRequest ().getSession ()
          .getAttribute ("nomUt"); //$NON-NLS-1$
    }
    return this.nomUt;
  }

  protected Personne getPersonne (final GerantTransactions gt) {
    final long taille = Personne.staticInstance (gt).requete ().size ();
    Personne pe = Personne.staticInstance (gt).getParNom (this.getNomUt ());

    if (pe == null) {
      pe = new Personne (gt, this.getNomUt ());
      pe.ajout ();
      pe.rafraichir (pe);
    }

    if (taille == 0) {
      new Permission (gt, pe.getId (), 0, 0, "SuperUtilisation").ajout (); //$NON-NLS-1$
      pe.rafraichir (pe);
    }

    pe = (Personne) gt.getSession ().load (Personne.class,
        new Long (pe.getId ()));
    pe.setGT (gt);
    return pe;
  }

  /**
   * Enregistre le context de l'ActionBean, fonction de l'interface ActionBean
   * de stripes
   */
  @Override
  public void setContext (final ActionBeanContext arg0) {
    this.context = arg0;
  }
}
