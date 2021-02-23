function checkempty(form) { 
            if (form.codice.value == null ||  form.codice.value == undefined || form.codice.value.length == 0) { 
                
                alert("Il campo codice non può essere vuoto \n"); 
                return false; 
            } if (form.nome.value == null ||  form.nome.value == undefined || form.nome.value.length == 0)  { 
                alert("Il campo nome non può essere vuoto\n"); 
                return false; 
            } if (form.prezzo.value == null ||  form.prezzo.value == undefined || form.prezzo.value.length == 0 || form.prezzo.value == 0)  { 
                alert("Il campo prezzo non può essere vuoto oppure 0 \n"); 
                return false; 
			} else return true;
				
} 