
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
<link rel="stylesheet" href="/css/table-style.css">
<link rel="stylesheet" href="/css/approve-btn.css">
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>

<title>List Laboratories</title>
</head>
<body>
	<table border="2" class="hoverTable">
		<thead>
			<tr>
				<th>Select</th>
				<th>Laboratory Number</th>
				<th>Title</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${laboratories}" var="lab">
				<tr>
					<td><input type="checkbox" value="${lab.id}"></td>
					<td><c:out value="${lab.labNumber}" /></td>
					<td><c:out value="${lab.title}" /></td>
					<td><c:out value="${lab.date}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button type="button" class="approveBtn" id="approve-btn"
			style="width: 300px; position: relative; right: 400px;padding: 20px" >Add Attendance</button>
	
	<button type="button" class="modifyBtn" id="modify-btn"
			style="width: 300px; position: relative; right: 400px;padding: 20px" >View Attendances</button>
					
	
	<script>
	
	
	 $(document).ready(function(){
	        $.ajax({

	            url: 'http://localhost:8080/lab',
	            type: 'GET',
	            dataType: 'JSON',
	            success: function(data){
	                $(data).each(function(){ 
	                    $('#lab').append('<tr><td><input type="radio" name="id" value="' + this.id + '"</td><td>' + this.labNumber + '</td><td>' + this.title + '</td><td>' + this.date + '</td></tr>');
	                });

	            },
	            error: function(e){
	                console.log("EROOR: " + e);
	            }

	        });
	    });
	
	
		jQuery("#approve-btn").on('click', function() {
			var id;
			$('input[type=radio]').each(function() {
				if (this.checked) {
					id = $(this).val();
					this.checked = false;
					this.disabled = true;
					
				}
			});
			console.log(id);
			
			window.location.assign("http://localhost:8080/addAttendanceView/" + id)

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
			console.log(id);
			
			var queryParams = "?labId=" + id;
			
			window.location.assign("http://localhost:8080/attendance/" + queryParams);

		});
		
		
		$('input[type="checkbox"]').on('change', function() {
			   $('input[type="checkbox"]').not(this).prop('checked', false);
			});
	
	</script>
</body>
</html>