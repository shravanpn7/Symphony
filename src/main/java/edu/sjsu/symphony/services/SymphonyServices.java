package edu.sjsu.symphony.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

import edu.sjsu.symphony.DAO.CategoryDAO;
import edu.sjsu.symphony.DAO.CreditCardDAO;
import edu.sjsu.symphony.DAO.CustomerDAO;
import edu.sjsu.symphony.DAO.ProductDAO;
import edu.sjsu.symphony.POJO.CreditCard;
import edu.sjsu.symphony.POJO.Order;
import edu.sjsu.symphony.POJO.Product;


@Path("/Symphony")
public class SymphonyServices {
	
	Map<String, Object> userMap=new HashMap<String, Object>();

	@Path("/LogIn")
	@POST
	public Response login(@FormParam("Username") String customerID, @FormParam("Password") String password, @Context HttpServletRequest request){
		String validID=new CustomerDAO().validateCustomerId(customerID, password);
		if(validID.equalsIgnoreCase("error")){
			userMap.put("Error", "Unable to connect to database. Try again later.");
			return Response.ok(new Viewable("/Error.jsp", userMap)).build();
		}
		if(validID.equalsIgnoreCase("Invalid User")){
			return Response.ok(new Viewable("/Home.jsp", userMap)).build();
		}
		HttpSession session= request.getSession(true);
		session.setAttribute("customerID", customerID);
		System.out.println("session"+session.getAttribute("customerID"));
		List<String> itemIdList=new CategoryDAO().getTopNList(customerID);
		userMap.put("Category", "Top N Recommendations");
		userMap.put("ProductList", itemIdList);
		return Response.ok(new Viewable("/Home.jsp", userMap)).build();
		
	}
	
	@Path("/UserRegistration")
	@POST
	public Response userRegistration(@FormParam("firstName") String firstName, @FormParam("middleName") String middleName, @FormParam("lastName") String lastName, @FormParam("email") String email, @FormParam("street") String street, @FormParam("aptNo") String aptNo, @FormParam("city") String city, @FormParam("state") String state, @FormParam("zipcode") String zipcode, @FormParam("password") String Password){
		String message=new CustomerDAO().createCustomer(firstName, middleName, lastName, email, street, aptNo, city, state, zipcode, Password);
		if(message.equalsIgnoreCase("User Created")){
			userMap.put("Message", message);
			return Response.ok(new Viewable("/Login.jsp", userMap)).build();
		}
		userMap.put("Error", "Error in adding user. Please try again later.");
		return Response.ok(new Viewable("/Error.jsp", userMap)).build();
	}
	
	@Path("/List/{category}")
	@GET
	public Response getProductsInCategory(@PathParam("category") String category){
		if(category.equalsIgnoreCase("Album"))
		{
			System.out.println("######## Album #############");
			List<String> productList=new CategoryDAO().getItemList(category);
			userMap.put("Category", category);
			userMap.put("ProductList", productList);
		}
		else if(category.equalsIgnoreCase("Tracks"))
		{
			System.out.println("######## Track #############");
			List<String> productList=new CategoryDAO().getTrackList(category);
			userMap.put("Category", category);
			userMap.put("ProductList", productList);
		}
		else if(category.equalsIgnoreCase("Artists"))
		{
			System.out.println("######## Artists #############");
			List<String> productList=new CategoryDAO().getArtistsList(category);
			userMap.put("Category", category);
			userMap.put("ProductList", productList);
		}
		else if(category.equalsIgnoreCase("Genre"))
		{
			System.out.println("######## Artists #############");
			List<String> productList=new CategoryDAO().getGenreList(category);
			userMap.put("Category", category);
			userMap.put("ProductList", productList);
		}
		System.out.println("***********userMap***** "+userMap);
		return Response.ok(new Viewable("/Home.jsp", userMap)).build();
		
	}
	
	@Path("/Product/{category}/{productId}")
	@GET
	public Response getProductDetails(@PathParam("category") String category, @PathParam("productId") String productId){
		Product product=new ProductDAO().getProductfromID(productId);
		if(product==null){
			userMap.put("Error", "Unable to retreive the product details at this moment. Please try again later.");
			return Response.ok(new Viewable("/Error.jsp", userMap)).build();
		}
		return Response.ok(new Viewable("/Product.jsp", product)).build();
	}
	
	@Path("/ViewMyCart")
	@POST
	public Response viewCart(@Context HttpServletRequest request){
		HttpSession session=request.getSession(false);
		String customerID= (String)session.getAttribute("customerID");
		CustomerDAO customer=new CustomerDAO();
		List<Product> productList=customer.viewCart(customerID);
		if(productList==null){
			userMap.put("Error", "Unable to retreive the product details at this moment. Please try again later.");
			return Response.ok(new Viewable("/Error.jsp", userMap)).build();
		}
		double grandTotal=customer.getGrandTotal(productList);
		userMap.put("GrandTotal", grandTotal);
		userMap.put("ProductList", productList);
		return Response.ok(new Viewable("/MyCart.jsp", userMap)).build();
	}
	
