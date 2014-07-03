package fr.unice.ent.eve.modele.sondages;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.EtatInexistantException;
import fr.unice.ent.eve.controle.etats.AbstractEtatSondage;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.reponses.Reponse;
import fr.unice.ent.eve.modele.votes.Vote;

public class Sondage extends Modele<Sondage> implements Serializable {
  private static Sondage    instance;
  /**
   * 
   */
  private static final long serialVersionUID = 7236865596320225334L;

  public static Sondage staticInstance (final GerantTransactions s) {
    if (Sondage.instance == null) {
      try {
        Sondage.instance = new Sondage (s);
      } catch (final EtatInexistantException e) {
        return null;
      }
    }
    return Sondage.instance;
  }

  private boolean              affichageAleatoire;
  private Date                 dateFermeture;
  private Date                 dateOuverture;
  private boolean              dupliquable;
  private boolean              enBanque;
  private AbstractEtatSondage  etat;
  private SondageEtat          etatN;
  private Set<GroupeSondage>   groupeSondages;
  private long                 id;
  private int                  idEtat;
  private int                  idSondageType;
  private Set<Permission>      permissions;
  private boolean              publique;
  private Set<QuestionSondage> questionSondage;
  private Set<Reponse>         reponses;
  private String               titre;
  private SondageType          type;

  private Set<Vote>            votes;

  private boolean              voteUnique;

  public Sondage () throws EtatInexistantException {
    super ();
    this.titre = "--"; //$NON-NLS-1$
    this.etat = AbstractEtatSondage.get ("Vide"); //$NON-NLS-1$
    this.etat.setSondage (this);
  }

  public Sondage (final boolean b) throws EtatInexistantException {
    super ();
    if (b) {
      this.titre = "--"; //$NON-NLS-1$
      this.etat = AbstractEtatSondage.get ("Vide"); //$NON-NLS-1$
      this.etat.setSondage (this);
    }
  }

  public Sondage (final GerantTransactions gt) throws EtatInexistantException {
    super (gt);
    this.titre = "--"; //$NON-NLS-1$
    this.etat = AbstractEtatSondage.get ("Vide"); //$NON-NLS-1$
    this.etat.setSondage (this);
  }

  public void ajoutQuestion () throws ActionNonPermiseException {
    this.etat.ajoutQuestion ();
  }

  public void bloquer () throws ActionNonPermiseException {
    this.etat.bloquer ();
  }

  public void cacher () throws ActionNonPermiseException {
    this.etat.cacher ();
  }

  public void changerPermissions () throws ActionNonPermiseException {
    this.etat.changerPermissions ();
  }

  public void consulterResultats () throws ActionNonPermiseException {
    this.etat.consulterResultats ();
  }

  public void debloquer () throws ActionNonPermiseException {
    this.etat.debloquer ();
  }

  public void dupliquer () throws ActionNonPermiseException {
    this.etat.dupliquer ();
  }

  public void editer () throws ActionNonPermiseException {
    this.etat.editer ();
  }

  public void envoyer () throws ActionNonPermiseException {
    this.etat.envoyer ();
  }

  public void envoyerCourriel () throws ActionNonPermiseException {
    this.etat.envoyer ();
  }

  public void envoyerResultats () throws ActionNonPermiseException {
    this.etat.envoyerResultats ();
  }

  public void fermer () throws ActionNonPermiseException {
    this.etat.fermer ();
  }

  @Override
  public Object getComparableId () {
    return new Long (this.id);
  }

  public Date getDateFermeture () {
    return this.dateFermeture;
  }

  public Date getDateOuverture () {
    return this.dateOuverture;
  }

  public AbstractEtatSondage getEtat () {
    return this.etat;
  }

  public SondageEtat getEtatN () {
    return this.etatN;
  }

  public Set<GroupeSondage> getGroupeSondages () {
    return this.groupeSondages;
  }

  public long getId () {
    return this.id;
  }

  public int getIdEtat () {
    return this.idEtat;
  }

  public int getIdSondageType () {
    return this.idSondageType;
  }

