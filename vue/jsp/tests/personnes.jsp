<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Test de gestion de la gestion des personnes (via HB)</stripes:layout-component>

<stripes:layout-component name="html-head">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
</stripes:layout-component>

<stripes:layout-component name="contenu">
    <h1>Test de la gestion des personnes (via HB)</h1>

    <stripes:form action="/controleurs/tests/TestPersonnes" focus="">
     Ajout d'un uid :
     <stripes:text name="uid" value=""/>
     <stripes:submit name="ajoutUid" value="Essayer"/></blockquote>
     <br/><br/>
    </stripes:form>
     
    <stripes:form action="/controleurs/tests/TestPersonnes" focus="">
     Suppression d'un uid :
     <stripes:text name="uid" value=""/>
     <stripes:submit name="supprUid" value="Essayer"/></blockquote>
     <br/><br/>
    </stripes:form>
    
     <stripes:form action="/controleurs/tests/TestPersonnes" focus="">
     Liste des uids :
     <stripes:submit name="liste" value="Essayer"/></blockquote>
     <br/><br/>
    </stripes:form>

     <br/><br/>
     Liste :<br/>
     <div style="width:1040px">
     <span class="gColonneGaucheEntete">Id de la personne</span><span class="gColonneDroiteEntete">Uid de la Personne</span><div class="finLigneEntete"></div>
     ${actionBean.liste}
     <span class="gColonneGaucheBas"></span><span class="gColonneDroiteBas"></span><div class="gFinLigneBas"></div>
     </div>

</stripes:layout-component>

</stripes:layout-render>
