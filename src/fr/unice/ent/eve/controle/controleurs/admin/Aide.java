package fr.unice.ent.eve.controle.controleurs.admin;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.SitePartie;

public class Aide extends ActionBeanTraitant {

  public Aide () {
  }

  @DefaultHandler
  public Resolution afficher () {
    final Modele<SitePartie> m = new Modele<SitePartie> (true);
    final GerantTransactions gt = m.getGT ();

    final Resolution r = this.traiter (gt);
    if (r != null) { return r; }

    return new ForwardResolution ("/vue/jsp/admin/aide.jsp"); //$NON-NLS-1$
  }
}
