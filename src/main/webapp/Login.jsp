<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Symphony/Stylesheets/Webpages.css" type="text/css">
<title>LogIn</title>
<script type="text/javascript" src="/Symphony/Javascript/Validation.js"></script>
</head>
<body>

	<%String customername=(String)request.getSession(false).getAttribute("customername"); %>
	
	<c:choose>
		<c:when test="${customername eq null}">
			<%@include file="/Header.jsp" %>
		</c:when>
		<c:otherwise>
			<jsp:include page="/Header.jsp">
				<jsp:param value="${customername}" name="customer"/>
			</jsp:include>
		</c:otherwise>
	</c:choose>
	
	<div class="login">
		<p>${it.Message}</p>
		<form name="LoginForm" action="/Symphony/Symphony/Symphony/LogIn" method="post" onsubmit="return validateLogin()">
			<table>
			<tr><td>UserId</td><td><input type="text" name="Username"></td></tr>
			<tr><td>Password</td><td><input type="password" name="Password"></td></tr>
			<tr><td><input id="submit" type="submit" name="Submit" value="LogIn"></td>	
		</form>
		<td><a href="/Symphony/Symphony/Symphony/UserRegistration">SignUp</a></td></tr>
		</table>
	</div>
</body>
</html>
