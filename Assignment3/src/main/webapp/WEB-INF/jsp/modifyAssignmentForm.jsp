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
<title>Modify Assignment</title>
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
						<td><input type="hidden" name=assignmentId id="assignmentId"
							value="${assignment.id}" />
						</td>
					</tr>
					<tr>
						<td>Assignment Name</td>
						<td><input name=name id="name"
							value="${assignment.name}" />
						</td>
					</tr>
					<tr>
						<td>Deadline</td>
						<td><input name="deadline" id="deadline"
							value="${assignment.deadline}" />
						</td>
					</tr>
					<tr>
						<td>Description</td>
						<td><input name="description" id="description"
							value="${assignment.description}" />
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
	var id = ${assignment.id};
	sendUpdateData("#modify-btn","assignment/"+id,"#myForm");
	</script>
</body>
</html>