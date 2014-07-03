package fr.unice.ent.eve.controle.controleurs.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.OnwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import fr.unice.ent.eve.controle.commandes.InvocateurCommande;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.CommandeErreur;
import fr.unice.ent.eve.controle.erreurs.PermissionInsuffisanteErreur;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.CommandePermission;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.commandes.SitePartie;
import fr.unice.ent.eve.modele.groupes.Groupe;

/**
 * ActionBean identifie par l'adresse relative "/controleurs/action/Redirection" <br/>
 * <br/>
 * A comme role d'enregistrer les parametres passes dans la session, de verifier
 * les permissions, puis de lancer la commande invoquee (parametre comm)
 * 
 * @author benychou
 * 
 */
public class Redirection extends ActionBeanTraitant {

  private final HashMap<String, String> paramsAdd;

  /**
   * Constructeur vide. Initialise la variable de parametres additionnels
   */
  public Redirection () {
    super ();
    this.paramsAdd = new HashMap<String, String> ();
  }

  /**
   * Initialise la variable de parametres additionnels et le contexte de
   * l'ActionBean
   */
  public Redirection (final ActionBeanContext abc) {
    this.setContext (abc);
    this.paramsAdd = new HashMap<String, String> ();
  }

  /**
   * Ajoute une entree dans les parametres additionnels non presents dans la
   * requete
   * 
   * @param k
   *          cle de l'entree
   * @param v
   *          valeur de l'entree
   */
  public void ajoutParamAdd (final String k, final String v) {
    this.paramsAdd.put (k, v);
  }

  @SuppressWarnings ("unchecked")
  private void ajoutSession () {
    final Enumeration<String> e = this.getContext ().getRequest ()
        .getParameterNames ();
    while (e.hasMoreElements ()) {
      final String elem = e.nextElement ();

      this.getContext ()
          .getRequest ()
          .getSession ()
          .setAttribute (elem,
              this.getContext ().getRequest ().getParameter (elem));
    }

    if (this.paramsAdd != null) {
      for (final String n : this.paramsAdd.keySet ()) {
        this.getContext ().getRequest ().getSession ()
            .setAttribute (n, this.paramsAdd.get (n));
      }
    }
  }

  /**
   * Realise la redirection apres l'appel de la commande. Fonction appellee par
   * l'ActionBean
   * 
   * @return un objet de type Resolution
   * @see net.sourceforge.stripes.action.Resolution
   */
  @DefaultHandler
  public Resolution faire () {
    final Modele<SitePartie> m = new Modele<SitePartie> (true);
    final GerantTransactions gt = m.getGT ();

    this.ajoutSession ();
    final String nomCommande = this.getNomCommande ();

    final long attrG = this.getAttrG ();
    final long attrS = this.getAttrS ();

    final Resolution r = this.traiter (gt);
    if (r != null) { return r; }

    final Commandes commandes = Commandes.staticInstance (gt).getParNom (
        nomCommande);

    if (commandes == null) { return new EveErreur (this.getContext ())
        .lancerErreur (new CommandeErreur (nomCommande)); }

    if ((commandes.getNiveau () >= 1) && (attrG != 0)) {
      final Groupe g = (Groupe) gt.getSession ().load (Groupe.class,
          new Long (attrG));
      if (!this.getP ().isSuperAdmin ()
          && g.getContrainte ().getContrainteCommandes ().contains (commandes)) { return new EveErreur (
          this.getContext ()).lancerErreur (new ActionNonPermiseErreur (
          nomCommande)); }
      gt.getSession ().evict (g);
    }

    final Set<CommandePermission> s = commandes.getCope ();
    final String permManquante = this.getP ().aPermissions (s, attrG, attrS);

    if ("".equals (permManquante)) //$NON-NLS-1$
    {
      this.getContext ()
          .getRequest ()
          .getSession ()
          .setAttribute (
              "valide" + nomCommande + attrG + attrS, new Boolean (true)); //$NON-NLS-1$
      Object o = new Object ();
      try {
        gt.getSession ().clear ();
        o = InvocateurCommande.lancer (nomCommande + "\n" + attrG + "\n" //$NON-NLS-1$ //$NON-NLS-2$
            + attrS, m, this);
      } catch (final ClassNotFoundException e1) {
        return new EveErreur (this.getContext ())
            .lancerErreur (new CommandeErreur (nomCommande));
      } catch (final InstantiationException e1) {
        return new EveErreur (this.getContext ())
            .lancerErreur (new CommandeErreur (nomCommande));
      } catch (final IllegalAccessException e1) {
        return new EveErreur (this.getContext ())
            .lancerErreur (new CommandeErreur (nomCommande));
      } catch (final InvocationTargetException e) {
        return new EveErreur (this.getContext ())
            .lancerErreur (new CommandeErreur (nomCommande));
      } catch (final NoSuchMethodException e) {
        return new EveErreur (this.getContext ())
            .lancerErreur (new CommandeErreur (nomCommande));
      }

      final String partie = Commandes.staticInstance (gt)
          .getParNom (nomCommande).getPartie ().getPartie ();

      m.fin ();
      if (o instanceof Resolution) { return (Resolution) o; }
      return new RedirectResolution ("/controleurs/" //$NON-NLS-1$
          + partie + "/" + nomCommande, true); //$NON-NLS-1$
    }
    m.fin ();
    return new EveErreur (this.getContext ())
        .lancerErreur (new PermissionInsuffisanteErreur (nomCommande + " : " //$NON-NLS-1$
            + permManquante));
  }

  private long getAttrG () {
    final long attrG;
    if ((String) this.getContext ().getRequest ().getSession ()
        .getAttribute ("g") != null) //$NON-NLS-1$
    {
      attrG = Long.parseLong ((String) this.getContext ().getRequest ()
          .getSession ().getAttribute ("g")); //$NON-NLS-1$
    } else {
      attrG = 0;
    }
    return attrG;
  }

  private long getAttrS () {
    final long attrS;
    if ((String) this.getContext ().getRequest ().getSession ()
        .getAttribute ("s") != null) //$NON-NLS-1$
    {
      attrS = Long.parseLong ((String) this.getContext ().getRequest ()
          .getSession ().getAttribute ("s")); //$NON-NLS-1$
    } else {
      attrS = 0;
    }
    return attrS;
  }

  private String getNomCommande () {
    String nomCommande;
    nomCommande = this.getContext ().getRequest ().getParameter ("comm"); //$NON-NLS-1$

    if (nomCommande == null) {
      nomCommande = (String) this.getContext ().getRequest ().getSession ()
          .getAttribute ("comm"); //$NON-NLS-1$      
    }

    if ((nomCommande == null) && this.paramsAdd.containsKey ("comm")) //$NON-NLS-1$
    {
      nomCommande = this.paramsAdd.get ("comm"); //$NON-NLS-1$
    }
    return nomCommande;
  }

  /**
   * Realise la redirection (appel de faire ())
   * 
   * @return la chaine de caractere correspondant a l'url appellee.
   * @see #faire
   */
  public String rediriger () {
    final Resolution r = this.faire ();
    if (r instanceof RedirectResolution) { return ((RedirectResolution) r)
        .getUrl (); }
    if (r instanceof StreamingResolution) { return ((StreamingResolution) r)
        .toString (); }
    if (r instanceof OnwardResolution) { return ""; //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }
}
