
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
<link rel="stylesheet" href="/css/approve-btn.css">
<link rel="stylesheet" href="/css/table-style.css">
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>

<title>List Customers</title>
</head>
<body>
	<table border="2" class="hoverTable">
		<thead>
			<tr>
				<th>Name</th>
			</tr>
		</thead>
		<tbody id = "customer">
		</tbody>
	</table>
	
	<div>
	
	
	<input type="hidden" name="activityId" id="activityId" value="${activityId }">
					
	</div>
	
	<script>
	
	var relativeURL = "?activityId=" + $("#activityId").val();
	
	 $(document).ready(function(){
	        $.ajax({

	            url: 'http://localhost:8080/reservation/activity/' + $("#activityId").val(),
	            type: 'GET',
	            dataType: 'JSON',
	            success: function(data){
	            	console.log(data);
	                $(data).each(function(){ 
	                    $('#customer').append('<tr><td>' + this.customer.name + '</td><td>');
	                });

	            },
	            error: function(e){
	                console.log("EROOR: " + e);
	            }

	        });
	    });
	
		
		
		$('input[type="checkbox"]').on('change', function() {
			   $('input[type="checkbox"]').not(this).prop('checked', false);
			});
	
	</script>
</body>
</html>