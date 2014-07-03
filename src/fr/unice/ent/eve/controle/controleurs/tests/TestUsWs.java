package fr.unice.ent.eve.controle.controleurs.tests;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ServiceWebErreur;
import fr.unice.ent.eve.controle.erreurs.ServiceWebException;
import fr.unice.ent.eve.controle.ws.AppelWS;
import fr.unice.ent.eve.controle.ws.ConsommateurWS;
import fr.unice.ent.eve.modele.services.ServiceAuth;

public class TestUsWs implements ActionBean {
  private ActionBeanContext context;
  private String            requete = ""; //$NON-NLS-1$

  public TestUsWs () {
    super ();
  }

  @Override
  public ActionBeanContext getContext () {
    return this.context;
  }

  public String getRequete () {
    return this.requete;
  }

  @Override
  public void setContext (final ActionBeanContext c) {
    this.context = c;
  }

  public void setRequete (final String r) {
    this.requete = r;
  }

  public Resolution testAppel () {
    try {
      final String result = ConsommateurWS.listeMapsVersString (AppelWS.appel (
          this.context.getLocale (), new ServiceAuth (
              "http://127.0.0.1/Eve-dev/services/UserService", //$NON-NLS-1$
              "fr.unice.ent.eve.controle.ws.client.userservice.UserService"), //$NON-NLS-1$
          this.requete));
      return new StreamingResolution (
          "text/html", //$NON-NLS-1$
          "<html><head><title>Resultat</title><style>body {font-family:Monospace}A {font-family:serif}" //$NON-NLS-1$
              + "</style></head><body><p><a href=\"javascript:history.go (-1)\">&lt; Revenir</a></p>" //$NON-NLS-1$
              + result + "</body></html>"); //$NON-NLS-1$
    } catch (final ServiceWebException e) {
      return new EveErreur (this.context).lancerErreur (new ServiceWebErreur (e
          .getMessage ()));
    }
  }
}
