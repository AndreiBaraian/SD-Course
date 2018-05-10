
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
		<tbody id="lab">
		</tbody>
	</table>
</form>
	
	
	<script>
	
	$(document).ready(function(){
        $.ajax({

            url: 'http://localhost:8080/lab',
            type: 'GET',
            dataType: 'JSON',
            success: function(data){
                $(data).each(function(){ 
                    $('#lab').append('<tr class="row-which-triggers-popup" onclick="goToAssignments(' + this.id + ')"><td>' + this.labNumber + '</td><td>' + this.title + '</td><td>' + this.date + '</td><td>' + this.curricula + '</td><td>' + this.description + '</td></tr>');
                });

            },
            error: function(data){
                alert("4");
            }

        });
    });
	
	
	function goToAssignments(id) {
		window.location.assign("http://localhost:8080/lab/assignments/" + id);
	}
	</script>	
	
</body>
</html>