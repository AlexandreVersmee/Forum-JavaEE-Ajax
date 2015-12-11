$(document).ready(function()
{
	/* SET PROPERTIES OF DIALOGS */
	$("#dialog").dialog({
      autoOpen: false,
      show: {
        effect: "slide",
        duration: 1000
      },
      hide: {
        effect: "fade",
        duration: 1000
      }
    });
	$("#dialogEdit").dialog({
	      autoOpen: false,
	      show: {
	        effect: "slide",
	        duration: 1000
	      },
	      hide: {
	        effect: "fade",
	        duration: 1000
	      }
	    });
	
	/* BUTTON'S EVENT */
	$("#newMessage").on("click",function(){
		$("#dialogEdit").dialog("close");
		$("#dialog").dialog("open");
	})

	/* START OF AJAX PART */
	displayListMessage();			  
	
	/*  GET REQUEST  */	
	function displayListMessage()
	{
		console.log("GET ALL");
		$.ajax({
			type : "GET",
			url : window.location.href + "/ajax",
			contentType : 'application/json',
			success : function(response) {
 				$.each(response, function(index, currEmp)
				{
 					$('#messageList').append('<div class="row">'+
								'<div class="large-12 columns answer child-answer">'+
									'<div class="row">'+
										'<div class="large-10 columns">'+
											'<span class="orange"><i class="fa fa-user"></i> Message de '+currEmp["membre"].name+' </span> <b>-</b> '+currEmp["texte"]+
										'</div>'+
										'<div class="large-2  columns">'+
										'<ul class="button-group right">'+
											'<li><a id="editLink" title="Modifier" href="/Forum-Bovier-Versmee/discussion/'+currEmp["fildiscussion"].id+'/message/'+currEmp.id+'" class="edit-comment fa-padding"><i class="fa fa-edit"></i></a></li>'+
											'<li><a id="deleteLink" tite="Supprimer" href="/Forum-Bovier-Versmee/discussion/'+currEmp["fildiscussion"].id+'/delete/'+currEmp.id+'"><i class="fa fa-trash"></i></a></li>'+
										'</ul>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>');
				});  				 				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("error: " + textStatus);
				alert("error: " + errorThrown);
			}
		});
	}

	
	/*  POST REQUEST TO ADD MESSAGE */
	$('#addMessage').submit(function(event)
	{
		console.log("ADD");
		var message = $('#addMessage #texte').val();
		var json = { "texte" : message};
		 $.ajax({
		 	type : "POST",
		 	headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		 	url : $('#addMessage').attr( "action"),
		 	data : JSON.stringify(json),
		 	dataType : 'json',
		 	success : function(response) {
		 		if (response.statut == "KO")
		 			alert(response.message);
		 		else
	 			{
			 		$( "#dialog" ).dialog( "close" );
			 		$('#texte').val('');
			 		$('#messageList').html('');
			 		displayListMessage();
	 			}
		 	},
				 error : function(jqXHR, textStatus, errorThrown) {
				alert("error: " + textStatus);
				alert("error: " + errorThrown);
			} 
		});
		event.preventDefault();
	});
		
		/*  POST REQUEST TO DELETE MESSAGE */
		$('#messageList').on("click", "a#deleteLink", function(event)
		{
			console.log("DELETE");	
			 $.ajax({
				 type : "POST",
			 	url : $(this).attr("href"),
			 	headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
				dataType : 'json',
			 	success : function(response) {
			 		if (response.statut == "KO")
			 			alert(response.message);
			 		else
		 			{
				 		$('#messageList').html('');
				 		displayListMessage();			 			
		 			}
			 	},
 				 error : function(jqXHR, textStatus, errorThrown) {
					alert("error: " + textStatus);
					alert("error: " + errorThrown);
				} 
			});
			event.preventDefault();
		});
				
		/*  GET REQUEST TO EDIT MESSAGE */
	 	$('#messageList').on("click", "a#editLink", function(event)
		{		
	 		console.log("EDIT GET");
	 		$("#dialog").dialog("close");
	 		$("#dialogEdit").dialog("open");	
			 $.ajax({
				 type : "GET",
			 	url : $(this).attr("href"),
			 	contentType : 'application/json',
			 	success : function(response) {
			 		$('#editId').val(response.id).hide();
			 		$('.editField').val(response.texte);			 		
			 		$('#messageList').html('');
			 		displayListMessage();
			 	},
 				 error : function(jqXHR, textStatus, errorThrown) {
					alert("error: " + textStatus);
					alert("error: " + errorThrown);
				} 
			});
			event.preventDefault();
		});
		
	 	/*  POST REQUEST TO EDIT MESSAGE */
		$("#btnEditSubmit").on("click", function(event)
		{
			console.log("EDIT POST");
			var message = $('#editMessage #texte').val();
			var json = { "texte" : message};	
			var id = $('#editId').val();
			var url =  $('#editMessage').attr( "action");
			url += id;
			 $.ajax({
			 	type : "POST",
			 	headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			 	url : url,
			 	data : JSON.stringify(json),
			 	dataType : 'json',
			 	success : function() {
			 		$( "#dialogEdit" ).dialog( "close" );
			 		$('#editMessage #texte').val('');
			 		$('#messageList').html('');
			 		displayListMessage();
			 	},
 				 error : function(jqXHR, textStatus, errorThrown) {
					alert("error: " + textStatus);
					alert("error: " + errorThrown);
				} 
			});
			event.preventDefault();
		}); 
		 
	});
