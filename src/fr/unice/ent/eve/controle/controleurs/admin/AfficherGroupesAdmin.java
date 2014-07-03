package fr.unice.ent.eve.controle.controleurs.admin;

import java.util.Collections;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;
import fr.unice.ent.eve.modele.groupes.GroupeContrainte;

public class AfficherGroupesAdmin extends ActionBeanTraitant {
  private int                    contrainte;
  private String                 groupes;
  private long                   idGroupe;
  private List<GroupeContrainte> listeGrCo;
  private String                 nomCommande;
  private String                 nomGroupe;

  public AfficherGroupesAdmin () {
    super ();
  }

  @SuppressWarnings ("unchecked")
  @DefaultHandler
  public Resolution affichageGroupes () {
    if (this.getContext ().getRequest ().getSession ()
        .getAttribute ("resultatDerniereCommande") == null) //$NON-NLS-1$
    {
      final Redirection red = new Redirection ();
      red.setContext (this.getContext ());
      red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$

      return red.faire ();
    }

    this.listeGrCo = (List<GroupeContrainte>) ((Object[]) this.getContext ()
        .getRequest ().getSession ().getAttribute ("resultatDerniereCommande"))[0]; //$NON-NLS-1$

    this.groupes = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[1]; //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution ("/vue/jsp/admin/groupes.jsp"); //$NON-NLS-1$
  }

  public int getContrainte () {
    return this.contrainte;
  }

  public String getGroupes () {
    return this.groupes;
  }

  public long getId () {
    return this.idGroupe;
  }

  public List<GroupeContrainte> getListeGrCo () {
    return Collections.unmodifiableList (this.listeGrCo);
  }

  public String getNomCommande () {
    return this.nomCommande;
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public Resolution ordre () {
    final Redirection red = new Redirection ();
    red.setContext (this.getContext ());
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionGroupe_idGroupe", new Long (this.idGroupe));//$NON-NLS-1$ 
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionGroupe_nomGroupe", this.nomGroupe);//$NON-NLS-1$ 
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute (
            "GestionGroupe_contrainte", new Integer (this.contrainte));//$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("g", "" + this.idGroupe);//$NON-NLS-1$ //$NON-NLS-2$  

    red.ajoutParamAdd ("comm", this.nomCommande); //$NON-NLS-1$

    return red.faire ();
  }

  public void setContrainte (final int c) {
    this.contrainte = c;
  }

  public void setGroupes (final String g) {
    this.groupes = g;
  }

  public void setId (final long idG) {
    this.idGroupe = idG;
  }

  public void setListeGrCo (final List<GroupeContrainte> lgc) {
    this.listeGrCo = lgc;
  }

  public void setNomCommande (final String nC) {
    this.nomCommande = nC;
  }

  public void setNomGroupe (final String nG) {
    this.nomGroupe = nG;
  }
}
