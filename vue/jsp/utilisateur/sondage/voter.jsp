<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Voter pour un sondage</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/vote.css"/>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <h1>${actionBean.nomGroupe} - ${actionBean.nomSondage}</h1>
  <h2>Formulaire de vote</h2>
  <div id="titreQuestionG"></div><div id="titreQuestion">${actionBean.libelle}</div><div id="titreQuestionD"></div>
  <div class="bourrage"></div>
  <fieldset><legend>Choix :</legend>
  <stripes:form action="/controleurs/utilisateur/sondage/Voter">
  ${actionBean.choix}
  <stripes:submit name="suivant" value="Suivante >"/>
  ${actionBean.passer}
  </stripes:form>
  </fieldset>
</stripes:layout-component>

</stripes:layout-render>
