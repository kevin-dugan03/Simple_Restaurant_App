package controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Customer;
import model.CustomerDB;
import model.FindCustomerDB;

/**
 * Servlet that adds a customer to the DB
 * @author KDugan
 */
@SuppressWarnings("serial")
public class AddCustomer extends HttpServlet {
	
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	Customer customer = new Customer();
    	String message = "";

        //create a new customer with the parameter data
    	FindCustomerDB findCustomer = new FindCustomerDB(request.getParameter("first_name"), 
    			request.getParameter("last_name"), request.getParameter("phone"));

    	//check if the customer already exists
        try {
			customer = findCustomer.find();
		} catch (SQLException e1) {
			System.out.println("Database error, line 66, AddCustomer.java Servlet");
			e1.printStackTrace();
		}
		
		//if the customer already exists, then set the appropriate message
		if (customer.getZip() != 0)
		{
			message = "Customer already exists.";
		}
		else //add the customer to the db
		{
			//call the DB class to execute on the new customer
			customer = new Customer(request.getParameter("first_name"), request.getParameter("last_name"),
					request.getParameter("email_address"), request.getParameter("street_address"), 
					request.getParameter("city"), request.getParameter("state"), 
					Integer.parseInt(request.getParameter("zip")), request.getParameter("phone"));
	        CustomerDB dbAccess = new CustomerDB(customer);

	        //try to access the db and add the customer to the db
			try {
				message = dbAccess.addToDB();		
				customer.setId(dbAccess.retrieveID());

			} catch (SQLException e) {
				System.out.println("Database error in AddCustomer.java Servlet");
				e.printStackTrace();
			}
		}
		
		//set the session attributes.
        session.setAttribute("customer", customer);
        session.setAttribute("message", message);

        //return the data.
        String url = "/customerResult.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
