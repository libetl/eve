package fr.unice.ent.eve.controle.controleurs.admin.groupe;

import java.util.Collections;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.action.Redirection;
import fr.unice.ent.eve.modele.sondages.SondageType;

public class AjouterSondage extends ActionBeanTraitant {

  private boolean           aleatoire;
  private boolean           banque;
  private String            dateFermeture;
  private String            dateOuverture;
  private boolean           dupliquable;
  private boolean           editer;
  private List<SondageType> lst;
  private String            nomGroupe = ""; //$NON-NLS-1$
  private boolean           publique;
  private String            titre;
  private int               type;

  private boolean           voteUnique;

  public AjouterSondage () {
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

    this.nomGroupe = (String) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[0]; //$NON-NLS-1$

    this.lst = (List<SondageType>) ((Object[]) this.getContext ().getRequest ()
        .getSession ().getAttribute ("resultatDerniereCommande"))[1]; //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .removeAttribute ("resultatDerniereCommande"); //$NON-NLS-1$

    return new ForwardResolution ("/vue/jsp/admin/groupe/ajoutsondage.jsp"); //$NON-NLS-1$
  }

  public Resolution creer () {
    final Redirection red = new Redirection ();
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute ("GestionSondage_aleatoire", new Boolean (this.aleatoire)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionSondage_dateOuverture", this.dateOuverture); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionSondage_dateFermeture", this.dateFermeture); //$NON-NLS-1$
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute (
            "GestionSondage_voteUnique", new Boolean (this.voteUnique)); //$NON-NLS-1$
    this.getContext ()
        .getRequest ()
        .getSession ()
        .setAttribute (
            "GestionSondage_dupliquable", new Boolean (this.dupliquable)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionSondage_banque", new Boolean (this.banque)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionSondage_type", new Integer (this.type)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionSondage_publique", new Boolean (this.publique)); //$NON-NLS-1$
    this.getContext ().getRequest ().getSession ()
        .setAttribute ("GestionSondage_titre", this.titre); //$NON-NLS-1$

    if (this.editer) {
      this.getContext ().getRequest ().getSession ()
          .setAttribute ("GestionSondage_editer", new Object ()); //$NON-NLS-1$      
    }

    red.ajoutParamAdd ("comm", this.getClass ().getSimpleName ()); //$NON-NLS-1$
    red.setContext (this.getContext ());
    return red.faire ();
  }

  public String getDateFermeture () {
    return this.dateFermeture;
  }

  public String getDateOuverture () {
    return this.dateOuverture;
  }

  public List<SondageType> getLst () {
    return Collections.unmodifiableList (this.lst);
  }

  public String getNomGroupe () {
    return this.nomGroupe;
  }

  public String getTitre () {
    return this.titre;
  }

  public int getType () {
    return this.type;
  }

  public boolean isAleatoire () {
    return this.aleatoire;
  }

  public boolean isBanque () {
    return this.banque;
  }

  public boolean isDupliquable () {
    return this.dupliquable;
  }

  public boolean isEditer () {
    return false;
  }

  public boolean isPublique () {
    return this.publique;
  }

  public boolean isVoteUnique () {
    return this.voteUnique;
  }

  public void setAleatoire (final boolean a) {
    this.aleatoire = a;
  }

  public void setBanque (final boolean b) {
    this.banque = b;
  }

  public void setDateFermeture (final String dF) {
    this.dateFermeture = dF;
  }

  public void setDateOuverture (final String dO) {
    this.dateOuverture = dO;
  }

  public void setDupliquable (final boolean d) {
    this.dupliquable = d;
  }

  public void setEditer (final boolean e) {
    this.editer = e;
  }

  public void setNomGroupe (final String nG) {
    this.nomGroupe = nG;
  }

  public void setPublique (final boolean p) {
    this.publique = p;
  }

  public void setTitre (final String t) {
    this.titre = t;
  }

  public void setType (final int t) {
    this.type = t;
  }

  public void setVoteUnique (final boolean vU) {
    this.voteUnique = vU;
  }

}
