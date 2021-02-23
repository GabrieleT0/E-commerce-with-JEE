<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
#error {
color:red;
font-weight:bolder;
font-size:18px;
}
.error {
		background-color: red;
		}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="icon" href="immagini/favicon.ico" type="image/ico">
<link rel="stylesheet" href="css/Registration.css">
<link rel="stylesheet" href="css/AdminView.css">
<link rel="stylesheet" href="css/AreaPersonale.css">
<link rel="stylesheet" href="css/Cart.css">
<meta charset="ISO-8859-1">
<title>Login Error</title>
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
<!-- ********************************************************************************* -->

<br>

<div class="container-sm text-white h-100" id="box">
	<form action="<%=response.encodeRedirectURL("RegistrationControl")%>" method="POST" onsubmit="event.preventDefault();  validate(this)">
	<div class="form-group col-md-5 mx-auto ">
		<h2><span class="badge badge-secondary" style="background-color: #48B072">Login</span></h2>
		<input type="hidden" name="action" value="login" >
		<div class="alert alert-danger">
    		<strong>Errore!</strong> Email o password errati
  		</div>
	
		<p>Email:</p>
		<input id="email" name="email" type="email" class="form-control"  maxlength="100" placeholder="example@gmail.com" required><br>
		
		<p>Password:</p>
		<input id="pass" name="password" type="password" class="form-control"  min="8" maxlength="64" placeholder="Inserisci password" required><br>
	
		<input type="submit" value="Login" class="btn btn-outline-primary">
		<input type="reset" value="Resetta i campi" class="btn btn-outline-danger">
	</div>
</form>
</div>
<script>
function validate(obj) {	
	var valid = true
function checkEmail(inputtxt) {
	var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(inputtxt.value.match(email)) 
		return true
	
	return false
}

var email = document.getElementsByName("email")[0];
if(!checkEmail(email)) {
	valid = false;
	email.classList.add("error");
} else {
	email.classList.remove("error");

}

if(valid) obj.submit();
}
</script>

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
<!-- *********************************************************************************************************** -->
</body>
</html>