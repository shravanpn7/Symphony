package edu.sjsu.shoppingcart.POJO;

public class CreditCard {

	private String creditCardNumber=null;
	private String type=null;
	private String dateOfExpiry=null;
	private String nameOnCard=null;
	private String customerID=null;
	private String cvn=null;
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCvn() {
		return cvn;
	}
	public void setCvn(String cvn) {
		this.cvn = cvn;
	}
}
