package fr.unice.ent.eve.modele;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Arrays;

import fr.unice.ent.eve.controle.erreurs.BaseDeDonneesException;

/**
 * Classe d'encapsulation de l'objet Session de la couche DAO
 * 
 * @author benychou
 * @see Session
 */
public class Session {

	private static boolean	                    HIBERNATE_AND_NOT_MONGO	= false;
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
		} else {
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
		return new Transaction (this.session.beginTransaction ());
	}

	/**
	 * Suppression des liens entre objet et session
	 */
	public void clear () {
		try {
			this.session.clear ();
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Fermeture de session
	 */
	public void close () {
		try {
			this.session.close ();
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Cree une requete et renvoit l'objet
	 * 
	 * @param phrase
	 *            le contenu de la requete
	 * @return un objet de type Query
	 * @see Query
	 */
	public Query createQuery (final String phrase) {
		try {
			final fr.unice.ent.eve.modele.Query query = new fr.unice.ent.eve.modele.Query (
			        this.session.createQuery (phrase));
			return query;
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Supprimer l'objet de la base de donnees
	 * 
	 * @param o
	 *            Objet du modele a supprimer
	 */
	public void delete (final Object o) {
		try {
			this.session.delete (o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Deconnexion de la base de donnees.
	 */
	public void disconnect () {
		try {
			this.session.disconnect ();
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Supprime un objet du cache de la session
	 */
	public void evict (final Object o) {
		try {
			this.session.evict (o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Mise a jour des objets non synchronises
	 */
	public void flush () {
		try {
			this.session.flush ();
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Renvoit vrai si la session est ouverte
	 * 
	 * @return un booleen
	 */
	public boolean isOpen () {
		return (this.session == null) || (this.session.isOpen ());
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
		try {
			return this.session.load (c, o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Rafraichir l'objet
	 * 
	 * @param o
	 *            Objet du modele a rafraichir
	 */
	public void refresh (final Object o) {
		try {
			this.session.refresh (o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Annule la derniere operation
	 */
	public void rollback () {
		this.session.getTransaction ().rollback ();
	}

	/**
	 * Enregistrer l'objet dans la base de donnees
	 * 
	 * @param o
	 *            Objet du modele a enregistrer
	 */
	public void save (final Object o) {
		try {
			this.session.save (o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

	/**
	 * Mettre a jour l'objet
	 * 
	 * @param o
	 *            Objet du modele a mettre a jour
	 */
	public void update (final Object o) {
		try {
			this.session.update (o);
		} catch (final org.hibernate.HibernateException e) {
			final BaseDeDonneesException bdde = new BaseDeDonneesException (
			        e.getMessage ());
			bdde.setStackTrace (e.getStackTrace ());
			throw bdde;
		}
	}

}
