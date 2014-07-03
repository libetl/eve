package fr.unice.ent.eve.controle.controleurs.admin.groupe;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;

public class VoirGroupeAdmin extends ActionBeanTraitant {
  private String nomGroupe = ""; //$NON-NLS-1$
  private String vueActions;
  private String vueSondages;

  public VoirGroupeAdmin () {
    super ();
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

    this.vueActions = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[1]; //$NON-NLS-1$

    this.vueSondages = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[2]; //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution ("/vue/jsp/admin/groupe/voirgroupe.jsp"); //$NON-NLS-1$
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public String getVueActions () {
    return this.vueActions;
  }

  public String getVueSondages () {
    return this.vueSondages;
  }

  public void setNomGroupe (final String nG) {
    this.nomGroupe = nG;
  }
}
