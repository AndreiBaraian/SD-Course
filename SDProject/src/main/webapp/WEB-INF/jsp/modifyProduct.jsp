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
<title>Extend Contract Expiration Date</title>
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
					<tr id="employee"></tr>
					<tr>
						<td><button type="button" class="modifyBtn" id="modify-btn"
								style="width: 100%; padding: 20px" value="Submit">Modify</button></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
	
					
	<script>
	
	$(document).ready(function(){
		
		var id = <c:out value="${productId}"/>;
		console.log(id);
		
        $.ajax({

            url: 'http://localhost:8080/product/' + id,
            type: 'GET',
            dataType: 'JSON',
            success: function(data){
            	
            	console.log(data);
            	var dispRented = '';
            	if(data.isRented)
            		dispRented = "true";
            	else(data.isRented)
            		dispRented = "false";
                 
               	$('#employee').append('<tr><td>Name</td><td><input name="name" id="name" value="' +
             			data.name + '"/></td></tr><tr><td>Model</td><td><input name="model" id="model" value="' +
             			data.model + '"/></td></tr><tr><td>Price Per Day</td><td><input name="pricePerDay" id="pricePerDay" value="' + 
             			data.pricePerDay + '"/></td></tr><tr><td>Rented</td><td><input name="isRented" id="isRented" value="' +
             			dispRented  + '" readonly/> </td>');
                

            },
            error: function(e){
                console.log("ERROR: " + e);
            }

        });
    });
	
               
	var id = <c:out value="${productId}"/>;
	sendUpdateData("#modify-btn","product/"+id,"#myForm");
	</script>
</body>
</html>