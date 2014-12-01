package edu.sjsu.symphony.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import redis.clients.jedis.Jedis;
import edu.sjsu.symphony.DB.DBConnection;
import edu.sjsu.symphony.POJO.Order;
import edu.sjsu.symphony.POJO.Product;

public class CustomerDAO {

	public String validateCredentials(String username, String password){
		System.out.println(username);
		System.out.println(password);
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		String query="select Password from Customer where Email=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, username);
			result=stmt.executeQuery();
			if(result.next()){
				if(result.getString("Password").equalsIgnoreCase(password))
					System.out.println("Valid User");
					return "valid user";
			}
			System.out.println("Invalid User");
			return "invalid user";
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}
	
	public String createCustomer(String firstName, String middleName, String lastName, String email, String street, String aptNo, String city, String state, String zipcode, String Password){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		String query="insert into Customer values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, this.getCustomerID(db));
			stmt.setString(2, firstName);
			stmt.setString(3, middleName);
			stmt.setString(4, lastName);
			stmt.setString(5, email);
			stmt.setString(6, street);
			stmt.setString(7, aptNo);
			stmt.setString(8, city);
			stmt.setString(9, state);
			stmt.setString(10, zipcode);
			stmt.setString(11, Password);
			stmt.setString(12, "Customer");
			if(stmt.executeUpdate()>=1){
				System.out.println("user created");
				return "User Created";
			}
			System.out.println("User not created");
			return "Unable to create new user. Please try again!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}
	
	public String getCustomerID(Connection db){
		Random randomNumber=new Random();
		String customerId=String.format("%04d", randomNumber.nextInt(99999));
		try {
			PreparedStatement stmt=db.prepareStatement("select * from Customer where CustomerID=?");
			stmt.setString(1, customerId);
			ResultSet result=stmt.executeQuery();
			if(result.next())
				getCustomerID(db);
			else 
				return customerId;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public String getCustomerName(String email) {
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		String query="select FirstName, MiddleName, LastName from Customer where Email=? ";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, email);
			result=stmt.executeQuery();
			if(result.next())
				return result.getString("FirstName")+" "+result.getString("MiddleName")+" "+result.getString("LastName");
			return "User doesnot exist";
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return "error";
			}
		}	
	}
	
