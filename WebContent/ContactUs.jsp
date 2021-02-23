<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import=" java.util.* ,it.unisa.model.ClientBean"%>

<%	ClientBean cliente = (ClientBean) session.getAttribute("account"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contatti</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="icon" href="immagini/favicon.ico" type="image/ico">
<link rel="stylesheet" href="css/AdminView.css">
<link rel="stylesheet" href="css/Cart.css">
<link rel="stylesheet" href="css/ContactUs.css">
<body>

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
        <% 
        if(cliente!=null){  %>
        <li class="nav-item">
         <a class="nav-link" href="<%=response.encodeRedirectURL("AreaPersonale.jsp")%>">Ciao <%=cliente.getNome() %></a> 
        </li>
      <%}else{ %>
       <li class="nav-item">
       <div class="nav-link"></div>
      <div class="btn-group" role="group">
    <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      Accesso
    </button>
    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
      <a class="dropdown-item" href="<%=response.encodeRedirectURL("Login.jsp")%>">Login</a>
      <a class="dropdown-item" href="<%=response.encodeRedirectURL("Registration.jsp")%>">Registrazione</a>
      </div>
      </div>
      </li>  <%} %>
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
<!-- ******************************************************************************************************************** -->

<br>

<div class="container-lg" id="box">	
		<div class=head>Contatti</div> 

<div class="text-center">
	<h4>Dove siamo:</h4>
	<div class="map-responsive">
	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3027.8006741897257!2d15.37747311540254!3d40.6342775793404!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x13396b477af48c3f%3A0x289fc76923c1a75c!2sCartomix!5e0!3m2!1sit!2sit!4v1594463260475!5m2!1sit!2sit" width="550" height="300" frameborder="0" style="border:0;" allowfullscreen aria-hidden="false" tabindex="0"></iframe>
	</div>
	<br>
	<h4>Ci trovi anche su:</h4>
		<a class="fb-ic" href="https://www.facebook.com/Cartomix-221221168029326">
       <img src="immagini/fb.png" width="60">
     	</a>
       	<a class="ins-ic" href="https://www.instagram.com/_cartomix_/?igshid=yo7j9nif0es6">
        <img src="immagini/ig.png" width="65">
         </a>
</div>
<h4>Numero di telefono: 0828-951340</h4>
<div class="text-center">
<button type="button" class="show btn btn-primary btn-lg">Inviaci un e-mail</button>
</div>
<br>

<form id=form action="<%=response.encodeRedirectURL("")%>" method="POST"  onsubmit="event.preventDefault();  validate(this)">
	<div class="form-group col-md-4 mx-auto ">
	<input type="hidden" name="action" value="updateInd">
		
		<p>Indirizzo e-mail personale:</p>
		<input name="email" type="email"  class="form-control"  placeholder="Inserisci e-mail" required><br>

		<p>Inserisci descrizione della richiesta:</p>
		<textarea name="richiesta" maxlength="500" rows="3" class="form-control" maxlength="50" placeholder="Inserisci richiesta" required></textarea><br>
	
	
		<input type="submit" value="Invia"  class="hide btn btn-outline-primary">
		<input type="reset" value="Resetta i campi"  class="btn btn-outline-danger">
		
	</div>
</form>
<br>
</div>
  
 														<!-- FOOTER -->
  <div class="wrapper">
<div class="push"></div>
 </div>
 <div class="footer ">
 <div class="container-fluid">
  <div class="row text-center">
      
 	<div class="col-lg-12">
  <div class="footer-copyright text-center py-3">© 2020 Copyright:
    <a href="" style="color : black"> cartomix-shop.com</a>
	</div>
</div>
</div>
</div>
</div>
<!-- ******************************************************************************************** -->


<script>
$(document).ready(function(){
	  $(".hide").click(function(){
	    	alert("Messaggio inviato. Ti risponderemo a breve.")
		  $("form").hide();
	  });
	  $(".show").click(function(){
	    $("form").show();
	  });
	});

</script>
</body>
</html>