<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Symphony/Stylesheets/Header.css" type="text/css">
<title>Header</title>
</head>
<body>
	
	<div class="logotext">
		<a class="logotext" href="/Symphony/Symphony/Symphony/Home.jsp">SYMPHONY</a>
	</div>
	
	<c:choose>
		<c:when test="${param.customer ne null}">
			<div class="welcometext">
				Welcome ${param.customer}
				<table>
				<tr> 
				<td>
					<form action="/Symphony/Symphony/Symphony/ViewMyCart" method="post">
					<input id="headersubmit" type="submit" name="MyCart" value="MyCart">
					</form>
				</td>
				<td>
				<form action="/Symphony/Symphony/Symphony/Logout" method="post">
					<input id="headersubmit" type="submit" name="Logout" value="Logout">
				</form>
				</td></tr>
				</table>
			</div>
		</c:when>
		<c:otherwise>
			<div class="welcometext">
				Welcome <br>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>