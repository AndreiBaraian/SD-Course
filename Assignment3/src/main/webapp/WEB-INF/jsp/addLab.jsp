<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>


<head>
<link rel="stylesheet" href="/css/submit-reset-btn.css">
	<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
	<title>Registration</title>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/js/sendFormData.js"></script>
	
</head>
	<body>
		<form name = "myForm" id="myForm">
			<center>
				<table width = "30%" cellpadding = "7" frame="box" rules="none">
					<thead>
						<tr>
							<th colspan = "2"> Enter information here</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Lab Number</td>
							<td><input type="text" name="labNumber" id="labNumber" value="" /></td>
						</tr>
						<tr>
							<td>Title</td>
							<td><input type="text" name="title" id="title" value="" /></td>
						</tr>
						<tr>
							<td>Date</td>
							<td><input type="text" name="date" id="date" value="" /></td>
						</tr>
						<tr>
							<td>Curricula</td>
							<td><input type="text" name="curricula" id="curricula" value="" /></td>
						</tr>
						<tr>
							<td>Description</td>
							<td><input type="text" name="description" id="description" value="" /></td>
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
	
	$(document).ready(function () {
		    
		    $.validator.addMethod("dateTime", function (value, element) {
		        var stamp = value.split(" ");
		        var validDate = !/Invalid|NaN/.test(new Date(stamp[0]).toString());
		        var validTime = /^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$/i.test(stamp[1]);
		        return this.optional(element) || (validDate && validTime);
		    }, "Please enter a valid date and time.");
	});
		
	sendFormData("#submitButton", "lab", "#myForm");
	</script>
	
</html>