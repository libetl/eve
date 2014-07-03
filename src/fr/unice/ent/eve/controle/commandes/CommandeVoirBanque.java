package fr.unice.ent.eve.controle.commandes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
import fr.unice.ent.eve.modele.sondages.Sondage;
import fr.unice.ent.eve.vue.VueSondages;

/**
 * Commande pour afficher les sondages en banque
 * 
 * @author benychou
 * 
 */
public class CommandeVoirBanque extends AbstractCommande {

  public CommandeVoirBanque () {
    super ();
  }

  public CommandeVoirBanque (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    final HashMap<String, String> h = new HashMap<String, String> ();

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
    if (abt.getP ().isSuperAdmin ()) {
      lcp.addAll (Commandes.staticInstance (gt).getParNiveau (3));
      lcp.addAll (Commandes.staticInstance (gt).getParNiveau (6));
    } else {
      for (final ContrainteCommande cC : g.getContrainte ()
          .getContrainteCommandes ()) {
        lcp.add (cC.getCommande ());
      }
      Collections.sort (lcp);
    }

    h.clear ();
    h.put ("enBanque", "1"); //$NON-NLS-1$ //$NON-NLS-2$

    final List<Sondage> lS = new ArrayList<Sondage> ();
    for (final Sondage sondage : Sondage.staticInstance (gt).requete (h)) {
      final Sondage s = (Sondage) gt.getSession ().load (Sondage.class,
          new Long (sondage.getId ()));
      lS.add (s);
    }
    Collections.sort (lS);

    final List<Integer> liNiveau = new ArrayList<Integer> ();
    liNiveau.add (new Integer (3));
    liNiveau.add (new Integer (6));

    final Object[] resultat = new Object[2];

    resultat[0] = g.getNom ();

    resultat[1] = VueSondages.vue (g, lS.iterator (), lcp, abt.getP (),
        new String (), liNiveau);

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$

    return new RedirectResolution ("/controleurs/" + //$NON-NLS-1$
        Commandes.staticInstance (m.getGT ())
            .getParNom ("VoirBanque").getPartie ().getPartie () + //$NON-NLS-1$
        "/VoirBanque", true); //$NON-NLS-1$
  }
}
