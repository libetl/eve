<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="/vue/_layout/eve.jsp">

<stripes:layout-component name="titre">Test du patron de conception "Etat"</stripes:layout-component>


<stripes:layout-component name="html-head">
</stripes:layout-component>

<stripes:layout-component name="contenu">
    <h1>Essais - patron de conception "Etat"</h1>

    <stripes:form action="/controleurs/tests/TestEtats" focus="">
     Appel de <blockquote><code>new Sondage ().modifierQuestions () ;</code> <stripes:submit name="modifierQuestions" value="Essayer"/> (Succ&egrave;s.)</blockquote>
     <br/><br/>
     Appel de <blockquote><code>new Sondage ().voter () ;</code> <stripes:submit name="voter" value="Essayer"/> (&Eacute;chec.)</blockquote> 
     <br/><br/>
     Appel de <blockquote><code>EtatSondage.get ("Vide") ;</code> <stripes:submit name="sondageVide" value="Essayer"/> (Succ&egrave;s.)</blockquote>
     <br/><br/>
     Appel de <blockquote><code>EtatSondage.get ("EtatInexistant") ;</code> <stripes:submit name="sondageEtatInexistant" value="Essayer"/> (&Eacute;chec.)</blockquote> 
    </stripes:form>
    
</stripes:layout-component>

</stripes:layout-render>