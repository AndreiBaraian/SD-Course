
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
	<input type="hidden" name="assignmentId" id="assignmentId" value="${assignmentId }">
	<input type="hidden" name="studentId" id="studentId" value="${sessionScope.userId }">
	<table border="2" class="hoverTable" id="myTable">
		<thead>
			<tr>
				<th>Select</th>
				<th>Student Name</th>
				<th>Git Repository Link</th>
				<th>Remark</th>
				<th>Number Of Submissions</th>
				<th>Grade</th>
			</tr>
		</thead>
		<tbody id = "submission">
		</tbody>
	</table>
	
	<button type="button" class="approveBtn" id="approve-btn"
				style="width: 300px; position: relative; left: 20px;">
				 Submit Grade
	</button>
			
	
					
	
	<script>
	
		$(document).ready(function(){
			
			var assignmentId = $("#assignmentId").val();
			
	        $.ajax({
	
	            url: 'http://localhost:8080/submission/' + assignmentId,
	            type: 'GET',
	            dataType: 'JSON',
	            success: function(data){
	                $(data).each(function(){ 
	                    $('#submission').append('<tr><td><input type="radio" name="id" value="' + this.id + '"</td><td>' + this.student.name + '</td><td>' + this.gitRepositoryLink + '</td><td>' + this.remark + '</td><td>' + this.numberOfSubmissions + '</td><td><input type="text" id="grade' + this.id + '" value="' + this.grade + '"></div></td></tr>');
	                });
	
	            },
	            error: function(e){
	                console.log("ERROR: "+ e);
	            }
	
	        });
	    });
	
	
		jQuery("#approve-btn").on('click', function() {
			var id;
			var cnt = 0;
			$('input[type=radio]').each(function() {
				if (this.checked) {
					id = $(this).val();
					this.checked = false;
					this.disabled = true;
					cnt++;
				}
			});
			console.log(cnt);
			
			
			var grade = $("#grade"+id).val();
			console.log(grade);
			
			var relativeURL = "?subId=" + id + "&grade=" + grade; 
				
			$.ajax({
				type : "PUT",
				contentType : "application/json",
				url : "http://localhost:8080/submission/" + relativeURL,
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
	
	</script>
</body>
</html>