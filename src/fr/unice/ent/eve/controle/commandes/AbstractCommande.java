package fr.unice.ent.eve.controle.commandes;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.CommandePermission;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.commandes.SitePartie;
import fr.unice.ent.eve.modele.groupes.PermissionType;

/**
 * Classe abstraite dont derivent toutes les commandes de l'application. Une
 * commande est un objet competent pour executer une action comme ajouter un
 * sondage, editer les permissions.<br/>
 * <br/>
 * Les commandes ont plusieurs caracteristiques :
 * <ul>
 * <li>La description, une phrase courte pour aider a comprendre le role de
 * celle ci</li>
 * <li>Le niveau, qui permet de savoir a quel endroit et dans quel contexte la
 * commande doit etre disponible. Les differents niveaux possibles sont :
 * <ol>
 * <li>Pour le groupe (Action dans la liste des groupes)</li>
 * <li>Pour le groupe (Action a l'interieur d'un groupes)</li>
 * <li>Pour un sondage</li>
 * <li>Pour une question</li>
 * <li>Commande invisible</li>
 * <li>Dans la banque (niveau additionnel)</li>
 * </ol>
 * Le niveau 0 designe une commande au dessus du niveau du groupe (gerer les
 * services, les utilisateurs)</li>
 * <li>La partie d'une commande est l'url relative a partir de laquelle est
 * lancee la commande. (l'actionBean responsable de la commande)</li>
 * </ul>
 * <br/>
 * Les fonctions de la classe abstraite sont les passages de parametres, et
 * l'obtention d'information sur la commande Le constructeur realise
 * l'inscription de la commande
 * 
 * 
 * @author benychou
 * @see net.sourceforge.stripes.action.Resolution
 */
public abstract class AbstractCommande {

  private final Messages mess;
  private String[]       params;
  private Object[]       paramsObjet;

  /**
   * Constructeur appellee par la sous-classe lors de l'indexation de la
   * commande. Ne doit etre appelle que lors de l'indexation.<br/>
   * <br/>
   * Ce constructeur cherche dans le fichier de proprietes CommandeX.properties,
   * situe dans le dossier relatif fr/unice/ent/eve/controle/commandes/ les
   * proprietes NomCommande.niveau, NomCommande.description NomCommande.partie
   * et les permissions necessaires a l'execution de la commande.
   * 
   * @see fr.unice.ent.eve.controle.indexation.Indexation
   */
  public AbstractCommande () {
    final Modele<Commandes> m = new Modele<Commandes> (true);
    this.mess = new Messages (
        "fr.unice.ent.eve.controle.commandes.CommandeX", Locale.getDefault ()); //$NON-NLS-1$
    final HashMap<String, String> h = new HashMap<String, String> ();
    h.put ("commande", this.getClass ().getSimpleName ().substring (8)); //$NON-NLS-1$
    final List<Commandes> lC = Commandes.staticInstance (m.getGT ())
        .requete (h);
    if ((lC.size () == 0) && (this.getPartie () != null)) {
      final Commandes c = new Commandes (m.getGT ());
      c.setCommande (this.getClass ().getSimpleName ().substring (8));
      c.setDescription (this.getDescription ());
      c.setNiveau (Integer.parseInt (this.getNiveau ()));

      SitePartie sp = SitePartie.staticInstance (m.getGT ()).getParNom (
          this.getPartie ());

      if (sp == null) {
        sp = new SitePartie (m.getGT ());
        sp.setPartie (this.getPartie ());
        sp.ajout ();
        m.mettreAJour ();
        m.rafraichir (sp);
      }
      c.setIdPartie (sp.getId ());
      c.ajout ();
      m.rafraichir (c);

      int i = 0;
      while (this.getPermissions (i).charAt (0) != '!') {
        final String permNom = this.getPermissions (i);
        PermissionType pt = PermissionType.staticInstance (m.getGT ())
            .getParNom (permNom);

        if (pt == null) {
          pt = new PermissionType (m.getGT (), permNom, new String ());
          pt.ajout ();
          m.rafraichir (pt);
        }

        new CommandePermission (m.getGT (), c.getIdCommande (),
            pt.getIdPermission ()).ajout ();
        i++;
      }

      m.mettreAJour ();
    }

    m.fin ();
  }

  /**
   * Constructeur appelle lors de l'invocation d'une commande.
   * 
   * Ce constructeur lit simplement le fichier de proprietes, sans indexation.
   * 
   * @param b
   *          parametre pour differencier le constructeur pour l'indexation du
   *          constructeur pour l'invocation
   */
  public AbstractCommande (final Boolean b) {
    if (b.booleanValue ()) {
      this.mess = new Messages (
          "fr.unice.ent.eve.controle.commandes.CommandeX", Locale.getDefault ()); //$NON-NLS-1$
    } else {
      this.mess = new Messages (
          "fr.unice.ent.eve.controle.commandes.CommandeX", Locale.getDefault ()); //$NON-NLS-1$
    }
  }

  /**
   * Fonction d'execution de la commande, appelle par l'invocateur de commande
   * 
   * @return null, un objet de type Resolution (pour la redirection)
   * @see net.sourceforge.stripes.action.Resolution
   */
  public abstract Object executer ();

  /**
   * Renvoit la description de la commande
   * 
   * @return une String
   */
  public String getDescription () {
    return this.mess.getString (this.getClass ().getSimpleName ()
        + ".description"); //$NON-NLS-1$
  }

  /**
   * Accesseur pour obtenir le fichier de proprietes dans la commande concrete.
   * 
   * @return un objet de type Messages
   * @see fr.unice.ent.eve.controle.bundlemessages.Messages
   */
  public Messages getMess () {
    return this.mess;
  }

  /**
   * Renvoit le niveau de la commande
   * 
   * @return une String
   */
  public String getNiveau () {
    return this.mess.getString (this.getClass ().getSimpleName () + ".niveau"); //$NON-NLS-1$
  }

  /**
   * Accesseur appelle par les commandes concretes pour obtenir le tableau de
   * String passe en parametre.
   * 
   * @return un objet de type String []
   */
  public String[] getParams () {
    return this.params;
  }

  /**
   * Accesseur appelle par les commandes concretes pour obtenir le tableau de
   * Object passe en parametre.
   * 
   * @return un objet de type Object []
   */
  public Object[] getParamsObjet () {
    return this.paramsObjet;
  }

  /**
   * Renvoit l'url relative de l'actionBean responsable
   * 
   * @return une String
   */
  public String getPartie () {
    return this.mess.getString (this.getClass ().getSimpleName () + ".partie"); //$NON-NLS-1$
  }

  /**
   * Renvoit une des chaines decrivant une des permissions necessaires a
   * l'execution de la commande
   * 
   * @param i
   *          un entier donnant le numero de la permission
   * @return une String
   */
  public String getPermissions (final int i) {
    return this.mess.getString (this.getClass ().getSimpleName ()
        + ".permission[" + i + "]"); //$NON-NLS-1$ //$NON-NLS-2$   
  }

  /**
   * Passe des parametres de type String a une commande. Fonction appellee par
   * l'invocateur de commande
   * 
   * @param o
   *          tableau de String
   * @see fr.unice.ent.eve.controle.commandes.InvocateurCommande
   */
  public void setParams (final String[] o) {
    this.params = o;
  }

  /**
   * Passe des parametres de type Object a une commande. Fonction appellee par
   * l'invocateur de commande
   * 
   * @param o
   *          tableau de Object
   * @see fr.unice.ent.eve.controle.commandes.InvocateurCommande
   */
  public void setParamsObjet (final Object[] o) {
    this.paramsObjet = o;
  }
}
