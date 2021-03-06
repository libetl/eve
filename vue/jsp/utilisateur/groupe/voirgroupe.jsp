<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Interface du groupe</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/groupeGUI.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/sondages.css"/>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Interface du groupe</h1>
  <p></p>
  <h2>${actionBean.nomGroupe}</h2>
  <p></p>
  <p>Liste des sondages :</p>
  <div id="tableauGroupes">
   <span class="colonneGaucheEntete">Nom du sondage</span><span class="colonneEntete">Type</span><span class="colonneDroiteEntete">Actions Possibles</span><div class="finLigneEntete"></div>
   ${actionBean.vueSondages}
   <span class="colonneGaucheBas"></span><span class="colonneBas"></span><span class="colonneDroiteBas"></span><div class="finLigneBas"></div>
  </div>
</stripes:layout-component>

</stripes:layout-render>
