<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="edu.sjsu.symphony.POJO.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<link rel="stylesheet" href="/Symphony/Stylesheets/homepage.css" type="text/css">
<link href="/Symphony/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<title>Symphony - Home</title>
</head>
<body>

	<%String customerId=(String)request.getSession(false).getAttribute("customerID");
	%>
	
	 <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
		<c:choose>
          	<c:when test="${customerId ne null}">
          		<a class="navbar-brand" style="font-style:oblique;font-size:1.8em;" href="/Symphony/Home.jsp">Symphony</a>
          	</c:when>
          	<c:otherwise>
          	    <a class="navbar-brand" style="font-style:oblique;font-size:1.8em;" href="/Symphony/Login.jsp">Symphony</a>
          	</c:otherwise>
          </c:choose>        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
           
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	  <li><a href="/Symphony/Feedback.jsp">Feedback</a></li>
              <li>
              	<form action="/Symphony/Symphony/Symphony/ViewMyCart" method="post">
					<input id="headersubmit" type="submit" name="MyCart" value="MyCart">
				</form>
              </li>
              <li><form action="/Symphony/Symphony/Symphony/Logout" method="post">
					<input id="headersubmit" type="submit" name="Logout" value="Logout">
				</form></li>
          </ul>
        </div><!-- /.nav-collapse -->
        </div>
    </nav><!-- /.navbar -->	
    
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="/Symphony/Symphony/Symphony/List/Album"> Album</a></li>
            <li><a href="/Symphony/Symphony/Symphony/List/Tracks">Tracks</a></li>
            <li><a href="/Symphony/Symphony/Symphony/List/Artists">Artists</a></li>
            <li><a href="/Symphony/Symphony/Symphony/List/Genre">Genre</a></li>
          </ul>
          
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
         
     		<h1 class="page-header">${it.Category}</h1> 
    	 <div class="music">
     	
		     
		<form name="checkoutForm" action="/Symphony/Symphony/Symphony/AddCard" method="post" onsubmit="return validateCreditCard()">
		<h3>Enter Credit Card Details:</h3><br>
		<table>
			<tr><td>Credit Card Number</td><td><input type="text" name="CreditCardNumber"></td></tr>
			<tr><td>Credit Card Type</td><td><input type="text" name="CreditCardType"></td></tr>
			<tr><td>Date of Expiry</td><td><input type="text" name="DateOfExpiry"></td></tr>
			<tr><td>Name on Card</td><td><input type="text" name="NameOnCard"></td></tr>
			<tr><td><input type="submit" value="Add Card"></td></tr>
		</table>
		</form>
			
			</div>
    	</div>
    </div>
   </div>
    

    
	<%String customername=(String)request.getSession(false).getAttribute("customername"); %>
	
</body>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</html>