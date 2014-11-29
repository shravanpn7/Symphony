function validateLogin(){
	if(document.forms["LoginForm"]["Username"].value==null || document.forms["LoginForm"]["Username"].value==""){
		alert("Please enter Email address");
		return false;
	}
	else if(document.forms["LoginForm"]["Password"].value==null || document.forms["LoginForm"]["Password"].value==""){
		alert("Please enter Password");
		return false;
	}
	return true;
}

function validateSignUp(){
	if(document.forms["SignupForm"]["firstName"].value==null || document.forms["SignupForm"]["firstName"].value==""){
		alert("First Name is required");
		return false;
	}
	else if(document.forms["SignupForm"]["lastName"].value==null || document.forms["SignupForm"]["lastName"].value==""){
		alert("Last Name is required");
		return false;
	}
	else if(document.forms["SignupForm"]["Email"].value==null || document.forms["SignupForm"]["Email"].value==""){
		alert("Email is required");
		return false;
	}
	else if(document.forms["SignupForm"]["Password"].value==null || document.forms["SignupForm"]["Password"].value==""){
		alert("Please enter the password for your account");
		return false;
	}
	return true;
}

function validateQuantity(){
	var quantity=document.forms["addCartForm"]["quantity"].value;
	var regexp=/[0-4]|/;
	if(!regexp.test(quantity)){
		alert("not a valid quantity. Please enter a number between 1 and 4");
		return false;
	}
	return true;
}

function validateCreditCard(){
	var cardNumber=document.forms["checkoutForm"]["CreditCardNumber"].value;
	alert(cardNumber);
	var type=document.forms["checkoutForm"]["CreditCardType"].value;
	var expiry=document.forms["checkoutForm"]["DateOfExpiry"].value;
	var name=document.forms["checkoutForm"]["NameOnCard"].value;
	if(cardNumber.length != 16){
		alert("Invalid credit card number.");
		return false;
	}
	if(type==null || type==""){
		alert("Enter card type");
		return false;
	}
	if(expiry==null || expiry==""){
		alert("Enter date of card expiry");
		return false;
	}
	if(name==null || name==""){
		alert("Enter card holder name");
		return false;
	}
	return true;	
}

function validateCVN(){
	var cardNumber=document.forms["checkoutForm"]["CVN"].value;
	if(cardNumber.length != 3){
		alert("Invalid CVN.");
		return false;
	}
	return true;
}