<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Accueil</stripes:layout-component>

<stripes:layout-component name="html-head">
  <meta http-equiv="refresh" content="5; url=${pageContext.request.contextPath}/vue/jsp/tests">
</stripes:layout-component>

<stripes:layout-component name="contenu">

  <div class="grandTitre">Redirection vers les tests</div>

  <p>Ceci est une version incompl&egrave;te de l'application Eve, avec du code non stabilis&eacute;.
  
</stripes:layout-component>

</stripes:layout-render> 
