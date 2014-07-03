package fr.unice.ent.eve.controle.ws;

import java.lang.reflect.Method;
import java.util.Locale;

import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.controle.erreurs.ServiceWebException;

/**
 * Classe d'invocation d'un service Web
 * 
 * @author benychou
 * 
 */
public class InvocateurWS {
  private Class<?>                       classe;
  private Method[]                       dm;
  private final Messages                 m;
  private Object                         port;
  private org.apache.axis.client.Service service;

  /**
   * Construit une instance de classe d'invocation a partir d'un nom de classe
   * cliente a appeller, d'une adresse url et d'un Locale pour l'affichage
   * d'erreurs
   * 
   * @author benychou
   * 
   */
  public InvocateurWS (final Locale l, final String nomClasse,
      final String endpoint) throws ServiceWebException {

    final String[] exp = nomClasse.split ("\\."); //$NON-NLS-1$
    final String nomService = exp[exp.length - 1];

    // Make a service
    try {
      this.classe = Class.forName (nomClasse + "ServiceLocator"); //$NON-NLS-1$
    } catch (final java.lang.ClassNotFoundException e) {
      throw new ServiceWebException (e.toString ());
    }

    try {
      this.service = (org.apache.axis.client.Service) this.classe
          .newInstance ();
    } catch (final java.lang.InstantiationException e) {
      throw new ServiceWebException (e.toString ());
    } catch (final java.lang.IllegalAccessException e) {
      throw new ServiceWebException (e.toString ());
    }

    final Class<?>[] pt = new Class<?>[1];
    try {
      pt[0] = Class.forName ("java.lang.String"); //$NON-NLS-1$
    } catch (final java.lang.ClassNotFoundException e) {
      throw new ServiceWebException (e.toString ());
    }

    final String[] params = new String[1];
    params[0] = endpoint;

    Method sSEpA = null;

    this.m = new Messages (this.getClass ().getName (), l);

    try {
      sSEpA = this.classe
          .getMethod ("set" + nomService + "EndpointAddress", pt); //$NON-NLS-1$ //$NON-NLS-2$
      sSEpA.invoke (this.service, (java.lang.Object[]) params);
    } catch (final java.lang.NullPointerException e) {
      throw new ServiceWebException (e.toString ());
    } catch (final java.lang.NoSuchMethodException e) {
      throw new ServiceWebException (e.toString ());
    } catch (final java.lang.IllegalAccessException e) {
      throw new ServiceWebException (e.toString ());
    } catch (final java.lang.reflect.InvocationTargetException e) {
      throw new ServiceWebException (e.toString ());
    }

    try {
      this.port = this.service.getPort (Class.forName (nomClasse));

      this.classe = Class.forName (nomClasse);
      this.dm = this.classe.getDeclaredMethods ();

    } catch (final javax.xml.rpc.ServiceException e) {
      throw new ServiceWebException (
          this.m.getString ("InvocateurWS.5") + "(" + e.toString () + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } catch (final java.lang.ClassNotFoundException e) {
      throw new ServiceWebException (
          this.m.getString ("InvocateurWS.6") + "(" + e.toString () + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
  }

  /**
   * Procede a l'invocation du service web
   * 
   * @param methode
   *          nom de la methode du service web a appeller
   * @param params
   *          parametres de la methode
   * @return des donnees au format brut
   * @throws ServiceWebException
   */
  public Object[] invoquer (final String methode, final Object[] params)
      throws ServiceWebException {
    Object[] o = new Object[2];

    try {
      int i = 0;
      boolean appel = false;
      while ((i < this.dm.length) && !appel) {
        if (this.dm[i].toString ().contains (methode)) {
          appel = true;
        } else {
          i++;
        }
      }

      if (i == this.dm.length) { throw new java.lang.NoSuchMethodException (); }

      final Method methodeClasse = this.dm[i];

      o = (Object[]) methodeClasse.invoke (this.port, params);

    } catch (final java.lang.NoSuchMethodException e) {
      throw new ServiceWebException (
          this.m.getString ("InvocateurWS.7") + "(" + e.toString () + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } catch (final java.lang.IllegalAccessException e) {
      throw new ServiceWebException (
          this.m.getString ("InvocateurWS.8") + "(" + e.toString () + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } catch (final java.lang.IllegalArgumentException e) {
      throw new ServiceWebException (
          this.m.getString ("InvocateurWS.9") + "(" + e.toString () + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } catch (final java.lang.reflect.InvocationTargetException e) {
      if (e.getCause () != null) { throw new ServiceWebException (
          this.m.getString ("InvocateurWS.10") + "(" + e.toString () //$NON-NLS-1$ //$NON-NLS-2$
              + ")<br/>--&gt;" + e.getCause ().toString ()); //$NON-NLS-1$ 
      }
      throw new ServiceWebException (
          this.m.getString ("InvocateurWS.10") + "(" + e.toString () + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$        
    }
    return o;
  }

  /**
   * Message informant de la mauvaise syntaxe de l'appel
   * 
   * @return un String
   */
  public String messageChaineIncorrecte () {
    return this.m.getString ("InvocateurWS.13"); //$NON-NLS-1$
  }
}
