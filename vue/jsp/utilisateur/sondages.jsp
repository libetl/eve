<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Mes sondages</stripes:layout-component>

<stripes:layout-component name="html-head">

</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Mes Sondages</h1>
  <div>Liste des sondages :</div>
  <ul>
   ${actionBean.sondages}
  </ul>
  
</stripes:layout-component>

</stripes:layout-render>
