package fr.unice.ent.eve.modele;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;

import sun.org.mozilla.javascript.internal.json.JsonParser;

import com.google.gson.Gson;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import fr.unice.ent.eve.controle.erreurs.BaseDeDonneesException;

/**
 * Classe d'encapsulation de l'objet Session de la couche DAO
 * 
 * @author benychou
 * @see Session
 */
public class Session {

	public  static boolean	                    HIBERNATE_AND_NOT_MONGO	= false;
	private static org.hibernate.SessionFactory	sessionFactory;
	private static com.mongodb.DB	            db;

	/**
	 * Methode statique de lecture de la configuration. Fait une seule fois lors
	 * de la premiere invocation d'une classe du modele.
	 * 
	 * @throws UnknownHostException
	 * @throws NumberFormatException
	 */
	public static void creerFabriqueSession () {
		if (Session.HIBERNATE_AND_NOT_MONGO) {
			Session.sessionFactory = new org.hibernate.cfg.Configuration ()
			        .configure ().buildSessionFactory ();
		} else if (System.getenv ("VCAP_SERVICES") != null){
			try {
				com.mongodb.DBObject dboConfig = (com.mongodb.DBObject) com.mongodb.util.JSON
				        .parse (System.getenv ("VCAP_SERVICES"));
				com.mongodb.BasicDBList list = (com.mongodb.BasicDBList) dboConfig
				        .get ("mongodb2-2.4.8");
				com.mongodb.DBObject element = (com.mongodb.DBObject) list
				        .get (0);
				com.mongodb.DBObject credentials = (com.mongodb.DBObject) element
				        .get ("credentials");
				com.mongodb.MongoClient client = new com.mongodb.MongoClient (
				        new com.mongodb.ServerAddress (credentials.get ("host")
				                .toString (), Integer.parseInt (credentials
				                .get ("port").toString ())),
				        Arrays.asList (com.mongodb.MongoCredential
				                .createMongoCRCredential (
				                        credentials.get ("user").toString (),
				                        "system", credentials.get ("password")
				                                .toString ().toCharArray ())));

				db = client.getDB (credentials.get ("name").toString ());
			} catch (NumberFormatException e) {
				e.printStackTrace ();
			} catch (UnknownHostException e) {
				e.printStackTrace ();
			}
		}else{
			com.mongodb.MongoClient client;
            try {
	            client = new com.mongodb.MongoClient (
	                    new com.mongodb.ServerAddress ("127.0.0.1", 27017));
				db = client.getDB ("eve");
            } catch (UnknownHostException e) {
	            e.printStackTrace();
            }
		}

	}

	/**
	 * Obtient une instance de session pre-existante ou une nouvelle session
	 * 
	 * @return un objet de type Session
	 */
	public static Session obtenirDerniereSession () {
		Session s = new Session ();
		if (Session.HIBERNATE_AND_NOT_MONGO) {
		try {
			s.session = Session.sessionFactory.getCurrentSession ();
		} catch (final org.hibernate.HibernateException e) {
			try {
				s.session = Session.sessionFactory.openSession ();
			} catch (final org.hibernate.HibernateException e2) {
				try {
					Session.sessionFactory.close ();
				} catch (final org.hibernate.HibernateException e3) {
				}
				Session.creerFabriqueSession ();
				s.session = Session.sessionFactory.openSession ();
			}
		}
		}else{
			if (Session.db == null){
				Session.creerFabriqueSession ();
			}
		}
		return s;
	}

	private org.hibernate.Session	session;

	/**
	 * Constructeur vide
	 */
	public Session () {

	}

