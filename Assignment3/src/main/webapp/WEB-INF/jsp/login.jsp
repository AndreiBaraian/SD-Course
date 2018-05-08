

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="/css/submit-reset-btn.css">
		<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login Here</title>
	</head>
<body>
	<form method="post" action="/app-login">
		<center>
		<table width = "30%" cellpadding = "7" frame="box" rules="none">
			<thead>
				<tr>
					<th colspan = "2">Login Here</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>User Name</td>
					<td><input type = "text" name="username" value = "" autocomplete="off"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type = "password" name="password" value = "" autocomplete="off"/></td>
				</tr>
				<tr>
					<td><input type = "submit" class="submitResetBtn" value = "Login" /></td>
					<td><input type = "reset" class="submitResetBtn" value = "Reset" /></td>
					<td><input type = "button" class="submitResetBtn" value = "Register" id="registerButton" /></td>
				</tr>
			</tbody>
		</table>
		</center>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	
	<script>
	jQuery("#registerButton").on('click', function() {
		
		window.location.assign("http://localhost:8080/registerView");
		
	});
	</script>
	
</body>
</html>
