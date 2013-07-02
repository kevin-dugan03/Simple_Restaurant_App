package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Customer;

/**
 * Accesses the DB to insert a new customer.
 * @author KDugan
 */
public class CustomerDB
{
    Customer cust;
    String message;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet result = null;
    
    /**
     * Constructor.
     * @param customer the customer to add
     */
    public CustomerDB(Customer customer)
    {
        cust = customer;
    }
    
    /**
     * adds a customer to the DB.
     * @return
     * @throws SQLException
     */
    public String addToDB() throws SQLException
    {
        String addCustomer = "INSERT INTO APP.CustomerTable (firstName, lastName, email, street, city, state, zip, phone) " +
        		"VALUES (?,?,?,?,?,?,?,?)";

        //access the database, insert the new customer
        try
        {        	
        	InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/PizzaRestaurantDB");
        	conn = data.getConnection();

            //insert the customer data into DB
            ps = conn.prepareStatement(addCustomer);
            ps.setString(1, cust.getFirst());
            ps.setString(2, cust.getLast());
            ps.setString(3, cust.getEmail());
            ps.setString(4, cust.getStreet());
            ps.setString(5, cust.getCity());
            ps.setString(6, cust.getState());
            ps.setInt(7, cust.getZip());
            ps.setString(8, cust.getPhone()); 
            
            //determine the appropriate message to return
            if (ps.executeUpdate() > 0)
            {
            	message = "Customer successfully added";
            }
            else
            {
            	message = "Customer unable to be added";
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
        
        return message;
    }
    
    /**
     * Retrieves the customer ID of the customer just added. Returns 0 is no customer is found.
     * @return customer ID
     * @throws SQLException
     */
    public int retrieveID() throws SQLException
    {
    	String getID = "SELECT customerID FROM APP.CustomerTable WHERE email=?";
    	
    	//access the database, insert the new customer
        try
        {        	
        	InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/PizzaRestaurantDB");
        	conn = data.getConnection();
            
        	//look for the customerID
            ps = conn.prepareStatement(getID);
            ps.setString(1, cust.getEmail());
            result = ps.executeQuery();
            
            while (result.next())
            {
            	//return the ID if found
            	return result.getInt(1);
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
        
        //return 0 if not found
        return 0;
    }
}
