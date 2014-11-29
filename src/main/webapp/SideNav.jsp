<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/ShoppingCart/Stylesheets/SideNav.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SideNav</title>
</head>
<body>
	
		<form action="/Symphony/Symphony/Symphony/List" method="post">
			<div class="sideNavigationBarContainer">
				<input id="submitSideNav" type="submit" name="category" value="Album"><br>
				<input id="submitSideNav" type="submit" name="category" value="Tracks"><br>
				<input id="submitSideNav" type="submit" name="category" value="Artists"><br>
				<input id="submitSideNav" type="submit" name="category" value="Genre"><br>
			</div>
		</form>
	
</body>
</html>