package fr.unice.ent.eve.controle.commandes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.CommandeErreur;
import fr.unice.ent.eve.controle.ws.EntreeWS;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.GroupeContrainte;
import fr.unice.ent.eve.vue.VueGroupes;

/**
 * Commande permettant d'afficher les groupes et les actions 'utilisateur'
 * possibles
 * 
 * @author benychou
 * 
 */
public class CommandeAfficherGroupes extends AbstractCommande {

  public CommandeAfficherGroupes () {
    super ();
  }

  public CommandeAfficherGroupes (final Boolean b) {
    super (b);
  }

  @SuppressWarnings ("unchecked")
  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    final Resolution r = abt.traiter (gt);

    if (r != null) { return r; }

    if (abt.getDonneesGroupes () != null) {
      for (int i = 0 ; i < abt.getDonneesGroupes ().size () ; i++) {
        final EntreeWS donnees = abt.getDonneesGroupes ().get (i);
        for (final String string : donnees.keySet ()) {
          final String nomGr = ((ArrayList<String>) donnees.get (string))
              .get (0);

          abt.getContext ().getRequest ().getSession ()
              .setAttribute ("GestionGroupe_nomGroupe", nomGr);//$NON-NLS-1$ 
          abt.getContext ()
              .getRequest ()
              .getSession ()
              .setAttribute (
                  "GestionGroupe_contrainte",//$NON-NLS-1$ 
                  new Integer (GroupeContrainte.staticInstance (gt)
                      .getDefault ().getId ()));

          final String lnCmd = "AjouterGroupe\n0\n0"; //$NON-NLS-1$
          final String[] permissions = new String[1];
          permissions[0] = "Appartenir"; //$NON-NLS-1$
          try {
            InvocateurCommande.lancer (lnCmd, m, abt, permissions);
          } catch (final ClassNotFoundException e) {
            return new EveErreur (abt.getContext ())
                .lancerErreur (new CommandeErreur (lnCmd));
          } catch (final InstantiationException e) {
            return new EveErreur (abt.getContext ())
                .lancerErreur (new CommandeErreur (lnCmd));
          } catch (final IllegalAccessException e) {
            return new EveErreur (abt.getContext ())
                .lancerErreur (new CommandeErreur (lnCmd));
          } catch (final InvocationTargetException e) {
            return new EveErreur (abt.getContext ())
                .lancerErreur (new CommandeErreur (lnCmd));
          } catch (final NoSuchMethodException e) {
            return new EveErreur (abt.getContext ())
                .lancerErreur (new CommandeErreur (lnCmd));
          }
        }
      }

      m.mettreAJour ();
    }

    final List<Groupe> lg = abt.getP ().groupes ();

    Collections.sort (lg);

    final Object[] resultat = new Object[2];

    resultat[0] = GroupeContrainte.staticInstance (gt).requete ();

    resultat[1] = VueGroupes.vueUtilisateur (lg.iterator (), abt.getP ());

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
        Commandes.staticInstance (m.getGT ())
            .getParNom ("AfficherGroupes").getPartie ().getPartie () + //$NON-NLS-1$
        "/AfficherGroupes", true); //$NON-NLS-1$
  }
}
