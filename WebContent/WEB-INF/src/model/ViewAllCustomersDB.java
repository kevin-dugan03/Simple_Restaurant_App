package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Customer;

/**
 * Gets all the customers in the DB for viewing.
 * @author KDugan
 *
 */
public class ViewAllCustomersDB
{
    ArrayList<Customer> customers;
    
    public ViewAllCustomersDB()
    {
        customers = new ArrayList<Customer>();
    }
    
    /**
     * Returns a HashMap of customers to the JSP for display.
     * @return
     * @throws SQLException
     */
    public ArrayList<Customer> getAll() throws SQLException
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        
        String viewAll = "SELECT * FROM APP.CustomerTable";
        
        try
        {
        	InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/PizzaRestaurantDB");
        	conn = data.getConnection();
            
            //execute the query to retrieve the customers.
            ps = conn.prepareStatement(viewAll);           
            result = ps.executeQuery();
            
            while (result.next())
            {
            	Customer newCust = new Customer(result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                        result.getInt(8), result.getString(9));
                newCust.setId(result.getInt(1));
                customers.add(newCust);
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
        
        return customers;
    }
}
