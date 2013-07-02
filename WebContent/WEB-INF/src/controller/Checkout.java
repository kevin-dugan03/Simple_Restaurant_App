package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Order;

import model.ViewSingleOrderDB;

/**
 * Servlet that is used to place an order after all pizzas have been added.
 */
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//Store the customer information to be used in checkout
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String phone = request.getParameter("phone");
        
        //Retrieve the current order for the customer
        ViewSingleOrderDB viewSingle = new ViewSingleOrderDB(first, last, phone);
        Order order = new Order();
        
        //try to retrieve the current order
		try {
			order = viewSingle.getOrder();
		} catch (SQLException e) {
			System.out.println("Database error, line 50 of Checkout.java Servlet");
			e.printStackTrace();
		}
        
		//set the session Order variable
        session.setAttribute("order", order);

        String url = "/placeOrder.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}
}
