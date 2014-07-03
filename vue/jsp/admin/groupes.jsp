<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eveAdmin.jsp">

<stripes:layout-component name="titre">Gestion de groupes</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      <script type="text/javascript">
        function commande (nomCommande, idG, nomG, contrainte)
        {
          switch (nomCommande)
          {
            case 'EditerProprietesGroupe': 
              document.forms [1].id.value = idG;
              document.forms [1].nomGroupe.value = nomG;
              document.forms [1].contrainte.value = contrainte;
              document.getElementById ("EditerProprietesGroupe").style.display = 'block';
              break;
            case 'SupprimerGroupe':
              document.forms [2].id.value = idG;
              document.getElementById ("nomSupprGroupe" ).innerHTML = nomG ;
              document.getElementById ("SupprimerGroupe").style.display = 'block';
              break;
            default:location.replace  ("${pageContext.request.contextPath}/controleurs/action/Redirection?comm=" + nomCommande + "&g=" + idG);
          }
        }
        
      </script>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Gestion des groupes</h1>
  <div>Liste des groupes :</div>
  <div id="tableauGroupes">
   <span class="colonneGaucheEntete">Nom du groupe</span><span class="colonneEntete">Contrainte</span><span class="colonneDroiteEntete">Actions Possibles</span><div class="finLigneEntete"></div>
   ${actionBean.groupes}
   <span class="colonneGaucheBas"></span><span class="colonneBas"></span><span class="colonneDroiteBas"></span><div class="finLigneBas"></div>
  </div>
  
  
  <div>
    <fieldset><legend>Ajouter un groupe :</legend>
      <stripes:form action="/controleurs/admin/AfficherGroupesAdmin">
        <stripes:text name="nomGroupe"/>
        <stripes:select name="contrainte"><stripes:options-collection collection="${actionBean.listeGrCo}" value="id" label="nomContrainte"/></stripes:select>
        <stripes:submit name="ordre" value="Ajouter"/>
        <input type="hidden" name="nomCommande" value="AjouterGroupe"/>
      </stripes:form>
    </fieldset>
  </div>
  
  <div id="EditerProprietesGroupe" style="display:none">
    <fieldset><legend>Editer un groupe :</legend>
      <stripes:form action="/controleurs/admin/AfficherGroupesAdmin">
        <stripes:hidden name="id"></stripes:hidden>
        <stripes:text class="input" name="nomGroupe"/>
        <stripes:select name="contrainte"><stripes:options-collection collection="${actionBean.listeGrCo}" value="id" label="nomContrainte"/></stripes:select>
        <stripes:submit name="ordre" value="Editer"/>
        <input type="hidden" name="nomCommande" value="EditerProprietesGroupe"/>
      </stripes:form>
    </fieldset>
  </div>
  
  <div id="SupprimerGroupe" style="display:none">
    <fieldset><legend>Supprimer un groupe :</legend>
      <stripes:form action="/controleurs/admin/AfficherGroupesAdmin">
      <stripes:hidden name="id"></stripes:hidden>
      &Ecirc;tes vous s&ucirc;r de vouloir supprimer le groupe 
      <span id="nomSupprGroupe"></span> ?
      <stripes:submit name="ordre" value="Oui"/>
      <input type="hidden" name="nomCommande" value="SupprimerGroupe"/>
      </stripes:form>
    </fieldset>
  </div>
</stripes:layout-component>

</stripes:layout-render>
