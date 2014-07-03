package fr.unice.ent.eve.controle.controleurs.tests;

import java.util.List;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.groupes.Personne;
import fr.unice.ent.eve.vue.VuePersonne;

public class TestPersonnes implements ActionBean {
  private ActionBeanContext      context;
  private String                 liste = "";                         //$NON-NLS-1$
  private final Modele<Personne> m     = new Modele<Personne> (true);
  private String                 uid   = "";                         //$NON-NLS-1$

  public TestPersonnes () {
    super ();
  }

  public ForwardResolution ajoutUid () {
    final Personne p = new Personne (this.m.getGT (), this.uid);
    p.ajout ();
    return this.liste ();
  }

  @Override
  public ActionBeanContext getContext () {
    return this.context;
  }

  public String getListe () {
    return this.liste;
  }

  public String getUid () {
    return this.uid;
  }

  @DefaultHandler
  public ForwardResolution liste () {
    List<Personne> listePersonnes;
    listePersonnes = Personne.staticInstance (this.m.getGT ()).requete ();
    this.liste += VuePersonne.vue (listePersonnes.iterator ());
    this.m.fin ();
    return new ForwardResolution ("/vue/jsp/tests/personnes.jsp"); //$NON-NLS-1$
  }

  @Override
  public void setContext (final ActionBeanContext c) {
    this.context = c;
  }

  public void setUid (final String u) {
    this.uid = u;
  }

  public ForwardResolution supprUid () {
    Personne.staticInstance (this.m.getGT ()).getParNom (this.uid)
        .suppression ();
    return this.liste ();
  }
}
