package fr.unice.ent.eve.controle.exceptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.StripesConstants;
import fr.unice.ent.eve.modele.GerantTransactions;

/**
 * Classe de prise en main des exceptions Provoque la reconnexion si la liaison
 * avec la base de donnees est rompue.
 * 
 * @author benychou
 * 
 */
public class ExceptionHandler implements
    net.sourceforge.stripes.exception.ExceptionHandler {

  public ExceptionHandler () {
    super ();
  }

  /**
   * Methode de prise en main des exceptions
   * 
   * @throws Exception
   * 
   * @see net.sourceforge.stripes.exception.ExceptionHandler#handle(java.lang.Throwable,
   *      javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  @Override
  public void handle (final Throwable arg0, final HttpServletRequest arg1,
      final HttpServletResponse arg2) throws ServletException {
    if (arg0.getClass ().getName ().contains ("JDBC") ||
    		arg0.getClass ().getName ().contains ("mongodb")) {
      final ActionBean bean = (ActionBean) arg1
          .getAttribute (StripesConstants.REQ_ATTR_ACTION_BEAN);
      GerantTransactions.reconstruire ();
      GerantTransactions.annuler ();
      try {
        bean.getContext ().getSourcePageResolution ().execute (arg1, arg2);
      } catch (final Exception e) {
        throw new ServletException (e);
      }
    }
    throw new ServletException (arg0);
  }

  /**
   * @see net.sourceforge.stripes.config.ConfigurableComponent#init(net.sourceforge.stripes.config.Configuration)
   */
  @Override
  public void init (final Configuration arg0) throws Exception {
    arg0.toString ();
  }

}
