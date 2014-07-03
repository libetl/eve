package fr.unice.ent.eve.controle.commandes;

import fr.unice.ent.eve.controle.controleurs.ActionBeanTraitant;
import fr.unice.ent.eve.controle.ws.ConsommateurWS;
import fr.unice.ent.eve.controle.ws.EntreeWS;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;

/**
 * Commande peu utile pour afficher les donnees renvoyees par le service web des
 * groupes
 * 
 * @author benychou
 * 
 */
public class CommandeVoirDonnees extends AbstractCommande {

  public CommandeVoirDonnees () {
    super ();
  }

  public CommandeVoirDonnees (final Boolean b) {
    super (b);
  }

  @Override
  public Object executer () {
    final Modele<?> m = (Modele<?>) this.getParamsObjet ()[0];
    final GerantTransactions gt = m.getGT ();
    final ActionBeanTraitant abt = (ActionBeanTraitant) this.getParamsObjet ()[1];

    abt.getP ().setGT (gt);

    final Object[] resultat = new Object[1];

    resultat[0] = ConsommateurWS.mapVersString ((EntreeWS) abt.getContext ()
        .getRequest ().getSession ().getAttribute ("donneesPerso")); //$NON-NLS-1$

    abt.getContext ().getRequest ().getSession ()
        .setAttribute ("resultatDerniereCommande", resultat); //$NON-NLS-1$  

    return null;
  }

}
