<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>


<head>
<link rel="stylesheet" href="/css/submit-reset-btn.css">
	<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
	<title>Assignment</title>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/js/sendFormData.js"></script>
	
</head>
	<body>
		<form name = "myForm" id="myForm">
		
			<center>
				<div class="popup">
					<span class="poputext" id="invalidUserNamePopup" style="color: red;"></span>
				</div>
				<div class="popup">
					<span class="popuptext" id="invalidPasswordPopup" style="color: red;"></span>
				</div>
				<div class="popup">
					<span class="popuptext" id="invalidFullNamePopup" style="color: red;"></span>
				</div>
				<div class="popup">
					<span class="popuptext" id="invalidCountryPopup" style="color:red;"></span>
				</div>
				<div class="popup">
					<span class="popuptext" id="invalidCountyPopup" style="color: red;"></span>
				</div>
				<div class="popup">
					<span class="popuptext" id="invalidCityPopup" style="color: red;"></span>
				</div>
				<div class="popup">
					<span class="popuptext" id="invalidStreetPopup" style="color: red;"></span>
				</div>
				<div class="popup">
					<span class="popuptext" id="invalidNumberPopup" style="color: red;"></span>
				</div>
				<div class="popup">
					<span class="popuptext" id="invalidEmailPopup" style="color: red;"></span>
				</div>
				<table width = "30%" cellpadding = "7" frame="box" rules="none">
					<thead>
						<tr>
							<th colspan = "2"> Enter information here</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Name</td>
							<td><input type="text" name="name" id="assignmentName" value="" /></td>
						</tr>
						<tr>
							<td>Deadline</td>
							<td><input type="text" name="deadline" id="deadline" value="" /></td>
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
	sendFormData("#submitButton", "assignment/" + ${labId}, "#myForm");
	</script>
	
</html>