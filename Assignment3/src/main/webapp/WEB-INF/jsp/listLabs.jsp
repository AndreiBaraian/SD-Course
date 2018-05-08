
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

<title>List Laboratories</title>
</head>
<body>
<form id="form">
	<!-- <input type="hidden" name="labId" id="labId"> -->
	
	<table border="2" class="hoverTable">
		<thead>
			<tr>
				<th>Select</th>
				<th>Laboratory Number</th>
				<th>Title</th>
				<th>Date</th>
				<th>Curricula</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${laboratories}" var="lab">
				<tr class="row-which-triggers-popup"
						onclick="redirectToAccountDetails('${lab.id}')">
					<td><input type="checkbox" value="${lab.id}"></td>
					<td><c:out value="${lab.labNumber}" /></td>
					<td><c:out value="${lab.title}" /></td>
					<td><c:out value="${lab.date}" /></td>
					<td><c:out value="${lab.curricula}" /></td>
					<td><c:out value="${laboratory.description}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
	
	<div id="dialog" title="Assignment Details" style="display: none">
		<p>TEST</p>
		<c:out value="Hello" />
		<c:out value="Hi" />
		<c:out value="Salut" />
	</div>
	
	<script>
		function redirectToAccountDetails(id) {
			window.location.assign("http://localhost:8080/lab/assignments/" + id);
		}
	</script>	
	
</body>
</html>