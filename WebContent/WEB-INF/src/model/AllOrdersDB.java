package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Customer;
import objects.Order;
import objects.Pizza;

/**
 * Accesses the DB and retrieves all the orders for viewing.
 * This does not work right yet.
 * @author KDugan
 *
 */
public class AllOrdersDB
{
    ArrayList<Order> orders;
    HashMap<Integer, Customer> customers;
    HashMap<Integer, Pizza> pizzas;
    
    
    public AllOrdersDB()
    {
        orders = new ArrayList<Order>();
        customers = new HashMap<Integer, Customer>();
        pizzas = new HashMap<Integer, Pizza>();
    }
    
    /**
     * Returns a list of orders to the jsp for viewing
     * @return
     * @throws SQLException
     */
    public  ArrayList<Order> getAllOrders() throws SQLException
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        String viewAll = "SELECT * FROM APP.OrderTable";
        String getCustomer = "SELECT * FROM APP.CustomerTable";
        
        try
        {
        	InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/PizzaRestaurantDB");
        	conn = data.getConnection();
            
            //get all the orders
            ps = conn.prepareStatement(viewAll);           
            result = ps.executeQuery();
            
            while (result.next())
            {
                orders.add(new Order(result.getInt(1), result.getInt(2), result.getDouble(3)));
            }
            
            //get all the customer data
            ps = conn.prepareStatement(getCustomer);
            result = ps.executeQuery();
            
            while (result.next())
            {
                customers.put(result.getInt(1), new Customer(result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                        result.getInt(8), result.getString(9)));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
        
        //return the orders
        return orders;
    }
}
