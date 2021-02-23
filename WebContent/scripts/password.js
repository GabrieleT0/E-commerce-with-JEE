$(document).ready(function(){
    $('#pass2').focusout(function(){
        var pass = $('#pass').val();
        var pass2 = $('#pass2').val();
        if(pass != pass2){
        	$("#pass2").css("background-color","#ff6666");
        	$("#pass").css("background-color","#ff6666");
        	alert(("Le password non coincidono"));        	
        }
		
    }	
    );
});

