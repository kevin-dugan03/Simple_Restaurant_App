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

import objects.Order;
import model.ViewAllOrdersDB;

/**
 * Servlet implementation class ViewAllOrders
 */
public class ViewAllOrders extends HttpServlet {
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
		
		//create a list of orders
		ArrayList<Order> orders = new ArrayList<Order>();
		
		//db object to find the orders
        ViewAllOrdersDB viewAll = new ViewAllOrdersDB();
        
        //try to retrieve all the orders from the db
        try {
			orders = viewAll.getAll();
		} catch (SQLException e) {
			System.out.println("Database error, line 44, ViewAllOrder.java Servlet");
			e.printStackTrace();
		}

        session.setAttribute("orders", orders);
        String url = "/viewAllOrdersResult.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}
}
