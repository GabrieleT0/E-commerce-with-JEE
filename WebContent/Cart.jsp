<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.*, it.unisa.model.ProductBean, it.unisa.model.Cart,  it.unisa.model.ClientBean"%>


<% Cart<ProductBean> cart = (Cart<ProductBean>)session.getAttribute("carrello"); 
	ClientBean cliente = (ClientBean) session.getAttribute("account");
	if(cart == null) {
		response.sendRedirect(response.encodeRedirectURL("./CartControl"));
		return;
	}	
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="immagini/favicon.ico" type="image/ico">
<link rel="stylesheet" href="css/AdminView.css">
<link rel="stylesheet" href="css/Cart.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Carrello</title>
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
<!-- ********************************************************************************************************** -->
<br>

<div class="container-sm " id="box">	
		<div class=head>Carrello</div> 



<%
	List<ProductBean> prodcart = cart.getItems();
	
	if(prodcart.size() > 0) {
		
%>


	
<%} %>
<div class="table-responsive-lg">
	<table class="table table-sm" id="table2">
	

	<% 
	double totale=0;
	if(prodcart.size() > 0) {
		for(ProductBean prod: prodcart) {
	%>
		<tr>
			<td> <img src="./GetFoto?codice=<%=prod.getCodice()%>" onerror="this.src='./immagini/noimg.png'" style="width:130px;height:130px"></td>
			<td> <%=prod.getNome()%></td>
			<td><a href="<%=response.encodeURL("CartControl?action=deleteCart&codice=" +prod.getCodice())%>" class="conferma">Rimuovi</a></td>
			<td >€<%=prod.getPrezzo()%></td>
			<%totale=totale+prod.getPrezzo();%>
		
		</tr>
	
	<%	} 	%><tr><td colspan="4" align="right" id="totale">Totale: €<%=totale%></td><tr>
	
	<tr><td colspan="4" align="right" id="acquista"><a href="<%=response.encodeRedirectURL("CartControl?action=clearCart")%>" type="button" id="svuota" class="btn btn-danger  btn-sm">Svuota carrello </a>&nbsp;
		<a href="<%=response.encodeRedirectURL("RiepilogoOrdine.jsp")%>" type="button" id="acq" class="btn btn-success">Procedi all'ordine</a>
		</td></tr>
	
	
	
	<% } else {
	%>
		<tr><td colspan="4" id="vuoto"> Nessun prodotto nel carrello</td></tr>
	<%
	}
	%>
</table>
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
<!-- **************************************************************************************************** -->

<script>
$('.conferma').on('click', function () {
    return confirm('Sei sicuro di voler cancellare il prodotto dal carrello ?');
});

$('.btn-danger').on('click', function () {
    return confirm('Sei sicuro di voler svuotare il carrello ?');
});
</script>
</body>
</html>