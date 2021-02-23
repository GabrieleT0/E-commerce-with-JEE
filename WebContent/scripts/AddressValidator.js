function checkProv(inputtxt) {
	var name = /^[A-Za-z]+$/;
	if(inputtxt.value.match(name)) 
		return true

	return false
}

function checkcity(inputtxt) {
	var citta = /^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/;
	if(inputtxt.value.match(citta)) 
		return true

	return false
}

function checkCap(inputtxt) {
	var number2 = /^([0-9]{5})$/;
	if(inputtxt.value.match(number2)) 
		return true
	
	return false
}

function checkVia(inputtxt) {
	var via = /^[a-zA-Z0-9,. ]*$/;
	if(inputtxt.value.match(via)) 
		return true
	
	return false
}	

function validate(obj) {	
	var valid = true
		
	var provincia = document.getElementsByName("provincia")[0];
		if(!checkProv(provincia)) {
		valid = false;
		provincia.classList.add("error");
	} else {
		provincia.classList.remove("error");
	}
	
		var city = document.getElementsByName("citta")[0];
		if(!checkcity(city)) {
		valid = false;
		city.classList.add("error");
	} else {
		city.classList.remove("error");
	}
	
	var via = document.getElementsByName("via")[0];
	if(!checkVia(via)) {
		valid = false;
		via.classList.add("error");
	} else {
		via.classList.remove("error");
	}
	
	var numeroCivico = document.getElementsByName("numeroCivico")[0];
	if(!checkVia(numeroCivico)) {
		valid = false;
		numeroCivico.classList.add("error");
	} else {
		numeroCivico.classList.remove("error");
	}
	
	
	
		var number2 = document.getElementsByName("cap");
	for(var i=0; i < number2.length; i++) {
		if(!checkCap(number2[i])) {
			valid = false;
			number2[i].classList.add("error");
		} else  {
			number2[i].classList.remove("error");
		}
	}
	
		
	if(valid) obj.submit();
}
	