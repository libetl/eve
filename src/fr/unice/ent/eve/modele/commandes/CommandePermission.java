package fr.unice.ent.eve.modele.commandes;

import java.io.Serializable;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.PermissionType;

public class CommandePermission extends Modele<CommandePermission> implements
    Serializable {
  private static CommandePermission instance;
  /**
   * 
   */
  private static final long         serialVersionUID = 9056983388922875120L;

  public static CommandePermission staticInstance (final GerantTransactions s) {
    if (CommandePermission.instance == null) {
      CommandePermission.instance = new CommandePermission (s);
    }
    return CommandePermission.instance;
  }

  private Commandes      c;
  private int            idCommande;
  private int            idPermission;

  private PermissionType p;

  public CommandePermission () {
  }

  public CommandePermission (final GerantTransactions gt) {
    super (gt);
  }

  public CommandePermission (final GerantTransactions gt, final Commandes co,
      final PermissionType pe) {
    super (gt);
    this.c = co;
    this.p = pe;
  }

  public CommandePermission (final GerantTransactions gt,
      final int idCommande2, final int idPermission2) {
    super (gt);
    this.idCommande = idCommande2;
    this.idPermission = idPermission2;
  }

  public Commandes getC () {
    return this.c;
  }

  public int getIdCommande () {
    return this.idCommande;
  }

  public int getIdPermission () {
    return this.idPermission;
  }

  public PermissionType getP () {
    return this.p;
  }

  public void setC (final Commandes co) {
    this.c = co;
  }

  public void setIdCommande (final int idC) {
    this.idCommande = idC;
  }

  public void setIdPermission (final int idP) {
    this.idPermission = idP;
  }

  public void setP (final PermissionType pe) {
    this.p = pe;
  }

}
