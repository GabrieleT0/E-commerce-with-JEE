<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.unisa.model.ProductBean, it.unisa.model.ClientBean"%>
<% 

ClientBean cliente = (ClientBean) session.getAttribute("account");
Collection<?> prodottiS =(Collection<?>) request.getAttribute("prodottiS");

%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
 <link rel="icon" href="immagini/favicon.ico" type="image/ico">
<link rel="stylesheet" href="css/AdminView.css">
<link rel="stylesheet" href="css/Vetrina.css">
<link rel="stylesheet" href="css/ProductManage.css">
<link rel="stylesheet" href="css/Cart.css">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ricerca</title>
<link href="style/bootstrap.css" rel="stylesheet" type="text/css">
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
<!-- ****************************************************************************************************************** -->

<br>

<div class="container-sm "id="box">	

	<div class="row">
	
<%
		if(prodottiS !=null && prodottiS.size()>0){
			%>
			<div class="titolo col-12">Risultati ricerca</div>
			
			<% 
			Iterator<?> it=prodottiS.iterator();
			while(it.hasNext()){
				ProductBean bean= (ProductBean) it.next();
				
			%>
	
	 <div class="col-12 col-md-12 col-lg-4 col-xl-4 py-2">
	<div class="card card-body h-100" >
 <a href="<%=response.encodeURL("ProductPageControl?action=details&codice=" +bean.getCodice())%>"><img class="card-img-top mx-auto d-block" src="./GetFoto?codice=<%=bean.getCodice()%>" onerror="this.src='./immagini/noimg.png'" style="width:100px;"></a>
  <div class="card-body">
   <h4 class="card-title"><a href="<%=response.encodeURL("ProductPageControl?action=details&codice=" +bean.getCodice())%>"> <%=bean.getNome()%></a></h4>
    <p class="card-text"><a href="<%=response.encodeURL("ProductPageControl?action=details&codice=" +bean.getCodice())%>"><%=bean.getDescrizione() %></a></p>
   <br>
    <a href="<%=response.encodeURL("ProductPageControl?action=details&codice=" +bean.getCodice())%>"class="btn btn-primary" id="prezzo">€<%=bean.getPrezzo() %></a> 
    <br><br>
  	    <a href="<%=response.encodeURL("ProductControl?action=addCart&codice=" +bean.getCodice())%>" class="conferma btn btn-outline-primary" style="color : black">Aggiungi al carrello </a>	
  		</div>
	</div>	
    </div>


<% } %>
	</div>
<%	}else{%>
<div class=titolo>La ricerca non ha prodotto risultati</div> 
<%} %>
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
<!-- ********************************************************************************************************* -->

</body>
</html>