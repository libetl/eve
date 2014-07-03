<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eveAdmin.jsp">

<stripes:layout-component name="titre">G&eacute;rer les utilisateurs</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>

</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Ajouter / Supprimer un super admin</h1>
  
  <h2 style="display:${actionBean.fait}">Action Effectu&eacute;e</h2>

  <fieldset><legend>Entrer un uid ci dessous</legend>
  <stripes:form action="/controleurs/admin/utilisateurs/GererUtilisateurs">
  Uid : <stripes:text name="uid"/>
  <br/>
  <stripes:checkbox name="sur"/> Je suis conscient des risques que je prends.
  <br/><stripes:submit name="ajoutUn" value="Ajouter/Supprimer"/>
  </stripes:form>
  </fieldset>
  
</stripes:layout-component>

</stripes:layout-render>
