package fr.unice.ent.eve.controle.controleurs.tests;

import java.io.StringReader;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.EtatInexistantErreur;
import fr.unice.ent.eve.controle.erreurs.EtatInexistantException;
import fr.unice.ent.eve.controle.etats.AbstractEtatSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class TestEtats implements ActionBean {

  private ActionBeanContext context;

  public TestEtats () {
    super ();
  }

  @Override
  public ActionBeanContext getContext () {
    return this.context;
  }

  public Resolution modifierQuestions () {
    try {
      new Sondage (true).modifierQuestions ();
    } catch (final EtatInexistantException e) {
      return new EveErreur (this.getContext ())
          .lancerErreur (new EtatInexistantErreur (e.getMessage ()));
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (this.getContext ())
          .lancerErreur (new ActionNonPermiseErreur (e.getMessage ()));
    }
    return new StreamingResolution (
        "text/html", new StringReader ("Tout est bon.")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public void setContext (final ActionBeanContext c) {
    this.context = c;
  }

  public Resolution sondageEtatInexistant () {
    try {
      AbstractEtatSondage.get ("EtatInexistant"); //$NON-NLS-1$
    } catch (final EtatInexistantException e) {
      return new EveErreur (this.getContext ())
          .lancerErreur (new EtatInexistantErreur (e.getMessage ()));
    }
    return new StreamingResolution (
        "text/html", new StringReader ("Tout est bon.")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public Resolution sondageVide () {
    try {
      AbstractEtatSondage.get ("Vide"); //$NON-NLS-1$
    } catch (final EtatInexistantException e) {
      return new EveErreur (this.getContext ())
          .lancerErreur (new EtatInexistantErreur (e.getMessage ()));
    }
    return new StreamingResolution (
        "text/html", new StringReader ("Tout est bon.")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public Resolution voter () {
    try {
      new Sondage (true).voter ();
    } catch (final EtatInexistantException e) {
      return new EveErreur (this.getContext ())
          .lancerErreur (new EtatInexistantErreur (e.getMessage ()));
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (this.getContext ())
          .lancerErreur (new ActionNonPermiseErreur (e.getMessage ()));
    }
    return new StreamingResolution (
        "text/html", new StringReader ("Tout est bon.")); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
