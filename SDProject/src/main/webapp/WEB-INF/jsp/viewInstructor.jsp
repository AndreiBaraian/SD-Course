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
<title>Instructor</title>
</head>
<body>

	<form id="myForm">
		<center>
			<table width=30% cellpadding="7" frame="box" rules="none">
				<thead>
					<tr>
						<th colspan="2">Instructor Details</th>
					</tr>
				</thead>
				<tbody>
					<tr id="employee"></tr>
				</tbody>
			</table>
		</center>
	</form>
	
					
	<script>
	
	$(document).ready(function(){
		
		var id = <c:out value="${instructorId}"/>;
		console.log(id);
		
        $.ajax({

            url: 'http://localhost:8080/employee/' + id,
            type: 'GET',
            dataType: 'JSON',
            success: function(data){
                 
               	$('#employee').append('<tr><td>Name</td><td><input name="name" id="name" value="' +
             			data.name + '" readonly/> </td>');
                

            },
            error: function(e){
                console.log("ERROR: " + e);
            }

        });
    });
	
         
	</script>
</body>
</html>