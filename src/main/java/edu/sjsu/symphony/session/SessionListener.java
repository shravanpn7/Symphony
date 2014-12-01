package edu.sjsu.symphony.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import edu.sjsu.symphony.DAO.CustomerDAO;

public class SessionListener implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session=event.getSession();
		System.out.println("session attribute is "+session.getAttribute("customerID"));
		
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session=event.getSession();
		String customerId=(String)session.getAttribute("customerID");
		String[] values=customerId.split("-");
		if(new CustomerDAO().deleteCustomerSession(values[0].trim())){
			System.out.println("session deleted");
		}
		else{
			System.out.println("Couldnt clear the cart entries in Cart DB");
		}
		session.removeAttribute("customerID");
		
	}

}
