<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8" import="java.util.*, it.unisa.model.ProductBean" %>
<%
	Collection<?> prodotti =(Collection<?>) request.getAttribute("prodotti");
	String autorizzazione = request.getHeader("Authorization");
	String error= (String) request.getAttribute("error");
	Collection<?> prodottiS =(Collection<?>) request.getAttribute("prodottiS");
	
	if(autorizzazione == null){
		response.sendRedirect(response.encodeRedirectURL("./protect"));
		return;
	}
	
	if(prodotti==null && error==null){
		response.sendRedirect(response.encodeRedirectURL("./Admin"));
		return;
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="jquery-3.5.1.min.js"></script>
<script src="scripts/ProductManage.js"></script>
 <link rel="icon" href="immagini/favicon.ico" type="image/ico">
<link rel="stylesheet" href="css/AdminView.css">
<link rel="stylesheet" href="css/Vetrina.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cartomix</title>
<link href="css/ProductManage.css" rel="stylesheet" type="text/css">
</head>
<body >

														<!-- NAVBAR -->
<nav class="navbar navbar-expand-md bg navbar-dark" style="background-color:#48B072">

  <a class="navbar-brand" href="<%=response.encodeRedirectURL("Vetrina.jsp")%>">
  <img src="immagini/logo2.jpg" alt="Logo" style=width:85px;>
	</a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav ">
      <li class="nav-item">
        <a class="nav-link" href="<%=response.encodeRedirectURL("AdminView.jsp")%>">Gestione clienti e ordini</a>
     	 </li>
     	 <li class="nav-item">
      		<a class="nav-link" href="#section1">Vai a inserimento prodotto</a>
    	</li>
     </ul>
	
  	<form action="<%=response.encodeRedirectURL("SearchControl")%>"  id="form" class=" navbar-form d-flex justify-content-center w-100 navbar-left" method="POST" >
  	<input type="text" name="search" id="search" class="form-control mr-sm-2 " placeholder="Cosa stai cercando?" autocomplete="off" >
  <button type="submit" value="search" class="btn btn-success " id="button1" ><span class="glyphicon glyphicon-search"></span>Ricerca</button>
 </form>

  </div>
</nav>
<!-- ************************************************************************************************************* -->

<br>
<div class="container-sm" id="box">	
	<div class=titolo  >Gestione catalogo</div> 
	<div class="row">
	
<%
		if(prodotti !=null && prodotti.size()>0){
			
			Iterator<?> it=prodotti.iterator();
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
    <a href="#" class="btn btn-primary" id="prezzo">€<%=bean.getPrezzo() %></a> 
    <br><br>
    <a href="<%=response.encodeURL("Admin?action=delete&codice=" +bean.getCodice())%>" class="conferma btn btn-outline-danger" id="del">Cancella prodotto </a>
 
  		</div>
	</div>	
    </div>


<%
		} %>
	
		
<%		}	%>

</div>


<form action="<%=response.encodeRedirectURL("Admin")%>" method="POST" name="form1" onsubmit="return checkempty(form1)">
	<div class="form-group" id="section1">
		<p class=head>Inserimento prodotto</p>
		<input type="hidden" name="action" value="insert"> 
		
		<div class="col-10">
		<label for="codice">Codice:</label>
		<input name="codice" type="text" class="form-control" maxlength="20" placeholder="Inserisci codice prodotto" ><br>
		
		<label for="nome">Nome:</label>
		<input name="nome" type="text" class="form-control" maxlength="500" placeholder="Inserisci nome prodotto"  ><br>
		
		<label for="description">Descrizione</label><br>
		<textarea name="description" maxlength="500" rows="3" class="form-control" placeholder="Inserisci una descrizione" ></textarea>
		</div>
		
		<div class="col-2">
		<label for="prezzo">Prezzo:</label>
		<input name="prezzo" type="number" class="form-control" min="0" step="0.01" value="0" ><br>
		
		</div>
	
		<input type="submit" value="Inserisci" class="btn btn-light">
		<input type="reset" value="Reset" class="btn btn-danger">
	
	</div>
</form>

<p class="head">Carica Foto:</p>
<form action="UploadPhoto" enctype="multipart/form-data" method="post">
<div class="form-group">
	<div>Nome Prodotto:</div>
	<select name="codice"  class="custom-select">

<%
		if(prodotti !=null && prodotti.size()>0){
			
			Iterator<?> it=prodotti.iterator();
			while(it.hasNext()){
				ProductBean bean= (ProductBean) it.next();
				
	%> 
				
				<option value="<%=bean.getCodice()%>"><%=bean.getNome()%>&nbsp;</option>
				
<%
		}
	}	
%>	
	</select>
	<br>
	<input class="file" type="file" name="talkPhoto" value="" maxlength="255">	
	<br><br>
	<input type="submit" value="Upload">&nbsp;<input type="reset">
</div>
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
<!-- *************************************************************************************************** -->

<script>
$('.conferma').on('click', function () {
    return confirm('Sei sicuro di voler cancellare il prodotto?');
});
</script>
</body>
</html>