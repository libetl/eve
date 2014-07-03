<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-definition>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="fr">
 <head>
  <title>Application EVE - <stripes:layout-component name="titre"/></title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/eve.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/menu.css"/>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/vue/_layout/imgs/favicon.ico"/>
  <stripes:layout-component name="html-head"/>
 </head>
 <body>
  <div id="conteneur">
   <stripes:layout-component name="entete">
    <jsp:include page="/vue/_layout/eve/enteteAdmin.jsp"/>
   </stripes:layout-component>
   <div class="pageContent">
    <div id="bienvenue">Bienvenue sur Eve, <% if (session.getAttribute ("nomAffichage") == null) out.print ("cher visiteur"); else out.print (session.getAttribute ("nomAffichage")); %>.</div>
    <stripes:layout-component name="menuAdmin">
     <jsp:include page="/vue/_layout/eve/menuAdmin.jsp"/>
    </stripes:layout-component>
    <stripes:layout-component name="contenu"/>
   </div>
   <stripes:layout-component name="bas">
    <jsp:include page="/vue/_layout/eve/bas.jsp"/>
   </stripes:layout-component>
  </div>
 </body>
</html>
</stripes:layout-definition>
