package fr.unice.ent.eve.modele;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.unice.ent.eve.modele.config.EveConfig;

/**
 * Le Modele est une classe d'implementation de fonctions destinees a simplifier
 * l'utilisation des classes du modele.
 * 
 * <br/>
 * <br/>
 * Les classes du modele sont des classes correspondant a des tables dans la
 * bdd. <br/>
 * Chaque classe du modele a ainsi des methodes d'ajout, suppression, requete
 * afin de ne pas avoir a utiliser de mecanismes complexes.
 * 
 * @author benychou
 * 
 */
public class Modele<T> implements java.lang.Comparable<Modele<T>>, Cloneable, Serializable {
  /**
	 * 
	 */
    private static final long serialVersionUID = 5815838653239395715L;

/**
   * Cette methode regarde si la derniere reinitialisation de la base de donnees
   * a bien fonctionne. Pour cela, elle regarde si la table EveConfig est vide
   * ou non.
   * 
   * @return un booleen
   */
  public static boolean testModele () {
    final Modele<?> m = new Modele<EveConfig> (true);

    final List<EveConfig> lec = EveConfig.staticInstance (m.getGT ())
        .requete ();

    final int taille = lec.size ();

    m.fin ();
    return taille > 0;
  }

  private transient GerantTransactions gT;

  private String objectId;
  
  public final String getObjectId () {
	return objectId;
  }

  public final void setObjectId (String objectId) {
	this.objectId = objectId;
  }

/**
   * Constructeur vide
   */
  public Modele () {

  }

  /**
   * Constructeur de creation de session
   * 
   * @param construire
   *          si construire est vrai, construit une session
   */
  public Modele (final boolean construire) {
    if (construire) {
      this.gT = new GerantTransactions ();
    }
  }

  /**
   * Constructeur d'affectation de session pre-existante
   * 
   * @param gt
   *          le gestionnaire de session
   */
  public Modele (final GerantTransactions gt) {
    this.gT = gt;
  }

  /**
   * Ajout de cet objet dans la base de donnees
   */
  public void ajout () {
    this.gT.ajout (this);
  }

  /**
   * Fonction de comparaison de deux element de ce type.
   * 
   * <br/>
   * <br/>
   * Fonction utilisee lors d'un tri
   * 
   * @param o
   *          l'autre objet avec lequel doit s'effectuer la comparaison
   * @return -1 si l'objet est avant, 0 s'ils sont au meme endroit, 1 si l'objet
   *         est apres
   */
  @Override
  @SuppressWarnings ({ "rawtypes", "unchecked" })
  public int compareTo (final Modele<T> o) {
    try {
      final Comparable c1;
      final Comparable c2;
      Method m;

      m = this.getClass ().getMethod ("getComparableId"); //$NON-NLS-1$
      c1 = (Comparable) m.invoke (this);

      m = o.getClass ().getMethod ("getComparableId"); //$NON-NLS-1$
      c2 = (Comparable) m.invoke (o);

      return c1.compareTo (c2);
    } catch (final SecurityException e) {
      return 0;
    } catch (final NoSuchMethodException e) {
      return 0;
    } catch (final IllegalArgumentException e) {
      return 0;
    } catch (final IllegalAccessException e) {
      return 0;
    } catch (final InvocationTargetException e) {
      return 0;
    } catch (final NullPointerException e) {
      return 0;
    }
  }

  /**
   * Enleve l'objet de la session
   */
  public void enlever () {
    this.gT.enlever (this);
  }

  /**
   * Fermeture de la session
   */
  public void fin () {
    this.gT.fermer ();
  }

  /**
   * Objet permettant de comparer deux element de ce type entre eux. Utilise
   * pour trier une liste-resultat lors d'une requete dans la base de donnees
   * 
   * @return l'objet pouvant etre compare
   */
  public Object getComparableId () {
	  if (Session.HIBERNATE_AND_NOT_MONGO){
        return new Long (this.hashCode ());
	  }
	  return this.getObjectId ();
  }

  /**
   * Obtenir le gestionnaire de session
   * 
   * @return le gestionnaire de session
   */
  public GerantTransactions getGT () {
    return this.gT;
  }

  /**
   * Obtenir la session
   * 
   * @return la session
   */
  public Session getSession () {
    return this.gT.ouvrir ();
  }

  /**
   * Declencher une mise a jour
   */
  public void mettreAJour () {
    this.gT.mettreAJour ();
  }

  /**
   * Declencher une mise a jour de l'objet o et des autres objets non
   * synchroniser
   * 
   * @param o
   *          l'objet a mettre a jour
   */
  public void mettreAJour (final Object o) {
    this.gT.mettreAJour (o);
  }

  /**
   * Declencher le rafraichissement de l'objet o
   * 
   * @param o
   *          l'objet a rafraichir
   */
  public void rafraichir (final Object o) {
    this.gT.rafraichir (o);
  }

  /**
   * Lance une requete sur tous les tuples presents dans la table
   * 
   * @return la liste-resultat des tuples
   */
  public List<T> requete () {
    final HashMap<String, String> defaut = new HashMap<String, String> ();
    return this.requete (this.getClass (), defaut);
  }

  /**
   * Lance une requete en specificant des conditions
   * 
   * @param h
   *          une HashMap<String, String> associant a un champ une valeur
   * @return la liste-resultat des tuples correspondant
   */
  public List<T> requete (final Map<String, String> h) {
    return this.requete (this.getClass (), h);
  }

  @SuppressWarnings ("unchecked")
protected List<T> requete (final Class<?> c, final Map<String, String> h) {
	  List<T> l = null; 
	  if (Session.HIBERNATE_AND_NOT_MONGO){
    String hSt = ""; //$NON-NLS-1$
    if (h.size () > 0) {
      hSt += " where "; //$NON-NLS-1$
    }

    for (final Iterator<String> it = h.keySet ().iterator () ; it.hasNext () ;) {
      final String s = it.next ();
      hSt += s + " = '" + h.get (s) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
      if (it.hasNext ()) {
        hSt += " and "; //$NON-NLS-1$
      }
    }
    this.gT.ouvrir ();
    l = (List<T>) this.gT.requete (c, hSt); //$NON-NLS-1$
	  }else{
		    String hSt = ""; //$NON-NLS-1$
		    if (h.size () > 1) {
		      hSt += "{$and : {"; //$NON-NLS-1$
		    }else if (h.size () == 1){
		    	hSt += "{";
		    }

		    for (final Iterator<String> it = h.keySet ().iterator () ; it.hasNext () ;) {
		      final String s = it.next ();
		      hSt += "\"" + s + "\" : \"" + h.get (s) + "\""; //$NON-NLS-1$ //$NON-NLS-2$
		      if (it.hasNext ()) {
		        hSt += ", "; //$NON-NLS-1$
		      }
		    }
		    if (h.size () > 1){
		      hSt += "}}";
		    }else if (h.size () == 1){
		    	hSt += "}";
		    }else{
		    	hSt = "{}";
		    }
		    this.gT.ouvrir ();
		    l = (List<T>) this.gT.requete (c, hSt); //$NON-NLS-1$
	  }
    return l;
  }

  /**
   * Fixe le gestionnaire de session. Doit etre fait pour utiliser une session
   * pre-existante
   * 
   * @param gt
   *          le gestionnaire de sessions
   */
  public void setGT (final GerantTransactions gt) {
    this.gT = gt;
  }

  /**
   * Declencher la suppression de l'objet o
   * 
   */
  public void suppression () {
    this.gT.suppression (this);
  }

}
