<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eveAdmin.jsp">

<stripes:layout-component name="titre">Gestion des services</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/services.css"/>

      <script type="text/javascript"
              src="${pageContext.request.contextPath}/vue/javascript/prototype/prototype.js"></script>

      <script type="text/javascript" xml:space="preserve">
          /*
           * Function that uses Prototype to invoke an action of a form. Slurps the values
           * from the form using prototype's 'Form.serialize()' method, and then submits
           * them to the server using prototype's 'Ajax.Updater' which transmits the request
           * and then renders the response text into the named container.
           *
           * @param form reference to the form object being submitted
           * @param event the name of the event to be triggered, or null
           * @param container the name of the HTML container to insert the result into
           */
          function invoke (adresse, methode, params, container)
          {
              params = methode + '&' + params;
              new Ajax.Updater (container, adresse, {method:'post', postBody:params});
          }
          
          function requestResponse (adresse, methode, params, retour)
          {
              params = methode + '&' + params;
              new Ajax.Request (adresse, {method:'post', postBody:params, onComplete:retour});
          }
          
          function commande (nomCommande)
          {
            commande (nomCommande, "");
          }
          
          function commande (nomCommande, adresse)
          {
            switch (nomCommande)
            {
              case 'AjouterService':
                n = document.createElement ('span');
                document.getElementById ("services").appendChild (n);
                invoke ("${pageContext.request.contextPath}/controleurs/admin/services/GererServices",
                 "ajouterService", "nAdresse=" + document.getElementById ('nServiceAdresse').value 
                                 + "&nClasse=" + document.getElementById ('nServiceClasse').value, n);
                break;
              case 'EditerSupprimer':
                if (document.getElementById ('mServiceAdresse') == null && document.getElementById ('mServiceClasse') == null)
                {
                  document.getElementById (adresse + 'AP').innerHTML = "<form action=\"javascript:commande ('ValiderEditerSupprimer', '"
                    + adresse + "')\"><input type=\"text\" id=\"mServiceAdresse\" value=\"" 
                    + document.getElementById (adresse + 'AP').innerHTML + "\"/></form>";
                  document.getElementById (adresse + 'CP').innerHTML = "<form action=\"javascript:commande ('ValiderEditerSupprimer', '"
                    + adresse + "')\"><input type=\"text\" id=\"mServiceClasse\" value=\"" 
                    + document.getElementById (adresse + 'CP').innerHTML + "\"/></form>";
                }
                break;
              case 'ValiderEditerSupprimer':
                invoke ("${pageContext.request.contextPath}/controleurs/admin/services/GererServices", 'validerEditerSupprimer', 
                "adresse=" + adresse + "&nAdresse=" + document.getElementById ('mServiceAdresse').value 
                                     + "&nClasse=" + document.getElementById ('mServiceClasse').value, document.createElement ('div'));
                if (document.getElementById ('mServiceAdresse').value.length == 0 ||
                    document.getElementById ('mServiceClasse').value.length == 0 )
                {
                  document.getElementById (adresse + 'A' ).style.display = 'none';
                  document.getElementById (adresse + 'C' ).style.display = 'none';
                  document.getElementById (adresse + 'ES').style.display = 'none';
                  document.getElementById (adresse + 'FL').style.display = 'none';
                }else
                {
                  document.getElementById (adresse + 'AP').innerHTML = document.getElementById ('mServiceAdresse').value;
                  document.getElementById (adresse + 'CP').innerHTML = document.getElementById ('mServiceClasse').value;
                }
                break;
              case 'ChangerServiceGroupes':
                document.getElementById ('sgPatienter').style.display = 'inline';
                requestResponse ("${pageContext.request.contextPath}/controleurs/admin/services/GererServices",
                 "changerServiceGroupes", "nServiceGroupesAdresse=" + document.getElementById ('services_groupes_adresse').value 
                               + "&nServiceGroupesClasse=" + document.getElementById ('services_groupes_classe').value,
                  function (transport, json)
                  {
                    document.getElementById ('sgPatienter').style.display = 'none';
                  });
                break;
              case 'ChangerCAS':
                document.getElementById ('casPatienter').style.display = 'inline';
                requestResponse ("${pageContext.request.contextPath}/controleurs/admin/services/GererServices",
                 "changerCAS", "nCasLogin=" + document.getElementById ('cas_login').value 
                               + "&nCasValidate=" + document.getElementById ('cas_validate').value,
                  function (transport, json)
                  {
                    document.getElementById ('casPatienter').style.display = 'none';
                  });
                break;
              case 'ChangerSMTP':
                document.getElementById ('smtpPatienter').style.display = 'inline';
                requestResponse ("${pageContext.request.contextPath}/controleurs/admin/services/GererServices",
                 "changerSMTP", "smtpServeur=" + document.getElementById ('smtp_serveur').value,
                  function (transport, json)
                  {
                    document.getElementById ('smtpPatienter').style.display = 'none';
                  });
                break;
            }
          }

      </script>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Gestion des services</h1>

  <h2>CAS</h2>
  <fieldset><legend>Fournir un service central d'authentification (CAS)</legend>
	<label for="services_groupes_adresse">Url (finit par '=') :</label><input type="text" id="cas_login"  value="${actionBean.casLogin}"/><br/>
	<label for="services_groupes_classe">Url de validation :</label><input type="text" id="cas_validate" value="${actionBean.casValidate}"/><br/>
    <input type="button" value="Changer" onclick="commande('ChangerCAS');"/><div id="casPatienter">Non synchro</div>
  </fieldset>

  <h2>SMTP</h2>
  <fieldset><legend>Fournir un service d'envoi de courriel</legend>
	<label for="smtp_serveur">Serveur :</label><input type="text" id="smtp_serveur"  value="${actionBean.smtpServeur}"/><br/>
    <input type="button" value="Changer" onclick="commande('ChangerSMTP');"/><div id="smtpPatienter">Non synchro</div>
  </fieldset>
  
  <p></p>
  <h2>Services de groupes</h2>
  <fieldset><legend>Fournir un service web de gestions de groupes</legend>
	<label for="services_groupes_adresse">Url :</label><input type="text" id="services_groupes_adresse"  value="${actionBean.servicesGroupesAdresse}"/><br/>
	<label for="services_groupes_classe">Classe cliente &agrave; appeller :</label> <input type="text" id="services_groupes_classe" value="${actionBean.classeDefautGroupes}"/><br/>
    <input type="button" value="Changer" onclick="commande('ChangerServiceGroupes');"/><div id="sgPatienter">Non synchro</div>
  </fieldset>
  
  <p></p>
  <h2>Annuaires</h2>
  <fieldset><legend>Services d'interrogation</legend>
  <span class="colonneGaucheEntete">Url du service</span><span class="colonneEntete">Classe</span><span class="colonneDroiteEntete">Action</span><div class="finLigneEntete"></div>  
  <span id="services">${actionBean.serviceAuth}</span>
  <span class="colonneGaucheBas"></span><span class="colonneBas"></span><span class="colonneDroiteBas"></span><div class="finLigneBas"></div>
  Nouveau service : 
  <label for="nServiceAdresse">Url du service :</label><input type="text" name="nServiceAdresse" id="nServiceAdresse" value=""/><br/>
  <label for="nServiceClasse">Classe : </label><input type="text" name="nServiceClasse" id="nServiceClasse" value=""/><br/>
  <input type="button" value="Ajouter" onclick="commande('AjouterService');"/>  </fieldset>
</stripes:layout-component>

</stripes:layout-render>
