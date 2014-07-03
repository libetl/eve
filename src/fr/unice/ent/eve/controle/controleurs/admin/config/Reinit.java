package fr.unice.ent.eve.controle.controleurs.admin.config;

import java.util.Enumeration;
import java.util.Locale;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.CommandeErreur;
import fr.unice.ent.eve.controle.indexation.Indexation;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.CommandePermission;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.commandes.ContrainteCommande;
import fr.unice.ent.eve.modele.config.EveConfig;
import fr.unice.ent.eve.modele.groupes.GroupeContrainte;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.groupes.PermissionType;
import fr.unice.ent.eve.modele.groupes.Personne;
import fr.unice.ent.eve.modele.questions.QuestionType;
import fr.unice.ent.eve.modele.sondages.SondageEtat;
import fr.unice.ent.eve.modele.sondages.SondageType;

public class Reinit extends ActionBeanTraitant {

  public Reinit () {
    super ();
  }

  public String initCommandes (final Modele<Commandes> m,
      final GerantTransactions gt, final String doss) {
    try {
      for (final Commandes c : Commandes.staticInstance (gt).requete ()) {
        c.setGT (gt);
        c.suppression ();
      }

      for (final CommandePermission c : CommandePermission.staticInstance (gt)
          .requete ()) {
        c.setGT (gt);
        c.suppression ();
      }

      for (final ContrainteCommande c : ContrainteCommande.staticInstance (gt)
          .requete ()) {
        c.setGT (gt);
        c.suppression ();
      }

      m.mettreAJour ();

      for (final Personne c : Personne.staticInstance (gt).requete ()) {
        c.setGT (gt);
        c.suppression ();
      }

      for (final Permission c : Permission.staticInstance (gt).requete ()) {
        c.setGT (gt);
        c.suppression ();
      }

      for (final PermissionType c : PermissionType.staticInstance (gt)
          .requete ()) {
        c.setGT (gt);
        c.suppression ();
      }

      m.mettreAJour ();
      return Indexation.indexerStatic (doss,
          "fr.unice.ent.eve.controle.commandes", //$NON-NLS-1$
          "Commande").toString (); //$NON-NLS-1$
    } catch (final InstantiationException e) {
      return new String ();
    } catch (final IllegalAccessException e) {
      return new String ();
    } catch (final ClassNotFoundException e) {
      return new String ();
    }
  }

