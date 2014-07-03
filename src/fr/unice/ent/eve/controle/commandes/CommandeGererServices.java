package fr.unice.ent.eve.controle.commandes;

import java.util.Collections;
import java.util.List;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.config.EveConfig;
import fr.unice.ent.eve.modele.services.ServiceAuth;
import fr.unice.ent.eve.vue.VueServiceAuth;

/**
 * Commande d'affichage de l'ecran de gestion des services, et de mise a jour
 * (utilise la technologie Ajax)
 * 
 * @author benychou
 * 
 */
public class CommandeGererServices extends AbstractCommande {

  public CommandeGererServices () {
    super ();
  }

  public CommandeGererServices (final Boolean b) {
    super (b);
  }

  private Resolution changerCAS (final ActionBeanTraitant abt, final Modele<?> m) {
    if ((abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionService_nCasLogin") != null) //$NON-NLS-1$
        && (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionService_nCasValidate") != null)) //$NON-NLS-1$
    {
      EveConfig.staticInstance (m.getGT ()).set (
          "cas_login", //$NON-NLS-1$
          (String) (abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionService_nCasLogin"))); //$NON-NLS-1$
      EveConfig.staticInstance (m.getGT ()).set (
          "cas_validate", //$NON-NLS-1$
          (String) (abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionService_nCasValidate"))); //$NON-NLS-1$

      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionService_nCasLogin"); //$NON-NLS-1$
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionService_nCasValidate"); //$NON-NLS-1$

      return new StreamingResolution ("text/html", new String ()); //$NON-NLS-1$
    }
    return null;
  }

  private Resolution changerServiceGroupes (final ActionBeanTraitant abt,
      final Modele<?> m) {
    if ((abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionService_nServiceGroupesAdresse") != null) //$NON-NLS-1$
        && (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionService_nServiceGroupesClasse") != null)) //$NON-NLS-1$
    {
      EveConfig.staticInstance (m.getGT ()).set (
          "service_groupes_adresse", //$NON-NLS-1$
          (String) (abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionService_nServiceGroupesAdresse"))); //$NON-NLS-1$
      EveConfig.staticInstance (m.getGT ()).set (
          "service_groupes_classe", //$NON-NLS-1$
          (String) (abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionService_nServiceGroupesClasse"))); //$NON-NLS-1$      

      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionService_nServiceGroupesAdresse"); //$NON-NLS-1$
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionService_nServiceGroupesClasse"); //$NON-NLS-1$

      return new StreamingResolution ("text/html", new String ()); //$NON-NLS-1$
    }
    return null;
  }

  private Resolution changerSMTP (final ActionBeanTraitant abt, final Modele<?> m) {
    if (abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionService_smtpServeur") != null)//$NON-NLS-1$
    {
      EveConfig.staticInstance (m.getGT ()).set (
          "smtp_serveur", //$NON-NLS-1$
          (String) (abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionService_smtpServeur"))); //$NON-NLS-1$

      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionService_smtpServeur"); //$NON-NLS-1$

      return new StreamingResolution ("text/html", new String ()); //$NON-NLS-1$
    }
    return null;
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    Resolution r = abt.traiter (gt);

    if (r != null) { return r; }

    r = this.changerServiceGroupes (abt, m);

    if (r != null) { return r; }

    r = this.changerSMTP (abt, m);

    if (r != null) { return r; }

    r = this.changerCAS (abt, m);

    if (r != null) { return r; }

    r = this.validerAjouterEditerSupprimer (abt, m);

    if (r != null) { return r; }
    final List<ServiceAuth> lSA = ServiceAuth.staticInstance (gt).requete ();
    Collections.sort (lSA);

    final Object[] resultat = new Object[6];

    resultat[0] = EveConfig.staticInstance (gt).get ("service_groupes_classe"); //$NON-NLS-1$
    resultat[1] = EveConfig.staticInstance (gt).get ("service_groupes_adresse"); //$NON-NLS-1$
    resultat[2] = EveConfig.staticInstance (gt).get ("cas_login"); //$NON-NLS-1$
    resultat[3] = EveConfig.staticInstance (gt).get ("cas_validate"); //$NON-NLS-1$
    resultat[4] = VueServiceAuth.vue (lSA);
    resultat[5] = EveConfig.staticInstance (gt).get ("smtp_serveur"); //$NON-NLS-1$

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
        Commandes.staticInstance (m.getGT ())
            .getParNom ("GererServices").getPartie ().getPartie () + //$NON-NLS-1$
        "/GererServices", true); //$NON-NLS-1$  }
  }

  private Resolution validerAjouterEditerSupprimer (
      final ActionBeanTraitant abt, final Modele<?> m) {
    if ((abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionService_nAdresse") != null) //$NON-NLS-1$
        && (abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionService_nClasse") != null)) //$NON-NLS-1$
    {
      ServiceAuth sa = null;

      if (abt.getContext ().getRequest ().getSession ()
          .getAttribute ("GestionService_adresse") != null) //$NON-NLS-1$
      {
        sa = ServiceAuth.staticInstance (m.getGT ()).get (
            (String) abt.getContext ().getRequest ().getSession ()
                .getAttribute ("GestionService_adresse")); //$NON-NLS-1$
        sa.setGT (m.getGT ());
        sa.suppression ();

        m.mettreAJour ();
        sa = null;
      }

      if ((((String) abt.getContext ().getRequest ().getSession ()
          .getAttribute ("GestionService_nAdresse")) != null) //$NON-NLS-1$
          && (((String) abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionService_nAdresse")).length () > 0) && //$NON-NLS-1$
          (((String) abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionService_nClasse")) != null) //$NON-NLS-1$
          && (((String) abt.getContext ().getRequest ().getSession ()
              .getAttribute ("GestionService_nClasse")).length () > 0)) //$NON-NLS-1$
      {
        sa = new ServiceAuth (
            m.getGT (),
            (String) abt.getContext ().getRequest ().getSession ()
                .getAttribute ("GestionService_nAdresse"), (String) abt.getContext () //$NON-NLS-1$
                .getRequest ().getSession ()
                .getAttribute ("GestionService_nClasse")); //$NON-NLS-1$

        sa.ajout ();
        m.mettreAJour ();
      }

      String res = new String ();

      if (sa != null) {
        res = VueServiceAuth.vueUn (sa);
      }

      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionService_adresse"); //$NON-NLS-1$
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionService_nAdresse"); //$NON-NLS-1$
      abt.getContext ().getRequest ().getSession ()
          .removeAttribute ("GestionService_nClasse"); //$NON-NLS-1$

      return new StreamingResolution ("text/html", res); //$NON-NLS-1$
    }
    return null;
  }
}
