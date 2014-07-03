package fr.unice.ent.eve.controle.controleurs.admin.utilisateurs;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;

public class GererUtilisateurs extends ActionBeanTraitant {

  private String fait;
  private String uid;

  public GererUtilisateurs () {
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
    if (this.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionUtilisateurs_fait") != null) //$NON-NLS-1$
    {
      this.fait = "block"; //$NON-NLS-1$
    } else {
      this.fait = "none"; //$NON-NLS-1$
    }

    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution (
        "/vue/jsp/admin/utilisateurs/gererutilisateurs.jsp"); //$NON-NLS-1$
  }

  public Resolution ajoutUn () {
    final Redirection red = new Redirection ();
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionUtilisateurs_uid", this.uid); //$NON-NLS-1$
    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public String getFait () {
    return this.fait;
  }

  public String getUid () {
    return this.uid;
  }

  public void setFait (final String f) {
    this.fait = f;
  }

  public void setUid (final String u) {
    this.uid = u;
  }
}