	public String getCustomerIDFromName(String customerName){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		String[] customerNameArray=customerName.split(" ");
		String query="select CustomerID from Customer where FirstName=? and MiddleName=? and LastName=?";
		try {
			stmt=db.prepareStatement(query);
			if(customerNameArray.length==2){
				stmt.setString(1,customerNameArray[0]);
				stmt.setString(2, "");
				stmt.setString(3, customerNameArray[2]);
			}
			else{
				stmt.setString(1,customerNameArray[0]);
				stmt.setString(2, customerNameArray[1]);
				stmt.setString(3, customerNameArray[2]);
			}
			result=stmt.executeQuery();
			if(result.next())
				return result.getString("CustomerID");
			return "User doesnot exist";
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return "error";
			}
		}	
	}
	
	public boolean addToCart(String customerName, String customerID, String productID, int quantity){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		int result=0;
		String query="insert into Cart values(?,?,?,?,?)";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, customerID);
			stmt.setString(2, customerName);
			stmt.setString(3, productID);
			stmt.setInt(4, quantity);
			stmt.setDouble(5, new ProductDAO().getPrice(productID) * quantity);
			result=stmt.executeUpdate();
			if(result>=1)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public boolean removeFromCart(String productID, String customerId){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		int result=0;
		String query="delete from Cart where CustomerID=? and ProductID=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, customerId);
			stmt.setString(2, productID);
			result=stmt.executeUpdate();
			if(result>=1)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public List<Product> viewCart(String customerID){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<Product> productList=new ArrayList<Product>();
		String query="select ProductID, Quantity from Cart where CustomerID=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, customerID);
			result=stmt.executeQuery();
			while(result.next()){
				String productID=result.getString("ProductID");
				System.out.println("Product ID is: "+productID);
				Product tempProduct=new ProductDAO().getProductfromID(productID);
				int quantity=result.getInt("Quantity");
				System.out.println("Quantity is:"+ quantity);
				tempProduct.setQuantity(quantity);
				tempProduct.setTotalPrice(tempProduct.getPrice()*quantity);
				productList.add(tempProduct);
			}
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public boolean deleteCustomerSession(String customerID){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		int result=0;
		String query="delete from Cart where CustomerID=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, customerID);
			result=stmt.executeUpdate();
			if(result>=1)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public String getCustomerAddress(String customerID){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		String query="select Street, Aptno, City, State, Zipcode from Customer where CustomerID=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1,customerID);
			result=stmt.executeQuery();
			if(result.next())
				return result.getString("Street")+", Aptno "+result.getString("Aptno")+", "+result.getString("City")+", "+result.getString("State")+", "+result.getString("Zipcode");
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	public boolean addCustomerAddress(String customerID, String street, String aptNo, String city, String state, String zipcode) {
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		int result=0;
		String query="update Customer set Street=?, Aptno=?, City=?, State=?, Zipcode=? where CustomerID=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1,street);
			stmt.setString(2,aptNo);
			stmt.setString(3,city);
			stmt.setString(4,state);
			stmt.setString(5,zipcode);
			stmt.setString(5, customerID);
			result=stmt.executeUpdate();
			if(result>=1)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public double getGrandTotal(List<Product> productList){
		double grandTotal=0.0;
		for(Product tmpProduct: productList){
			grandTotal+=tmpProduct.getQuantity()*tmpProduct.getTotalPrice();
		}
		return grandTotal;
	}

	public boolean addOrderHistory(String customerID, String customerName, String address, String creditCard) {
		Jedis jedis=new Jedis("localhost");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		List<Product> productList=this.viewCart(customerID);
		String productString=this.formatProductView(productList);
		String orderNo="";
		do{
			int randomnumber=new Random().nextInt(999999999);
			orderNo=String.format("%09d", randomnumber);
		}while(!jedis.hgetAll(orderNo).isEmpty());
		
		System.out.println("Order Number" + orderNo);
		jedis.hset(orderNo, "Order Date", dateFormat.format(date));
		jedis.hset(orderNo, "CustomerID", customerID);
		jedis.hset(orderNo, "CustomerName", customerName);
		jedis.hset(orderNo, "Product List", productString);
		jedis.hset(orderNo, "Total Price", ""+this.getGrandTotal(productList));
		jedis.hset(orderNo, "Shipping Address", address);
		jedis.hset(orderNo, "Credit Card Number", creditCard);
		jedis.close();
		return true;
	}

	private String formatProductView(List<Product> productList) {
		String productListHtml="<html><body><table border=\"1%\">";
		for(Product tempProduct: productList){
			productListHtml+="<tr><td>"+tempProduct.getProductName()+"</td>"+
					"<td>"+tempProduct.getQuantity()+"</td>"+
					"<td>"+tempProduct.getTotalPrice()+"</td></tr>";
		}
		return productListHtml+"</table></body></html>";
	}

	public List<Order> getOrderHistory(String customerID) {
		List<Order> orderList=new ArrayList<Order>();
		Jedis db=new DBConnection("redis").getRedisDBConnection();
		Set<String> keys=db.keys("*");
		Iterator<String> setIterator=keys.iterator();
		while(setIterator.hasNext()){
			String tempKey=setIterator.next();
			if(customerID.equalsIgnoreCase(db.hget(tempKey,"CustomerID"))){
				Order tempOrder=new Order();
				tempOrder.setOrderNumber(tempKey);
				tempOrder.setOrderDate(db.hget(tempKey, "Order Date"));
				tempOrder.setCreditCardNumber(db.hget(tempKey, "Credit Card Number"));
				tempOrder.setShippingaddress(db.hget(tempKey, "Shipping Address"));
				tempOrder.setProductList(db.hget(tempKey, "Product List"));
				tempOrder.setPrice(db.hget(tempKey, "Total Price"));
				orderList.add(tempOrder);
			}
		}
		return orderList;
	}

	public String validateCustomerId(String customerID, String password) {
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		String query="select Password from Customer where CustomerId=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, customerID);
			result=stmt.executeQuery();
			if(result.next()){
				System.out.println("Password"+result.getString("Password"));
				if(result.getString("Password").equalsIgnoreCase(password))
					System.out.println("Valid User");
					return "valid user";
			}
			return "invalid user";
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}
}
