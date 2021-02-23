<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.unisa.model.ProductBean, it.unisa.model.Cart, it.unisa.model.ClientBean, it.unisa.model.OrderBean "%>
<%
String autorizzazione = request.getHeader("Authorization");
String error= (String) request.getAttribute("error");
Collection<?> ordini =(Collection<?>) request.getAttribute("ordini");

	if(autorizzazione == null){
		response.sendRedirect(response.encodeRedirectURL("./protect"));
		return;
	}
%>
<html>
<head>
 <link rel="icon" href="immagini/favicon.ico" type="image/ico">
<link rel="stylesheet" href="css/AdminView.css">
<link rel="stylesheet" href="css/AreaPersonale.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script src="scripts/AdminView.js" type="text/javascript"></script>
</head>
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
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="<%=response.encodeRedirectURL("ProductManage.jsp")%>">Catalogo Prodotti</a>
      </li>
    <li class="nav-item">
    	<button type="button" class="badge" id="button2" >Mostra Ordini</button>	
  	</li>	
  	<li class="nav-item">	
  		<button type="button" id="button1"class="badge">Mostra utenti registrati</button>
    </li>
    </ul>
  </div>
</nav>
<!-- ******************************************************************************* -->

<br>
<div class="container-sm" id="box">	
	
	<div class=head>Utenti registrati al sito</div>
	<div class="table-responsive-lg ">
	<table class="table table-bordered table-sm" id="table1">
		<thead class="thead">
			<tr>
			<th>Email</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Città</th>
			<th>Provincia</th>
			<th>Via</th>
			<th>Numero Civico</th>
			<th>CAP</th>
			<th>Numero Telefono</th>
		</tr>
	</thead>
	</table>
	</div>

		
<div class=head>Ordini effettuati</div>
	<div class="table-responsive-lg ">
	<table class="table table-bordered table-sm" id="table2">
	<thead class="thead">
		<tr>
			<th>Numero Ordine</th>
			<th>Email</th>
			<th>Data Ordine</th>
			<th>Importo Ordine</th>
			<th>TipoPagamento</th>
			<th colspan=2>Action</th>
		</tr>
	</thead>
	</table>
	</div>
	
<div class="container">
	<button type="button" class=" hide badge badge-pill badge-info">Chiudi tabelle</button>
	<button type="button" class=" show badge badge-pill badge-info">Mostra tabelle</button>
</div>
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
<!-- ******************************************************************************************************** -->
</body>
</html>