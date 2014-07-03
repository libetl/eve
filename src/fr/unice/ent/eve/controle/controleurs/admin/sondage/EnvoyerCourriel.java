package fr.unice.ent.eve.controle.controleurs.admin.sondage;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;

public class EnvoyerCourriel extends ActionBeanTraitant {

  private String listeUid;
  private String nomGroupe;
  private String nomSondage;
  private String texteM;
  private String uid;

  public EnvoyerCourriel () {
    super ();
  }

  @DefaultHandler
  public Resolution afficherFormulaire () {
    if (this.getContext ().getRequest ().getSession ()
        .getAttribute ("resultatDerniereCommande") == null) //$NON-NLS-1$
    {
      final Redirection red = new Redirection ();
      red.setContext (this.getContext ());
      red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$

      return red.faire ();
    }

    this.nomGroupe = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[0]; //$NON-NLS-1$
    this.nomSondage = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[1]; //$NON-NLS-1$
    this.listeUid = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[2]; //$NON-NLS-1$
    this.texteM = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[3]; //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution ("/vue/jsp/admin/sondage/envoicourriel.jsp"); //$NON-NLS-1$
  }

  public Resolution envoi () {
    final Redirection red = new Redirection ();
    red.setContext (this.getContext ());
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionCourriels_uid", this.uid); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionCourriels_texteM", this.texteM); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    return red.faire ();
  }

  public String getListeUid () {
    return this.listeUid;
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public String getNomSondage () {
    return this.nomSondage;
  }

  public String getTexteM () {
    return this.texteM;
  }

  public String getUid () {
    return this.uid;
  }

  public void setListeUid (final String lU) {
    this.listeUid = lU;
  }

  public void setNomGroupe (final String nomG) {
    this.nomGroupe = nomG;
  }

  public void setNomSondage (final String nomS) {
    this.nomSondage = nomS;
  }

  public void setTexteM (final String tM) {
    this.texteM = tM;
  }

  public void setUid (final String u) {
    this.uid = u;
  }

}
