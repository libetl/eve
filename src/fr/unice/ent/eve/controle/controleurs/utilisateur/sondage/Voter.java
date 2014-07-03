package fr.unice.ent.eve.controle.controleurs.utilisateur.sondage;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;

public class Voter extends ActionBeanTraitant {

  private String choix;
  private String libelle;
  private String nomGroupe;
  private String nomSondage;
  private String passer;

  public Voter () {
  }

  @DefaultHandler
  public Resolution afficher () {

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

    this.libelle = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[2]; //$NON-NLS-1$

    this.choix = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[3]; //$NON-NLS-1$

    this.passer = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[4]; //$NON-NLS-1$

    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution ("/vue/jsp/utilisateur/sondage/voter.jsp"); //$NON-NLS-1$
  }

  public String getChoix () {
    return this.choix;
  }

  public String getLibelle () {
    return this.libelle;
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public String getNomSondage () {
    return this.nomSondage;
  }

  public String getPasser () {
    return this.passer;
  }

  public Resolution passer () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionVote_passer", new Boolean (true)); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public void setNomGroupe (final String nomG) {
    this.nomGroupe = nomG;
  }

  public void setNomSondage (final String nomS) {
    this.nomSondage = nomS;
  }

  public Resolution suivant () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionVote_valider", new Boolean (true)); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }
}
