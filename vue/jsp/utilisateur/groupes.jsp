<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Gestion de groupes</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      <script type="text/javascript">
        function commande (nomCommande, idG, nomG, contrainte)
        {
          switch (nomCommande)
          {
            default:location.replace  ("${pageContext.request.contextPath}/controleurs/action/Redirection?comm=" + nomCommande + "&g=" + idG);
           }
        }
        
      </script>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Mes groupes</h1>
  <div>Liste des groupes :</div>
  <div id="tableauGroupes">
   <span class="colonneGaucheEntete">Nom du groupe</span><span class="colonneEntete">Contrainte</span><span class="colonneDroiteEntete">Actions Possibles</span><div class="finLigneEntete"></div>
   ${actionBean.groupes}
   <span class="colonneGaucheBas"></span><span class="colonneBas"></span><span class="colonneDroiteBas"></span><div class="finLigneBas"></div>
  </div>
  
</stripes:layout-component>

</stripes:layout-render>
