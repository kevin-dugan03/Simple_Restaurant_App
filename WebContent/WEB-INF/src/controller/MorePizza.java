package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that redirects to the place order menu when a customer wishes
 * to add more pizza to their order.
 */
public class MorePizza extends HttpServlet {
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
		
		//Get the customer data
		String first = request.getParameter("first");
        String last = request.getParameter("last");
        String phone = request.getParameter("phone");
        
        //set the fields to default to the current customer
        request.setAttribute("firstName", first);
        request.setAttribute("lastName", last);
        request.setAttribute("phone", phone);

        String url = "/newOrder.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

}