	@Path("/AddToMyCart/{productId}")
	@POST
	public void addToCart(@PathParam("productId") String productID, @FormParam("quantity") String quantity, @Context HttpServletRequest request){
		HttpSession session=request.getSession(false);
		String customerName= (String)session.getAttribute("customername");
		String customerID=(String)session.getAttribute("customerID");
		int intQuantity=Integer.parseInt(quantity);
		if(intQuantity!=0){
			new CustomerDAO().addToCart(customerName, customerID, productID, intQuantity);
		}
	}
	
	@Path("/DeleteFromCart/{productId}")
	@GET
	public Response deleteFromCart(@PathParam("productId") String productID, @Context HttpServletRequest request){
		HttpSession session=request.getSession(false);
		String customerId=(String)session.getAttribute("customerID");
		new CustomerDAO().removeFromCart(productID, customerId);
		return viewCart(request);
	}
	
	@Path("/CheckOut")
	@POST
	public Response checkout(@Context HttpServletRequest request){
		HttpSession session=request.getSession(false);
		String customerID=(String) session.getAttribute("customerID");
		List<CreditCard> cardList=new CreditCardDAO().getCreditCardDetails(customerID);
		if(!cardList.isEmpty())
			userMap.put("CardList", cardList);
		String address=new CustomerDAO().getCustomerAddress(customerID);
		if(address!=null){
			userMap.put("Address", address);
			if(address.equalsIgnoreCase("error")){
				userMap.put("Error", "Couldnot retrieve address from your profile. Please try again later.");
				return Response.ok(new Viewable("/Error.jsp", userMap)).build();
			}
		}
		return Response.ok(new Viewable("/CheckOut.jsp", userMap)).build();
	}
	
	@Path("/AddAddress")
	@POST
	public Response addAddress(@Context HttpServletRequest request, @FormParam("street") String street, @FormParam("aptNo") String aptNo, @FormParam("city") String city,@FormParam("state") String state, @FormParam("zipcode") String zipcode){
		HttpSession session=request.getSession(false);
		String customerID= (String)session.getAttribute("customerID");
		if(new CustomerDAO().addCustomerAddress(customerID, street, aptNo, city, state, zipcode))
			return checkout(request);
		else{
			userMap.put("Error", "Couldnot add address to your profile. Please try again later.");
			return Response.ok(new Viewable("/Error.jsp", userMap)).build();
		}
	}
	
	@Path("/AddCard")
	@POST
	public Response addCard(@Context HttpServletRequest request, @FormParam("CreditCardNumber") String cardNumber, @FormParam("CreditCardType") String cardtype, @FormParam("DateOfExpiry") String expiryDate, @FormParam("NameOnCard") String holderName){
		HttpSession session=request.getSession(false);
		String customerID=(String)session.getAttribute("customerID");
		if(new CreditCardDAO().addNewCard(customerID, cardNumber, cardtype, expiryDate, holderName))
			return checkout(request);
		else{
			userMap.put("Error", "Couldnot add card to your profile. Please try again later.");
			return Response.ok(new Viewable("/Error.jsp", userMap)).build();
		}
	}
	
	@Path("/Purchase")
	@POST
	public Response processOrder(@FormParam("address") String address, @FormParam("card") String creditCard, @Context HttpServletRequest request){
		HttpSession session=request.getSession(false);
		String customerName=(String)session.getAttribute("customername");
		String customerID=(String)session.getAttribute("customerID");
		if(address==null || address.equalsIgnoreCase("") || creditCard==null || creditCard.equalsIgnoreCase("")){
			userMap.put("Error", "Please enter the shipping address and credit card number to proceed with checkout");
			return Response.ok(new Viewable("/Error.jsp", userMap)).build();
		}
		boolean isHistoryCreated=new CustomerDAO().addOrderHistory(customerID, customerName, address, creditCard);
		if(!isHistoryCreated){
			userMap.put("Error", "Sorry, couldn't process your order. Try again later");
			return Response.ok(new Viewable("/Error.jsp", userMap)).build();
		}
		userMap.put("Message", "Your order is placed successfully. Thank You for shopping at Ametronics.");
		return Response.ok(new Viewable("/Success.jsp",userMap)).build();
	}
	
	@Path("/ViewOrderHistory")
	@POST
	public Response orderHistory(@Context HttpServletRequest request){
		HttpSession session=request.getSession(false);
		String customerID=(String)session.getAttribute("customerID");
		List<Order> orderList=new CustomerDAO().getOrderHistory(customerID);
		if(orderList.isEmpty()){
			userMap.put("Message", "You don't have any orders placed yet.");
			return Response.ok(new Viewable("/OrderHistory.jsp",userMap)).build();
		}
		userMap.put("OrderList", orderList);
		return Response.ok(new Viewable("/OrderHistory.jsp",userMap)).build();
	}
	
	@Path("/Logout")
	@POST
	public Response logout(@Context HttpServletRequest request){
		request.getSession(false).invalidate();
		return Response.ok(new Viewable("/Home.jsp", userMap)).build();
	}

  @Path("/Search")
  @POST
  public Response search(@QueryParam("category") String category,
                         @QueryParam("search") String searchString) {    
    List<String> searchResults = new CategoryDAO().getSearchDetails(category, searchString); 
    if(searchResults.isEmpty()) {
      userMap.put("Message", "No results found");
      return Response.ok(new Viewable("/Search.jsp", userMap)).build();
    }
    userMap.put("Results", searchResults);
    return Response.ok(new Viewable("/Search.jsp", userMap)).build();
    }
}