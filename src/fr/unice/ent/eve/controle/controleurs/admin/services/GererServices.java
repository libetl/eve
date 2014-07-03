package fr.unice.ent.eve.controle.controleurs.admin.services;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;

public class GererServices extends ActionBeanTraitant {

  private String casLogin;
  private String casValidate;
  private String classeDefautGroupes;
  private String serviceAuth;
  private String servicesGroupesAdresse;
  private String smtpServeur;

  public GererServices () {
    super ();
  }

  @DefaultHandler
  public Resolution afficherPage () {
    if (this.getContext ().getRequest ().getSession ()
        .getAttribute ("resultatDerniereCommande") == null) //$NON-NLS-1$
    {
      final Redirection red = new Redirection ();
      red.setContext (this.getContext ());
      red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$

      return red.faire ();
    }

    this.classeDefautGroupes = (String) ((Object[]) this.getContext ()
        .getRequest ().getSession ().getAttribute ("resultatDerniereCommande"))[0]; //$NON-NLS-1$

    this.servicesGroupesAdresse = (String) ((Object[]) this.getContext ()
        .getRequest ().getSession ().getAttribute ("resultatDerniereCommande"))[1]; //$NON-NLS-1$

    this.casLogin = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[2]; //$NON-NLS-1$

    this.casValidate = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[3]; //$NON-NLS-1$

    this.serviceAuth = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[4]; //$NON-NLS-1$

    this.smtpServeur = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[5]; //$NON-NLS-1$

    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution ("/vue/jsp/admin/services/gererservices.jsp"); //$NON-NLS-1$
  }

  public Resolution ajouterService () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionService_nAdresse", //$NON-NLS-1$
            this.getContext ().getRequest ().getParameter ("nAdresse")); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionService_nClasse", //$NON-NLS-1$
            this.getContext ().getRequest ().getParameter ("nClasse")); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public Resolution changerCAS () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionService_nCasLogin", //$NON-NLS-1$
            this.getContext ().getRequest ().getParameter ("nCasLogin")); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionService_nCasValidate", //$NON-NLS-1$
            this.getContext ().getRequest ().getParameter ("nCasValidate")); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public Resolution changerServiceGroupes () {
    final Redirection red = new Redirection ();
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute (
            "GestionService_nServiceGroupesAdresse", //$NON-NLS-1$
            this.getContext ().getRequest ()
                .getParameter ("nServiceGroupesAdresse")); //$NON-NLS-1$
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute (
            "GestionService_nServiceGroupesClasse", //$NON-NLS-1$
            this.getContext ().getRequest ()
                .getParameter ("nServiceGroupesClasse")); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public Resolution changerSMTP () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionService_smtpServeur", //$NON-NLS-1$
            this.getContext ().getRequest ().getParameter ("smtpServeur")); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public String getCasLogin () {
    return this.casLogin;
  }

  public String getCasValidate () {
    return this.casValidate;
  }

  public String getClasseDefautGroupes () {
    return this.classeDefautGroupes;
  }

  public String getServiceAuth () {
    return this.serviceAuth;
  }

  public String getServicesGroupesAdresse () {
    return this.servicesGroupesAdresse;
  }

  public String getSmtpServeur () {
    return this.smtpServeur;
  }

  public void setCasLogin (final String cL) {
    this.casLogin = cL;
  }

  public void setCasValidate (final String cV) {
    this.casValidate = cV;
  }

  public void setClasseDefautGroupes (final String cDG) {
    this.classeDefautGroupes = cDG;
  }

  public void setServiceAuth (final String sA) {
    this.serviceAuth = sA;
  }

  public void setServicesGroupesAdresse (final String sGA) {
    this.servicesGroupesAdresse = sGA;
  }

  public void setSmtpServeur (final String smtpS) {
    this.smtpServeur = smtpS;
  }

  public Resolution validerEditerSupprimer () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionService_adresse", //$NON-NLS-1$
            this.getContext ().getRequest ().getParameter ("adresse")); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionService_nAdresse", //$NON-NLS-1$
            this.getContext ().getRequest ().getParameter ("nAdresse")); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionService_nClasse", //$NON-NLS-1$
            this.getContext ().getRequest ().getParameter ("nClasse")); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }
}
