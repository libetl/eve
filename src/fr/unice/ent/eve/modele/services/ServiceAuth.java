package fr.unice.ent.eve.modele.services;

import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.config.EveConfig;

public class ServiceAuth extends Modele<ServiceAuth> {
  private static ServiceAuth instance;

  public static ServiceAuth getGroupsService (final GerantTransactions gt) {
    final EveConfig ec = EveConfig.staticInstance (gt);
    return new ServiceAuth (ec.get ("service_groupes_adresse"), //$NON-NLS-1$
        ec.get ("service_groupes_classe")); //$NON-NLS-1$
  }

  public static ServiceAuth staticInstance (final GerantTransactions s) {
    if (ServiceAuth.instance == null) {
      ServiceAuth.instance = new ServiceAuth (s);
    }
    return ServiceAuth.instance;
  }

  private String adresse;

  private String classe;

  public ServiceAuth () {
  }

  public ServiceAuth (final GerantTransactions gt) {
    super (gt);
  }

  public ServiceAuth (final GerantTransactions gt, final String ad,
      final String cl) {
    super (gt);
    this.adresse = ad;
    this.classe = cl;
  }

  public ServiceAuth (final String ad, final String cl) {
    super ();
    this.adresse = ad;
    this.classe = cl;
  }

  public ServiceAuth get (final String a) {
    return (ServiceAuth) this.getSession ().load (this.getClass (), a);
  }

  public String getAdresse () {
    return this.adresse;
  }

  public String getClasse () {
    return this.classe;
  }

  @Override
  public Object getComparableId () {
    return this.adresse;
  }

  public void setAdresse (final String ad) {
    this.adresse = ad;
  }

  public void setClasse (final String cl) {
    this.classe = cl;
  }

}
