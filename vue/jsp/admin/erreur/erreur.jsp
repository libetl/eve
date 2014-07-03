<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eveAdmin.jsp">

<stripes:layout-component name="titre">Probl&egrave;me</stripes:layout-component>

<stripes:layout-component name="html-head">
 <style>
  .italique {font-style:italic;}
 </style>
</stripes:layout-component>

<stripes:layout-component name="contenu">
 <h1>Acc&egrave;s impossible :</h1>
 <p>Eve a emp&ecirc;ch&eacute; l'acc&egrave;s &agrave; cette page pour la raison suivante : <br/>
 <span class="italique"><% out.println (fr.unice.ent.eve.vue.VueErreur.getErreurMessage ()); %></span></p>
</stripes:layout-component>


</stripes:layout-render>
