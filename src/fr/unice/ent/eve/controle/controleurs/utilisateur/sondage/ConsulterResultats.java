package fr.unice.ent.eve.controle.controleurs.utilisateur.sondage;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;

public class ConsulterResultats extends ActionBeanTraitant {
  private String nomGroupe;
  private String nomSondage;
  private String resultats;

  public ConsulterResultats () {

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

    this.resultats = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[2]; //$NON-NLS-1$

    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution ("/vue/jsp/utilisateur/sondage/resultats.jsp"); //$NON-NLS-1$
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public String getNomSondage () {
    return this.nomSondage;
  }

  public String getResultats () {
    return this.resultats;
  }
}
