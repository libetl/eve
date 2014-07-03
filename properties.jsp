<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Accueil</stripes:layout-component>

<stripes:layout-component name="html-head">
</stripes:layout-component>

<stripes:layout-component name="contenu">

  Je t'affiche ici les properties java :
  <ul>
  <% java.util.Properties prop = java.lang.System.getProperties ();
     for (Object key : prop.keySet ()){
       out.print ("<li>" + key + " = " + prop.get (key) + "</li>");
     }
  %>
  </ul>

</stripes:layout-component>

</stripes:layout-render> 