	/**
	 * Debute une transaction
	 * 
	 * @return un objet de type Transaction
	 */
	public Transaction beginTransaction () {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		  return new Transaction (this.session.beginTransaction ());
		}
		return new Transaction (new Object ());
	}

	/**
	 * Suppression des liens entre objet et session
	 */
	public void clear () {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		  try {
			  this.session.clear ();
		  } catch (final org.hibernate.HibernateException e) {
			  final BaseDeDonneesException bdde = new BaseDeDonneesException (
			          e.getMessage ());
			  bdde.setStackTrace (e.getStackTrace ());
			  throw bdde;
		  }
		}
	}

	/**
	 * Fermeture de session
	 */
	public void close () {
		if (Session.HIBERNATE_AND_NOT_MONGO){
			 try {
			this.session.close ();
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}
	}

	/**
	 * Cree une requete et renvoit l'objet
	 * 
	 * @param classe
	 *            la classe a rechercher pour trouver sa table / collection
	 * @param phrase
	 *            les conditions de la requete
	 * @return un objet de type Query
	 * @see Query
	 */
	public Query createQuery (final Class<?> c, final String conditions) {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		try {
			final fr.unice.ent.eve.modele.Query query = new fr.unice.ent.eve.modele.Query (
			        this.session.createQuery (c.getSimpleName () + conditions));
			return query;
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}else{
			final fr.unice.ent.eve.modele.Query query = new fr.unice.ent.eve.modele.Query (
			        Session.db.getCollection (c.getSimpleName ()).find ((DBObject) JSON.parse (conditions)));
			return query;			
		}
	}

	/**
	 * Supprimer l'objet de la base de donnees
	 * 
	 * @param o
	 *            Objet du modele a supprimer
	 */
	public void delete (final Object o) {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		try {
			this.session.delete (o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}else{
			
		}
	}

	/**
	 * Deconnexion de la base de donnees.
	 */
	public void disconnect () {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		try {
			this.session.disconnect ();
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}
	}

	/**
	 * Supprime un objet du cache de la session
	 */
	public void evict (final Object o) {

		if (Session.HIBERNATE_AND_NOT_MONGO){
		try {
			this.session.evict (o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}else{
			if (o instanceof Modele){
				((Modele<?>) o).setObjectId (null);
			}
		}
	}

	/**
	 * Mise a jour des objets non synchronises
	 */
	public void flush () {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		try {
			this.session.flush ();
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}
	}

	/**
	 * Renvoit vrai si la session est ouverte
	 * 
	 * @return un booleen
	 */
	public boolean isOpen () {

		if (Session.HIBERNATE_AND_NOT_MONGO){
		return (this.session == null) || (this.session.isOpen ());
		}
		return true;
	}

	/**
	 * Recupere un objet du modele en fonction de son id a partir de la base de
	 * donnees
	 * 
	 * @param c
	 *            l'objet Class correspondant a la classe du modele
	 * @param o
	 *            l'id (objet serialisable)
	 * @return l'objet charge
	 */
	public Object load (final Class<?> c, final Serializable o) {

		if (Session.HIBERNATE_AND_NOT_MONGO){
		try {
			return this.session.load (c, o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}else if (o instanceof Modele){
			return this.convertOneToModele (c, Session.db.getCollection (c.getSimpleName ()).findOne (
					(DBObject) JSON.parse ("{\"_id\": { \"$oid\" : \"" + ((Modele<?>) o).getObjectId () + "\"}}")));
		}else if (o instanceof Number){
			return this.convertOneToModele (c, Session.db.getCollection (c.getSimpleName ()).findOne (
					(DBObject) JSON.parse ("{\"id\": " + o + "}")));
		}else if (o instanceof CharSequence){
			return this.convertOneToModele (c, Session.db.getCollection (c.getSimpleName ()).findOne (
					(DBObject) JSON.parse ("{\"id\": \"" + o + "\"}")));
		}
		return null;
	}

	private Object convertOneToModele (Class<?> c, DBObject found) {
	    return new Gson ().fromJson (JSON.serialize (found), c);
    }

	/**
	 * Rafraichir l'objet
	 * 
	 * @param o
	 *            Objet du modele a rafraichir
	 */
	public void refresh (final Object o) {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		try {
			this.session.refresh (o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}else if (o instanceof Serializable){
			this.load (o.getClass (), (Serializable)o);
		}
	}

	/**
	 * Annule la derniere operation
	 */
	public void rollback () {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		  this.session.getTransaction ().rollback ();
		}
	}

	/**
	 * Enregistrer l'objet dans la base de donnees
	 * 
	 * @param o
	 *            Objet du modele a enregistrer
	 * @return 
	 */
	public Object save (final Object o) {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		try {
			this.session.save (o);
			return o;
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}else{
			this.update (o);
			return o;
		}
	}

	/**
	 * Mettre a jour l'objet
	 * 
	 * @param o
	 *            Objet du modele a mettre a jour
	 */
	public void update (final Object o) {
		if (Session.HIBERNATE_AND_NOT_MONGO){
		try {
			this.session.update (o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
		}else if (o instanceof Modele){
			DBObject dbo = this.serializeToDBObject ((Modele<?>) o);
			if (((Modele<?>) o).getObjectId () == null){
				Session.db.getCollection (o.getClass ().getSimpleName ()).insert (dbo).getUpsertedId ();
				((Modele<?>) o).setObjectId (((ObjectId)dbo.get ("_id")).toHexString ());
			}else{
				Session.db.getCollection (o.getClass ().getSimpleName ()).update (
						(DBObject) JSON.parse ("{\"_id\": ObjectId(\"" + 
				                               ((Modele<?>) o).getObjectId () + "\")}"), 
								dbo);
			}
		}
	}

	private DBObject serializeToDBObject (Modele<?> o) {
	    return (DBObject) JSON.parse (ToStringBuilder.reflectionToString (o, new JsonToStringStyle (), false));
    }

}
