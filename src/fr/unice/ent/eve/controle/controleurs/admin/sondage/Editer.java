package fr.unice.ent.eve.controle.controleurs.admin.sondage;

import java.util.Collections;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;
import fr.unice.ent.eve.modele.sondages.SondageType;

public class Editer extends ActionBeanTraitant {

  private Boolean           aleatoire;
  private Boolean           banque;
  private String            dateFermeture;
  private String            dateOuverture;
  private Boolean           dupliquable;
  private List<SondageType> lst;
  private String            nomGroupe = ""; //$NON-NLS-1$
  private Boolean           publique;
  private String            titre;
  private int               type;

  private Boolean           voteUnique;

  public Editer () {
  }

  @SuppressWarnings ("unchecked")
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

    this.aleatoire = (Boolean) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[0]; //$NON-NLS-1$
    this.dateOuverture = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[1]; //$NON-NLS-1$
    this.dateFermeture = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[2]; //$NON-NLS-1$
    this.voteUnique = (Boolean) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[3]; //$NON-NLS-1$
    this.dupliquable = (Boolean) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[4]; //$NON-NLS-1$
    this.banque = (Boolean) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[5]; //$NON-NLS-1$
    this.type = ((Integer) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[6]).intValue (); //$NON-NLS-1$
    this.publique = (Boolean) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[7]; //$NON-NLS-1$
    this.titre = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[8]; //$NON-NLS-1$
    this.lst = (List<SondageType>) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[9]; //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution ("/vue/jsp/admin/groupe/ajoutsondage.jsp"); //$NON-NLS-1$
  }

  public Boolean getAleatoire () {
    return this.aleatoire;
  }

  public Boolean getBanque () {
    return this.banque;
  }

  public String getDateFermeture () {
    return this.dateFermeture;
  }

  public String getDateOuverture () {
    return this.dateOuverture;
  }

  public Boolean getDupliquable () {
    return this.dupliquable;
  }

  public List<SondageType> getLst () {
    return Collections.unmodifiableList (this.lst);
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public Boolean getPublique () {
    return this.publique;
  }

  public String getTitre () {
    return this.titre;
  }

  public int getType () {
    return this.type;
  }

  public Boolean getVoteUnique () {
    return this.voteUnique;
  }

  public boolean isEditer () {
    return true;
  }

  public void setAleatoire (final Boolean a) {
    this.aleatoire = a;
  }

  public void setBanque (final Boolean b) {
    this.banque = b;
  }

  public void setDateFermeture (final String dF) {
    this.dateFermeture = dF;
  }

  public void setDateOuverture (final String dO) {
    this.dateOuverture = dO;
  }

  public void setDupliquable (final Boolean d) {
    this.dupliquable = d;
  }

  public void setNomGroupe (final String nG) {
    this.nomGroupe = nG;
  }

  public void setPublique (final Boolean p) {
    this.publique = p;
  }

  public void setTitre (final String t) {
    this.titre = t;
  }

  public void setType (final int t) {
    this.type = t;
  }

  public void setVoteUnique (final Boolean vU) {
    this.voteUnique = vU;
  }
}
