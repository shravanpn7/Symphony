<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Symphony/Stylesheets/Webpages.css" type="text/css">
<link rel="stylesheet" href="/Symphony/Stylesheets/style.css" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<script type="text/javascript" src="/ShoppingCart/Javascript/Validation.js"></script>

<title>Sign Up</title>
</head>
<body style="background-image:url('Images/signupbg.jpg'); background-size:100%;">	
  <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" style="font-style:oblique;font-size:1.8em;" href="/Symphony/Home.jsp">Symphony</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
       
          </ul>
          <ul class="nav navbar-nav navbar-right">
              <li><a href="/Symphony/Login.jsp">Login</a></li>
              <li><a href="/Symphony/About.jsp">About</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->	
		      
	<br/><br/>

	<div class="container">
      <form class="form-signin" action="/Symphony/Symphony/Symphony/UserRegistration" method="post" onsubmit="return validateSignUp()">
        <h2 class="form-signin-heading" style="color:white;">Please sign up</h2>
        <input type="text" id="firstName" class="form-control" placeholder="First Name" required autofocus>
        <input type="text" id="middleName" class="form-control" placeholder="Middle Name" required>
        <input type="text" id="lastName" class="form-control" placeholder="Last Name" required>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required>
        <input type="text" id="street" class="form-control" placeholder="Street" required>
        <input type="text" id="apt" class="form-control" placeholder="Apt No." required>
        <input type="text" id="city" class="form-control" placeholder="City" required>
        <input type="text" id="state" class="form-control" placeholder="State" required>
        <input type="text" id="zip" class="form-control" placeholder="ZipCode" required>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me" > <span style="color:white;">Remember me</span>
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
      </form>

    </div> <!-- /container -->
	
</body>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</html>