  public Set<Permission> getPermissions () {
    return this.permissions;
  }

  public Set<QuestionSondage> getQuestionSondage () {
    return this.questionSondage;
  }

  public Set<Reponse> getReponses () {
    return this.reponses;
  }

  public String getTitre () {
    return this.titre;
  }

  public SondageType getType () {
    return this.type;
  }

  public Set<Vote> getVotes () {
    return this.votes;
  }

  public boolean isAffichageAleatoire () {
    return this.affichageAleatoire;
  }

  public boolean isBloque () {
    final Date dateActuelle = new Date ();
    if (this.dateFermeture.before (dateActuelle)) {
      this.setIdEtat (SondageEtat.staticInstance (this.getGT ())
          .getParNom ("Bloque").getId ()); //$NON-NLS-1$
    }
    return this.etatN.getNomEtat ().equals ("Bloque") //$NON-NLS-1$
        || this.dateOuverture.after (dateActuelle);
  }

  public boolean isDupliquable () {
    return this.dupliquable;
  }

  public boolean isEnBanque () {
    return this.enBanque;
  }

  public boolean isOuvert () {
    return this.etatN.getNomEtat ().equals ("Ouvert"); //$NON-NLS-1$
  }

  public boolean isPublique () {
    return this.publique;
  }

  public boolean isVoteUnique () {
    return this.voteUnique;
  }

  public void lier () throws ActionNonPermiseException {
    this.etat.lier ();
  }

  public void modifierQuestions () throws ActionNonPermiseException {
    this.etat.modifierQuestions ();
  }

  public void ouvrir () throws ActionNonPermiseException {
    this.etat.ouvrir ();
  }

  public void remiseAZero () throws ActionNonPermiseException {
    this.etat.remiseAZero ();
  }

  public void restaurer () throws ActionNonPermiseException {
    this.etat.restaurer ();
  }

  public void setAffichageAleatoire (final boolean affichageAleatoire0) {
    this.affichageAleatoire = affichageAleatoire0;
  }

  public void setDateFermeture (final Date dateFermeture0) {
    this.dateFermeture = dateFermeture0;
  }

  public void setDateOuverture (final Date dateOuverture0) {
    this.dateOuverture = dateOuverture0;
  }

  public void setDupliquable (final boolean dupliquable0) {
    this.dupliquable = dupliquable0;
  }

  public void setEnBanque (final boolean enBanque0) {
    this.enBanque = enBanque0;
  }

  public void setEtat (final AbstractEtatSondage etat0) {
    this.etat = etat0;
  }

  public void setEtatN (final SondageEtat eN) {
    this.etatN = eN;
    try {
      this.etat = AbstractEtatSondage.get (eN.getNomEtat ());
      this.etat.setSondage (this);
    } catch (final EtatInexistantException e) {
      e.printStackTrace ();
    }
  }

  public void setGroupeSondages (final Set<GroupeSondage> grSo) {
    this.groupeSondages = grSo;
  }

  public void setId (final long id0) {
    this.id = id0;
  }

  public void setIdEtat (final int idE) {
    this.idEtat = idE;
  }

  public void setIdSondageType (final int iST) {
    this.idSondageType = iST;
  }

  public void setPermissions (final Set<Permission> p) {
    this.permissions = p;
  }

  public void setPublique (final boolean publique0) {
    this.publique = publique0;
  }

  public void setQuestionSondage (final Set<QuestionSondage> qS) {
    this.questionSondage = qS;
  }

  public void setReponses (final Set<Reponse> rep) {
    this.reponses = rep;
  }

  public void setTitre (final String titre0) {
    this.titre = titre0;
  }

  public void setType (final SondageType type0) {
    this.type = type0;
  }

  public void setVotes (final Set<Vote> v) {
    this.votes = v;
  }

  public void setVoteUnique (final boolean vU) {
    this.voteUnique = vU;
  }

  public void supprimer () throws ActionNonPermiseException {
    this.etat.supprimer ();
  }

  public void voter () throws ActionNonPermiseException {
    this.etat.voter ();
  }
}
