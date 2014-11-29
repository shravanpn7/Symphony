<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="edu.sjsu.shoppingcart.POJO.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Symphony/Stylesheets/Webpages.css" type="text/css">
<title>Welcome</title>
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
	<%@include file="/SideNav.jsp" %>
	
	<div class="breaker"></div>
	
	<div class="productTilesContainer">
	
	<ul class="productTiles">
		<c:forEach var="product" items="${it.ProductList}">
		<li>
			<a href="/Symphony/Symphony/Symphony/Product/${it.Category}/${product}">
					<div>
						<h4>${product}</h4>
					</div>
			</a>
		</li>
		</c:forEach>
	</ul>
	
	</div>
</body>
</html>