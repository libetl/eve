/*
 * Copyright 2001, 2004 The JA-SIG Collaborative. All rights reserved. See
 * license distributed with this file and available online at
 * http://www.uportal.org/license.html
 */

package fr.unice.ent.eve.controle.ws.serveur.userservice.annuaires;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Default implementation of LdapServer.
 * 
 * @author edalquist@unicon.net
 * @author andrew.petro@yale.edu
 * @version $Revision: 1.2 $ $Date: 2004/11/17 19:09:45 $
 */
public class LdapServer {

  private static final String DEFAULT_CXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory"; //$NON-NLS-1$

  /**
   * Returns the chckStr argument unless the chkStr argument is null, in which
   * case returns the defStr (default) argument.
   * 
   * @param chkStr
   *          - String to check
   * @param defStr
   *          - fallback default for when the string to check was null
   * @return chkStr if not null, defStr if chkStr was null
   */
  private static String checkNull (final String chkStr, final String defStr) {
    if (chkStr == null) { return defStr; }
    return chkStr;
  }

  /**
   * Construct a URL for an LDAP server.
   * 
   * @param host
   * @param port
   * @param useSsl
   * @return URL constructed from the arguments
   */
  private static String constructLdapUrl (final String host, final String port,
      final boolean useSsl) {

    if (host == null) { throw new IllegalArgumentException (
        "host cannot be null."); //$NON-NLS-1$
    }

    final String port2 = LdapServer.checkNull (port, "389"); //$NON-NLS-1$

    int portNum;
    try {
      portNum = Integer.parseInt (port2);
    } catch (final NumberFormatException nfe) {
      throw new IllegalArgumentException ("port, if specified, must be a " + //$NON-NLS-1$
          "positive integer.  It had invalid value [" + port + "]"); //$NON-NLS-1$//$NON-NLS-2$
    }

    if (portNum < 1) { throw new IllegalArgumentException (
        "port, if specified, must be " + //$NON-NLS-1$
            "positive.  It had invalid value " + portNum); //$NON-NLS-1$
    }

    final StringBuffer urlBuffer = new StringBuffer ();
    if (useSsl) {
      urlBuffer.append ("ldaps://"); //$NON-NLS-1$
    } else {
      urlBuffer.append ("ldap://"); //$NON-NLS-1$
    }

    urlBuffer.append (host).append (":").append (port); //$NON-NLS-1$

    return urlBuffer.toString ();
  }

  /**
   * Environment, populated at construction.
   */
  private final Hashtable<Object, String> env = new Hashtable<Object, String> (
                                                  5, 0.75f);

  private final String                    ldapBaseDn;

  /**
   * Name of this LDAP server for identification and debugging purposes.
   */
  private final String                    ldapName;

  /**
   * The attribute by which we query using uids.
   */
  private final String                    ldapUidAttribute;

  private final Log                       log = LogFactory.getLog (this
                                                  .getClass ());

  /**
   * Instantiate an LdapServerImpl using somewhat abtstracted configuration over
   * that present in the more detailed constructor. You communicate a desire to
   * use SSL to this method by using an ldaps//... URL rather than an ldap//...
   * url.
   * 
   * @param name
   *          mnemonic name for this Ldap server instance
   * @param url
   *          URL of LDAP server
   * @param baseDn
   * @param uidAttribute
   *          attribute against which to match for uid queries
   * @param managerDn
   *          principal for LDAP authentication, null implies no authentication
   * @param managerPw
   *          password for LDAP authentication, null implies no authentication
   * @param initialContextFactory
   *          the name of the class to use to instantiate the context.
   */
  public LdapServer (final String name, final String url, final String baseDn,
      final String uidAttribute, final String managerDn,
      final String managerPw, final String initialContextFactory) {

    if (name == null) { throw new IllegalArgumentException (
        "name cannot be null."); //$NON-NLS-1$
    }

    this.ldapName = name;

    this.ldapBaseDn = LdapServer.checkNull (baseDn, new String ());
    this.ldapUidAttribute = LdapServer.checkNull (uidAttribute, new String ());

    final String managerDn2 = LdapServer.checkNull (managerDn, new String ());
    final String managerPw2 = LdapServer.checkNull (managerPw, new String ());
    final String initialContextFactory2 = LdapServer.checkNull (
        initialContextFactory, LdapServer.DEFAULT_CXT_FACTORY);

    // parse the URL parameter to detect SSL -- in case of SSL
    // fix the URL and set the useSsl flag.

    if (url.startsWith ("ldaps")) { // Handle SSL connections //$NON-NLS-1$
      // remove the 's' from "ldaps"
      url.replaceAll ("ldaps:", "ldap:"); //$NON-NLS-1$ //$NON-NLS-2$
      this.env.put (Context.SECURITY_PROTOCOL, "ssl"); //$NON-NLS-1$
    }

    this.env.put (Context.INITIAL_CONTEXT_FACTORY, initialContextFactory2);

    this.env.put (Context.PROVIDER_URL, url);
    this.env.put (Context.SECURITY_AUTHENTICATION, "simple"); //$NON-NLS-1$
    this.env.put (Context.SECURITY_PRINCIPAL, managerDn2);
    this.env.put (Context.SECURITY_CREDENTIALS, managerPw2);

    if (this.log.isDebugEnabled ()) {
      this.log.debug ("Instantiated: [" + this + "]"); //$NON-NLS-1$//$NON-NLS-2$
    }
  }

