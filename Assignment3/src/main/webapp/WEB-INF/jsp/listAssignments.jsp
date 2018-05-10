
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ page import="hello.controllers.LaboratoryController"%>
<%@ page import="java.util.List"%>
<%@ page import="hello.apimodels.LaboratoryAPIModel"%>
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

<title>List Assignments</title>
</head>
<body>
<form id="form" method="post" action="/addAssignmentView">
	<input type="hidden" name="labId" id="labId" value="${labId }">
	<table border="2" class="hoverTable">
		<thead>
			<tr>
				<th>Select</th>
				<th>Name</th>
				<th>Deadline</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody id = "assignments">
		</tbody>
	</table>
	
	<button type="button" class="approveBtn" id="approve-btn"
			style="width: 300px; position: relative; right: 400px;padding: 20px" >Add</button>
</form>
	
	<button type="button" class="deleteBtn" id="delete-btn"
				style="width: 300px; position: relative; left: 20px;">
				<span class="glyphicon glyphicon-trash"></span> Delete Assignments
	</button>
	
	<button type="button" class="modifyBtn" id="modify-btn"
			style="width: 300px; position: relative; right: 400px;padding: 20px" >Modify</button>
			
	
					
	
	<script>
	
		$(document).ready(function(){
			
			var id = <c:out value="${labId}"/>;
			//console.log(id);
			
	        $.ajax({
	
	            url: 'http://localhost:8080/lab/' + id + '/assignments',
	            type: 'GET',
	            dataType: 'JSON',
	            success: function(data){
	                $(data).each(function(){ 
	                    $('#assignments').append('<tr><td><input type="radio" name="id" value="' + this.id + '"</td><td>' + this.name + '</td><td>' + this.deadline + '</td><td>' + this.description + '</td></tr>');
	                });
	
	            },
	            error: function(e){
	                console.log("ERROR: "+ e);
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
			//console.log(id);
			
			
			$.ajax({
				type : "DELETE",
				contentType : "application/json",
				url : "http://localhost:8080/assignment/" + id,
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

		document.getElementById("approve-btn").onclick = function () {
			$("#form").submit();
		}
		
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
			window.location.assign("http://localhost:8080/modifyAssignment/" + id);
		});
		
		
		$('input[type="checkbox"]').on('change', function() {
			   $('input[type="checkbox"]').not(this).prop('checked', false);
			});
	
	</script>
</body>
</html>