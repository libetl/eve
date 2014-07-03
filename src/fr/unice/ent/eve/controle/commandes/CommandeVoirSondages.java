package fr.unice.ent.eve.controle.commandes;

import java.util.ArrayList;
import java.util.List;

import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.Groupe;
import fr.unice.ent.eve.modele.groupes.Personne;
import fr.unice.ent.eve.modele.sondages.GroupeSondage;
import fr.unice.ent.eve.vue.VueSondages;

/**
 * Commande pour voir les sondages ouverts d'un utilisateur
 * 
 * @author benychou
 * 
 */
public class CommandeVoirSondages extends AbstractCommande {

  public CommandeVoirSondages () {
    super ();
  }

  public CommandeVoirSondages (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    final Personne p = (Personne) gt.getSession ().load (Personne.class,
        new Long (abt.getP ().getId ()));

    p.setGT (gt);

    final List<GroupeSondage> lS = new ArrayList<GroupeSondage> ();
    final List<Groupe> lg = p.groupes ();

    for (final Groupe g : lg) {
      for (final GroupeSondage gs : g.getGs ()) {
        if (gs.getS ().isOuvert () && !gs.getS ().isBloque ()) {
          lS.add (gs);
        }
      }
    }

    final Object[] resultat = new Object[1];
    resultat[0] = VueSondages.vueListe (lS);

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$    

    return null;
  }

}
