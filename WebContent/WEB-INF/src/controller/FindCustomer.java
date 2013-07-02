package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Customer;
import model.FindCustomerDB;
import model.ViewAllCustomersDB;


/**
 * Servlet that looks through the db for a user-specified customer.
 */
public class FindCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
    	
    	String message = "";
    	HttpSession session = request.getSession();
    	
    	//if only looking for a single customer, process that request
        if (request.getParameter("find") == null)
        {
        	//Get the search criteria
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            
            //look for the customer
            FindCustomerDB findDB = new FindCustomerDB(firstName, lastName, phone);
            Customer customer = new Customer();
            
            //try to locate the desired customer
			try {
				customer = findDB.find();
			} catch (SQLException e) {
				System.out.println("Database error, line 70, FindCustomer.java Servlet");
				e.printStackTrace();
			}
            
			//if the zip code is the default, the customer wasn't found 
            if (customer.getZip() != 0)
            {
                message = "Customer found. Below is the information.";
            }
            else
            {
                message = "Customer not found. Try again or add new customer.";
            }
            
            //set the attributes
            session.setAttribute("customer", customer);
            session.setAttribute("message", message);
            
            String url = "/customerResult.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else //if looking for all customers, process that request
        {
        	//create an ArrayList to store customers and their IDs
            ArrayList<Customer> customers = new ArrayList<Customer>();
            
            //DB object to search for customers
            ViewAllCustomersDB viewAll = new ViewAllCustomersDB();
            
            //try to access the db to retrieve all the customers
            try {
				customers = viewAll.getAll();
			} catch (SQLException e) {
				System.out.println("Database error, line 104, FindCustomer.java Servlet");
				e.printStackTrace();
			}
			
			//set the attribute to the list
            session.setAttribute("customers", customers);
            
            String url = "/viewAllCustomerResult.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
}
