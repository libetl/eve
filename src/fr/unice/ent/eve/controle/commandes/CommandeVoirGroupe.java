package fr.unice.ent.eve.controle.commandes;

import java.util.ArrayList;
import java.util.Collections;

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
import fr.unice.ent.eve.vue.VueSondages;

/**
 * Commande pour acceder a l'interface utilisateur d'un groupe
 * 
 * @author benychou
 * 
 */
public class CommandeVoirGroupe extends AbstractCommande {

  public CommandeVoirGroupe () {
    super ();
  }

  public CommandeVoirGroupe (final Boolean b) {
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

    final ArrayList<Commandes> lcp = new ArrayList<Commandes> ();
    for (final ContrainteCommande cC : g.getContrainte ()
        .getContrainteCommandes ()) {
      lcp.add (cC.getCommande ());
    }

    Collections.sort (lcp);

    final ArrayList<Sondage> lS = new ArrayList<Sondage> ();
    for (final GroupeSondage groupeSondage : g.getGs ()) {
      final Sondage s = groupeSondage.getS ();
      s.setGT (gt);
      if (s.isOuvert () || s.isBloque ()) {
        final Sondage stmp = (Sondage) gt.getSession ().load (Sondage.class,
            new Long (s.getId ()));
        lS.add (stmp);
      }
    }
    Collections.sort (lS);

    final Object[] resultat = new Object[2];

    resultat[0] = g.getNom ();

    resultat[1] = VueSondages.vue (g, lS.iterator (), lcp, abt.getP (),
        "utilisateur"); //$NON-NLS-1$

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
        Commandes.staticInstance (m.getGT ())
            .getParNom ("VoirGroupe").getPartie ().getPartie () + //$NON-NLS-1$
        "/VoirGroupe", true); //$NON-NLS-1$
  }
}
