<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- @author Varadi Robert -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/css/checkbox-style.css">
<link rel="stylesheet" href="/css/delete-btn.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/table-style.css">
<title>Delete labs</title>
</head>
<body>
	<table border="1" width="30%" cellpadding="3" class="hoverTable">
		<thead>
			<tr>
				<th>Laboratory Number</th>
				<th>Title</th>
				<th>Date</th>
				<th>Curricula</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${accountInformation}" var="entry">
				<tr>
					<td><input type="checkbox" value="${lab.id}"> <c:out value="${lab.id}"/></td>
					<td><c:out value="${lab.labNumber}" /></td>
					<td><c:out value="${lab.title}" /></td>
					<td><c:out value="${lab.date}" /></td>
					<td><c:out value="${lab.curricula}" /></td>
					<td><c:out value="${laboratory.description}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button type="button" class="deleteBtn" id="delete-accounts-btn"
				style="width: 300px; position: relative; left: 20px;">
				<span class="glyphicon glyphicon-trash"></span> Delete Selected
				Accounts
	</button>
	<script>
		jQuery("#delete-accounts-btn").on('click', function() {
			var checkedItems = [];
			$('input[type=checkbox]').each(function() {
				if (this.checked) {
					checkedItems.push($(this).val());
					this.checked = false;
					this.disabled = true;
				}
			});
			console.log(JSON.stringify({
				paramName : checkedItems
			}));
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "http://localhost:8080/deleteAccountsLogic",
				data : JSON.stringify(checkedItems),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		});
	</script>
	<script>
	$('input[type="checkbox"]').on('change', function() {
		   $(this).siblings('input[type="checkbox"]').prop('checked', false);
		});
	</script>
</body>
</html>
