package fr.unice.ent.eve.controle.commandes;

import java.lang.reflect.InvocationTargetException;

/**
 * L'invocateur de commande est une classe qui interprete la ligne de commande
 * et les parametres passes. <br/>
 * Elle cree une instance de la commande correspondante (premier mot de la ligne
 * de Commande, exemple "Voter\n0\n0"
 * 
 * 
 * <br/>
 * <br/>
 * La ligne de commande a la syntaxe suivante :
 * "NomAbregeCommande\n[idGroupe]\n[idSondage]\n[AutresParametres]"
 * 
 * <br/>
 * Le nom abrege correspond au nom de la commande sans le prefixe "Commande".
 * 
 * <br/>
 * Il est possible de passer des parametres objets lors de l'invocation.
 * 
 * @author benychou
 * 
 */
public class InvocateurCommande {

  public static Object lancer (final String lnCmd)
      throws ClassNotFoundException, InstantiationException,
      IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    return InvocateurCommande.lancer (lnCmd, new Object[0]);
  }

  public static Object lancer (final String lnCmd, final Object... paramsObjet)
      throws ClassNotFoundException, InstantiationException,
      IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    final String[] tabLnCmd = lnCmd.split ("\n"); //$NON-NLS-1$
    final AbstractCommande c = (AbstractCommande) Class
        .forName ("fr.unice.ent.eve.controle.commandes.Commande" + tabLnCmd[0]) //$NON-NLS-1$
        .getConstructor (Boolean.class).newInstance (new Boolean (true));
    c.setParams (tabLnCmd);
    c.setParamsObjet (paramsObjet);
    return c.executer ();
  }

  public InvocateurCommande () {
    super ();
  }
}
