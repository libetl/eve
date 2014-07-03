package fr.unice.ent.eve.controle.commandes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.controleurs.erreurs.EveErreur;
import fr.unice.ent.eve.controle.erreurs.ParametreErreur;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.commandes.Commandes;
import fr.unice.ent.eve.modele.commandes.ContrainteCommande;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.sondages.GroupeSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.vue.VueActionsGroupe;
import fr.unice.ent.eve.vue.VueSondages;

/**
 * Commande pour acceder a l'interface Admin d'un groupe
 * 
 * @author benychou
 * 
 */
public class CommandeVoirGroupeAdmin extends AbstractCommande {

  public CommandeVoirGroupeAdmin () {
    super ();
  }

  public CommandeVoirGroupeAdmin (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    if (abt.getContext ().getRequest ().getSession ().getAttribute ("g") == null)//$NON-NLS-1$
    { return new EveErreur (abt.getContext ())
        .lancerErreur (new ParametreErreur ("g")); //$NON-NLS-1$
    }

    final Groupe g = (Groupe) gt.getSession ().load (
        Groupe.class,
        new Long ((String) abt.getContext ().getRequest ().getSession ()
            .getAttribute ("g"))); //$NON-NLS-1$

    final Resolution r = abt.traiter (gt);
    if (r != null) { return r; }

    final ArrayList<Sondage> lS = new ArrayList<Sondage> ();
    for (final GroupeSondage groupeSondage : g.getGs ()) {
      Sondage s = (Sondage) gt.getSession ().load (Sondage.class,
          new Long (groupeSondage.getS ().getId ()));
      s.setGT (gt);

      if (s.isBloque ()) {
        s = (Sondage) gt.getSession ().load (Sondage.class,
            new Long (groupeSondage.getS ().getId ()));
      }

      lS.add (s);
    }
    Collections.sort (lS);

    final Object[] resultat = new Object[3];
    final ArrayList<Commandes> lcp = new ArrayList<Commandes> ();

    if (abt.getP ().isSuperAdmin ()) {
      lcp.addAll (Commandes.staticInstance (gt).getParNiveau (2));
      lcp.addAll (Commandes.staticInstance (gt).getParNiveau (3));
    } else {
      for (final ContrainteCommande cC : g.getContrainte ()
          .getContrainteCommandes ()) {
        lcp.add (cC.getCommande ());
      }
    }

    Collections.sort (lcp);

    final Iterator<Commandes> itc = lcp.iterator ();

    resultat[0] = g.getNom ();

    resultat[1] = VueActionsGroupe.vue (g, itc, abt.getP ());

    resultat[2] = VueSondages.vue (g, lS.iterator (), lcp, abt.getP ());

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
        Commandes.staticInstance (m.getGT ())
            .getParNom ("VoirGroupeAdmin").getPartie ().getPartie () + //$NON-NLS-1$
        "/VoirGroupeAdmin", true); //$NON-NLS-1$
  }
}
