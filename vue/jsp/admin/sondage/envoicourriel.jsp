<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/vue/_layout/eveAdmin.jsp">

<stripes:layout-component name="titre">Envoi de courriel - Sondage</stripes:layout-component>

<stripes:layout-component name="html-head">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vue/_layout/style/tableau.css"/>

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

	  function envoyerUnCourriel ()
	  {
	      if (document.getElementById ("votants").childNodes [0])
	      {
	        document.getElementById ('lien').innerHTML =  "(veuillez patienter";
	        for (i = 0 ; i < (progress % 4) ; i++) document.getElementById ('lien').innerHTML += ".";
	        document.getElementById ('lien').innerHTML += ")";
	        requestResponse ("${pageContext.request.contextPath}/controleurs/admin/sondage/EnvoyerCourriel",
	                         "envoi", "uid=" + document.getElementById ("votants").childNodes [0].id
	                                + "&texteM=" + document.getElementById ("texteM").value, envoyerUnCourriel);
	        document.getElementById ("votants").removeChild (document.getElementById ("votants").childNodes [0]);
	      }
	      else
		this.location = '../groupe/VoirGroupeAdmin';
	      progress++;
	  }

	  function demarrer ()
	  {
	      envoyerUnCourriel ();
	  }

	  var progress = 0;
	</script>
</stripes:layout-component>

<stripes:layout-component name="contenu">
  <p></p>
  <h1>Interface Admin du groupe</h1>
  <p></p>
  <h2>${actionBean.nomGroupe}</h2>
  <p>Envoyer un courriel aux votants du sondage '${actionBean.nomSondage}'</p>
 
  <br/><div id="lien"><a href="javascript:demarrer ()">D&eacute;marrer l'envoi (veuillez patienter apr&egrave;s avoir cliqu&eacute;)</a></div><br/><br/>

  Message :<br/>
  <textarea id="texteM" cols="80" rows="10">${actionBean.texteM}</textarea><br/>
  Votants :
  <span class="colonneGaucheEntete">Uid</span><span class="colonneDroiteEntete">Nom</span><div class="finLigneEntete"></div>
  <div id="votants">${actionBean.listeUid}</div>
  <span class="colonneGaucheBas"></span><span class="colonneDroiteBas"></span><div class="finLigneBas"></div>
</stripes:layout-component>

</stripes:layout-render>