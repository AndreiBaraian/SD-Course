/**
 * @Author: Dan Mitrea,
 * @author Varadi Robert
 */

function approveDataBtn(buttonId, relativeURL) {
	jQuery(buttonId).on("click", function() {
		var checkedItems = [];
		$('input[type=checkbox]').each(function() {
			if(this.checked) {
				checkedItems.push($(this).val());
			}
		});
		console.log(JSON.stringify({
			paramName : checkedItems
		}));
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:8080/" + relativeURL,
			data : JSON.stringify(checkedItems),
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