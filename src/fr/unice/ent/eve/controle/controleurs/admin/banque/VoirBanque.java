package fr.unice.ent.eve.controle.controleurs.admin.banque;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;

public class VoirBanque extends ActionBeanTraitant {
  private long   idSondage;
  private String nomGroupe;
  private String vueSondages;

  public VoirBanque () {
    super ();
  }

  @DefaultHandler
  public Resolution afficherSondages () {
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
    this.vueSondages = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[1]; //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution ("/vue/jsp/admin/banque/voirbanque.jsp"); //$NON-NLS-1$
  }

  public long getIdSondage () {
    return this.idSondage;
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public String getVueSondages () {
    return this.vueSondages;
  }

  public void setIdSondage (final long idS) {
    this.idSondage = idS;
  }

  public void setNomGroupe (final String nomG) {
    this.nomGroupe = nomG;
  }

  public void setVueSondages (final String vueS) {
    this.vueSondages = vueS;
  }
}
