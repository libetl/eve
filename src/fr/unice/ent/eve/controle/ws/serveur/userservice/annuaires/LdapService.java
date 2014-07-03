package fr.unice.ent.eve.controle.ws.serveur.userservice.annuaires;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import fr.unice.ent.eve.controle.bundlemessages.Messages;

/**
 * Sous service d'interrogation de Ldap
 */
public class LdapService implements AbstractService {

  private static final String CHAMP = "supannAliasLogin"; //$NON-NLS-1$

  private String              ldapaddress;
  private String              ldapCtxFactory;
  private String              ldapgroupBaseDN;
  private String              ldapManagerDN;
  private String              ldapManagerPW;
  private String              ldapname;
  private LdapServer          ldapServer;

  private String              ldapuiddefaut;

  /**
   * Constructeur vide
   */
  public LdapService () {
  }

  @Override
  public Object[] concreteQuery (final Messages mess, final String query) {

    this.ldapname = mess.getString ("ldap_name"); //$NON-NLS-1$
    this.ldapaddress = mess.getString ("ldap_address"); //$NON-NLS-1$
    this.ldapgroupBaseDN = mess.getString ("ldap_groupBaseDN"); //$NON-NLS-1$
    this.ldapuiddefaut = mess.getString ("ldap_uid_defaut"); //$NON-NLS-1$
    this.ldapManagerDN = mess.getString ("ldap_ManagerDN"); //$NON-NLS-1$
    this.ldapManagerPW = mess.getString ("ldap_ManagerPW"); //$NON-NLS-1$
    this.ldapCtxFactory = mess.getString ("ldap_CtxFactory"); //$NON-NLS-1$

    this.ldapServer = new LdapServer (this.ldapname, this.ldapaddress,
        this.ldapgroupBaseDN, this.ldapuiddefaut, this.ldapManagerDN,
        this.ldapManagerPW, this.ldapCtxFactory);

    // Checks to make sure the argument & state is valid
    if (query == null) { throw new IllegalArgumentException (
        "The query String cannot be null."); //$NON-NLS-1$
    }

    if (this.ldapServer == null) { throw new IllegalStateException (
        "ILdapServer is null"); //$NON-NLS-1$
    }

    // Connect to the LDAP server
    DirContext context = null;
    try {

      context = this.ldapServer.getConnection ();

    } catch (final javax.naming.NamingException e) {
      final Object[] o = new Object[2];
      o[0] = "NamingException"; //$NON-NLS-1$
      o[1] = e.getMessage ();
      return o;
    }

    if (context == null) {
      final Object[] o = new Object[1];
      o[0] = this.ldapgroupBaseDN;
      return o;
    }

    final SearchControls sc = new SearchControls ();
    sc.setSearchScope (SearchControls.SUBTREE_SCOPE);

    // Search the LDAP
    NamingEnumeration<SearchResult> grouplist = null;

    try {
      grouplist = context.search (this.ldapgroupBaseDN, query, sc);
    } catch (final javax.naming.NamingException e) {
      final Object[] o = new Object[2];
      o[0] = "NamingException"; //$NON-NLS-1$
      o[1] = e.getMessage ();
      return o;
    }

    if (grouplist == null) { throw new IllegalStateException ("Result is null"); //$NON-NLS-1$
    }

    final Object[] res = this.recupererChamp (grouplist, LdapService.CHAMP);

    this.ldapServer.releaseConnection (context);
    return res;
  }

  @SuppressWarnings ("unchecked")
  private Object[] recupererChamp (
      final NamingEnumeration<SearchResult> grouplist, final String champ) {
    int i = 0;
    final ArrayList<Object> al = new ArrayList<Object> ();
    while (grouplist.hasMoreElements ()) {
      try {
        final SearchResult sr = grouplist.next ();
        final Attributes attrs = sr.getAttributes ();
        final Attribute att = attrs.get (champ);
        final ArrayList<Object> al2 = new ArrayList<Object> ();
        final Object[] prop = new Object[2];
        final ArrayList<String> vals = new ArrayList<String> ();
        prop[0] = "" + i++; //$NON-NLS-1$
        final Enumeration<Object> values = (Enumeration<Object>) att.getAll ();
        while (values.hasMoreElements ()) {
          vals.add (values.nextElement ().toString ());
        }
        prop[1] = vals.toArray ();
        al2.add (prop);
        al.add (al2.toArray ());
      } catch (final javax.naming.NamingException e) {
        final Object[] o = new Object[2];
        o[0] = "NamingException"; //$NON-NLS-1$
        o[1] = e.getMessage ();
        return o;
      }
    }
    return al.toArray ();
  }

  @SuppressWarnings ({ "unchecked", "unused" })
  private Object[] recupererTout (
      final NamingEnumeration<SearchResult> grouplist) {
    final ArrayList<Object> al = new ArrayList<Object> ();
    while (grouplist.hasMoreElements ()) {
      try {
        final SearchResult sr = grouplist.next ();
        final Attributes attrs = sr.getAttributes ();
        final NamingEnumeration<Attribute> memberlist = (NamingEnumeration<Attribute>) attrs
            .getAll ();
        final ArrayList<Object> al2 = new ArrayList<Object> ();
        while (memberlist.hasMoreElements ()) {
          final Attribute att = memberlist.next ();
          final Object[] prop = new Object[2];
          final ArrayList<String> vals = new ArrayList<String> ();
          prop[0] = att.getID ();
          final Enumeration<Object> values = (Enumeration<Object>) att
              .getAll ();
          while (values.hasMoreElements ()) {
            vals.add (values.nextElement ().toString ());
          }
          prop[1] = vals.toArray ();
          al2.add (prop);
        }
        al.add (al2.toArray ());
      } catch (final javax.naming.NamingException e) {
        final Object[] o = new Object[2];
        o[0] = "NamingException"; //$NON-NLS-1$
        o[1] = e.getMessage ();
        return o;
      }
    }
    return al.toArray ();
  }
}
