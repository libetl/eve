<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Aide de l'utilisateur</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      </script>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Aide</h1>
  <div>Bienvenue sur l'aide :</div>
  
</stripes:layout-component>

</stripes:layout-render>
