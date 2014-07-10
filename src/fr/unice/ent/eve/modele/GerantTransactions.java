package fr.unice.ent.eve.modele;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Classe gestionnaire de sessions avec la base de donnees. <br/>
 * Rend simple certaines taches (ajout, suppression, mise a jour, femeture de
 * session)
 * 
 * @author benychou
 * 
 */
public class GerantTransactions {
  static {
    Session.creerFabriqueSession ();
  }

  /**
   * Annuler la derniere operation
   */
  public static void annuler () {
	  try {
    new GerantTransactions ().session.rollback ();
	  }catch (Exception e){
		  Logger.getLogger (GerantTransactions.class).error ("rollback", e);
	  }
  }

  /**
   * Reconstruit la fabrique de sessions. Permet de se reconnecter a la base de
   * donnees (utilise dans la classe de gestion des exceptions).
   */
  public static void reconstruire () {
    Session.creerFabriqueSession ();
  }

  private fr.unice.ent.eve.modele.Session session;

  /**
   * Constructeur. Cree une nouvelle session
   */
  public GerantTransactions () {
    this.session = this.ouvrir ();
  }

  /**
   * Constructeur qui ne fait qu'affecter une session pre-existante
   * 
   * @param s
   */
  public GerantTransactions (final Session s) {
    this.session = s;
  }

  /**
   * Ajout d'un objet dans la base de donnees
   * 
   * @param o
   *          l'objet du modele
   */
  public Object ajout (final Object o) {
    final Transaction tx = this.session.beginTransaction ();
    Object ret = this.session.save (o);
    tx.commit ();
    return ret;
  }

  /**
   * Lorsqu'une action en ecriture sur la bdd necessite plusieurs requetes afin
   * de preserver l'integrite de la base de donnees, il est obligatoire
   * d'utiliser les transactions. Lors de la suppression d'un objet A, il faut
   * veiller a ce qu'aucun objet B ne soit, ou ne contienne l'objet A.
   * 
   * @return un objet de type Transaction
   * @see Transaction
   */
  public Transaction debutTransaction () {
    this.ouvrir ();
    return this.session.beginTransaction ();
  }

  /**
   * Enleve l'objet de la session
   * 
   * @param o
   *          l'objet a enlever
   */
  public void enlever (final Object o) {
    this.session.evict (o);
  }

  /**
   * Enregistrer un objet dans la base de donnees, a l'interieur d'une
   * transaction. L'action doit etre precedee de Transaction t =
   * gt.debutTransaction (), et suivie de t.commit ()
   * 
   * @param o
   *          l'objet du modele a enregistrer
   */
  public void enregistrerT (final Object o) {
    this.session.save (o);
  }

  /**
   * Si la session n'est pas fermee, effectue une mise a jour, supprime les
   * liens avec les objets manipules, puis ferme la session
   */
  public void fermer () {
    if (this.session.isOpen ()) {
      this.session.flush ();
      this.session.clear ();
      this.session.close ();
    }
  }

  /**
   * Fait un tx.commit ()
   * 
   * @param tx
   *          l'objet de type Transaction
   */
  public void finTransaction (final Transaction tx) {
    tx.commit ();
  }

  /**
   * Obtient l'objet de session
   * 
   * @return un objet de type Session
   * @see fr.unice.ent.eve.modele.Session
   */
  public fr.unice.ent.eve.modele.Session getSession () {
    return this.ouvrir ();
  }

  /**
   * Effectue une mise a jour des objets non synchronises avec la base de
   * donnees
   */
  public void mettreAJour () {
    this.session.flush ();
  }

  /**
   * Met a jour l'objet, puis effectue une mise a jour des objets non
   * synchronises avec la base de donnees
   */
  public void mettreAJour (final Object o) {
    this.session.update (o);
    this.mettreAJour ();
  }

  /**
   * Ouvre une session si une session n'est pas deja ouverte dans l'objet
   * 
   * @return un objet de type Session
   * @see fr.unice.ent.eve.modele.Session
   */
  public fr.unice.ent.eve.modele.Session ouvrir () {
    if ((this.session == null) || !this.session.isOpen ()) {
      this.session = Session.obtenirDerniereSession ();
    }
    return this.session;
  }

  /**
   * Recupere le dernier etat dans la base de donnees de l'objet
   * 
   * @param o
   *          l'objet a rafraichir
   */
  public void rafraichir (final Object o) {
    this.session.refresh (o);
  }

  /**
   * Lance une requete de selection dans la session.
   * 
   * @param phrase
   *          requete a executer
   * @return une Liste d'objets correspondant a la requete
   */
  @SuppressWarnings ({ "unchecked", "rawtypes" })
  public List<? extends Modele> requete (final Class<?> c, final String conditions) {
    final List<Modele> l = (List<Modele>) this.session.createQuery (c, conditions)
        .list (c);
    Collections.sort (l);
    return l;

  }

  /**
   * Supprime l'objet du modele de la base de donnees
   * 
   * @param o
   *          l'objet a supprimer
   */
  public void suppression (final Object o) {
    final Transaction tx = this.session.beginTransaction ();
    this.session.delete (o);
    tx.commit ();
  }

  /**
   * Supprimer un objet de la base de donnees, a l'interieur d'une transaction.
   * L'action doit etre precedee de Transaction t = gt.debutTransaction (), et
   * suivie de t.commit ()
   * 
   * @param o
   *          l'objet du modele a supprimer
   */
  public void supprimerT (final Object o) {
    this.session.delete (o);
  }
}
