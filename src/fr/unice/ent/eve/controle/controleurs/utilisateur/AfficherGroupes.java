package fr.unice.ent.eve.controle.controleurs.utilisateur;

import java.util.Collections;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;
import fr.unice.ent.eve.modele.groupes.GroupeContrainte;

public class AfficherGroupes extends ActionBeanTraitant {
  private int                    contrainte;
  private String                 groupes;
  private int                    idGroupe;
  private List<GroupeContrainte> listeGrCo;
  private String                 nomGroupe;

  public AfficherGroupes () {
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

    return new ForwardResolution ("/vue/jsp/utilisateur/groupes.jsp"); //$NON-NLS-1$
  }

  public int getContrainte () {
    return this.contrainte;
  }

  public String getGroupes () {
    return this.groupes;
  }

  public int getId () {
    return this.idGroupe;
  }

  public List<GroupeContrainte> getListeGrCo () {
    return Collections.unmodifiableList (this.listeGrCo);
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public void setContrainte (final int c) {
    this.contrainte = c;
  }

  public void setGroupes (final String g) {
    this.groupes = g;
  }

  public void setId (final int idG) {
    this.idGroupe = idG;
  }

  public void setListeGrCo (final List<GroupeContrainte> lgc) {
    this.listeGrCo = lgc;
  }

  public void setNomGroupe (final String nG) {
    this.nomGroupe = nG;
  }
}