  /**
   * Instantiate an LdapServerImpl with the given low-level configuration. Using
   * this constructor has the advantage of doing some argument checking on the
   * port number.
   * 
   * @param name
   *          mnemonic name for this Ldap server instance
   * @param host
   *          host of the LDAP server
   * @param port
   *          port number. null implies default of 389
   * @param baseDn
   * @param uidAttribute
   *          attribute against which to match for uid queries
   * @param managerDn
   *          principal for LDAP authentication, null implies no authentication
   * @param managerPw
   *          password for LDAP authentication
   * @param useSsl
   *          true if we should use SSL, false otherwise.
   * @param initialContextFactory
   *          name of the class to use for building init context, null defaults
   *          to com.sun.jndi.ldap.LdapCtxFactory
   * @throws IllegalArgumentException
   *           when arguments do not specify valid server
   */
  public LdapServer (final String name, final String host, final String port,
      final String baseDn, final String uidAttribute, final String managerDn,
      final String managerPw, final boolean useSsl,
      final String initialContextFactory) {

    this (name, LdapServer.constructLdapUrl (host, port, useSsl), baseDn,
        uidAttribute, managerDn, managerPw, initialContextFactory);
  }

  public String getBaseDN () {
    return this.ldapBaseDn;
  }

  public DirContext getConnection () throws NamingException {
    return new InitialDirContext (this.env);
  }

  public String getUidAttribute () {
    return this.ldapUidAttribute;
  }

  public void releaseConnection (final DirContext conn) {
    if (conn == null) { return; }

    try {
      conn.close ();
    } catch (final NamingException e) {
      this.log.debug (
          "Error closing the LDAP Connection to server [" + this + "]", e); //$NON-NLS-1$//$NON-NLS-2$
    }
  }

  @Override
  public String toString () {
    final StringBuffer sb = new StringBuffer ();
    sb.append (this.getClass ().getName ());
    sb.append (" name=").append (this.ldapName); //$NON-NLS-1$
    sb.append (" url=").append (this.env.get (Context.PROVIDER_URL)); //$NON-NLS-1$
    sb.append (" uidAttr=").append (this.ldapUidAttribute); //$NON-NLS-1$
    sb.append (" baseDn=").append (this.ldapBaseDn); //$NON-NLS-1$
    if ("ssl".equals (this.env.get (Context.SECURITY_PROTOCOL))) //$NON-NLS-1$
    {
      sb.append (" using SSL"); //$NON-NLS-1$
    }
    if (this.env.get (Context.SECURITY_PRINCIPAL) != null) {
      sb.append (" authentication principal=").append (this.env.get (Context.SECURITY_PRINCIPAL)); //$NON-NLS-1$
    }
    if (this.env.get (Context.SECURITY_CREDENTIALS) != null) {
      sb.append (" password=").append (this.env.get (Context.SECURITY_CREDENTIALS)); //$NON-NLS-1$
    }
    if (!LdapServer.DEFAULT_CXT_FACTORY.equals (this.env
        .get (Context.INITIAL_CONTEXT_FACTORY))) {
      sb.append (" initialContextFactory=").append (this.env.get (Context.INITIAL_CONTEXT_FACTORY)); //$NON-NLS-1$
    }
    return sb.toString ();
  }
}
