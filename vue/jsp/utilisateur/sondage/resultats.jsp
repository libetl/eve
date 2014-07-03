<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Resultat du sondage '${actionBean.nomSondage}'</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/vote.css"/>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <h1>${actionBean.nomGroupe}</h1>
  <h2>Resultats du sondage '${actionBean.nomSondage}'</h2>
  <p></p>
  <p>${actionBean.resultats}</p>
</stripes:layout-component>

</stripes:layout-render>
