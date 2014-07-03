<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Test des services Web</stripes:layout-component>

<stripes:layout-component name="html-head">
      <style type="text/css">
          code { background-color: #f0f0f0; border:solid 1px grey ; }
      </style>
</stripes:layout-component>

<stripes:layout-component name="contenu">
    <h1>Test des services web</h1>

    <stripes:form action="/controleurs/tests/TestUpWs" focus="">
     Appel du service web de Uportal
     <stripes:text name="requete" value=""/>
     <stripes:submit name="testAppel" value="Essayer"/></blockquote>
     <br/><br/>
    </stripes:form>
     
    <stripes:form action="/controleurs/tests/TestUsWs" focus="">
     Appel du service web UserService
     <stripes:text name="requete" value=""/>
     <stripes:submit name="testAppel" value="Essayer"/></blockquote>
     <br/><br/>
    </stripes:form>
    
</stripes:layout-component>

</stripes:layout-render>
