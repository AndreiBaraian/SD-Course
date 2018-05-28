
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/modify-btn.css">
<link rel="stylesheet" href="/css/checkbox-style.css">
<link rel="stylesheet" href="/css/delete-btn.css">
<link rel="stylesheet" href="/css/table-style.css">
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>

<title>List Laboratories</title>
</head>
<body>
	<table border="2" class="hoverTable">
		<thead>
			<tr>
				<th>Select</th>
				<th>Name</th>
				<th>Contract Reference</th>
				<th>Contract Expiration Date</th>
				<th>Rating</th>
			</tr>
		</thead>
		<tbody id = "lab">
		</tbody>
	</table>
	
	<div>
	
	<button type="button" class="deleteBtn" id="delete-btn"
				style="width: 300px; position: relative; left: 20px;">
				<span class="glyphicon glyphicon-trash"></span> Delete Employee
	</button>
	
	<button type="button" class="modifyBtn" id="modify-btn"
			style="width: 300px; position: relative; right: 400px;padding: 20px" >Extend Contract</button>
					
	</div>
	
	<script>
	
	 $(document).ready(function(){
	        $.ajax({

	            url: 'http://localhost:8080/employee',
	            type: 'GET',
	            dataType: 'JSON',
	            success: function(data){
	                $(data).each(function(){ 
	                    $('#lab').append('<tr><td><input type="radio" name="id" value="' + this.id + '"</td><td>' + this.name + '</td><td>' + this.contractReference + '</td><td>' + this.contractExpiration + '</td><td>' + this.rating + '</td></tr>');
	                });

	            },
	            error: function(e){
	                console.log("EROOR: " + e);
	            }

	        });
	    });
	
		jQuery("#delete-btn").on('click', function() {
			var id;
			$('input[type=radio]').each(function() {
				if (this.checked) {
					id = $(this).val();
					this.checked = false;
					this.disabled = true;
					
				}
			});
			
			$.ajax({
				type : "DELETE",
				contentType : "application/json",
				url : "http://localhost:8080/employee/" + id,
				dataType : 'json',
				timeout : 100000,
				success : function(response) {
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
		
		jQuery("#modify-btn").on('click', function() {
			var id;
			$('input[type=radio]').each(function() {
				if (this.checked) {
					id = $(this).val();
					this.checked = false;
					this.disabled = true;
					
				}
			});
			//console.log(id);
			window.location.assign("http://localhost:8080/modifyEmployeeFromAdmin/" + id);
		});
		
		
		$('input[type="checkbox"]').on('change', function() {
			   $('input[type="checkbox"]').not(this).prop('checked', false);
			});
	
	</script>
</body>
</html>