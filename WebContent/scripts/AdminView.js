// AJAX CALL FOR CLIENTS//

$(document).ready(function(){
	$('#button1').one('click',function(){	
		$.ajax({
			type:'GET',
			url:'AdminClientControl?action=showClients',
			headers : {
				Accept: "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
				
			},
			success : function(result){
				var clienti = $.parseJSON(result);
				var s = '';
				for(var i=0;i<clienti.length;i++){
					  tr = $('<tr/>');
				        tr.append("<td>" + clienti[i].email + "</td>");
				        tr.append("<td>" + clienti[i].nome + "</td>");
				        tr.append("<td>" + clienti[i].cognome + "</td>");
				        tr.append("<td>" + clienti[i].city+ "</td>");
				        tr.append("<td>" + clienti[i].provincia + "</td>");
				        tr.append("<td>" + clienti[i].via + "</td>");
				        tr.append("<td>" + clienti[i].numeroCivico + "</td>");
				        tr.append("<td>" + clienti[i].CAP + "</td>");
				        tr.append("<td>" + clienti[i].numeroTelefono + "</td>");
				        $('#table1').append(tr);
				}
				
			}
			
		});
	});
	});
	
//AJAX CALL FOR ORDERS // 
$(document).ready(function(){
		$('#button2').one('click',function(){	
			$.ajax({
				type:'GET',
				url:'AdminClientControl?action=showOrders',
				headers : {
					Accept: "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
					
				},
				success : function(result){
					var ordini = $.parseJSON(result);
					var s = '';
					for(var i=0;i<ordini.length;i++){
						  tr = $('<tr/>');
					        tr.append("<td>" + ordini[i].numeroOrdine + "</td>");
					        tr.append("<td>" + ordini[i].email + "</td>");
					        tr.append("<td>" + ordini[i].dataOrdine + "</td>");
					        tr.append("<td>" + ordini[i].importoOrdine+ "</td>");
					        tr.append("<td>" + ordini[i].tipoPagamento+ "</td>");
					        tr.append('<td>'+ '<a href="AdminClientControl?action=details&numeroOrdine='+ ordini[i].numeroOrdine+'">Visualizza Ordine</a></td>');
					        tr.append('<td>'+ '<a href="AdminClientControl?action=deleteOrder&numeroOrdine='+ ordini[i].numeroOrdine+'">Cancella Ordine</a></td>');
					        $('#table2').append(tr);
					}
					
				}
				
			});
		});
		});

$(document).ready(function(){
	  $(".hide").click(function(){
	    $("table").hide();
	  });
	  $(".show").click(function(){
	    $("table").show();
	  });
	});		
		
		
		