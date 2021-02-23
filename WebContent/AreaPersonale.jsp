<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.unisa.model.ProductBean, it.unisa.model.Cart, it.unisa.model.ClientBean"%>
<%
	
ClientBean cliente = (ClientBean) session.getAttribute("account");
String error= (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
 <link rel="icon" href="immagini/favicon.ico" type="image/ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/AdminView.css">
<link rel="stylesheet" href="css/AreaPersonale.css">
<script src="scripts/AddressValidator.js"></script>
<meta charset="ISO-8859-1">
<title>Il mio account</title>
</head>

<body>
<% 
if(cliente==null){
response.sendRedirect(response.encodeRedirectURL("Login.jsp"));
 } else { %>

												<!-- NAVBAR -->
<nav class="navbar navbar-expand-md bg navbar-dark" style="background-color:#48B072">
  
  <a class="navbar-brand" href="<%=response.encodeRedirectURL("Vetrina.jsp")%>">
  <img src="immagini/logo2.jpg" alt="Logo" style=width:85px;>
	</a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav align-items-end   ">
      <li class="nav-item">
       <a class="nav-link"  href="<%=response.encodeRedirectURL("Vetrina.jsp")%>"><img src="immagini/Home.png" width="50"></a>
      </li>
        <li class="nav-item">
         <a class="nav-link" href="<%=response.encodeRedirectURL("AreaPersonale.jsp")%>">Ciao <%=cliente.getNome() %></a> 
        </li>
   <li class="nav-item">
        <a class="nav-link" href="<%=response.encodeRedirectURL("ContactUs.jsp")%>">Contact us</a>
      </li>  
  
  </ul>
  <form action="<%=response.encodeRedirectURL("SearchControl")%>"  id="form" class=" navbar-form d-flex justify-content-center w-100 navbar-left" method="POST" >
  	<input type="text" name="search" id="search" class="form-control mr-sm-2 " placeholder="Cosa stai cercando?" autocomplete="off" >
  <button type="submit" value="search" class="btn btn-success " id="button1"><span class="glyphicon glyphicon-search"></span>Ricerca</button>
 </form>
 <ul class="navbar-nav align-items-end ">
  <li class="nav-item">
    	<a class="nav-link" href="<%=response.encodeRedirectURL("OrderControl?action=ordini")%>">Ordini effettuati</a>
      </li>
  	<li class="nav-item">
    	<a id="img" class="nav-link" href="<%=response.encodeRedirectURL("Cart.jsp")%>"><img src="immagini/cart.png" width="60"></a>
      </li>
  	
  	</ul>
 </div>
</nav>
<!-- *********************************************************************************************************** -->

<br>

	<div class="container-sm " id="box">	
		<div class=head>Il mio account</div> 
		<%
	
%>
  <div class="card-deck">
    <div class="card">
      <div class="card-body text-center">
      <p  id="titolo">Attuale indirizzo di spedizione: <button type="button" id="editBadge" class="show badge badge-pill badge-info">Modifica</button>
    	<p class="corpo"><%=cliente.getCitta()%> <%=cliente.getProvincia()%><br><%=cliente.getcap()%>,<%=cliente.getVia()%>,<%=cliente.getNumerocivico()%></p>
    </div>
	</div>
	<div class="card">
    <div class="card-body text-center">
      <p id="titolo">Dati personali:</p>
    	<p class="corpo"><%=cliente.getEmail()%></p>
    	<p class="corpo"><%=cliente.getNome()%>&nbsp;<%=cliente.getCognome()%> </p>
    	
    </div>
    </div>
	</div>
	<br>
	<a href="<%=response.encodeRedirectURL("RegistrationControl?action=logout")%>" id="logout" class="btn btn-danger">Logout</a>
	<br><br>
	
	<form id=form action="<%=response.encodeRedirectURL("RegistrationControl")%>" method="POST" onsubmit="event.preventDefault();  validate(this)">
	<div class="form-group col-md-6 mx-auto ">
	<input type="hidden" name="action" value="updateInd" >
	<p>Provincia:</p>
		<input name="provincia" type="text"  class="form-control" maxlength="4" placeholder="Inserisci solo sigla" required value="<%=cliente.getProvincia()%>"><br>

		<p>Città:</p>
		<input name="citta" type="text"  class="form-control" maxlength="50" placeholder="Inserisci il nome della tua città" required value="<%=cliente.getCitta()%>"><br>
		
		<p>Via:</p>
		<input name="via" type="text"  class="form-control" maxlength="150" placeholder="Inserisci il nome della tua via" required value="<%=cliente.getVia()%>"><br>
		
		<p>Numero civico:</p>
		<input name="numeroCivico" type="text"  class="form-control" maxlength="10" placeholder="Inserisci numero civico" required value="<%=cliente.getNumerocivico()%>"><br>
	
		<p>CAP:</p>
		<input name="cap" type="text"  class="form-control" maxlength="5" placeholder="Inserisci CAP" required value="<%=cliente.getcap()%>"><br>
	
	
		<input type="submit" value="Modifica Indirizzo"  id="edit" class="btn btn-outline-primary">
		<input type="reset" value="Resetta i campi"  class="btn btn-outline-danger">
		<button type="button" id="close" class=" hide badge badge-pill badge-info">Chiudi senza modificare</button>
		
	</div>
<br>
</form>
</div>	

													<!-- FOOTER -->
<div class="wrapper">
<div class="push"></div>
 </div>
 <div class="footer ">
 <div class="container-fluid">
  <div class="row text-center">
      
		<div class="col-lg-12">
      <a class="fb-ic" href="https://www.facebook.com/Cartomix-221221168029326">
            <img src="immagini/fb.png" width="40">
          </a>
         
         
          <!--Instagram-->
          <a class="ins-ic" href="https://www.instagram.com/_cartomix_/?igshid=yo7j9nif0es6">
              <img src="immagini/ig.png" width="45">
          </a>
          </div>
          <div class="col-lg-12">
          <!--Contatti-->
          <a href="<%=response.encodeRedirectURL("ContactUs.jsp")%>">
             Contattaci
          </a>
       </div>
 	<div class="col-lg-12">
  <div class="footer-copyright text-center py-3">© 2020 Copyright:
    <a href="" style="color : black"> cartomix-shop.com</a>
	</div>
</div>
</div>
</div>
</div>
<!-- ***************************************************************************************************** -->

<%} %>
<script>
$(document).ready(function(){
	  $(".hide").click(function(){
	    $("form").hide();
	  });
	  $(".show").click(function(){
	    $("form").show();
	  });
	});

$('#logout').on('click', function () {
    return confirm('Sei sicuro di voler uscire?');
});
</script>
</body>
</html>