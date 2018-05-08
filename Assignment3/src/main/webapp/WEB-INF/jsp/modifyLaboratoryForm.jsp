<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/css/modify-btn.css">
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<script src="/js/sendUpdateData.js"></script>
<title>Modify Account</title>
</head>
<body>

	<form id="myForm">
		<center>
			<table width=30% cellpadding="7" frame="box" rules="none">
				<thead>
					<tr>
						<th colspan="2">Enter information here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td><input type="hidden" name=labId id="labId"
							value="${laboratory.id}" />
						</td>
					</tr>
					<tr>
						<td>Laboratory Number</td>
						<td><input name=labNumber id="labNumber"
							value="${laboratory.labNumber}" />
						</td>
					</tr>
					<tr>
						<td>Title</td>
						<td><input name="title" id="title"
							value="${laboratory.title}" />
						</td>
					</tr>
					<tr>
						<td>Date</td>
						<td><input name="date" id="date"
							value="${laboratory.date}" />
						</td>

					</tr>
					<tr>
						<td>Curricula</td>
						<td><input name="curricula" id="curricula"
							value="${laboratory.curricula}" />
						</td>
					</tr>
					<tr>
						<td>Description</td>
						<td><input name="description" id="description"
							value="${laboratory.description}" />
						</td>
					</tr>
					<tr>
						<td><button type="button" class="modifyBtn" id="modify-btn"
								style="width: 100%; padding: 20px" value="Submit">Modify</button></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
	
	<script>
	var id = $('#labId').val();
	sendUpdateData("#modify-btn","lab/"+id,"#myForm");
	</script>
</body>
</html>