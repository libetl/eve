package fr.unice.ent.eve.controle.controleurs.erreurs;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.LocalizableError;
import fr.unice.ent.eve.vue.VueErreur;

public class EveErreur implements ActionBean {

  private ActionBeanContext context;

  public EveErreur (final ActionBeanContext c) {
    this.setContext (c);
  }

  @Override
  public ActionBeanContext getContext () {
    return this.context;
  }

  public Resolution lancerErreur (final LocalizableError le) {
    return new VueErreur (this.context, le).voir ();
  }

  @Override
  public void setContext (final ActionBeanContext c) {
    this.context = c;
  }
}
