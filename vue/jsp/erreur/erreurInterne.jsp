<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Erreur Interne</stripes:layout-component>

<stripes:layout-component name="html-head">
</stripes:layout-component>

<stripes:layout-component name="contenu">
 <h1>Erreur interne :</h1>
 <p>Ceci n'est pas un probl&egrave;me de saisie ou une action incorrecte.</p>
 <p>L'application n'a pu r&eacute;pondre correctement &agrave; la requete.<br/>
    Nous vous prions d'accepter nos excuses.<br/><br/>Ci joint la trace, &agrave; envoyer &agrave; l'administrateur du site.</p>
 <pre>
 <%
 if (exception != null)
   exception.printStackTrace (new java.io.PrintWriter (out)); 
 %>
 </pre>
</stripes:layout-component>


</stripes:layout-render>