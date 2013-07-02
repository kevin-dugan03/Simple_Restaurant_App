package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AddPizzaDB;
import model.FindCustomerDB;
import objects.Pizza;
import objects.Customer;


/**
 * Servlet that adds a pizza to an order.
 */
public class AddPizza extends HttpServlet {
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
    	
    	HttpSession session = request.getSession();
    	Customer customer = (Customer) session.getAttribute("customer");
    	
    	//if there is no active
    	if (customer == null)
    	{
    		//store the customer data
        	String first = request.getParameter("firstName");
        	String last = request.getParameter("lastName");
        	String phone = request.getParameter("phone");
        	
        	//try to find the customer
        	FindCustomerDB findDB = new FindCustomerDB(first, last, phone);
            
            //look for the customer in the database
    		try {
    			customer = findDB.find();
    		} catch (SQLException e1) {
    			System.out.println("Database error, line 96 in Pizza.java Servlet");
    			e1.printStackTrace();
    		}
    	}
    	
    	String customerMessage = "";

		//if the customer zip code is not 0, then the customer was found
        if (customer.getZip() != 0)
        {
            customerMessage = "";
            
            //Get the size and crust types for the pizza ordered
            String size = request.getParameter("group1");
            String crust = request.getParameter("group2");
            String[] toppings;
            
            //if no toppings were selected, then indicate that in the message
            if (request.getParameterValues("group3") == null)
            {
            	toppings = new String[1];
            	toppings[0] = "No Toppings Selected";
            }
            else
            {
            	toppings = request.getParameterValues("group3");
            }
            
            //make a new Pizza bean with the parameters
            Pizza pizza = new Pizza(size, crust, toppings);

            //try to add the pizza to the appropriate order
            AddPizzaDB add = new AddPizzaDB(pizza);
            try {
    			add.addToOrder();
    		} catch (SQLException e) {
    			System.out.println("Database error, line 140 of Pizza.java Servlet");
    			e.printStackTrace();
    		}
            
    		//set the session variables and forward the response to the correct page
            String pizzaMessage = "Pizza has been added to your order";
            session.setAttribute("pizza", pizza);
            session.setAttribute("pizzaMessage", pizzaMessage);
            session.setAttribute("customer", customer);
            session.setAttribute("customerMessage", customerMessage);
            
            String url = "/viewOrder.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else //customer wasn't found and we need to return without further processing
        {
            customerMessage = "Customer not found. \nCustomer needs to be added to the database.";
            session.setAttribute("customer", customer);
            session.setAttribute("customerMessage", customerMessage);
            
            String url = "/viewOrder.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
}
