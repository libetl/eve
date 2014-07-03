<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Voter pour un sondage - Merci pour ce vote</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/vote.css"/>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <h1>${actionBean.nomGroupe} - ${actionBean.nomSondage}</h1>
  <h2>Vote effectu&eacute;</h2>
  <p>Merci d'avoir vot&eacute;.</p>
  <p>Vous pouvez revenir &agrave; <a href="../../action/Redirection?comm=VoirGroupe" title="Interface du groupe">l'interface de votre groupe</a>.</p> 
</stripes:layout-component>

</stripes:layout-render>
