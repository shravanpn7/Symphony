package edu.sjsu.shoppingcart.POJO;

public class Order {

	private String orderNumber;
	private String creditCardNumber;
	private String shippingaddress;
	private String productList;
	private String price;
	private String orderDate;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getShippingaddress() {
		return shippingaddress;
	}
	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}
	public String getProductList() {
		return productList;
	}
	public void setProductList(String productList) {
		this.productList = productList;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}
