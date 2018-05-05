/**
 * @Author: Dan Mitrea,
 * @author Varadi Robert
 */

function approveDataBtn(buttonId, relativeURL) {
	jQuery(buttonId).on("click", function() {
		
		var myLab = new Object();
		myLab.labNumber = "2";
		myLab.date="2018-08-09 12:12";
		myLab.title="title";
		myLab.curricula="my curricula";
		myLab.description="description";
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:8080/" + relativeURL,
			data : JSON.stringify(myLab),
			dataType : 'html',
			timeout : 100000,
			success : function(response) {
				$("#root").html(response);
				console.log("SUCCESS: ", response);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}); 
}