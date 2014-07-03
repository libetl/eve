<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eveAdmin.jsp">

<stripes:layout-component name="titre">Gestion des permissions - Groupe</stripes:layout-component>

<stripes:layout-component name="html-head">

</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Interface Admin du groupe</h1>
  <p></p>
  <h2>${actionBean.nomGroupe}</h2>
  <p>Permissions sur les personnes du groupe</p>
  
  <h2 style="display:${actionBean.fait}">Action Effectu&eacute;e</h2>
  
  <fieldset><legend>Entrer un uid ci dessous</legend>
  <stripes:form action="/controleurs/admin/groupe/OperationsPersonnes">
  Uid : <stripes:text name="uid"/>
  <br/>
  ${actionBean.formPermissions}<br/>
  <stripes:submit name="ajoutUn" value="Ajouter"/>
  </stripes:form>
  </fieldset>
  
  <fieldset><legend>Sp&eacute;cifier un filtre</legend>
  <stripes:form action="/controleurs/admin/groupe/OperationsPersonnes">
  Service : <stripes:select name="adresse">
  <stripes:options-collection collection="${actionBean.listeServices}" value="adresse" label="adresse"/>
  </stripes:select>
  <br/>Ligne de commande :<stripes:text name="lnCmd" size="50"></stripes:text><br/>
  <br/>
  ${actionBean.formPermissions}<br/>
  <stripes:submit name="ajout" value="Ajouter"/>
  </stripes:form>
  </fieldset>
  
</stripes:layout-component>

</stripes:layout-render>
