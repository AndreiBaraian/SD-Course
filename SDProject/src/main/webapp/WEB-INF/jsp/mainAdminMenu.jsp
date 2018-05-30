<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="UTF-8">
<title>Main Menu</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/sidebar-menu.css">
<link rel="stylesheet" href="/css/logout-btn.css">
</head>
<body>
	<%--<% response.setIntHeader("Refresh", 10); --%>
	<section style="width: 250px">
	<ul class="sidebar-menu">
		<li class="sidebar-header">Admin</li>

		<li><a href="#">
			<i class="glyphicon glyphicon-calendar"></i><span> Employees </span>
		</a>
		<ul class="sidebar-submenu">
			<li><a href="/listEmployees" target="page">
			<span>View Employees</span>
			</a>
		</ul>
		<ul class="sidebar-submenu">
			<li><a href="/addEmployeeView" target="page">
			<span>Add Employee</span>
			</a>
		</ul>
		</li>
		<li><a href="#">
			<i class="glyphicon glyphicon-calendar"></i><span> Products </span>
		</a>
		<ul class="sidebar-submenu">
			<li><a href="/listProducts" target="page">
			<span>View Products</span>
			</a>
		</ul>
		<ul class="sidebar-submenu">
			<li><a href="/addProductView" target="page">
			<span>Add Product</span>
			</a>
		</ul>
		</li>
		
		<li><a href="#">
			<i class="glyphicon glyphicon-envelope"></i><span> Mailbox</span>
		</a>
		</li>
		<li><a href="#">
			<i class="glyphicon glyphicon-sort"></i><span> Messages</span>
		</a>
		</li>
		<li><a href="#">
			<i class="glyphicon glyphicon-info-sign"></i><span> Contact</span>
		</a>
		</li>
		<li><a href="#">
			<i class="glyphicon glyphicon-cog"></i><span> Settings</span>
		</a>
		</li>
		<li><a href="#">
			<i class="glyphicon glyphicon-question-sign"></i><span> Help</span>
		</a>
		</li>
		
		
		<li>
			<form method="post" action="/logout" target="_top">
				<div class="container" align="center">
					<button type="submit" class="logoutButton">Logout</button>
				</div>
			</form>
		</li>
	</ul>
	</section>
	<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
	<script src="/js/sidebar-menu.js"></script>
	<script>
		$.sidebarMenu($('.sidebar-menu'))
	</script>
</body>
</html>