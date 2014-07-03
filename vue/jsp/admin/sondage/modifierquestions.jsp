<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eveAdmin.jsp">

<stripes:layout-component name="titre">Interface Admin du groupe - Modification de sondage</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/groupeGUI.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/calendrier.css"/>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/questions.css"/>

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

          ${actionBean.jsOrdre}
          adresseAjax = "${pageContext.request.contextPath}/controleurs/admin/sondage/ModifierQuestions";
          
          function ajouterQuestion (typeQuestion)
          {
            requestResponse (adresseAjax, "ajoutQuestion", "typeQuestion=" + typeQuestion, 
            function (transport, json)
            {
              n = document.createElement ('div');
              n.className = 'liQuestions';
              document.getElementById ("questions").appendChild (n);
              n.innerHTML = transport.responseText;
              num = transport.responseText.substring (32);
              num = num.substring (0, num.indexOf ("<"));
              jsOrdre [jsOrdre.length] = parseInt (num);
            });
              
          }

          function ajouterChoix (idQ)
          {
              n = document.createElement ('div');
              document.getElementById ("choixEdition").appendChild (n);
              invoke (adresseAjax, "ajouterChoix", "idQuestion=" + idQ, n);
          }
          
          function trouverOrdreQuestion (idQ)
          {
            i = 0;
            while (i < jsOrdre.length && jsOrdre [i] != idQ)
              i++;
            return i;
          }
          
          function commande (nomCommande, idQ)
          {
            switch (nomCommande)
            {
              case 'RemonterQuestion': 
                i = trouverOrdreQuestion (idQ);
                if (i == 0) return;
                tmp = document.getElementById ("questions").childNodes [i].innerHTML;
                document.getElementById ("questions").childNodes [i].innerHTML = 
                        document.getElementById ("questions").childNodes [i - 1].innerHTML;
                document.getElementById ("questions").childNodes [i - 1].innerHTML = tmp;
                jsOrdre [i] = jsOrdre [i - 1];
                jsOrdre [i - 1] = idQ;
                invoke (adresseAjax, "remonterQuestion", "idQuestion=" + idQ, document.createElement ('div'));
                break;
              case 'DescendreQuestion':
                i = trouverOrdreQuestion (idQ);
                if (i == jsOrdre.length - 1) return;
                tmp = document.getElementById ("questions").childNodes [i + 1].innerHTML;
                document.getElementById ("questions").childNodes [i + 1].innerHTML = 
                        document.getElementById ("questions").childNodes [i].innerHTML;
                document.getElementById ("questions").childNodes [i].innerHTML = tmp;
                jsOrdre [i] = jsOrdre [i + 1];
                jsOrdre [i + 1] = idQ;
                invoke (adresseAjax, "descendreQuestion", "idQuestion=" + idQ, document.createElement ('div'));
                break;
              case 'EditerQuestion':
                editerQuestion (idQ);
                break;
              case 'ValiderFormulaire':
                invoke (adresseAjax, "validerFormulaire", Form.serialize(document.forms [0]) + "&idQuestion=" + idQ, 
                        "questionTitre" + idQ);
                document.getElementById ("editerQuestion").style.display = "none";
              	break;
              case 'AjouterChoix':
                ajouterChoix (idQ);
                break;
              case 'SupprimerQuestion':
              	i = trouverOrdreQuestion (idQ);
              	document.getElementById ("questions").removeChild 
              	        (document.getElementById ("questions").childNodes [i]);
                invoke (adresseAjax, "supprimerQuestion", "idQuestion=" + idQ, document.createElement ('div'));              	
              	for (j = i ; j < jsOrdre.length ; j++)
              		jsOrdre [j] = jsOrdre [j + 1];
	        jsOrdre.length--;
              	break;
              default:location.replace  ("${pageContext.request.contextPath}/controleurs/action/Redirection?comm=" + nomCommande + "&q=" + idQ);
            }
          }
          
          function editerQuestion (idQ)
          {
            document.getElementById ("editerQuestion").style.display = "block";
            invoke (adresseAjax, "voirFormulaire", "idQuestion=" + idQ, 'formEdition');
            window.scrollTo (0, 0);
          }
      </script>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Interface Admin du groupe</h1>
  <p></p>
  <h2>${actionBean.nomGroupe}</h2>
  <p>Modifier les questions du sondage '${actionBean.nomSondage}'</p>
  <p>Ajouter une question : ${actionBean.ajouterQuestion}</p>
  <fieldset><legend>Questions :</legend>
  <div id="editerQuestion"><h1>Editer la question</h1><div id="formEdition"></div></div>
  <div class="ulQuestions" id="questions">${actionBean.questions}</div>
  </fieldset>
</stripes:layout-component>

</stripes:layout-render>
