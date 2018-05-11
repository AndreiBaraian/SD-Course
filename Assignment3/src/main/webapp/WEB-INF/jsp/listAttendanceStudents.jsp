
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

<title>List Students</title>
</head>
<body>
<form id="form" method="post" action="student/labs">
	<input type="hidden" name="studentId" id="studentId">
	<input type="hidden" name="labId" id="labId" value="${labId }">
	<table border="2" class="hoverTable">
		<thead>
			<tr>
				<th>Select</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody id="student">
		</tbody>
	</table>
	
	<button type="button" class="approveBtn" id="approve-btn"
			style="width: 300px; position: relative; right: 400px;padding: 20px" >Submit Attendance</button>
	
</form>
					
	<script>
	
	$(document).ready(function(){
        $.ajax({

            url: 'http://localhost:8080/student',
            type: 'GET',
            dataType: 'JSON',
            success: function(data){
                $(data).each(function(){ 
                    $('#student').append('<tr><td><input type="radio" name="id" value="' + this.id + '"</td><td>' + this.name + '</td></tr>');
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
			
			var relativeurl = "?labId=" + $(labId).val() + "&" + "studentId=" + id
			console.log(relativeurl);		
			
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "http://localhost:8080/attendance/" + relativeurl,
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
		
	
	</script>
</body>
</html>