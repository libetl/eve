package fr.unice.ent.eve.controle.indexation;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe d'indexation de classes.
 * 
 * <br/>
 * <br/>
 * Cette classe va parcourir la liste des classes compilees a partir du dossier
 * specifie puis les inscrire en appellant le constructeur d'indexation de ces
 * classes
 * 
 * @author benychou
 * 
 */
public class Indexation implements FilenameFilter {
  /**
   * Methode d'indexation a partir d'un objet package
   * 
   * @param p
   *          le package ou se trouvent les classes a indexer
   * @return les objets indexes
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   */
  public static Set<Object> indexer (final Package p)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException {
    return new Indexation ().indexer (new String (), p.getName (),
        new String ());
  }

  /**
   * Methode d'indexation a partir d'un objet package
   * 
   * @param p
   *          le package ou se trouvent les classes a indexer
   * @param prefixe
   *          le nom des classes a indexer commence par ce prefixe
   * @return les objets indexes commencant par le prefixe
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   */
  public static Set<Object> indexer (final Package p, final String prefixe)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException {
    return new Indexation ().indexer (new String (), p.getName (), prefixe);
  }

  /**
   * Methode d'indexation a partir d'un objet package
   * 
   * @param package0
   *          le nom d'un package contenant les classes a indexer
   * @return les objets indexes
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   */
  public static Set<Object> indexer (final String package0)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException {
    return new Indexation ().indexer (new String (), package0, new String ());
  }

  public static Set<Object> indexerFilsStatic (final Class<?> classe)
      throws InstantiationException, IllegalAccessException {
    return new Indexation ().indexerFils (classe);
  }

  public static Set<Object> indexerFilsStatic (final String classe)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException {
    return new Indexation ().indexerFils (Class.forName (classe));
  }

  public static Set<Object> indexerStatic (final String s, final String prefixe)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException {
    return new Indexation ().indexer (new String (), s, prefixe);
  }

  public static Set<Object> indexerStatic (final String doss, final String s,
      final String prefixe) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    return new Indexation ().indexer (doss, s, prefixe);
  }

  private String prefixe;

  /**
   * Constructeur vide
   */
  public Indexation () {
    this.prefixe = ""; //$NON-NLS-1$
  }

  @Override
  public boolean accept (final File dir, final String name) {
    dir.canRead ();
    return name.endsWith (".class") && name.startsWith (this.prefixe) //$NON-NLS-1$
        && (name.length () > this.prefixe.length ());
  }

  public Set<Object> indexer (final String doss, final String package0,
      final String prefixe0) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {

    final String p = doss + package0.replace ('.', '/');
    final File f = new File (p);
    this.prefixe = prefixe0;
    final String[] noms = f.list (this);
    final HashSet<Object> res = new HashSet<Object> ();

    if (noms == null) { return res; }

    for (final String element : noms) {
      final Object e = Class
          .forName (package0 + "." + this.nomSimple (element)).newInstance (); //$NON-NLS-1$
      res.add (e);
    }

    return res;
  }

  public Set<Object> indexerFils (final Class<?> classe)
      throws InstantiationException, IllegalAccessException {

    final HashSet<Object> res = new HashSet<Object> ();

    for (final Class<?> cf : classe.getClasses ()) {
      final Object e = cf.newInstance ();
      res.add (e);
    }

    return res;
  }

  private String nomSimple (final String nom) {
    return nom.substring (0, nom.length () - 6);
  }

  public String nomSimple (final String nom, final String prefixe0) {
    return nom.substring (prefixe0.length (), nom.length () - 6);
  }

}
