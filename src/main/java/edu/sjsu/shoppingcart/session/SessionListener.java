package edu.sjsu.shoppingcart.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import edu.sjsu.shoppingcart.DAO.CustomerDAO;

public class SessionListener implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session=event.getSession();
		System.out.println("session attribute is "+session.getAttribute("customername"));
		
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session=event.getSession();
		if(new CustomerDAO().deleteCustomerSession((String)session.getAttribute("customerID"))){
			System.out.println("session deleted");
		}
		else{
			System.out.println("Couldnt clear the cart entries in Cart DB");
		}
		session.removeAttribute("customername");
		session.removeAttribute("customerID");
		
	}

}
