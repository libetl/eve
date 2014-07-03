package fr.unice.ent.eve.controle.controleurs.admin.sondage;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;

public class ModifierQuestions extends ActionBeanTraitant {
  private String ajouterQuestion;
  private long   idQuestion;
  private String jsOrdre;
  private String nomGroupe;
  private String nomSondage;
  private String questions;
  private String typeQuestion;

  public ModifierQuestions () {
    super ();
  }

  @DefaultHandler
  public Resolution afficherQuestions () {
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

    this.ajouterQuestion = (String) ((Object[]) this.getContext ()
        .getRequest ().getSession ().getAttribute ("resultatDerniereCommande"))[2]; //$NON-NLS-1$

    this.questions = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[3]; //$NON-NLS-1$

    this.jsOrdre = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[4]; //$NON-NLS-1$

    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution (
        "/vue/jsp/admin/sondage/modifierquestions.jsp"); //$NON-NLS-1$
  }

  public Resolution ajouterChoix () {
    final Redirection red = new Redirection ();
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute ("GestionQuestion_idQuestion", new Long (this.idQuestion)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionQuestion_ajoutChoix", new Boolean (true)); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public Resolution ajoutQuestion () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionQuestion_type", this.typeQuestion); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public Resolution descendreQuestion () {
    final Redirection red = new Redirection ();
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute ("GestionQuestion_idQuestion", new Long (this.idQuestion)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionQuestion_direction", "+"); //$NON-NLS-1$ //$NON-NLS-2$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public String getAjouterQuestion () {
    return this.ajouterQuestion;
  }

  public String getJsOrdre () {
    return this.jsOrdre;
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public String getNomSondage () {
    return this.nomSondage;
  }

  public String getQuestions () {
    return this.questions;
  }

  public Resolution remonterQuestion () {
    final Redirection red = new Redirection ();
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute ("GestionQuestion_idQuestion", new Long (this.idQuestion)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionQuestion_direction", "-"); //$NON-NLS-1$ //$NON-NLS-2$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public void setIdQuestion (final long idQ) {
    this.idQuestion = idQ;
  }

  public void setTypeQuestion (final String tQ) {
    this.typeQuestion = tQ;
  }

  public Resolution supprimerQuestion () {
    final Redirection red = new Redirection ();
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute ("GestionQuestion_idQuestion", new Long (this.idQuestion)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionQuestion_supprimer", new Boolean (true)); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public Resolution validerFormulaire () {
    final Redirection red = new Redirection ();
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute ("GestionQuestion_idQuestion", new Long (this.idQuestion)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionQuestion_valider", new Boolean (true)); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public Resolution voirFormulaire () {
    final Redirection red = new Redirection ();
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute ("GestionQuestion_idQuestion", new Long (this.idQuestion)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionQuestion_voir", new Boolean (true)); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }
}
