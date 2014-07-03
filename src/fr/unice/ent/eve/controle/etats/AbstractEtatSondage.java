package fr.unice.ent.eve.controle.etats;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.EtatInexistantException;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.modele.sondages.SondageEtat;

/**
 * Classe abstraite des etats d'un sondage. Un etat d'un sondage est un objet
 * qui fournit les fonctions accessibles et les transitions vers un autre etat.
 * 
 * Ceci permet d'identifier un comportement de l'objet sondage sans toucher aux
 * autres objets. Il s'agit du schema de conception du meme nom.
 * 
 * @author benychou
 * 
 */
public abstract class AbstractEtatSondage implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -8993076587399572412L;

  /**
   * Obtient un etat d'un sondage a partir de son nom.
   * 
   * @param s
   *          la chaine correspondant au nom
   * @return un etat de sondage
   * @throws EtatInexistantException
   *           si l'etat demande n'existe pas ou est mal implemente
   */
  public static AbstractEtatSondage get (final String s)
      throws EtatInexistantException {
    try {
      return (AbstractEtatSondage) Class
          .forName ("fr.unice.ent.eve.controle.etats.Sondage" + s) //$NON-NLS-1$
          .getConstructor (Boolean.class).newInstance (new Boolean (true));

    } catch (final InstantiationException e) {
      throw new EtatInexistantException (s);
    } catch (final IllegalAccessException e) {
      throw new EtatInexistantException (s);
    } catch (final ClassNotFoundException e) {
      throw new EtatInexistantException (s);
    } catch (final IllegalArgumentException e) {
      throw new EtatInexistantException (s);
    } catch (final SecurityException e) {
      throw new EtatInexistantException (s);
    } catch (final InvocationTargetException e) {
      throw new EtatInexistantException (s);
    } catch (final NoSuchMethodException e) {
      throw new EtatInexistantException (s);
    }
  }

  private Sondage sondage;

  /**
   * Constructeur appelle lors de l'indexation. Ajoute l'etat courant a la liste
   * des etats enregistres
   */
  public AbstractEtatSondage () {
    final Modele<Sondage> m = new Modele<Sondage> (true);
    final GerantTransactions gt = m.getGT ();
    new SondageEtat (gt, this.getClass ().getSimpleName ().substring (7))
        .ajout ();
  }

  /**
   * Constructeur vide, appelle lors du changement d'etat
   * 
   * @param b
   *          Boolean pour differencier ce constructeur de l'autre
   */
  public AbstractEtatSondage (final Boolean b) {
    b.hashCode ();
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void ajoutQuestion () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("ajoutQuestion"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void bloquer () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("bloquer"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void cacher () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("cacher"); //$NON-NLS-1$
  }

  protected void changerEtat (final String etat) {
    final SondageEtat se = SondageEtat.staticInstance (
        this.getSondage ().getGT ()).getParNom (etat);

    this.getSondage ().setEtatN (se);
    this.getSondage ().setIdEtat (se.getId ());
    this.getSondage ().mettreAJour (this.getSondage ());
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void changerPermissions () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("changerPermissions"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void consulterResultats () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("consulterResultats"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void debloquer () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("debloquer"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void dupliquer () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("dupliquer"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void editer () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("editer"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void envoyer () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("envoyer"); //$NON-NLS-1$
  }

  /**
   * 
   */
  public void envoyerCourriel () {
    // TODO Auto-generated method stub

  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void envoyerResultats () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("envoyerResultats"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void fermer () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("fermer"); //$NON-NLS-1$
  }

  /**
   * Obtenir le sondage associe a l'etat courant
   * 
   * @return un objet de type Sondage
   */
  public Sondage getSondage () {
    return this.sondage;
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void lier () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("lier"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void modifierQuestions () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("modifierQuestions"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void ouvrir () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("ouvrir"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void remiseAZero () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("remiseAZero"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void restaurer () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("restaurer"); //$NON-NLS-1$
  }

  /**
   * Associe le sondage a l'etat courant
   */
  public void setSondage (final Sondage s) {
    this.sondage = s;
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void supprimer () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("supprimer"); //$NON-NLS-1$
  }

  /**
   * Lance l'action sur le sondage et renvoit une exception si elle n'est pas
   * permise dans l'etat courant
   * 
   * @throws ActionNonPermiseException
   *           si l'action n'est pas possible dans cet etat
   */
  public void voter () throws ActionNonPermiseException {
    throw new ActionNonPermiseException ("voter"); //$NON-NLS-1$
  }

}
