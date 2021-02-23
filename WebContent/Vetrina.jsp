<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.unisa.model.ProductBean, it.unisa.model.Cart, it.unisa.model.ClientBean"%>
<%
	Collection<?> prodotti =(Collection<?>) request.getAttribute("prodotti");
	
	String error= (String) request.getAttribute("error");
	
	if((prodotti==null && error==null)){
		response.sendRedirect(response.encodeRedirectURL("./ProductControl"));
		return;
	}
	
	Cart<ProductBean> cart = (Cart<ProductBean>)request.getAttribute("cart");
	
	if(cart == null) {
 		response.sendRedirect(response.encodeRedirectURL("./ProductControl"));
 		return;
 	}	
	
	ClientBean cliente = (ClientBean) session.getAttribute("account");

	ProductBean prodotto = (ProductBean) request.getAttribute("prodotto");
%>
<!DOCTYPE html>
<html>
<head>
<title>Cartomix</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
 <link rel="icon" href="immagini/favicon.ico" type="image/ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
 <link rel="icon" href="immagini/favicon.ico" type="image/ico">
<link rel="stylesheet" href="css/AdminView.css">
<link rel="stylesheet" href="css/Vetrina.css">
<link rel="stylesheet" href="css/ProductManage.css">
<link rel="stylesheet" href="css/Cart.css">
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
         <a class="nav-link" href="<%=response.encodeRedirectURL("AreaPersonale.jsp")%>">Ciao <%=cliente.getNome()%></a> 
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
<!-- **************************************************************************************************************** -->

<br>

<div class="container-sm " id="box">	

<div id="demo" class="carousel slide" data-ride="carousel">
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
  </ul>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="immagini/banner1.jpg" alt="Los Angeles" style="width:100% ; height:100%">
      <div class="carousel-caption">
      </div>   
    </div>
    <div class="carousel-item">
      <img src="immagini/banner2.jpg" alt="Chicago"  style="width:100% ; height::100%">
      <div class="carousel-caption">
     
      </div>   
    </div>
    <div class="carousel-item">
      <img src="immagini/banner3.jpg" alt="New York" style="width:100% ; height::100%">
      <div class="carousel-caption">
      </div>   
    </div>
  </div>
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>

	<div class="row">
	
<%
		if(prodotti !=null && prodotti.size()>0){
			
			Iterator<?> it=prodotti.iterator();
			while(it.hasNext()){
				ProductBean bean= (ProductBean) it.next();
%>
	 <div class="col-12 col-md-12 col-lg-4 col-xl-4 py-2">
	<div class="card card-body h-100" >
	<img class="zoom card-img-top mx-auto d-block" src="./GetFoto?codice=<%=bean.getCodice()%>" onerror="this.src='./immagini/noimg.png'" style="width:200px;">
  <div class="card-body">
   <h4 class="card-title"><a href="<%=response.encodeURL("ProductPageControl?action=details&codice=" +bean.getCodice())%>"> <%=bean.getNome()%></a></h4>
    <p class="card-text"><a href="<%=response.encodeURL("ProductPageControl?action=details&codice=" +bean.getCodice())%>"><%=bean.getDescrizione() %></a></p>
   <br>
    <a href="<%=response.encodeURL("ProductPageControl?action=details&codice=" +bean.getCodice())%>" class="btn btn-primary" id="prezzo">€<%=bean.getPrezzo() %></a> 
    <br><br>
    <a href="<%=response.encodeURL("ProductControl?action=addCart&codice=" +bean.getCodice())%>" class="btn btn-outline-primary" >Aggiungi al carrello </a>
 
  		</div>
	</div>	
    </div>


<%
		} %>
	
		</div>
<%}  else {  %>
  	
  		<div id="titolo" > Nessun prodotto disponibile</div>
	</div>

<% } %>


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