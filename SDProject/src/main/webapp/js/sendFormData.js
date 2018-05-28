

function sendFormData(buttonId, relativeURL, form) {
	jQuery(buttonId).on("click", function() {
		
		var myData = $(form).serializeArray().reduce(function(obj, item) {
		    obj[item.name] = item.value;
		    return obj;
		}, {});
		
		console.log(JSON.stringify(myData));
		//console.log(relativeURL);
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:8080/" + relativeURL,
			data : JSON.stringify(myData),
			dataType : 'html',
			timeout : 100000,
			success : function(response) {
				$("#root").html(response);
				console.log("SUCCESS: ", response);
			},
			error : function(xhr, e) {
				console.log("ERROR: ", e);
				console.log("Status: ",xhr.status);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
	}); 
	
}