  public void initContrainteCommande (final Modele<GroupeContrainte> m,
      final GerantTransactions gt, final Messages mess) {
    for (final GroupeContrainte c : GroupeContrainte.staticInstance (gt)
        .requete ()) {
      c.setGT (gt);
      c.suppression ();
    }

    m.mettreAJour ();

    int i = 0;
    while (mess.getString ("contrainte[" + i + "].nom").charAt (0) != '!') //$NON-NLS-1$//$NON-NLS-2$
    {
      final GroupeContrainte gc = new GroupeContrainte (gt,
          mess.getString ("contrainte[" + i + "].nom")); //$NON-NLS-1$//$NON-NLS-2$
      int j = 0;
      gc.ajout ();
      m.rafraichir (gc);
      while (mess
          .getString ("contrainte[" + i + "].commande[" + j + "]").charAt (0) != '!') //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
      {
        final String comm = mess
            .getString ("contrainte[" + i + "].commande[" + j + "]"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        new ContrainteCommande (gt, gc.getId (), Commandes.staticInstance (gt)
            .getParNom (comm).getIdCommande ()).ajout ();
        j++;
      }
      i++;
    }
    m.mettreAJour ();
  }

  public String initEtats (final Modele<SondageEtat> m,
      final GerantTransactions gt, final String doss) {
    try {
      for (final SondageEtat se : SondageEtat.staticInstance (gt).requete ()) {
        se.setGT (gt);
        se.suppression ();
      }

      m.mettreAJour ();
      return Indexation.indexerStatic (doss, "fr.unice.ent.eve.controle.etats", //$NON-NLS-1$
          "Sondage").toString (); //$NON-NLS-1$
    } catch (final InstantiationException e) {
      return new String ();
    } catch (final IllegalAccessException e) {
      return new String ();
    } catch (final ClassNotFoundException e) {
      return new String ();
    }
  }

  public void initEveConfig (final Modele<EveConfig> m,
      final GerantTransactions gt, final Messages mess) {
    for (final EveConfig c : EveConfig.staticInstance (gt).requete ()) {
      c.setGT (gt);
      c.suppression ();
    }

    final Enumeration<String> e = mess.getKeys ();
    while (e.hasMoreElements ()) {
      final String n = e.nextElement ();
      final EveConfig ec = new EveConfig (gt, n, mess.getString (n));
      ec.ajout ();
    }
    m.mettreAJour ();
  }

  public String initQuestionType (final Modele<QuestionType> m,
      final GerantTransactions gt, final String doss) {
    try {
      for (final QuestionType qt : QuestionType.staticInstance (gt).requete ()) {
        qt.setGT (gt);
        qt.suppression ();
      }

      m.mettreAJour ();
      return Indexation.indexerStatic (doss,
          "fr.unice.ent.eve.modele.questions.types", //$NON-NLS-1$
          "Question").toString (); //$NON-NLS-1$
    } catch (final InstantiationException e) {
      return new String ();
    } catch (final IllegalAccessException e) {
      return new String ();
    } catch (final ClassNotFoundException e) {
      return new String ();
    }
  }

  public void initSondageType (final Modele<SondageType> m,
      final GerantTransactions gt, final Messages mess) {
    for (final SondageType c : SondageType.staticInstance (gt).requete ()) {
      c.setGT (gt);
      c.suppression ();
    }

    m.mettreAJour ();

    int i = 0;
    while (mess.getString ("type[" + i + "].nom").charAt (0) != '!') //$NON-NLS-1$//$NON-NLS-2$
    {
      final SondageType st = new SondageType (gt,
          mess.getString ("type[" + i + "].nom")); //$NON-NLS-1$//$NON-NLS-2$
      st.ajout ();
      i++;
    }
    m.mettreAJour ();
  }

  @SuppressWarnings ("unchecked")
  @DefaultHandler
  public Resolution reinit () {
    String res = new String ();
    String initCommandes;
    String initEtats;
    String initQuestionType;

    @SuppressWarnings ("rawtypes")
    final Modele m = new Modele (true);
    final GerantTransactions gt = m.getGT ();

    final Resolution r = this.traiter (gt);
    if (r != null) { return r; }

    if (!this.getP ().isSuperAdmin () && false) { return new EveErreur (
        this.getContext ()).lancerErreur (new ActionNonPermiseErreur (
        "initCommandes")); //$NON-NLS-1$
    }

    final String doss = this
        .getContext ()
        .getRequest ()
        .getPathTranslated ()
        .substring (
            0,
            this.getContext ().getRequest ().getPathTranslated ().length () - 19)
        + "WEB-INF/classes/"; //$NON-NLS-1$

    initCommandes = this.initCommandes (m, gt, doss);

    if (initCommandes.length () == 0) {
      new EveErreur (this.getContext ()).lancerErreur (new CommandeErreur (
          "initCommandes")); //$NON-NLS-1$
    }

    res += initCommandes;
    res += "<br/>"; //$NON-NLS-1$

    initEtats = this.initEtats (m, gt, doss);

    if (initEtats.length () == 0) {
      new EveErreur (this.getContext ()).lancerErreur (new CommandeErreur (
          "initEtats")); //$NON-NLS-1$
    }

    res += initEtats;
    res += "<br/>"; //$NON-NLS-1$

    initQuestionType = this.initQuestionType (m, gt, doss);

    if (initQuestionType.length () == 0) {
      new EveErreur (this.getContext ()).lancerErreur (new CommandeErreur (
          "initQuestionType")); //$NON-NLS-1$
    }

    res += initQuestionType;

    Messages mess = new Messages ("ContrainteCommande", Locale.getDefault ()); //$NON-NLS-1$

    this.initContrainteCommande (m, gt, mess);

    mess = new Messages ("SondageType", Locale.getDefault ()); //$NON-NLS-1$

    this.initSondageType (m, gt, mess);

    mess = new Messages ("EveConfig", Locale.getDefault ()); //$NON-NLS-1$

    this.initEveConfig (m, gt, mess);

    return new RedirectResolution ("/", true); //$NON-NLS-1$
  }
}
