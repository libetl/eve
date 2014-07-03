/**
 * 
 */
package fr.unice.ent.eve.controle.commandes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseErreur;
import fr.unice.ent.eve.controle.erreurs.ActionNonPermiseException;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.controle.erreurs.ServiceWebException;
import fr.unice.ent.eve.controle.ws.AppelWS;
import fr.unice.ent.eve.controle.ws.EntreeWS;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.config.EveConfig;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Permission;
import fr.unice.ent.eve.modele.groupes.Personne;
import fr.unice.ent.eve.modele.services.ServiceAuth;
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.vue.VuePersonne;

/**
 * Commande d'envoi de notification d'un sondage aux etudiants cibles.
 * 
 * @author benychou
 * 
 */
public class CommandeEnvoyerCourriel extends AbstractCommande {

  private Messages mess;

  public CommandeEnvoyerCourriel () {
    super ();
  }

  public CommandeEnvoyerCourriel (final Boolean b) {
    super (b);
  }

  private void afficherListe (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Groupe g, final Sondage s) {
    final List<Personne> lp = new ArrayList<Personne> ();
    final HashMap<String, EntreeWS> uidCourriel = new HashMap<String, EntreeWS> ();

    for (final Permission p : s.getPermissions ()) {
      if (p.getNaturePermission ().getNomPermission ().equals ("Voter")) //$NON-NLS-1$
      {
        lp.add (p.getPersonne ());
        try {
          final EntreeWS ews = AppelWS.appel (abt.getContext ().getLocale (),
              ServiceAuth.getGroupsService (gt),
              "getUser " + p.getPersonne ().getValeurUid ()).get (1); //$NON-NLS-1$
          uidCourriel.put (p.getPersonne ().getValeurUid (), ews);
        } catch (final ServiceWebException e) {
          e.printStackTrace ();
        }
      }
    }

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("uidCourriel", uidCourriel); //$NON-NLS-1$

    final Object[] resultat = new Object[4];

    resultat[0] = g.getNom ();

    resultat[1] = s.getTitre ();

    resultat[2] = VuePersonne.vue (lp.iterator (), uidCourriel);

    resultat[3] = this.mess.getString ("CommandeEnvoyerCourriel.1"); //$NON-NLS-1$

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$
  }

  @SuppressWarnings ("unchecked")
  private Resolution envoi (final ActionBeanTraitant abt,
      final GerantTransactions gt, final Groupe g, final Sondage s) {

    if (abt.getContext ().getRequest ().getSession ()
        .getAttribute ("GestionCourriels_uid") != null) //$NON-NLS-1$
    {
      final Properties props = System.getProperties ();
      props.put ("mail.smtp.host", //$NON-NLS-1$
          EveConfig.staticInstance (gt).get ("smtp_serveur")); //$NON-NLS-1$
      final Session session1 = Session.getDefaultInstance (props, null);
      final Message message = new MimeMessage (session1);

      try {
        final String uid = (String) abt.getContext ().getRequest ()
            .getSession ().getAttribute ("GestionCourriels_uid"); //$NON-NLS-1$
        abt.getContext ().getRequest ().getSession ()
            .removeAttribute ("GestionCourriels_uid"); //$NON-NLS-1$
        final HashMap<String, EntreeWS> entree = (HashMap<String, EntreeWS>) abt
            .getContext ().getRequest ().getSession ()
            .getAttribute ("uidCourriel"); //$NON-NLS-1$
        final String adresse = ((ArrayList<String>) entree.get (uid).get (
            "mail")).get (0); //$NON-NLS-1$

        String texte = (String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("GestionCourriels_texteM"); //$NON-NLS-1$

        texte = this.replaceAllWords (texte, "<titreSondage>", s.getTitre ()); //$NON-NLS-1$
        texte = this.replaceAllWords (texte, "<nomGroupe>", g.getNom ()); //$NON-NLS-1$
        texte = this.replaceAllWords (texte, "<adresse>", //$NON-NLS-1$ 
            "http://" + abt.getContext ().getRequest ().getServerName () //$NON-NLS-1$ 
                + abt.getContext ().getRequest ().getContextPath ()
                + "/controleurs/utilisateur/sondage/Voter?g=" //$NON-NLS-1$
                + g.getId () + "&s=" + s.getId ()); //$NON-NLS-1$
        texte = this.replaceAllWords (texte,
            "<auteur>", (String) abt.getContext ().getRequest () //$NON-NLS-1$
                .getSession ().getAttribute ("nomAffichage")); //$NON-NLS-1$

        message.setFrom (new InternetAddress (((ArrayList<String>) abt
            .getDonneesPerso ().get ("mail")).get (0))); //$NON-NLS-1$
        message.setRecipients (Message.RecipientType.TO,
            InternetAddress.parse (adresse, false));
        message.setHeader ("Content-type", //$NON-NLS-1$
            "text/html; charset=ISO-8859-1"); //$NON-NLS-1$
        message.setSubject (this.mess.getString ("CommandeEnvoyerCourriel.2") //$NON-NLS-1$
            + s.getTitre ());
        message.setText (texte);
        message.setSentDate (new Date ());
        Transport.send (message);
      } catch (final AddressException e) {
        return new StreamingResolution ("text/html", new String ()); //$NON-NLS-1$

      } catch (javax.mail.MessagingException e) {
        return new StreamingResolution ("text/html", new String ()); //$NON-NLS-1$
      }
      gt.toString ();
      g.toString ();
      s.toString ();
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

    if (abt.getContext ().getRequest ().getSession ().getAttribute ("g") == null)//$NON-NLS-1$
    { return new EveErreur (abt.getContext ())
        .lancerErreur (new ParametreErreur ("g")); //$NON-NLS-1$
    }

    if (abt.getContext ().getRequest ().getSession ().getAttribute ("s") == null)//$NON-NLS-1$
    { return new EveErreur (abt.getContext ())
        .lancerErreur (new ParametreErreur ("s")); //$NON-NLS-1$
    }

    final Groupe g = (Groupe) gt.getSession ().load (
        Groupe.class,
        new Long ((String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("g"))); //$NON-NLS-1$

    final Sondage s = (Sondage) gt.getSession ().load (
        Sondage.class,
        new Long ((String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("s"))); //$NON-NLS-1$

    this.mess = new Messages (
        "fr.unice.ent.eve.controle.commandes.CommandeEnvoyerCourriel", //$NON-NLS-1$
        abt.getContext ().getLocale ());

    try {
      s.envoyerCourriel ();
    } catch (final ActionNonPermiseException e) {
      return new EveErreur (abt.getContext ())
          .lancerErreur (new ActionNonPermiseErreur ("EnvoyerCourriel")); //$NON-NLS-1$
    }

    r = this.envoi (abt, gt, g, s);
    if (r != null) { return r; }

    this.afficherListe (abt, gt, g, s);
    return null;
  }

  private String replaceAllWords (final String original, final String find,
      final String replacement) {
    final StringBuilder result = new StringBuilder (original.length ());
    final String delimiters = "+-*/(),.'\t\n "; //$NON-NLS-1$
    final StringTokenizer st = new StringTokenizer (original, delimiters, true);
    while (st.hasMoreTokens ()) {
      final String w = st.nextToken ();
      if (w.equals (find)) {
        result.append (replacement);
      } else {
        result.append (w);
      }
    }
    return result.toString ();
  }

}
