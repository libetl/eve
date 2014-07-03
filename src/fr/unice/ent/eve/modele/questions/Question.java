package fr.unice.ent.eve.modele.questions;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import fr.unice.ent.eve.controle.bundlemessages.Messages;
import fr.unice.ent.eve.modele.GerantTransactions;
import fr.unice.ent.eve.modele.Modele;
import fr.unice.ent.eve.modele.reponses.Reponse;
import fr.unice.ent.eve.modele.sondages.QuestionSondage;
import fr.unice.ent.eve.modele.sondages.Sondage;

public class Question extends Modele<Question> implements Serializable {
  private static Question   instance;
  /**
   * 
   */
  private static final long serialVersionUID = 5247969447803974208L;

  public static Question staticInstance (final GerantTransactions s) {
    if (Question.instance == null) {
      Question.instance = new Question (s);
    }
    return Question.instance;
  }

  private Set<Choix>           choix;
  private long                 id;
  private int                  idType;
  private boolean              obligatoire;
  private int                  ordre;
  private Set<QuestionSondage> questionSondage;
  private String               titre;

  private QuestionType         type;

  public Question () {
  }

  public Question (final boolean b) {
    if (b) {
      Messages mess;
      final Modele<Question> m = new Modele<Question> (true);
      mess = new Messages (
          "fr.unice.ent.eve.modele.questions.QuestionX", Locale.getDefault ()); //$NON-NLS-1$
      final QuestionType qt = new QuestionType (m.getGT ());
      qt.setNom (mess.getString (this.getClass ().getSimpleName ()
          + ".description")); //$NON-NLS-1$
      qt.setType (this.getClass ().getSimpleName ().substring (8));
      qt.ajout ();
      m.mettreAJour ();
      m.fin ();
    }
  }

  public Question (final GerantTransactions gt) {
    super (gt);
  }

  public Question (final String t) {
    super ();
    this.titre = t;
  }

  public List<Choix> ajoutChoix (final String nomChoix)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
    this.rafraichir (this);
    final Question q = this.instanceTypeQuestion ();
    final int taille = this.choix.size ();
    final List<Choix> c = q.ajoutChoix (nomChoix, taille);
    this.rafraichir (this);
    return c;
  }

  protected List<Choix> ajoutChoix (final String nomChoix, final int taille) {
    final ArrayList<Choix> res = new ArrayList<Choix> ();
    final Choix c = new Choix (this.getGT (), this.id, taille + 1, nomChoix, -1);
    c.ajout ();
    res.add (c);
    return res;
  }

  public Reponse[] creerReponse (final Sondage s,
      final Map<String, Object> object) {
    object.hashCode ();
    s.hashCode ();
    return new Reponse[0];
  }

  public Set<Choix> getChoix () {
    return this.choix;
  }

  @Override
  public Object getComparableId () {
    return new Integer (this.ordre);
  }

  public long getId () {
    return this.id;
  }

  public int getIdType () {
    return this.idType;
  }

  public int getOrdre () {
    return this.ordre;
  }

  public Set<QuestionSondage> getQuestionSondage () {
    return this.questionSondage;
  }

  public String getTitre () {
    return this.titre;
  }

  public QuestionType getType () {
    return this.type;
  }

  public Question instanceTypeQuestion () throws InstantiationException,
      InvocationTargetException, NoSuchMethodException, IllegalAccessException,
      ClassNotFoundException {
    final Question q = (Question) Class
        .forName ("fr.unice.ent.eve.modele.questions.types.Question" //$NON-NLS-1$
            + this.type.getType ()).getConstructor (Boolean.class)
        .newInstance (new Boolean (false));
    q.setId (this.id);
    q.setQuestionSondage (this.questionSondage);
    q.setOrdre (this.ordre);
    q.setTitre (this.titre);
    q.setIdType (this.idType);
    q.setType (this.type);
    q.setChoix (this.choix);
    q.setGT (this.getGT ());
    return q;
  }

  public boolean isObligatoire () {
    return this.obligatoire;
  }

  public void modifierChoix (final Map<String, Object> m) {
    final ArrayList<Choix> alC = new ArrayList<Choix> ();
    alC.addAll (this.getChoix ());

    Collections.sort (alC);

    for (int i = 1 ; i <= alC.size () ; i++) {
      if (m.get ("choix" + i) != null) //$NON-NLS-1$
      {
        alC.get (i - 1).setNomChoix (((String[]) m.get ("choix" + i))[0]); //$NON-NLS-1$
      }

      if (m.get ("valeurChoix" + i) != null) //$NON-NLS-1$
      {
        alC.get (i - 1).setValeur (
            Integer.parseInt (((String[]) m.get ("valeurChoix" + i))[0])); //$NON-NLS-1$
      }

      alC.get (i - 1).setGT (this.getGT ());
      alC.get (i - 1).mettreAJour (alC.get (i - 1));
    }
  }

  public void setChoix (final Set<Choix> c) {
    this.choix = c;
  }

  public void setId (final long id1) {
    this.id = id1;
  }

  public void setIdType (final int idT) {
    this.idType = idT;
  }

  public void setObligatoire (final boolean ob) {
    this.obligatoire = ob;
  }

  public void setOrdre (final int o) {
    this.ordre = o;
  }

  public void setQuestionSondage (final Set<QuestionSondage> qS) {
    this.questionSondage = qS;
  }

  public void setTitre (final String titre1) {
    this.titre = titre1;
  }

  public void setType (final QuestionType t) {
    this.type = t;
  }

  public Question toQuestion () {
    final Question q = Question.staticInstance (this.getGT ());
    q.setId (this.id);
    q.setQuestionSondage (this.questionSondage);
    q.setOrdre (this.ordre);
    q.setTitre (this.titre);
    q.setIdType (this.idType);
    q.setType (this.type);
    q.setChoix (this.choix);
    q.setGT (this.getGT ());
    return q;
  }
}
