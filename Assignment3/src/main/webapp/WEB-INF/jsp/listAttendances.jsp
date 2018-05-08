
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
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>

<title>List Attendances</title>
</head>
<body>
	<table border="2" class="hoverTable">
		<thead>
			<tr>
				<th>Select</th>
				<th>Student Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${attendances}" var="att">
				<tr>
					<td><input type="checkbox" value="${att.id}"></td>
					<td><c:out value="${att.student.name}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button type="button" class="deleteBtn" id="delete-btn"
				style="width: 300px; position: relative; left: 20px;">
				<span class="glyphicon glyphicon-trash"></span> Delete Selected
				Accounts
	</button>
					
	
	<script>
		jQuery("#delete-btn").on('click', function() {
			var id;
			$('input[type=checkbox]').each(function() {
				if (this.checked) {
					id = $(this).val();
					this.checked = false;
					this.disabled = true;
					
				}
			});
			console.log(id);
			
			var queryParams = "?attId=" + id;
			
			$.ajax({
				type : "DELETE",
				contentType : "application/json",
				url : "http://localhost:8080/attendance/" + queryParams,
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
		
		
		$('input[type="checkbox"]').on('change', function() {
			   $('input[type="checkbox"]').not(this).prop('checked', false);
			});
	
	</script>
</body>
</html>