package fr.unice.ent.eve.controle.controleurs.admin.groupe;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;
import fr.unice.ent.eve.modele.services.ServiceAuth;

public class OperationsPersonnes extends ActionBeanTraitant {

  private String            adresse;
  private String            fait;
  private String            formPermissions;
  private List<ServiceAuth> listeServices;
  private String            lnCmd;
  private String            nomGroupe;
  private String            uid;

  public OperationsPersonnes () {
    super ();
  }

  public Resolution ajout () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionPersonnes_adresse", this.adresse); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionPersonnes_lnCmd", this.lnCmd); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public Resolution ajoutUn () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionPersonnes_uid", this.uid); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  @SuppressWarnings ("unchecked")
  @DefaultHandler
  public Resolution formulaire () {
    if (this.getContext ().getRequest ().getSession ()
        .getAttribute ("resultatDerniereCommande") == null) //$NON-NLS-1$
    {
      final Redirection red = new Redirection ();
      red.setContext (this.getContext ());
      red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$

      return red.faire ();
    }

    if (this.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionPersonnes_fait") != null) //$NON-NLS-1$
    {
      this.fait = "block"; //$NON-NLS-1$
      this.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionPersonnes_fait"); //$NON-NLS-1$
    } else {
      this.fait = "none"; //$NON-NLS-1$
    }

    this.nomGroupe = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[0]; //$NON-NLS-1$

    this.listeServices = (List<ServiceAuth>) ((Object[]) this.getContext ()
        .getRequest ().getSession ().getAttribute ("resultatDerniereCommande"))[1]; //$NON-NLS-1$

    this.formPermissions = (String) ((Object[]) this.getContext ()
        .getRequest ().getSession ().getAttribute ("resultatDerniereCommande"))[2]; //$NON-NLS-1$

    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution (
        "/vue/jsp/admin/groupe/operationspersonnes.jsp"); //$NON-NLS-1$
  }

  public String getAdresse () {
    return this.adresse;
  }

  public String getFait () {
    return this.fait;
  }

  public String getFormPermissions () {
    return this.formPermissions;
  }

  public List<ServiceAuth> getListeServices () {
    return this.listeServices;
  }

  public String getLnCmd () {
    return this.lnCmd;
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public String getUid () {
    return this.uid;
  }

  public void setAdresse (final String a) {
    this.adresse = a;
  }

  public void setFait (final String f) {
    this.fait = f;
  }

  public void setFormPermissions (final String formP) {
    this.formPermissions = formP;
  }

  public void setListeServices (final List<ServiceAuth> listeS) {
    this.listeServices = listeS;
  }

  public void setLnCmd (final String lnC) {
    this.lnCmd = lnC;
  }

  public void setNomGroupe (final String nomG) {
    this.nomGroupe = nomG;
  }

  public void setUid (final String u) {
    this.uid = u;
  }
}
