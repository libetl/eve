<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eveAdmin.jsp">

<stripes:layout-component name="titre">Interface Admin du groupe - Ajout/Edition de sondage</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/groupeGUI.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/calendrier.css"/>

      <script type="text/javascript" src="${pageContext.request.contextPath}/vue/javascript/jscalendar-1.0/calendar.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/vue/javascript/jscalendar-1.0/calendar-setup.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/vue/javascript/jscalendar-1.0/lang/calendar-fr.js"></script>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Interface Admin du groupe</h1>
  <p></p>
  <h2>${actionBean.nomGroupe}</h2>
  <p>Ajout/Edition de sondage</p>
  <stripes:form action="/controleurs/admin/groupe/AjouterSondage">
  <stripes:hidden name="editer" value="${actionBean.editer}"/>
  <fieldset><legend>Formulaire :</legend>
  <span class="colonneGaucheEntete">Propriete</span><span class="colonneDroiteEntete">Valeur</span><div class="finLigneEntete"></div>
  <span class="colonneGauche">Titre</span><span class="colonneDroite"><stripes:text class="input" name="titre" size="30" value="${actionBean.titre}"/></span><div class="finLigne"></div>
  <span class="colonneGauche">Type de sondage</span><span class="colonneDroite"><stripes:select class="input" name="type" value="${actionBean.type}"><stripes:options-collection collection="${actionBean.lst}" value="id" label="nomSondageType"/></stripes:select></span><div class="finLigne"></div>
  <span class="colonneGauche">Date d'ouverture </span><span class="colonneDroite"><stripes:text  name="dateOuverture" id="selO" size="30" value="${actionBean.dateOuverture}"/><input type="reset" class="input" value=" ... " onclick="return showCalendar('selO', '%d/%m/%Y %H:%M', '12');"></span><div class="finLigne"></div>
  <span class="colonneGauche">Date de fermeture</span><span class="colonneDroite"><stripes:text  name="dateFermeture" id="selF" size="30" value="${actionBean.dateFermeture}"/><input type="reset" class="input" value=" ... " onclick="return showCalendar('selF', '%d/%m/%Y %H:%M', '12');"></span><div class="finLigne"></div>
  <span class="colonneGauche">Sondage publique</span><span class="colonneDroite"><stripes:checkbox name="publique" checked="${actionBean.publique}"/></span><div class="finLigne"></div>
  <span class="colonneGauche">Banque des sondages</span><span class="colonneDroite"><stripes:checkbox name="banque" checked="${actionBean.banque}"/></span><div class="finLigne"></div>
  <span class="colonneGauche">Vote une seule fois</span><span class="colonneDroite"><stripes:checkbox name="voteUnique" checked="${actionBean.voteUnique}"/></span><div class="finLigne"></div>
  <span class="colonneGauche">Reproductible</span><span class="colonneDroite"><stripes:checkbox name="dupliquable" checked="${actionBean.dupliquable}"/></span><div class="finLigne"></div>
  <span class="colonneGauche">Affichage al&eacute;atoire lors du vote</span><span class="colonneDroite"><stripes:checkbox name="aleatoire" checked="${actionBean.aleatoire}"/></span><div class="finLigne"></div>
  <span class="colonneGauche"></span><span class="colonneDroite"><stripes:submit class="input" name="creer" value="Ajout/Edition"/></span><div class="finLigne"></div>
  <span class="colonneGaucheBas"></span><span class="colonneDroiteBas"></span><div class="finLigneBas"></div>
  </fieldset>
  </stripes:form>
  <script type="text/javascript">
  function commit (cal, date)
  {
    cal.sel.value = date;
  }

  function closeHandler(cal) {
    cal.hide();
    _dynarch_popupCalendar = null;
  }

  function showCalendar (id, format, showsTime, showsOtherMonths) {
    var el = document.getElementById(id);
    if (_dynarch_popupCalendar != null) {
      _dynarch_popupCalendar.hide(); 
    } else {
      var cal = new Calendar (1, format, commit, closeHandler);
      if (typeof showsTime == "string") {
        cal.showsTime = true;
        cal.time24 = true;
      }
      if (showsOtherMonths) {
        cal.showsOtherMonths = true;
      }
      _dynarch_popupCalendar = cal;
      cal.setRange (1900, 2070);
      cal.create ();
    }
    _dynarch_popupCalendar.setDateFormat (format);
    _dynarch_popupCalendar.parseDate (el.value);
    _dynarch_popupCalendar.sel = el;

    _dynarch_popupCalendar.showAtElement (el.nextSibling, "Br");

    return false;
  }

  var cal = new Calendar(0, "%Y.%m.%d %H:%M", commit);

  </script>
</stripes:layout-component>

</stripes:layout-render>
