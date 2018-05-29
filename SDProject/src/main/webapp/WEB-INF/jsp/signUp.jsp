
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

	<head>
	<link rel="stylesheet" href="/css/submit-reset-btn.css">
		<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
		<title>Registration</title>
	</head>
	<body>
		<form name="myForm" id="myForm" >
			<center>
				<table width = "30%" cellpadding = "7" frame="box" rules="none">
					<thead>
						<tr>
							<th colspan = "2"> Enter information here</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Name </td>
							<td><input type="text" name="name" id="name" value="" /></td>
						</tr>
						<tr>
							<td>Email </td>
							<td><input type="text" name="email" id="email" value="" /></td>
						</tr>
						<tr>
							<td>Username </td>
							<td><input type="text" name="username" id="username" value="" /></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" id="password" value="" /></td>
						</tr>
						<tr>
	                        <td><input type="button"  class="submitResetBtn" value="Submit" id="submitButton"/></td>
	                        <td><input type="reset" class="submitResetBtn" value="Reset" /></td>
	                    </tr>
					</tbody>
				</table>
			</center>
		</form>
	</body>
	
	
	<script>
	jQuery("#submitButton").on("click", function() {
		
		var myData = $("#myForm").serializeArray().reduce(function(obj, item) {
		    obj[item.name] = item.value;
		    return obj;
		}, {});
		
		console.log(JSON.stringify(myData));
		//console.log(relativeURL);
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:8080/customer",
			data : JSON.stringify(myData),
			dataType : 'html',
			timeout : 100000,
			success : function(response) {
				$("#root").html(response);
				console.log("SUCCESS: ", response);
			},
			error : function(xhr, status, error) {
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
	}); 
	</script>
</html>